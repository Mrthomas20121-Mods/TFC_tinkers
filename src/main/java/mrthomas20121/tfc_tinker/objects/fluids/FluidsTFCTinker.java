package mrthomas20121.tfc_tinker.objects.fluids;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import javax.annotation.Nonnull;

public class FluidsTFCTinker {

    private static final ResourceLocation STILL = new ResourceLocation("tfc", "blocks/fluid_still");
    private static final ResourceLocation FLOW = new ResourceLocation("tfc", "blocks/fluid_flow");

    public static void init()
    {
        registerFluid(new Fluid("clay", STILL, FLOW, 0xFFB1B2B6));
    }

    private static void registerFluid(@Nonnull Fluid newFluid)
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
    }
}
