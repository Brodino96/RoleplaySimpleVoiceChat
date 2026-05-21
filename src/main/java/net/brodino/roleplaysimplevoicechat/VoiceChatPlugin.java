package net.brodino.roleplaysimplevoicechat;

import de.maxhenkel.voicechat.api.VoicechatApi;
import de.maxhenkel.voicechat.api.VoicechatPlugin;

public class VoiceChatPlugin implements VoicechatPlugin {

    @Override
    public String getPluginId() {
        return RoleplaySimpleVoiceChat.MOD_ID;
    }

    @Override
    public void initialize(VoicechatApi api) {
        RoleplaySimpleVoiceChat.LOGGER.info("Initializing plugin");
    }
}
