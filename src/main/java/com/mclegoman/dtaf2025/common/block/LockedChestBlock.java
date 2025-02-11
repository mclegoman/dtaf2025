/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.common.block;

import com.mclegoman.dtaf2025.common.data.Data;
import com.mclegoman.dtaf2025.common.network.Packets;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stat;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.*;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;

public class LockedChestBlock extends Block implements Waterloggable {
	public static final MapCodec<LockedChestBlock> codec = createCodec(LockedChestBlock::new);
	public static final EnumProperty<Direction> facing;
	public static final BooleanProperty waterlogged;
	protected static final VoxelShape shape;

	public MapCodec<? extends LockedChestBlock> getCodec() {
		return codec;
	}

	public LockedChestBlock(AbstractBlock.Settings settings) {
		super(settings);
		this.setDefaultState(this.stateManager.getDefaultState().with(facing, Direction.NORTH).with(waterlogged, false));
	}

	protected BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}

	protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return shape;
	}

	public BlockState getPlacementState(ItemPlacementContext ctx) {
		Direction direction = ctx.getHorizontalPlayerFacing().getOpposite();
		FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
		return this.getDefaultState().with(facing, direction).with(waterlogged, fluidState.getFluid() == Fluids.WATER);
	}

	protected FluidState getFluidState(BlockState state) {
		return state.get(waterlogged) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
	}

	protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
		ItemScatterer.onStateReplaced(state, newState, world, pos);
		super.onStateReplaced(state, world, pos, newState, moved);
	}

	protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
		if (world.isClient) return ActionResult.SUCCESS;
		else {
			Packets.sendOpenScreen((ServerPlayerEntity) player, Identifier.of(Data.getVersion().getID(), "locked_chest"));
			world.playSound(null, pos, SoundEvents.BLOCK_CHEST_LOCKED, SoundCategory.BLOCKS, 1.0F, 1.0F);
			player.incrementStat(this.getOpenStat());
			PiglinBrain.onGuardedBlockInteracted((ServerWorld) world, player, true);
			return ActionResult.CONSUME;
		}
	}

	protected Stat<Identifier> getOpenStat() {
		return Stats.CUSTOM.getOrCreateStat(Stats.OPEN_CHEST);
	}

	protected BlockState rotate(BlockState state, BlockRotation rotation) {
		return state.with(facing, rotation.rotate(state.get(facing)));
	}

	protected BlockState mirror(BlockState state, BlockMirror mirror) {
		return state.rotate(mirror.getRotation(state.get(facing)));
	}

	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(facing, waterlogged);
	}

	protected boolean canPathfindThrough(BlockState state, NavigationType type) {
		return false;
	}

	protected BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, Random random) {
		if (state.get(waterlogged)) tickView.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
		return super.getStateForNeighborUpdate(state, world, tickView, pos, direction, neighborPos, neighborState, random);
	}

	static {
		facing = HorizontalFacingBlock.FACING;
		waterlogged = Properties.WATERLOGGED;
		shape = Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 14.0, 15.0);
	}
}
