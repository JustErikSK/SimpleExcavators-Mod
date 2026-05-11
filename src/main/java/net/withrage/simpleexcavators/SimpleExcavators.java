package net.withrage.simpleexcavators;


import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.withrage.simpleexcavators.config.SimpleExcavatorsConfig;
import net.withrage.simpleexcavators.item.ModCreativeTabs;
import net.withrage.simpleexcavators.item.ModItems;
import net.withrage.simpleexcavators.item.ExcavatorEvents;
import org.slf4j.Logger;

@Mod(SimpleExcavators.MOD_ID)
public class SimpleExcavators {
    public static final String MOD_ID = "simpleexcavators";
    public static final Logger LOGGER = LogUtils.getLogger();

    public SimpleExcavators(IEventBus modEventBus) {
        SimpleExcavatorsConfig.load();

        ModItems.register(modEventBus);
        ModCreativeTabs.register(modEventBus);

        ExcavatorEvents.register();
    }
}
