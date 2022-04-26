package mrthomas20121.tfc_tinkers.init;

import mrthomas20121.tfc_tinkers.TFCTinkers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import slimeknights.mantle.registration.deferred.FluidDeferredRegister;
import slimeknights.mantle.registration.object.FluidObject;

public class Fluids {

    public static final FluidDeferredRegister FLUIDS = new FluidDeferredRegister(TFCTinkers.mod_id);

    public static FluidObject<ForgeFlowingFluid> ethanol = FLUIDS.register("ethanol", hotBuilder("ethanol", 1500), Material.WATER, 0);


    private static FluidAttributes.Builder hotBuilder(String name, int temp) {
        return FluidAttributes.builder(new ResourceLocation(String.format("tfc_tinkers:block/fluids/%s_still", name)), new ResourceLocation(String.format("tfc_tinkers:block/fluids/%s_flow", name))).density(2000).viscosity(10000).temperature(temp).sound(SoundEvents.BUCKET_FILL_LAVA, SoundEvents.BUCKET_EMPTY_LAVA);
    }
}
