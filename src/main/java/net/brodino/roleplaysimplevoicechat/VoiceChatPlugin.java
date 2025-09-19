package net.brodino.roleplaysimplevoicechat;

import de.maxhenkel.voicechat.api.VoicechatApi;
import de.maxhenkel.voicechat.api.VoicechatConnection;
import de.maxhenkel.voicechat.api.VoicechatPlugin;
import de.maxhenkel.voicechat.api.events.EventRegistration;
import de.maxhenkel.voicechat.api.events.VoiceDistanceEvent;
import net.brodino.roleplaysimplevoicechat.items.ItemManager;
import net.minecraft.server.network.ServerPlayerEntity;

public class VoiceChatPlugin implements VoicechatPlugin {

    @Override
    public String getPluginId() {
        return RoleplaySimpleVoiceChat.MOD_ID;
    }

    @Override
    public void initialize(VoicechatApi api) {
        RoleplaySimpleVoiceChat.LOGGER.info("Initializing plugin");
    }

    @Override
    public void registerEvents(EventRegistration registration) {
        registration.registerEvent(VoiceDistanceEvent.class, this::onDistanceEvent);
    }

    private void onDistanceEvent(VoiceDistanceEvent event) {

        VoicechatConnection senderConnection = event.getSenderConnection();

        if (senderConnection == null) {
            return;
        }

        ServerPlayerEntity player = (ServerPlayerEntity) senderConnection.getPlayer().getPlayer();

        if (player == null) {
            return;
        }

        float calculatedDistance = event.getDistance();

        if (player.isSneaking()) {
            calculatedDistance = RoleplaySimpleVoiceChat.CONFIG.getSneakingDistance();
        }

        if (player.getMainHandStack().getItem().equals(ItemManager.VOICE_EXTENDER)) {
            calculatedDistance = RoleplaySimpleVoiceChat.CONFIG.getExtendedDistance();
        }

        event.setDistance(calculatedDistance);
    }
}
