/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.common.block;

import com.mclegoman.dtaf2025.common.item.ItemRegistry;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Iterator;

public class SpaceAirBlock extends Block {
	public static final MapCodec<SpaceAirBlock> codec = createCodec(SpaceAirBlock::new);
	public SpaceAirBlock(Settings settings) {
		super(settings);
	}
	public MapCodec<? extends SpaceAirBlock> getCodec() {
		return codec;
	}
	protected BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.INVISIBLE;
	}
	protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return context.isHolding(ItemRegistry.spaceAir) ? VoxelShapes.fullCube() : VoxelShapes.empty();
	}
	protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		super.scheduledTick(state, world, pos, random);
		updateNonSpaceAir(world, pos);
	}
	public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
		super.onPlaced(world, pos, state, placer, itemStack);
		world.scheduleBlockTick(pos, this, 1);
	}
	protected BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, Random random) {
		if (neighborState.isIn(BlockTags.AIR)) tickView.scheduleBlockTick(pos, this, 1);
		return super.getStateForNeighborUpdate(state, world, tickView, pos, direction, neighborPos, neighborState, random);
	}
	private void updateNonSpaceAir(ServerWorld world, BlockPos pos) {
		Iterator<Direction> directions = getDirectionIterator();
		while(directions.hasNext()) updateAir(world, pos, directions.next());
	}
	private void updateAir(ServerWorld world, BlockPos pos, Direction direction) {
		BlockPos blockPos = pos.offset(direction);
		BlockState blockState = world.getBlockState(blockPos);
		if (blockState.isIn(BlockTags.AIR) && isSurrounded(world, blockPos)) {
			world.setBlockState(blockPos, BlockRegistry.spaceAir.getDefaultState());
		}
	}
	private boolean isSurrounded(ServerWorld world, BlockPos pos) {
		Iterator<Direction> directions = getDirectionIterator();
		int max = 6;
		int isSpaceAir = 0;
		while(directions.hasNext()) {
			BlockPos blockPos = pos.offset(directions.next());
			BlockState blockState = world.getBlockState(blockPos);
			if (blockState.isOf(BlockRegistry.spaceAir)) isSpaceAir++;
			else if (!blockState.isIn(BlockTags.AIR)) max--;
		}
		return ((Math.clamp(isSpaceAir, 1, 6)/Math.clamp(max, 1, 6)) * 100) == 100;
	}
	public Iterator<Direction> getDirectionIterator() {
		return Arrays.stream(new Direction[]{Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST, Direction.UP, Direction.DOWN}).iterator();
	}
}
