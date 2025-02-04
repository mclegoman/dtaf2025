package com.mclegoman.dtaf2025.common.network.payload;

import com.mclegoman.dtaf2025.common.network.Packets;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record RequestPayload(Identifier identifier) implements CustomPayload {
	public static final Id<RequestPayload> id = new Id<>(Packets.request);
	public static final PacketCodec<RegistryByteBuf, RequestPayload> packetCodec = PacketCodec.tuple(Identifier.PACKET_CODEC, RequestPayload::identifier, RequestPayload::new);
	@Override
	public Id<? extends CustomPayload> getId() {
		return id;
	}
}