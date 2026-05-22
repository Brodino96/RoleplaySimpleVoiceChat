package net.brodino.roleplaysimplevoicechat.shared;

import net.minecraft.util.Identifier;

public enum VoiceStates {
    WHISPER(new VoiceState("whisper")),
    NORMAL(new VoiceState("normal")),
    SHOUTING(new VoiceState("shout"));

    private final VoiceState state;

    VoiceStates(VoiceState state) {
        this.state = state;
    }

    public Identifier getOffTextureId() { return this.state.getOffTextureId(); }
    public Identifier getOnTextureId() { return this.state.getOnTextureId(); }
    public Identifier getSlashedTextureId() { return this.state.getSlashedTextureId(); }

    /** Returns the next state in the carousel: WHISPER -> NORMAL -> SHOUTING -> WHISPER */
    public VoiceStates next() {
        VoiceStates[] values = VoiceStates.values();
        return values[(this.ordinal() + 1) % values.length];
    }
}
