/*
    dtaf2025
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.common.registry;

import com.mclegoman.dtaf2025.common.block.LockedChestBlock;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class BlockRegistry {
	public static final Block crystalBlock;
	public static final Block crystalGlass;
	public static final Block crystalGlassPane;
	public static final Block lockedChest;
	public static void init() {
	}
	static {
		crystalBlock = Registry.register(Registries.BLOCK, Identifier.of("dtaf2025", "crystal_block"), new Block(AbstractBlock.Settings.create().mapColor(MapColor.BRIGHT_TEAL).requiresTool().strength(55.0F, 1200.0F).sounds(BlockSoundGroup.METAL)));
		crystalGlass = Registry.register(Registries.BLOCK, Identifier.of("dtaf2025", "crystal_glass"), new TransparentBlock(AbstractBlock.Settings.create().mapColor(MapColor.BRIGHT_TEAL).requiresTool().strength(27.5F, 600.0F).instrument(NoteBlockInstrument.HAT).sounds(BlockSoundGroup.GLASS).nonOpaque().allowsSpawning(Blocks::never).solidBlock(Blocks::never).suffocates(Blocks::never).blockVision(Blocks::never)));
		crystalGlassPane = Registry.register(Registries.BLOCK, Identifier.of("dtaf2025", "crystal_glass_pane"), new PaneBlock(AbstractBlock.Settings.create().instrument(NoteBlockInstrument.HAT).strength(27.5F, 600.0F).sounds(BlockSoundGroup.GLASS).nonOpaque()));
		lockedChest = Registry.register(Registries.BLOCK, Identifier.of("dtaf2025", "locked_chest"), new LockedChestBlock(AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).instrument(NoteBlockInstrument.BASS).strength(2.5F).sounds(BlockSoundGroup.WOOD).burnable()));
	}
}
