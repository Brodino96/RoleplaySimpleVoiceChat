package net.brodino.roleplaysimplevoicechat.client;

import net.brodino.roleplaysimplevoicechat.RoleplaySimpleVoicechat;
import net.fabricmc.api.ClientModInitializer;

public class RoleplaySimpleVoicechatClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        RoleplaySimpleVoicechat.LOGGER.info("Initializing RoleplaySimpleVoicechat Client");
    }
}
