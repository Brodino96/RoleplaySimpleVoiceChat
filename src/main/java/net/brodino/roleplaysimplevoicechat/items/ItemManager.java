package net.brodino.roleplaysimplevoicechat.items;

import net.brodino.roleplaysimplevoicechat.RoleplaySimpleVoiceChat;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemManager {

    public static Item VOICE_EXTENDER;

    private static <T extends Item> T register(String name, T item) {
        return Registry.register(Registry.ITEM, new Identifier(RoleplaySimpleVoiceChat.MOD_ID, name), item);
    }

    public static void initialize() {
        RoleplaySimpleVoiceChat.LOGGER.info("Initializing Items");
        ItemManager.VOICE_EXTENDER = ItemManager.register("voice_extender", new VoiceExtender());
    }
}
