package net.brodino.roleplaysimplevoicechat.client.mixin;

import de.maxhenkel.voicechat.intercompatibility.ClientCompatibilityManager;
import de.maxhenkel.voicechat.voice.client.KeyEvents;
import net.minecraft.client.option.KeyBinding;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = KeyEvents.class, remap = false)
public class KeybindsRegistrationMixin {

    @Redirect(method = "registerKeyBinds", remap = true, at = @At(value = "INVOKE", target = "Lde/maxhenkel/voicechat/intercompatibility/ClientCompatibilityManager;registerKeyBinding(Lnet/minecraft/client/option/KeyBinding;)Lnet/minecraft/client/option/KeyBinding;", ordinal = 1))
    private static KeyBinding skipWhisperKeybind(ClientCompatibilityManager manager, KeyBinding keyBinding) {
        return keyBinding;
    }

    @Redirect(method = "registerKeyBinds", remap = true, at = @At(value = "INVOKE", target = "Lde/maxhenkel/voicechat/intercompatibility/ClientCompatibilityManager;registerKeyBinding(Lnet/minecraft/client/option/KeyBinding;)Lnet/minecraft/client/option/KeyBinding;", ordinal = 7))
    private static KeyBinding skipGroupKeybind(ClientCompatibilityManager manager, KeyBinding keyBinding) {
        return keyBinding;
    }

    @Redirect(method = "registerKeyBinds", remap = true, at = @At(value = "INVOKE", target = "Lde/maxhenkel/voicechat/intercompatibility/ClientCompatibilityManager;registerKeyBinding(Lnet/minecraft/client/option/KeyBinding;)Lnet/minecraft/client/option/KeyBinding;", ordinal = 9))
    private static KeyBinding skipAdjustKeybind(ClientCompatibilityManager manager, KeyBinding keyBinding) {
        return keyBinding;
    }
}
