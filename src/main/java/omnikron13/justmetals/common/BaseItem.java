package omnikron13.justmetals.common;

import lombok.Getter;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.commons.lang3.StringUtils;

import java.util.LinkedList;
import java.util.List;

@Mod.EventBusSubscriber
public abstract class BaseItem extends Item {
    /**
     * Identifies the basic type of the item, e.g. ingot, nugget, etc.
     * As would be used in the most significant position of an ore dictionary string.
     * @return the type string
     */
    protected abstract String TYPE();

    /** Hold a reference to each created instance, primarily for registration later on. */
    protected static List<BaseItem> Items = new LinkedList<>();

    @Getter protected String name;
    @Getter protected String oreDict;

    public BaseItem(String name) {
        this.name = name;
        oreDict = TYPE() + StringUtils.capitalize(name);
        setTranslationKey(TYPE() + "." + name);
        setRegistryName(TYPE() + "." + name);
        Items.add(this);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        for(BaseItem i : Items) {
            event.getRegistry().register(i);
            OreDictionary.registerOre(i.oreDict, i);
        }
    }

    @SubscribeEvent
    public static void registerRenders(ModelRegistryEvent event) {
        for(BaseItem i : Items) {
            ModelResourceLocation mrl = new ModelResourceLocation(JustMetals.MODID + ":" + i.name + "/" + i.TYPE(), "inventory");
            ModelLoader.setCustomModelResourceLocation(i, 0, mrl);
        }
    }
}
