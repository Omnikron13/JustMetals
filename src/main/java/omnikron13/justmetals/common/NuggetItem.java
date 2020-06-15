package omnikron13.justmetals.common;

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

import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber
public class NuggetItem extends Item {
    public static final String TYPE = "nugget";

    protected static List<NuggetItem> Items = new ArrayList<NuggetItem>();

    protected String name;

    public NuggetItem(String name) {
        this.name = name;
        setUnlocalizedName(TYPE + "." + name);
        setRegistryName(TYPE + "." + name);
        setCreativeTab(CreativeTabs.MISC);
        Items.add(this);
    }
    
    @SubscribeEvent
    public static void registerItems(Register<Item> event) {
        for(NuggetItem i : Items) {
            event.getRegistry().register(i);
            OreDictionary.registerOre(TYPE + StringUtils.capitalize(i.name), i);
        }
    }
    
    @SubscribeEvent
	public static void registerRenders(ModelRegistryEvent event) {
        for(NuggetItem i : Items) {
            ModelResourceLocation mrl = new ModelResourceLocation(JustMetals.MODID + ":" + i.name + "/nugget", "inventory");
            ModelLoader.setCustomModelResourceLocation(i, 0, mrl);
        }
	}
}
