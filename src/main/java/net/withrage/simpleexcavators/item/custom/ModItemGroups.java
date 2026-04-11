package net.withrage.simpleexcavators.item.custom;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.withrage.simpleexcavators.SimpleExcavators;

public class ModItemGroups {

    public static final ResourceKey<CreativeModeTab> SIMPLE_EXCAVATORS_KEY = ResourceKey.create(
            Registries.CREATIVE_MODE_TAB,
            Identifier.fromNamespaceAndPath(SimpleExcavators.MOD_ID, "simpleexcavators")
    );

    public static final CreativeModeTab SIMPLE_EXCAVATORS = FabricCreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.EMERALD_EXCAVATOR))
                    .title(Component.translatable("itemgroup.simpleexcavators"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.WOODEN_EXCAVATOR);
                        output.accept(ModItems.STONE_EXCAVATOR);
                        output.accept(ModItems.COPPER_EXCAVATOR);
                        output.accept(ModItems.GOLDEN_EXCAVATOR);
                        output.accept(ModItems.IRON_EXCAVATOR);
                        output.accept(ModItems.EMERALD_EXCAVATOR);
                        output.accept(ModItems.DIAMOND_EXCAVATOR);
                        output.accept(ModItems.NETHERITE_EXCAVATOR);
                        output.accept(ModItems.WOODEN_HANDLE);
                        output.accept(ModItems.WOODEN_EXCAVATOR_HEAD);
                        output.accept(ModItems.STONE_EXCAVATOR_HEAD);
                        output.accept(ModItems.COPPER_EXCAVATOR_HEAD);
                        output.accept(ModItems.GOLDEN_EXCAVATOR_HEAD);
                        output.accept(ModItems.IRON_EXCAVATOR_HEAD);
                        output.accept(ModItems.EMERALD_EXCAVATOR_HEAD);
                        output.accept(ModItems.DIAMOND_EXCAVATOR_HEAD);
                    })
                    .build();

    public static void registerItemGroups() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, SIMPLE_EXCAVATORS_KEY, SIMPLE_EXCAVATORS);
        SimpleExcavators.LOGGER.info("Registering Item Groups for " + SimpleExcavators.MOD_ID);
    }
}