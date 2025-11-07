package net.withrage.simpleexcavators.item.custom;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.withrage.simpleexcavators.SimpleExcavators;

public class ModItemGroups {
    public static final ItemGroup SIMPLE_HAMMERS = Registry.register(Registries.ITEM_GROUP,
            new Identifier(SimpleExcavators.MOD_ID, "iron_excavator"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.simpleexcavators"))
                    .icon(() -> new ItemStack(ModItems.IRON_EXCAVATOR)).entries((displayContext, entries) -> {
                        entries.add(ModItems.WOODEN_EXCAVATOR);
                        entries.add(ModItems.STONE_EXCAVATOR);
                        entries.add(ModItems.COPPER_EXCAVATOR);
                        entries.add(ModItems.GOLDEN_EXCAVATOR);
                        entries.add(ModItems.IRON_EXCAVATOR);
                        entries.add(ModItems.EMERALD_EXCAVATOR);
                        entries.add(ModItems.DIAMOND_EXCAVATOR);
                        entries.add(ModItems.NETHERITE_EXCAVATOR);
                    }).build());

    public static void registerItemGroups() {
        SimpleExcavators.LOGGER.info("Registering Item Groups for " + SimpleExcavators.MOD_ID);
    }
}
