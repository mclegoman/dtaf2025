package com.mclegoman.mclmaf2025.client;

import com.mclegoman.mclmaf2025.client.gui.SplashesDataloader;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resource.ResourceType;

public class mclmaf2025Client implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new SplashesDataloader());
	}
}
