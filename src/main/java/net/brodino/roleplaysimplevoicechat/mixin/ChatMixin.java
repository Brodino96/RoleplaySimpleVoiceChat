package net.brodino.roleplaysimplevoicechat.mixin;

import net.brodino.roleplaysimplevoicechat.RoleplaySimpleVoicechat;
import net.brodino.roleplaysimplevoicechat.shared.VoiceStates;
import net.minecraft.network.message.MessageType;
import net.minecraft.network.message.SignedMessage;
import net.minecraft.network.message.SentMessage;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayNetworkHandler.class)
public class ChatMixin {

    @Shadow
    public ServerPlayerEntity player;

    @Inject(method = "handleDecoratedMessage", at = @At("HEAD"), cancellable = true)
    private void onHandleDecoratedMessage(SignedMessage message, CallbackInfo ci) {
        ci.cancel();

        VoiceStates state = RoleplaySimpleVoicechat.PLAYER_VOICE_STATES.getOrDefault(player.getUuid(), VoiceStates.NORMAL);

        float maxDist = switch (state) {
            case WHISPER -> RoleplaySimpleVoicechat.CONFIG.getData().getWhisperDistance();
            case NORMAL -> RoleplaySimpleVoicechat.CONFIG.getData().getNormalDistance();
            case SHOUTING -> RoleplaySimpleVoicechat.CONFIG.getData().getShoutDistance();
            case EXTENDED -> RoleplaySimpleVoicechat.CONFIG.getData().getExtendedDistance();
        };

        MessageType.Parameters params = MessageType.params(MessageType.CHAT, player);
        MinecraftServer server = player.getServer();
        if (server == null) {
            return;
        }

        server.logChatMessage(message.getContent(), params, message.headerSignature().isEmpty() ? "Not Secure" : null);

        SentMessage sent = SentMessage.of(message);
        float distSq = maxDist * maxDist;

        for (ServerPlayerEntity recipient : server.getPlayerManager().getPlayerList()) {
            if (recipient == player || (recipient.getWorld() == player.getWorld() && recipient.squaredDistanceTo(player) <= distSq)) {
                recipient.sendChatMessage(sent, false, params);
            }
        }
    }
}
