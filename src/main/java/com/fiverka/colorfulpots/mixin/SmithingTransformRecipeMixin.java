package com.fiverka.colorfulpots.mixin;

import com.fiverka.colorfulpots.component.ColorfulPotsDataComponents;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.NbtOps;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.TypedEntityData;
import net.minecraft.world.item.crafting.SmithingRecipeInput;
import net.minecraft.world.item.crafting.SmithingTransformRecipe;
import net.minecraft.world.level.block.entity.BlockEntityType;
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
	private static int colorfulPots$resolveCoating(
		boolean diamonded,
		boolean golded,
		boolean coppered,
		boolean emeralded,
		boolean amethysted,
		boolean resined,
		boolean redstoned
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
		boolean redstoned
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
		return count;
	}

	@Unique
	private static DataComponentType<Boolean> colorfulPots$getCoatingComponent(int coating) {
		return switch (coating) {
			case COLORFUL_POTS_COATING_DIAMOND -> ColorfulPotsDataComponents.DIAMONDED;
			case COLORFUL_POTS_COATING_GOLD -> ColorfulPotsDataComponents.GOLDED;
			case COLORFUL_POTS_COATING_COPPER -> ColorfulPotsDataComponents.COPPERED;
			case COLORFUL_POTS_COATING_EMERALD -> ColorfulPotsDataComponents.EMERALDED;
			case COLORFUL_POTS_COATING_AMETHYST -> ColorfulPotsDataComponents.AMETHYSTED;
			case COLORFUL_POTS_COATING_RESIN -> ColorfulPotsDataComponents.RESINED;
			case COLORFUL_POTS_COATING_REDSTONE -> ColorfulPotsDataComponents.REDSTONED;
			default -> null;
		};
	}

	@Unique
	private static DataComponentType<PotDecorations> colorfulPots$getCoatingDecorationsComponent(int coating) {
		return switch (coating) {
			case COLORFUL_POTS_COATING_DIAMOND -> ColorfulPotsDataComponents.DIAMONDED_DECORATIONS;
			case COLORFUL_POTS_COATING_GOLD -> ColorfulPotsDataComponents.GOLDED_DECORATIONS;
			case COLORFUL_POTS_COATING_COPPER -> ColorfulPotsDataComponents.COPPERED_DECORATIONS;
			case COLORFUL_POTS_COATING_EMERALD -> ColorfulPotsDataComponents.EMERALDED_DECORATIONS;
			case COLORFUL_POTS_COATING_AMETHYST -> ColorfulPotsDataComponents.AMETHYSTED_DECORATIONS;
			case COLORFUL_POTS_COATING_RESIN -> ColorfulPotsDataComponents.RESINED_DECORATIONS;
			case COLORFUL_POTS_COATING_REDSTONE -> ColorfulPotsDataComponents.REDSTONED_DECORATIONS;
			default -> null;
		};
	}

	@Unique
	private static void colorfulPots$clearAllCoatings(ItemStack stack) {
		stack.set(ColorfulPotsDataComponents.DIAMONDED, false);
		stack.set(ColorfulPotsDataComponents.GOLDED, false);
		stack.set(ColorfulPotsDataComponents.COPPERED, false);
		stack.set(ColorfulPotsDataComponents.EMERALDED, false);
		stack.set(ColorfulPotsDataComponents.AMETHYSTED, false);
		stack.set(ColorfulPotsDataComponents.RESINED, false);
		stack.set(ColorfulPotsDataComponents.REDSTONED, false);
	}

	@Unique
	private static void colorfulPots$clearAllCoatingDecorations(ItemStack stack) {
		stack.remove(ColorfulPotsDataComponents.DIAMONDED_DECORATIONS);
		stack.remove(ColorfulPotsDataComponents.GOLDED_DECORATIONS);
		stack.remove(ColorfulPotsDataComponents.COPPERED_DECORATIONS);
		stack.remove(ColorfulPotsDataComponents.EMERALDED_DECORATIONS);
		stack.remove(ColorfulPotsDataComponents.AMETHYSTED_DECORATIONS);
		stack.remove(ColorfulPotsDataComponents.RESINED_DECORATIONS);
		stack.remove(ColorfulPotsDataComponents.REDSTONED_DECORATIONS);
	}

	@Inject(
		method = "assemble(Lnet/minecraft/world/item/crafting/SmithingRecipeInput;Lnet/minecraft/core/HolderLookup$Provider;)Lnet/minecraft/world/item/ItemStack;",
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

		boolean diamonded = result.getOrDefault(ColorfulPotsDataComponents.DIAMONDED, false);
		boolean golded = result.getOrDefault(ColorfulPotsDataComponents.GOLDED, false);
		boolean coppered = result.getOrDefault(ColorfulPotsDataComponents.COPPERED, false);
		boolean emeralded = result.getOrDefault(ColorfulPotsDataComponents.EMERALDED, false);
		boolean amethysted = result.getOrDefault(ColorfulPotsDataComponents.AMETHYSTED, false);
		boolean resined = result.getOrDefault(ColorfulPotsDataComponents.RESINED, false);
		boolean redstoned = result.getOrDefault(ColorfulPotsDataComponents.REDSTONED, false);

		int coatingCount = colorfulPots$coatingCount(diamonded, golded, coppered, emeralded, amethysted, resined, redstoned);
		if (coatingCount == 0) {
			return;
		}

		int coating = colorfulPots$resolveCoating(diamonded, golded, coppered, emeralded, amethysted, resined, redstoned);
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

		TypedEntityData<BlockEntityType<?>> blockEntityData = stack.get(DataComponents.BLOCK_ENTITY_DATA);
		if (blockEntityData != null && blockEntityData.type() == BlockEntityType.DECORATED_POT) {
			return blockEntityData.copyTagWithoutId()
				.read("sherds", PotDecorations.CODEC, registries.createSerializationContext(NbtOps.INSTANCE))
				.orElse(decorations);
		}

		return decorations;
	}
}
