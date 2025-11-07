package net.withrage.simpleexcavators.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.HashSet;
import java.util.Set;

public class ExcavatorItem extends Item {

    public ExcavatorItem(ToolMaterial material,
                         int attackDamage,
                         float attackSpeed,
                         Settings settings) {
        super(settings.shovel(material, attackDamage, attackSpeed));
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        boolean result = super.postMine(stack, world, state, pos, miner);

        if (!world.isClient() && miner instanceof PlayerEntity player) {
            if (!state.isIn(BlockTags.SHOVEL_MINEABLE)) return result;
            if (player.isSneaking()) return result;

            Direction face = ExcavatorMiningContext.consumeLastHitFace(player);
            if (face == null) face = fallbackFace(player);

            mineExtraBlocks((ServerWorld) world, player, stack, pos, face);
        }
        return result;
    }

    private static Direction fallbackFace(PlayerEntity p) {
        float pitch = p.getPitch();
        if (pitch > 60f)  return Direction.DOWN;
        if (pitch < -60f) return Direction.UP;
        return p.getHorizontalFacing();
    }

    private void mineExtraBlocks(ServerWorld world, PlayerEntity player, ItemStack excavatorStack,
                                 BlockPos origin, Direction hitFace) {
        Set<BlockPos> targets = get3x3Positions(origin, hitFace);
        for (BlockPos targetPos : targets) {
            if (targetPos.equals(origin)) continue;
            breakOneExtraBlock(world, player, excavatorStack, targetPos);
        }
    }

    private Set<BlockPos> get3x3Positions(BlockPos origin, Direction face) {
        Set<BlockPos> out = new HashSet<>();
        if (face == Direction.UP || face == Direction.DOWN) {
            for (int dx=-1; dx<=1; dx++) for (int dz=-1; dz<=1; dz++) out.add(origin.add(dx,0,dz));
        } else if (face == Direction.NORTH || face == Direction.SOUTH) {
            for (int dx=-1; dx<=1; dx++) for (int dy=-1; dy<=1; dy++) out.add(origin.add(dx,dy,0));
        } else {
            for (int dz=-1; dz<=1; dz++) for (int dy=-1; dy<=1; dy++) out.add(origin.add(0,dy,dz));
        }
        return out;
    }

    private void breakOneExtraBlock(ServerWorld world, PlayerEntity player, ItemStack excavatorStack, BlockPos targetPos) {
        BlockState target = world.getBlockState(targetPos);
        if (target.isAir() || target.getHardness(world, targetPos) < 0.0F) return;
        if (!target.isIn(BlockTags.SHOVEL_MINEABLE)) return;
        if (!excavatorStack.isSuitableFor(target)) return;
        if (!player.canHarvest(target)) return;

        ItemStack toolForLoot = excavatorStack.copy();

        if (!player.isCreative()) {
            excavatorStack.damage(1, player, EquipmentSlot.MAINHAND);
        }

        BlockState stateForLoot = world.getBlockState(targetPos);
        world.breakBlock(targetPos, false, player);
        Block.dropStacks(stateForLoot, world, targetPos, world.getBlockEntity(targetPos), player, toolForLoot);
    }
}