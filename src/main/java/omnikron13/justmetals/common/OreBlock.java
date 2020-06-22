package omnikron13.justmetals.common;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.BlockRenderLayer;

public class OreBlock extends BaseBlock {
    protected String TYPE() { return "ore"; }

    public static final float HARDNESS = 3f;
    public static final float BLAST_RESISTANCE = 3f;

    public OreBlock(String name, int mining_level) {
        super(name, Material.ROCK, mining_level);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        setSoundType(SoundType.STONE);
        setHardness(HARDNESS);
        setResistance(BLAST_RESISTANCE);
    }

    // This is necessary to do the base rock + ore texture thing
    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }
}
