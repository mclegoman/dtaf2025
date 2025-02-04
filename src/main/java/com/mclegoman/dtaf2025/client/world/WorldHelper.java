/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.client.world;

import com.mclegoman.dtaf2025.client.world.dimension.SpaceEffect;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import org.joml.Matrix4f;

public class WorldHelper {
	public static boolean isInSpace(ClientWorld world) {
		return world != null && world.getDimensionEffects() instanceof SpaceEffect;
	}
	public static boolean isIn(ClientWorld world, Identifier identifier) {
		return world != null && world.getDimension().effects().equals(identifier);
	}
	public static void renderSun(float alpha, VertexConsumerProvider vertexConsumers, MatrixStack matrices, float scale) {
		float f = 30.0F * scale;
		float g = 100.0F;
		VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getCelestial(Identifier.ofVanilla("textures/environment/sun.png")));
		int i = ColorHelper.getWhite(alpha);
		Matrix4f matrix4f = matrices.peek().getPositionMatrix();
		vertexConsumer.vertex(matrix4f, -f, g, -f).texture(0.0F, 0.0F).color(i);
		vertexConsumer.vertex(matrix4f, f, g, -f).texture(1.0F, 0.0F).color(i);
		vertexConsumer.vertex(matrix4f, f, g, f).texture(1.0F, 1.0F).color(i);
		vertexConsumer.vertex(matrix4f, -f, g, f).texture(0.0F, 1.0F).color(i);
	}
	public static void renderMoon(int phase, float alpha, VertexConsumerProvider vertexConsumers, MatrixStack matrices, float scale) {
		float f = 20.0F * scale;
		int i = phase % 4;
		int j = phase / 4 % 2;
		float g = (float)(i) / 4.0F;
		float h = (float)(j) / 2.0F;
		float k = (float)(i + 1) / 4.0F;
		float l = (float)(j + 1) / 2.0F;
		float m = 100.0F;
		VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getCelestial(Identifier.ofVanilla("textures/environment/moon_phases.png")));
		int n = ColorHelper.getWhite(alpha);
		Matrix4f matrix4f = matrices.peek().getPositionMatrix();
		vertexConsumer.vertex(matrix4f, -f, -m, f).texture(k, l).color(n);
		vertexConsumer.vertex(matrix4f, f, -m, f).texture(g, l).color(n);
		vertexConsumer.vertex(matrix4f, f, -m, -f).texture(g, h).color(n);
		vertexConsumer.vertex(matrix4f, -f, -m, -f).texture(k, h).color(n);
	}
}
