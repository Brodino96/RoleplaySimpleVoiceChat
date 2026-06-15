package dev.brodino.roleplaysimplevoicechat.mixin.client.gui;

import de.maxhenkel.voicechat.gui.VoiceChatScreenBase;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin( value = VoiceChatScreenBase.class, remap = false )
public class VoiceChatScreenBaseMixin {

	@Inject(method = "getFontColor", at = @At("HEAD"), cancellable = true )
	private void getFontColor(CallbackInfoReturnable<Integer> cir) {
		cir.setReturnValue(Formatting.WHITE.getColorValue());
	}

}
