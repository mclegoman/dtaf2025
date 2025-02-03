/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.common.block;

import com.mclegoman.dtaf2025.common.data.Data;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class BlockRegistry {
	public static final Block spaceAir;
	public static final Block moonStone;
	public static final Block crystalBlock;
	public static final Block crystalGlass;
	public static final Block crystalGlassPane;
	public static final Block lockedChest;
	public static void init() {
	}
	static {
		spaceAir = Blocks.register(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Data.version.getID(), "space_air")), SpaceAirBlock::new, AbstractBlock.Settings.create().replaceable().noCollision().dropsNothing());
		moonStone = Blocks.register(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Data.version.getID(), "moon_stone")), Block::new, AbstractBlock.Settings.create().mapColor(MapColor.PALE_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3.0F, 9.0F));
		crystalBlock = Blocks.register(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Data.version.getID(), "crystal_block")), Block::new, AbstractBlock.Settings.create().mapColor(MapColor.BRIGHT_TEAL).requiresTool().strength(55.0F, 1200.0F).sounds(BlockSoundGroup.METAL));
		crystalGlass = Blocks.register(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Data.version.getID(), "crystal_glass")), TransparentBlock::new, AbstractBlock.Settings.create().mapColor(MapColor.BRIGHT_TEAL).requiresTool().strength(27.5F, 600.0F).instrument(NoteBlockInstrument.HAT).sounds(BlockSoundGroup.GLASS).nonOpaque().allowsSpawning(Blocks::never).solidBlock(Blocks::never).suffocates(Blocks::never).blockVision(Blocks::never));
		crystalGlassPane = Blocks.register(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Data.version.getID(), "crystal_glass_pane")), PaneBlock::new, AbstractBlock.Settings.create().instrument(NoteBlockInstrument.HAT).strength(27.5F, 600.0F).sounds(BlockSoundGroup.GLASS).nonOpaque());
		lockedChest = Blocks.register(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Data.version.getID(), "locked_chest")), LockedChestBlock::new, AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).instrument(NoteBlockInstrument.BASS).strength(2.5F).sounds(BlockSoundGroup.WOOD).burnable());
	}
}
