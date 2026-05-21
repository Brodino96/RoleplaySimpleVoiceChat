package net.brodino.roleplaysimplevoicechat.mixin;

import de.maxhenkel.voicechat.gui.VoiceChatScreenBase;
import de.maxhenkel.voicechat.gui.VoiceChatSettingsScreen;
import de.maxhenkel.voicechat.gui.widgets.KeybindButton;
import net.brodino.roleplaysimplevoicechat.client.RoleplaySimpleVoicechatClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = VoiceChatSettingsScreen.class, remap = false)
public abstract class MixinVoiceChatSettingsScreen {

    @Unique
    private KeybindButton roleplaysvc$cycleModeButton;

    @Inject(method = "init", at = @At("HEAD"), remap = true)
    private void expandDialog(CallbackInfo ci) {
        VoiceChatScreenBaseAccessor accessor = (VoiceChatScreenBaseAccessor) (Object) this;
        accessor.setYSize(accessor.getYSize() + 21);
    }

    @Inject(method = "init", at = @At("TAIL"), remap = true)
    private void addCycleModeButton(CallbackInfo ci) {
        VoiceChatScreenBaseAccessor accessor = (VoiceChatScreenBaseAccessor) (Object) this;
        VoiceChatScreenBase base = (VoiceChatScreenBase) (Object) this;
        int guiLeft = base.getGuiLeft();
        int guiTop = base.getGuiTop();
        int xSize = accessor.getXSize();

        this.roleplaysvc$cycleModeButton = new KeybindButton(
            RoleplaySimpleVoicechatClient.cycleModeKey,
            guiLeft + 7,
            guiTop + 209,
            xSize - 14,
            20
        );
        ((ScreenAccessor) (Object) this).invokeAddDrawableChild(this.roleplaysvc$cycleModeButton);
    }

    @Inject(method = "shouldCloseOnEsc", at = @At("HEAD"), cancellable = true, remap = true)
    private void blockEscWhenListening(CallbackInfoReturnable<Boolean> cir) {
        if (this.roleplaysvc$cycleModeButton != null && this.roleplaysvc$cycleModeButton.isListening()) {
            cir.setReturnValue(false);
        }
    }
}
