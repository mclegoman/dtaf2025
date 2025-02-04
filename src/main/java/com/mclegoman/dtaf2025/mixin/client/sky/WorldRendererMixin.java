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
import com.mclegoman.dtaf2025.client.world.WorldHelper;
import com.mclegoman.dtaf2025.client.world.dimension.DimensionRegistry;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.RotationAxis;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public abstract class WorldRendererMixin {
	@Shadow @Nullable private ClientWorld world;
	@Shadow @Final private DefaultFramebufferSet framebufferSet;
	@Shadow @Final private SkyRendering skyRendering;
	@Shadow @Final private BufferBuilderStorage bufferBuilders;
	@Shadow protected abstract boolean isSkyDark(float tickDelta);
	@Inject(method = "renderSky", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/DimensionEffects;getSkyType()Lnet/minecraft/client/render/DimensionEffects$SkyType;"))
	private void dtaf2025$renderCustomSkies(FrameGraphBuilder frameGraphBuilder, Camera camera, float tickDelta, Fog fog, CallbackInfo ci) {
		if (this.world != null) {
			DimensionEffects dimensionEffects = this.world.getDimensionEffects();
			DimensionEffects.SkyType skyType = dimensionEffects.getSkyType();
			if (skyType == DimensionEffects.SkyType.NONE) {
				RenderPass renderPass = frameGraphBuilder.createPass("sky");
				this.framebufferSet.mainFramebuffer = renderPass.transfer(this.framebufferSet.mainFramebuffer);
				renderPass.setRenderer(() -> {
					RenderSystem.setShaderFog(fog);
					MatrixStack matrixStack = new MatrixStack();
					float skyAngleRadians = this.world.getSkyAngleRadians(tickDelta);
					float skyAngle = this.world.getSkyAngle(tickDelta);
					float rainGradiant = 1.0F - this.world.getRainGradient(tickDelta);
					float starBrightness = this.world.getStarBrightness(tickDelta) * rainGradiant;
					int skyColor = dimensionEffects.getSkyColor(skyAngle);
					int moonPhase = this.world.getMoonPhase();
					int skyColorAtPos = this.world.getSkyColor(ClientData.client.gameRenderer.getCamera().getPos(), tickDelta);
					this.skyRendering.renderSky(ColorHelper.getRedFloat(skyColorAtPos), ColorHelper.getGreenFloat(skyColorAtPos), ColorHelper.getBlueFloat(skyColorAtPos));
					VertexConsumerProvider.Immediate immediate = this.bufferBuilders.getEntityVertexConsumers();
					if (dimensionEffects.isSunRisingOrSetting(skyAngle)) this.skyRendering.renderGlowingSky(matrixStack, immediate, skyAngleRadians, skyColor);
					matrixStack.push();
					if (WorldHelper.isInSpace(this.world)) {
						rainGradiant = 1.0F;
						matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-90.0F));
						matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(skyAngle * 360.0F));
						if (WorldHelper.isIn(this.world, DimensionRegistry.spaceStation.getId())) {
							WorldHelper.renderSun(rainGradiant, immediate, matrixStack, 1.1F);
							WorldHelper.renderMoon(moonPhase, rainGradiant, immediate, matrixStack, 1.04F);
						} else if (WorldHelper.isIn(this.world, DimensionRegistry.theMoon.getId())) {
							WorldHelper.renderSun(rainGradiant, immediate, matrixStack, 1.04F);
						}
					}
					float rainGradiantSky = rainGradiant;
					SkyDataloader.registry.forEach((identifier, skyObj) -> Sky.renderObject(immediate, skyObj.getFirst(), skyObj.getSecond(), ClientData.client.getRenderTickCounter().getTickDelta(false), ClientData.client.world, rainGradiantSky));
					immediate.draw();
					float spaceStarBrightness = 0.92F * rainGradiant;
					if (starBrightness > 0.0F || WorldHelper.isInSpace(this.world)) this.skyRendering.renderStars(fog, WorldHelper.isInSpace(this.world) ? spaceStarBrightness : starBrightness, matrixStack);
					matrixStack.pop();
					immediate.draw();
					if (!WorldHelper.isIn(this.world, DimensionRegistry.spaceStation.getId()) && this.isSkyDark(tickDelta)) this.skyRendering.renderSkyDark(matrixStack);
				});
			}
		}
	}
}