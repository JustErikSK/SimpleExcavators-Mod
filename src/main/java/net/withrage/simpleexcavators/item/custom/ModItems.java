package net.withrage.simpleexcavators.item.custom;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.withrage.simpleexcavators.SimpleExcavators;

public class ModItems {
    public static final Item WOODEN_EXCAVATOR = registerItem("wooden_excavator", new ExcavatorItem(ModToolMaterial.WOOD, 2, -2.8F, new Item.Settings().maxCount(1).maxDamage(108)));
    public static final Item STONE_EXCAVATOR = registerItem("stone_excavator", new ExcavatorItem(ModToolMaterial.STONE, 3, -2.8F, new Item.Settings().maxCount(1).maxDamage(262)));
    public static final Item COPPER_EXCAVATOR = registerItem("copper_excavator", new ExcavatorItem(ModToolMaterial.COPPER, 3, -2.8F, new Item.Settings().maxCount(1).maxDamage(380)));
    public static final Item GOLDEN_EXCAVATOR = registerItem("golden_excavator", new ExcavatorItem(ModToolMaterial.GOLD, 2, -2.8F, new Item.Settings().maxCount(1).maxDamage(64)));
    public static final Item IRON_EXCAVATOR = registerItem("iron_excavator", new ExcavatorItem(ModToolMaterial.IRON, 4, -2.8F, new Item.Settings().maxCount(1).maxDamage(506)));
    public static final Item EMERALD_EXCAVATOR = registerItem("emerald_excavator", new ExcavatorItem(ModToolMaterial.EMERALD, 5, -2.8F, new Item.Settings().maxCount(1).maxDamage(2084)));
    public static final Item DIAMOND_EXCAVATOR = registerItem("diamond_excavator", new ExcavatorItem(ModToolMaterial.DIAMOND, 5, -2.8F, new Item.Settings().maxCount(1).maxDamage(3122)));
    public static final Item NETHERITE_EXCAVATOR = registerItem("netherite_excavator", new ExcavatorItem(ModToolMaterial.NETHERITE, 6, -2.8F, new Item.Settings().maxCount(1).maxDamage(4062)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(SimpleExcavators.MOD_ID, name), item);
    }

    public static void registerModItems() {
        SimpleExcavators.LOGGER.info("Registering Mod Items for " + SimpleExcavators.MOD_ID);
    }
}
