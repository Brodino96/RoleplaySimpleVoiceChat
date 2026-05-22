package net.brodino.roleplaysimplevoicechat.shared;

import net.brodino.roleplaysimplevoicechat.RoleplaySimpleVoicechat;
import net.minecraft.util.Identifier;

public enum VoiceState {
    WHISPER("whisper"),
    NORMAL("normal"),
    SHOUTING("shout");

    private final Identifier offTextureId;
    private final Identifier onTextureId;
    private final Identifier slashedTextureId;

    VoiceState(String name) {
        this.offTextureId = new Identifier(RoleplaySimpleVoicechat.MOD_ID, "textures/icons/" + name + "_off.png");
        this.onTextureId = new Identifier(RoleplaySimpleVoicechat.MOD_ID, "textures/icons/" + name + "_on.png");
        this.slashedTextureId = new Identifier(RoleplaySimpleVoicechat.MOD_ID, "textures/icons/" + name + "_slashed.png");
    }

    public Identifier getOffTextureId() { return this.offTextureId; }
    public Identifier getOnTextureId() { return this.onTextureId; }
    public Identifier getSlashedTextureId() { return this.slashedTextureId; }

    /** Returns the next state in the carousel: WHISPER -> NORMAL -> SHOUTING -> WHISPER */
    public VoiceState next() {
        VoiceState[] values = VoiceState.values();
        return values[(this.ordinal() + 1) % values.length];
    }
}
