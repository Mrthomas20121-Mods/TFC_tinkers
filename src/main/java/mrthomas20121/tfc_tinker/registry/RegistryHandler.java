package mrthomas20121.tfc_tinker.registry;

import mrthomas20121.biolib.common.SmelteryUtils;
import mrthomas20121.rocksalt.utils.MetalUtils;
import mrthomas20121.tfc_tinker.TFC_Tinker;
import mrthomas20121.tfc_tinker.compat.tfctech.GeneralCompat;
import mrthomas20121.tfc_tinker.config.ConfigTic;
import mrthomas20121.tfc_tinker.objects.Cast;
import mrthomas20121.tfc_tinker.objects.items.ItemCast;
import mrthomas20121.tfc_tinker.objects.items.TFCTinkerItems;
import net.dries007.tfc.api.recipes.WeldingRecipe;
import net.dries007.tfc.api.recipes.anvil.AnvilRecipe;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.objects.inventory.ingredient.IIngredient;
import net.dries007.tfc.objects.items.metal.ItemMetal;
import net.dries007.tfc.util.forge.ForgeRule;
import net.dries007.tfc.util.skills.SmithingSkill;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.commons.lang3.StringUtils;
import slimeknights.tconstruct.library.materials.Material;

import java.util.ArrayList;

@Mod.EventBusSubscriber(modid = TFC_Tinker.MODID)
public class RegistryHandler {

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onRecipeEvent(RegistryEvent.Register<IRecipe> event)
    {
        if(Loader.isModLoaded("tfctech"))
        {
            GeneralCompat.onRecipeEvent(event);
        }
        if(ConfigTic.ConfigTFCTinker.general.register_alloys)
        {
            SmelteryUtils.registerAlloy(getFluidStack("bismuth_bronze", 1), getFluidStack("zinc", 2), getFluidStack("copper", 5), getFluidStack("bismuth", 1));
            SmelteryUtils.registerAlloy(getFluidStack("black_bronze", 1), getFluidStack("copper", 5), getFluidStack("silver", 1), getFluidStack("gold", 1));
            SmelteryUtils.registerAlloy(getFluidStack("rose_gold", 1), getFluidStack("copper", 2), getFluidStack("gold", 7));
            SmelteryUtils.registerAlloy(getFluidStack("sterling_silver", 1), getFluidStack("copper", 2), getFluidStack("silver", 6));
            SmelteryUtils.registerAlloy(getFluidStack("weak_steel", 1), getFluidStack("steel", 5), getFluidStack("nickel", 2), getFluidStack("black_bronze", 2));
            SmelteryUtils.registerAlloy(getFluidStack("weak_blue_steel", 1), getFluidStack("black_steel", 5), getFluidStack("steel", 2), getFluidStack("bismuth_bronze", 1), getFluidStack("sterling_silver", 1));
            SmelteryUtils.registerAlloy(getFluidStack("weak_red_steel", 1), getFluidStack("black_steel", 5), getFluidStack("steel", 2), getFluidStack("brass", 1), getFluidStack("rose_gold", 1));
        }

        for(Metal metal : TFCRegistries.METALS.getValuesCollection())
        {
            ArrayList<Metal> blacklists = new ArrayList<>();
            for(String metalLoc : ConfigTic.ConfigTFCTinker.general.metalBlacklist)
            {
                blacklists.add(TFCRegistries.METALS.getValue(new ResourceLocation(metalLoc)));
            }

            if(!blacklists.contains(metal) && ObfuscationReflectionHelper.getPrivateValue(Metal.class, metal, "usable").equals(true))
            {
                String path = cap(metal.getRegistryName().getPath());
                String doubleIngot = "ingotDouble"+path;
                String doubleSheet = "sheetDouble"+path;
                String sheet = "sheet"+path;
                String scrap = "scrap"+path;

                Fluid fluid = getFluid(metal);

                SmelteryUtils.registerMelting("ingot"+path,fluid, Material.VALUE_Ingot);
                SmelteryUtils.registerMelting(doubleIngot,fluid,Material.VALUE_Ingot*2);
                SmelteryUtils.registerMelting(doubleSheet,fluid,Material.VALUE_Ingot*2);
                SmelteryUtils.registerMelting(sheet,fluid,Material.VALUE_Ingot);
                SmelteryUtils.registerMelting(scrap,fluid,Material.VALUE_Ingot);

                for(String m : TFCTinkerItems.metals)
                {
                    Metal castMetal = MetalUtils.getMetal(m);
                    if(metal.isToolMetal())
                    {
                        SmelteryUtils.registerCasting(new ItemStack(ItemMetal.get(metal, Metal.ItemType.TUYERE)), ItemCast.get(castMetal, Cast.TUYERE, 1), fluid, Material.VALUE_Ingot);
                    }
                    SmelteryUtils.registerCasting(new ItemStack(ItemMetal.get(metal, Metal.ItemType.SHEET)), ItemCast.get(castMetal, Cast.SHEET, 1), fluid, Material.VALUE_Ingot);
                    SmelteryUtils.registerCasting(new ItemStack(ItemMetal.get(metal, Metal.ItemType.SCRAP)), ItemCast.get(castMetal, Cast.SCRAP, 1), fluid, Material.VALUE_Ingot);
                    SmelteryUtils.registerCasting(new ItemStack(ItemMetal.get(metal, Metal.ItemType.DOUBLE_SHEET)), ItemCast.get(castMetal, Cast.DOUBLE_SHEET, 1), fluid, Material.VALUE_Ingot*2);
                    SmelteryUtils.registerCasting(new ItemStack(ItemMetal.get(metal, Metal.ItemType.DOUBLE_INGOT)), ItemCast.get(castMetal, Cast.DOUBLE_INGOT, 1), fluid, Material.VALUE_Ingot*2);
                }

                if(Loader.isModLoaded("tfctech"))
                {
                    SmelteryUtils.registerMelting("rackwheelPiece"+path,fluid,Material.VALUE_Ingot);
                    SmelteryUtils.registerMelting("rackwheel"+path,fluid,Material.VALUE_Ingot*4);
                    SmelteryUtils.registerMelting("wire"+path,fluid,Material.VALUE_Ingot);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onWeldingRecipeEvent(RegistryEvent.Register<WeldingRecipe> event)
    {
        IForgeRegistry<WeldingRecipe> r = event.getRegistry();
        for(Metal metal: TFCRegistries.METALS.getValuesCollection())
        {
            for(String metalName : TFCTinkerItems.metals)
            {
                Metal castMetal = MetalUtils.getMetal(metalName);

                if(ObfuscationReflectionHelper.getPrivateValue(Metal.class, metal, "usable").equals(true))
                {
                    r.register(new WeldingRecipe(new ResourceLocation("tfc", metalName+"_"+Cast.DOUBLE_INGOT.name().toLowerCase()+"_cast_from_"+metal.getRegistryName().getPath()), IIngredient.of(ItemCast.get(castMetal, Cast.BLANK)), IIngredient.of(ItemMetal.get(metal, Metal.ItemType.DOUBLE_INGOT)), ItemCast.get(castMetal, Cast.DOUBLE_INGOT, 1), metal.getTier()));
                    r.register(new WeldingRecipe(new ResourceLocation("tfc", metalName+"_"+Cast.DOUBLE_SHEET.name().toLowerCase()+"_cast_from_"+metal.getRegistryName().getPath()), IIngredient.of(ItemCast.get(castMetal, Cast.BLANK)), IIngredient.of(ItemMetal.get(metal, Metal.ItemType.DOUBLE_SHEET)), ItemCast.get(castMetal, Cast.DOUBLE_SHEET, 1), metal.getTier()));
                    r.register(new WeldingRecipe(new ResourceLocation("tfc", metalName+"_"+Cast.RACKWHEEL_PIECE.name().toLowerCase()+"_cast_from_"+metal.getRegistryName().getPath()), IIngredient.of(ItemCast.get(castMetal, Cast.BLANK)), IIngredient.of(ItemMetal.get(metal, Metal.ItemType.SCRAP)), ItemCast.get(castMetal, Cast.SCRAP, 1), metal.getTier()));
                    if(metal.isToolMetal())
                        r.register(new WeldingRecipe(new ResourceLocation("tfc", metalName+"_"+Cast.TUYERE.name().toLowerCase()+"_cast_from_"+metal.getRegistryName().getPath()), IIngredient.of(ItemCast.get(castMetal, Cast.BLANK)), IIngredient.of(ItemMetal.get(metal, Metal.ItemType.TUYERE)), ItemCast.get(castMetal, Cast.TUYERE, 1), metal.getTier()));

                    if(Loader.isModLoaded("tfctech"))
                    {
                        GeneralCompat.onWeldingRecipeEvent(r, metal, metalName, castMetal);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onAnvilRecipeEvent(RegistryEvent.Register<AnvilRecipe> event)
    {
        IForgeRegistry<AnvilRecipe> r = event.getRegistry();
        for(String metalName : TFCTinkerItems.metals)
        {
            Metal castMetal = MetalUtils.getMetal(metalName);
            r.register(new AnvilRecipe(new ResourceLocation("tfc", metalName+"_blank_cast"), IIngredient.of(ItemMetal.get(castMetal, Metal.ItemType.DOUBLE_INGOT)), ItemCast.get(castMetal, Cast.BLANK, 1), castMetal.getTier(), SmithingSkill.Type.GENERAL, ForgeRule.BEND_SECOND_LAST, ForgeRule.HIT_ANY, ForgeRule.DRAW_ANY));
        }
    }

    private static FluidStack getFluidStack(String fluidName, int stack)
    {
        return FluidRegistry.getFluidStack(fluidName, Material.VALUE_Ingot*stack);
    }

    private static String cap(String str)
    {
        String[] array = str.split("_");
        StringBuilder s = new StringBuilder();
        for(String string: array)
        {
            s.append(StringUtils.capitalize(string));
        }
        return s.toString();
    }

    private static Fluid getFluid(Metal metal)
    {
        return FluidRegistry.getFluid(metal.getRegistryName().getPath()).setTemperature((int)metal.getMeltTemp()/2);
    }
}
