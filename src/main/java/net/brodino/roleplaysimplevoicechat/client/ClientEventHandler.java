package net.brodino.roleplaysimplevoicechat.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class ClientEventHandler {

	public static void initialize() {
		ClientTickEvents.END_CLIENT_TICK.register(RoleplaySimpleVoicechatClient::tick);
	}
}
