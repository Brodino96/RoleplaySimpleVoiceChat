package net.brodino.roleplaysimplevoicechat.mixin.client;

import de.maxhenkel.voicechat.gui.VoiceChatScreenBase;
import de.maxhenkel.voicechat.gui.VoiceChatSettingsScreen;
import de.maxhenkel.voicechat.gui.widgets.KeybindButton;
import net.brodino.roleplaysimplevoicechat.RoleplaySimpleVoicechat;
import net.brodino.roleplaysimplevoicechat.client.RoleplaySimpleVoicechatClient;
import net.brodino.roleplaysimplevoicechat.client.SavingKeybindButton;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = VoiceChatSettingsScreen.class, remap = false)
public abstract class VoicechatSettingsMixin {

    @Redirect(method = "<clinit>", at = @At(value = "NEW", target = "net/minecraft/util/Identifier", ordinal = 0))
    private static Identifier redirectTexture(String namespace, String path) {
        return new Identifier(RoleplaySimpleVoicechat.MOD_ID, "textures/gui/gui_voicechat_settings.png");
    }

    @Unique
    private KeybindButton rpsvc$cycleModeButton;

    @Inject(method = "init", at = @At("HEAD"), remap = true)
    private void expandDialog(CallbackInfo ci) {
        VoicechatScreenAccessor accessor = (VoicechatScreenAccessor) (Object) this;
        accessor.setYSize(240);
    }

    @Inject(method = "init", at = @At("TAIL"), remap = true)
    private void addCycleModeButton(CallbackInfo ci) {
        VoicechatScreenAccessor accessor = (VoicechatScreenAccessor) (Object) this;
        VoiceChatScreenBase base = (VoiceChatScreenBase) (Object) this;
        int guiLeft = base.getGuiLeft();
        int guiTop = base.getGuiTop();
        int xSize = accessor.getXSize();

        // Slot immediately below the PTT keybind button (guiTop+104, height 20)
        int insertY = guiTop + 125;

        // Push all widgets at or below the insertion point down one slot
        for (Element element : ((Screen) (Object) this).children()) {
            if (element instanceof ClickableWidget widget && widget.y >= insertY) {
                widget.y += 21;
            }
        }

        rpsvc$cycleModeButton = new SavingKeybindButton(
            RoleplaySimpleVoicechatClient.cycleModeKey,
            guiLeft + 10,
            insertY,
            xSize - 20,
            20,
            Text.translatable("key.roleplaysimplevoicechat.cycle_mode.button")
        );
        ((ScreenAccessor) (Object) this).invokeAddDrawableChild(rpsvc$cycleModeButton);
    }

    @Inject(method = "shouldCloseOnEsc", at = @At("HEAD"), cancellable = true, remap = true)
    private void blockEscWhenListening(CallbackInfoReturnable<Boolean> cir) {
        if (rpsvc$cycleModeButton != null && rpsvc$cycleModeButton.isListening()) {
            cir.setReturnValue(false);
        }
    }
}
