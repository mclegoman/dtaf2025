/*
    mclmaf2025
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/mclmaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.mclmaf2025.client.space;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mclegoman.luminance.common.util.LogType;
import com.mclegoman.mclmaf2025.common.data.Data;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.resource.JsonDataLoader;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.profiler.Profiler;

import java.util.HashMap;
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
			if (phases >= 1) add(identifier, new Sky.Celestial(JsonHelper.getFloat(reader, "x1"), JsonHelper.getFloat(reader, "y1"), JsonHelper.getFloat(reader, "z1"), JsonHelper.getFloat(reader, "y2"), JsonHelper.getFloat(reader, "scale", 5.0F), Sky.Visible.fromString(JsonHelper.getString(reader, "visible", "night")), phases, JsonHelper.getInt(reader, "phaseOffset", 0)));
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