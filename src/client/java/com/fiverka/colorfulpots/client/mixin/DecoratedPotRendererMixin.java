package com.fiverka.colorfulpots.client.mixin;

import com.fiverka.colorfulpots.ColorfulPotsMod;
import com.fiverka.colorfulpots.access.DiamondPotAccess;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
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
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.entity.DecoratedPotBlockEntity;
import net.minecraft.world.level.block.entity.PotDecorations;
import net.minecraft.world.level.block.entity.DecoratedPotPatterns;
import net.minecraft.world.item.Item;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DecoratedPotRenderer.class)
public abstract class DecoratedPotRendererMixin {
	@Unique
	private static final Material COLORFUL_POTS_DIAMOND_SIDE_MATERIAL = new Material(
		Sheets.DECORATED_POT_SHEET,
		Identifier.fromNamespaceAndPath(ColorfulPotsMod.MOD_ID, "entity/decorated_pot/diamond_base")
	);

	@Shadow
	@Final
	private MaterialSet materials;

	@Shadow
	@Final
	private ModelPart neck;

	@Shadow
	@Final
	private ModelPart frontSide;

	@Shadow
	@Final
	private ModelPart backSide;

	@Shadow
	@Final
	private ModelPart leftSide;

	@Shadow
	@Final
	private ModelPart rightSide;

	@Shadow
	@Final
	private ModelPart top;

	@Shadow
	@Final
	private ModelPart bottom;

	@Unique
	private boolean colorfulPots$renderingPlacedBlock;

	@Unique
	private boolean colorfulPots$renderDiamondSidesFromBlockState;

	@Unique
	private PotDecorations colorfulPots$lastExtractedDecorations = PotDecorations.EMPTY;

	@Unique
	private static long colorfulPots$lastMissingDecorationsLogMs;

	@Unique
	private static long colorfulPots$lastOverlayMaterialLogMs;

	@Inject(
		method = "extractRenderState(Lnet/minecraft/world/level/block/entity/DecoratedPotBlockEntity;Lnet/minecraft/client/renderer/blockentity/state/DecoratedPotRenderState;FLnet/minecraft/world/phys/Vec3;Lnet/minecraft/client/renderer/feature/ModelFeatureRenderer$CrumblingOverlay;)V",
		at = @At("TAIL")
	)
	private void colorfulPots$copyDiamondProperty(
		DecoratedPotBlockEntity blockEntity,
		DecoratedPotRenderState renderState,
		float tickDelta,
		Vec3 cameraPos,
		ModelFeatureRenderer.CrumblingOverlay crumblingOverlay,
		CallbackInfo ci
	) {
		boolean diamonded = ((DiamondPotAccess) blockEntity).colorfulPots$isDiamonded();
		((DiamondPotAccess) renderState).colorfulPots$setDiamonded(diamonded);
		this.colorfulPots$lastExtractedDecorations = blockEntity.getDecorations();
	}

	@Inject(
		method = "submit(Lnet/minecraft/client/renderer/blockentity/state/DecoratedPotRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;Lnet/minecraft/client/renderer/state/CameraRenderState;)V",
		at = @At("HEAD")
	)
	private void colorfulPots$beginBlockRender(
		DecoratedPotRenderState renderState,
		PoseStack poseStack,
		SubmitNodeCollector collector,
		CameraRenderState cameraState,
		CallbackInfo ci
	) {
		this.colorfulPots$renderingPlacedBlock = true;
		this.colorfulPots$renderDiamondSidesFromBlockState = ((DiamondPotAccess) renderState).colorfulPots$isDiamonded();
	}

	@Inject(
		method = "submit(Lnet/minecraft/client/renderer/blockentity/state/DecoratedPotRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;Lnet/minecraft/client/renderer/state/CameraRenderState;)V",
		at = @At("TAIL")
	)
	private void colorfulPots$endBlockRender(
		DecoratedPotRenderState renderState,
		PoseStack poseStack,
		SubmitNodeCollector collector,
		CameraRenderState cameraState,
		CallbackInfo ci
	) {
		this.colorfulPots$renderingPlacedBlock = false;
		this.colorfulPots$renderDiamondSidesFromBlockState = false;
	}

	@Inject(
		method = "submit(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;IILnet/minecraft/world/level/block/entity/PotDecorations;I)V",
		at = @At("HEAD"),
		cancellable = true
	)
	private void colorfulPots$submitDiamondedPlacedPot(
		PoseStack poseStack,
		SubmitNodeCollector collector,
		int light,
		int overlay,
		PotDecorations decorations,
		int modelFeature,
		CallbackInfo ci
	) {
		if (!this.colorfulPots$renderingPlacedBlock) {
			return;
		}

		if (!this.colorfulPots$renderDiamondSidesFromBlockState) {
			return;
		}

		PotDecorations effectiveDecorations = decorations;
		if (PotDecorations.EMPTY.equals(effectiveDecorations)
			&& !PotDecorations.EMPTY.equals(this.colorfulPots$lastExtractedDecorations)) {
			effectiveDecorations = this.colorfulPots$lastExtractedDecorations;
		}
		if (PotDecorations.EMPTY.equals(effectiveDecorations)) {
			long now = System.currentTimeMillis();
			if (now - colorfulPots$lastMissingDecorationsLogMs > 2000L) {
				colorfulPots$lastMissingDecorationsLogMs = now;
				ColorfulPotsMod.LOGGER.warn(
					"Diamonded pot render has empty decorations (submitArg={}, extracted={})",
					decorations,
					this.colorfulPots$lastExtractedDecorations
				);
			}
		}

		this.colorfulPots$submitBaseParts(poseStack, collector, light, overlay, modelFeature);

		this.colorfulPots$submitPart(this.frontSide, poseStack, collector, light, overlay, COLORFUL_POTS_DIAMOND_SIDE_MATERIAL, modelFeature);
		this.colorfulPots$submitPart(this.backSide, poseStack, collector, light, overlay, COLORFUL_POTS_DIAMOND_SIDE_MATERIAL, modelFeature);
		this.colorfulPots$submitPart(this.leftSide, poseStack, collector, light, overlay, COLORFUL_POTS_DIAMOND_SIDE_MATERIAL, modelFeature);
		this.colorfulPots$submitPart(this.rightSide, poseStack, collector, light, overlay, COLORFUL_POTS_DIAMOND_SIDE_MATERIAL, modelFeature);

		Material frontMaterial = this.colorfulPots$getModSideMaterial(effectiveDecorations.front());
		Material backMaterial = this.colorfulPots$getModSideMaterial(effectiveDecorations.back());
		Material leftMaterial = this.colorfulPots$getModSideMaterial(effectiveDecorations.left());
		Material rightMaterial = this.colorfulPots$getModSideMaterial(effectiveDecorations.right());

		this.colorfulPots$debugOverlayMaterials(frontMaterial, backMaterial, leftMaterial, rightMaterial);

		poseStack.pushPose();
		poseStack.translate(0.5D, 0.5D, 0.5D);
		poseStack.scale(1.001F, 1.001F, 1.001F);
		poseStack.translate(-0.5D, -0.5D, -0.5D);

		this.colorfulPots$submitPartWithCutout(this.frontSide, poseStack, collector, light, overlay, frontMaterial, modelFeature);
		this.colorfulPots$submitPartWithCutout(this.backSide, poseStack, collector, light, overlay, backMaterial, modelFeature);
		this.colorfulPots$submitPartWithCutout(this.leftSide, poseStack, collector, light, overlay, leftMaterial, modelFeature);
		this.colorfulPots$submitPartWithCutout(this.rightSide, poseStack, collector, light, overlay, rightMaterial, modelFeature);

		poseStack.popPose();

		ci.cancel();
	}

	@Unique
	private void colorfulPots$submitBaseParts(
		PoseStack poseStack,
		SubmitNodeCollector collector,
		int light,
		int overlay,
		int modelFeature
	) {
		this.colorfulPots$submitPart(this.neck, poseStack, collector, light, overlay, Sheets.DECORATED_POT_BASE, modelFeature);
		this.colorfulPots$submitPart(this.top, poseStack, collector, light, overlay, Sheets.DECORATED_POT_BASE, modelFeature);
		this.colorfulPots$submitPart(this.bottom, poseStack, collector, light, overlay, Sheets.DECORATED_POT_BASE, modelFeature);
	}

	@Unique
	private Material colorfulPots$getModSideMaterial(java.util.Optional<Item> sideItem) {
		Material vanillaMaterial = Sheets.DECORATED_POT_SIDE;
		if (sideItem.isPresent()) {
			Material patternMaterial = Sheets.getDecoratedPotMaterial(DecoratedPotPatterns.getPatternFromItem(sideItem.get()));
			if (patternMaterial != null) {
				vanillaMaterial = patternMaterial;
			}
		}

		Identifier texture = vanillaMaterial.texture();
		Identifier modTexture = Identifier.fromNamespaceAndPath(ColorfulPotsMod.MOD_ID, texture.getPath());
		return new Material(Sheets.DECORATED_POT_SHEET, modTexture);
	}

	@Unique
	private void colorfulPots$submitPart(
		ModelPart part,
		PoseStack poseStack,
		SubmitNodeCollector collector,
		RenderType renderType,
		int light,
		int overlay,
		TextureAtlasSprite sprite,
		int modelFeature
	) {
		collector.submitModelPart(part, poseStack, renderType, light, overlay, sprite, false, false, -1, null, modelFeature);
	}

	@Unique
	private void colorfulPots$submitPart(
		ModelPart part,
		PoseStack poseStack,
		SubmitNodeCollector collector,
		int light,
		int overlay,
		Material material,
		int modelFeature
	) {
		RenderType renderType = material.renderType(RenderTypes::entitySolid);
		TextureAtlasSprite sprite = this.materials.get(material);
		this.colorfulPots$submitPart(part, poseStack, collector, renderType, light, overlay, sprite, modelFeature);
	}

	@Unique
	private void colorfulPots$submitPartWithCutout(
		ModelPart part,
		PoseStack poseStack,
		SubmitNodeCollector collector,
		int light,
		int overlay,
		Material material,
		int modelFeature
	) {
		RenderType renderType = material.renderType(RenderTypes::entityCutoutNoCull);
		TextureAtlasSprite sprite = this.materials.get(material);
		this.colorfulPots$submitPart(part, poseStack, collector, renderType, light, overlay, sprite, modelFeature);
	}

	@Unique
	private void colorfulPots$debugOverlayMaterials(
		Material frontMaterial,
		Material backMaterial,
		Material leftMaterial,
		Material rightMaterial
	) {
		long now = System.currentTimeMillis();
		if (now - colorfulPots$lastOverlayMaterialLogMs <= 2000L) {
			return;
		}
		colorfulPots$lastOverlayMaterialLogMs = now;

		ResourceManager resourceManager = Minecraft.getInstance().getResourceManager();
		ColorfulPotsMod.LOGGER.warn(
			"Diamonded pot overlay materials front={}({}) back={}({}) left={}({}) right={}({})",
			frontMaterial.texture(),
			resourceManager.getResource(this.colorfulPots$asTexturePng(frontMaterial.texture())).isPresent(),
			backMaterial.texture(),
			resourceManager.getResource(this.colorfulPots$asTexturePng(backMaterial.texture())).isPresent(),
			leftMaterial.texture(),
			resourceManager.getResource(this.colorfulPots$asTexturePng(leftMaterial.texture())).isPresent(),
			rightMaterial.texture(),
			resourceManager.getResource(this.colorfulPots$asTexturePng(rightMaterial.texture())).isPresent()
		);
	}

	@Unique
	private Identifier colorfulPots$asTexturePng(Identifier textureId) {
		return textureId.withPrefix("textures/").withSuffix(".png");
	}
}
