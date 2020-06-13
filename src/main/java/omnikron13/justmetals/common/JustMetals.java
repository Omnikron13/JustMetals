package omnikron13.justmetals.common;

import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.ArrayList;
import java.util.List;

@Mod(modid="justmetals", useMetadata=true)
@EventBusSubscriber
public final class JustMetals {
    public static final String MODID = "justmetals";
    
    public static List<Metal> Metals = new ArrayList<Metal>();
    
    @Instance
    public static JustMetals instance;

    static {
        FluidRegistry.enableUniversalBucket();
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        // TODO: Work out proper viscosity
        Metals.add(new Metal("lithium", 1, 1, 545, 512, 1500));
    }
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        for(Metal m : Metals) {
            m.registerSmeltingRecipes();
        }
    }
}
