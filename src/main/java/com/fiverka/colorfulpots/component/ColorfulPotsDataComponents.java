package com.fiverka.colorfulpots.component;

import com.fiverka.colorfulpots.ColorfulPotsMod;
import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.PotDecorations;

public final class ColorfulPotsDataComponents {
	public static final DataComponentType<Boolean> DIAMONDED = Registry.register(
		BuiltInRegistries.DATA_COMPONENT_TYPE,
		ResourceLocation.fromNamespaceAndPath(ColorfulPotsMod.MOD_ID, "diamonded"),
		DataComponentType.<Boolean>builder()
			.persistent(Codec.BOOL)
			.networkSynchronized(ByteBufCodecs.BOOL)
			.build()
	);

	public static final DataComponentType<PotDecorations> DIAMONDED_DECORATIONS = Registry.register(
		BuiltInRegistries.DATA_COMPONENT_TYPE,
		ResourceLocation.fromNamespaceAndPath(ColorfulPotsMod.MOD_ID, "diamonded_decorations"),
		DataComponentType.<PotDecorations>builder()
			.persistent(PotDecorations.CODEC)
			.networkSynchronized(PotDecorations.STREAM_CODEC)
			.build()
	);

	public static final DataComponentType<Boolean> GOLDED = Registry.register(
		BuiltInRegistries.DATA_COMPONENT_TYPE,
		ResourceLocation.fromNamespaceAndPath(ColorfulPotsMod.MOD_ID, "golded"),
		DataComponentType.<Boolean>builder()
			.persistent(Codec.BOOL)
			.networkSynchronized(ByteBufCodecs.BOOL)
			.build()
	);

	public static final DataComponentType<PotDecorations> GOLDED_DECORATIONS = Registry.register(
		BuiltInRegistries.DATA_COMPONENT_TYPE,
		ResourceLocation.fromNamespaceAndPath(ColorfulPotsMod.MOD_ID, "golded_decorations"),
		DataComponentType.<PotDecorations>builder()
			.persistent(PotDecorations.CODEC)
			.networkSynchronized(PotDecorations.STREAM_CODEC)
			.build()
	);

	public static final DataComponentType<Boolean> COPPERED = Registry.register(
		BuiltInRegistries.DATA_COMPONENT_TYPE,
		ResourceLocation.fromNamespaceAndPath(ColorfulPotsMod.MOD_ID, "coppered"),
		DataComponentType.<Boolean>builder()
			.persistent(Codec.BOOL)
			.networkSynchronized(ByteBufCodecs.BOOL)
			.build()
	);

	public static final DataComponentType<PotDecorations> COPPERED_DECORATIONS = Registry.register(
		BuiltInRegistries.DATA_COMPONENT_TYPE,
		ResourceLocation.fromNamespaceAndPath(ColorfulPotsMod.MOD_ID, "coppered_decorations"),
		DataComponentType.<PotDecorations>builder()
			.persistent(PotDecorations.CODEC)
			.networkSynchronized(PotDecorations.STREAM_CODEC)
			.build()
	);

	public static final DataComponentType<Boolean> EMERALDED = Registry.register(
		BuiltInRegistries.DATA_COMPONENT_TYPE,
		ResourceLocation.fromNamespaceAndPath(ColorfulPotsMod.MOD_ID, "emeralded"),
		DataComponentType.<Boolean>builder()
			.persistent(Codec.BOOL)
			.networkSynchronized(ByteBufCodecs.BOOL)
			.build()
	);

	public static final DataComponentType<PotDecorations> EMERALDED_DECORATIONS = Registry.register(
		BuiltInRegistries.DATA_COMPONENT_TYPE,
		ResourceLocation.fromNamespaceAndPath(ColorfulPotsMod.MOD_ID, "emeralded_decorations"),
		DataComponentType.<PotDecorations>builder()
			.persistent(PotDecorations.CODEC)
			.networkSynchronized(PotDecorations.STREAM_CODEC)
			.build()
	);

	public static final DataComponentType<Boolean> AMETHYSTED = Registry.register(
		BuiltInRegistries.DATA_COMPONENT_TYPE,
		ResourceLocation.fromNamespaceAndPath(ColorfulPotsMod.MOD_ID, "amethysted"),
		DataComponentType.<Boolean>builder()
			.persistent(Codec.BOOL)
			.networkSynchronized(ByteBufCodecs.BOOL)
			.build()
	);

	public static final DataComponentType<PotDecorations> AMETHYSTED_DECORATIONS = Registry.register(
		BuiltInRegistries.DATA_COMPONENT_TYPE,
		ResourceLocation.fromNamespaceAndPath(ColorfulPotsMod.MOD_ID, "amethysted_decorations"),
		DataComponentType.<PotDecorations>builder()
			.persistent(PotDecorations.CODEC)
			.networkSynchronized(PotDecorations.STREAM_CODEC)
			.build()
	);

	public static final DataComponentType<Boolean> RESINED = Registry.register(
		BuiltInRegistries.DATA_COMPONENT_TYPE,
		ResourceLocation.fromNamespaceAndPath(ColorfulPotsMod.MOD_ID, "resined"),
		DataComponentType.<Boolean>builder()
			.persistent(Codec.BOOL)
			.networkSynchronized(ByteBufCodecs.BOOL)
			.build()
	);

	public static final DataComponentType<PotDecorations> RESINED_DECORATIONS = Registry.register(
		BuiltInRegistries.DATA_COMPONENT_TYPE,
		ResourceLocation.fromNamespaceAndPath(ColorfulPotsMod.MOD_ID, "resined_decorations"),
		DataComponentType.<PotDecorations>builder()
			.persistent(PotDecorations.CODEC)
			.networkSynchronized(PotDecorations.STREAM_CODEC)
			.build()
	);

	public static final DataComponentType<Boolean> REDSTONED = Registry.register(
		BuiltInRegistries.DATA_COMPONENT_TYPE,
		ResourceLocation.fromNamespaceAndPath(ColorfulPotsMod.MOD_ID, "redstoned"),
		DataComponentType.<Boolean>builder()
			.persistent(Codec.BOOL)
			.networkSynchronized(ByteBufCodecs.BOOL)
			.build()
	);

	public static final DataComponentType<PotDecorations> REDSTONED_DECORATIONS = Registry.register(
		BuiltInRegistries.DATA_COMPONENT_TYPE,
		ResourceLocation.fromNamespaceAndPath(ColorfulPotsMod.MOD_ID, "redstoned_decorations"),
		DataComponentType.<PotDecorations>builder()
			.persistent(PotDecorations.CODEC)
			.networkSynchronized(PotDecorations.STREAM_CODEC)
			.build()
	);

	public static final DataComponentType<Boolean> IRONED = Registry.register(
		BuiltInRegistries.DATA_COMPONENT_TYPE,
		ResourceLocation.fromNamespaceAndPath(ColorfulPotsMod.MOD_ID, "ironed"),
		DataComponentType.<Boolean>builder()
			.persistent(Codec.BOOL)
			.networkSynchronized(ByteBufCodecs.BOOL)
			.build()
	);

	public static final DataComponentType<PotDecorations> IRONED_DECORATIONS = Registry.register(
		BuiltInRegistries.DATA_COMPONENT_TYPE,
		ResourceLocation.fromNamespaceAndPath(ColorfulPotsMod.MOD_ID, "ironed_decorations"),
		DataComponentType.<PotDecorations>builder()
			.persistent(PotDecorations.CODEC)
			.networkSynchronized(PotDecorations.STREAM_CODEC)
			.build()
	);

	public static final DataComponentType<Boolean> QUARTZED = Registry.register(
		BuiltInRegistries.DATA_COMPONENT_TYPE,
		ResourceLocation.fromNamespaceAndPath(ColorfulPotsMod.MOD_ID, "quartzed"),
		DataComponentType.<Boolean>builder()
			.persistent(Codec.BOOL)
			.networkSynchronized(ByteBufCodecs.BOOL)
			.build()
	);

	public static final DataComponentType<PotDecorations> QUARTZED_DECORATIONS = Registry.register(
		BuiltInRegistries.DATA_COMPONENT_TYPE,
		ResourceLocation.fromNamespaceAndPath(ColorfulPotsMod.MOD_ID, "quartzed_decorations"),
		DataComponentType.<PotDecorations>builder()
			.persistent(PotDecorations.CODEC)
			.networkSynchronized(PotDecorations.STREAM_CODEC)
			.build()
	);

	public static final DataComponentType<Boolean> LAPISED = Registry.register(
		BuiltInRegistries.DATA_COMPONENT_TYPE,
		ResourceLocation.fromNamespaceAndPath(ColorfulPotsMod.MOD_ID, "lapised"),
		DataComponentType.<Boolean>builder()
			.persistent(Codec.BOOL)
			.networkSynchronized(ByteBufCodecs.BOOL)
			.build()
	);

	public static final DataComponentType<PotDecorations> LAPISED_DECORATIONS = Registry.register(
		BuiltInRegistries.DATA_COMPONENT_TYPE,
		ResourceLocation.fromNamespaceAndPath(ColorfulPotsMod.MOD_ID, "lapised_decorations"),
		DataComponentType.<PotDecorations>builder()
			.persistent(PotDecorations.CODEC)
			.networkSynchronized(PotDecorations.STREAM_CODEC)
			.build()
	);

	public static final DataComponentType<Boolean> NETHERITED = Registry.register(
		BuiltInRegistries.DATA_COMPONENT_TYPE,
		ResourceLocation.fromNamespaceAndPath(ColorfulPotsMod.MOD_ID, "netherited"),
		DataComponentType.<Boolean>builder()
			.persistent(Codec.BOOL)
			.networkSynchronized(ByteBufCodecs.BOOL)
			.build()
	);

	public static final DataComponentType<PotDecorations> NETHERITED_DECORATIONS = Registry.register(
		BuiltInRegistries.DATA_COMPONENT_TYPE,
		ResourceLocation.fromNamespaceAndPath(ColorfulPotsMod.MOD_ID, "netherited_decorations"),
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

	public static boolean isGolded(ItemStack stack) {
		return stack.getOrDefault(GOLDED, false);
	}

	public static boolean isCoppered(ItemStack stack) {
		return stack.getOrDefault(COPPERED, false);
	}

	public static boolean isEmeralded(ItemStack stack) {
		return stack.getOrDefault(EMERALDED, false);
	}

	public static boolean isAmethysted(ItemStack stack) {
		return stack.getOrDefault(AMETHYSTED, false);
	}

	public static boolean isResined(ItemStack stack) {
		return stack.getOrDefault(RESINED, false);
	}

	public static boolean isRedstoned(ItemStack stack) {
		return stack.getOrDefault(REDSTONED, false);
	}

	public static boolean isIroned(ItemStack stack) {
		return stack.getOrDefault(IRONED, false);
	}

	public static boolean isQuartzed(ItemStack stack) {
		return stack.getOrDefault(QUARTZED, false);
	}

	public static boolean isLapised(ItemStack stack) {
		return stack.getOrDefault(LAPISED, false);
	}

	public static boolean isNetherited(ItemStack stack) {
		return stack.getOrDefault(NETHERITED, false);
	}
}
