package omnikron13.justmetals.common;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
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
public class PlateBlock extends Block {
    private static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0f, 0f, 0f, 1.0f, 0x0.1p0, 1.0f);

    public static final String TYPE = "plate";
    public static final float HARDNESS = 1.5f;
    public static final float BLAST_RESISTANCE = 3f;
    public static final String TOOL = "pickaxe";

    protected static List<PlateBlock> Blocks = new ArrayList<PlateBlock>();
    protected static List<Item> ItemBlocks = new ArrayList<Item>();

    protected String name;
    public ItemBlock itemBlock;

    public PlateBlock(String name, int mining_level, float hardness, float blast_resistance) {
        super(Material.IRON);
        this.name = name;
        setRegistryName(TYPE + "." + name);
        setUnlocalizedName(TYPE + "." + name);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        setSoundType(SoundType.METAL);
        setHardness(hardness);
        setResistance(blast_resistance);
        setHarvestLevel(TOOL, mining_level);
        itemBlock = new ItemBlock(this);
        itemBlock.setRegistryName(getRegistryName());
        Blocks.add(this);
    }

    public PlateBlock(String name, int mining_level) {
        this(name, mining_level, HARDNESS, BLAST_RESISTANCE);
    }

    @SubscribeEvent
    public static void registerBlocks(Register<Block> event) {
        for (PlateBlock b : Blocks) {
            event.getRegistry().register(b);
        }
    }

    @SubscribeEvent
    public static void registerItemBlocks(Register<Item> event) {
        for (PlateBlock b : Blocks) {
            event.getRegistry().register(b.itemBlock);
            ItemBlocks.add(b.itemBlock);
            OreDictionary.registerOre(TYPE + StringUtils.capitalize(b.name), b.itemBlock);
        }
    }

    @SubscribeEvent
    public static void registerRenders(ModelRegistryEvent event) {
        for (Item i : ItemBlocks) {
            ModelResourceLocation mrl = new ModelResourceLocation(i.getRegistryName(), "inventory");
            ModelLoader.setCustomModelResourceLocation(i, 0, mrl);
        }
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return BOUNDING_BOX;
    }

    // TODO: investigate what the 'correct' way to achieve this is
    // Without this the block x-rays blocks next to it
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    // TODO: investigate what the 'correct' way to achieve this is
    // Without this lighting on the top of the block is odd
    @Override
    public boolean isBlockNormalCube(IBlockState state) {
        return false;
    }

    // TODO: investigate what the 'correct' way to achieve this is
    // Without this the player is pushed off of the block
    @Override
    public boolean isNormalCube(IBlockState state) {
        return false;
    }

    // Without this short mobs suffocate when standing on plates
    @Override
    public boolean causesSuffocation(IBlockState state) {
        return false;
    }

    // TODO: presumably override something else to prevent short mobs suffocating 'in' the block
}
