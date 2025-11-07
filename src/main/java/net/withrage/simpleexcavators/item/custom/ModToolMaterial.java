package net.withrage.simpleexcavators.item.custom;

import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.BlockTags;

public class ModToolMaterial {

    public static final ToolMaterial WOOD      = ToolMaterial.WOOD;
    public static final ToolMaterial STONE     = ToolMaterial.STONE;
    public static final ToolMaterial IRON      = ToolMaterial.IRON;
    public static final ToolMaterial GOLD      = ToolMaterial.GOLD;
    public static final ToolMaterial DIAMOND   = ToolMaterial.DIAMOND;
    public static final ToolMaterial NETHERITE = ToolMaterial.NETHERITE;

    public static final ToolMaterial COPPER =
            new ToolMaterial(
                    BlockTags.INCORRECT_FOR_IRON_TOOL,
                    262,
                    6.0f,
                    2.0f,
                    12,
                    ModItemTags.COPPER_TOOL_MATERIALS
            );

    public static final ToolMaterial EMERALD =
            new ToolMaterial(
                    BlockTags.INCORRECT_FOR_DIAMOND_TOOL,
                    2031,
                    8.0f,
                    3.0f,
                    15,
                    ModItemTags.EMERALD_TOOL_MATERIALS
            );
}