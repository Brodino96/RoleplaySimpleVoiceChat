package net.brodino.roleplaysimplevoicechat.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.brodino.roleplaysimplevoicechat.RoleplaySimpleVoiceChat;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public class Config {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private Path configPath;
    private ConfigType data;

    public Config() {
        Path dataDirectory = Path.of("config/roleplaysimplevoicechat");

        try {
            if (!Files.exists(dataDirectory)) {
                Files.createDirectories(dataDirectory);
            }
            this.configPath = dataDirectory.resolve("config.json");
            this.load();
        } catch (IOException e) {
            RoleplaySimpleVoiceChat.LOGGER.error("Failed to load config.json!", e);
        }
    }

    private void load() throws IOException {
        if (!Files.exists(this.configPath)) {
            this.data = this.getDefaults();
            this.save();
            return;
        }

        try (Reader reader = Files.newBufferedReader(this.configPath)) {
            this.data = GSON.fromJson(reader, ConfigType.class);
            if (data == null) {
                this.data = this.getDefaults();
                this.save();
            }
        }
    }

    public void reload() throws IOException {
        this.load();
    }

    private void save() throws IOException {
        try (Writer writer = Files.newBufferedWriter(this.configPath)) {
            GSON.toJson(this.data, writer);
        }
    }

    private ConfigType getDefaults() {
        ConfigType defaults = new ConfigType();
        defaults.extendedVoiceDistance = 64;
        return defaults;
    }

    public int getExtendedDistance() {
        return this.data.extendedVoiceDistance;
    }
    public int getSneakingDistance() {
        return  this.data.sneakingVoiceDistance;
    }

}
