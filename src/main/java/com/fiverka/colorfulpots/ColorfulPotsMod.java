package com.fiverka.colorfulpots;

import com.fiverka.colorfulpots.component.ColorfulPotsDataComponents;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ColorfulPotsMod implements ModInitializer {
	public static final String MOD_ID = "colorful_pots";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ColorfulPotsDataComponents.bootstrap();
		LOGGER.info("Colorful Pots initialized.");
	}
}
