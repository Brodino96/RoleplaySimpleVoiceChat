package net.brodino.roleplaysimplevoicechat.client.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import de.maxhenkel.voicechat.VoicechatClient;
import de.maxhenkel.voicechat.voice.client.RenderEvents;
import net.brodino.roleplaysimplevoicechat.client.VoiceStateManager;
import net.brodino.roleplaysimplevoicechat.shared.VoiceState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RenderEvents.class, remap = false)
public class HudRenderMixin {

    @Unique
    private static final Identifier SPEAKER_OFF_ICON = new Identifier("voicechat", "textures/icons/speaker_off.png");

    @Inject(method = "onRenderHUD", at = @At("HEAD"), cancellable = true)
    private void onRenderHUD(MatrixStack stack, float tickDelta, CallbackInfo ci) {
        if (VoicechatClient.CLIENT_CONFIG.hideIcons.get() || !VoicechatClient.CLIENT_CONFIG.showHudIcons.get()) {
            ci.cancel();
            return;
        }

        VoiceStateManager manager = VoiceStateManager.getInstance();

        if (manager.isDisabled()) {
            this.renderIcon(stack, SPEAKER_OFF_ICON);
            ci.cancel();
            return;
        }

        VoiceState state = manager.getCurrentState();
        Identifier texture;
        if (manager.isMuted() && !manager.isPushToTalk()) {
            texture = state.getMutedTextureId();
        } else if (manager.isTalking()) {
            texture = state.getEnabledTextureId();
        } else {
            texture = state.getDisabledTextureId();
        }

        this.renderIcon(stack, texture);
        ci.cancel();
    }

    @Unique
	private void renderIcon(MatrixStack matrixStack, Identifier texture) {
        MinecraftClient minecraft = MinecraftClient.getInstance();
        matrixStack.push();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
        RenderSystem.setShaderTexture(0, texture);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        int posX = VoicechatClient.CLIENT_CONFIG.hudIconPosX.get();
        int posY = VoicechatClient.CLIENT_CONFIG.hudIconPosY.get();

        if (posX < 0) { matrixStack.translate(minecraft.getWindow().getScaledWidth(), 0D, 0D); }
        if (posY < 0) { matrixStack.translate(0D, minecraft.getWindow().getScaledHeight(), 0D); }
        matrixStack.translate(posX, posY, 0D);

        float scale = VoicechatClient.CLIENT_CONFIG.hudIconScale.get().floatValue();
        matrixStack.scale(scale, scale, 1F);

        DrawableHelper.drawTexture(matrixStack, posX < 0 ? -16 : 0, posY < 0 ? -16 : 0, 0, 0, 16, 16, 16, 16);

        RenderSystem.disableBlend();
        matrixStack.pop();
    }
}
