package com.mclegoman.dtaf2025.common.network.payload;

import com.mclegoman.dtaf2025.common.network.Packets;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record SanicPayload(boolean isSanic) implements CustomPayload {
	public static final Id<SanicPayload> id = new Id<>(Packets.isSanic);
	public static final PacketCodec<RegistryByteBuf, SanicPayload> packetCodec = PacketCodec.tuple(PacketCodecs.BOOLEAN, SanicPayload::isSanic, SanicPayload::new);
	@Override
	public Id<? extends CustomPayload> getId() {
		return id;
	}
}