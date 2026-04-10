package net.withrage.simpleexcavators.item.custom;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.withrage.simpleexcavators.SimpleExcavators;
import net.withrage.simpleexcavators.config.SimpleExcavatorsConfig;

public class ModItems {
    public static final Item WOODEN_EXCAVATOR = registerItem("wooden_excavator", new ExcavatorItem(ModToolMaterial.WOOD, 1, -3.2F, new Item.Settings().maxCount(1).maxDamage(SimpleExcavatorsConfig.woodenDurability)));
    public static final Item STONE_EXCAVATOR = registerItem("stone_excavator", new ExcavatorItem(ModToolMaterial.STONE, 1, -3.1F, new Item.Settings().maxCount(1).maxDamage(SimpleExcavatorsConfig.stoneDurability)));
    public static final Item COPPER_EXCAVATOR = registerItem("copper_excavator", new ExcavatorItem(ModToolMaterial.COPPER, 1, -2.9F, new Item.Settings().maxCount(1).maxDamage(SimpleExcavatorsConfig.copperDurability)));
    public static final Item GOLDEN_EXCAVATOR = registerItem("golden_excavator", new ExcavatorItem(ModToolMaterial.GOLD, 1, -2.7F, new Item.Settings().maxCount(1).maxDamage(SimpleExcavatorsConfig.goldenDurability)));
    public static final Item IRON_EXCAVATOR = registerItem("iron_excavator", new ExcavatorItem(ModToolMaterial.IRON, 2, -3.0F, new Item.Settings().maxCount(1).maxDamage(SimpleExcavatorsConfig.ironDurability)));
    public static final Item EMERALD_EXCAVATOR = registerItem("emerald_excavator", new ExcavatorItem(ModToolMaterial.EMERALD, 3, -2.5F, new Item.Settings().maxCount(1).maxDamage(SimpleExcavatorsConfig.emeraldDurability)));
    public static final Item DIAMOND_EXCAVATOR = registerItem("diamond_excavator", new ExcavatorItem(ModToolMaterial.DIAMOND, 3, -2.6F, new Item.Settings().maxCount(1).maxDamage(SimpleExcavatorsConfig.diamondDurability)));
    public static final Item NETHERITE_EXCAVATOR = registerItem("netherite_excavator", new ExcavatorItem(ModToolMaterial.NETHERITE, 4, -2.4F, new Item.Settings().maxCount(1).maxDamage(SimpleExcavatorsConfig.netheriteDurability).fireproof()));

    public static final Item WOODEN_HANDLE = registerItem("wooden_handle", new Item(new FabricItemSettings()));
    public static final Item WOODEN_EXCAVATOR_HEAD = registerItem("wooden_excavator_head", new Item(new FabricItemSettings()));
    public static final Item STONE_EXCAVATOR_HEAD = registerItem("stone_excavator_head", new Item(new FabricItemSettings()));
    public static final Item COPPER_EXCAVATOR_HEAD = registerItem("copper_excavator_head", new Item(new FabricItemSettings()));
    public static final Item GOLDEN_EXCAVATOR_HEAD = registerItem("golden_excavator_head", new Item(new FabricItemSettings()));
    public static final Item IRON_EXCAVATOR_HEAD = registerItem("iron_excavator_head", new Item(new FabricItemSettings()));
    public static final Item EMERALD_EXCAVATOR_HEAD = registerItem("emerald_excavator_head", new Item(new FabricItemSettings()));
    public static final Item DIAMOND_EXCAVATOR_HEAD = registerItem("diamond_excavator_head", new Item(new FabricItemSettings()));

    public static final Item EXCAVATOR_ADV_TROPHY = registerItem("all_excavators", new Item(new FabricItemSettings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(SimpleExcavators.MOD_ID, name), item);
    }

    public static void registerModItems() {
        SimpleExcavators.LOGGER.info("Registering Mod Items for " + SimpleExcavators.MOD_ID);
    }
}
