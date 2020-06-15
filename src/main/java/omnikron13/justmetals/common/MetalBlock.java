package omnikron13.justmetals.common;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
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
public class MetalBlock extends Block {
    public static final String TYPE = "block";
    public static final float HARDNESS = 1.5f;
    public static final float BLAST_RESISTANCE = 3f;
    public static final String TOOL = "pickaxe";

    protected static List<MetalBlock> Blocks = new ArrayList<>();
    protected static List<ItemBlock> ItemBlocks = new ArrayList<>();

    protected String name;
    public ItemBlock itemBlock;

    public MetalBlock(String name, int mining_level) {
        super(Material.IRON);
        this.name = name;
        setRegistryName(TYPE + "." + name);
        setUnlocalizedName(TYPE + "." + name);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        setSoundType(SoundType.METAL);
        setHardness(HARDNESS);
        setResistance(BLAST_RESISTANCE);
        setHarvestLevel(TOOL, mining_level);
        itemBlock = new ItemBlock(this);
        itemBlock.setRegistryName(getRegistryName());
        Blocks.add(this);
    }
    
    @SubscribeEvent
    public static void registerBlocks(Register<Block> event) {   
        for(MetalBlock b : Blocks) {
            event.getRegistry().register(b);
        }
    }
    
    @SubscribeEvent
    public static void registerItemBlocks(Register<Item> event) {
        for(MetalBlock b : Blocks) {
            event.getRegistry().register(b.itemBlock);
            ItemBlocks.add(b.itemBlock);
            OreDictionary.registerOre(TYPE + StringUtils.capitalize(b.name), b.itemBlock);
        }
    }
    
    @SubscribeEvent
    public static void registerRenders(ModelRegistryEvent event) {
        for(MetalBlock b : Blocks) {
            ModelResourceLocation mrl = new ModelResourceLocation(JustMetals.MODID + ":" + b.name + "/" + TYPE, "inventory");
            ModelLoader.setCustomModelResourceLocation(b.itemBlock, 0, mrl);
        }
    }
    
    // This is necessary to do the base rock + ore texture thing
    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }
}
