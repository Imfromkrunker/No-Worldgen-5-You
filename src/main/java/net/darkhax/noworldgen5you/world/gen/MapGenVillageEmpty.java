package net.darkhax.noworldgen5you.world.gen;

import net.minecraft.world.gen.structure.MapGenVillage;

public class MapGenVillageEmpty extends MapGenVillage {

    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
        return false;
    }
}
