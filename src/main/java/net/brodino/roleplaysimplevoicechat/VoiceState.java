package net.brodino.roleplaysimplevoicechat;

import net.minecraft.util.Identifier;

public enum VoiceState {
    WHISPER("whisper"),
    NORMAL("normal"),
    SHOUTING("shout");

    private final Identifier disabledTextureId;
    private final Identifier enabledTextureId;
    private final Identifier mutedTextureId;

    VoiceState(String name) {
        this.disabledTextureId = new Identifier(RoleplaySimpleVoicechat.MOD_ID, "textures/icons/" + name + "_disabled.png");
        this.enabledTextureId = new Identifier(RoleplaySimpleVoicechat.MOD_ID, "textures/icons/" + name + "_enabled.png");
        this.mutedTextureId = new Identifier(RoleplaySimpleVoicechat.MOD_ID, "textures/icons/" + name + "_muted.png");
    }

    public Identifier getDisabledTextureId() { return this.disabledTextureId; }
    public Identifier getEnabledTextureId() { return this.enabledTextureId; }
    public Identifier getMutedTextureId() { return this.mutedTextureId; }

    /** Returns the next state in the carousel: WHISPER -> NORMAL -> SHOUTING -> WHISPER */
    public VoiceState next() {
        VoiceState[] values = VoiceState.values();
        return values[(this.ordinal() + 1) % values.length];
    }
}
