package dev.brodino.roleplaysimplevoicechat.client;

import dev.brodino.roleplaysimplevoicechat.RoleplaySimpleVoicechat;
import dev.brodino.roleplaysimplevoicechat.shared.VoiceStatePacket;
import dev.brodino.roleplaysimplevoicechat.shared.VoiceStates;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class RoleplaySimpleVoicechatClient implements ClientModInitializer {

    public static KeyBinding cycleModeKey;

    @Override
    public void onInitializeClient() {
        RoleplaySimpleVoicechat.LOGGER.info("Initializing RoleplaySimpleVoicechat Client");

        cycleModeKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.roleplaysimplevoicechat.cycle_mode",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_Z,
            "category.roleplaysimplevoicechat"
        ));

        ClientEventHandler.initialize();
    }

    public static void tick(MinecraftClient client) {
        ClientPlayerEntity player = client.player;
        if (player == null) {
            return;
        }

        while (cycleModeKey.wasPressed()) {
            VoiceStateManager manager = VoiceStateManager.getInstance();

            if (!manager.canCycleState(player)) {
                return;
            }

            VoiceStates newState = manager.cycleState();
            RoleplaySimpleVoicechat.LOGGER.debug("Voice mode cycled to {}", newState);

            var buf = PacketByteBufs.create();
            buf.writeString(newState.name());
            ClientPlayNetworking.send(VoiceStatePacket.CHANNEL, buf);
        }
    }
}
