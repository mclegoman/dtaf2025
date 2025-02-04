/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.client.shaders;

import com.mclegoman.dtaf2025.common.data.Data;
import com.mclegoman.dtaf2025.common.network.Packets;
import com.mclegoman.luminance.client.shaders.ShaderTime;

public class Uniforms {
	public static void init() {
		String path = Data.version.getID();
		com.mclegoman.luminance.client.shaders.Uniforms.registerSingleTree(path, "isSanic", Uniforms::getIsSanic, 0f, 1f);
	}
	public static float getIsSanic(ShaderTime shaderTime) {
		return Packets.Values.isSanic ? 1.0F : 0.0F;
	}
}
