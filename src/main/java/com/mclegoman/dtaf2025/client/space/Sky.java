/*
    dtaf2025
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.client.space;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import org.joml.Matrix4f;

import java.util.List;

public class Sky {
	public record Position(Type type, float value, float skyAngleMultiplier) {
		public enum Type {
			X,
			Y,
			Z
		}
	}
	public record Celestial(List<Position> positions, float scale, Visible visible, int phases, int phaseOffset) {
		public Identifier getTexture(Identifier id) {
			return Identifier.of(id.getNamespace(), "textures/sky/" + id.getPath() + ".png");
		}
		public int getPhase(long time) {
			return (int)((time / 24000L % ((long)phases()) + ((long)phases())) + phaseOffset()) % phases();
		}
		public Celestial get() {
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
	public static void renderObject(MatrixStack matrixStack, Identifier id, Celestial celestialObj, float tickDelta, ClientWorld world) {
		matrixStack.push();
		float rainGradient = 1.0F - world.getRainGradient(tickDelta);
		float alpha = celestialObj.visible.equals(Visible.NIGHT) ? world.getStarBrightness(tickDelta) : (celestialObj.visible.equals(Visible.DAY) ? 0.5F - world.getStarBrightness(tickDelta) : 0.5F);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, (2 * alpha * rainGradient));
		for (Position position : celestialObj.positions()) {
			float angle = ((-world.getSkyAngle(tickDelta) * 360.0F) * position.skyAngleMultiplier()) + position.value();
			switch (position.type()) {
				case X -> matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(angle));
				case Y -> matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(angle));
				case Z -> matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(angle));
			}
		}
		Matrix4f positionMatrix = matrixStack.peek().getPositionMatrix();
		RenderSystem.setShaderTexture(0, celestialObj.getTexture(id));
		BufferBuilder bufferBuilder = Tessellator.getInstance().begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE);
		int phase = celestialObj.getPhase(world.getLunarTime()) % celestialObj.phases();
		float v1 = (float)(phase) / celestialObj.phases();
		float v2 = (phase + 1.0F) / celestialObj.phases();
		bufferBuilder.vertex(positionMatrix, -celestialObj.scale(), 100.0F, -celestialObj.scale()).texture(0.0F, v1);
		bufferBuilder.vertex(positionMatrix, celestialObj.scale(), 100.0F, -celestialObj.scale()).texture(1.0F, v1);
		bufferBuilder.vertex(positionMatrix, celestialObj.scale(), 100.0F, celestialObj.scale()).texture(1.0F, v2);
		bufferBuilder.vertex(positionMatrix, -celestialObj.scale(), 100.0F, celestialObj.scale()).texture(0.0F, v2);
		BufferRenderer.drawWithGlobalProgram(bufferBuilder.end());
		matrixStack.pop();
	}
}