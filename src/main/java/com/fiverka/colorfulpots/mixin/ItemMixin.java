package com.fiverka.colorfulpots.mixin;

import com.fiverka.colorfulpots.component.ColorfulPotsDataComponents;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Item.class)
public abstract class ItemMixin {
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

	@Inject(method = "appendHoverText", at = @At("TAIL"))
	private void colorfulPots$appendCurrentCoatingTooltip(
		ItemStack stack,
		Item.TooltipContext context,
		List<Component> tooltipComponents,
		TooltipFlag flag,
		CallbackInfo ci
	) {
		if (!stack.is(Items.DECORATED_POT)) {
			return;
		}

		int coating = colorfulPots$resolveCoatingFromComponents(stack);
		if (coating == COLORFUL_POTS_COATING_NONE) {
			coating = colorfulPots$resolveCoatingFromLegacyTag(stack);
		}
		if (coating == COLORFUL_POTS_COATING_NONE) {
			return;
		}

		String attributeName = colorfulPots$getCoatingName(coating);
		if (attributeName == null) {
			return;
		}

		tooltipComponents.add(Component.literal(attributeName).withStyle(ChatFormatting.GRAY));
	}

	@Unique
	private static int colorfulPots$resolveCoatingFromComponents(ItemStack stack) {
		return colorfulPots$resolveCoating(
			stack.getOrDefault(ColorfulPotsDataComponents.DIAMONDED, false),
			stack.getOrDefault(ColorfulPotsDataComponents.GOLDED, false),
			stack.getOrDefault(ColorfulPotsDataComponents.COPPERED, false),
			stack.getOrDefault(ColorfulPotsDataComponents.EMERALDED, false),
			stack.getOrDefault(ColorfulPotsDataComponents.AMETHYSTED, false),
			stack.getOrDefault(ColorfulPotsDataComponents.RESINED, false),
			stack.getOrDefault(ColorfulPotsDataComponents.REDSTONED, false),
			stack.getOrDefault(ColorfulPotsDataComponents.IRONED, false),
			stack.getOrDefault(ColorfulPotsDataComponents.QUARTZED, false),
			stack.getOrDefault(ColorfulPotsDataComponents.LAPISED, false),
			stack.getOrDefault(ColorfulPotsDataComponents.NETHERITED, false)
		);
	}

	@Unique
	private static int colorfulPots$resolveCoatingFromLegacyTag(ItemStack stack) {
		CustomData blockEntityData = stack.get(DataComponents.BLOCK_ENTITY_DATA);
		if (blockEntityData == null) {
			return COLORFUL_POTS_COATING_NONE;
		}

		CompoundTag tag = blockEntityData.copyTag();
		return colorfulPots$resolveCoating(
			tag.getBoolean("colorful_pots_diamonded"),
			tag.getBoolean("colorful_pots_golded"),
			tag.getBoolean("colorful_pots_coppered"),
			tag.getBoolean("colorful_pots_emeralded"),
			tag.getBoolean("colorful_pots_amethysted"),
			tag.getBoolean("colorful_pots_resined"),
			tag.getBoolean("colorful_pots_redstoned"),
			tag.getBoolean("colorful_pots_ironed"),
			tag.getBoolean("colorful_pots_quartzed"),
			tag.getBoolean("colorful_pots_lapised"),
			tag.getBoolean("colorful_pots_netherited")
		);
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
	private static String colorfulPots$getCoatingName(int coating) {
		return switch (coating) {
			case COLORFUL_POTS_COATING_DIAMOND -> "Diamond";
			case COLORFUL_POTS_COATING_GOLD -> "Gold";
			case COLORFUL_POTS_COATING_COPPER -> "Copper";
			case COLORFUL_POTS_COATING_EMERALD -> "Emerald";
			case COLORFUL_POTS_COATING_AMETHYST -> "Amethyst";
			case COLORFUL_POTS_COATING_RESIN -> "Resin";
			case COLORFUL_POTS_COATING_REDSTONE -> "Redstone";
			case COLORFUL_POTS_COATING_IRON -> "Iron";
			case COLORFUL_POTS_COATING_QUARTZ -> "Quartz";
			case COLORFUL_POTS_COATING_LAPIS -> "Lapis";
			case COLORFUL_POTS_COATING_NETHERITE -> "Netherite";
			default -> null;
		};
	}
}
