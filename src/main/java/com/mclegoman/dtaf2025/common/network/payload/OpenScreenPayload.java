/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.common.network.payload;

import com.mclegoman.dtaf2025.common.network.Packets;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record OpenScreenPayload(Identifier identifier) implements CustomPayload {
	public static final Id<OpenScreenPayload> id = new Id<>(Packets.openScreen);
	public static final PacketCodec<RegistryByteBuf, OpenScreenPayload> packetCodec = PacketCodec.tuple(Identifier.PACKET_CODEC, OpenScreenPayload::identifier, OpenScreenPayload::new);
	@Override
	public Id<? extends CustomPayload> getId() {
		return id;
	}
}