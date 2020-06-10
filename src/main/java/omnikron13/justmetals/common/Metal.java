package omnikron13.justmetals.common;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Metal {
    protected String name;
    protected float smeltingXP;
    
    public Block ore;
    public Item ingot;
    
    public Metal(String name, int mining_level, float smeltingXP) {
        this.name = name;
        this.smeltingXP = smeltingXP;
        ore = new OreBlock(name, mining_level);
        ingot = new IngotItem(name);
    }
    
    public Metal(String name, int mining_level) {
        this(name, mining_level, 1.0f);
    }
    
    public void registerSmeltingRecipes() {
        GameRegistry.addSmelting(ore, new ItemStack(ingot, 1), smeltingXP);
    }
}