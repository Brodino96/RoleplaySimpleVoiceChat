package net.brodino.roleplaysimplevoicechat;

import net.brodino.roleplaysimplevoicechat.commands.CommandHandler;
import net.brodino.roleplaysimplevoicechat.config.Config;
import net.brodino.roleplaysimplevoicechat.config.ConfigType;
import net.brodino.roleplaysimplevoicechat.items.ItemManager;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RoleplaySimpleVoiceChat implements ModInitializer {

    public static final String MOD_ID = "roleplaysimplevoicechat";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    public static final Config<ConfigType> CONFIG = new Config<>(MOD_ID, MOD_ID, ConfigType.class, ConfigType::new, LOGGER);

    @Override
    public void onInitialize() {
        RoleplaySimpleVoiceChat.LOGGER.info("Initializing RoleplaySimpleVoiceChat!");

        ItemManager.initialize();
        CommandHandler.initialize();
    }

    public static int reloadConfig() {
		return RoleplaySimpleVoiceChat.CONFIG.reload() ? 1 : 0;
	}
}
