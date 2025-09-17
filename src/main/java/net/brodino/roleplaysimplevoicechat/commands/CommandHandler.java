package net.brodino.roleplaysimplevoicechat.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.brodino.roleplaysimplevoicechat.RoleplaySimpleVoicechat;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.command.CommandManager.RegistrationEnvironment;

public class CommandHandler {

    public static void initialize() {
        ServerLifecycleEvents.START_DATA_PACK_RELOAD.register((server, resManager) -> {
            RoleplaySimpleVoicechat.reloadConfig();
        });

        CommandRegistrationCallback.EVENT.register((
                CommandDispatcher<ServerCommandSource> dispatcher,
                CommandRegistryAccess registryAccess,
                RegistrationEnvironment environment
        ) -> {
            ReloadConfigCommand.register(dispatcher);
        });
    }
}
