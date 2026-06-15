package dev.brodino.roleplaysimplevoicechat.shared;

import dev.brodino.roleplaysimplevoicechat.RoleplaySimpleVoicechat;
import net.minecraft.util.Identifier;

public enum VoiceStates {
    WHISPER("whisper"),
    NORMAL("normal"),
    SHOUTING("shout"),
    EXTENDED("extended");

    private final Identifier offSpriteId;
    private final Identifier onSpriteId;
    private final Identifier disabledSpriteId;

    private static final VoiceStates[] states = { WHISPER, NORMAL, SHOUTING };

    VoiceStates(String name) {
        this.offSpriteId = new Identifier(RoleplaySimpleVoicechat.MOD_ID, name + "_off");
        this.onSpriteId = new Identifier(RoleplaySimpleVoicechat.MOD_ID, name + "_on");
        this.disabledSpriteId = new Identifier(RoleplaySimpleVoicechat.MOD_ID, name + "_disabled");
    }

    public Identifier getOffSpriteId() { return this.offSpriteId; }
    public Identifier getOnSpriteId() { return this.onSpriteId; }
    public Identifier getDisabledSpriteId() { return this.disabledSpriteId; }

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
