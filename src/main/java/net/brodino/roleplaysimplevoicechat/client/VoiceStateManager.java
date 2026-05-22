package net.brodino.roleplaysimplevoicechat.client;

import de.maxhenkel.voicechat.api.VoicechatClientApi;
import net.brodino.roleplaysimplevoicechat.effects.EffectsManager;
import net.brodino.roleplaysimplevoicechat.shared.VoiceStates;
import net.minecraft.client.network.ClientPlayerEntity;

public class VoiceStateManager {

    private static VoiceStateManager instance;
    private VoiceStates currentState = VoiceStates.NORMAL;
    private VoicechatClientApi clientApi;

    public void setClientApi(VoicechatClientApi api) { this.clientApi = api; }
    public static VoiceStateManager getInstance() {
        if (instance == null) {
            instance = new VoiceStateManager();
        }
        return instance;
    }

    public boolean canCycleState(ClientPlayerEntity player) {
        return !player.hasStatusEffect(EffectsManager.NEGATED_SPEECH)
            && !player.hasStatusEffect(EffectsManager.EXTENDED_SPEECH)
            && !player.isDead()
            && !this.isDisabled()
            && !this.isMuted();
    }

    public VoiceStates cycleState() {
        this.currentState = currentState.next();
        return this.currentState;
    }

    public VoiceStates getCurrentState() { return currentState; }

    public boolean isTalking() { return this.clientApi != null && this.clientApi.isTalking(); }
    public boolean isMuted() { return this.clientApi != null && this.clientApi.isMuted() && !this.isPushToTalk();  }
    public boolean isDisabled() { return this.clientApi != null && this.clientApi.isDisabled(); }
    private boolean isPushToTalk() { return this.clientApi != null && "PTT".equals(this.clientApi.getClientConfig().getValue("microphone_activation_type")); }
}
