package com.mclegoman.dtaf2025.common.block;

import com.mclegoman.dtaf2025.common.data.Data;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.block.WireOrientation;
import org.jetbrains.annotations.Nullable;

public class PulserBlock extends Block {
	public static final MapCodec<PulserBlock> codec = createCodec(PulserBlock::new);
	public static final BooleanProperty pulsed;
	protected MapCodec<PulserBlock> getCodec() {
		return codec;
	}
	public PulserBlock(Settings settings) {
		super(settings);
		this.setDefaultState(this.stateManager.getDefaultState().with(pulsed, false));
	}
	protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
		builder.add(pulsed);
	}
	protected boolean emitsRedstonePower(BlockState state) {
		return false;
	}
	protected boolean hasComparatorOutput(BlockState state) {
		return true;
	}
	protected int getComparatorOutput(BlockState state, World world, BlockPos pos) {
		return state.get(pulsed) ? world.getReceivedRedstonePower(pos) : 0;
	}
	protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		world.setBlockState(pos, state.with(pulsed, world.isReceivingRedstonePower(pos) && !state.get(pulsed)), 2);
		world.updateNeighbors(pos, this);
		world.scheduleBlockTick(pos, this, 4);
	}
	protected void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify) {
		if (!world.getBlockTickScheduler().isQueued(pos, this)) {
			world.updateNeighbors(pos, this);
			world.scheduleBlockTick(pos, this, 4);
		}
	}
	public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
		super.onPlaced(world, pos, state, placer, itemStack);
		if (!world.getBlockTickScheduler().isQueued(pos, this)) {
			world.updateNeighbors(pos, this);
			world.scheduleBlockTick(pos, this, 4);
		}
	}
	protected void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, @Nullable WireOrientation wireOrientation, boolean notify) {
		super.neighborUpdate(state, world, pos, sourceBlock, wireOrientation, notify);
		if (!world.getBlockTickScheduler().isQueued(pos, this)) {
			world.scheduleBlockTick(pos, this, 4);
		}
	}
	static {
		pulsed = BooleanProperty.of(Data.getVersion().getID() + "_pulsed");
	}
}
