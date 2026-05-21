package net.brodino.roleplaysimplevoicechat;

import de.maxhenkel.voicechat.api.VoicechatApi;
import de.maxhenkel.voicechat.api.events.EventRegistration;
import de.maxhenkel.voicechat.api.events.VoiceDistanceEvent;

import java.util.UUID;

public class VoicechatPlugin implements de.maxhenkel.voicechat.api.VoicechatPlugin {

    @Override
    public String getPluginId() {
        return RoleplaySimpleVoicechat.MOD_ID;
    }

    @Override
    public void initialize(VoicechatApi api) {
        RoleplaySimpleVoicechat.LOGGER.info("Initializing RoleplaySimpleVoicechat voice chat plugin");
    }

    @Override
    public void registerEvents(EventRegistration registration) {
        registration.registerEvent(VoiceDistanceEvent.class, this::onVoiceDistance);
    }

    private void onVoiceDistance(VoiceDistanceEvent event) {
        UUID playerId = event.getSenderConnection().getPlayer().getUuid();
        VoiceState state = RoleplaySimpleVoicechat.PLAYER_VOICE_STATES.getOrDefault(playerId, VoiceState.NORMAL);

        float distance = switch (state) {
            case WHISPER -> RoleplaySimpleVoicechat.CONFIG.getData().getWhisperDistance();
            case NORMAL -> RoleplaySimpleVoicechat.CONFIG.getData().getNormalDistance();
            case SHOUTING -> RoleplaySimpleVoicechat.CONFIG.getData().getShoutDistance();
        };

        event.setDistance(distance);
    }
}
