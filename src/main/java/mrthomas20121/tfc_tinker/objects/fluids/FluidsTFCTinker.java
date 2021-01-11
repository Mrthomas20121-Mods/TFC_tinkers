package mrthomas20121.tfc_tinker.objects.fluids;

import net.dries007.tfc.objects.fluids.FluidsTFC;
import net.dries007.tfc.objects.fluids.properties.FluidWrapper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import slimeknights.tconstruct.library.fluid.FluidColored;

import javax.annotation.Nonnull;

import java.util.ArrayList;

public class FluidsTFCTinker {

    private static final ResourceLocation STILL = new ResourceLocation("tfc", "blocks/fluid_still");
    private static final ResourceLocation FLOW = new ResourceLocation("tfc", "blocks/fluid_flow");

    // water type fluids
    private static ArrayList<FluidWrapper> waterFluids = new ArrayList<>();

    public static ArrayList<FluidWrapper> getWaterFluids() {
        return waterFluids;
    }

    public static void registerFluids()
    {
        waterFluids.add(registerFluid(new FluidColored("clay", 0xB1B2B6, STILL, FLOW)));
    }

    @Nonnull
    private static FluidWrapper registerFluid(@Nonnull Fluid newFluid)
    {
        boolean isDefault = !FluidRegistry.isFluidRegistered(newFluid.getName());

        if (!isDefault)
        {
            // Fluid was already registered with this name, default to that fluid
            newFluid = FluidRegistry.getFluid(newFluid.getName());
        }
        else
        {
            // No fluid found we are safe to register our default
            FluidRegistry.registerFluid(newFluid);
        }
        FluidRegistry.addBucketForFluid(newFluid);
        return FluidsTFC.getWrapper(newFluid);
    }
}
