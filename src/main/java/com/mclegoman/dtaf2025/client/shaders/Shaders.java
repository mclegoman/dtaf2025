/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.client.shaders;

import com.mclegoman.dtaf2025.client.config.AprilFoolsConfig;
import com.mclegoman.dtaf2025.client.data.ClientData;
import com.mclegoman.dtaf2025.common.data.Data;
import com.mclegoman.dtaf2025.client.world.WorldHelper;
import com.mclegoman.dtaf2025.common.easter_egg.EasterEggRegistry;
import com.mclegoman.luminance.client.events.Events;
import com.mclegoman.luminance.client.shaders.Shader;
import com.mclegoman.luminance.client.shaders.ShaderTime;
import com.mclegoman.luminance.client.shaders.Uniforms;
import net.minecraft.util.Identifier;

import java.util.List;

public class Shaders {
	public static void init() {
		Events.ShaderRender.register(getId("main"));
		Events.AfterShaderDataRegistered.register(getId("main"), () -> Events.ShaderRender.modify(getId("main"), setup()));
		Uniforms.registerSingleTree(Data.getVersion().getID(), "bloomStrength", Shaders::getBloomStrength, 0.0F, 1.0F);
		Uniforms.registerSingleTree(Data.getVersion().getID(), "easterEggStrength", Shaders::getEasterEggStrength, 0.0F, 1.0F);
	}
	public static int getRawBloomStrength() {
		return AprilFoolsConfig.config.bloomStrength.value();
	}
	public static int getRawEasterEggStrength() {
		return AprilFoolsConfig.config.easterEggStrength.value();
	}
	public static float getBloomStrength(ShaderTime shaderTime) {
		return Math.clamp(getRawBloomStrength() / 100.0F, 0.0F, 1.0F);
	}
	public static float getEasterEggStrength(ShaderTime shaderTime) {
		return Math.clamp(getRawEasterEggStrength() / 100.0F, 0.0F, 1.0F);
	}
	private static List<Shader.Data> setup() {
		return List.of(
				new Shader.Data(
						getId("bloom"),
						new Shader(
								com.mclegoman.luminance.client.shaders.Shaders.get(getId("main"), getId("bloom")),
								Shaders::getRenderType,
								Shaders::canRenderBloomShader)
				),
				new Shader.Data(
						getId("space"),
						new Shader(
								com.mclegoman.luminance.client.shaders.Shaders.get(getId("main"), getId("space")),
								Shaders::getRenderType,
								Shaders::canRenderSpaceShader)
				),
				new Shader.Data(
						getId("sanic"),
						new Shader(
								com.mclegoman.luminance.client.shaders.Shaders.get(getId("main"), getId("sanic")),
								Shaders::getRenderType,
								Shaders::canRenderSanicShader)
				)
		);
	}
	public static Identifier getId(String path) {
		return Identifier.of(Data.getVersion().getID(), path);
	}
	private static boolean canRenderBloomShader() {
		return getRawBloomStrength() > 0;
	}
	private static boolean canRenderSpaceShader() {
		return WorldHelper.isInSpace(ClientData.client.player);
	}
	private static boolean canRenderSanicShader() {
		return EasterEggRegistry.getSanic();
	}
	private static Shader.RenderType getRenderType() {
		return Shader.RenderType.WORLD;
	}
}
