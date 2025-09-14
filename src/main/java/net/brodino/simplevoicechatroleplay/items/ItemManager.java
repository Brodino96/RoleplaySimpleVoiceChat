package net.brodino.simplevoicechatroleplay;

import net.brodino.simplevoicechatroleplay.items.VoiceExtender;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class ItemManager {

    public static Item VOICE_EXTENDER;

    private static <T extends Item> T register(String name, T item) {
        return Registry.register(Registry.ITEM, new Identifier(SimpleVoicechatRoleplay.MOD_ID, name), item);
    }

    public static void initialize() {
        SimpleVoicechatRoleplay.LOGGER.info("Initializing Items");
        ItemManager.VOICE_EXTENDER = ItemManager.register("voice_extender", new VoiceExtender(new Item.Settings()
                .group(ItemGroup.TOOLS)
                .rarity(Rarity.EPIC)
                .maxCount(1)
                .fireproof()
                .maxDamage(0)
        ));

        /**
         *         ItemManager.VOICE_EXTENDER = ItemManager.register("voice_extender", new Item(new Item.Settings()
         *                 .group(ItemGroup.TOOLS)
         *                 .rarity(Rarity.EPIC)
         *                 .maxCount(1)
         *                 .fireproof()
         *                 .maxDamage(0)
         *         ));
         */
    }
}
