package dev.brodino.roleplaysimplevoicechat.client;

import dev.brodino.roleplaysimplevoicechat.RoleplaySimpleVoicechat;
import dev.brodino.roleplaysimplevoicechat.shared.VoiceStates;
import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasHolder;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceReloader;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Stream;

public class HudIconAtlas extends SpriteAtlasHolder {

    private static final Identifier ATLAS_ID = new Identifier(RoleplaySimpleVoicechat.MOD_ID, "textures/atlas/hud_icons.png");
    private static final Identifier FABRIC_ID = new Identifier(RoleplaySimpleVoicechat.MOD_ID, "hud_icon_atlas");
    private static HudIconAtlas INSTANCE;

    private HudIconAtlas() { super(MinecraftClient.getInstance().getTextureManager(), ATLAS_ID, "icons"); }

    @Override
    protected Stream<Identifier> getSprites() {
        return Stream.of(
                VoiceStates.NORMAL.getOnSpriteId(), VoiceStates.NORMAL.getOffSpriteId(), VoiceStates.NORMAL.getDisabledSpriteId(),
                VoiceStates.WHISPER.getOnSpriteId(), VoiceStates.WHISPER.getOffSpriteId(), VoiceStates.WHISPER.getDisabledSpriteId(),
                VoiceStates.SHOUTING.getOnSpriteId(), VoiceStates.SHOUTING.getOffSpriteId(), VoiceStates.SHOUTING.getDisabledSpriteId(),
                VoiceStates.EXTENDED.getOnSpriteId(), VoiceStates.EXTENDED.getOffSpriteId(), VoiceStates.EXTENDED.getDisabledSpriteId(),
                RoleplaySimpleVoicechatClient.FALLBACK_SPRITE_ID
        );
    }

    @Override
    public Sprite getSprite(Identifier id) { return super.getSprite(id); }
    public static HudIconAtlas getInstance() { return INSTANCE; }

    private static HudIconAtlas getOrCreate() {
        if (INSTANCE == null) {
            INSTANCE = new HudIconAtlas();
        }
        return INSTANCE;
    }

    public static IdentifiableResourceReloadListener createLazyListener() {
        return new IdentifiableResourceReloadListener() {
            @Override
            public Identifier getFabricId() {
                return FABRIC_ID;
            }

            @Override
            public CompletableFuture<Void> reload(ResourceReloader.Synchronizer s, ResourceManager m, Profiler p, Profiler ap, Executor pe, Executor ae) {
                return getOrCreate().reload(s, m, p, ap, pe, ae);
            }
        };
    }
}
