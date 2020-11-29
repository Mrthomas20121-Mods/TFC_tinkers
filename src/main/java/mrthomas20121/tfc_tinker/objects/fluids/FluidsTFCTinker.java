package mrthomas20121.tfc_tinker.objects.fluids;

import mrthomas20121.rocksalt.utils.FluidUtils;
import net.dries007.tfc.objects.fluids.FluidsTFC;
import net.dries007.tfc.objects.fluids.properties.FluidWrapper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import javax.annotation.Nonnull;

import static net.dries007.tfc.TerraFirmaCraft.MOD_ID;

public class FluidsTFCTinker {

    private static final ResourceLocation STILL = new ResourceLocation(MOD_ID, "blocks/fluid_still");
    private static final ResourceLocation FLOW = new ResourceLocation(MOD_ID, "blocks/fluid_flow");

    public static FluidWrapper clay;

    public static void registerFluids()
    {
        clay = registerFluid(new Fluid("clay", STILL, FLOW, 0xB1B2B6));
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
