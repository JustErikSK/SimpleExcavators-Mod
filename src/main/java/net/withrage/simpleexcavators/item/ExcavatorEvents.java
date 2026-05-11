package net.withrage.simpleexcavators.item;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.level.BlockEvent;

public class ExcavatorEvents {

    public static void register() {
        NeoForge.EVENT_BUS.register(new ExcavatorEvents());
    }

    @SubscribeEvent
    public void onBlockBreak(BlockEvent.BreakEvent event) {
        if (!(event.getPlayer() instanceof ServerPlayer player)) return;

        HitResult hitResult = player.pick(5.0D, 0.0F, false);

        if (hitResult instanceof BlockHitResult blockHitResult
                && hitResult.getType() == HitResult.Type.BLOCK) {

            BlockPos hitPos = blockHitResult.getBlockPos();

            if (hitPos.closerThan(event.getPos(), 1.5D)) {
                ExcavatorMiningContext.setLastHitFace(player, blockHitResult.getDirection());
            }
        }
    }
}
