package com.mclegoman.dtaf2025.common.network;

import com.mclegoman.dtaf2025.common.data.Data;
import com.mclegoman.dtaf2025.common.easter_egg.EasterEggsRegistry;
import com.mclegoman.dtaf2025.common.network.payload.RequestPayload;
import com.mclegoman.dtaf2025.common.network.payload.SanicPayload;
import com.mclegoman.luminance.common.util.LogType;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public final class Packets {
	public static final Identifier request;
	public static final Identifier isSanic;
	public static void init() {
		PayloadTypeRegistry.playS2C().register(RequestPayload.id, RequestPayload.packetCodec);
		PayloadTypeRegistry.playC2S().register(RequestPayload.id, RequestPayload.packetCodec);
		PayloadTypeRegistry.playS2C().register(SanicPayload.id, SanicPayload.packetCodec);
		ServerLifecycleEvents.SERVER_STARTED.register((server) -> {
			Values.isSanic = EasterEggsRegistry.isSanicEasterEgg(server.getSaveProperties().getGeneratorOptions().getSeed());
			server.getPlayerManager().getPlayerList().forEach(Packets::sendIsSanic);
		});
		ServerPlayNetworking.registerGlobalReceiver(RequestPayload.id, (payload, context) -> context.server().execute(() -> {
			if (payload.identifier().equals(Packets.isSanic)) sendIsSanic(context.player());
		}));
	}
	public static void sendRequest(ServerPlayerEntity player, Identifier requestId) {
		Data.getVersion().sendToLog(LogType.INFO, "[SERVER] Requesting '" + requestId.toString() + "' packet!");
		ServerPlayNetworking.send(player, new RequestPayload(requestId));
	}
	public static void sendIsSanic(ServerPlayerEntity player) {
		Data.getVersion().sendToLog(LogType.INFO, "[SERVER] Sending '" + isSanic.toString() + "' packet!");
		ServerPlayNetworking.send(player, new SanicPayload(Values.isSanic));
	}
	static {
		request = Identifier.of(Data.version.getID(), "request");
		isSanic = Identifier.of(Data.version.getID(), "is_sanic");
	}
	public static class Values {
		public static boolean isSanic;
	}
}
