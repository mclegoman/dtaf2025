package com.mclegoman.dtaf2025.client.entity.renderer;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.LoadedEntityModels;
import net.minecraft.client.render.entity.model.SlimeEntityModel;
import net.minecraft.client.render.entity.state.SlimeEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;

@Environment(EnvType.CLIENT)
public class MoonSlimeOverlayFeatureRenderer extends FeatureRenderer<SlimeEntityRenderState, SlimeEntityModel> {
	private final SlimeEntityModel model;
	public MoonSlimeOverlayFeatureRenderer(FeatureRendererContext<SlimeEntityRenderState, SlimeEntityModel> context, LoadedEntityModels loader) {
		super(context);
		this.model = new SlimeEntityModel(loader.getModelPart(EntityModelLayers.SLIME_OUTER));
	}
	public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, SlimeEntityRenderState slimeEntityRenderState, float f, float g) {
		boolean bl = slimeEntityRenderState.hasOutline && slimeEntityRenderState.invisible;
		if (!slimeEntityRenderState.invisible || bl) {
			this.model.setAngles(slimeEntityRenderState);
			this.model.render(matrixStack, vertexConsumerProvider.getBuffer(bl ? RenderLayer.getOutline(MoonSlimeEntityRenderer.texture) : RenderLayer.getEntityTranslucent(MoonSlimeEntityRenderer.texture)), i, LivingEntityRenderer.getOverlay(slimeEntityRenderState, 0.0F));
		}
	}
}
