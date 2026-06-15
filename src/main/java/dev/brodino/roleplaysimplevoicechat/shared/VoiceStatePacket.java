package dev.brodino.roleplaysimplevoicechat.shared;

import dev.brodino.roleplaysimplevoicechat.RoleplaySimpleVoicechat;
import net.minecraft.util.Identifier;

public class VoiceStatePacket {
    public static final Identifier CHANNEL = new Identifier(RoleplaySimpleVoicechat.MOD_ID, "voice_state");
}
