package net.darkhax.noworldgen5you.world.gen;

import net.minecraft.world.gen.structure.MapGenStronghold;

public class MapGenStrongholdEmpty extends MapGenStronghold {

    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
        return false;
    }
}
