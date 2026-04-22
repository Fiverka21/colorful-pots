package com.fiverka.colorfulpots;

import com.fiverka.colorfulpots.component.ColorfulPotsDataComponents;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(ColorfulPotsMod.MOD_ID)
public class ColorfulPotsMod {
	public static final String MOD_ID = "colorful_pots";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public ColorfulPotsMod(IEventBus modEventBus) {
		ColorfulPotsDataComponents.register(modEventBus);
		LOGGER.info("Colorful Pots initialized.");
	}
}
