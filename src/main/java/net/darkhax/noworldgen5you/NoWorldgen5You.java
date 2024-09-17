package net.darkhax.noworldgen5you;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.EventBus;
import cpw.mods.fml.common.eventhandler.EventPriority;
import net.darkhax.noworldgen5you.world.gen.MapGenEmpty;
import net.darkhax.noworldgen5you.world.gen.MapGenFortressEmpty;
import net.darkhax.noworldgen5you.world.gen.MapGenMineshaftEmpty;
import net.darkhax.noworldgen5you.world.gen.MapGenScatteredFeaturesEmpty;
import net.darkhax.noworldgen5you.world.gen.MapGenStrongholdEmpty;
import net.darkhax.noworldgen5you.world.gen.MapGenVillageEmpty;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = "noworldgen5you", name = "No Worldgen 5 You", version = "1.0")
public class NoWorldgen5You {

    private static final Logger LOG = LogManager.getLogger("No Worldgen 5 You");

    // Done early for config reasons
    private static MapGenScatteredFeaturesEmpty SCATTERED_GEN;

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {

        WorldgenConfig.initConfig(event.getSuggestedConfigurationFile());
        MinecraftForge.TERRAIN_GEN_BUS.register(this);

        // Initialize custom world generators
        SCATTERED_GEN = new MapGenScatteredFeaturesEmpty();
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void onChunkPopulated(Populate event) {

        if (WorldgenConfig.isPopulateDisabled(event.type.name().toLowerCase())) {

            event.setResult(Result.DENY);
        }
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void onMapGen(InitMapGenEvent event) {

        if (!WorldgenConfig.isStructureDisabled(event.type.name().toLowerCase())) {
            return;
        }

        switch (event.type) {

            case CAVE:
                event.newGen = new MapGenEmpty();
                break;

            case MINESHAFT:
                event.newGen = new MapGenMineshaftEmpty();
                break;

            case NETHER_BRIDGE:
                event.newGen = new MapGenFortressEmpty();
                break;

            case RAVINE:
                event.newGen = new MapGenEmpty();
                break;

            case SCATTERED_FEATURE:
                event.newGen = SCATTERED_GEN;
                break;

            case STRONGHOLD:
                event.newGen = new MapGenStrongholdEmpty();
                break;

            case VILLAGE:
                event.newGen = new MapGenVillageEmpty();
                break;

            default:
                break;
        }
    }
}
