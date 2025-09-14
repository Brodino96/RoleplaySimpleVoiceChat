package net.brodino.roleplaysimplevoicechat.items;

import net.brodino.roleplaysimplevoicechat.RoleplaySimpleVoicechat;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemManager {

    public static Item VOICE_EXTENDER;

    private static <T extends Item> T register(String name, T item) {
        return Registry.register(Registry.ITEM, new Identifier(RoleplaySimpleVoicechat.MOD_ID, name), item);
    }

    public static void initialize() {
        RoleplaySimpleVoicechat.LOGGER.info("Initializing Items");
        ItemManager.VOICE_EXTENDER = ItemManager.register("voice_extender", new VoiceExtender());
    }
}
