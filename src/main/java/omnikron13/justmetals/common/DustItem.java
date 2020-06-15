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
public class DustItem extends Item {
    public static final String TYPE = "dust";

    protected static List<DustItem> Items = new ArrayList<DustItem>();

    protected String name;

    public DustItem(String name) {
        this.name = name;
        setUnlocalizedName(TYPE + "." + name);
        setRegistryName(TYPE + "." + name);
        setCreativeTab(CreativeTabs.MISC);
        Items.add(this);
    }
    
    @SubscribeEvent
    public static void registerItems(Register<Item> event) {
        for(DustItem i : Items) {
            event.getRegistry().register(i);
            OreDictionary.registerOre(TYPE + StringUtils.capitalize(i.name), i);
        }
    }
    
    @SubscribeEvent
	public static void registerRenders(ModelRegistryEvent event) {
        for(DustItem i : Items) {
            ModelResourceLocation mrl = new ModelResourceLocation(JustMetals.MODID + ":" + i.name + "/dust", "inventory");
            ModelLoader.setCustomModelResourceLocation(i, 0, mrl);
        }
	}
}
