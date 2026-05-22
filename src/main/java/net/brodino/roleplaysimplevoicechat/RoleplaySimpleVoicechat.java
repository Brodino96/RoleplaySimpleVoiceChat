package net.brodino.roleplaysimplevoicechat;

import net.brodino.roleplaysimplevoicechat.commands.CommandHandler;
import net.brodino.roleplaysimplevoicechat.config.Config;
import net.brodino.roleplaysimplevoicechat.config.ConfigType;
import net.brodino.roleplaysimplevoicechat.effects.EffectsManager;
import net.brodino.roleplaysimplevoicechat.items.ItemManager;
import net.brodino.roleplaysimplevoicechat.shared.VoiceStates;
import net.fabricmc.api.ModInitializer;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class RoleplaySimpleVoicechat implements ModInitializer {

    public static final String MOD_ID = "roleplaysimplevoicechat";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final Config<ConfigType> CONFIG = new Config<>(MOD_ID, "config", ConfigType.class, ConfigType::new, LOGGER);

    public static final Map<UUID, VoiceStates> PLAYER_VOICE_STATES = new ConcurrentHashMap<>();

    @Override
    public void onInitialize() {
        RoleplaySimpleVoicechat.LOGGER.info("Initializing RoleplaySimpleVoicechat!");
        CommandHandler.initialize();
        ItemManager.initialize();
        EffectsManager.initialize();
        ServerEventHandler.initialize();
    }

    public static void handleVoiceStateChange(ServerPlayerEntity player, PacketByteBuf buf) {
        String stateName = buf.readString(32);
        try {
            VoiceStates state = VoiceStates.valueOf(stateName);
            PLAYER_VOICE_STATES.put(player.getUuid(), state);
        } catch (IllegalArgumentException e) {
            LOGGER.error("Received invalid VoiceState \"{}\" from player {}", stateName, player);
        }
    }

    public static int reloadConfig() { return RoleplaySimpleVoicechat.CONFIG.reload() ? 1 : 0; }

    public static float getPlayerVoiceDistance(ServerPlayerEntity player) {
        if (player.isDead()) {
            return CONFIG.getData().getNormalDistance();
        }

        if (player.hasStatusEffect(EffectsManager.NEGATED_SPEECH)) {
            return 0.0F;
        }

        if (player.hasStatusEffect(EffectsManager.EXTENDED_SPEECH) || player.getMainHandStack().getItem().equals(ItemManager.VOICE_EXTENDER)) {
           return CONFIG.getData().getExtendedDistance();
       }

       return switch (RoleplaySimpleVoicechat.PLAYER_VOICE_STATES.getOrDefault(player.getUuid(), VoiceStates.NORMAL)) {
           case WHISPER -> CONFIG.getData().getWhisperDistance();
           case NORMAL -> CONFIG.getData().getNormalDistance();
           case SHOUTING -> CONFIG.getData().getShoutDistance();
           case EXTENDED -> CONFIG.getData().getExtendedDistance();
       };
    }
}
