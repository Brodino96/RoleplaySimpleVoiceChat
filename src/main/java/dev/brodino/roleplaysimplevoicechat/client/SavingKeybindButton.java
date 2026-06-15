package dev.brodino.roleplaysimplevoicechat.client;

import de.maxhenkel.voicechat.gui.widgets.KeybindButton;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.Text;

public class SavingKeybindButton extends KeybindButton {

    public SavingKeybindButton(KeyBinding mapping, int x, int y, int width, int height, Text description) {
        super(mapping, x, y, width, height, description);
    }

    @Override
    public boolean mouseClicked(double x, double y, int button) {
        boolean wasListening = this.isListening();
        boolean result = super.mouseClicked(x, y, button);
        if (wasListening) {
            KeyBinding.updateKeysByCode();
        }
        return result;
    }

    @Override
    public boolean keyPressed(int key, int scanCode, int modifiers) {
        boolean wasListening = this.isListening();
        boolean result = super.keyPressed(key, scanCode, modifiers);
        if (wasListening) {
            KeyBinding.updateKeysByCode();
        }
        return result;
    }
}
