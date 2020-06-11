package omnikron13.justmetals.common;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Metal {
    protected String name;
    protected float smeltingXP;

    public Block ore;
    public Block block;
    public Item ingot;
    public Item nugget;
    public Item dust;
    
    public Metal(String name, int mining_level, float smeltingXP) {
        this.name = name;
        this.smeltingXP = smeltingXP;
        ore = new OreBlock(name, mining_level);
        block = new MetalBlock(name, mining_level);
        ingot = new IngotItem(name);
        nugget = new NuggetItem(name);
        dust = new DustItem(name);
    }
    
    public Metal(String name, int mining_level) {
        this(name, mining_level, 1.0f);
    }
    
    public void registerSmeltingRecipes() {
        GameRegistry.addSmelting(ore, new ItemStack(ingot, 1), smeltingXP);
        GameRegistry.addSmelting(dust, new ItemStack(ingot, 1), smeltingXP);
    }
}