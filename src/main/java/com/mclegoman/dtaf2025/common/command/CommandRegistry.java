/*
    dtaf2025
    Contributor(s): dannytaylor
    Github: https://github.com/mclegoman/dtaf2025
    Licence: GNU LGPLv3
*/

package com.mclegoman.dtaf2025.common.command;

import com.mclegoman.dtaf2025.common.data.Data;
import com.mclegoman.luminance.client.translation.Translation;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.*;

public class CommandRegistry {
	public static void init() {
		CommandRegistrationCallback.EVENT.register(CommandRegistry::register);
	}
	private static void teleport(Entity target, ServerWorld world) throws CommandSyntaxException {
		double x = target.getX();
		double y = target.getY();
		double z = target.getZ();
		if (!World.isValid(BlockPos.ofFloored(x, y, z))) {
			throw new SimpleCommandExceptionType(Text.translatable("commands.teleport.invalidPosition")).create();
		} else {
			float yaw = MathHelper.wrapDegrees(target.getYaw());
			float pitch = MathHelper.wrapDegrees(target.getPitch());
			if (target.teleport(world, x, y, z, EnumSet.noneOf(PositionFlag.class), yaw, pitch, false)) {
				if (target instanceof PathAwareEntity pathAwareEntity) pathAwareEntity.getNavigation().stop();
			}
		}
	}
	private static int execute(ServerCommandSource source, Collection<? extends Entity> targets, ServerWorld world, Identifier worldId) throws CommandSyntaxException {
		for (Entity target : targets) teleport(target, world);
		if (targets.size() == 1) source.sendFeedback(() -> Translation.getTranslation(Data.version.getID(), "commands.dimension.success.single", new Object[]{targets.iterator().next().getDisplayName(), worldId.toString()}), true);
		else source.sendFeedback(() -> Translation.getTranslation(Data.version.getID(), "commands.dimension.success.multiple", new Object[]{targets.size(), worldId.toString()}), true);
		return targets.size();
	}
	private static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess, CommandManager.RegistrationEnvironment environment) {
		dispatcher.register(CommandManager.literal(getString("dimension"))
				.requires((source) -> source.hasPermissionLevel(2))
				.then(addDimensionArgument().then(addTargetsArgument()))
		);
	}
	private static ArgumentBuilder<ServerCommandSource, ?> addDimensionArgument() {
		return CommandManager.argument("dimension", DimensionArgumentType.dimension())
				.executes((context) -> execute(context.getSource(), Collections.singleton((context.getSource()).getEntityOrThrow()), DimensionArgumentType.getDimensionArgument(context, "dimension"), IdentifierArgumentType.getIdentifier(context, "dimension")));
	}
	private static ArgumentBuilder<ServerCommandSource, ?> addTargetsArgument() {
		return CommandManager.argument("targets", EntityArgumentType.entities()).executes((context) -> execute(context.getSource(), EntityArgumentType.getEntities(context, "targets"), DimensionArgumentType.getDimensionArgument(context, "dimension"), IdentifierArgumentType.getIdentifier(context, "dimension")));
	}
	public static String getString(Identifier id) {
		return id.toString();
	}
	public static String getString(String namespace, String key) {
		return getString(Identifier.of(namespace, key));
	}
	public static String getString(String key) {
		return getString(Data.version.getID(), key);
	}
}