package net.brodino.roleplaysimplevoicechat.client.mixin;

import de.maxhenkel.voicechat.gui.VoiceChatScreen;
import de.maxhenkel.voicechat.gui.widgets.ImageButton;
import de.maxhenkel.voicechat.gui.widgets.ToggleImageButton;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.TranslatableTextContent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = VoiceChatScreen.class, remap = false)
public abstract class VoicechatScreenMixin {

    @Inject(method = "init", at = @At("TAIL"), remap = true)
    private void disableGroupAndVolumeButtons(CallbackInfo ci) {
        for (Element element : ((Screen) (Object) this).children()) {
            if (!(element instanceof ClickableWidget button)) continue;

            // Group button: ButtonWidget whose message key is "message.voicechat.group"
            if (button.getMessage().getContent() instanceof TranslatableTextContent ttc && ttc.getKey().equals("message.voicechat.group")) {
                button.active = false;
                continue;
            }

            // Volumes button: the only non-toggle ImageButton in this screen
            if (element instanceof ImageButton && !(element instanceof ToggleImageButton)) {
                button.active = false;
            }
        }
    }
}
