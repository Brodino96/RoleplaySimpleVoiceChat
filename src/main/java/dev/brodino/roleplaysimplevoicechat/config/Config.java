package dev.brodino.roleplaysimplevoicechat.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Supplier;

public class Config<T> {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private Path configPath;
    private T data;
    private final Class<T> type;
    private final Supplier<T> defaults;

    public Config(String modId, String configName, Class<T> type, Supplier<T> defaults, Logger logger) {
        Path dataDirectory = Path.of("config").resolve(modId);
        this.type = type;
        this.defaults = defaults;

        try {
            if (!Files.exists(dataDirectory)) {
                Files.createDirectories(dataDirectory);
            }
            this.configPath = dataDirectory.resolve(configName + ".json");
            this.load();
        } catch (IOException e) {
            logger.error("Failed to load {}.json", configName);
        }
    }

    private void load() throws IOException {
        if (!Files.exists(this.configPath)) {
            this.data = this.defaults.get();
            this.saveToFile();
            return;
        }

        try (Reader reader = Files.newBufferedReader(this.configPath)) {
            this.data = GSON.fromJson(reader, this.type);
            if (data == null) {
                this.data = this.defaults.get();
                this.saveToFile();
            }
        }
    }

    public boolean reload() {
        try {
            this.load();
            return true;
        } catch (IOException ignored) {
            return false;
        }
    }

    public boolean save() {
        try {
            this.saveToFile();
            return true;
        } catch (IOException ignored) {
            return false;
        }
    }

    private void saveToFile() throws IOException {
        try (Writer writer = Files.newBufferedWriter(this.configPath)) {
            GSON.toJson(this.data, writer);
        }
    }

    public T getData() { return this.data; }

}
