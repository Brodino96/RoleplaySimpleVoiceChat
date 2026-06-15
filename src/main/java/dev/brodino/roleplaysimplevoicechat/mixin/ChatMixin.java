package dev.brodino.roleplaysimplevoicechat.mixin;

import de.maxhenkel.voicechat.Voicechat;
import dev.brodino.roleplaysimplevoicechat.RoleplaySimpleVoicechat;
import net.minecraft.network.message.MessageType;
import net.minecraft.network.message.SentMessage;
import net.minecraft.network.message.SignedMessage;
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

        float distance = RoleplaySimpleVoicechat.getPlayerVoiceDistance(player);

        MessageType.Parameters params = MessageType.params(MessageType.CHAT, this.player);
        MinecraftServer server = this.player.getServer();
        if (server == null) {
            return;
        }

        server.logChatMessage(message.getContent(), params, message.headerSignature().isEmpty() ? "Not Secure" : null);

        SentMessage sent = SentMessage.of(message);
        float distSq = distance * distance;

        boolean restrictSpectatorChat = !Voicechat.SERVER_CONFIG.spectatorInteraction.get() && this.player.isSpectator();

        for (ServerPlayerEntity recipient : server.getPlayerManager().getPlayerList()) {
            if (recipient == this.player) {
                recipient.sendChatMessage(sent, false, params);
                continue;
            }

            if (recipient.getWorld() != this.player.getWorld()) {
                continue;
            }

            if (recipient.squaredDistanceTo(this.player) > distSq) {
                continue;
            }

            if (restrictSpectatorChat && !recipient.isSpectator()) {
                continue;
            }

            recipient.sendChatMessage(sent, false, params);
        }
    }
}
