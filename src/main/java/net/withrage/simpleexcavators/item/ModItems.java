package net.withrage.simpleexcavators.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.withrage.simpleexcavators.SimpleExcavators;
import net.withrage.simpleexcavators.config.SimpleExcavatorsConfig;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(SimpleExcavators.MOD_ID);

    public static final DeferredItem<Item> WOODEN_EXCAVATOR = registerExcavator("wooden_excavator", ModToolMaterial.WOOD, 1, -3.2F, SimpleExcavatorsConfig.woodenDurability, false);
    public static final DeferredItem<Item> STONE_EXCAVATOR = registerExcavator("stone_excavator", ModToolMaterial.STONE, 1, -3.1F, SimpleExcavatorsConfig.stoneDurability, false);
    public static final DeferredItem<Item> COPPER_EXCAVATOR = registerExcavator("copper_excavator", ModToolMaterial.COPPER, 1, -2.9F, SimpleExcavatorsConfig.copperDurability, false);
    public static final DeferredItem<Item> GOLDEN_EXCAVATOR = registerExcavator("golden_excavator", ModToolMaterial.GOLD, 1, -2.7F, SimpleExcavatorsConfig.goldenDurability, false);
    public static final DeferredItem<Item> IRON_EXCAVATOR = registerExcavator("iron_excavator", ModToolMaterial.IRON, 2, -3.0F, SimpleExcavatorsConfig.ironDurability, false);
    public static final DeferredItem<Item> EMERALD_EXCAVATOR = registerExcavator("emerald_excavator", ModToolMaterial.EMERALD, 3, -2.5F, SimpleExcavatorsConfig.emeraldDurability, false);
    public static final DeferredItem<Item> DIAMOND_EXCAVATOR = registerExcavator("diamond_excavator", ModToolMaterial.DIAMOND, 3, -2.6F, SimpleExcavatorsConfig.diamondDurability, false);
    public static final DeferredItem<Item> NETHERITE_EXCAVATOR = registerExcavator("netherite_excavator", ModToolMaterial.NETHERITE, 4, -2.4F, SimpleExcavatorsConfig.netheriteDurability, true);

    public static final DeferredItem<Item> WOODEN_HANDLE = ITEMS.registerSimpleItem("wooden_handle");
    public static final DeferredItem<Item> WOODEN_EXCAVATOR_HEAD = ITEMS.registerSimpleItem("wooden_excavator_head");
    public static final DeferredItem<Item> STONE_EXCAVATOR_HEAD = ITEMS.registerSimpleItem("stone_excavator_head");
    public static final DeferredItem<Item> COPPER_EXCAVATOR_HEAD = ITEMS.registerSimpleItem("copper_excavator_head");
    public static final DeferredItem<Item> GOLDEN_EXCAVATOR_HEAD = ITEMS.registerSimpleItem("golden_excavator_head");
    public static final DeferredItem<Item> IRON_EXCAVATOR_HEAD = ITEMS.registerSimpleItem("iron_excavator_head");
    public static final DeferredItem<Item> EMERALD_EXCAVATOR_HEAD = ITEMS.registerSimpleItem("emerald_excavator_head");
    public static final DeferredItem<Item> DIAMOND_EXCAVATOR_HEAD = ITEMS.registerSimpleItem("diamond_excavator_head");

    public static final DeferredItem<Item> EXCAVATOR_ADV_TROPHY = ITEMS.registerSimpleItem("all_excavators");

    private static ResourceKey<Item> itemKey(String name) {
        return ResourceKey.create(
                Registries.ITEM,
                Identifier.fromNamespaceAndPath(SimpleExcavators.MOD_ID, name)
        );
    }

    private static DeferredItem<Item> registerExcavator(String name,
                                                        ToolMaterial material,
                                                        int attackDamage,
                                                        float attackSpeed,
                                                        int durability,
                                                        boolean fireproof) {

        return ITEMS.register(name, () -> {
            ResourceKey<Item> key = itemKey(name);

            Item.Properties properties = new Item.Properties()
                    .setId(key)
                    .stacksTo(1)
                    .durability(durability);

            if (fireproof) {
                properties = properties.fireResistant();
            }

            return new ExcavatorItem(material, attackDamage, attackSpeed, durability, properties);
        });
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
