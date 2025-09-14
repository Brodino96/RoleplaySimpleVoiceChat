package net.brodino.simplevoicechatroleplay;

import de.maxhenkel.voicechat.api.VoicechatApi;
import de.maxhenkel.voicechat.api.VoicechatConnection;
import de.maxhenkel.voicechat.api.VoicechatPlugin;
import de.maxhenkel.voicechat.api.events.EventRegistration;
import de.maxhenkel.voicechat.api.events.VoiceDistanceEvent;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;

public class Plugin implements VoicechatPlugin {

    @Override
    public String getPluginId() {
        return SimpleVoicechatRoleplay.MOD_ID;
    }

    @Override
    public void initialize(VoicechatApi api) {
        SimpleVoicechatRoleplay.LOGGER.info("Initializing plugin");
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

        if (!player.getMainHandStack().getItem().equals(Items.IRON_INGOT)) {
            return;
        }

        event.setDistance(SimpleVoicechatRoleplay.CONFIG.getExtendedDistance());
    }
}
