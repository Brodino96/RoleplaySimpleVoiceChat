package net.brodino.simplevoicechatroleplay.items;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class VoiceExtender extends Item {
    public VoiceExtender() {
        super(new Item.Settings()
                .group(ItemGroup.TOOLS)
                .rarity(Rarity.EPIC)
                .maxCount(1)
                .fireproof()
                .maxDamage(0)
        );
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("item.simplevoicechatroleplay.voice_extender.tooltip"));
    }


}
