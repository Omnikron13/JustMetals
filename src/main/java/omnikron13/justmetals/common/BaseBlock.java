package omnikron13.justmetals.common;

import lombok.Getter;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
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
public abstract class BaseBlock extends Block {
    /**
     * Identifies the basic type of the block, e.g. ore, plate, etc.
     * As would be used in the most significant position of an ore dictionary string.
     * @return the type string
     */
    protected abstract String TYPE();

    protected static List<BaseBlock> Blocks = new LinkedList<>();
    protected static List<ItemBlock> ItemBlocks = new LinkedList<>();

    // 'Name' here is the element/type of metal.
    @Getter protected String name;
    @Getter protected String oreDict;
    @Getter protected ItemBlock itemBlock;

    public BaseBlock(String name, Material material, int mining_level) {
        super(material);
        this.name = name;
        oreDict = TYPE() + StringUtils.capitalize(name);
        setRegistryName(TYPE() + "." + name);
        setUnlocalizedName(TYPE() + "." + name);
        setHarvestLevel("pickaxe", mining_level);
        itemBlock = new ItemBlock(this);
        itemBlock.setRegistryName(getRegistryName());
        Blocks.add(this);
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        for(BaseBlock b : Blocks) {
            event.getRegistry().register(b);
        }
    }

    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
        for(BaseBlock b : Blocks) {
            event.getRegistry().register(b.itemBlock);
            ItemBlocks.add(b.itemBlock);
            OreDictionary.registerOre(b.oreDict, b.itemBlock);
        }
    }

    @SubscribeEvent
    public static void registerRenders(ModelRegistryEvent event) {
        for(BaseBlock b : Blocks) {
            ModelResourceLocation mrl = new ModelResourceLocation(JustMetals.MODID + ":" + b.name + "/" + b.TYPE(), "inventory");
            ModelLoader.setCustomModelResourceLocation(b.itemBlock, 0, mrl);
        }
    }
}
