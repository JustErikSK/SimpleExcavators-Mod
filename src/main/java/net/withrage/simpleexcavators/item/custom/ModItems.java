package net.withrage.simpleexcavators.item.custom;

import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.withrage.simpleexcavators.SimpleExcavators;

public class ModItems {
    public static final Item WOODEN_EXCAVATOR = registerExcavator("wooden_excavator", ModToolMaterial.WOOD, 2, -2.8F, 108, false);
    public static final Item STONE_EXCAVATOR = registerExcavator("stone_excavator", ModToolMaterial.STONE, 3, -2.8F, 262, false);
    public static final Item COPPER_EXCAVATOR = registerExcavator("copper_excavator", ModToolMaterial.COPPER, 3, -2.8F, 380, false);
    public static final Item GOLDEN_EXCAVATOR = registerExcavator("golden_excavator", ModToolMaterial.GOLD, 2, -2.8F, 64, false);
    public static final Item IRON_EXCAVATOR = registerExcavator("iron_excavator", ModToolMaterial.IRON, 4, -2.8F, 506, false);
    public static final Item EMERALD_EXCAVATOR = registerExcavator("emerald_excavator", ModToolMaterial.EMERALD, 5, -2.8F, 2084, false);
    public static final Item DIAMOND_EXCAVATOR = registerExcavator("diamond_excavator", ModToolMaterial.DIAMOND, 5, -2.8F, 3122, false);
    public static final Item NETHERITE_EXCAVATOR = registerExcavator("netherite_excavator", ModToolMaterial.NETHERITE, 6, -2.8F, 4062, true);

    private static Item registerExcavator(String name,
                                       ToolMaterial material,
                                       int attackDamage,
                                       float attackSpeed,
                                       int durability,
                                       boolean fireproof) {

        Item.Settings settings = new Item.Settings()
                .maxCount(1)
                .maxDamage(durability);

        if (fireproof) settings.fireproof();

        return Registry.register(Registries.ITEM,
                Identifier.of("simpleexcavators", name),
                new ExcavatorItem(material, attackDamage, attackSpeed, settings));
    }

    public static void registerModItems() {
        SimpleExcavators.LOGGER.info("Registering Mod Items for " + SimpleExcavators.MOD_ID);
    }
}
