package net.withrage.simpleexcavators.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.withrage.simpleexcavators.config.SimpleExcavatorsConfig;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ExcavatorItem extends Item {

    private static final Map<Block, BlockState> PATH_STATES = Map.of(
            Blocks.GRASS_BLOCK, Blocks.DIRT_PATH.defaultBlockState(),
            Blocks.DIRT, Blocks.DIRT_PATH.defaultBlockState(),
            Blocks.PODZOL, Blocks.DIRT_PATH.defaultBlockState(),
            Blocks.COARSE_DIRT, Blocks.DIRT_PATH.defaultBlockState(),
            Blocks.MYCELIUM, Blocks.DIRT_PATH.defaultBlockState(),
            Blocks.ROOTED_DIRT, Blocks.DIRT_PATH.defaultBlockState()
    );

    public ExcavatorItem(ToolMaterial material, float attackDamage, float attackSpeed, Item.Properties properties) {
        super(properties.shovel(material, attackDamage, attackSpeed));
    }

    private static Direction fallbackFace(Player player) {
        float pitch = player.getXRot();
        if (pitch > 60f) return Direction.DOWN;
        if (pitch < -60f) return Direction.UP;
        return player.getDirection();
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerLevel level, Entity entity, EquipmentSlot slot) {
        super.inventoryTick(stack, level, entity, slot);

        if (level.isClientSide()) return;
        if (!(entity instanceof Player)) return;

        int max = stack.getMaxDamage();
        if (max <= 0) return;

        int dmg = stack.getDamageValue();
        if (dmg < 0) {
            stack.setDamageValue(0);
            return;
        }

        if (dmg >= max) {
            stack.setDamageValue(max - 1);
        }
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity miner) {
        boolean result = super.mineBlock(stack, level, state, pos, miner);

        if (!level.isClientSide() && miner instanceof Player player) {
            if (!state.is(BlockTags.MINEABLE_WITH_SHOVEL)) return result;
            if (SimpleExcavatorsConfig.sneakMines1x1 && player.isShiftKeyDown()) return result;

            Direction hitFace = ExcavatorMiningContext.consumeLastHitFace(player);
            if (hitFace == null) hitFace = fallbackFace(player);
            breakExtraBlocksAround(pos, level, player, stack, state, hitFace);
        }

        return result;
    }

    private void breakExtraBlocksAround(BlockPos origin,
                                        Level level,
                                        Player player,
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
                    targets.add(origin.offset(ox, oy, oz));
                }
            }
        }

        ServerPlayer serverPlayer = player instanceof ServerPlayer sp ? sp : null;
        int remaining = player.isCreative()
                ? Integer.MAX_VALUE
                : (excavatorStack.getMaxDamage() - excavatorStack.getDamageValue());

        for (BlockPos targetPos : targets) {
            if (remaining <= 0) break;

            boolean broke = breakOneExtraBlock(level, player, excavatorStack, origin, originState, targetPos);
            if (!broke) continue;

            if (!player.isCreative() && serverPlayer != null) {
                spendOneDurability(serverPlayer, InteractionHand.MAIN_HAND, excavatorStack);
                remaining--;

                if (excavatorStack.getDamageValue() >= excavatorStack.getMaxDamage()) {
                    break;
                }
            }
        }
    }

    @Override
    public InteractionResult useOn(UseOnContext ctx) {
        if (!(ctx.getLevel() instanceof ServerLevel level)) return InteractionResult.PASS;
        Direction side = ctx.getClickedFace();
        if (side == Direction.DOWN) return InteractionResult.PASS;
        BlockPos origin = ctx.getClickedPos();
        if (!isPathable(level, origin)) return InteractionResult.PASS;
        boolean sneaking = ctx.getPlayer() != null && ctx.getPlayer().isShiftKeyDown();
        int changed = SimpleExcavatorsConfig.sneakPathMaking1x1 && sneaking
                ? makeSinglePath(level, ctx, origin)
                : make3x3Paths(level, ctx, origin);
        if (changed > 0) {
            if (ctx.getPlayer() != null) {
                ctx.getPlayer().swing(ctx.getHand(), true);
            }
            level.playSound(null, origin, SoundEvents.SHOVEL_FLATTEN, SoundSource.BLOCKS, 1.0F, 1.0F);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    private int make3x3Paths(ServerLevel level, UseOnContext ctx, BlockPos origin) {
        ServerPlayer player = ctx.getPlayer() instanceof ServerPlayer sp ? sp : null;
        ItemStack stack = ctx.getItemInHand();
        int changed = 0;
        if (SimpleExcavatorsConfig.pathMaking) {
            for (int dx = -1; dx <= 1; dx++) {
                for (int dz = -1; dz <= 1; dz++) {
                    BlockPos pos = origin.offset(dx, 0, dz);
                    if (!isPathable(level, pos)) continue;
                    if (!canSpendOneDurability(player, stack)) return changed;
                    BlockState newState = PATH_STATES.get(level.getBlockState(pos).getBlock());
                    if (newState == null) continue;
                    level.setBlockAndUpdate(pos, newState);
                    if (player != null) {
                        spendOneDurability(player, ctx.getHand(), stack);
                    }
                    changed++;
                }
            }
        }
        return changed;
    }

    private int makeSinglePath(ServerLevel level, UseOnContext ctx, BlockPos pos) {
        ServerPlayer player = ctx.getPlayer() instanceof ServerPlayer sp ? sp : null;
        ItemStack stack = ctx.getItemInHand();
        if (!SimpleExcavatorsConfig.pathMaking) return 0;
        if (!isPathable(level, pos)) return 0;
        if (!canSpendOneDurability(player, stack)) return 0;
        BlockState newState = PATH_STATES.get(level.getBlockState(pos).getBlock());
        if (newState == null) return 0;
        level.setBlockAndUpdate(pos, newState);
        if (player != null) {
            spendOneDurability(player, ctx.getHand(), stack);
        }
        return 1;
    }

    private boolean canSpendOneDurability(Player player, ItemStack stack) {
        if (player == null || player.isCreative()) return true;
        int remaining = stack.getMaxDamage() - stack.getDamageValue();
        return remaining > 0;
    }

    private static void spendOneDurability(ServerPlayer player, InteractionHand hand, ItemStack stack) {
        if (player.isCreative()) return;
        EquipmentSlot slot = hand == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;
        stack.hurtAndBreak(1, player, slot);
    }

    private boolean isPathable(ServerLevel level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        if (!PATH_STATES.containsKey(state.getBlock())) return false;
        return level.getBlockState(pos.above()).isAir();
    }

    private boolean breakOneExtraBlock(Level level,
                                       Player player,
                                       ItemStack excavatorStack,
                                       BlockPos originPos,
                                       BlockState originState,
                                       BlockPos targetPos) {

        if (!(level instanceof ServerLevel serverLevel)) {
            return false;
        }

        BlockState targetState = level.getBlockState(targetPos);
        if (targetState.isAir() || targetState.getDestroySpeed(level, targetPos) < 0.0F) return false;
        if (!targetState.is(BlockTags.MINEABLE_WITH_SHOVEL)) return false;
        if (!excavatorStack.isCorrectToolForDrops(targetState)) return false;
        if (!player.hasCorrectToolForDrops(targetState)) return false;

        float originHardness = originState.getDestroySpeed(level, originPos);
        float targetHardness = targetState.getDestroySpeed(level, targetPos);
        if (targetHardness < 0) return false;
        if (originHardness >= 0 && targetHardness > originHardness + 0.5f) return false;

        level.destroyBlock(targetPos, false, player);
        Block.dropResources(targetState, serverLevel, targetPos, level.getBlockEntity(targetPos), player, excavatorStack);
        level.setBlock(targetPos, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL);

        return true;
    }

    private Plane getPlaneFromHitFace(Direction face) {
        if (face == null) return Plane.HORIZONTAL;

        return switch (face) {
            case UP, DOWN -> Plane.HORIZONTAL;
            case NORTH, SOUTH -> Plane.VERTICAL_YX;
            case EAST, WEST -> Plane.VERTICAL_YZ;
        };
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