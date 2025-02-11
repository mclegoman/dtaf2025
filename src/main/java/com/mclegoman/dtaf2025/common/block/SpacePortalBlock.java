/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.common.block;

import com.mclegoman.dtaf2025.common.sound.SoundRegistry;
import com.mclegoman.dtaf2025.common.util.Tags;
import com.mclegoman.dtaf2025.common.world.dimension.DimensionRegistry;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.tick.ScheduledTickView;

public class SpacePortalBlock extends NetherPortalBlock {
	public SpacePortalBlock(Settings settings) {
		super(settings);
	}
	protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return Block.createCuboidShape(1.0, 0.0, 1.0, 15.0, 16.0, 15.0);
	}
	public TeleportTarget createTeleportTarget(ServerWorld world, Entity entity, BlockPos pos) {
		ServerWorld spaceStation = world.getServer().getWorld(DimensionRegistry.spaceStation.getWorld());
		if (world.getRegistryKey() == World.OVERWORLD && spaceStation != null) return new TeleportTarget(spaceStation, new Vec3d(2, 65, 2), Vec3d.ZERO, 0.0F, 0.0F, PositionFlag.combine(PositionFlag.DELTA, PositionFlag.ROT), (e) -> {});
		else return null;
	}
	protected BlockState getStateForNeighborUpdate(BlockState state, WorldView world, ScheduledTickView tickView, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, Random random) {
		return !world.getBlockState(pos.down()).isIn(Tags.Block.spacePortalSurfaces) ? Blocks.AIR.getDefaultState() : state;
	}

	public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
		if (random.nextInt(16) == 0) world.playSound((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, SoundRegistry.spacePortalAmbient, SoundCategory.BLOCKS, 0.5F, random.nextFloat() * 0.4F + 0.8F, false);
		for (int index = 0; index < 16; ++index) {
			double x = (double)pos.getX() + random.nextDouble();
			double y = (double)pos.getY() + random.nextDouble();
			double z = (double)pos.getZ() + random.nextDouble();
			world.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0, 0.0, 0.0);
		}
	}
}
