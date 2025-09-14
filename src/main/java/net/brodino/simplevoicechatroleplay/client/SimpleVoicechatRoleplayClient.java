package net.brodino.simplevoicechatroleplay.client;

import net.brodino.simplevoicechatroleplay.SimpleVoicechatRoleplay;
import net.fabricmc.api.ClientModInitializer;

public class SimpleVoicechatRoleplayClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        SimpleVoicechatRoleplay.LOGGER.info("Initializing SimpleVoicechatRoleplay Client");
    }
}
