package dev.brodino.roleplaysimplevoicechat.client;

import de.maxhenkel.voicechat.api.VoicechatPlugin;
import de.maxhenkel.voicechat.api.events.ClientVoicechatConnectionEvent;
import de.maxhenkel.voicechat.api.events.EventRegistration;
import dev.brodino.roleplaysimplevoicechat.RoleplaySimpleVoicechat;

/**
 * Client-side VoicechatPlugin that captures the VoicechatClientApi on connection
 * Registered as an additional "voicechat" entrypoint alongside the server plugin
 */
public class VoicechatClientPlugin implements VoicechatPlugin {

    @Override
    public String getPluginId() { return RoleplaySimpleVoicechat.MOD_ID + "_client"; }

    @Override
    public void registerEvents(EventRegistration registration) {
        registration.registerEvent(ClientVoicechatConnectionEvent.class, event -> {
            if (event.isConnected()) {
                VoiceStateManager.getInstance().setClientApi(event.getVoicechat());
                RoleplaySimpleVoicechat.LOGGER.info("Voice chat connected - client API captured");
            } else {
                VoiceStateManager.getInstance().setClientApi(null);
                RoleplaySimpleVoicechat.LOGGER.info("Voice chat disconnected");
            }
        });
    }
}
