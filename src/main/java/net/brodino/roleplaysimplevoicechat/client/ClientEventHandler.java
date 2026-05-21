package net.brodino.roleplaysimplevoicechat.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class ClientEventHandler {

	public static void initialize() {
		HudRenderCallback.EVENT.register(new VoiceHudRenderer());
		ClientTickEvents.END_CLIENT_TICK.register(RoleplaySimpleVoicechatClient::tick);
	}
}
