package com.fiverka.colorfulpots.client.mixin;

import com.fiverka.colorfulpots.ColorfulPotsMod;
import com.fiverka.colorfulpots.access.DecoratedPotRenderStateAccess;
import com.fiverka.colorfulpots.access.DiamondPotAccess;
import com.mojang.blaze3d.vertex.PoseStack;
import java.util.Optional;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.DecoratedPotRenderer;
import net.minecraft.client.renderer.blockentity.state.DecoratedPotRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.MaterialSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.DecoratedPotBlockEntity;
import net.minecraft.world.level.block.entity.DecoratedPotPattern;
import net.minecraft.world.level.block.entity.DecoratedPotPatterns;
import net.minecraft.world.level.block.entity.PotDecorations;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DecoratedPotRenderer.class)
public abstract class DecoratedPotRendererMixin {
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
	private static final Material COLORFUL_POTS_DIAMOND_BASE = Sheets.DECORATED_POT_MAPPER.apply(
		Identifier.fromNamespaceAndPath(ColorfulPotsMod.MOD_ID, "diamond_base")
	);
	@Unique
	private static final Material COLORFUL_POTS_GOLD_BASE = Sheets.DECORATED_POT_MAPPER.apply(
		Identifier.fromNamespaceAndPath(ColorfulPotsMod.MOD_ID, "gold_base")
	);
	@Unique
	private static final Material COLORFUL_POTS_COPPER_BASE = Sheets.DECORATED_POT_MAPPER.apply(
		Identifier.fromNamespaceAndPath(ColorfulPotsMod.MOD_ID, "copper_base")
	);
	@Unique
	private static final Material COLORFUL_POTS_EMERALD_BASE = Sheets.DECORATED_POT_MAPPER.apply(
		Identifier.fromNamespaceAndPath(ColorfulPotsMod.MOD_ID, "emerald_base")
	);
	@Unique
	private static final Material COLORFUL_POTS_AMETHYST_BASE = Sheets.DECORATED_POT_MAPPER.apply(
		Identifier.fromNamespaceAndPath(ColorfulPotsMod.MOD_ID, "amethyst_base")
	);
	@Unique
	private static final Material COLORFUL_POTS_RESIN_BASE = Sheets.DECORATED_POT_MAPPER.apply(
		Identifier.fromNamespaceAndPath(ColorfulPotsMod.MOD_ID, "resin_base")
	);
	@Unique
	private static final Material COLORFUL_POTS_REDSTONE_BASE = Sheets.DECORATED_POT_MAPPER.apply(
		Identifier.fromNamespaceAndPath(ColorfulPotsMod.MOD_ID, "redstone_base")
	);
	@Unique
	private static final Material COLORFUL_POTS_IRON_BASE = Sheets.DECORATED_POT_MAPPER.apply(
		Identifier.fromNamespaceAndPath(ColorfulPotsMod.MOD_ID, "iron_base")
	);
	@Unique
	private static final Material COLORFUL_POTS_QUARTZ_BASE = Sheets.DECORATED_POT_MAPPER.apply(
		Identifier.fromNamespaceAndPath(ColorfulPotsMod.MOD_ID, "quartz_base")
	);
	@Unique
	private static final Material COLORFUL_POTS_LAPIS_BASE = Sheets.DECORATED_POT_MAPPER.apply(
		Identifier.fromNamespaceAndPath(ColorfulPotsMod.MOD_ID, "lapis_base")
	);
	@Unique
	private static final Material COLORFUL_POTS_NETHERITE_BASE = Sheets.DECORATED_POT_MAPPER.apply(
		Identifier.fromNamespaceAndPath(ColorfulPotsMod.MOD_ID, "netherite_base")
	);
	@Unique
	private static final Material COLORFUL_POTS_BASE = Sheets.DECORATED_POT_MAPPER.apply(
		Identifier.fromNamespaceAndPath(ColorfulPotsMod.MOD_ID, "decorated_pot_base")
	);
	@Unique
	private static final Material COLORFUL_POTS_SIDE = Sheets.DECORATED_POT_MAPPER.apply(
		Identifier.fromNamespaceAndPath(ColorfulPotsMod.MOD_ID, "decorated_pot_side")
	);

	@Shadow
	private MaterialSet materials;
	@Shadow
	private ModelPart neck;
	@Shadow
	private ModelPart frontSide;
	@Shadow
	private ModelPart backSide;
	@Shadow
	private ModelPart leftSide;
	@Shadow
	private ModelPart rightSide;
	@Shadow
	private ModelPart top;
	@Shadow
	private ModelPart bottom;

	@Unique
	private int colorfulPots$currentCoating = COLORFUL_POTS_COATING_NONE;
	@Unique
	private boolean colorfulPots$renderingPlacedPot;

	@Inject(method = "extractRenderState", at = @At("TAIL"))
	private void colorfulPots$extractCoatingState(
		DecoratedPotBlockEntity blockEntity,
		DecoratedPotRenderState renderState,
		float partialTick,
		Vec3 cameraPos,
		ModelFeatureRenderer.@Nullable CrumblingOverlay overlay,
		CallbackInfo ci
	) {
		int coating = COLORFUL_POTS_COATING_NONE;
		if (blockEntity instanceof DiamondPotAccess access) {
			coating = this.colorfulPots$resolveCoating(
				access.colorfulPots$isDiamonded(),
				access.colorfulPots$isGolded(),
				access.colorfulPots$isCoppered(),
				access.colorfulPots$isEmeralded(),
				access.colorfulPots$isAmethysted(),
				access.colorfulPots$isResined(),
				access.colorfulPots$isRedstoned(),
				access.colorfulPots$isIroned(),
				access.colorfulPots$isQuartzed(),
				access.colorfulPots$isLapised(),
				access.colorfulPots$isNetherited()
			);
		}

		((DecoratedPotRenderStateAccess) renderState).colorfulPots$setCoating(coating);
	}

	@Inject(
		method = "submit(Lnet/minecraft/client/renderer/blockentity/state/DecoratedPotRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;Lnet/minecraft/client/renderer/state/CameraRenderState;)V",
		at = @At("HEAD")
	)
	private void colorfulPots$captureRenderCoating(
		DecoratedPotRenderState renderState,
		PoseStack poseStack,
		SubmitNodeCollector collector,
		CameraRenderState cameraState,
		CallbackInfo ci
	) {
		this.colorfulPots$currentCoating = ((DecoratedPotRenderStateAccess) renderState).colorfulPots$getCoating();
		this.colorfulPots$renderingPlacedPot = true;
	}

	@Inject(
		method = "submit(Lnet/minecraft/client/renderer/blockentity/state/DecoratedPotRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;Lnet/minecraft/client/renderer/state/CameraRenderState;)V",
		at = @At("TAIL")
	)
	private void colorfulPots$clearRenderCoating(
		DecoratedPotRenderState renderState,
		PoseStack poseStack,
		SubmitNodeCollector collector,
		CameraRenderState cameraState,
		CallbackInfo ci
	) {
		this.colorfulPots$currentCoating = COLORFUL_POTS_COATING_NONE;
		this.colorfulPots$renderingPlacedPot = false;
	}

	@Inject(
		method = "submit(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;IILnet/minecraft/world/level/block/entity/PotDecorations;I)V",
		at = @At("HEAD"),
		cancellable = true
	)
	private void colorfulPots$submitWithCoatingTexture(
		PoseStack poseStack,
		SubmitNodeCollector collector,
		int light,
		int overlay,
		PotDecorations decorations,
		int color,
		CallbackInfo ci
	) {
		// Only override placed block entity rendering. Keep item/special model rendering vanilla.
		if (!this.colorfulPots$renderingPlacedPot) {
			return;
		}
		if (this.colorfulPots$currentCoating == COLORFUL_POTS_COATING_NONE) {
			// Uncoated pots should render exactly like vanilla.
			return;
		}

		// Neck/top/bottom use the 32x32 base atlas texture.
		RenderType potBaseRenderType = COLORFUL_POTS_BASE.renderType(RenderTypes::entitySolid);
		TextureAtlasSprite potBaseSprite = this.materials.get(COLORFUL_POTS_BASE);
		collector.submitModelPart(this.neck, poseStack, potBaseRenderType, light, overlay, potBaseSprite, false, false, -1, null, color);
		collector.submitModelPart(this.top, poseStack, potBaseRenderType, light, overlay, potBaseSprite, false, false, -1, null, color);
		collector.submitModelPart(this.bottom, poseStack, potBaseRenderType, light, overlay, potBaseSprite, false, false, -1, null, color);

		// Side pass 1: coating base (or default side texture).
		Material sideBaseMaterial = this.colorfulPots$getSideBaseMaterial(this.colorfulPots$currentCoating);
		RenderType sideBaseRenderType = sideBaseMaterial.renderType(RenderTypes::entitySolid);
		TextureAtlasSprite sideBaseSprite = this.materials.get(sideBaseMaterial);
		collector.submitModelPart(this.frontSide, poseStack, sideBaseRenderType, light, overlay, sideBaseSprite, false, false, -1, null, color);
		collector.submitModelPart(this.backSide, poseStack, sideBaseRenderType, light, overlay, sideBaseSprite, false, false, -1, null, color);
		collector.submitModelPart(this.leftSide, poseStack, sideBaseRenderType, light, overlay, sideBaseSprite, false, false, -1, null, color);
		collector.submitModelPart(this.rightSide, poseStack, sideBaseRenderType, light, overlay, sideBaseSprite, false, false, -1, null, color);

		// Side pass 2: decoration pattern overlay (transparent where empty).
		Material frontMaterial = this.colorfulPots$getSideOverlayMaterial(decorations.front());
		if (frontMaterial != null) {
			collector.submitModelPart(
				this.frontSide,
				poseStack,
				frontMaterial.renderType(RenderTypes::entityCutoutNoCullZOffset),
				light,
				overlay,
				this.materials.get(frontMaterial),
				false,
				false,
				-1,
				null,
				color
			);
		}

		Material backMaterial = this.colorfulPots$getSideOverlayMaterial(decorations.back());
		if (backMaterial != null) {
			collector.submitModelPart(
				this.backSide,
				poseStack,
				backMaterial.renderType(RenderTypes::entityCutoutNoCullZOffset),
				light,
				overlay,
				this.materials.get(backMaterial),
				false,
				false,
				-1,
				null,
				color
			);
		}

		Material leftMaterial = this.colorfulPots$getSideOverlayMaterial(decorations.left());
		if (leftMaterial != null) {
			collector.submitModelPart(
				this.leftSide,
				poseStack,
				leftMaterial.renderType(RenderTypes::entityCutoutNoCullZOffset),
				light,
				overlay,
				this.materials.get(leftMaterial),
				false,
				false,
				-1,
				null,
				color
			);
		}

		Material rightMaterial = this.colorfulPots$getSideOverlayMaterial(decorations.right());
		if (rightMaterial != null) {
			collector.submitModelPart(
				this.rightSide,
				poseStack,
				rightMaterial.renderType(RenderTypes::entityCutoutNoCullZOffset),
				light,
				overlay,
				this.materials.get(rightMaterial),
				false,
				false,
				-1,
				null,
				color
			);
		}

		ci.cancel();
	}

	@Unique
	private Material colorfulPots$getSideBaseMaterial(int coating) {
		return switch (coating) {
			case COLORFUL_POTS_COATING_DIAMOND -> COLORFUL_POTS_DIAMOND_BASE;
			case COLORFUL_POTS_COATING_GOLD -> COLORFUL_POTS_GOLD_BASE;
			case COLORFUL_POTS_COATING_COPPER -> COLORFUL_POTS_COPPER_BASE;
			case COLORFUL_POTS_COATING_EMERALD -> COLORFUL_POTS_EMERALD_BASE;
			case COLORFUL_POTS_COATING_AMETHYST -> COLORFUL_POTS_AMETHYST_BASE;
			case COLORFUL_POTS_COATING_RESIN -> COLORFUL_POTS_RESIN_BASE;
			case COLORFUL_POTS_COATING_REDSTONE -> COLORFUL_POTS_REDSTONE_BASE;
			case COLORFUL_POTS_COATING_IRON -> COLORFUL_POTS_IRON_BASE;
			case COLORFUL_POTS_COATING_QUARTZ -> COLORFUL_POTS_QUARTZ_BASE;
			case COLORFUL_POTS_COATING_LAPIS -> COLORFUL_POTS_LAPIS_BASE;
			case COLORFUL_POTS_COATING_NETHERITE -> COLORFUL_POTS_NETHERITE_BASE;
			default -> COLORFUL_POTS_SIDE;
		};
	}

	@Unique
	private @Nullable Material colorfulPots$getSideOverlayMaterial(Optional<Item> item) {
		if (item.isEmpty()) {
			return null;
		}

		var patternKey = DecoratedPotPatterns.getPatternFromItem(item.get());
		if (patternKey == null) {
			return null;
		}

		DecoratedPotPattern pattern = BuiltInRegistries.DECORATED_POT_PATTERN.getValue(patternKey);
		if (pattern == null) {
			return null;
		}

		return Sheets.DECORATED_POT_MAPPER.apply(
			Identifier.fromNamespaceAndPath(ColorfulPotsMod.MOD_ID, pattern.assetId().getPath())
		);
	}

	@Unique
	private int colorfulPots$resolveCoating(
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
}
