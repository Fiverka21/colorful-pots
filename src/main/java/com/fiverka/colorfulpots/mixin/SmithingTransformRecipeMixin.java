package com.fiverka.colorfulpots.mixin;

import com.fiverka.colorfulpots.component.ColorfulPotsDataComponents;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.SmithingRecipeInput;
import net.minecraft.world.item.crafting.SmithingTransformRecipe;
import net.minecraft.world.level.block.entity.PotDecorations;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SmithingTransformRecipe.class)
public abstract class SmithingTransformRecipeMixin {
	@Unique
	private static final int COLORFUL_POTS_COATING_NONE = -1;

	@Unique
	private static final int COLORFUL_POTS_COATING_DIAMOND = 0;

	@Unique
	private static final int COLORFUL_POTS_COATING_GOLD = 1;

	@Unique
	private static final int COLORFUL_POTS_COATING_COPPER = 2;

	@Unique
	private static final int COLORFUL_POTS_COATING_EMERALD = 3;

	@Unique
	private static final int COLORFUL_POTS_COATING_AMETHYST = 4;

	@Unique
	private static final int COLORFUL_POTS_COATING_RESIN = 5;

	@Unique
	private static final int COLORFUL_POTS_COATING_REDSTONE = 6;

	@Unique
	private static final int COLORFUL_POTS_COATING_IRON = 7;

	@Unique
	private static final int COLORFUL_POTS_COATING_QUARTZ = 8;

	@Unique
	private static final int COLORFUL_POTS_COATING_LAPIS = 9;

	@Unique
	private static final int COLORFUL_POTS_COATING_NETHERITE = 10;

	@Unique
	private static int colorfulPots$resolveCoating(
		boolean diamonded,
		boolean golded,
		boolean coppered,
		boolean emeralded,
		boolean amethysted,
		boolean resined,
		boolean redstoned,
		boolean ironed,
		boolean quartzed,
		boolean lapised,
		boolean netherited
	) {
		int coating = COLORFUL_POTS_COATING_NONE;
		int count = 0;

		if (diamonded) {
			coating = COLORFUL_POTS_COATING_DIAMOND;
			count++;
		}
		if (golded) {
			coating = COLORFUL_POTS_COATING_GOLD;
			count++;
		}
		if (coppered) {
			coating = COLORFUL_POTS_COATING_COPPER;
			count++;
		}
		if (emeralded) {
			coating = COLORFUL_POTS_COATING_EMERALD;
			count++;
		}
		if (amethysted) {
			coating = COLORFUL_POTS_COATING_AMETHYST;
			count++;
		}
		if (resined) {
			coating = COLORFUL_POTS_COATING_RESIN;
			count++;
		}
		if (redstoned) {
			coating = COLORFUL_POTS_COATING_REDSTONE;
			count++;
		}
		if (ironed) {
			coating = COLORFUL_POTS_COATING_IRON;
			count++;
		}
		if (quartzed) {
			coating = COLORFUL_POTS_COATING_QUARTZ;
			count++;
		}
		if (lapised) {
			coating = COLORFUL_POTS_COATING_LAPIS;
			count++;
		}
		if (netherited) {
			coating = COLORFUL_POTS_COATING_NETHERITE;
			count++;
		}

		return count == 1 ? coating : COLORFUL_POTS_COATING_NONE;
	}

	@Unique
	private static int colorfulPots$coatingCount(
		boolean diamonded,
		boolean golded,
		boolean coppered,
		boolean emeralded,
		boolean amethysted,
		boolean resined,
		boolean redstoned,
		boolean ironed,
		boolean quartzed,
		boolean lapised,
		boolean netherited
	) {
		int count = 0;
		if (diamonded) {
			count++;
		}
		if (golded) {
			count++;
		}
		if (coppered) {
			count++;
		}
		if (emeralded) {
			count++;
		}
		if (amethysted) {
			count++;
		}
		if (resined) {
			count++;
		}
		if (redstoned) {
			count++;
		}
		if (ironed) {
			count++;
		}
		if (quartzed) {
			count++;
		}
		if (lapised) {
			count++;
		}
		if (netherited) {
			count++;
		}
		return count;
	}

	@Unique
	private static int colorfulPots$resolveCoatingFromAddition(ItemStack addition) {
		if (addition.is(Items.DIAMOND)) {
			return COLORFUL_POTS_COATING_DIAMOND;
		}
		if (addition.is(Items.GOLD_INGOT)) {
			return COLORFUL_POTS_COATING_GOLD;
		}
		if (addition.is(Items.COPPER_INGOT)) {
			return COLORFUL_POTS_COATING_COPPER;
		}
		if (addition.is(Items.EMERALD)) {
			return COLORFUL_POTS_COATING_EMERALD;
		}
		if (addition.is(Items.AMETHYST_SHARD)) {
			return COLORFUL_POTS_COATING_AMETHYST;
		}
		if (addition.is(Items.RESIN_CLUMP) || addition.is(Items.RESIN_BRICK)) {
			return COLORFUL_POTS_COATING_RESIN;
		}
		if (addition.is(Items.REDSTONE)) {
			return COLORFUL_POTS_COATING_REDSTONE;
		}
		if (addition.is(Items.IRON_INGOT)) {
			return COLORFUL_POTS_COATING_IRON;
		}
		if (addition.is(Items.QUARTZ)) {
			return COLORFUL_POTS_COATING_QUARTZ;
		}
		if (addition.is(Items.LAPIS_LAZULI)) {
			return COLORFUL_POTS_COATING_LAPIS;
		}
		if (addition.is(Items.NETHERITE_INGOT)) {
			return COLORFUL_POTS_COATING_NETHERITE;
		}

		return COLORFUL_POTS_COATING_NONE;
	}

	@Unique
	private static DataComponentType<Boolean> colorfulPots$getCoatingComponent(int coating) {
		return switch (coating) {
			case COLORFUL_POTS_COATING_DIAMOND -> ColorfulPotsDataComponents.DIAMONDED.get();
			case COLORFUL_POTS_COATING_GOLD -> ColorfulPotsDataComponents.GOLDED.get();
			case COLORFUL_POTS_COATING_COPPER -> ColorfulPotsDataComponents.COPPERED.get();
			case COLORFUL_POTS_COATING_EMERALD -> ColorfulPotsDataComponents.EMERALDED.get();
			case COLORFUL_POTS_COATING_AMETHYST -> ColorfulPotsDataComponents.AMETHYSTED.get();
			case COLORFUL_POTS_COATING_RESIN -> ColorfulPotsDataComponents.RESINED.get();
			case COLORFUL_POTS_COATING_REDSTONE -> ColorfulPotsDataComponents.REDSTONED.get();
			case COLORFUL_POTS_COATING_IRON -> ColorfulPotsDataComponents.IRONED.get();
			case COLORFUL_POTS_COATING_QUARTZ -> ColorfulPotsDataComponents.QUARTZED.get();
			case COLORFUL_POTS_COATING_LAPIS -> ColorfulPotsDataComponents.LAPISED.get();
			case COLORFUL_POTS_COATING_NETHERITE -> ColorfulPotsDataComponents.NETHERITED.get();
			default -> null;
		};
	}

	@Unique
	private static DataComponentType<PotDecorations> colorfulPots$getCoatingDecorationsComponent(int coating) {
		return switch (coating) {
			case COLORFUL_POTS_COATING_DIAMOND -> ColorfulPotsDataComponents.DIAMONDED_DECORATIONS.get();
			case COLORFUL_POTS_COATING_GOLD -> ColorfulPotsDataComponents.GOLDED_DECORATIONS.get();
			case COLORFUL_POTS_COATING_COPPER -> ColorfulPotsDataComponents.COPPERED_DECORATIONS.get();
			case COLORFUL_POTS_COATING_EMERALD -> ColorfulPotsDataComponents.EMERALDED_DECORATIONS.get();
			case COLORFUL_POTS_COATING_AMETHYST -> ColorfulPotsDataComponents.AMETHYSTED_DECORATIONS.get();
			case COLORFUL_POTS_COATING_RESIN -> ColorfulPotsDataComponents.RESINED_DECORATIONS.get();
			case COLORFUL_POTS_COATING_REDSTONE -> ColorfulPotsDataComponents.REDSTONED_DECORATIONS.get();
			case COLORFUL_POTS_COATING_IRON -> ColorfulPotsDataComponents.IRONED_DECORATIONS.get();
			case COLORFUL_POTS_COATING_QUARTZ -> ColorfulPotsDataComponents.QUARTZED_DECORATIONS.get();
			case COLORFUL_POTS_COATING_LAPIS -> ColorfulPotsDataComponents.LAPISED_DECORATIONS.get();
			case COLORFUL_POTS_COATING_NETHERITE -> ColorfulPotsDataComponents.NETHERITED_DECORATIONS.get();
			default -> null;
		};
	}

	@Unique
	private static void colorfulPots$clearAllCoatings(ItemStack stack) {
		stack.set(ColorfulPotsDataComponents.DIAMONDED.get(), false);
		stack.set(ColorfulPotsDataComponents.GOLDED.get(), false);
		stack.set(ColorfulPotsDataComponents.COPPERED.get(), false);
		stack.set(ColorfulPotsDataComponents.EMERALDED.get(), false);
		stack.set(ColorfulPotsDataComponents.AMETHYSTED.get(), false);
		stack.set(ColorfulPotsDataComponents.RESINED.get(), false);
		stack.set(ColorfulPotsDataComponents.REDSTONED.get(), false);
		stack.set(ColorfulPotsDataComponents.IRONED.get(), false);
		stack.set(ColorfulPotsDataComponents.QUARTZED.get(), false);
		stack.set(ColorfulPotsDataComponents.LAPISED.get(), false);
		stack.set(ColorfulPotsDataComponents.NETHERITED.get(), false);
	}

	@Unique
	private static void colorfulPots$clearAllCoatingDecorations(ItemStack stack) {
		stack.remove(ColorfulPotsDataComponents.DIAMONDED_DECORATIONS.get());
		stack.remove(ColorfulPotsDataComponents.GOLDED_DECORATIONS.get());
		stack.remove(ColorfulPotsDataComponents.COPPERED_DECORATIONS.get());
		stack.remove(ColorfulPotsDataComponents.EMERALDED_DECORATIONS.get());
		stack.remove(ColorfulPotsDataComponents.AMETHYSTED_DECORATIONS.get());
		stack.remove(ColorfulPotsDataComponents.RESINED_DECORATIONS.get());
		stack.remove(ColorfulPotsDataComponents.REDSTONED_DECORATIONS.get());
		stack.remove(ColorfulPotsDataComponents.IRONED_DECORATIONS.get());
		stack.remove(ColorfulPotsDataComponents.QUARTZED_DECORATIONS.get());
		stack.remove(ColorfulPotsDataComponents.LAPISED_DECORATIONS.get());
		stack.remove(ColorfulPotsDataComponents.NETHERITED_DECORATIONS.get());
	}

	@Inject(
		method = "assemble",
		at = @At("RETURN"),
		cancellable = true
	)
	private void colorfulPots$preserveDecorationsOnCoatedPot(
		SmithingRecipeInput input,
		HolderLookup.Provider registries,
		CallbackInfoReturnable<ItemStack> cir
	) {
		ItemStack result = cir.getReturnValue();
		if (!result.is(Items.DECORATED_POT)) {
			return;
		}

		boolean diamonded = result.getOrDefault(ColorfulPotsDataComponents.DIAMONDED.get(), false);
		boolean golded = result.getOrDefault(ColorfulPotsDataComponents.GOLDED.get(), false);
		boolean coppered = result.getOrDefault(ColorfulPotsDataComponents.COPPERED.get(), false);
		boolean emeralded = result.getOrDefault(ColorfulPotsDataComponents.EMERALDED.get(), false);
		boolean amethysted = result.getOrDefault(ColorfulPotsDataComponents.AMETHYSTED.get(), false);
		boolean resined = result.getOrDefault(ColorfulPotsDataComponents.RESINED.get(), false);
		boolean redstoned = result.getOrDefault(ColorfulPotsDataComponents.REDSTONED.get(), false);
		boolean ironed = result.getOrDefault(ColorfulPotsDataComponents.IRONED.get(), false);
		boolean quartzed = result.getOrDefault(ColorfulPotsDataComponents.QUARTZED.get(), false);
		boolean lapised = result.getOrDefault(ColorfulPotsDataComponents.LAPISED.get(), false);
		boolean netherited = result.getOrDefault(ColorfulPotsDataComponents.NETHERITED.get(), false);

		int coating = colorfulPots$resolveCoatingFromAddition(input.addition());
		if (coating == COLORFUL_POTS_COATING_NONE) {
			int coatingCount = colorfulPots$coatingCount(
				diamonded,
				golded,
				coppered,
				emeralded,
				amethysted,
				resined,
				redstoned,
				ironed,
				quartzed,
				lapised,
				netherited
			);
			if (coatingCount == 0) {
				return;
			}

			coating = colorfulPots$resolveCoating(
				diamonded,
				golded,
				coppered,
				emeralded,
				amethysted,
				resined,
				redstoned,
				ironed,
				quartzed,
				lapised,
				netherited
			);
		}
		colorfulPots$clearAllCoatings(result);
		colorfulPots$clearAllCoatingDecorations(result);

		if (coating == COLORFUL_POTS_COATING_NONE) {
			return;
		}

		DataComponentType<Boolean> coatingComponent = colorfulPots$getCoatingComponent(coating);
		DataComponentType<PotDecorations> coatingDecorationsComponent = colorfulPots$getCoatingDecorationsComponent(coating);
		if (coatingComponent == null || coatingDecorationsComponent == null) {
			return;
		}

		result.set(coatingComponent, true);

		PotDecorations decorations = this.colorfulPots$resolveDecorations(input.base(), registries);
		if (decorations != null) {
			result.set(DataComponents.POT_DECORATIONS, decorations);
			result.set(coatingDecorationsComponent, decorations);
		}
	}

	@Unique
	private PotDecorations colorfulPots$resolveDecorations(ItemStack stack, HolderLookup.Provider registries) {
		PotDecorations decorations = stack.get(DataComponents.POT_DECORATIONS);
		if (decorations != null && !PotDecorations.EMPTY.equals(decorations)) {
			return decorations;
		}

		// Legacy tag support removed for 1.21.11 compatibility
		// The new data component system should handle all cases
		return decorations;
	}
}
