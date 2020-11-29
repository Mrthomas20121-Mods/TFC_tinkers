package mrthomas20121.tfc_tinker.compat.tfctech;

import mrthomas20121.biolib.common.SmelteryUtils;
import mrthomas20121.rocksalt.utils.MetalUtils;
import mrthomas20121.tfc_tinker.objects.Cast;
import mrthomas20121.tfc_tinker.objects.items.ItemCast;
import mrthomas20121.tfc_tinker.objects.items.TFCTinkerItems;
import net.dries007.tfc.api.recipes.WeldingRecipe;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.objects.inventory.ingredient.IIngredient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.registries.IForgeRegistry;
import slimeknights.tconstruct.library.materials.Material;
import tfctech.objects.items.glassworking.ItemBlowpipe;
import tfctech.objects.items.metal.ItemTechMetal;

public class GeneralCompat {

    public static void onWeldingRecipeEvent(IForgeRegistry<WeldingRecipe> r, Metal metal, String metalName, Metal castMetal)
    {
        r.register(new WeldingRecipe(new ResourceLocation("tfc", metalName+"_"+Cast.BOLT.name().toLowerCase()+"_from_"+metal.getRegistryName().getPath()), IIngredient.of(ItemCast.get(castMetal, Cast.BLANK)), IIngredient.of(ItemTechMetal.get(metal, ItemTechMetal.ItemType.BOLT)), ItemCast.get(castMetal, Cast.BOLT, 1), metal.getTier()));
        r.register(new WeldingRecipe(new ResourceLocation("tfc", metalName+"_"+Cast.LONG_ROD.name().toLowerCase()+"_from_"+metal.getRegistryName().getPath()), IIngredient.of(ItemCast.get(castMetal, Cast.BLANK)), IIngredient.of(ItemTechMetal.get(metal, ItemTechMetal.ItemType.LONG_ROD)), ItemCast.get(castMetal, Cast.LONG_ROD, 1), metal.getTier()));
        r.register(new WeldingRecipe(new ResourceLocation("tfc", metalName+"_"+Cast.RACKWHEEL_PIECE.name().toLowerCase()+"_from_"+metal.getRegistryName().getPath()), IIngredient.of(ItemCast.get(castMetal, Cast.BLANK)), IIngredient.of(ItemTechMetal.get(metal, ItemTechMetal.ItemType.RACKWHEEL_PIECE)), ItemCast.get(castMetal, Cast.RACKWHEEL_PIECE, 1), metal.getTier()));
        r.register(new WeldingRecipe(new ResourceLocation("tfc", metalName+"_"+Cast.RACKWHEEL.name().toLowerCase()+"_from_"+metal.getRegistryName().getPath()), IIngredient.of(ItemCast.get(castMetal, Cast.BLANK)), IIngredient.of(ItemTechMetal.get(metal, ItemTechMetal.ItemType.RACKWHEEL)), ItemCast.get(castMetal, Cast.RACKWHEEL, 1), metal.getTier()));
        r.register(new WeldingRecipe(new ResourceLocation("tfc", metalName+"_"+Cast.WIRE.name().toLowerCase()+"_from_"+metal.getRegistryName().getPath()), IIngredient.of(ItemCast.get(castMetal, Cast.BLANK)), IIngredient.of(ItemTechMetal.get(metal, ItemTechMetal.ItemType.WIRE)), ItemCast.get(castMetal, Cast.WIRE, 1), metal.getTier()));
        r.register(new WeldingRecipe(new ResourceLocation("tfc", metalName+"_"+Cast.SCREW.name().toLowerCase()+"_from_"+metal.getRegistryName().getPath()), IIngredient.of(ItemCast.get(castMetal, Cast.BLANK)), IIngredient.of(ItemTechMetal.get(metal, ItemTechMetal.ItemType.SCREW)), ItemCast.get(castMetal, Cast.SCREW, 1), metal.getTier()));
        r.register(new WeldingRecipe(new ResourceLocation("tfc", metalName+"_"+Cast.BLOWPIPE.name().toLowerCase()+"_from_"+metal.getRegistryName().getPath()), IIngredient.of(ItemCast.get(castMetal, Cast.BLANK)), IIngredient.of(ItemBlowpipe.get(metal)), ItemCast.get(castMetal, Cast.BLOWPIPE, 1), metal.getTier()));
    }

    public static void onRecipeEvent(RegistryEvent.Register<IRecipe> event)
    {
        for(Metal metal: TFCRegistries.METALS.getValuesCollection())
        {
            for(String metalName : TFCTinkerItems.metals)
            {
                if(ObfuscationReflectionHelper.getPrivateValue(Metal.class, metal, "usable").equals(false))
                    continue;

                Metal castMetal = MetalUtils.getMetal(metalName);
                Fluid fluid = FluidRegistry.getFluid(metalName);
                SmelteryUtils.registerCasting(new ItemStack(ItemTechMetal.get(metal, ItemTechMetal.ItemType.WIRE)), ItemCast.get(castMetal, Cast.WIRE, 1), fluid, Material.VALUE_Ingot);
                SmelteryUtils.registerCasting(new ItemStack(ItemTechMetal.get(metal, ItemTechMetal.ItemType.RACKWHEEL)), ItemCast.get(castMetal, Cast.RACKWHEEL, 1), fluid, Material.VALUE_Ingot*4);
                SmelteryUtils.registerCasting(new ItemStack(ItemTechMetal.get(metal, ItemTechMetal.ItemType.RACKWHEEL_PIECE)), ItemCast.get(castMetal, Cast.RACKWHEEL_PIECE, 1), fluid, Material.VALUE_Ingot);
                SmelteryUtils.registerCasting(new ItemStack(ItemTechMetal.get(metal, ItemTechMetal.ItemType.LONG_ROD)), ItemCast.get(castMetal, Cast.LONG_ROD, 1), fluid, Material.VALUE_Ingot);
                SmelteryUtils.registerCasting(new ItemStack(ItemTechMetal.get(metal, ItemTechMetal.ItemType.BOLT)), ItemCast.get(castMetal, Cast.BOLT, 1), fluid, Material.VALUE_Ingot);
            }
        }
    }
}
