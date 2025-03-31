/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.client.sky;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mclegoman.luminance.client.util.JsonResourceReloader;
import com.mclegoman.luminance.common.util.Couple;
import com.mclegoman.luminance.common.util.LogType;
import com.mclegoman.dtaf2025.common.data.Data;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.profiler.Profiler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Environment(EnvType.CLIENT)
public class SkyDataloader extends JsonResourceReloader implements IdentifiableResourceReloadListener {
	public static final Map<Identifier, Couple<Identifier, Sky.Celestial>> registry = new HashMap<>();
	public static final String id = "sky";
	public SkyDataloader() {
		super(new Gson(), id);
	}
	@Override
	public void apply(Map<Identifier, JsonElement> prepared, ResourceManager manager, Profiler profiler) {
		registry.clear();
		prepared.forEach(this::register);
	}
	private void add(Identifier id, Sky.Celestial celestialObj, Identifier dimId) {
		Identifier regId = Identifier.of(id.getNamespace(), id.getPath() + "_" + dimId.toUnderscoreSeparatedString());
		if (registry.containsKey(regId)) {
			if (!registry.get(regId).getSecond().get().equals(celestialObj)) registry.replace(id, new Couple<>(id, celestialObj));
		} else registry.put(regId, new Couple<>(id, celestialObj));
	}
	private void register(Identifier identifier, JsonElement jsonElement) {
		try {
			JsonObject reader = jsonElement.getAsJsonObject();
			for (JsonElement dimensions : JsonHelper.getArray(reader, "dimensions", new JsonArray())) {
				if (dimensions instanceof JsonObject dimension) {
					Identifier dimId = Identifier.of(JsonHelper.getString(dimension, "id"));
					int phases = JsonHelper.getInt(dimension, "phases", 1);
					if (phases >= 1) {
						List<Sky.Position> positions = new ArrayList<>();
						for (JsonElement position : JsonHelper.getArray(dimension, "position", new JsonArray())) {
							if (position instanceof JsonObject pos) {
								Sky.Position.Type type = switch (JsonHelper.getString(pos, "type").toLowerCase()) {
									case "x" -> Sky.Position.Type.X;
									case "y" -> Sky.Position.Type.Y;
									case "z" -> Sky.Position.Type.Z;
									default -> throw new IllegalStateException("Unexpected value: " + JsonHelper.getString(pos, "type"));
								};
								positions.add(new Sky.Position(type, JsonHelper.getFloat(pos, "value"), JsonHelper.getInt(pos, "skyAngleMultiplier", 0)));
							} else {
								Data.getVersion().sendToLog(LogType.WARN, "Invalid Position Data!");
							}
						}
						add(identifier, new Sky.Celestial(dimId, positions, JsonHelper.getFloat(dimension, "scale", 5.0F), Sky.Visible.fromString(JsonHelper.getString(dimension, "visible", "night")), phases, JsonHelper.getInt(dimension, "phaseOffset", 0)), dimId);
					}
				} else Data.getVersion().sendToLog(LogType.WARN, "Invalid Dimension Data!");
			}
		} catch (Exception error) {
			Data.getVersion().sendToLog(LogType.ERROR, error.getLocalizedMessage());
		}
	}
	@Override
	public Identifier getFabricId() {
		return Identifier.of(Data.getVersion().getID(), id);
	}
}