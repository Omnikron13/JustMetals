package omnikron13.justmetals.common;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid="justmetals", useMetadata=true)
@EventBusSubscriber
public final class JustMetals {
    public static final String MODID = "justmetals";
    
    @Instance
    public static JustMetals instance;
    
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        new OreBlock("lithium", 1);
    }
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
    }
}
