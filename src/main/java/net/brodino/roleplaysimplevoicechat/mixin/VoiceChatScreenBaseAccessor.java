package net.brodino.roleplaysimplevoicechat.mixin;

import de.maxhenkel.voicechat.gui.VoiceChatScreenBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = VoiceChatScreenBase.class, remap = false)
public interface VoiceChatScreenBaseAccessor {
    @Accessor("xSize") int getXSize();
    @Accessor("ySize") int getYSize();
    @Accessor("ySize") void setYSize(int ySize);
}
