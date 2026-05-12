package net.withrage.simpleexcavators;


import com.mojang.logging.LogUtils;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.withrage.simpleexcavators.config.SimpleExcavatorsConfig;
import net.withrage.simpleexcavators.item.ExcavatorEvents;
import net.withrage.simpleexcavators.item.ModCreativeTabs;
import net.withrage.simpleexcavators.item.ModItems;
import org.slf4j.Logger;

@Mod(SimpleExcavators.MOD_ID)
public class SimpleExcavators {
    public static final String MOD_ID = "simpleexcavators";
    public static final Logger LOGGER = LogUtils.getLogger();

    public SimpleExcavators() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        SimpleExcavatorsConfig.load();

        ModItems.register(modEventBus);
        ModCreativeTabs.register(modEventBus);

        ExcavatorEvents.register();
    }
}
