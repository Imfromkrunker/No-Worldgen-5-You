package net.darkhax.noworldgen5you;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import net.darkhax.noworldgen5you.world.gen.MapGenScatteredFeaturesEmpty;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate;

public class WorldgenConfig {

    private static Configuration config;
    public static final Map<String, Boolean> settings = new HashMap<>();

    public static Configuration initConfig(File file) {

        config = new Configuration(file);

        // Comment for structure settings
        config.setCategoryComment("map_structures", "Allows for various types of map generators to be disabled.");

        // Iterate over InitMapGenEvent types and register them in config
        for (final InitMapGenEvent.EventType type : InitMapGenEvent.EventType.values()) {
            if (type != InitMapGenEvent.EventType.CUSTOM) {  // 1.7.10 also has a CUSTOM type
                isStructureDisabled(type.name().toLowerCase());
            }
        }

        // Comment for scattered structure settings
        config.setCategoryComment("scattered_structures", "This category requires the scattered map generator from the map_structures category to be disabled.");

        // Iterate over custom scattered features and register them
        for (final MapGenScatteredFeaturesEmpty.Type type : MapGenScatteredFeaturesEmpty.Type.values()) {
            isScateredStructureDisabled(type.name().toLowerCase());
        }

        // Comment for chunk populate settings
        config.setCategoryComment("map_populates", "Allows for various types of chunk populators to be disabled.");

        // Iterate over Populate.EventType and register them
        for (final Populate.EventType type : Populate.EventType.values()) {
            if (type != Populate.EventType.CUSTOM) {  // 1.7.10 also has a CUSTOM type
                isPopulateDisabled(type.name().toLowerCase());
            }
        }

        syncConfigData();
        return config;
    }

    /**
     * Sync the config data if it has changed.
     */
    public static void syncConfigData() {
        if (config.hasChanged()) {
            config.save();
        }
    }

    /**
     * Checks if a specific structure type is disabled via config.
     *
     * @param type The type of structure.
     * @return true if the structure should be disabled.
     */
    public static boolean isStructureDisabled(String type) {
        return config.getBoolean("disable_" + type, "map_structures", false, "Should " + type + " generation be disabled?");
    }

    /**
     * Checks if a specific scattered structure type is disabled via config.
     *
     * @param type The type of scattered structure.
     * @return true if the scattered structure should be disabled.
     */
    public static boolean isScateredStructureDisabled(String type) {
        return config.getBoolean("disable_" + type, "scattered_structures", false, "Should " + type + " generation be disabled?");
    }

    /**
     * Checks if a specific chunk populate type is disabled via config.
     *
     * @param type The type of populate event.
     * @return true if the populate event should be disabled.
     */
    public static boolean isPopulateDisabled(String type) {
        return config.getBoolean("disable_" + type, "map_populates", false, "Should " + type + " generation be disabled?");
    }
}
