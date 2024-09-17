package net.darkhax.noworldgen5you.world.gen;

import net.darkhax.noworldgen5you.WorldgenConfig;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;

public class MapGenScatteredFeaturesEmpty extends MapGenScatteredFeature {

    public static enum Type {
        WITCH_HUT,
        IGLOO,
        DESSERT_PYRAMID,
        JUNGLE_TEMPLE;
    }

    @Override
    public boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {

        // Get the biome at the given chunk coordinates
        final BiomeGenBase biome = this.worldObj.getBiomeGenForCoords(chunkX * 16 + 8, chunkZ * 16 + 8);

        // Witch hut
        if (WorldgenConfig.isScateredStructureDisabled(Type.WITCH_HUT.name().toLowerCase()) && biome == BiomeGenBase.swampland) {
            return false;
        }

        // Desert pyramid
        else if (WorldgenConfig.isScateredStructureDisabled(Type.DESSERT_PYRAMID.name().toLowerCase()) &&
                (biome == BiomeGenBase.desert || biome == BiomeGenBase.desertHills)) {
            return false;
        }

        // Igloo
        else if (WorldgenConfig.isScateredStructureDisabled(Type.IGLOO.name().toLowerCase()) &&
                (biome == BiomeGenBase.icePlains || biome == BiomeGenBase.coldTaiga)) {
            return false;
        }

        // Jungle temple
        else if (WorldgenConfig.isScateredStructureDisabled(Type.JUNGLE_TEMPLE.name().toLowerCase()) &&
                (biome == BiomeGenBase.jungle || biome == BiomeGenBase.jungleHills)) {
            return false;
        }

        return super.canSpawnStructureAtCoords(chunkX, chunkZ);
    }
}
