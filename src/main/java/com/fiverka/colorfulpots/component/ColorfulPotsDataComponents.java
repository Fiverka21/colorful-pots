package com.fiverka.colorfulpots.component;

import com.fiverka.colorfulpots.ColorfulPotsMod;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.PotDecorations;

public final class ColorfulPotsDataComponents {
	public static final DataComponentType<Boolean> DIAMONDED = Registry.register(
		BuiltInRegistries.DATA_COMPONENT_TYPE,
		Identifier.fromNamespaceAndPath(ColorfulPotsMod.MOD_ID, "diamonded"),
		DataComponentType.<Boolean>builder()
			.persistent(Codec.BOOL)
			.networkSynchronized(ByteBufCodecs.BOOL)
			.build()
	);

	public static final DataComponentType<PotDecorations> DIAMONDED_DECORATIONS = Registry.register(
		BuiltInRegistries.DATA_COMPONENT_TYPE,
		Identifier.fromNamespaceAndPath(ColorfulPotsMod.MOD_ID, "diamonded_decorations"),
		DataComponentType.<PotDecorations>builder()
			.persistent(PotDecorations.CODEC)
			.networkSynchronized(PotDecorations.STREAM_CODEC)
			.build()
	);

	private ColorfulPotsDataComponents() {
	}

	public static void bootstrap() {
		// Force class loading so static component registration runs during mod init.
	}

	public static boolean isDiamonded(ItemStack stack) {
		return stack.getOrDefault(DIAMONDED, false);
	}
}
