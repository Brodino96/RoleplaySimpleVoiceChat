package net.brodino.roleplaysimplevoicechat.shared;

import net.brodino.roleplaysimplevoicechat.RoleplaySimpleVoicechat;
import net.minecraft.util.Identifier;

public class VoiceState {

	private final Identifier offTextureId;
	private final Identifier onTextureId;
	private final Identifier slashedTextureId;

	public VoiceState(String name) {
		this.offTextureId = new Identifier(RoleplaySimpleVoicechat.MOD_ID, "textures/icons/" + name + "_off.png");
		this.onTextureId = new Identifier(RoleplaySimpleVoicechat.MOD_ID, "textures/icons/" + name + "_on.png");
		this.slashedTextureId = new Identifier(RoleplaySimpleVoicechat.MOD_ID, "textures/icons/" + name + "_slashed.png");
	}

	public Identifier getOffTextureId() { return this.offTextureId; }
	public Identifier getOnTextureId() { return this.onTextureId; }
	public Identifier getSlashedTextureId() { return this.slashedTextureId; }
}
