/*
    mclmaf2025
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/mclmaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.mclmaf2025.mixin.client.Sky;

import com.mclegoman.mclmaf2025.client.space.Sky;
import com.mclegoman.mclmaf2025.client.space.SkyDataloader;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public abstract class WorldRendererMixin {
	@Shadow @Nullable private ClientWorld world;
	@Inject(method = "renderSky(Lorg/joml/Matrix4f;Lorg/joml/Matrix4f;FLnet/minecraft/client/render/Camera;ZLjava/lang/Runnable;)V", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;setShaderColor(FFFF)V", ordinal = 4))
	private void mclmaf2025$renderObjects(Matrix4f modelViewMatrix, Matrix4f projectionMatrix, float tickDelta, Camera preStep, boolean skipRendering, Runnable preRender, CallbackInfo ci) {
		if (world != null) {
			MatrixStack matrices = new MatrixStack();
			matrices.multiplyPositionMatrix(modelViewMatrix);
			SkyDataloader.registry.forEach((identifier, skyObj) -> Sky.renderObject(matrices, identifier, skyObj, tickDelta, world));
		}
	}
}