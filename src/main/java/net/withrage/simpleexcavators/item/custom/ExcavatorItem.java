package net.withrage.simpleexcavators.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.withrage.simpleexcavators.config.SimpleExcavatorsConfig;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ExcavatorItem extends MiningToolItem {

    private static final Map<Block, BlockState> PATH_STATES = Map.of(
            Blocks.GRASS_BLOCK, Blocks.DIRT_PATH.getDefaultState(),
            Blocks.DIRT,        Blocks.DIRT_PATH.getDefaultState(),
            Blocks.PODZOL,      Blocks.DIRT_PATH.getDefaultState(),
            Blocks.COARSE_DIRT, Blocks.DIRT_PATH.getDefaultState(),
            Blocks.MYCELIUM,    Blocks.DIRT_PATH.getDefaultState(),
            Blocks.ROOTED_DIRT, Blocks.DIRT_PATH.getDefaultState()
    );

    public ExcavatorItem(ToolMaterial material,
                         int attackDamage,
                         float attackSpeed,
                         Settings settings) {
        super(attackDamage, attackSpeed, material, BlockTags.SHOVEL_MINEABLE, settings);
    }

    private static Direction fallbackFace(PlayerEntity p) {
        float pitch = p.getPitch();
        if (pitch > 60.0f) return Direction.DOWN;
        if (pitch < -60.0f) return Direction.UP;
        return p.getHorizontalFacing();
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);

        if (world.isClient()) return;
        if (!(entity instanceof PlayerEntity)) return;
        int max = stack.getMaxDamage();
        if (max <= 0) return;
        int dmg = stack.getDamage();
        if (dmg < 0) {
            stack.setDamage(0);
            return;
        }
        if (dmg >= max) {
            stack.setDamage(max - 1);
        }
    }

    @Override
    public boolean postMine(ItemStack stack,
                            World world,
                            BlockState state,
                            BlockPos pos,
                            LivingEntity miner) {

        boolean result = super.postMine(stack, world, state, pos, miner);
        if (!world.isClient && miner instanceof PlayerEntity player) {
            if (!state.isIn(BlockTags.SHOVEL_MINEABLE)) {
                return result;
            }
            if (SimpleExcavatorsConfig.sneakMines1x1 && player.isSneaking()) {
                return result;
            }
            Direction hitFace = ExcavatorMiningContext.consumeLastHitFace(player);
            if (hitFace == null) hitFace = fallbackFace(player);
            breakExtraBlocksAround(pos, world, player, stack, state, hitFace);
        }
        return result;
    }

    private void breakExtraBlocksAround(BlockPos origin,
                                        World world,
                                        PlayerEntity player,
                                        ItemStack excavatorStack,
                                        BlockState originState,
                                        Direction hitFace) {

        Plane plane = getPlaneFromHitFace(hitFace);
        Set<BlockPos> targets = new HashSet<>();
        for (int ox = -1; ox <= 1; ox++) {
            for (int oy = -1; oy <= 1; oy++) {
                for (int oz = -1; oz <= 1; oz++) {
                    if (ox == 0 && oy == 0 && oz == 0) continue;
                    if (!plane.allowsOffset(ox, oy, oz)) continue;
                    targets.add(origin.add(ox, oy, oz));
                }
            }
        }
        for (BlockPos targetPos : targets) {
            breakOneExtraBlock(world, player, excavatorStack, origin, originState, targetPos);
        }
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext ctx) {
        if (!(ctx.getWorld() instanceof ServerWorld world)) return ActionResult.PASS;
        Direction side = ctx.getSide();
        if (side == Direction.DOWN) return ActionResult.PASS;
        BlockPos origin = ctx.getBlockPos();
        ItemStack stack = ctx.getStack();
        if (!isPathable(world, origin)) return ActionResult.PASS;
        int changed = make3x3Paths(world, ctx, origin);
        if (changed > 0) {
            world.playSound(null, origin, SoundEvents.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }

    private int make3x3Paths(ServerWorld world, ItemUsageContext ctx, BlockPos origin) {
        var player = ctx.getPlayer();
        ItemStack stack = ctx.getStack();
        int changed = 0;
        for (int dx = -1; dx <= 1; dx++) {
            for (int dz = -1; dz <= 1; dz++) {
                BlockPos pos = origin.add(dx, 0, dz);
                if (!isPathable(world, pos)) continue;
                if (!canSpendOneDurability(player, stack)) return changed;
                BlockState newState = PATH_STATES.get(world.getBlockState(pos).getBlock());
                if (newState == null) continue;
                world.setBlockState(pos, newState);
                spendOneDurability(player, stack, ctx.getHand());
                changed++;
            }
        }
        return changed;
    }

    private boolean canSpendOneDurability(net.minecraft.entity.player.PlayerEntity player, ItemStack stack) {
        if (player == null || player.isCreative()) return true;
        int remaining = stack.getMaxDamage() - stack.getDamage();
        return remaining > 0;
    }

    private void spendOneDurability(LivingEntity user, ItemStack stack, Hand hand) {
        if (!(user instanceof PlayerEntity player)) return;
        if (player.isCreative()) return;

        stack.damage(1, player, p -> p.sendToolBreakStatus(hand));
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    private boolean isPathable(ServerWorld world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        if (!PATH_STATES.containsKey(state.getBlock())) return false;
        return world.getBlockState(pos.up()).isAir();
    }

    private void breakOneExtraBlock(World world,
                                    PlayerEntity player,
                                    ItemStack excavatorStack,
                                    BlockPos originPos,
                                    BlockState originState,
                                    BlockPos targetPos) {

        if (!(world instanceof ServerWorld serverWorld)) {
            return;
        }

        BlockState targetState = world.getBlockState(targetPos);
        if (targetState.isAir() || targetState.getHardness(world, targetPos) < 0.0F) return;
        if (!targetState.isIn(BlockTags.SHOVEL_MINEABLE)) return;
        if (!excavatorStack.isSuitableFor(targetState)) return;
        if (!player.canHarvest(targetState)) return;
        float originHardness = originState.getHardness(world, originPos);
        float targetHardness = targetState.getHardness(world, targetPos);
        if (targetHardness < 0) return;
        if (originHardness >= 0 && targetHardness > originHardness + 0.5f) {
            return;
        }
        boolean creative = player.isCreative();
        if (!creative) {
            excavatorStack.damage(1, player, (p) -> {
                p.sendToolBreakStatus(Hand.MAIN_HAND);
            });
        }
        world.breakBlock(targetPos, false, player);
        Block.dropStacks(
                targetState,
                serverWorld,
                targetPos,
                world.getBlockEntity(targetPos),
                player,
                excavatorStack
        );
        world.setBlockState(
                targetPos,
                net.minecraft.block.Blocks.AIR.getDefaultState(),
                Block.NOTIFY_ALL
        );
    }

    private Plane getPlaneFromHitFace(Direction face) {
        if (face == null) {
            return Plane.HORIZONTAL;
        }
        switch (face) {
            case UP:
            case DOWN:
                return Plane.HORIZONTAL;
            case NORTH:
            case SOUTH:
                return Plane.VERTICAL_YX;
            case EAST:
            case WEST:
                return Plane.VERTICAL_YZ;
            default:
                return Plane.HORIZONTAL;
        }
    }

    private enum Plane {
        HORIZONTAL {
            @Override
            public boolean allowsOffset(int ox, int oy, int oz) {
                return oy == 0 && !(ox == 0 && oz == 0);
            }
        },

        VERTICAL_YX {
            @Override
            public boolean allowsOffset(int ox, int oy, int oz) {
                return oz == 0 && !(ox == 0 && oy == 0);
            }
        },

        VERTICAL_YZ {
            @Override
            public boolean allowsOffset(int ox, int oy, int oz) {
                return ox == 0 && !(oy == 0 && oz == 0);
            }
        };
        public abstract boolean allowsOffset(int ox, int oy, int oz);
    }
}