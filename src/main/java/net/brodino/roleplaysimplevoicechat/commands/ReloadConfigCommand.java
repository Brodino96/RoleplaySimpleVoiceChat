package net.brodino.roleplaysimplevoicechat.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.brodino.roleplaysimplevoicechat.RoleplaySimpleVoiceChat;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class ReloadConfigCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("reloadRpVoiceChat")
                .requires(source -> source.hasPermissionLevel(2))
                .executes(ReloadConfigCommand::execute)
        );
    }

    private static int execute(CommandContext<ServerCommandSource> context) {
        return RoleplaySimpleVoiceChat.reloadConfig();
    }
}
