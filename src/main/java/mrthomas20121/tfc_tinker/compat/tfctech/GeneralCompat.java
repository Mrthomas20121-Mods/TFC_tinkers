package mrthomas20121.tfc_tinker.compat.tfctech;

import mrthomas20121.biolib.library.SmelteryUtils;
import mrthomas20121.rocksalt.utils.MetalUtils;
import mrthomas20121.tfc_tinker.objects.Cast;
import mrthomas20121.tfc_tinker.objects.items.ItemCast;
import mrthomas20121.tfc_tinker.objects.items.TFCTinkerItems;
import net.dries007.tfc.api.recipes.WeldingRecipe;
import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.objects.inventory.ingredient.IIngredient;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.registries.IForgeRegistry;
import slimeknights.tconstruct.library.TinkerRegistry;
import tfctech.objects.items.glassworking.ItemBlowpipe;
import tfctech.objects.items.metal.ItemTechMetal;

public class GeneralCompat {

    public static void registerMelting(Metal metal, Fluid fluid)
    {
    }

    public static void onWeldingRecipeEvent(IForgeRegistry<WeldingRecipe> r, Metal metal, String metalName, Metal castMetal)
    {
        r.register(new WeldingRecipe(new ResourceLocation("tfc", metalName+"_"+Cast.BOLT.name().toLowerCase()+"_from_"+metal.getRegistryName().getPath()), IIngredient.of(ItemCast.get(castMetal, Cast.BLANK)), IIngredient.of(ItemTechMetal.get(metal, ItemTechMetal.ItemType.BOLT)), ItemCast.get(castMetal, Cast.BOLT, 1), metal.getTier()));
        r.register(new WeldingRecipe(new ResourceLocation("tfc", metalName+"_"+Cast.LONG_ROD.name().toLowerCase()+"_from_"+metal.getRegistryName().getPath()), IIngredient.of(ItemCast.get(castMetal, Cast.BLANK)), IIngredient.of(ItemTechMetal.get(metal, ItemTechMetal.ItemType.LONG_ROD)), ItemCast.get(castMetal, Cast.LONG_ROD, 1), metal.getTier()));
        r.register(new WeldingRecipe(new ResourceLocation("tfc", metalName+"_"+Cast.RACKWHEEL_PIECE.name().toLowerCase()+"_from_"+metal.getRegistryName().getPath()), IIngredient.of(ItemCast.get(castMetal, Cast.BLANK)), IIngredient.of(ItemTechMetal.get(metal, ItemTechMetal.ItemType.RACKWHEEL_PIECE)), ItemCast.get(castMetal, Cast.RACKWHEEL_PIECE, 1), metal.getTier()));
        r.register(new WeldingRecipe(new ResourceLocation("tfc", metalName+"_"+Cast.RACKWHEEL.name().toLowerCase()+"_from_"+metal.getRegistryName().getPath()), IIngredient.of(ItemCast.get(castMetal, Cast.BLANK)), IIngredient.of(ItemTechMetal.get(metal, ItemTechMetal.ItemType.RACKWHEEL)), ItemCast.get(castMetal, Cast.RACKWHEEL, 1), metal.getTier()));
        r.register(new WeldingRecipe(new ResourceLocation("tfc", metalName+"_"+Cast.WIRE.name().toLowerCase()+"_from_"+metal.getRegistryName().getPath()), IIngredient.of(ItemCast.get(castMetal, Cast.BLANK)), IIngredient.of(ItemTechMetal.get(metal, ItemTechMetal.ItemType.WIRE)), ItemCast.get(castMetal, Cast.WIRE, 1), metal.getTier()));
        r.register(new WeldingRecipe(new ResourceLocation("tfc", metalName+"_"+Cast.SCREW.name().toLowerCase()+"_from_"+metal.getRegistryName().getPath()), IIngredient.of(ItemCast.get(castMetal, Cast.BLANK)), IIngredient.of(ItemTechMetal.get(metal, ItemTechMetal.ItemType.SCREW)), ItemCast.get(castMetal, Cast.SCREW, 1), metal.getTier()));
        if(ItemBlowpipe.get(metal) != null) r.register(new WeldingRecipe(new ResourceLocation("tfc", metalName+"_"+Cast.BLOWPIPE.name().toLowerCase()+"_from_"+metal.getRegistryName().getPath()), IIngredient.of(ItemCast.get(castMetal, Cast.BLANK)), IIngredient.of(ItemBlowpipe.get(metal)), ItemCast.get(castMetal, Cast.BLOWPIPE, 1), metal.getTier()));
        if(ItemTechMetal.get(metal, ItemTechMetal.ItemType.SLEEVE) != null)
            r.register(new WeldingRecipe(new ResourceLocation("tfc", metalName+"_"+Cast.SLEEVE.name().toLowerCase()+"_from_"+metal.getRegistryName().getPath()), IIngredient.of(ItemCast.get(castMetal, Cast.BLANK)), IIngredient.of(ItemTechMetal.get(metal, ItemTechMetal.ItemType.SLEEVE)), ItemCast.get(castMetal, Cast.SLEEVE, 1), metal.getTier()));
    }

    @SuppressWarnings("staticInspection")
    public static void onRecipeEvent(Metal metal, Fluid fluid)
    {
        for(ItemTechMetal.ItemType type : ItemTechMetal.ItemType.values())
        {
            Item item = ItemTechMetal.get(metal, type);
            if(item != null) TinkerRegistry.registerMelting(item, fluid, type.getSmeltAmount());
        }
        for(String metalName : TFCTinkerItems.metals)
        {

            Metal castMetal = MetalUtils.getMetal(metalName);
            String name = metal.getRegistryName().getPath();

            if(ItemBlowpipe.get(metal) != null)
            {
                SmelteryUtils.registerCasting(new ItemStack(ItemBlowpipe.get(metal)), ItemCast.get(castMetal, Cast.BLOWPIPE, 1), fluid, 200);
            }

            if(ItemTechMetal.get(metal, ItemTechMetal.ItemType.SLEEVE) != null)
                SmelteryUtils.registerCasting(new ItemStack(ItemTechMetal.get(metal, ItemTechMetal.ItemType.SLEEVE)), ItemCast.get(castMetal, Cast.SLEEVE, 1), fluid, ItemTechMetal.ItemType.SLEEVE.getSmeltAmount());

            SmelteryUtils.registerCasting(new ItemStack(ItemTechMetal.get(metal, ItemTechMetal.ItemType.WIRE)), ItemCast.get(castMetal, Cast.WIRE, 1), fluid, ItemTechMetal.ItemType.WIRE.getSmeltAmount());
            SmelteryUtils.registerCasting(new ItemStack(ItemTechMetal.get(metal, ItemTechMetal.ItemType.RACKWHEEL)), ItemCast.get(castMetal, Cast.RACKWHEEL, 1), fluid, ItemTechMetal.ItemType.RACKWHEEL.getSmeltAmount());
            SmelteryUtils.registerCasting(new ItemStack(ItemTechMetal.get(metal, ItemTechMetal.ItemType.RACKWHEEL_PIECE)), ItemCast.get(castMetal, Cast.RACKWHEEL_PIECE, 1), fluid, ItemTechMetal.ItemType.RACKWHEEL_PIECE.getSmeltAmount());
            SmelteryUtils.registerCasting(new ItemStack(ItemTechMetal.get(metal, ItemTechMetal.ItemType.LONG_ROD)), ItemCast.get(castMetal, Cast.LONG_ROD, 1), fluid, ItemTechMetal.ItemType.LONG_ROD.getSmeltAmount());
            SmelteryUtils.registerCasting(new ItemStack(ItemTechMetal.get(metal, ItemTechMetal.ItemType.BOLT)), ItemCast.get(castMetal, Cast.BOLT, 1), fluid, ItemTechMetal.ItemType.BOLT.getSmeltAmount());
            SmelteryUtils.registerCasting(new ItemStack(ItemTechMetal.get(metal, ItemTechMetal.ItemType.SCREW)), ItemCast.get(castMetal, Cast.SCREW, 1), fluid, ItemTechMetal.ItemType.SCREW.getSmeltAmount());
        }
    }
}
