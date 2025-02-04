/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.common.network;

import com.mclegoman.dtaf2025.common.data.Data;
import com.mclegoman.dtaf2025.common.network.payload.RequestPayload;
import com.mclegoman.dtaf2025.common.network.payload.SanicPayload;
import com.mclegoman.luminance.common.util.LogType;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public final class Packets {
	public static final Identifier request;
	public static final Identifier isSanic;
	public static void init() {
		PayloadTypeRegistry.playS2C().register(RequestPayload.id, RequestPayload.packetCodec);
		PayloadTypeRegistry.playC2S().register(RequestPayload.id, RequestPayload.packetCodec);
		ServerPlayNetworking.registerGlobalReceiver(RequestPayload.id, Packets::receiveRequest);

		PayloadTypeRegistry.playS2C().register(SanicPayload.id, SanicPayload.packetCodec);

		ServerLifecycleEvents.SERVER_STARTED.register(Packets::update);
		ServerLifecycleEvents.END_DATA_PACK_RELOAD.register((server, serverResourceManager, success) -> update(server));
	}
	public static void sendRequest(ServerPlayerEntity player, Identifier requestId) {
		// We don't currently require anything from the client.
		ServerPlayNetworking.send(player, new RequestPayload(requestId));
	}
	public static void receiveRequest(RequestPayload payload, ServerPlayNetworking.Context context) {
		context.server().execute(() -> {
			if (payload.identifier().equals(Packets.isSanic)) sendIsSanic(context.player());
			else Data.getVersion().sendToLog(LogType.WARN, "[SERVER] Unexpectedly received RequestPayload: " + payload.identifier());
		});
	}
	public static void sendIsSanic(ServerPlayerEntity player) {
		ServerPlayNetworking.send(player, new SanicPayload(Values.isSanic));
	}
	public static void update(MinecraftServer server) {
		Values.isSanic = (server.getSaveProperties().getGeneratorOptions().getSeed() == 78665946);
		server.getPlayerManager().getPlayerList().forEach(Packets::sendIsSanic);
	}
	static {
		request = Identifier.of(Data.version.getID(), "request");
		isSanic = Identifier.of(Data.version.getID(), "is_sanic");
	}
	public static class Values {
		public static boolean isSanic;
	}
}
