package net.brodino.simplevoicechatroleplay;

import net.brodino.simplevoicechatroleplay.config.Config;
import net.brodino.simplevoicechatroleplay.items.ItemManager;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleVoicechatRoleplay implements ModInitializer {

    public static final String MOD_ID = "simplevoicechatroleplay";
    public static final Logger LOGGER = LoggerFactory.getLogger(SimpleVoicechatRoleplay.MOD_ID);
    public static final Config CONFIG = new Config();

    @Override
    public void onInitialize() {
        SimpleVoicechatRoleplay.LOGGER.info("Initializing SimpleVoicechatRoleplay!");

        ItemManager.initialize();
    }
}
