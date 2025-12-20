package net.withrage.simpleexcavators.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Files;
import java.nio.file.Path;

public class SimpleExcavatorsConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path PATH = FabricLoader.getInstance().getConfigDir().resolve("simple_excavators.json");

    public boolean sneakMines1x1 = true;

    public int woodenDurability = 108;
    public int stoneDurability = 262;
    public int copperDurability = 380;
    public int goldenDurability = 64;
    public int ironDurability = 506;
    public int emeraldDurability = 2084;
    public int diamondDurability = 3122;
    public int netheriteDurability = 4062;

    public static SimpleExcavatorsConfig load() {
        try {
            if (!Files.exists(PATH)) {
                SimpleExcavatorsConfig cfg = new SimpleExcavatorsConfig();
                cfg.save();
                return cfg;
            }
            return GSON.fromJson(Files.readString(PATH), SimpleExcavatorsConfig.class);
        } catch (Exception e) {
            // fallback if config is broken
            SimpleExcavatorsConfig cfg = new SimpleExcavatorsConfig();
            cfg.save();
            return cfg;
        }
    }

    public void save() {
        try {
            Files.createDirectories(PATH.getParent());
            Files.writeString(PATH, GSON.toJson(this));
        } catch (Exception ignored) {}
    }
}
