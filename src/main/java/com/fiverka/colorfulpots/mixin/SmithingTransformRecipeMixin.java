package com.fiverka.colorfulpots.mixin;

import com.fiverka.colorfulpots.component.ColorfulPotsDataComponents;
import net.minecraft.core.HolderLookup;
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
	@Inject(
		method = "assemble(Lnet/minecraft/world/item/crafting/SmithingRecipeInput;Lnet/minecraft/core/HolderLookup$Provider;)Lnet/minecraft/world/item/ItemStack;",
		at = @At("RETURN"),
		cancellable = true
	)
	private void colorfulPots$preserveDecorationsOnDiamondedPot(
		SmithingRecipeInput input,
		HolderLookup.Provider registries,
		CallbackInfoReturnable<ItemStack> cir
	) {
		ItemStack result = cir.getReturnValue();
		if (!result.is(Items.DECORATED_POT) || !result.getOrDefault(ColorfulPotsDataComponents.DIAMONDED, false)) {
			return;
		}

		PotDecorations decorations = this.colorfulPots$resolveDecorations(input.base(), registries);
		if (decorations != null) {
			result.set(DataComponents.POT_DECORATIONS, decorations);
			result.set(ColorfulPotsDataComponents.DIAMONDED_DECORATIONS, decorations);
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
