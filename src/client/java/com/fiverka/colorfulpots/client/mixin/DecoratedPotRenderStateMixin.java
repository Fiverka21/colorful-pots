package com.fiverka.colorfulpots.client.mixin;

import com.fiverka.colorfulpots.access.DiamondPotAccess;
import net.minecraft.client.renderer.blockentity.state.DecoratedPotRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(DecoratedPotRenderState.class)
public abstract class DecoratedPotRenderStateMixin implements DiamondPotAccess {
	@Unique
	private boolean colorfulPots$diamonded;

	@Override
	public boolean colorfulPots$isDiamonded() {
		return this.colorfulPots$diamonded;
	}

	@Override
	public void colorfulPots$setDiamonded(boolean diamonded) {
		this.colorfulPots$diamonded = diamonded;
	}
}
