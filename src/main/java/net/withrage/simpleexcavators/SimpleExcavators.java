package net.withrage.simpleexcavators;

import net.fabricmc.api.ModInitializer;

import net.withrage.simpleexcavators.items.custom.ExcavatorEvents;
import net.withrage.simpleexcavators.items.custom.ModItemGroups;
import net.withrage.simpleexcavators.items.custom.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleExcavators implements ModInitializer {
	public static final String MOD_ID = "simpleexcavators";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

        ModItems.registerModItems();
        ModItemGroups.registerItemGroups();
        ExcavatorEvents.register();
	}
}