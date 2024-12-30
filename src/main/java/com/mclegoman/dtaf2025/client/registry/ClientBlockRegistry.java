/*
    dtaf2025
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.client.registry;

import com.mclegoman.dtaf2025.common.registry.BlockRegistry;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class ClientBlockRegistry {
	public static void init() {
		BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.crystalGlass, RenderLayer.getTranslucent());
		BlockRenderLayerMap.INSTANCE.putBlock(BlockRegistry.crystalGlassPane, RenderLayer.getTranslucent());
	}
}
