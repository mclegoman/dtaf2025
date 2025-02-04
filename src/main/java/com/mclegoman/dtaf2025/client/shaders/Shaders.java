/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.client.shaders;

import com.mclegoman.dtaf2025.client.data.ClientData;
import com.mclegoman.dtaf2025.common.data.Data;
import com.mclegoman.dtaf2025.client.world.WorldHelper;
import com.mclegoman.dtaf2025.common.network.Packets;
import com.mclegoman.luminance.client.events.Events;
import com.mclegoman.luminance.client.shaders.Shader;
import net.minecraft.util.Identifier;

import java.util.List;

public class Shaders {
	public static void init() {
		Events.ShaderRender.register(getId("main"));
		Events.AfterShaderDataRegistered.register(getId("main"), () -> Events.ShaderRender.modify(getId("main"), setup()));
		Uniforms.init();
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
								com.mclegoman.luminance.client.shaders.Shaders.get(getId("main"), getId("sanic")),
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
		return Identifier.of(Data.version.getID(), path);
	}
	private static boolean canRenderBloomShader() {
		return true;
	}
	private static boolean canRenderSpaceShader() {
		return WorldHelper.isInSpace(ClientData.client.world);
	}
	private static boolean canRenderSanicShader() {
		return Packets.Values.isSanic;
	}
	private static Shader.RenderType getRenderType() {
		return Shader.RenderType.WORLD;
	}
}
