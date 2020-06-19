package omnikron13.justmetals.common;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.LinkedList;
import java.util.List;

@EventBusSubscriber
public class MoltenFluid extends Fluid {
    protected static List<MoltenFluid> Fluids = new LinkedList<>();

    protected String name;

    public MoltenFluid(String name, int temperature, int density, int viscosity) {
        super(
            "molten." + name,
            new ResourceLocation(JustMetals.MODID, "fluids/" + name + "/still"),
            new ResourceLocation(JustMetals.MODID, "fluids/" + name + "/flowing")
        );
        this.name = name;
        setGaseous(false);
        setTemperature(temperature);
        setDensity(density);
        setViscosity(viscosity);
        // This pegs lava (1473k) at 15, and mercury (room temperature, 20c/293k) at 0
        setLuminosity(Math.round((temperature-293)/79));
        Fluids.add(this);
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        for(MoltenFluid f : Fluids) {
            FluidRegistry.registerFluid(f);
            FluidRegistry.addBucketForFluid(f);
            f.setBlock(new BlockFluidClassic(f, Material.LAVA).setRegistryName("molten." + f.name));
            event.getRegistry().register(f.getBlock());
        }
    }

    @Override
    public SoundEvent getEmptySound() {
        return SoundEvents.ITEM_BUCKET_EMPTY;
    }

    @Override
    public SoundEvent getFillSound() {
        return SoundEvents.ITEM_BUCKET_FILL;
    }

    @Override
    public boolean doesVaporize(FluidStack fluidStack) {
        return false;
    }
}
