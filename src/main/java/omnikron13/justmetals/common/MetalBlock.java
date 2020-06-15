package omnikron13.justmetals.common;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class MetalBlock extends BaseBlock {
    protected String TYPE() { return "block"; }

    public static final float HARDNESS = 1.5f;
    public static final float BLAST_RESISTANCE = 3f;

    public MetalBlock(String name, int mining_level) {
        super(name, Material.IRON, mining_level);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        setSoundType(SoundType.METAL);
        setHardness(HARDNESS);
        setResistance(BLAST_RESISTANCE);
    }
}
