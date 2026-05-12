package net.withrage.simpleexcavators.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.withrage.simpleexcavators.SimpleExcavators;

public class ModCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, SimpleExcavators.MOD_ID);

    public static final RegistryObject<CreativeModeTab> SIMPLE_EXCAVATORS_TAB =
            CREATIVE_MODE_TABS.register("simpleexcavators_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemgroup.simpleexcavators"))
                    .icon(() -> new ItemStack(ModItems.IRON_EXCAVATOR.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.WOODEN_EXCAVATOR.get());
                        output.accept(ModItems.STONE_EXCAVATOR.get());
                        output.accept(ModItems.COPPER_EXCAVATOR.get());
                        output.accept(ModItems.GOLDEN_EXCAVATOR.get());
                        output.accept(ModItems.IRON_EXCAVATOR.get());
                        output.accept(ModItems.EMERALD_EXCAVATOR.get());
                        output.accept(ModItems.DIAMOND_EXCAVATOR.get());
                        output.accept(ModItems.NETHERITE_EXCAVATOR.get());
                        output.accept(ModItems.WOODEN_HANDLE.get());
                        output.accept(ModItems.WOODEN_EXCAVATOR_HEAD.get());
                        output.accept(ModItems.STONE_EXCAVATOR_HEAD.get());
                        output.accept(ModItems.COPPER_EXCAVATOR_HEAD.get());
                        output.accept(ModItems.GOLDEN_EXCAVATOR_HEAD.get());
                        output.accept(ModItems.IRON_EXCAVATOR_HEAD.get());
                        output.accept(ModItems.EMERALD_EXCAVATOR_HEAD.get());
                        output.accept(ModItems.DIAMOND_EXCAVATOR_HEAD.get());
                    })
                    .build());

    public static void register(BusGroup modBusGroup) {
        CREATIVE_MODE_TABS.register(modBusGroup);
    }
}
