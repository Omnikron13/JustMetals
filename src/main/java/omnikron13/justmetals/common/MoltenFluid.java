package omnikron13.justmetals.common;

import net.minecraft.block.Block;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber
public class MoltenFluid extends Fluid {
    protected static List<MoltenFluid> Fluids = new ArrayList<MoltenFluid>();

    protected String name;

    public MoltenFluid(String name) {
        super(
            "molten." + name,
            new ResourceLocation(JustMetals.MODID, "fluids/molten." + name + ".still"),
            new ResourceLocation(JustMetals.MODID, "fluids/molten." + name + ".flowing")
        );
        this.name = name;
        this.setColor(0xFFFFFFFF);
        this.setGaseous(false);
        Fluids.add(this);
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        for(MoltenFluid f : Fluids) {
            FluidRegistry.registerFluid(f);
            FluidRegistry.addBucketForFluid(f);
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
