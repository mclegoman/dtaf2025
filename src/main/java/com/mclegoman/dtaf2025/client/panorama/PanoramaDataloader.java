/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.client.panorama;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mclegoman.dtaf2025.client.data.ClientData;
import com.mclegoman.dtaf2025.client.gui.TitleScreenHelper;
import com.mclegoman.dtaf2025.common.data.Data;
import com.mclegoman.luminance.client.util.JsonDataLoader;
import com.mclegoman.luminance.common.util.LogType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.profiler.Profiler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.IntStream;

@Environment(EnvType.CLIENT)
public class PanoramaDataloader extends JsonDataLoader implements IdentifiableResourceReloadListener {
	public static final List<Identifier> registry = new ArrayList<>();
	private static Identifier currentPanorama;
	public static Identifier getPanorama() {
		return getPanorama(currentPanorama);
	}
	private static Identifier getPanorama(Identifier identifier) {
		Identifier panoramaId = identifier != null ? identifier : getDefaultPanorama();
		return Identifier.of(panoramaId.getNamespace(), "textures/" + panoramaId.getPath());
	}
	private static List<Identifier> getPanoramaAssets(Identifier identifier) {
		List<Identifier> assets = new ArrayList<>();
		Identifier panoramaDir = getPanorama(identifier);
		IntStream.range(0, 6).forEach((face) -> assets.add(panoramaDir.withPath(panoramaDir.getPath() + "/panorama_" + face + ".png")));
		assets.add(panoramaDir.withPath(panoramaDir.getPath() + "/panorama_overlay" + ".png"));
		return assets;
	}
	public static void randomizePanorama() {
		if (registry.size() > 1) {
			List<Identifier> panoramas = new ArrayList<>(registry);
			if (currentPanorama != null) panoramas.remove(currentPanorama);
			currentPanorama = panoramas.get(new Random().nextInt(panoramas.size()));
		} else {
			if (registry.size() == 1) currentPanorama = registry.getFirst();
			else currentPanorama = getDefaultPanorama();
		}
	}
	public static final String id = "panorama";
	public PanoramaDataloader() {
		super(new Gson(), id);
	}
	@Override
	public void apply(Map<Identifier, JsonElement> prepared, ResourceManager manager, Profiler profiler) {
		registry.clear();
		prepared.forEach((identifier, jsonElement) -> {
			Identifier assets = Identifier.of(JsonHelper.getString(jsonElement.getAsJsonObject(), "assets"));
			boolean validPanorama = true;
			for (Identifier asset : getPanoramaAssets(assets)) {
				if (ClientData.client.getResourceManager().getResource(asset).isEmpty()) {
					validPanorama = false;
					Data.getVersion().sendToLog(LogType.WARN, "Could not find asset '" + asset + "' for panorama with id '" + identifier + "', ignoring panorama!");
					break;
				}
			}
			if (validPanorama) registry.add(assets);
		});
		if (registry.isEmpty()) registry.add(getDefaultPanorama());
		randomizePanorama();
		TitleScreenHelper.updateCubeMapRenderer();
	}
	private static Identifier getDefaultPanorama() {
		return Identifier.of("gui/title/background");
	}
	@Override
	public Identifier getFabricId() {
		return Identifier.of(Data.getVersion().getID(), id);
	}
}