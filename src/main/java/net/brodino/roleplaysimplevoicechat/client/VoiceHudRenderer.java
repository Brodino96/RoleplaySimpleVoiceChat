package net.brodino.roleplaysimplevoicechat.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.brodino.roleplaysimplevoicechat.VoiceState;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class VoiceHudRenderer implements HudRenderCallback {

    private static final int ICON_SIZE = 16;
    private static final int MARGIN = 8;

    @Override
    public void onHudRender(MatrixStack matrixStack, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null || client.options.hudHidden) {
            return;
        }

        VoiceStateManager manager = VoiceStateManager.getInstance();
        VoiceState state = manager.getCurrentState();
        boolean talking = manager.isTalking();
        Identifier texture = talking
            ? state.getEnabledTextureId()
            : state.getDisabledTextureId();

        int y = client.getWindow().getScaledHeight() - ICON_SIZE - MARGIN;

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, texture);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        DrawableHelper.drawTexture(matrixStack, MARGIN, y, 0, 0, ICON_SIZE, ICON_SIZE, ICON_SIZE, ICON_SIZE);

        RenderSystem.disableBlend();
    }
}
