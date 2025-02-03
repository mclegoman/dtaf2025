/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.mixin.client.block;

import com.mclegoman.dtaf2025.common.item.ItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.world.GameMode;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientWorld.class)
public abstract class ClientWorldMixin {
	@Shadow @Final private MinecraftClient client;

	@Inject(method = "getBlockParticle", at = @At("HEAD"), cancellable = true)
	private void dtaf2025$getBlockParticle(CallbackInfoReturnable<Block> cir) {
		if (this.client != null && this.client.interactionManager != null &&  this.client.player != null) {
			if (this.client.interactionManager.getCurrentGameMode() == GameMode.CREATIVE) {
				Item item = this.client.player.getMainHandStack().getItem();
				if (item.getDefaultStack().isOf(ItemRegistry.spaceAir) && item instanceof BlockItem blockItem) {
					cir.setReturnValue(blockItem.getBlock());
				}
			}
		}
	}
}