/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.client;

import com.mclegoman.dtaf2025.client.compatibility.Compatibility;
import com.mclegoman.dtaf2025.client.config.AprilFoolsConfig;
import com.mclegoman.dtaf2025.client.entity.EntityModelRegistry;
import com.mclegoman.dtaf2025.client.gui.TitleScreenHelper;
import com.mclegoman.dtaf2025.client.block.ClientBlockRegistry;
import com.mclegoman.dtaf2025.client.item.ItemGroupRegistry;
import com.mclegoman.dtaf2025.client.keybindings.Keybindings;
import com.mclegoman.dtaf2025.client.network.ClientPackets;
import com.mclegoman.dtaf2025.client.particle.ClientParticleRegistry;
import com.mclegoman.dtaf2025.client.shaders.Shaders;
import com.mclegoman.dtaf2025.client.sky.SkyDataloader;
import com.mclegoman.dtaf2025.client.util.ClientTick;
import com.mclegoman.dtaf2025.client.world.dimension.ClientDimensionRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resource.ResourceType;

@Environment(EnvType.CLIENT)
public class ClientAprilFools implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		AprilFoolsConfig.init();
		ClientPackets.init();
		ClientParticleRegistry.init();
		ClientBlockRegistry.init();
		TitleScreenHelper.init();
		ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new SkyDataloader());
		ItemGroupRegistry.init();
		EntityModelRegistry.init();
		ClientDimensionRegistry.init();
		ClientTick.init();
		Shaders.init();
		Keybindings.init();
		Compatibility.init();
	}
}
