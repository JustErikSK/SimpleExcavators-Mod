package net.withrage.simpleexcavators.item.custom;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.withrage.simpleexcavators.SimpleExcavators;

public class ModItems {
    public static final Item WOODEN_EXCAVATOR = registerItem("wooden_excavator", new ExcavatorItem(ModToolMaterial.WOOD, 1, -2.8F, new Item.Settings().maxCount(1).maxDamage(SimpleExcavators.CONFIG.woodenDurability)));
    public static final Item STONE_EXCAVATOR = registerItem("stone_excavator", new ExcavatorItem(ModToolMaterial.STONE, 1, -2.8F, new Item.Settings().maxCount(1).maxDamage(SimpleExcavators.CONFIG.stoneDurability)));
    public static final Item COPPER_EXCAVATOR = registerItem("copper_excavator", new ExcavatorItem(ModToolMaterial.COPPER, 1, -2.8F, new Item.Settings().maxCount(1).maxDamage(SimpleExcavators.CONFIG.copperDurability)));
    public static final Item GOLDEN_EXCAVATOR = registerItem("golden_excavator", new ExcavatorItem(ModToolMaterial.GOLD, 1, -2.8F, new Item.Settings().maxCount(1).maxDamage(SimpleExcavators.CONFIG.goldenDurability)));
    public static final Item IRON_EXCAVATOR = registerItem("iron_excavator", new ExcavatorItem(ModToolMaterial.IRON, 2, -2.8F, new Item.Settings().maxCount(1).maxDamage(SimpleExcavators.CONFIG.ironDurability)));
    public static final Item EMERALD_EXCAVATOR = registerItem("emerald_excavator", new ExcavatorItem(ModToolMaterial.EMERALD, 3, -2.8F, new Item.Settings().maxCount(1).maxDamage(SimpleExcavators.CONFIG.emeraldDurability)));
    public static final Item DIAMOND_EXCAVATOR = registerItem("diamond_excavator", new ExcavatorItem(ModToolMaterial.DIAMOND, 3, -2.8F, new Item.Settings().maxCount(1).maxDamage(SimpleExcavators.CONFIG.diamondDurability)));
    public static final Item NETHERITE_EXCAVATOR = registerItem("netherite_excavator", new ExcavatorItem(ModToolMaterial.NETHERITE, 4, -2.8F, new Item.Settings().maxCount(1).maxDamage(SimpleExcavators.CONFIG.netheriteDurability)));

    public static final Item EXCAVATOR_HANDLE = registerItem("excavator_handle", new Item(new FabricItemSettings()));
    public static final Item WOODEN_EXCAVATOR_HEAD = registerItem("wooden_excavator_head", new Item(new FabricItemSettings()));
    public static final Item STONE_EXCAVATOR_HEAD = registerItem("stone_excavator_head", new Item(new FabricItemSettings()));
    public static final Item COPPER_EXCAVATOR_HEAD = registerItem("copper_excavator_head", new Item(new FabricItemSettings()));
    public static final Item GOLDEN_EXCAVATOR_HEAD = registerItem("golden_excavator_head", new Item(new FabricItemSettings()));
    public static final Item IRON_EXCAVATOR_HEAD = registerItem("iron_excavator_head", new Item(new FabricItemSettings()));
    public static final Item EMERALD_EXCAVATOR_HEAD = registerItem("emerald_excavator_head", new Item(new FabricItemSettings()));
    public static final Item DIAMOND_EXCAVATOR_HEAD = registerItem("diamond_excavator_head", new Item(new FabricItemSettings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(SimpleExcavators.MOD_ID, name), item);
    }

    public static void registerModItems() {
        SimpleExcavators.LOGGER.info("Registering Mod Items for " + SimpleExcavators.MOD_ID);
    }
}
