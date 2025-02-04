package com.mclegoman.dtaf2025.client.entity.renderer;

import com.mclegoman.dtaf2025.common.data.Data;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.SlimeEntityModel;
import net.minecraft.client.render.entity.state.SlimeEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class MoonSlimeEntityRenderer extends MobEntityRenderer<SlimeEntity, SlimeEntityRenderState, SlimeEntityModel> {
	public static final Identifier texture = Identifier.of(Data.version.getID(), "textures/entity/moon_slime/moon_slime.png");

	public MoonSlimeEntityRenderer(EntityRendererFactory.Context context) {
		super(context, new SlimeEntityModel(context.getPart(EntityModelLayers.SLIME)), 0.25F);
		this.addFeature(new MoonSlimeOverlayFeatureRenderer(this, context.getEntityModels()));
	}

	protected float getShadowRadius(SlimeEntityRenderState slimeEntityRenderState) {
		return (float)slimeEntityRenderState.size * 0.25F;
	}

	protected void scale(SlimeEntityRenderState slimeEntityRenderState, MatrixStack matrixStack) {
		float f = 0.999F;
		matrixStack.scale(f, f, f);
		matrixStack.translate(0.0F, 0.001F, 0.0F);
		float g = (float)slimeEntityRenderState.size;
		float h = slimeEntityRenderState.stretch / (g * 0.5F + 1.0F);
		float i = 1.0F / (h + 1.0F);
		matrixStack.scale(i * g, 1.0F / i * g, i * g);
	}

	public Identifier getTexture(SlimeEntityRenderState slimeEntityRenderState) {
		return texture;
	}

	public SlimeEntityRenderState createRenderState() {
		return new SlimeEntityRenderState();
	}

	public void updateRenderState(SlimeEntity slimeEntity, SlimeEntityRenderState slimeEntityRenderState, float f) {
		super.updateRenderState(slimeEntity, slimeEntityRenderState, f);
		slimeEntityRenderState.stretch = MathHelper.lerp(f, slimeEntity.lastStretch, slimeEntity.stretch);
		slimeEntityRenderState.size = slimeEntity.getSize();
	}
}
