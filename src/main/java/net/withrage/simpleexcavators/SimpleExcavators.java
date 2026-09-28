package net.withrage.simpleexcavators;


import com.mojang.logging.LogUtils;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.withrage.simpleexcavators.config.SimpleExcavatorsConfig;
import net.withrage.simpleexcavators.item.ModCreativeTabs;
import net.withrage.simpleexcavators.item.ModItems;
import org.slf4j.Logger;

@Mod(SimpleExcavators.MOD_ID)
public class SimpleExcavators {
    public static final String MOD_ID = "simpleexcavators";
    public static final Logger LOGGER = LogUtils.getLogger();

    public SimpleExcavators(FMLJavaModLoadingContext context) {
        BusGroup modBusGroup = context.getModBusGroup();

        SimpleExcavatorsConfig.load();

        ModItems.register(modBusGroup);
        ModCreativeTabs.register(modBusGroup);
    }
}
