package dev.brodino.roleplaysimplevoicechat.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class CommandHandler {

    public static void initialize() {
        CommandRegistrationCallback.EVENT.register((dispatcher, ra, e) -> {
            registerCommand(dispatcher);
        });
    }

    private static void registerCommand(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("rpsvc")
            .requires(src -> src.hasPermissionLevel(2))
            .then(ReloadConfigCommand.getCommand())
        );
    }
}
