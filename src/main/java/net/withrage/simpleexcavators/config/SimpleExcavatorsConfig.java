package net.withrage.simpleexcavators.config;

import net.neoforged.fml.loading.FMLPaths;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class SimpleExcavatorsConfig {

    private static final Path PATH =
            FMLPaths.CONFIGDIR.get().resolve("simpleexcavators.toml");

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

            Map<String, String> values = readSimpleToml(PATH);

            sneakMines1x1 = getBoolean(values, "general.sneakMines1x1", sneakMines1x1);
            pathMaking = getBoolean(values, "general.pathMaking", pathMaking);
            sneakPathMaking1x1 = getBoolean(values, "general.sneakPathMaking1x1", sneakPathMaking1x1);

            woodenDurability = getInt(values, "durability.wooden", woodenDurability);
            stoneDurability = getInt(values, "durability.stone", stoneDurability);
            copperDurability = getInt(values, "durability.copper", copperDurability);
            goldenDurability = getInt(values, "durability.golden", goldenDurability);
            ironDurability = getInt(values, "durability.iron", ironDurability);
            emeraldDurability = getInt(values, "durability.emerald", emeraldDurability);
            diamondDurability = getInt(values, "durability.diamond", diamondDurability);
            netheriteDurability = getInt(values, "durability.netherite", netheriteDurability);

        } catch (Exception e) {
            e.printStackTrace();

            try {
                writeDefaultFile();
            } catch (Exception ignored) {
            }
        }
    }

    private static Map<String, String> readSimpleToml(Path path) throws Exception {
        Map<String, String> values = new HashMap<>();
        String section = "";

        for (String rawLine : Files.readAllLines(path)) {
            String line = rawLine.trim();

            if (line.isEmpty() || line.startsWith("#")) continue;

            int commentIndex = line.indexOf("#");
            if (commentIndex >= 0) {
                line = line.substring(0, commentIndex).trim();
            }

            if (line.startsWith("[") && line.endsWith("]")) {
                section = line.substring(1, line.length() - 1).trim();
                continue;
            }

            int equalsIndex = line.indexOf("=");
            if (equalsIndex < 0) continue;

            String key = line.substring(0, equalsIndex).trim();
            String value = line.substring(equalsIndex + 1).trim();

            values.put(section + "." + key, value);
        }

        return values;
    }

    private static boolean getBoolean(Map<String, String> values,
                                      String key,
                                      boolean def) {

        String value = values.get(key);
        if (value == null) return def;

        return Boolean.parseBoolean(value);
    }

    private static int getInt(Map<String, String> values,
                              String key,
                              int def) {

        String value = values.get(key);
        if (value == null) return def;

        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return def;
        }
    }

    private static void writeDefaultFile() throws Exception {
        String text = """
                # Simple Excavators Configuration

                [general]

                # If true, sneaking mines only 1x1 instead of 3x3.
                sneakMines1x1 = true

                # If true, excavators are able to create 3x3 dirt paths.
                pathMaking = true

                # If true, excavators are able to create 1x1 dirt paths while sneaking.
                sneakPathMaking1x1 = true


                [durability]

                wooden = 108
                stone = 262
                copper = 380
                golden = 64
                iron = 506
                emerald = 2084
                diamond = 3122
                netherite = 4062
                """;

        Files.createDirectories(PATH.getParent());
        Files.writeString(PATH, text);
    }
}
