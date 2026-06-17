package net.withrage.simpleexcavators.item.custom;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class ExcavatorEvents {
    public static void register() {
        PlayerBlockBreakEvents.BEFORE.register((Level level, Player player, BlockPos pos, BlockState state, BlockEntity blockEntity) -> {
            if (!(player instanceof ServerPlayer serverPlayer)) {
                return true;
            }
            HitResult hr = serverPlayer.pick(5.0D, 0.0F, false);
            if (hr instanceof BlockHitResult bhr && hr.getType() == HitResult.Type.BLOCK) {
                BlockPos hitPos = bhr.getBlockPos();
                if (hitPos.closerThan(pos, 1.5D)) {
                    ExcavatorMiningContext.setLastHitFace(player, bhr.getDirection());
                }
            }
            return true;
        });
    }
}