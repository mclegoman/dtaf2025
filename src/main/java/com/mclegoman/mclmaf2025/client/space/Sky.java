/*
    mclmaf2025
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/mclmaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.mclmaf2025.client.space;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import org.joml.Matrix4f;

public class Sky {
	public record Object(float x, float y, float z, float y2, float scale, Visible visible, int phases, int phaseOffset) {
		public Identifier getTexture(Identifier id) {
			return new Identifier(id.getNamespace(), "textures/sky/" + id.getPath() + ".png");
		}
		public int getPhase(long time) {
			return (int)((time / 24000L % ((long)phases()) + ((long)phases())) + phaseOffset()) % phases();
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
	public static void renderObject(MatrixStack matrixStack, Identifier id, Object skyObject, float tickDelta, ClientWorld world) {
		matrixStack.push();
		float rainGradient = 1.0F - world.getRainGradient(tickDelta);
		float alpha = skyObject.visible.equals(Visible.NIGHT) ? world.getStarBrightness(tickDelta) : (skyObject.visible.equals(Visible.DAY) ? 0.5F - world.getStarBrightness(tickDelta) : 0.5F);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, (2 * alpha * rainGradient));
		matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(90.0F));
		matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(skyObject.y()));
		matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-world.getSkyAngle(tickDelta) * 360.0F + skyObject.x()));
		matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(skyObject.z()));
		matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(skyObject.y2()));
		Matrix4f positionMatrix = matrixStack.peek().getPositionMatrix();
		BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
		RenderSystem.setShaderTexture(0, skyObject.getTexture(id));
		int phase = skyObject.getPhase(world.getLunarTime()) % skyObject.phases();
		float v1 = (float)(phase) / skyObject.phases();
		float v2 = (phase + 1.0F) / skyObject.phases();
		bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE);
		bufferBuilder.vertex(positionMatrix, -skyObject.scale(), 100.0F, -skyObject.scale()).texture(0.0F, v1).next();
		bufferBuilder.vertex(positionMatrix, skyObject.scale(), 100.0F, -skyObject.scale()).texture(1.0F, v1).next();
		bufferBuilder.vertex(positionMatrix, skyObject.scale(), 100.0F, skyObject.scale()).texture(1.0F, v2).next();
		bufferBuilder.vertex(positionMatrix, -skyObject.scale(), 100.0F, skyObject.scale()).texture(0.0F, v2).next();
		BufferRenderer.drawWithGlobalProgram(bufferBuilder.end());
		matrixStack.pop();
	}
}