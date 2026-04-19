package com.fiverka.colorfulpots.client.mixin;

import com.fiverka.colorfulpots.access.DiamondPotAccess;
import net.minecraft.client.renderer.blockentity.state.DecoratedPotRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(DecoratedPotRenderState.class)
public abstract class DecoratedPotRenderStateMixin implements DiamondPotAccess {
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
	private void colorfulPots$clearAllCoatings() {
		this.colorfulPots$diamonded = false;
		this.colorfulPots$golded = false;
		this.colorfulPots$coppered = false;
		this.colorfulPots$emeralded = false;
		this.colorfulPots$amethysted = false;
		this.colorfulPots$resined = false;
		this.colorfulPots$redstoned = false;
	}

	@Override
	public boolean colorfulPots$isDiamonded() {
		return this.colorfulPots$diamonded;
	}

	@Override
	public void colorfulPots$setDiamonded(boolean diamonded) {
		if (diamonded) {
			this.colorfulPots$clearAllCoatings();
		}
		this.colorfulPots$diamonded = diamonded;
	}

	@Override
	public boolean colorfulPots$isGolded() {
		return this.colorfulPots$golded;
	}

	@Override
	public void colorfulPots$setGolded(boolean golded) {
		if (golded) {
			this.colorfulPots$clearAllCoatings();
		}
		this.colorfulPots$golded = golded;
	}

	@Override
	public boolean colorfulPots$isCoppered() {
		return this.colorfulPots$coppered;
	}

	@Override
	public void colorfulPots$setCoppered(boolean coppered) {
		if (coppered) {
			this.colorfulPots$clearAllCoatings();
		}
		this.colorfulPots$coppered = coppered;
	}

	@Override
	public boolean colorfulPots$isEmeralded() {
		return this.colorfulPots$emeralded;
	}

	@Override
	public void colorfulPots$setEmeralded(boolean emeralded) {
		if (emeralded) {
			this.colorfulPots$clearAllCoatings();
		}
		this.colorfulPots$emeralded = emeralded;
	}

	@Override
	public boolean colorfulPots$isAmethysted() {
		return this.colorfulPots$amethysted;
	}

	@Override
	public void colorfulPots$setAmethysted(boolean amethysted) {
		if (amethysted) {
			this.colorfulPots$clearAllCoatings();
		}
		this.colorfulPots$amethysted = amethysted;
	}

	@Override
	public boolean colorfulPots$isResined() {
		return this.colorfulPots$resined;
	}

	@Override
	public void colorfulPots$setResined(boolean resined) {
		if (resined) {
			this.colorfulPots$clearAllCoatings();
		}
		this.colorfulPots$resined = resined;
	}

	@Override
	public boolean colorfulPots$isRedstoned() {
		return this.colorfulPots$redstoned;
	}

	@Override
	public void colorfulPots$setRedstoned(boolean redstoned) {
		if (redstoned) {
			this.colorfulPots$clearAllCoatings();
		}
		this.colorfulPots$redstoned = redstoned;
	}
}
