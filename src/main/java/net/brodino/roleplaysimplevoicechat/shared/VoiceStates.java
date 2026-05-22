package net.brodino.roleplaysimplevoicechat.shared;

import net.brodino.roleplaysimplevoicechat.RoleplaySimpleVoicechat;
import net.minecraft.util.Identifier;

public enum VoiceStates {
    WHISPER("whisper"),
    NORMAL("normal"),
    SHOUTING("shout"),
    EXTENDED("extended");

    private final Identifier offTextureId;
    private final Identifier onTextureId;
    private final Identifier slashedTextureId;

    private static final VoiceStates[] states = { WHISPER, NORMAL, SHOUTING };

    VoiceStates(String name) {
        this.offTextureId = new Identifier(RoleplaySimpleVoicechat.MOD_ID, "textures/icons/" + name + "_off.png");
        this.onTextureId = new Identifier(RoleplaySimpleVoicechat.MOD_ID, "textures/icons/" + name + "_on.png");
        this.slashedTextureId = new Identifier(RoleplaySimpleVoicechat.MOD_ID, "textures/icons/" + name + "_slashed.png");
    }

    public Identifier getOffTextureId() { return this.offTextureId; }
    public Identifier getOnTextureId() { return this.onTextureId; }
    public Identifier getSlashedTextureId() { return this.slashedTextureId; }

    /** Returns the next state in the carousel: WHISPER -> NORMAL -> SHOUTING -> WHISPER */
    public VoiceStates next() {
        for (int i = 0; i < states.length; i++) {
            if (states[i].equals(this)) {
                return states[(i + 1) % states.length];
            }
        }

        return this;
    }
}
