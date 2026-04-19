package com.fiverka.colorfulpots.mixin;

import com.fiverka.colorfulpots.access.DiamondPotAccess;
import com.fiverka.colorfulpots.component.ColorfulPotsDataComponents;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.NbtOps;
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
	private static final String COLORFUL_POTS_DIAMONDED_TAG = "colorful_pots_diamonded";

	@Unique
	private static final String COLORFUL_POTS_GOLDED_TAG = "colorful_pots_golded";

	@Unique
	private static final String COLORFUL_POTS_COPPERED_TAG = "colorful_pots_coppered";

	@Unique
	private static final String COLORFUL_POTS_EMERALDED_TAG = "colorful_pots_emeralded";

	@Unique
	private static final String COLORFUL_POTS_AMETHYSTED_TAG = "colorful_pots_amethysted";

	@Unique
	private static final String COLORFUL_POTS_RESINED_TAG = "colorful_pots_resined";

	@Unique
	private static final String COLORFUL_POTS_REDSTONED_TAG = "colorful_pots_redstoned";

	@Unique
	private static final String COLORFUL_POTS_DIAMONDED_DECORATIONS_TAG = "colorful_pots_diamonded_decorations";

	@Unique
	private static final String COLORFUL_POTS_GOLDED_DECORATIONS_TAG = "colorful_pots_golded_decorations";

	@Unique
	private static final String COLORFUL_POTS_COPPERED_DECORATIONS_TAG = "colorful_pots_coppered_decorations";

	@Unique
	private static final String COLORFUL_POTS_EMERALDED_DECORATIONS_TAG = "colorful_pots_emeralded_decorations";

	@Unique
	private static final String COLORFUL_POTS_AMETHYSTED_DECORATIONS_TAG = "colorful_pots_amethysted_decorations";

	@Unique
	private static final String COLORFUL_POTS_RESINED_DECORATIONS_TAG = "colorful_pots_resined_decorations";

	@Unique
	private static final String COLORFUL_POTS_REDSTONED_DECORATIONS_TAG = "colorful_pots_redstoned_decorations";

	@Shadow
	private PotDecorations decorations;

	@Unique
	private boolean colorfulPots$diamonded;

	@Unique
	private boolean colorfulPots$golded;

	@Unique
	private boolean colorfulPots$coppered;

	@Unique
	private boolean colorfulPots$emeralded;

	@Unique
	private boolean colorfulPots$amethysted;

	@Unique
	private boolean colorfulPots$resined;

	@Unique
	private boolean colorfulPots$redstoned;

	@Unique
	private PotDecorations colorfulPots$decorationsBeforeApply;

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
	private int colorfulPots$getActiveCoatingFromState() {
		return colorfulPots$resolveCoating(
			this.colorfulPots$diamonded,
			this.colorfulPots$golded,
			this.colorfulPots$coppered,
			this.colorfulPots$emeralded,
			this.colorfulPots$amethysted,
			this.colorfulPots$resined,
			this.colorfulPots$redstoned
		);
	}

	@Unique
	private void colorfulPots$clearAllCoatings() {
		this.colorfulPots$diamonded = false;
		this.colorfulPots$golded = false;
		this.colorfulPots$coppered = false;
		this.colorfulPots$emeralded = false;
		this.colorfulPots$amethysted = false;
		this.colorfulPots$resined = false;
		this.colorfulPots$redstoned = false;
	}

	@Unique
	private void colorfulPots$setExclusiveCoating(int coating) {
		this.colorfulPots$clearAllCoatings();
		switch (coating) {
			case COLORFUL_POTS_COATING_DIAMOND -> this.colorfulPots$diamonded = true;
			case COLORFUL_POTS_COATING_GOLD -> this.colorfulPots$golded = true;
			case COLORFUL_POTS_COATING_COPPER -> this.colorfulPots$coppered = true;
			case COLORFUL_POTS_COATING_EMERALD -> this.colorfulPots$emeralded = true;
			case COLORFUL_POTS_COATING_AMETHYST -> this.colorfulPots$amethysted = true;
			case COLORFUL_POTS_COATING_RESIN -> this.colorfulPots$resined = true;
			case COLORFUL_POTS_COATING_REDSTONE -> this.colorfulPots$redstoned = true;
			default -> {
			}
		}
	}

	@Unique
	private static String colorfulPots$getCoatingTag(int coating) {
		return switch (coating) {
			case COLORFUL_POTS_COATING_DIAMOND -> COLORFUL_POTS_DIAMONDED_TAG;
			case COLORFUL_POTS_COATING_GOLD -> COLORFUL_POTS_GOLDED_TAG;
			case COLORFUL_POTS_COATING_COPPER -> COLORFUL_POTS_COPPERED_TAG;
			case COLORFUL_POTS_COATING_EMERALD -> COLORFUL_POTS_EMERALDED_TAG;
			case COLORFUL_POTS_COATING_AMETHYST -> COLORFUL_POTS_AMETHYSTED_TAG;
			case COLORFUL_POTS_COATING_RESIN -> COLORFUL_POTS_RESINED_TAG;
			case COLORFUL_POTS_COATING_REDSTONE -> COLORFUL_POTS_REDSTONED_TAG;
			default -> null;
		};
	}

	@Unique
	private static String colorfulPots$getCoatingDecorationsTag(int coating) {
		return switch (coating) {
			case COLORFUL_POTS_COATING_DIAMOND -> COLORFUL_POTS_DIAMONDED_DECORATIONS_TAG;
			case COLORFUL_POTS_COATING_GOLD -> COLORFUL_POTS_GOLDED_DECORATIONS_TAG;
			case COLORFUL_POTS_COATING_COPPER -> COLORFUL_POTS_COPPERED_DECORATIONS_TAG;
			case COLORFUL_POTS_COATING_EMERALD -> COLORFUL_POTS_EMERALDED_DECORATIONS_TAG;
			case COLORFUL_POTS_COATING_AMETHYST -> COLORFUL_POTS_AMETHYSTED_DECORATIONS_TAG;
			case COLORFUL_POTS_COATING_RESIN -> COLORFUL_POTS_RESINED_DECORATIONS_TAG;
			case COLORFUL_POTS_COATING_REDSTONE -> COLORFUL_POTS_REDSTONED_DECORATIONS_TAG;
			default -> null;
		};
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

	@Inject(method = "saveAdditional", at = @At("TAIL"))
	private void colorfulPots$saveDecorationState(ValueOutput output, CallbackInfo ci) {
		int coating = this.colorfulPots$getActiveCoatingFromState();
		if (coating == COLORFUL_POTS_COATING_NONE) {
			return;
		}

		String coatingTag = colorfulPots$getCoatingTag(coating);
		String decorationsTag = colorfulPots$getCoatingDecorationsTag(coating);
		if (coatingTag == null || decorationsTag == null) {
			return;
		}

		output.putBoolean(coatingTag, true);
		if (!PotDecorations.EMPTY.equals(this.decorations)) {
			output.store(decorationsTag, PotDecorations.CODEC, this.decorations);
		}
	}

	@Inject(method = "loadAdditional", at = @At("TAIL"))
	private void colorfulPots$loadDecorationState(ValueInput input, CallbackInfo ci) {
		int coating = colorfulPots$resolveCoating(
			input.getBooleanOr(COLORFUL_POTS_DIAMONDED_TAG, false),
			input.getBooleanOr(COLORFUL_POTS_GOLDED_TAG, false),
			input.getBooleanOr(COLORFUL_POTS_COPPERED_TAG, false),
			input.getBooleanOr(COLORFUL_POTS_EMERALDED_TAG, false),
			input.getBooleanOr(COLORFUL_POTS_AMETHYSTED_TAG, false),
			input.getBooleanOr(COLORFUL_POTS_RESINED_TAG, false),
			input.getBooleanOr(COLORFUL_POTS_REDSTONED_TAG, false)
		);
		this.colorfulPots$setExclusiveCoating(coating);

		if (coating == COLORFUL_POTS_COATING_NONE || !PotDecorations.EMPTY.equals(this.decorations)) {
			return;
		}

		String decorationsTag = colorfulPots$getCoatingDecorationsTag(coating);
		if (decorationsTag == null) {
			return;
		}

		PotDecorations loadedDecorations = input.read(decorationsTag, PotDecorations.CODEC).orElse(PotDecorations.EMPTY);
		if (!PotDecorations.EMPTY.equals(loadedDecorations)) {
			this.decorations = loadedDecorations;
		}
	}

	@Inject(method = "collectImplicitComponents", at = @At("TAIL"))
	private void colorfulPots$collectDecorationComponent(DataComponentMap.Builder builder, CallbackInfo ci) {
		int coating = this.colorfulPots$getActiveCoatingFromState();
		if (coating == COLORFUL_POTS_COATING_NONE) {
			return;
		}

		DataComponentType<Boolean> coatingComponent = colorfulPots$getCoatingComponent(coating);
		DataComponentType<PotDecorations> coatingDecorationsComponent = colorfulPots$getCoatingDecorationsComponent(coating);
		if (coatingComponent == null || coatingDecorationsComponent == null) {
			return;
		}

		builder.set(coatingComponent, true);
		builder.set(coatingDecorationsComponent, this.decorations);
	}

	@Inject(method = "applyImplicitComponents", at = @At("HEAD"))
	private void colorfulPots$captureDecorationsBeforeApply(DataComponentGetter componentGetter, CallbackInfo ci) {
		this.colorfulPots$decorationsBeforeApply = this.decorations;
	}

	@Inject(method = "applyImplicitComponents", at = @At("TAIL"))
	private void colorfulPots$applyDecorationComponent(DataComponentGetter componentGetter, CallbackInfo ci) {
		PotDecorations previousDecorations = this.colorfulPots$decorationsBeforeApply;
		this.colorfulPots$decorationsBeforeApply = null;

		if (previousDecorations != null
			&& PotDecorations.EMPTY.equals(this.decorations)
			&& !PotDecorations.EMPTY.equals(previousDecorations)) {
			this.decorations = previousDecorations;
		}

		int coating = colorfulPots$resolveCoating(
			componentGetter.getOrDefault(ColorfulPotsDataComponents.DIAMONDED, false),
			componentGetter.getOrDefault(ColorfulPotsDataComponents.GOLDED, false),
			componentGetter.getOrDefault(ColorfulPotsDataComponents.COPPERED, false),
			componentGetter.getOrDefault(ColorfulPotsDataComponents.EMERALDED, false),
			componentGetter.getOrDefault(ColorfulPotsDataComponents.AMETHYSTED, false),
			componentGetter.getOrDefault(ColorfulPotsDataComponents.RESINED, false),
			componentGetter.getOrDefault(ColorfulPotsDataComponents.REDSTONED, false)
		);
		this.colorfulPots$setExclusiveCoating(coating);

		DataComponentType<PotDecorations> coatingDecorationsComponent = colorfulPots$getCoatingDecorationsComponent(coating);
		PotDecorations coatingDecorations = coatingDecorationsComponent == null ? null : componentGetter.get(coatingDecorationsComponent);
		if (coatingDecorations != null
			&& PotDecorations.EMPTY.equals(this.decorations)
			&& !PotDecorations.EMPTY.equals(coatingDecorations)) {
			this.decorations = coatingDecorations;
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
		if (diamonded) {
			this.colorfulPots$setExclusiveCoating(COLORFUL_POTS_COATING_DIAMOND);
		} else {
			this.colorfulPots$diamonded = false;
		}
	}

	@Override
	public boolean colorfulPots$isGolded() {
		return this.colorfulPots$golded;
	}

	@Override
	public void colorfulPots$setGolded(boolean golded) {
		if (golded) {
			this.colorfulPots$setExclusiveCoating(COLORFUL_POTS_COATING_GOLD);
		} else {
			this.colorfulPots$golded = false;
		}
	}

	@Override
	public boolean colorfulPots$isCoppered() {
		return this.colorfulPots$coppered;
	}

	@Override
	public void colorfulPots$setCoppered(boolean coppered) {
		if (coppered) {
			this.colorfulPots$setExclusiveCoating(COLORFUL_POTS_COATING_COPPER);
		} else {
			this.colorfulPots$coppered = false;
		}
	}

	@Override
	public boolean colorfulPots$isEmeralded() {
		return this.colorfulPots$emeralded;
	}

	@Override
	public void colorfulPots$setEmeralded(boolean emeralded) {
		if (emeralded) {
			this.colorfulPots$setExclusiveCoating(COLORFUL_POTS_COATING_EMERALD);
		} else {
			this.colorfulPots$emeralded = false;
		}
	}

	@Override
	public boolean colorfulPots$isAmethysted() {
		return this.colorfulPots$amethysted;
	}

	@Override
	public void colorfulPots$setAmethysted(boolean amethysted) {
		if (amethysted) {
			this.colorfulPots$setExclusiveCoating(COLORFUL_POTS_COATING_AMETHYST);
		} else {
			this.colorfulPots$amethysted = false;
		}
	}

	@Override
	public boolean colorfulPots$isResined() {
		return this.colorfulPots$resined;
	}

	@Override
	public void colorfulPots$setResined(boolean resined) {
		if (resined) {
			this.colorfulPots$setExclusiveCoating(COLORFUL_POTS_COATING_RESIN);
		} else {
			this.colorfulPots$resined = false;
		}
	}

	@Override
	public boolean colorfulPots$isRedstoned() {
		return this.colorfulPots$redstoned;
	}

	@Override
	public void colorfulPots$setRedstoned(boolean redstoned) {
		if (redstoned) {
			this.colorfulPots$setExclusiveCoating(COLORFUL_POTS_COATING_REDSTONE);
		} else {
			this.colorfulPots$redstoned = false;
		}
	}
}
