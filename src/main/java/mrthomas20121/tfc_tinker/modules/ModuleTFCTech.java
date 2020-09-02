package mrthomas20121.tfc_tinker.modules;

import mrthomas20121.biolib.common.SmelteryUtils;
import net.dries007.tfc.api.types.Metal;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import slimeknights.tconstruct.library.materials.Material;
import tfctech.objects.items.metal.ItemTechMetal;
import tfctech.objects.items.glassworking.ItemBlowpipe;

import static mrthomas20121.tfc_tinker.items.Items.*;

public class ModuleTFCTech {
    public static void castRecipes(Metal metal, Fluid castingFluid)
    {
        SmelteryUtils.registerCasting(new ItemStack(castWire), new ItemStack(ItemTechMetal.get(metal, ItemTechMetal.ItemType.WIRE)), castingFluid, Material.VALUE_Ingot*2);
        SmelteryUtils.registerCasting(new ItemStack(castRackwheel), new ItemStack(ItemTechMetal.get(metal, ItemTechMetal.ItemType.RACKWHEEL)), castingFluid, Material.VALUE_Ingot*2);
        SmelteryUtils.registerCasting(new ItemStack(castRackwheelPiece), new ItemStack(ItemTechMetal.get(metal, ItemTechMetal.ItemType.RACKWHEEL_PIECE)), castingFluid, Material.VALUE_Ingot*2);
        SmelteryUtils.registerCasting(new ItemStack(castBlowpipe), new ItemStack(ItemBlowpipe.get(metal)), castingFluid, Material.VALUE_Ingot*2);
        SmelteryUtils.registerCasting(new ItemStack(castLongRod), new ItemStack(ItemTechMetal.get(metal, ItemTechMetal.ItemType.LONG_ROD)), castingFluid, Material.VALUE_Ingot*2);

    }
    public static void castingRecipes(Metal metal, Fluid fluid)
    {
        SmelteryUtils.registerCasting(new ItemStack(ItemTechMetal.get(metal, ItemTechMetal.ItemType.WIRE)), new ItemStack(castWire), fluid, Material.VALUE_Ingot);
        SmelteryUtils.registerCasting(new ItemStack(ItemTechMetal.get(metal, ItemTechMetal.ItemType.RACKWHEEL)), new ItemStack(castRackwheel), fluid, Material.VALUE_Ingot*4);
        SmelteryUtils.registerCasting(new ItemStack(ItemTechMetal.get(metal, ItemTechMetal.ItemType.RACKWHEEL_PIECE)), new ItemStack(castRackwheelPiece), fluid, Material.VALUE_Ingot);
        SmelteryUtils.registerCasting(new ItemStack(ItemTechMetal.get(metal, ItemTechMetal.ItemType.LONG_ROD)), new ItemStack(castLongRod), fluid, Material.VALUE_Ingot);
        SmelteryUtils.registerCasting(new ItemStack(ItemTechMetal.get(metal, ItemTechMetal.ItemType.LONG_ROD)), new ItemStack(castLongRod), fluid, Material.VALUE_Ingot);
    }
}
