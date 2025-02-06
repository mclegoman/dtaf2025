/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.client.world;

import com.mclegoman.dtaf2025.client.world.dimension.SpaceEffect;
import com.mclegoman.dtaf2025.common.data.Data;
import com.mclegoman.dtaf2025.common.util.Tags;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import org.joml.Matrix4f;

public class WorldHelper {
	public static boolean isInSpace(ClientPlayerEntity player) {
		return player != null && player.getWorld().getBiome(player.getBlockPos()).isIn(Tags.WorldGen.Biome.space);
	}
	public static boolean isInSpace(ClientWorld world) {
		return world != null && world.getDimensionEffects() instanceof SpaceEffect;
	}
	public static boolean isIn(ClientWorld world, Identifier identifier) {
		return world != null && world.getDimension().effects().equals(identifier);
	}
	public static void renderSun(float alpha, VertexConsumerProvider vertexConsumers, MatrixStack matrices, float scale, boolean isSpace) {
		float f = 30.0F * scale;
		float g = 100.0F;
		VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getCelestial(isSpace ? Identifier.of(Data.getVersion().getID(), "textures/environment/space_sun.png") : Identifier.of("textures/environment/sun.png")));
		int i = ColorHelper.getWhite(alpha);
		Matrix4f matrix4f = matrices.peek().getPositionMatrix();
		vertexConsumer.vertex(matrix4f, -f, g, -f).texture(0.0F, 0.0F).color(i);
		vertexConsumer.vertex(matrix4f, f, g, -f).texture(1.0F, 0.0F).color(i);
		vertexConsumer.vertex(matrix4f, f, g, f).texture(1.0F, 1.0F).color(i);
		vertexConsumer.vertex(matrix4f, -f, g, f).texture(0.0F, 1.0F).color(i);
	}
}
