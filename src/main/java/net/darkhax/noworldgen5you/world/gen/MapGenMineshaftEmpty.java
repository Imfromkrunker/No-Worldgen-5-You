package net.darkhax.noworldgen5you.world.gen;

import net.minecraft.world.gen.structure.MapGenMineshaft;

public class MapGenMineshaftEmpty extends MapGenMineshaft {

    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
        return false;
    }
}
