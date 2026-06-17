package net.withrage.simpleexcavators.config;

import net.fabricmc.loader.api.FabricLoader;
import org.tomlj.Toml;
import org.tomlj.TomlParseResult;

import java.nio.file.Files;
import java.nio.file.Path;

public class SimpleExcavatorsConfig {
    private static final Path PATH = FabricLoader.getInstance().getConfigDir().resolve("simpleexcavators.toml");

    public static boolean sneakMines1x1 = true;
    public static boolean pathMaking = true;
    public static boolean sneakPathMaking1x1 = true;

    public static int woodenDurability = 108;
    public static int stoneDurability = 262;
    public static int copperDurability = 380;
    public static int goldenDurability = 64;
    public static int ironDurability = 506;
    public static int emeraldDurability = 2084;
    public static int diamondDurability = 3122;
    public static int netheriteDurability = 4062;

    public static void load() {
        try {
            if (!Files.exists(PATH)) {
                writeDefaultFile();
                return;
            }

            TomlParseResult toml = Toml.parse(Files.readString(PATH));

            if (toml.hasErrors()) {
                writeDefaultFile();
                return;
            }

            sneakMines1x1 = getBoolean(toml, "general.sneakMines1x1", sneakMines1x1);
            pathMaking = getBoolean(toml, "general.pathMaking", pathMaking);
            sneakPathMaking1x1 = getBoolean(toml, "general.sneakPathMaking1x1", sneakPathMaking1x1);

            woodenDurability = getInt(toml, "durability.wooden", woodenDurability);
            stoneDurability = getInt(toml, "durability.stone", stoneDurability);
            copperDurability = getInt(toml, "durability.copper", copperDurability);
            goldenDurability = getInt(toml, "durability.golden", goldenDurability);
            ironDurability = getInt(toml, "durability.iron", ironDurability);
            emeraldDurability = getInt(toml, "durability.emerald", emeraldDurability);
            diamondDurability = getInt(toml, "durability.diamond", diamondDurability);
            netheriteDurability = getInt(toml, "durability.netherite", netheriteDurability);

        } catch (Exception e) {
            try { writeDefaultFile(); } catch (Exception ignored) {}
        }
    }

    private static boolean getBoolean(TomlParseResult toml, String key, boolean def) {
        Boolean v = toml.getBoolean(key);
        return v != null ? v : def;
    }

    private static int getInt(TomlParseResult toml, String key, int def) {
        Long v = toml.getLong(key);
        if (v == null) return def;
        if (v > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        if (v < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        return v.intValue();
    }

    private static void writeDefaultFile() throws Exception {
        Files.createDirectories(PATH.getParent());

        String content = ""
                + "# ================================\n"
                + "# Simple Excavators Configuration\n"
                + "# ================================\n\n"
                + "[general]\n"
                + "# If true, sneaking mines only 1x1 instead of 3x3.\n"
                + "sneakMines1x1 = " + sneakMines1x1 + "\n"
                + "# If true, excavators are able to create 3x3 dirt paths.\n"
                + "pathMaking = " + pathMaking + "\n"
                + "# If true, excavators are able to create 1x1 dirt paths while sneaking.\n"
                + "sneakPathMaking1x1 = " + sneakPathMaking1x1 + "\n\n"
                + "[durability]\n"
                + "# You can change the durability for each excavator here.\n"
                + "wooden = " + woodenDurability + "\n"
                + "stone = " + stoneDurability + "\n"
                + "copper = " + copperDurability + "\n"
                + "golden = " + goldenDurability + "\n"
                + "iron = " + ironDurability + "\n"
                + "emerald = " + emeraldDurability + "\n"
                + "diamond = " + diamondDurability + "\n"
                + "netherite = " + netheriteDurability + "\n";

        Files.writeString(PATH, content);
    }
}
