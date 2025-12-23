package net.withrage.simpleexcavators;

import net.fabricmc.api.ModInitializer;

import net.withrage.simpleexcavators.config.SimpleExcavatorsConfig;
import net.withrage.simpleexcavators.item.custom.ExcavatorEvents;
import net.withrage.simpleexcavators.item.custom.ModItemGroups;
import net.withrage.simpleexcavators.item.custom.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleExcavators implements ModInitializer {
	public static final String MOD_ID = "simpleexcavators";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


	@Override
	public void onInitialize() {
        SimpleExcavatorsConfig.load();
        ModItems.registerModItems();
        ModItemGroups.registerItemGroups();
        ExcavatorEvents.register();
	}
}