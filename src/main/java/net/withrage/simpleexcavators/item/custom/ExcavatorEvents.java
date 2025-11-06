package net.withrage.simpleexcavators.item.custom;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;

public class ExcavatorEvents {
    public static void register() {

        PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state, blockEntity) -> {
            if (!(player instanceof ServerPlayerEntity serverPlayer)) {
                return true;
            }

            HitResult hr = serverPlayer.raycast(5.0D, 0.0F, false);
            if (hr instanceof BlockHitResult bhr && hr.getType() == HitResult.Type.BLOCK) {
                BlockPos hitPos = bhr.getBlockPos();
                if (hitPos.isWithinDistance(pos, 1.5)) {
                    HammerMiningContext.setLastHitFace(player, bhr.getSide());
                }
            }

            return true;
        });
    }
}