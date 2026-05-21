package net.brodino.roleplaysimplevoicechat;

import net.brodino.roleplaysimplevoicechat.commands.CommandHandler;
import net.brodino.roleplaysimplevoicechat.config.Config;
import net.brodino.roleplaysimplevoicechat.config.ConfigType;
import net.brodino.roleplaysimplevoicechat.items.ItemManager;
import net.brodino.roleplaysimplevoicechat.network.VoiceStatePacket;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class RoleplaySimpleVoicechat implements ModInitializer {

    public static final String MOD_ID = "roleplaysimplevoicechat";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final Config<ConfigType> CONFIG = new Config<>(MOD_ID, MOD_ID, ConfigType.class, ConfigType::new, LOGGER);

    public static final Map<UUID, VoiceState> PLAYER_VOICE_STATES = new ConcurrentHashMap<>();

    @Override
    public void onInitialize() {
        RoleplaySimpleVoicechat.LOGGER.info("Initializing RoleplaySimpleVoicechat!");

        ItemManager.initialize();
        CommandHandler.initialize();
        this.registerPacketReceivers();
    }

    private void registerPacketReceivers() {
        ServerPlayNetworking.registerGlobalReceiver(VoiceStatePacket.CHANNEL, (s, player, handler, buf, rs) -> {
            String stateName = buf.readString(32);
            try {
                VoiceState state = VoiceState.valueOf(stateName);
                PLAYER_VOICE_STATES.put(player.getUuid(), state);
                LOGGER.debug("Player {} set voice state to {}", player.getName().getString(), state);
            } catch (IllegalArgumentException e) {
                LOGGER.warn("Received invalid VoiceState '{}' from player {}", stateName, player.getName().getString());
            }
        });
    }

    public static int reloadConfig() {
        return RoleplaySimpleVoicechat.CONFIG.reload() ? 1 : 0;
    }
}
