package net.brodino.roleplaysimplevoicechat;

import net.brodino.roleplaysimplevoicechat.commands.CommandHandler;
import net.brodino.roleplaysimplevoicechat.config.Config;
import net.brodino.roleplaysimplevoicechat.items.ItemManager;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class RoleplaySimpleVoiceChat implements ModInitializer {

    public static final String MOD_ID = "roleplaysimplevoicechat";
    public static final Logger LOGGER = LoggerFactory.getLogger(RoleplaySimpleVoiceChat.MOD_ID);
    public static final Config CONFIG = new Config();

    @Override
    public void onInitialize() {
        RoleplaySimpleVoiceChat.LOGGER.info("Initializing RoleplaySimpleVoiceChat!");

        ItemManager.initialize();
        CommandHandler.initialize();
    }

    public static int reloadConfig() {
        try {
            RoleplaySimpleVoiceChat.CONFIG.reload();
            return 1;
        } catch (IOException e) {
            RoleplaySimpleVoiceChat.LOGGER.error("Failed to reload config file: ", e);
            return 0;
        }
    }
}
