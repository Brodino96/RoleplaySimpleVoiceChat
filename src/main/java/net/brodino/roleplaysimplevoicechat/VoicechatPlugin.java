package net.brodino.roleplaysimplevoicechat;

import de.maxhenkel.voicechat.api.VoicechatApi;
import de.maxhenkel.voicechat.api.events.EventRegistration;
import de.maxhenkel.voicechat.api.events.VoiceDistanceEvent;
import net.brodino.roleplaysimplevoicechat.effects.EffectsManager;
import net.brodino.roleplaysimplevoicechat.items.ItemManager;
import net.brodino.roleplaysimplevoicechat.shared.VoiceStates;
import net.minecraft.server.network.ServerPlayerEntity;

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
        ServerPlayerEntity player = (ServerPlayerEntity) (Object) event.getSenderConnection().getPlayer().getPlayer();

        if (player.isDead() || player.hasStatusEffect(EffectsManager.NEGATE_SPEECH)) {
            event.cancel();
            return;
        }

        if (player.getMainHandStack().getItem().equals(ItemManager.VOICE_EXTENDER) || player.hasStatusEffect(EffectsManager.EXTEND_SPEECH)) {
            event.setDistance(RoleplaySimpleVoicechat.CONFIG.getData().getVoiceExtenderDistance());
            return;
        }

        VoiceStates state = RoleplaySimpleVoicechat.PLAYER_VOICE_STATES.getOrDefault(player.getUuid(), VoiceStates.NORMAL);
        float distance = switch (state) {
            case WHISPER -> RoleplaySimpleVoicechat.CONFIG.getData().getWhisperDistance();
            case NORMAL -> RoleplaySimpleVoicechat.CONFIG.getData().getNormalDistance();
            case SHOUTING -> RoleplaySimpleVoicechat.CONFIG.getData().getShoutDistance();
        };

        event.setDistance(distance);
    }
}
