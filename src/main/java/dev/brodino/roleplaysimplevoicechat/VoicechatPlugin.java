package dev.brodino.roleplaysimplevoicechat;

import de.maxhenkel.voicechat.api.VoicechatApi;
import de.maxhenkel.voicechat.api.VoicechatConnection;
import de.maxhenkel.voicechat.api.events.EntitySoundPacketEvent;
import de.maxhenkel.voicechat.api.events.EventRegistration;
import de.maxhenkel.voicechat.api.events.VoiceDistanceEvent;
import dev.brodino.roleplaysimplevoicechat.effects.EffectsManager;
import net.minecraft.server.network.ServerPlayerEntity;

public class VoicechatPlugin implements de.maxhenkel.voicechat.api.VoicechatPlugin {

    @Override
    public String getPluginId() { return RoleplaySimpleVoicechat.MOD_ID; }

    @Override
    public void initialize(VoicechatApi api) {
        RoleplaySimpleVoicechat.LOGGER.info("Initializing RoleplaySimpleVoicechat voice chat plugin");
    }

    @Override
    public void registerEvents(EventRegistration registration) {
        registration.registerEvent(VoiceDistanceEvent.class, this::onVoiceDistance);
        registration.registerEvent(EntitySoundPacketEvent.class, this::onEntitySoundPacket);
    }

    private void onEntitySoundPacket(EntitySoundPacketEvent event) {
        VoicechatConnection connection = event.getSenderConnection();
        if (connection == null) {
            return;
        }

        ServerPlayerEntity player = (ServerPlayerEntity) (Object) connection.getPlayer().getPlayer();

        if (player.isDead() || player.hasStatusEffect(EffectsManager.NEGATED_SPEECH)) {
            event.cancel();
        }
    }

    private void onVoiceDistance(VoiceDistanceEvent event) {
        ServerPlayerEntity player = (ServerPlayerEntity) (Object) event.getSenderConnection().getPlayer().getPlayer();
        event.setDistance(RoleplaySimpleVoicechat.getPlayerVoiceDistance(player));
    }
}
