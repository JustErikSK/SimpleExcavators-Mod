package net.withrage.simpleexcavators.item;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

public class ExcavatorEvents {
    public static void register() {
        NeoForge.EVENT_BUS.register(new ExcavatorEvents());
    }

    @SubscribeEvent
    public void onBreakSpeed(PlayerEvent.BreakSpeed event) {
        Player basePlayer = event.getEntity();
        if (!(basePlayer instanceof ServerPlayer player)) return;
        HitResult hitResult = player.pick(5.0D, 0.0F, false);
        if (hitResult instanceof BlockHitResult blockHitResult
                && hitResult.getType() == HitResult.Type.BLOCK) {
            BlockPos hitPos = blockHitResult.getBlockPos();
            if (hitPos.closerThan(event.getPosition().orElse(BlockPos.ZERO), 1.5D)) {
                ExcavatorMiningContext.setLastHitFace(player, blockHitResult.getDirection());
            }
        }
    }
}
