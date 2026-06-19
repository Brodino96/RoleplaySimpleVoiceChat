package dev.brodino.roleplaysimplevoicechat.mixin.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import de.maxhenkel.voicechat.VoicechatClient;
import de.maxhenkel.voicechat.voice.client.RenderEvents;
import dev.brodino.roleplaysimplevoicechat.client.VoiceStateManager;
import dev.brodino.roleplaysimplevoicechat.client.gui.GuiSpriteAtlas;
import dev.brodino.roleplaysimplevoicechat.effects.EffectsManager;
import dev.brodino.roleplaysimplevoicechat.items.ItemManager;
import dev.brodino.roleplaysimplevoicechat.shared.VoiceStates;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RenderEvents.class, remap = false)
public class HudRenderMixin {

    @Shadow
    @Final
    private MinecraftClient minecraft;

    @Inject(method = "onRenderHUD", at = @At("HEAD"), cancellable = true)
    private void onRenderHUD(MatrixStack stack, float tickDelta, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.options.hudHidden || VoicechatClient.CLIENT_CONFIG.hideIcons.get() || !VoicechatClient.CLIENT_CONFIG.showHudIcons.get()) {
            ci.cancel();
            return;
        }

        this.renderSprite(stack, this.getCurrentSprite());
        ci.cancel();
    }

    @Unique
    private Sprite getCurrentSprite() {
        GuiSpriteAtlas atlas = GuiSpriteAtlas.getInstance();

        ClientPlayerEntity player = this.minecraft.player;
        if (player == null) {
            return atlas.getFallbackSprite();
        }

        VoiceStateManager manager = VoiceStateManager.getInstance();

        VoiceStates state;
        if (player.hasStatusEffect(EffectsManager.EXTENDED_SPEECH) || player.getMainHandStack().getItem().equals(ItemManager.VOICE_EXTENDER)) {
            state = VoiceStates.EXTENDED;
        } else {
            state = manager.getCurrentState();
        }

        if (manager.isDisabled() || player.isDead() || manager.isMuted() || player.hasStatusEffect(EffectsManager.NEGATED_SPEECH)) {
            return atlas.getSprite(state.getDisabledSpriteId());
        }

        if (manager.isTalking()) {
            return atlas.getSprite(state.getOnSpriteId());
        }

        return atlas.getSprite(state.getOffSpriteId());
    }

    @Unique
    private void renderSprite(MatrixStack matrixStack, Sprite sprite) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) {
            return;
        }

        matrixStack.push();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1F, 1F, 1F, 1F);
        RenderSystem.setShaderTexture(0, sprite.getAtlas().getId());
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        int posX = VoicechatClient.CLIENT_CONFIG.hudIconPosX.get();
        int posY = VoicechatClient.CLIENT_CONFIG.hudIconPosY.get();

        if (posX < 0) { matrixStack.translate(client.getWindow().getScaledWidth(), 0D, 0D); }
        if (posY < 0) { matrixStack.translate(0D, client.getWindow().getScaledHeight(), 0D); }
        matrixStack.translate(posX, posY, 0D);

        float scale = VoicechatClient.CLIENT_CONFIG.hudIconScale.get().floatValue();
        matrixStack.scale(scale, scale, 1F);

        DrawableHelper.drawSprite(
                matrixStack,
                posX < 0 ? -16 : 0,
                posY < 0 ? -16 : 0,
                0,
                16, 16,
                sprite
        );

        RenderSystem.disableBlend();
        matrixStack.pop();
    }
}
