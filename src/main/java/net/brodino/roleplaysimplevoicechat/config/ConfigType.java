package net.brodino.roleplaysimplevoicechat.config;

public class ConfigType {
    public float whisperDistance = 6.0F;
    public float normalDistance = 24.0F;
    public float shoutDistance = 48.0F;
    public float voiceExtenderDistance = 128.0F;

    public float getWhisperDistance() { return this.whisperDistance; }
    public float getNormalDistance() { return this.normalDistance; }
    public float getShoutDistance() { return this.shoutDistance; }
    public float getVoiceExtenderDistance() { return this.voiceExtenderDistance; }
}
