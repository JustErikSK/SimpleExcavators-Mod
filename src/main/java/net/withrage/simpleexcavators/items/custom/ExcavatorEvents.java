package net.withrage.simpleexcavators.items.custom;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;

public class ExcavatorEvents {
    public static void register() {

        PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state, be) -> {
            if (!(player instanceof ServerPlayerEntity sp)) return true;
            HitResult hr = sp.raycast(5.0D, 0.0F, false);
            if (hr instanceof BlockHitResult bhr && hr.getType() == HitResult.Type.BLOCK) {
                if (bhr.getBlockPos().isWithinDistance(pos, 1.5)) {
                    ExcavatorMiningContext.setLastHitFace(player, bhr.getSide());
                }
            }
            return true;
        });
    }
}