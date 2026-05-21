package net.brodino.roleplaysimplevoicechat;

import net.minecraft.util.Identifier;

public enum VoiceState {
	WHISPER("whisper"),
	NORMAL("normal"),
	SHOUTING("shout");

	private final Identifier disabledTextureId;
	private final Identifier enabledTextureId;

	VoiceState(String textureId) {
		this.disabledTextureId = new Identifier(RoleplaySimpleVoiceChat.MOD_ID, textureId + "_disabled");
		this.enabledTextureId = new Identifier(RoleplaySimpleVoiceChat.MOD_ID, textureId + "_enabled");
	}

	public Identifier getDisabledTextureId() { return this.disabledTextureId; }

	public Identifier getEnabledTextureId() { return this.enabledTextureId; }
}
