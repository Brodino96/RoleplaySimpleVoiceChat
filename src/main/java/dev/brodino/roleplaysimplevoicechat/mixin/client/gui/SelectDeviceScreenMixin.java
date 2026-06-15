package dev.brodino.roleplaysimplevoicechat.mixin.client.gui;

import de.maxhenkel.voicechat.gui.audiodevice.SelectDeviceScreen;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = SelectDeviceScreen.class, remap = false)
public class SelectDeviceScreenMixin {

	@ModifyConstant(method = "renderForeground", constant = @Constant(intValue = 4210752))
	private int replaceGray(int original) {
		return Formatting.WHITE.getColorValue();
	}
}
