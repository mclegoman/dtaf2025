/*
    dtaf2025
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.client.space;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mclegoman.luminance.common.util.LogType;
import com.mclegoman.dtaf2025.common.data.Data;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.resource.JsonDataLoader;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.profiler.Profiler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SkyDataloader extends JsonDataLoader implements IdentifiableResourceReloadListener {
	public static final Map<Identifier, Sky.Celestial> registry = new HashMap<>();
	public static final String id = "sky";
	public SkyDataloader() {
		super(new Gson(), id);
	}
	@Override
	public void apply(Map<Identifier, JsonElement> prepared, ResourceManager manager, Profiler profiler) {
		registry.clear();
		prepared.forEach(this::register);
	}
	private void add(Identifier id, Sky.Celestial celestialObj) {
		if (registry.containsKey(id)) {
			if (!registry.get(id).get().equals(celestialObj)) registry.replace(id, celestialObj);
		} else registry.put(id, celestialObj);
	}
	private void register(Identifier identifier, JsonElement jsonElement) {
		try {
			JsonObject reader = jsonElement.getAsJsonObject();
			int phases = JsonHelper.getInt(reader, "phases", 1);
			if (phases >= 1) {
				List<Sky.Position> positions = new ArrayList<>();
				for (JsonElement position : JsonHelper.getArray(reader, "position", new JsonArray())) {
					if (position instanceof JsonObject pos) {
						Sky.Position.Type type = switch (JsonHelper.getString(pos, "type").toLowerCase()) {
							case "x" -> Sky.Position.Type.X;
							case "y" -> Sky.Position.Type.Y;
							case "z" -> Sky.Position.Type.Z;
							default -> throw new IllegalStateException("Unexpected value: " + JsonHelper.getString(pos, "type"));
						};
						positions.add(new Sky.Position(type, JsonHelper.getFloat(pos, "value"), JsonHelper.getFloat(pos, "skyAngleMultiplier", 0.0F)));
					} else {
						Data.version.sendToLog(LogType.WARN, "Invalid Position Data!");
					}
				}
				add(identifier, new Sky.Celestial(positions, JsonHelper.getFloat(reader, "scale", 5.0F), Sky.Visible.fromString(JsonHelper.getString(reader, "visible", "night")), phases, JsonHelper.getInt(reader, "phaseOffset", 0)));
			}
			else registry.remove(identifier);
		} catch (Exception error) {
			Data.version.sendToLog(LogType.ERROR, error.getLocalizedMessage());
		}
	}
	@Override
	public Identifier getFabricId() {
		return Identifier.of(Data.version.getID(), id);
	}
}