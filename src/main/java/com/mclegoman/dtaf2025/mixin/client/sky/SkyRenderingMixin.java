/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.mixin.client.sky;

import com.mclegoman.dtaf2025.client.data.ClientData;
import com.mclegoman.dtaf2025.client.sky.Sky;
import com.mclegoman.dtaf2025.client.sky.SkyDataloader;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SkyRendering.class)
public abstract class SkyRenderingMixin {
	@Inject(method = "renderCelestialBodies", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/VertexConsumerProvider$Immediate;draw()V"))
	private void dtaf2025$renderObjectsNormal(MatrixStack matrices, VertexConsumerProvider.Immediate vertexConsumers, float rot, int phase, float alpha, float starBrightness, Fog fog, CallbackInfo ci) {
		SkyDataloader.registry.forEach((identifier, skyObj) -> Sky.renderObject(vertexConsumers, skyObj.getFirst(), skyObj.getSecond(), ClientData.client.getRenderTickCounter().getTickDelta(false), ClientData.client.world, alpha));
	}
}