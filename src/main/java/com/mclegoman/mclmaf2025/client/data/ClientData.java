package com.mclegoman.mclmaf2025.client.data;

import net.minecraft.client.MinecraftClient;

public class ClientData {
	public static MinecraftClient client;
	static {
		client = MinecraftClient.getInstance();
	}
}