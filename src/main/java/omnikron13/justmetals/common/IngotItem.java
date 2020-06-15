package omnikron13.justmetals.common;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.commons.lang3.StringUtils;

@EventBusSubscriber
public class IngotItem extends Item {
    protected static List<IngotItem> Items = new ArrayList<>();
    
    protected String name;
    
    public IngotItem(String name) {
        this.name = name;
        setUnlocalizedName("ingot." + name);
        setRegistryName("ingot." + name);
        setCreativeTab(CreativeTabs.MISC);
        Items.add(this);
    }
    
    @SubscribeEvent
    public static void registerItems(Register<Item> event) {
        for(IngotItem i : Items) {
            event.getRegistry().register(i);
            OreDictionary.registerOre("ingot" + StringUtils.capitalize(i.name), i);
        }
    }
    
    @SubscribeEvent
	public static void registerRenders(ModelRegistryEvent event) {
        for(IngotItem i : Items) {
            ModelResourceLocation mrl = new ModelResourceLocation(JustMetals.MODID + ":" + i.name + "/ingot", "inventory");
            ModelLoader.setCustomModelResourceLocation(i, 0, mrl);
        }
	}
}
