/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.client.block;

import com.mclegoman.dtaf2025.common.block.BlockRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

@Environment(EnvType.CLIENT)
public class ClientBlockRegistry {
	public static void init() {
		BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.crystalGlass, RenderLayer.getTranslucent());
		BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.crystalGlassPane, RenderLayer.getTranslucent());
		BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.spacePortal, RenderLayer.getTranslucent());
	}
}
