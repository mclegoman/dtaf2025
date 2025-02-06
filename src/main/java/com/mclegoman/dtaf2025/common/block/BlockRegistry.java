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
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class BlockRegistry {
	public static final Block spaceAir;
	public static final Block moonStone;
	public static final Block moonBricks;
	public static final Block crackedMoonBricks;
	public static final Block moonBrickStairs;
	public static final Block moonBrickSlab;
	public static final Block moonBrickWall;
	public static final Block chiseledMoonBricks;
	public static final Block crystalOre;
	public static final Block crystalBlock;
	public static final Block crystalStairs;
	public static final Block crystalSlab;
	public static final Block crystalWall;
	public static final Block crystalGlass;
	public static final Block crystalGlassPane;
	public static final Block pulser;
	public static final Block lockedChest;
	public static void init() {
	}
	static {
		spaceAir = Blocks.register(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Data.getVersion().getID(), "space_air")), SpaceAirBlock::new, AbstractBlock.Settings.create().replaceable().noCollision().dropsNothing());
		moonStone = Blocks.register(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Data.getVersion().getID(), "moon_stone")), Block::new, AbstractBlock.Settings.create().mapColor(MapColor.PALE_YELLOW).instrument(NoteBlockInstrument.BASEDRUM).requiresTool().strength(3.0F, 9.0F).sounds(BlockSoundGroup.NETHERRACK));
		moonBricks = Blocks.register(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Data.getVersion().getID(), "moon_bricks")), Block::new, AbstractBlock.Settings.copy(moonStone).sounds(BlockSoundGroup.NETHER_BRICKS));
		crackedMoonBricks = Blocks.register(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Data.getVersion().getID(), "cracked_moon_bricks")), Block::new, AbstractBlock.Settings.copy(moonStone));
		moonBrickStairs = Blocks.register(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Data.getVersion().getID(), "moon_brick_stairs")), (settings) -> new StairsBlock(moonBricks.getDefaultState(), settings), AbstractBlock.Settings.copy(moonBricks));
		moonBrickSlab = Blocks.register(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Data.getVersion().getID(), "moon_brick_slab")), SlabBlock::new, AbstractBlock.Settings.copy(moonBricks));
		moonBrickWall = Blocks.register(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Data.getVersion().getID(), "moon_brick_wall")), WallBlock::new, AbstractBlock.Settings.copy(moonBricks));
		chiseledMoonBricks = Blocks.register(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Data.getVersion().getID(), "chiseled_moon_bricks")), Block::new, AbstractBlock.Settings.copy(moonStone));
		crystalOre = Blocks.register(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Data.getVersion().getID(), "crystal_ore")), (settings) -> new ExperienceDroppingBlock(UniformIntProvider.create(3, 7), settings), AbstractBlock.Settings.copy(moonStone).strength(9.0F, 27.0F));
		crystalBlock = Blocks.register(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Data.getVersion().getID(), "crystal_block")), Block::new, AbstractBlock.Settings.create().mapColor(MapColor.BRIGHT_TEAL).requiresTool().strength(55.0F, 1200.0F).sounds(BlockSoundGroup.AMETHYST_BLOCK));
		crystalStairs = Blocks.register(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Data.getVersion().getID(), "crystal_stairs")), (settings) -> new StairsBlock(crystalBlock.getDefaultState(), settings), AbstractBlock.Settings.copy(crystalBlock));
		crystalSlab = Blocks.register(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Data.getVersion().getID(), "crystal_slab")), SlabBlock::new, AbstractBlock.Settings.copy(crystalBlock));
		crystalWall = Blocks.register(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Data.getVersion().getID(), "crystal_wall")), WallBlock::new, AbstractBlock.Settings.copy(crystalBlock));
		crystalGlass = Blocks.register(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Data.getVersion().getID(), "crystal_glass")), TransparentBlock::new, AbstractBlock.Settings.create().mapColor(MapColor.BRIGHT_TEAL).requiresTool().strength(27.5F, 600.0F).instrument(NoteBlockInstrument.HAT).sounds(BlockSoundGroup.GLASS).nonOpaque().allowsSpawning(Blocks::never).solidBlock(Blocks::never).suffocates(Blocks::never).blockVision(Blocks::never));
		crystalGlassPane = Blocks.register(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Data.getVersion().getID(), "crystal_glass_pane")), PaneBlock::new, AbstractBlock.Settings.create().instrument(NoteBlockInstrument.HAT).strength(27.5F, 600.0F).sounds(BlockSoundGroup.GLASS).nonOpaque());
		pulser = Blocks.register(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Data.getVersion().getID(), "pulser")), PulserBlock::new, AbstractBlock.Settings.copy(crystalBlock).nonOpaque());
		lockedChest = Blocks.register(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Data.getVersion().getID(), "locked_chest")), LockedChestBlock::new, AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).instrument(NoteBlockInstrument.BASS).strength(2.5F).sounds(BlockSoundGroup.WOOD).burnable());
	}
}
