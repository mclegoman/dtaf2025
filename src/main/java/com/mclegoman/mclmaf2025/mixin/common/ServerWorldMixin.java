/*
    mclmaf2025
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/mclmaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.mclmaf2025.mixin.common;

import com.mclegoman.mclmaf2025.common.entity.data.Sanic;
import com.mclegoman.mclmaf2025.common.registry.EasterEggsRegistry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ServerWorld.class)
public abstract class ServerWorldMixin {
	@Shadow public abstract long getSeed();
	@Shadow public abstract List<ServerPlayerEntity> getPlayers();
	@Inject(method = "tick", at = @At("HEAD"))
	private void mclmaf2025$tick(CallbackInfo ci) {
		// We make the player have a "Sanic" attribute for multiplayer support.
		this.getPlayers().forEach((player -> ((Sanic)player).mclmaf2025$setSanic(EasterEggsRegistry.isSanicEasterEgg(this.getSeed()))));
	}
}
