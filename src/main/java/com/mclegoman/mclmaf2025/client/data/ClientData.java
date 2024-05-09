/*
    mclmaf2025
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/mclmaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.mclmaf2025.client.data;

import net.minecraft.client.MinecraftClient;

public class ClientData {
	public static MinecraftClient client;
	static {
		client = MinecraftClient.getInstance();
	}
}