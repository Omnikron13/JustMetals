package omnikron13.justmetals.common;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class OreBlock extends Block {
    public static final float HARDNESS = 3f;
    public static final float BLAST_RESISTANCE = 3f;
    public static final String TOOL = "pickaxe";
    
    protected static List<OreBlock> Blocks = new ArrayList<OreBlock>();
    protected static List<Item> ItemBlocks = new ArrayList<Item>();
    
    public OreBlock(String name, int mining_level) {
        super(Material.ROCK);
        setRegistryName("ore." + name);
        setUnlocalizedName("ore." + name);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        setSoundType(SoundType.STONE);
        setHardness(HARDNESS);
        setResistance(BLAST_RESISTANCE);
        setHarvestLevel(TOOL, mining_level);
        Blocks.add(this);
    }
    
    @SubscribeEvent
    public static void registerBlocks(Register<Block> event) {   
        for(Block b : Blocks) {
            event.getRegistry().register(b);
        }
    }
    
    @SubscribeEvent
    public static void registerItemBlocks(Register<Item> event) {
        for(Block b : Blocks) {
            Item ib = new ItemBlock(b).setRegistryName(b.getRegistryName());
            event.getRegistry().register(ib);
            ItemBlocks.add(ib);
        }
    }
    
    @SubscribeEvent
    public static void registerRenders(ModelRegistryEvent event) {
        for(Item i : ItemBlocks) {
            ModelResourceLocation mrl = new ModelResourceLocation(i.getRegistryName(), "inventory");
            ModelLoader.setCustomModelResourceLocation(i, 0, mrl);
        }
    }
    
    // This is necessary to do the base rock + ore texture thing
    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }
}
