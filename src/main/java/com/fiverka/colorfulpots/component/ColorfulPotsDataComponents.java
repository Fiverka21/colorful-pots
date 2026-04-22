package com.fiverka.colorfulpots.component;

import com.fiverka.colorfulpots.ColorfulPotsMod;
import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.PotDecorations;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ColorfulPotsDataComponents {
	public static final DeferredRegister.DataComponents DATA_COMPONENTS = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, ColorfulPotsMod.MOD_ID);

	public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> DIAMONDED = registerBoolean("diamonded");
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<PotDecorations>> DIAMONDED_DECORATIONS = registerDecorations("diamonded_decorations");
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> GOLDED = registerBoolean("golded");
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<PotDecorations>> GOLDED_DECORATIONS = registerDecorations("golded_decorations");
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> COPPERED = registerBoolean("coppered");
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<PotDecorations>> COPPERED_DECORATIONS = registerDecorations("coppered_decorations");
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> EMERALDED = registerBoolean("emeralded");
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<PotDecorations>> EMERALDED_DECORATIONS = registerDecorations("emeralded_decorations");
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> AMETHYSTED = registerBoolean("amethysted");
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<PotDecorations>> AMETHYSTED_DECORATIONS = registerDecorations("amethysted_decorations");
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> RESINED = registerBoolean("resined");
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<PotDecorations>> RESINED_DECORATIONS = registerDecorations("resined_decorations");
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> REDSTONED = registerBoolean("redstoned");
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<PotDecorations>> REDSTONED_DECORATIONS = registerDecorations("redstoned_decorations");
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> IRONED = registerBoolean("ironed");
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<PotDecorations>> IRONED_DECORATIONS = registerDecorations("ironed_decorations");
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> QUARTZED = registerBoolean("quartzed");
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<PotDecorations>> QUARTZED_DECORATIONS = registerDecorations("quartzed_decorations");
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> LAPISED = registerBoolean("lapised");
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<PotDecorations>> LAPISED_DECORATIONS = registerDecorations("lapised_decorations");
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> NETHERITED = registerBoolean("netherited");
	public static final DeferredHolder<DataComponentType<?>, DataComponentType<PotDecorations>> NETHERITED_DECORATIONS = registerDecorations("netherited_decorations");

	private ColorfulPotsDataComponents() {
	}

	private static DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> registerBoolean(String name) {
		return DATA_COMPONENTS.registerComponentType(name, builder ->
			builder.persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL)
		);
	}

	private static DeferredHolder<DataComponentType<?>, DataComponentType<PotDecorations>> registerDecorations(String name) {
		return DATA_COMPONENTS.registerComponentType(name, builder ->
			builder.persistent(PotDecorations.CODEC).networkSynchronized(PotDecorations.STREAM_CODEC)
		);
	}

	public static void register(IEventBus modEventBus) {
		DATA_COMPONENTS.register(modEventBus);
	}

	public static boolean isDiamonded(ItemStack stack) {
		return stack.getOrDefault(DIAMONDED.get(), false);
	}

	public static boolean isGolded(ItemStack stack) {
		return stack.getOrDefault(GOLDED.get(), false);
	}

	public static boolean isCoppered(ItemStack stack) {
		return stack.getOrDefault(COPPERED.get(), false);
	}

	public static boolean isEmeralded(ItemStack stack) {
		return stack.getOrDefault(EMERALDED.get(), false);
	}

	public static boolean isAmethysted(ItemStack stack) {
		return stack.getOrDefault(AMETHYSTED.get(), false);
	}

	public static boolean isResined(ItemStack stack) {
		return stack.getOrDefault(RESINED.get(), false);
	}

	public static boolean isRedstoned(ItemStack stack) {
		return stack.getOrDefault(REDSTONED.get(), false);
	}

	public static boolean isIroned(ItemStack stack) {
		return stack.getOrDefault(IRONED.get(), false);
	}

	public static boolean isQuartzed(ItemStack stack) {
		return stack.getOrDefault(QUARTZED.get(), false);
	}

	public static boolean isLapised(ItemStack stack) {
		return stack.getOrDefault(LAPISED.get(), false);
	}

	public static boolean isNetherited(ItemStack stack) {
		return stack.getOrDefault(NETHERITED.get(), false);
	}
}
