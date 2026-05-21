package net.brodino.roleplaysimplevoicechat;

import net.minecraft.util.Identifier;

public enum VoiceState {
    WHISPER("whisper"),
    NORMAL("normal"),
    SHOUTING("shout");

    private final Identifier disabledTextureId;
    private final Identifier enabledTextureId;

    VoiceState(String name) {
        this.disabledTextureId = new Identifier(RoleplaySimpleVoicechat.MOD_ID, "textures/hud/" + name + "_disabled.png");
        this.enabledTextureId  = new Identifier(RoleplaySimpleVoicechat.MOD_ID, "textures/hud/" + name + "_enabled.png");
    }

    public Identifier getDisabledTextureId() { return this.disabledTextureId; }
    public Identifier getEnabledTextureId()  { return this.enabledTextureId; }

    /** Returns the next state in the carousel: WHISPER -> NORMAL -> SHOUTING -> WHISPER */
    public VoiceState next() {
        VoiceState[] values = VoiceState.values();
        return values[(this.ordinal() + 1) % values.length];
    }
}
