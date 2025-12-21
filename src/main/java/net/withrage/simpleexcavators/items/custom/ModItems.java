package net.withrage.simpleexcavators.items.custom;

import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.withrage.simpleexcavators.SimpleExcavators;

public class ModItems {
    public static final Item WOODEN_EXCAVATOR = registerExcavator("wooden_excavator", ModToolMaterial.WOOD, 1, -2.8F, false);
    public static final Item STONE_EXCAVATOR = registerExcavator("stone_excavator", ModToolMaterial.STONE, 1, -2.8F, false);
    public static final Item COPPER_EXCAVATOR = registerExcavator("copper_excavator", ModToolMaterial.COPPER, 1, -2.8F, false);
    public static final Item GOLDEN_EXCAVATOR = registerExcavator("golden_excavator", ModToolMaterial.GOLD, 1, -2.8F, false);
    public static final Item IRON_EXCAVATOR = registerExcavator("iron_excavator", ModToolMaterial.IRON, 2, -2.8F, false);
    public static final Item EMERALD_EXCAVATOR = registerExcavator("emerald_excavator", ModToolMaterial.EMERALD, 3, -2.8F, false);
    public static final Item DIAMOND_EXCAVATOR = registerExcavator("diamond_excavator", ModToolMaterial.DIAMOND, 3, -2.8F, false);
    public static final Item NETHERITE_EXCAVATOR = registerExcavator("netherite_excavator", ModToolMaterial.NETHERITE, 4, -2.8F, true);

    public static final Item EXCAVATOR_HANDLE = registerItem("excavator_handle");
    public static final Item WOODEN_EXCAVATOR_HEAD = registerItem("wooden_excavator_head");
    public static final Item STONE_EXCAVATOR_HEAD = registerItem("stone_excavator_head");
    public static final Item COPPER_EXCAVATOR_HEAD = registerItem("copper_excavator_head");
    public static final Item GOLDEN_EXCAVATOR_HEAD = registerItem("golden_excavator_head");
    public static final Item IRON_EXCAVATOR_HEAD = registerItem("iron_excavator_head");
    public static final Item EMERALD_EXCAVATOR_HEAD = registerItem("emerald_excavator_head");
    public static final Item DIAMOND_EXCAVATOR_HEAD = registerItem("diamond_excavator_head");

    private static Item registerExcavator(String name,
                                       ToolMaterial material,
                                       int attackDamage,
                                       float attackSpeed,
                                       boolean fireproof) {

        Identifier id = Identifier.of(SimpleExcavators.MOD_ID, name);
        RegistryKey<Item> key = RegistryKey.of(Registries.ITEM.getKey(), id);

        Item.Settings settings = new Item.Settings()
                .registryKey(key)
                .maxCount(1);

        if (fireproof) settings.fireproof();

        Item hammer = new ExcavatorItem(material, attackDamage, attackSpeed, settings);

        return Registry.register(Registries.ITEM, id, hammer);
    }

    private static Item registerItem(String name) {
        Identifier id = Identifier.of(SimpleExcavators.MOD_ID, name);
        RegistryKey<Item> key = RegistryKey.of(Registries.ITEM.getKey(), id);

        Item.Settings settings = new Item.Settings()
                .registryKey(key);

        return Registry.register(Registries.ITEM, key, new Item(settings));
    }

    public static void registerModItems() {
        SimpleExcavators.LOGGER.info("Registering Mod Items for " + SimpleExcavators.MOD_ID);
    }
}
