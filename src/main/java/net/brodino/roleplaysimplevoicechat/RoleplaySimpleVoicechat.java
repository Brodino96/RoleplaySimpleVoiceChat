package net.brodino.roleplaysimplevoicechat;

import net.brodino.roleplaysimplevoicechat.config.Config;
import net.brodino.roleplaysimplevoicechat.items.ItemManager;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RoleplaySimpleVoicechat implements ModInitializer {

    public static final String MOD_ID = "roleplaysimplevoicechat";
    public static final Logger LOGGER = LoggerFactory.getLogger(RoleplaySimpleVoicechat.MOD_ID);
    public static final Config CONFIG = new Config();

    @Override
    public void onInitialize() {
        RoleplaySimpleVoicechat.LOGGER.info("Initializing RoleplaySimpleVoicechat!");

        ItemManager.initialize();
    }
}
