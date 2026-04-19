package com.fiverka.colorfulpots.mixin;

import com.fiverka.colorfulpots.access.DiamondPotAccess;
import com.fiverka.colorfulpots.component.ColorfulPotsDataComponents;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.NbtOps;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.world.item.component.TypedEntityData;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.DecoratedPotBlockEntity;
import net.minecraft.world.level.block.entity.PotDecorations;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DecoratedPotBlockEntity.class)
public abstract class DecoratedPotBlockEntityMixin implements DiamondPotAccess {
	@Shadow
	private PotDecorations decorations;

	@Unique
	private static final String COLORFUL_POTS_DIAMONDED_TAG = "colorful_pots_diamonded";

	@Unique
	private static final String COLORFUL_POTS_DIAMONDED_DECORATIONS_TAG = "colorful_pots_diamonded_decorations";

	@Unique
	private boolean colorfulPots$diamonded;

	@Unique
	private PotDecorations colorfulPots$decorationsBeforeApply;

	@Inject(method = "saveAdditional", at = @At("TAIL"))
	private void colorfulPots$saveDiamondState(ValueOutput output, CallbackInfo ci) {
		if (this.colorfulPots$diamonded) {
			output.putBoolean(COLORFUL_POTS_DIAMONDED_TAG, true);
			if (!PotDecorations.EMPTY.equals(this.decorations)) {
				output.store(COLORFUL_POTS_DIAMONDED_DECORATIONS_TAG, PotDecorations.CODEC, this.decorations);
			}
		}
	}

	@Inject(method = "loadAdditional", at = @At("TAIL"))
	private void colorfulPots$loadDiamondState(ValueInput input, CallbackInfo ci) {
		this.colorfulPots$diamonded = input.getBooleanOr(COLORFUL_POTS_DIAMONDED_TAG, false);
		if (this.colorfulPots$diamonded && PotDecorations.EMPTY.equals(this.decorations)) {
			PotDecorations decorations = input.read(COLORFUL_POTS_DIAMONDED_DECORATIONS_TAG, PotDecorations.CODEC)
				.orElse(PotDecorations.EMPTY);
			if (!PotDecorations.EMPTY.equals(decorations)) {
				this.decorations = decorations;
			}
		}
	}

	@Inject(method = "collectImplicitComponents", at = @At("TAIL"))
	private void colorfulPots$collectDiamondComponent(DataComponentMap.Builder builder, CallbackInfo ci) {
		if (this.colorfulPots$diamonded) {
			builder.set(ColorfulPotsDataComponents.DIAMONDED, true);
			builder.set(ColorfulPotsDataComponents.DIAMONDED_DECORATIONS, this.decorations);
		}
	}

	@Inject(method = "applyImplicitComponents", at = @At("HEAD"))
	private void colorfulPots$captureDecorationsBeforeApply(DataComponentGetter componentGetter, CallbackInfo ci) {
		this.colorfulPots$decorationsBeforeApply = this.decorations;
	}

	@Inject(method = "applyImplicitComponents", at = @At("TAIL"))
	private void colorfulPots$applyDiamondComponent(DataComponentGetter componentGetter, CallbackInfo ci) {
		PotDecorations previousDecorations = this.colorfulPots$decorationsBeforeApply;
		this.colorfulPots$decorationsBeforeApply = null;

		if (previousDecorations != null
			&& PotDecorations.EMPTY.equals(this.decorations)
			&& !PotDecorations.EMPTY.equals(previousDecorations)) {
			this.decorations = previousDecorations;
		}

		this.colorfulPots$diamonded = componentGetter.getOrDefault(ColorfulPotsDataComponents.DIAMONDED, false);
		PotDecorations decorations = componentGetter.get(ColorfulPotsDataComponents.DIAMONDED_DECORATIONS);
		if (decorations != null
			&& PotDecorations.EMPTY.equals(this.decorations)
			&& !PotDecorations.EMPTY.equals(decorations)) {
			this.decorations = decorations;
		}

		TypedEntityData<BlockEntityType<?>> blockEntityData = componentGetter.get(DataComponents.BLOCK_ENTITY_DATA);
		if (blockEntityData != null
			&& blockEntityData.type() == BlockEntityType.DECORATED_POT
			&& PotDecorations.EMPTY.equals(this.decorations)) {
			PotDecorations sherdsDecorations = blockEntityData.copyTagWithoutId()
				.read("sherds", PotDecorations.CODEC, NbtOps.INSTANCE)
				.orElse(null);
			if (sherdsDecorations != null && !PotDecorations.EMPTY.equals(sherdsDecorations)) {
				this.decorations = sherdsDecorations;
			}
		}
	}

	@Override
	public boolean colorfulPots$isDiamonded() {
		return this.colorfulPots$diamonded;
	}

	@Override
	public void colorfulPots$setDiamonded(boolean diamonded) {
		this.colorfulPots$diamonded = diamonded;
	}
}
