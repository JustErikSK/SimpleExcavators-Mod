package net.withrage.simpleexcavators.item;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ToolMaterial;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.withrage.simpleexcavators.SimpleExcavators;
import net.withrage.simpleexcavators.config.SimpleExcavatorsConfig;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SimpleExcavators.MOD_ID);

    public static final RegistryObject<Item> WOODEN_EXCAVATOR = registerExcavator("wooden_excavator", ModToolMaterial.WOOD, 1, -3.2F, SimpleExcavatorsConfig.woodenDurability, false);
    public static final RegistryObject<Item> STONE_EXCAVATOR = registerExcavator("stone_excavator", ModToolMaterial.STONE, 1, -3.1F, SimpleExcavatorsConfig.stoneDurability, false);
    public static final RegistryObject<Item> COPPER_EXCAVATOR = registerExcavator("copper_excavator", ModToolMaterial.COPPER, 1, -2.9F, SimpleExcavatorsConfig.copperDurability, false);
    public static final RegistryObject<Item> GOLDEN_EXCAVATOR = registerExcavator("golden_excavator", ModToolMaterial.GOLD, 1, -2.7F, SimpleExcavatorsConfig.goldenDurability, false);
    public static final RegistryObject<Item> IRON_EXCAVATOR = registerExcavator("iron_excavator", ModToolMaterial.IRON, 2, -3.0F, SimpleExcavatorsConfig.ironDurability, false);
    public static final RegistryObject<Item> EMERALD_EXCAVATOR = registerExcavator("emerald_excavator", ModToolMaterial.EMERALD, 3, -2.5F, SimpleExcavatorsConfig.emeraldDurability, false);
    public static final RegistryObject<Item> DIAMOND_EXCAVATOR = registerExcavator("diamond_excavator", ModToolMaterial.DIAMOND, 3, -2.6F, SimpleExcavatorsConfig.diamondDurability, false);
    public static final RegistryObject<Item> NETHERITE_EXCAVATOR = registerExcavator("netherite_excavator", ModToolMaterial.NETHERITE, 4, -2.4F, SimpleExcavatorsConfig.netheriteDurability, true);

    public static final RegistryObject<Item> WOODEN_HANDLE = registerSimpleItem("wooden_handle");
    public static final RegistryObject<Item> WOODEN_EXCAVATOR_HEAD = registerSimpleItem("wooden_excavator_head");
    public static final RegistryObject<Item> STONE_EXCAVATOR_HEAD = registerSimpleItem("stone_excavator_head");
    public static final RegistryObject<Item> COPPER_EXCAVATOR_HEAD = registerSimpleItem("copper_excavator_head");
    public static final RegistryObject<Item> GOLDEN_EXCAVATOR_HEAD = registerSimpleItem("golden_excavator_head");
    public static final RegistryObject<Item> IRON_EXCAVATOR_HEAD = registerSimpleItem("iron_excavator_head");
    public static final RegistryObject<Item> EMERALD_EXCAVATOR_HEAD = registerSimpleItem("emerald_excavator_head");
    public static final RegistryObject<Item> DIAMOND_EXCAVATOR_HEAD = registerSimpleItem("diamond_excavator_head");

    public static final RegistryObject<Item> EXCAVATOR_ADV_TROPHY = registerSimpleItem("all_excavators");

    private static ResourceKey<Item> itemKey(String name) {
        return ResourceKey.create(
                Registries.ITEM,
                Identifier.fromNamespaceAndPath(SimpleExcavators.MOD_ID, name)
        );
    }

    private static RegistryObject<Item> registerSimpleItem(String name) {
        return ITEMS.register(name, () -> new Item(
                new Item.Properties().setId(itemKey(name))
        ));
    }

    private static RegistryObject<Item> registerExcavator(
            String name,
            ToolMaterial material,
            int attackDamage,
            float attackSpeed,
            int durability,
            boolean fireproof
    ) {
        return ITEMS.register(name, () -> {

            Item.Properties properties = new Item.Properties()
                    .setId(itemKey(name))
                    .durability(durability);

            if (fireproof) {
                properties.fireResistant();
            }

            return new ExcavatorItem(
                    material,
                    attackDamage,
                    attackSpeed,
                    durability,
                    properties
            );
        });
    }

    public static void register(BusGroup modBusGroup) {
        ITEMS.register(modBusGroup);
    }
}
