package net.brodino.roleplaysimplevoicechat;

import net.brodino.roleplaysimplevoicechat.shared.VoiceStatePacket;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class ServerEventHandler {

	public static void initialize() {
		ServerPlayNetworking.registerGlobalReceiver(VoiceStatePacket.CHANNEL, (s, player, handler, buf, rs) -> {
			RoleplaySimpleVoicechat.handleVoiceStateChange(player, buf);
		});

		ServerLifecycleEvents.START_DATA_PACK_RELOAD.register((server, resManager) -> {
			RoleplaySimpleVoicechat.reloadConfig();
		});
	}
}
