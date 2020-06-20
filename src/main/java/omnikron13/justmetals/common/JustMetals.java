package omnikron13.justmetals.common;

import com.amihaiemil.eoyaml.Yaml;
import com.amihaiemil.eoyaml.YamlMapping;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Mod(modid="justmetals", useMetadata=true)
@EventBusSubscriber
public final class JustMetals {
    public static final String MODID = "justmetals";
    
    public static List<Metal> Metals = new ArrayList<>();
    
    @Instance
    public static JustMetals instance;

    static {
        FluidRegistry.enableUniversalBucket();
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) throws IOException {
        Config.preInit(event);

        // Initialise metals
        for(File f : Config.getMetalsDir().toFile().listFiles()) {
            YamlMapping metal = Yaml.createYamlInput(f, true).readYamlMapping();
            YamlMapping ore = metal.yamlMapping("ore");
            YamlMapping block = metal.yamlMapping("block");
            YamlMapping plate = metal.yamlMapping("plate");
            YamlMapping molten = metal.yamlMapping("molten");
            Metals.add(new Metal(
                metal.string("name"),
                metal.integer("mining_level"),
                ore.integer("smelting_xp"),
                molten.integer("temperature"),
                molten.integer("density"),
                molten.integer("viscosity")
            ));
        }
    }
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        for(Metal m : Metals) {
            m.registerSmeltingRecipes();
        }
    }

    @Mod.EventHandler
    public void postInit(FMLInitializationEvent event) {
        // TODO: move this into a better place?
        for(Metal m : Metals) {
            m.addRollingMachineRecipes();
        }
    }
}
