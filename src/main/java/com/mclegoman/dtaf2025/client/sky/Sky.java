/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.client.sky;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.RotationAxis;
import org.joml.Matrix4f;

import java.util.List;

@Environment(EnvType.CLIENT)
public class Sky {
	public record Position(Type type, float value, float skyAngleMultiplier) {
		public enum Type {
			X,
			Y,
			Z
		}
	}
	public record Celestial(Identifier dimension, List<Position> positions, float scale, Visible visible, int phases, int phaseOffset) {
		public Identifier getTexture(Identifier id) {
			return Identifier.of(id.getNamespace(), "textures/sky/" + id.getPath() + ".png");
		}
		public int getPhase(long time) {
			return (int)((time / 24000L % ((long)phases()) + ((long)phases())) + phaseOffset()) % phases();
		}
		public Object get() {
			return this;
		}
	}
	public enum Visible {
		ALWAYS,
		DAY,
		NIGHT;
		public static Visible fromString(String string) {
			switch (string.toLowerCase()) {
				case "always" -> {
					return ALWAYS;
				}
				case "day" -> {
					return DAY;
				}
				default -> {
					return NIGHT;
				}
			}
		}
	}
	public static void renderObject(VertexConsumerProvider vertexConsumers, Identifier id, Celestial celestialObj, float tickDelta, ClientWorld world, float alpha) {
		if (world != null) {
			Identifier effectsId = world.getDimension().effects();
			if (celestialObj.dimension.equals(effectsId)) {
				MatrixStack matrixStack = new MatrixStack();
				matrixStack.push();
				alpha = switch (celestialObj.visible) {
					case NIGHT -> world.getStarBrightness(tickDelta) * alpha;
					case DAY -> (0.5F - world.getStarBrightness(tickDelta)) * alpha;
					default -> alpha;
				};
				for (Position position : celestialObj.positions()) {
					float angle = ((-world.getSkyAngle(tickDelta) * 360.0F) * position.skyAngleMultiplier()) + position.value();
					switch (position.type()) {
						case X -> matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(angle));
						case Y -> matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(angle));
						case Z -> matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(angle));
					}
				}
				Matrix4f positionMatrix = matrixStack.peek().getPositionMatrix();
				VertexConsumer bufferBuilder = vertexConsumers.getBuffer(RenderLayer.getCelestial(celestialObj.getTexture(id)));
				int phase = celestialObj.getPhase(world.getLunarTime()) % celestialObj.phases();
				float v1 = (float) (phase) / celestialObj.phases();
				float v2 = (phase + 1.0F) / celestialObj.phases();
				int color = ColorHelper.getWhite(alpha);
				bufferBuilder.vertex(positionMatrix, -celestialObj.scale(), 100.0F, -celestialObj.scale()).texture(0.0F, v1).color(color);
				bufferBuilder.vertex(positionMatrix, celestialObj.scale(), 100.0F, -celestialObj.scale()).texture(1.0F, v1).color(color);
				bufferBuilder.vertex(positionMatrix, celestialObj.scale(), 100.0F, celestialObj.scale()).texture(1.0F, v2).color(color);
				bufferBuilder.vertex(positionMatrix, -celestialObj.scale(), 100.0F, celestialObj.scale()).texture(0.0F, v2).color(color);
				matrixStack.pop();
			}
		}
	}
}