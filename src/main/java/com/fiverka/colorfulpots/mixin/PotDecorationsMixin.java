package com.fiverka.colorfulpots.mixin;

import com.fiverka.colorfulpots.component.ColorfulPotsDataComponents;
import java.util.function.Consumer;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.entity.PotDecorations;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PotDecorations.class)
public abstract class PotDecorationsMixin {
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

	@Inject(method = "addToTooltip", at = @At("TAIL"))
	private void colorfulPots$appendDiamondTooltipLine(
		Item.TooltipContext context,
		Consumer<Component> tooltipAdder,
		TooltipFlag tooltipFlag,
		DataComponentGetter components,
		CallbackInfo ci
	) {
		int coating = colorfulPots$resolveCoating(
			components.getOrDefault(ColorfulPotsDataComponents.DIAMONDED, false),
			components.getOrDefault(ColorfulPotsDataComponents.GOLDED, false),
			components.getOrDefault(ColorfulPotsDataComponents.COPPERED, false),
			components.getOrDefault(ColorfulPotsDataComponents.EMERALDED, false),
			components.getOrDefault(ColorfulPotsDataComponents.AMETHYSTED, false),
			components.getOrDefault(ColorfulPotsDataComponents.RESINED, false),
			components.getOrDefault(ColorfulPotsDataComponents.REDSTONED, false),
			components.getOrDefault(ColorfulPotsDataComponents.IRONED, false),
			components.getOrDefault(ColorfulPotsDataComponents.QUARTZED, false),
			components.getOrDefault(ColorfulPotsDataComponents.LAPISED, false),
			components.getOrDefault(ColorfulPotsDataComponents.NETHERITED, false)
		);
		if (coating == COLORFUL_POTS_COATING_NONE) {
			return;
		}

		String label = switch (coating) {
			case COLORFUL_POTS_COATING_DIAMOND -> "Diamond";
			case COLORFUL_POTS_COATING_GOLD -> "Gold";
			case COLORFUL_POTS_COATING_COPPER -> "Copper";
			case COLORFUL_POTS_COATING_EMERALD -> "Emerald";
			case COLORFUL_POTS_COATING_AMETHYST -> "Amethyst";
			case COLORFUL_POTS_COATING_RESIN -> "Resin";
			case COLORFUL_POTS_COATING_REDSTONE -> "Redstone";
			case COLORFUL_POTS_COATING_IRON -> "Iron";
			case COLORFUL_POTS_COATING_QUARTZ -> "Quartz";
			case COLORFUL_POTS_COATING_LAPIS -> "Lapis Lazuli";
			case COLORFUL_POTS_COATING_NETHERITE -> "Netherite";
			default -> null;
		};
		if (label != null) {
			tooltipAdder.accept(Component.literal(label));
		}
	}
}
