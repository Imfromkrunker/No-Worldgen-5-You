package net.darkhax.noworldgen5you.world.gen;

import net.minecraft.world.gen.structure.MapGenNetherBridge;

public class MapGenFortressEmpty extends MapGenNetherBridge {

    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
        return false;
    }
}
