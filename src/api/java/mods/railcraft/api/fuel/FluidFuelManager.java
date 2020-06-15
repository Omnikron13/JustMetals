/*------------------------------------------------------------------------------
 Copyright (c) CovertJaguar, 2011-2019

 This work (the API) is licensed under the "MIT" License,
 see LICENSE.md for details.
 -----------------------------------------------------------------------------*/

package mods.railcraft.api.fuel;

import mods.railcraft.api.core.RailcraftConstantsAPI;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * @author CovertJaguar <http://www.railcraft.info>
 */
@SuppressWarnings("WeakerAccess")
public final class FluidFuelManager {

    private static final Logger logger = LogManager.getLogger(RailcraftConstantsAPI.MOD_ID);
    private static final Map<FluidStack, Integer> boilerFuel = new HashMap<>();

    public static void addFuel(Fluid fluid, int heatValuePerBucket) {
        addFuel(new FluidStack(fluid, 1), heatValuePerBucket);
    }

    /**
     * Register the amount of heat in a bucket of liquid fuel.
     *
     * @param fluid              the fluid
     * @param heatValuePerBucket the amount of "heat" per bucket of fuel
     */
    public static void addFuel(FluidStack fluid, int heatValuePerBucket) {
        if (fluid == null) {
            logger.log(Level.WARN, "An invalid fluid type was provided during registration of fluid fuel", new IllegalArgumentException("null fluid stack"));
            return;
        }
        FluidStack toSave = fluid.copy();
        toSave.amount = 1; // hashcode uses this
        boilerFuel.put(toSave, heatValuePerBucket);
    }

    @Deprecated // Use fluid stack aware version
    public static int getFuelValue(Fluid fluid) {
        FluidStack key = new FluidStack(fluid, 1);
        return boilerFuel.getOrDefault(key, 0);
    }

    public static int getFuelValue(FluidStack fluid) {
        FluidStack key = fluid.copy();
        key.amount = 1; // hashcode
        return boilerFuel.getOrDefault(key, 0);
    }

    public static double getFuelValueForSize(FluidStack fluid) {
        int amount = fluid.amount;
        return getFuelValue(fluid) * amount / 1000D;
    }

}
