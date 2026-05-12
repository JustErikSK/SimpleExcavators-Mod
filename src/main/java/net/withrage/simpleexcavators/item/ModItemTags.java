package net.withrage.simpleexcavators.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public final class ModItemTags {
    public static final TagKey<Item> WOODEN_TOOL_MATERIALS =
            TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("simpleexcavators", "wooden_tool_materials"));
    public static final TagKey<Item> STONE_TOOL_MATERIALS =
            TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("simpleexcavators", "stone_tool_materials"));
    public static final TagKey<Item> COPPER_TOOL_MATERIALS =
            TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("simpleexcavators", "copper_tool_materials"));
    public static final TagKey<Item> GOLDEN_TOOL_MATERIALS =
            TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("simpleexcavators", "golden_tool_materials"));
    public static final TagKey<Item> IRON_TOOL_MATERIALS =
            TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("simpleexcavators", "iron_tool_materials"));
    public static final TagKey<Item> EMERALD_TOOL_MATERIALS =
            TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("simpleexcavators", "emerald_tool_materials"));
    public static final TagKey<Item> DIAMOND_TOOL_MATERIALS =
            TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("simpleexcavators", "diamond_tool_materials"));
    public static final TagKey<Item> NETHERITE_TOOL_MATERIALS =
            TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("simpleexcavators", "netherite_tool_materials"));
    private ModItemTags() {}
}