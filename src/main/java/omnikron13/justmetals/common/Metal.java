package omnikron13.justmetals.common;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.NonNullList;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreIngredient;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber
public class Metal {
    protected String name;
    protected float smeltingXP;

    public OreBlock ore;
    public MetalBlock block;
    public IngotItem ingot;
    public NuggetItem nugget;
    public DustItem dust;
    public MoltenFluid moltenFluid;

    public static List<IRecipe> Recipes = new ArrayList<>();
    
    public Metal(String name, int mining_level, float smeltingXP, int moltenTemperature, int moltenDensity, int moltenViscosity) {
        this.name = name;
        this.smeltingXP = smeltingXP;
        ore = new OreBlock(name, mining_level);
        block = new MetalBlock(name, mining_level);
        ingot = new IngotItem(name);
        nugget = new NuggetItem(name);
        dust = new DustItem(name);
        moltenFluid = new MoltenFluid(name, moltenTemperature, moltenDensity, moltenViscosity);
        addRecipes();
    }
    
    public void registerSmeltingRecipes() {
        GameRegistry.addSmelting(ore, new ItemStack(ingot, 1), smeltingXP);
        GameRegistry.addSmelting(dust, new ItemStack(ingot, 1), smeltingXP);
    }

    protected void addRecipes() {
        addNuggetRecipe();
        addBlockRecipe();
        addIngotFromBlockRecipe();
    }

    // TODO: refactor for code reuse
    protected void addNuggetRecipe() {
        NonNullList<Ingredient> ingredients = NonNullList.create();
        ingredients.add(new OreIngredient("ingot" + StringUtils.capitalize(name)));
        ShapelessRecipes r = new ShapelessRecipes(JustMetals.MODID + ":nugget." + name, new ItemStack(nugget, 9), ingredients);
        r.setRegistryName(JustMetals.MODID, "nugget." + name);
        Recipes.add(r);
    }

    protected void addBlockRecipe() {
        NonNullList<Ingredient> ingredients = NonNullList.create();
        OreIngredient ingotDict = new OreIngredient("ingot" + StringUtils.capitalize(name));
        for(int n = 0; n < 9; n++) {
            ingredients.add(ingotDict);
        }
        ShapelessRecipes r = new ShapelessRecipes(JustMetals.MODID + ":block." + name, new ItemStack(block.itemBlock, 1), ingredients);
        r.setRegistryName(JustMetals.MODID, "block." + name);
        Recipes.add(r);
    }

    protected void addIngotFromBlockRecipe() {
        NonNullList<Ingredient> ingredients = NonNullList.create();
        ingredients.add(new OreIngredient("block" + StringUtils.capitalize(name)));
        ShapelessRecipes r = new ShapelessRecipes(JustMetals.MODID + ":ingot." + name + ".fromblock", new ItemStack(ingot, 9), ingredients);
        r.setRegistryName(JustMetals.MODID, "ingot." + name + ".fromblock");
        Recipes.add(r);
    }

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        for(IRecipe r : Recipes) {
            event.getRegistry().register(r);
        }
    }
}