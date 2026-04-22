package com.fiverka.colorfulpots.client.mixin;

import com.fiverka.colorfulpots.ColorfulPotsMod;
import com.fiverka.colorfulpots.access.DiamondPotAccess;
import com.fiverka.colorfulpots.component.ColorfulPotsDataComponents;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import java.util.Optional;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.DecoratedPotRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.DecoratedPotBlockEntity;
import net.minecraft.world.level.block.entity.PotDecorations;
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
	private static final Material COLORFUL_POTS_DIAMOND_SIDE_MATERIAL = new Material(
		Sheets.DECORATED_POT_SHEET,
		ResourceLocation.fromNamespaceAndPath(ColorfulPotsMod.MOD_ID, "entity/decorated_pot/diamond_base")
	);

	@Unique
	private static final Material COLORFUL_POTS_GOLD_SIDE_MATERIAL = new Material(
		Sheets.DECORATED_POT_SHEET,
		ResourceLocation.fromNamespaceAndPath(ColorfulPotsMod.MOD_ID, "entity/decorated_pot/gold_base")
	);

	@Unique
	private static final Material COLORFUL_POTS_COPPER_SIDE_MATERIAL = new Material(
		Sheets.DECORATED_POT_SHEET,
		ResourceLocation.fromNamespaceAndPath(ColorfulPotsMod.MOD_ID, "entity/decorated_pot/copper_base")
	);

	@Unique
	private static final Material COLORFUL_POTS_EMERALD_SIDE_MATERIAL = new Material(
		Sheets.DECORATED_POT_SHEET,
		ResourceLocation.fromNamespaceAndPath(ColorfulPotsMod.MOD_ID, "entity/decorated_pot/emerald_base")
	);

	@Unique
	private static final Material COLORFUL_POTS_AMETHYST_SIDE_MATERIAL = new Material(
		Sheets.DECORATED_POT_SHEET,
		ResourceLocation.fromNamespaceAndPath(ColorfulPotsMod.MOD_ID, "entity/decorated_pot/amethyst_base")
	);

	@Unique
	private static final Material COLORFUL_POTS_RESIN_SIDE_MATERIAL = new Material(
		Sheets.DECORATED_POT_SHEET,
		ResourceLocation.fromNamespaceAndPath(ColorfulPotsMod.MOD_ID, "entity/decorated_pot/resin_base")
	);

	@Unique
	private static final Material COLORFUL_POTS_REDSTONE_SIDE_MATERIAL = new Material(
		Sheets.DECORATED_POT_SHEET,
		ResourceLocation.fromNamespaceAndPath(ColorfulPotsMod.MOD_ID, "entity/decorated_pot/redstone_base")
	);

	@Unique
	private static final Material COLORFUL_POTS_IRON_SIDE_MATERIAL = new Material(
		Sheets.DECORATED_POT_SHEET,
		ResourceLocation.fromNamespaceAndPath(ColorfulPotsMod.MOD_ID, "entity/decorated_pot/iron_base")
	);

	@Unique
	private static final Material COLORFUL_POTS_QUARTZ_SIDE_MATERIAL = new Material(
		Sheets.DECORATED_POT_SHEET,
		ResourceLocation.fromNamespaceAndPath(ColorfulPotsMod.MOD_ID, "entity/decorated_pot/quartz_base")
	);

	@Unique
	private static final Material COLORFUL_POTS_LAPIS_SIDE_MATERIAL = new Material(
		Sheets.DECORATED_POT_SHEET,
		ResourceLocation.fromNamespaceAndPath(ColorfulPotsMod.MOD_ID, "entity/decorated_pot/lapis_base")
	);

	@Unique
	private static final Material COLORFUL_POTS_NETHERITE_SIDE_MATERIAL = new Material(
		Sheets.DECORATED_POT_SHEET,
		ResourceLocation.fromNamespaceAndPath(ColorfulPotsMod.MOD_ID, "entity/decorated_pot/netherite_base")
	);

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

	@Shadow
	private static Material getSideMaterial(Optional<Item> sideItem) {
		throw new AssertionError();
	}

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
	private static int colorfulPots$resolveCoating(DiamondPotAccess access) {
		return colorfulPots$resolveCoating(
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

	@Unique
	private static int colorfulPots$resolveCoatingFromComponents(DecoratedPotBlockEntity blockEntity) {
		return colorfulPots$resolveCoating(
			blockEntity.components().getOrDefault(ColorfulPotsDataComponents.DIAMONDED.get(), false),
			blockEntity.components().getOrDefault(ColorfulPotsDataComponents.GOLDED.get(), false),
			blockEntity.components().getOrDefault(ColorfulPotsDataComponents.COPPERED.get(), false),
			blockEntity.components().getOrDefault(ColorfulPotsDataComponents.EMERALDED.get(), false),
			blockEntity.components().getOrDefault(ColorfulPotsDataComponents.AMETHYSTED.get(), false),
			blockEntity.components().getOrDefault(ColorfulPotsDataComponents.RESINED.get(), false),
			blockEntity.components().getOrDefault(ColorfulPotsDataComponents.REDSTONED.get(), false),
			blockEntity.components().getOrDefault(ColorfulPotsDataComponents.IRONED.get(), false),
			blockEntity.components().getOrDefault(ColorfulPotsDataComponents.QUARTZED.get(), false),
			blockEntity.components().getOrDefault(ColorfulPotsDataComponents.LAPISED.get(), false),
			blockEntity.components().getOrDefault(ColorfulPotsDataComponents.NETHERITED.get(), false)
		);
	}

	@Unique
	private static int colorfulPots$resolveCoating(DecoratedPotBlockEntity blockEntity) {
		int coating = colorfulPots$resolveCoating((DiamondPotAccess) blockEntity);
		if (coating != COLORFUL_POTS_COATING_NONE) {
			return coating;
		}
		return colorfulPots$resolveCoatingFromComponents(blockEntity);
	}

	@Unique
	private static Material colorfulPots$getBaseMaterialByCoating(int coating) {
		return switch (coating) {
			case COLORFUL_POTS_COATING_DIAMOND -> COLORFUL_POTS_DIAMOND_SIDE_MATERIAL;
			case COLORFUL_POTS_COATING_GOLD -> COLORFUL_POTS_GOLD_SIDE_MATERIAL;
			case COLORFUL_POTS_COATING_COPPER -> COLORFUL_POTS_COPPER_SIDE_MATERIAL;
			case COLORFUL_POTS_COATING_EMERALD -> COLORFUL_POTS_EMERALD_SIDE_MATERIAL;
			case COLORFUL_POTS_COATING_AMETHYST -> COLORFUL_POTS_AMETHYST_SIDE_MATERIAL;
			case COLORFUL_POTS_COATING_RESIN -> COLORFUL_POTS_RESIN_SIDE_MATERIAL;
			case COLORFUL_POTS_COATING_REDSTONE -> COLORFUL_POTS_REDSTONE_SIDE_MATERIAL;
			case COLORFUL_POTS_COATING_IRON -> COLORFUL_POTS_IRON_SIDE_MATERIAL;
			case COLORFUL_POTS_COATING_QUARTZ -> COLORFUL_POTS_QUARTZ_SIDE_MATERIAL;
			case COLORFUL_POTS_COATING_LAPIS -> COLORFUL_POTS_LAPIS_SIDE_MATERIAL;
			case COLORFUL_POTS_COATING_NETHERITE -> COLORFUL_POTS_NETHERITE_SIDE_MATERIAL;
			default -> null;
		};
	}

	@Unique
	private static void colorfulPots$applyWobble(DecoratedPotBlockEntity blockEntity, float tickDelta, PoseStack poseStack) {
		DecoratedPotBlockEntity.WobbleStyle wobbleStyle = blockEntity.lastWobbleStyle;
		if (wobbleStyle == null || blockEntity.getLevel() == null) {
			return;
		}

		float wobbleProgress = ((float) (blockEntity.getLevel().getGameTime() - blockEntity.wobbleStartedAtTick) + tickDelta)
			/ (float) wobbleStyle.duration;
		if (wobbleProgress < 0.0F || wobbleProgress > 1.0F) {
			return;
		}

		if (wobbleStyle == DecoratedPotBlockEntity.WobbleStyle.POSITIVE) {
			float cycle = wobbleProgress * ((float) Math.PI * 2.0F);
			float tilt = -1.5F * (Mth.cos(cycle) + 0.5F) * Mth.sin(cycle / 2.0F);
			poseStack.rotateAround(Axis.XP.rotation(tilt * 0.015625F), 0.5F, 0.0F, 0.5F);
			float roll = Mth.sin(cycle);
			poseStack.rotateAround(Axis.ZP.rotation(roll * 0.015625F), 0.5F, 0.0F, 0.5F);
			return;
		}

		float yaw = Mth.sin(-wobbleProgress * 3.0F * (float) Math.PI) * 0.125F;
		float decay = 1.0F - wobbleProgress;
		poseStack.rotateAround(Axis.YP.rotation(yaw * decay), 0.5F, 0.0F, 0.5F);
	}

	@Unique
	private static Material colorfulPots$getModSideMaterial(Optional<Item> sideItem) {
		Material vanillaMaterial = getSideMaterial(sideItem);
		ResourceLocation texture = vanillaMaterial.texture();
		ResourceLocation modTexture = ResourceLocation.fromNamespaceAndPath(ColorfulPotsMod.MOD_ID, texture.getPath());
		return new Material(Sheets.DECORATED_POT_SHEET, modTexture);
	}

	@Unique
	private static void colorfulPots$renderPartSolid(
		ModelPart part,
		PoseStack poseStack,
		MultiBufferSource bufferSource,
		int packedLight,
		int packedOverlay,
		Material material
	) {
		VertexConsumer vertexConsumer = material.buffer(bufferSource, RenderType::entitySolid);
		part.render(poseStack, vertexConsumer, packedLight, packedOverlay);
	}

	@Unique
	private static void colorfulPots$renderPartCutout(
		ModelPart part,
		PoseStack poseStack,
		MultiBufferSource bufferSource,
		int packedLight,
		int packedOverlay,
		Material material
	) {
		VertexConsumer vertexConsumer = material.buffer(bufferSource, RenderType::entityCutoutNoCullZOffset);
		part.render(poseStack, vertexConsumer, packedLight, packedOverlay);
	}

	@Inject(method = "render", at = @At("HEAD"), cancellable = true)
	private void colorfulPots$renderCoatedPot(
		DecoratedPotBlockEntity blockEntity,
		float tickDelta,
		PoseStack poseStack,
		MultiBufferSource bufferSource,
		int packedLight,
		int packedOverlay,
		CallbackInfo ci
	) {
		if (blockEntity.getLevel() == null) {
			return;
		}

		int coating = colorfulPots$resolveCoating(blockEntity);
		if (coating == COLORFUL_POTS_COATING_NONE) {
			return;
		}

		Material baseSideMaterial = colorfulPots$getBaseMaterialByCoating(coating);
		if (baseSideMaterial == null) {
			return;
		}

		poseStack.pushPose();
		Direction direction = blockEntity.getDirection();
		poseStack.translate(0.5D, 0.0D, 0.5D);
		poseStack.mulPose(Axis.YP.rotationDegrees(180.0F - direction.toYRot()));
		poseStack.translate(-0.5D, 0.0D, -0.5D);
		colorfulPots$applyWobble(blockEntity, tickDelta, poseStack);

		VertexConsumer baseConsumer = Sheets.DECORATED_POT_BASE.buffer(bufferSource, RenderType::entitySolid);
		this.neck.render(poseStack, baseConsumer, packedLight, packedOverlay);
		this.top.render(poseStack, baseConsumer, packedLight, packedOverlay);
		this.bottom.render(poseStack, baseConsumer, packedLight, packedOverlay);

		colorfulPots$renderPartSolid(this.frontSide, poseStack, bufferSource, packedLight, packedOverlay, baseSideMaterial);
		colorfulPots$renderPartSolid(this.backSide, poseStack, bufferSource, packedLight, packedOverlay, baseSideMaterial);
		colorfulPots$renderPartSolid(this.leftSide, poseStack, bufferSource, packedLight, packedOverlay, baseSideMaterial);
		colorfulPots$renderPartSolid(this.rightSide, poseStack, bufferSource, packedLight, packedOverlay, baseSideMaterial);

		PotDecorations decorations = blockEntity.getDecorations();
		colorfulPots$renderPartCutout(
			this.frontSide,
			poseStack,
			bufferSource,
			packedLight,
			packedOverlay,
			colorfulPots$getModSideMaterial(decorations.front())
		);
		colorfulPots$renderPartCutout(
			this.backSide,
			poseStack,
			bufferSource,
			packedLight,
			packedOverlay,
			colorfulPots$getModSideMaterial(decorations.back())
		);
		colorfulPots$renderPartCutout(
			this.leftSide,
			poseStack,
			bufferSource,
			packedLight,
			packedOverlay,
			colorfulPots$getModSideMaterial(decorations.left())
		);
		colorfulPots$renderPartCutout(
			this.rightSide,
			poseStack,
			bufferSource,
			packedLight,
			packedOverlay,
			colorfulPots$getModSideMaterial(decorations.right())
		);

		poseStack.popPose();
		ci.cancel();
	}
}
