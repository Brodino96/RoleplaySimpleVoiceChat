package net.brodino.roleplaysimplevoicechat.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.brodino.roleplaysimplevoicechat.RoleplaySimpleVoicechat;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

public class ReloadConfigCommand {

    public static LiteralArgumentBuilder<ServerCommandSource> getCommand() {
        return CommandManager.literal("reloadConfig")
            .executes(ReloadConfigCommand::execute);
    }

    private static int execute(CommandContext<ServerCommandSource> context) {
        return RoleplaySimpleVoicechat.reloadConfig();
    }
}
