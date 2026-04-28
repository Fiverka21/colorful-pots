package com.fiverka.colorfulpots.client.mixin;

import com.fiverka.colorfulpots.access.DecoratedPotRenderStateAccess;
import net.minecraft.client.renderer.blockentity.state.DecoratedPotRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(DecoratedPotRenderState.class)
public class DecoratedPotRenderStateMixin implements DecoratedPotRenderStateAccess {
	@Unique
	private int colorfulPots$coating = -1;

	@Override
	public int colorfulPots$getCoating() {
		return this.colorfulPots$coating;
	}

	@Override
	public void colorfulPots$setCoating(int coating) {
		this.colorfulPots$coating = coating;
	}
}
