package mrthomas20121.tfc_tinker.objects.blocks;

import mrthomas20121.tfc_tinker.TFC_Tinker;
import mrthomas20121.tfc_tinker.objects.fluids.FluidsTFCTinker;
import net.dries007.tfc.objects.blocks.BlockFluidTFC;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.BlockFluidClassic;

public class TFCTinkerBlocks {

    public static BlockFluidClassic clayBlock;

    public static void init(RegistryEvent.Register<Block> event)
    {
        BlockFluidClassic blockFluidTFC = new BlockFluidClassic(FluidsTFCTinker.clay.get(), Material.CLAY);
        blockFluidTFC.setRegistryName(TFC_Tinker.MODID, "fluid/"+FluidsTFCTinker.clay.get().getName());
        blockFluidTFC.setTranslationKey(TFC_Tinker.MODID+".fluid.clay");
        event.getRegistry().register(blockFluidTFC);
        clayBlock = blockFluidTFC;
    }
}
