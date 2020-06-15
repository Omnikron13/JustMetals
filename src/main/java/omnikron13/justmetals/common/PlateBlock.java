package omnikron13.justmetals.common;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class PlateBlock extends BaseBlock {
    protected String TYPE() { return "plate"; }

    private static final AxisAlignedBB BOUNDING_BOX = new AxisAlignedBB(0f, 0f, 0f, 1.0f, 0x0.1p0, 1.0f);

    public static final float HARDNESS = 1.5f;
    public static final float BLAST_RESISTANCE = 3f;

    public PlateBlock(String name, int mining_level, float hardness, float blast_resistance) {
        super(name, Material.IRON, mining_level);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        setSoundType(SoundType.METAL);
        setHardness(hardness);
        setResistance(blast_resistance);
    }

    public PlateBlock(String name, int mining_level) {
        this(name, mining_level, HARDNESS, BLAST_RESISTANCE);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) { return BOUNDING_BOX; }

    // TODO: investigate what the 'correct' way to achieve this is
    // Without this the block x-rays blocks next to it
    @Override
    public boolean isOpaqueCube(IBlockState state) { return false; }

    // TODO: investigate what the 'correct' way to achieve this is
    // Without this lighting on the top of the block is odd
    @Override
    public boolean isBlockNormalCube(IBlockState state) { return false; }

    // TODO: investigate what the 'correct' way to achieve this is
    // Without this the player is pushed off of the block
    @Override
    public boolean isNormalCube(IBlockState state) { return false; }

    // Without this short mobs suffocate when standing on plates
    @Override
    public boolean causesSuffocation(IBlockState state) { return false; }
}
