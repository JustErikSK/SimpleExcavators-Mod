package net.withrage.simpleexcavators.item;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;
import net.minecraftforge.eventbus.api.IEventBus;
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

    public static final RegistryObject<Item> WOODEN_HANDLE = ITEMS.register("wooden_handle", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> WOODEN_EXCAVATOR_HEAD = ITEMS.register("wooden_excavator_head", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> STONE_EXCAVATOR_HEAD = ITEMS.register("stone_excavator_head", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COPPER_EXCAVATOR_HEAD = ITEMS.register("copper_excavator_head", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> GOLDEN_EXCAVATOR_HEAD = ITEMS.register("golden_excavator_head", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> IRON_EXCAVATOR_HEAD = ITEMS.register("iron_excavator_head", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> EMERALD_EXCAVATOR_HEAD = ITEMS.register("emerald_excavator_head", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_EXCAVATOR_HEAD = ITEMS.register("diamond_excavator_head", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> EXCAVATOR_ADV_TROPHY = ITEMS.register("all_excavators", () -> new Item(new Item.Properties()));

    private static RegistryObject<Item> registerExcavator(String name,
                                                          Tier material,
                                                          int attackDamage,
                                                          float attackSpeed,
                                                          int durability,
                                                          boolean fireproof) {
        return ITEMS.register(name, () -> {
            Item.Properties properties = new Item.Properties().stacksTo(1);

            if (fireproof) {
                properties.fireResistant();
            }

            return new ExcavatorItem(material, attackDamage, attackSpeed, durability, properties);
        });
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
