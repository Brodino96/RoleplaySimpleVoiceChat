package dev.brodino.roleplaysimplevoicechat.client;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.resource.ResourceType;

public class ClientEventHandler {

	public static void initialize() {
		ClientTickEvents.END_CLIENT_TICK.register(RoleplaySimpleVoicechatClient::tick);

		ResourceManagerHelper.get(ResourceType.CLIENT_RESOURCES).registerReloadListener(HudIconAtlas.createLazyListener());
	}
}
