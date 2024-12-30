/*
    dtaf2025
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.client.data;

import net.minecraft.client.MinecraftClient;

public class ClientData {
	public static MinecraftClient client;
	static {
		client = MinecraftClient.getInstance();
	}
}