package net.withrage.simpleexcavators.item.custom;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public final class ModItemTags {
    public static final TagKey<Item> WOODEN_TOOL_MATERIALS =
            TagKey.of(RegistryKeys.ITEM, Identifier.of("simpleexcavators", "wooden_tool_materials"));
    public static final TagKey<Item> STONE_TOOL_MATERIALS =
            TagKey.of(RegistryKeys.ITEM, Identifier.of("simpleexcavators", "stone_tool_materials"));
    public static final TagKey<Item> COPPER_TOOL_MATERIALS =
            TagKey.of(RegistryKeys.ITEM, Identifier.of("simpleexcavators", "copper_tool_materials"));
    public static final TagKey<Item> GOLDEN_TOOL_MATERIALS =
            TagKey.of(RegistryKeys.ITEM, Identifier.of("simpleexcavators", "golden_tool_materials"));
    public static final TagKey<Item> IRON_TOOL_MATERIALS =
            TagKey.of(RegistryKeys.ITEM, Identifier.of("simpleexcavators", "iron_tool_materials"));
    public static final TagKey<Item> EMERALD_TOOL_MATERIALS =
            TagKey.of(RegistryKeys.ITEM, Identifier.of("simpleexcavators", "emerald_tool_materials"));
    public static final TagKey<Item> DIAMOND_TOOL_MATERIALS =
            TagKey.of(RegistryKeys.ITEM, Identifier.of("simpleexcavators", "diamond_tool_materials"));
    public static final TagKey<Item> NETHERITE_TOOL_MATERIALS =
            TagKey.of(RegistryKeys.ITEM, Identifier.of("simpleexcavators", "netherite_tool_materials"));
    private ModItemTags() {}
}