/*
    mclmaf2025
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/mclmaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.mclmaf2025.client;

import com.mclegoman.mclmaf2025.client.gui.TitleScreenHelper;
import com.mclegoman.mclmaf2025.client.space.SkyDataloader;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resource.ResourceType;

public class mclmaf2025Client implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		TitleScreenHelper.init();
		ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(new SkyDataloader());
	}
}
