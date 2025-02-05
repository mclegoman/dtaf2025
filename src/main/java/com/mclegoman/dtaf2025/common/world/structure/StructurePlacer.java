/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3 (This class is licenced under CC-0-1.0)

    This class was created using parts of StructurePlacerAPI.
    https://github.com/Emafire003/StructurePlacerAPI
    Whilst we could have included it as a jar, as dtaf2025 is an april fools mod,
    we cannot be certain that the mod will be updated for this year's april fools snapshot.
    Since StructurePlacerAPI is licenced under CC-0, I'm including the parts we need as part of dtaf2025.
*/

package com.mclegoman.dtaf2025.common.world.structure;

import com.mclegoman.dtaf2025.common.data.Data;
import com.mclegoman.luminance.common.util.LogType;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.StructureTemplateManager;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;

import java.util.Optional;

public class StructurePlacer {
	private final StructureWorldAccess world;
	private final Identifier identifier;
	private final BlockPos blockPos;

	public StructurePlacer(StructureWorldAccess world, Identifier identifier, BlockPos blockPos) {
		this.world = world;
		this.identifier = identifier;
		this.blockPos = blockPos;
	}

	public boolean generate() {
		if (this.identifier != null && world.getServer() != null) {
			StructureTemplateManager structureTemplateManager = world.getServer().getStructureTemplateManager();
			try {
				Optional<StructureTemplate> optional = structureTemplateManager.getTemplate(this.identifier);
				return optional.isPresent() && this.place(optional.get());
			} catch (InvalidIdentifierException error) {
				return false;
			}
		} else {
			return false;
		}
	}

	private boolean place(StructureTemplate template) {
		try {
			template.place(world, blockPos, blockPos, new StructurePlacementData(), getRandom(this.world.getSeed()), 2);
			unload();
			return true;
		} catch (Exception error){
			Data.getVersion().sendToLog(LogType.ERROR, error.getLocalizedMessage());
			return false;
		}
	}

	private void unload() {
		if (this.identifier != null && world.getServer() != null) {
			StructureTemplateManager structureTemplateManager = world.getServer().getStructureTemplateManager();
			structureTemplateManager.unloadTemplate(this.identifier);
		}
	}

	public static Random getRandom(long seed) {
		return seed == 0L ? Random.create(Util.getMeasuringTimeMs()) : Random.create(seed);
	}
}