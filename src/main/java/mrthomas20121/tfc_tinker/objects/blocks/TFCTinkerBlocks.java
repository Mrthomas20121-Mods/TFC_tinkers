package mrthomas20121.tfc_tinker.objects.blocks;

import mrthomas20121.tfc_tinker.TFC_Tinker;
import mrthomas20121.tfc_tinker.objects.fluids.FluidsTFCTinker;
import net.dries007.tfc.objects.blocks.BlockFluidTFC;
import net.dries007.tfc.objects.fluids.properties.FluidWrapper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import slimeknights.tconstruct.smeltery.block.BlockTinkerFluid;

import java.util.ArrayList;

public class TFCTinkerBlocks {

    private static ArrayList<Block> fluidBlocks = new ArrayList<>();

    public static ArrayList<Block> getFluidBlocks() {
        return fluidBlocks;
    }

    public static void init(RegistryEvent.Register<Block> event)
    {
        fluidBlocks.add(register(event.getRegistry(), "fluid/clay", new BlockTinkerFluid(FluidRegistry.getFluid("clay"), Material.WATER)));
    }

    private static <T extends Block> T register(IForgeRegistry<Block> r, String name, T block)
    {
        block.setRegistryName(TFC_Tinker.MODID, name);
        block.setTranslationKey(TFC_Tinker.MODID + "." + name.replace('/', '.'));
        r.register(block);
        return block;
    }
}
