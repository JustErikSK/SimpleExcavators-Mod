package net.withrage.simpleexcavators.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ToolMaterial;
import net.withrage.simpleexcavators.config.SimpleExcavatorsConfig;

public class ModToolMaterial {

    public static final ToolMaterial WOOD = new ToolMaterial(
            BlockTags.INCORRECT_FOR_WOODEN_TOOL,
            SimpleExcavatorsConfig.woodenDurability,
            6.0F,
            1.0F,
            8,
            ModItemTags.WOODEN_TOOL_MATERIALS
    );

    public static final ToolMaterial STONE = new ToolMaterial(
            BlockTags.INCORRECT_FOR_STONE_TOOL,
            SimpleExcavatorsConfig.stoneDurability,
            6.0F,
            1.0F,
            9,
            ModItemTags.STONE_TOOL_MATERIALS
    );

    public static final ToolMaterial COPPER = new ToolMaterial(
            BlockTags.INCORRECT_FOR_IRON_TOOL,
            SimpleExcavatorsConfig.copperDurability,
            6.0F,
            1.0F,
            10,
            ModItemTags.COPPER_TOOL_MATERIALS
    );

    public static final ToolMaterial GOLD = new ToolMaterial(
            BlockTags.INCORRECT_FOR_GOLD_TOOL,
            SimpleExcavatorsConfig.goldenDurability,
            6.0F,
            1.0F,
            11,
            ModItemTags.GOLDEN_TOOL_MATERIALS
    );

    public static final ToolMaterial IRON = new ToolMaterial(
            BlockTags.INCORRECT_FOR_IRON_TOOL,
            SimpleExcavatorsConfig.ironDurability,
            6.0F,
            2.0F,
            12,
            ModItemTags.IRON_TOOL_MATERIALS
    );

    public static final ToolMaterial EMERALD = new ToolMaterial(
            BlockTags.INCORRECT_FOR_DIAMOND_TOOL,
            SimpleExcavatorsConfig.emeraldDurability,
            9.0F,
            3.0F,
            18,
            ModItemTags.EMERALD_TOOL_MATERIALS
    );

    public static final ToolMaterial DIAMOND = new ToolMaterial(
            BlockTags.INCORRECT_FOR_DIAMOND_TOOL,
            SimpleExcavatorsConfig.diamondDurability,
            7.0F,
            3.0F,
            16,
            ModItemTags.DIAMOND_TOOL_MATERIALS
    );

    public static final ToolMaterial NETHERITE = new ToolMaterial(
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
            SimpleExcavatorsConfig.netheriteDurability,
            8.0F,
            4.0F,
            16,
            ModItemTags.NETHERITE_TOOL_MATERIALS
    );
}