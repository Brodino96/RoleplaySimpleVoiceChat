package net.brodino.roleplaysimplevoicechat.client;

import de.maxhenkel.voicechat.api.VoicechatClientApi;
import net.brodino.roleplaysimplevoicechat.VoiceState;

public class VoiceStateManager {

    private static VoiceStateManager instance;
    private VoiceState currentState = VoiceState.NORMAL;
    private VoicechatClientApi clientApi;

    public static VoiceStateManager getInstance() {
        if (instance == null) {
            instance = new VoiceStateManager();
        }
        return instance;
    }

    public VoiceState getCurrentState() {
        return currentState;
    }

    public void cycleState() {
        currentState = currentState.next();
    }

    public void setClientApi(VoicechatClientApi api) {
        this.clientApi = api;
    }

    public boolean isTalking() {
        return this.clientApi != null && this.clientApi.isTalking();
    }

    public boolean isMuted() {
        return this.clientApi != null && this.clientApi.isMuted();
    }
}
