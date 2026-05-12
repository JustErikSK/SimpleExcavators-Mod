package net.withrage.simpleexcavators.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ToolMaterial;
import net.withrage.simpleexcavators.config.SimpleExcavatorsConfig;

public class ModToolMaterial {

    public static final ToolMaterial WOOD =
            new ToolMaterial(
                    BlockTags.INCORRECT_FOR_WOODEN_TOOL,
                    SimpleExcavatorsConfig.woodenDurability,
                    6.0f,
                    2.0f,
                    12,
                    ModItemTags.WOODEN_TOOL_MATERIALS
            );

    public static final ToolMaterial STONE =
            new ToolMaterial(
                    BlockTags.INCORRECT_FOR_STONE_TOOL,
                    SimpleExcavatorsConfig.stoneDurability,
                    6.0f,
                    2.0f,
                    12,
                    ModItemTags.STONE_TOOL_MATERIALS
            );

    public static final ToolMaterial COPPER =
            new ToolMaterial(
                    BlockTags.INCORRECT_FOR_IRON_TOOL,
                    SimpleExcavatorsConfig.copperDurability,
                    6.0f,
                    2.0f,
                    12,
                    ModItemTags.COPPER_TOOL_MATERIALS
            );

    public static final ToolMaterial GOLD =
            new ToolMaterial(
                    BlockTags.INCORRECT_FOR_GOLD_TOOL,
                    SimpleExcavatorsConfig.goldenDurability,
                    6.0f,
                    2.0f,
                    12,
                    ModItemTags.GOLDEN_TOOL_MATERIALS
            );

    public static final ToolMaterial IRON =
            new ToolMaterial(
                    BlockTags.INCORRECT_FOR_IRON_TOOL,
                    SimpleExcavatorsConfig.ironDurability,
                    6.0f,
                    2.0f,
                    12,
                    ModItemTags.IRON_TOOL_MATERIALS
            );

    public static final ToolMaterial EMERALD =
            new ToolMaterial(
                    BlockTags.INCORRECT_FOR_DIAMOND_TOOL,
                    SimpleExcavatorsConfig.emeraldDurability,
                    8.0f,
                    3.0f,
                    15,
                    ModItemTags.EMERALD_TOOL_MATERIALS
            );

    public static final ToolMaterial DIAMOND =
            new ToolMaterial(
                    BlockTags.INCORRECT_FOR_DIAMOND_TOOL,
                    SimpleExcavatorsConfig.diamondDurability,
                    6.0f,
                    2.0f,
                    12,
                    ModItemTags.DIAMOND_TOOL_MATERIALS
            );

    public static final ToolMaterial NETHERITE =
            new ToolMaterial(
                    BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
                    SimpleExcavatorsConfig.netheriteDurability,
                    6.0f,
                    2.0f,
                    12,
                    ModItemTags.NETHERITE_TOOL_MATERIALS
            );
}
