package net.withrage.simpleexcavators.item.custom;

import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;

import java.util.Map;
import java.util.WeakHashMap;

public class ExcavatorMiningContext {
    private static final Map<Player, Direction> LAST_FACE = new WeakHashMap<>();
    public static void setLastHitFace(Player player, Direction face) {
        LAST_FACE.put(player, face);
    }
    public static Direction consumeLastHitFace(Player player) {
        return LAST_FACE.remove(player);
    }
}