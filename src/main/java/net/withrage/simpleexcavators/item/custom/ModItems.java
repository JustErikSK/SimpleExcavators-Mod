package net.withrage.simpleexcavators.item.custom;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.withrage.simpleexcavators.SimpleExcavators;

import java.util.function.Function;

public class ModItems {
    public static final Item WOODEN_EXCAVATOR = registerExcavator("wooden_excavator", ModToolMaterial.WOOD, 1.0F, -3.2F, false);
    public static final Item STONE_EXCAVATOR = registerExcavator("stone_excavator", ModToolMaterial.STONE, 1.0F, -3.1F, false);
    public static final Item COPPER_EXCAVATOR = registerExcavator("copper_excavator", ModToolMaterial.COPPER, 1.0F, -2.9F, false);
    public static final Item GOLDEN_EXCAVATOR = registerExcavator("golden_excavator", ModToolMaterial.GOLD, 1.0F, -2.7F, false);
    public static final Item IRON_EXCAVATOR = registerExcavator("iron_excavator", ModToolMaterial.IRON, 2.0F, -3.0F, false);
    public static final Item EMERALD_EXCAVATOR = registerExcavator("emerald_excavator", ModToolMaterial.EMERALD, 3.0F, -2.5F, false);
    public static final Item DIAMOND_EXCAVATOR = registerExcavator("diamond_excavator", ModToolMaterial.DIAMOND, 3.0F, -2.6F, false);
    public static final Item NETHERITE_EXCAVATOR = registerExcavator("netherite_excavator", ModToolMaterial.NETHERITE, 4.0F, -2.4F, true);

    public static final Item WOODEN_HANDLE = registerItem("wooden_handle");
    public static final Item WOODEN_EXCAVATOR_HEAD = registerItem("wooden_excavator_head");
    public static final Item STONE_EXCAVATOR_HEAD = registerItem("stone_excavator_head");
    public static final Item COPPER_EXCAVATOR_HEAD = registerItem("copper_excavator_head");
    public static final Item GOLDEN_EXCAVATOR_HEAD = registerItem("golden_excavator_head");
    public static final Item IRON_EXCAVATOR_HEAD = registerItem("iron_excavator_head");
    public static final Item EMERALD_EXCAVATOR_HEAD = registerItem("emerald_excavator_head");
    public static final Item DIAMOND_EXCAVATOR_HEAD = registerItem("diamond_excavator_head");

    public static final Item EXCAVATOR_ADV_TROPHY = registerItem("all_excavators");

    private static Item registerExcavator(String name,
                                          ToolMaterial material,
                                          float attackDamage,
                                          float attackSpeed,
                                          boolean fireproof) {

        int enchantability = switch (name) {
            case "wooden_excavator" -> 15;
            case "stone_excavator" -> 5;
            case "copper_excavator" -> 12;
            case "golden_excavator" -> 22;
            case "iron_excavator" -> 14;
            case "emerald_excavator" -> 18;
            case "diamond_excavator" -> 11;
            case "netherite_excavator" -> 16;
            default -> 10;
        };

        Item.Properties properties = new Item.Properties()
                .stacksTo(1)
                .enchantable(enchantability)
                .repairable(material.repairItems());

        if (fireproof) {
            properties = properties.fireResistant();
        }

        return register(name, props -> new ExcavatorItem(material, attackDamage, attackSpeed, props), properties);
    }

    private static Item registerItem(String name) {
        return register(name, Item::new, new Item.Properties());
    }

    private static <T extends Item> T register(String name, Function<Item.Properties, T> factory, Item.Properties properties) {
        ResourceKey<Item> itemKey = ResourceKey.create(
                Registries.ITEM,
                Identifier.fromNamespaceAndPath(SimpleExcavators.MOD_ID, name)
        );

        T item = factory.apply(properties.setId(itemKey));
        return Registry.register(BuiltInRegistries.ITEM, itemKey, item);
    }

    public static void registerModItems() {
        SimpleExcavators.LOGGER.info("Registering items for {}", SimpleExcavators.MOD_ID);
    }
}