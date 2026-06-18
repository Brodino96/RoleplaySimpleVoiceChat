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

import java.util.HashSet;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.stream.Stream;

public class GuiSpriteAtlas extends SpriteAtlasHolder {

    private static final Identifier ATLAS_ID = new Identifier(RoleplaySimpleVoicechat.MOD_ID, "textures/atlas/hud_icons.png");
    private static final Identifier FABRIC_ID = new Identifier(RoleplaySimpleVoicechat.MOD_ID, "hud_icon_atlas");
    private static final Identifier FALLBACK_SPRITE_ID = new Identifier(RoleplaySimpleVoicechat.MOD_ID, "fallback");
    private static GuiSpriteAtlas INSTANCE;

    private GuiSpriteAtlas() { super(MinecraftClient.getInstance().getTextureManager(), ATLAS_ID, "gui/icons"); }

    @Override
    protected Stream<Identifier> getSprites() {
        HashSet<Identifier> set = new HashSet<>();
        for (VoiceStates state : VoiceStates.values()) {
            set.add(state.getOnSpriteId());
            set.add(state.getOffSpriteId());
            set.add(state.getDisabledSpriteId());
        }
        set.add(FALLBACK_SPRITE_ID);
        return set.stream();
    }

    @Override
    public Sprite getSprite(Identifier id) { return super.getSprite(id); }
    public Sprite getFallbackSprite() { return this.getSprite(FALLBACK_SPRITE_ID); }
    public static GuiSpriteAtlas getInstance() { return INSTANCE; }

    private static GuiSpriteAtlas getOrCreate() {
        if (INSTANCE == null) {
            INSTANCE = new GuiSpriteAtlas();
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
