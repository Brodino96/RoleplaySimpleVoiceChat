package net.brodino.roleplaysimplevoicechat.client;

import net.brodino.roleplaysimplevoicechat.RoleplaySimpleVoiceChat;
import net.fabricmc.api.ClientModInitializer;

public class RoleplaySimpleVoiceChatClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        RoleplaySimpleVoiceChat.LOGGER.info("Initializing RoleplaySimpleVoiceChat Client");
    }
}
