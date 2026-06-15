package dev.brodino.roleplaysimplevoicechat.mixin.client;

import de.maxhenkel.voicechat.gui.VoiceChatScreenBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = VoiceChatScreenBase.class, remap = false)
public interface VoicechatScreenAccessor {
    @Accessor("xSize") int getXSize();
    @Accessor("ySize") void setYSize(int ySize);
}
