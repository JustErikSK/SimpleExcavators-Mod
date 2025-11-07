package net.withrage.simpleexcavators.items.custom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Direction;

import java.util.Map;
import java.util.WeakHashMap;

public class ExcavatorMiningContext {

    private static final Map<PlayerEntity, Direction> LAST_FACE = new WeakHashMap<>();

    public static void setLastHitFace(PlayerEntity player, Direction face) {
        LAST_FACE.put(player, face);
    }

    public static Direction consumeLastHitFace(PlayerEntity player) {
        return LAST_FACE.remove(player);
    }
}
