package net.brodino.roleplaysimplevoicechat.mixin.client;

import com.mojang.blaze3d.systems.RenderSystem;
import de.maxhenkel.voicechat.VoicechatClient;
import de.maxhenkel.voicechat.voice.client.RenderEvents;
import net.brodino.roleplaysimplevoicechat.client.VoiceStateManager;
import net.brodino.roleplaysimplevoicechat.effects.EffectsManager;
import net.brodino.roleplaysimplevoicechat.items.ItemManager;
import net.brodino.roleplaysimplevoicechat.shared.VoiceStates;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
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

    @Unique private static final Identifier SPEAKER_OFF_ICON = new Identifier("voicechat", "textures/icons/speaker_off.png");
    @Unique private static final int animationsTotalFrames = 7;
    @Unique private static final int animationsFrameHeight = 16;
    @Unique private static final int animationsFrameWidth = 16;
    @Unique private static final int animationsFrameTime = 3;

    @Inject(method = "onRenderHUD", at = @At("HEAD"), cancellable = true)
    private void onRenderHUD(MatrixStack stack, float tickDelta, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.options.hudHidden || VoicechatClient.CLIENT_CONFIG.hideIcons.get() || !VoicechatClient.CLIENT_CONFIG.showHudIcons.get()) {
            ci.cancel(); // Doesn't render anything
            return;
        }

        this.renderIcon(stack, this.getCurrentIcon());
        ci.cancel();
    }

    /**
     * Gets the correct icon to be rendered...
     * WARNING: This method CAN return a basic icon (16x16) instead of the expected format
     * I could fix this by having a way do differentiate icons during rendering to not animate this
     * But it will return this icon only if the player is null, and if the player is null I don't think this is going to be a problem
     */
    @Unique
    private Identifier getCurrentIcon() {
        ClientPlayerEntity player = this.minecraft.player;
        if (player == null) {
            return SPEAKER_OFF_ICON;
        }

        VoiceStateManager manager = VoiceStateManager.getInstance();

        VoiceStates state;
        if (player.hasStatusEffect(EffectsManager.EXTENDED_SPEECH) || player.getMainHandStack().getItem().equals(ItemManager.VOICE_EXTENDER)) {
            state = VoiceStates.EXTENDED;
        } else {
            state = manager.getCurrentState();
        }

        if (manager.isDisabled() || player.isDead() || manager.isMuted() || player.hasStatusEffect(EffectsManager.NEGATED_SPEECH)) {
            return state.getDisabledTextureId();
        }

        if (manager.isTalking()) {
            return state.getOnTextureId();
        }

        return state.getOffTextureId();
    }

    @Unique
	private void renderIcon(MatrixStack matrixStack, Identifier texture) {
        MinecraftClient minecraft = MinecraftClient.getInstance();
        if (minecraft.player == null) {
            return;
        }

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

        int currentFrame = (minecraft.player.age / animationsFrameTime) % animationsTotalFrames;

        int v = currentFrame * animationsFrameHeight;

        DrawableHelper.drawTexture(
                matrixStack,
                posX < 0 ? -16 : 0,
                posY < 0 ? -16 : 0,
                0, v,
                animationsFrameWidth,
                animationsFrameHeight,
                animationsFrameWidth,
                animationsFrameHeight * animationsTotalFrames
        );

        RenderSystem.disableBlend();
        matrixStack.pop();
    }
}
