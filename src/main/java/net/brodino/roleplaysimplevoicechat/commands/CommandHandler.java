package net.brodino.roleplaysimplevoicechat.commands;

import net.brodino.roleplaysimplevoicechat.RoleplaySimpleVoiceChat;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

public class CommandHandler {

    public static void initialize() {
        ServerLifecycleEvents.START_DATA_PACK_RELOAD.register((server, resManager) -> {
            RoleplaySimpleVoiceChat.reloadConfig();
        });

        CommandRegistrationCallback.EVENT.register((dispatcher, ra, e) -> {
            ReloadConfigCommand.register(dispatcher);
        });
    }
}
