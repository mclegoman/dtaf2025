/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.common.network;

import com.mclegoman.dtaf2025.common.data.Data;
import com.mclegoman.dtaf2025.common.easter_egg.EasterEggRegistry;
import com.mclegoman.dtaf2025.common.network.payload.OpenScreenPayload;
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
	public static final Identifier openScreen;
	public static void init() {
		PayloadTypeRegistry.playS2C().register(RequestPayload.id, RequestPayload.packetCodec);
		PayloadTypeRegistry.playC2S().register(RequestPayload.id, RequestPayload.packetCodec);
		ServerPlayNetworking.registerGlobalReceiver(RequestPayload.id, Packets::receiveRequest);

		PayloadTypeRegistry.playS2C().register(SanicPayload.id, SanicPayload.packetCodec);

		PayloadTypeRegistry.playS2C().register(OpenScreenPayload.id, OpenScreenPayload.packetCodec);

		ServerLifecycleEvents.SERVER_STARTED.register(Packets::update);
		ServerLifecycleEvents.END_DATA_PACK_RELOAD.register((server, serverResourceManager, success) -> update(server));
	}
	public static void sendRequest(ServerPlayerEntity player, Identifier requestId) {
		// We don't currently require anything from the client.
		player.server.execute(() -> {
			ServerPlayNetworking.send(player, new RequestPayload(requestId));
		});
	}
	public static void receiveRequest(RequestPayload payload, ServerPlayNetworking.Context context) {
		context.server().execute(() -> {
			if (payload.identifier().equals(isSanic)) sendSanic(context.player());
			else Data.getVersion().sendToLog(LogType.WARN, "[SERVER] Unexpectedly received RequestPayload('" + payload.identifier() + "') from '" + context.player().getGameProfile().getName() + "(" + context.player().getGameProfile().getId() + ")" + "'!");
		});
	}
	public static void sendSanic(ServerPlayerEntity player) {
		player.server.execute(() -> {
			update(player.server);
			ServerPlayNetworking.send(player, new SanicPayload(EasterEggRegistry.getSanic()));
		});
	}
	public static void sendOpenScreen(ServerPlayerEntity player, Identifier identifier) {
		player.server.execute(() -> {
			ServerPlayNetworking.send(player, new OpenScreenPayload(identifier));
		});
	}
	public static void update(MinecraftServer server) {
		server.execute(() -> EasterEggRegistry.setSanic(server.getSaveProperties().getGeneratorOptions().getSeed() == 78665946));
	}
	static {
		request = Identifier.of(Data.getVersion().getID(), "request");
		isSanic = Identifier.of(Data.getVersion().getID(), "is_sanic");
		openScreen = Identifier.of(Data.getVersion().getID(), "open_screen");
	}
}
