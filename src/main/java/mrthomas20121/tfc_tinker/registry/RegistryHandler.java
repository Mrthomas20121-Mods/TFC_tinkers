package mrthomas20121.tfc_tinker.registry;

import com.google.common.collect.Lists;
import mrthomas20121.rocksalt.Utils;
import mrthomas20121.tfc_tinker.TFC_Tinker;
import mrthomas20121.tfc_tinker.compat.tfctech.GeneralCompat;
import mrthomas20121.tfc_tinker.compat.tinkers_construct.MaterialTFC;
import mrthomas20121.tfc_tinker.ConfigMain;
import mrthomas20121.tfc_tinker.objects.Cast;
import mrthomas20121.tfc_tinker.objects.items.ItemCast;
import mrthomas20121.tfc_tinker.objects.items.TFCTinkerItems;
import net.dries007.tfc.api.capability.heat.CapabilityItemHeat;
import net.dries007.tfc.api.capability.heat.ItemHeatHandler;
import net.dries007.tfc.api.recipes.WeldingRecipe;
import net.dries007.tfc.api.recipes.anvil.AnvilRecipe;
import net.dries007.tfc.api.recipes.barrel.BarrelRecipe;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.registries.TFCRegistryEvent;
import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.api.types.Ore;
import net.dries007.tfc.objects.Gem;
import net.dries007.tfc.objects.blocks.BlocksTFC;
import net.dries007.tfc.objects.inventory.ingredient.IIngredient;
import net.dries007.tfc.objects.items.ItemGem;
import net.dries007.tfc.objects.items.metal.ItemMetal;
import net.dries007.tfc.util.calendar.ICalendar;
import net.dries007.tfc.util.forge.ForgeRule;
import net.dries007.tfc.util.skills.SmithingSkill;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryModifiable;
import org.apache.commons.lang3.StringUtils;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.*;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.TinkerTraits;

import java.util.ArrayList;

import static slimeknights.tconstruct.shared.TinkerCommons.searedBrick;

@Mod.EventBusSubscriber(modid = TFC_Tinker.MODID)
public class RegistryHandler {

    public static ArrayList<MaterialTFC> materials = new ArrayList<>();

    @SubscribeEvent
    public static void onRegisterBarrelRecipeEvent(RegistryEvent.Register<BarrelRecipe> event)
    {
        IForgeRegistry<BarrelRecipe> r = event.getRegistry();
        r.register(new BarrelRecipe(IIngredient.of(FluidRegistry.getFluid("hot_water"), 1000), IIngredient.of(new ItemStack(Items.CLAY_BALL)), new FluidStack(FluidRegistry.getFluid("clay"), 500), ItemStack.EMPTY, 4 * ICalendar.TICKS_IN_HOUR).setRegistryName("liquid_clay"));
        r.register(new BarrelRecipe(IIngredient.of(FluidRegistry.getFluid("clay"), 1000), IIngredient.of(new ItemStack(BlocksTFC.AGGREGATE, 1)), null, new ItemStack(TFCTinkerItems.grout, 1), 4 * ICalendar.TICKS_IN_HOUR).setRegistryName("grout"));
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onPreRegisterMetal(TFCRegistryEvent.RegisterPreBlock<Metal> event)
    {
        ArrayList<String> blacklists = Lists.newArrayList(ConfigMain.ConfigTFCTinker.material.material_blacklists);
        for(Metal metal : event.getRegistry().getValuesCollection()) {
            String metalName = metal.getRegistryName().getPath();
            if(metal.isToolMetal())
            {
                if(metalName.equals("boron") && Loader.isModLoaded("nuclearcraft"))
                    continue;
                if(!blacklists.contains(metalName) && TinkerRegistry.getMaterial(metalName) == Material.UNKNOWN)
                {
                    Material material = new Material(metal.getRegistryName().toString().replace(":", "_"), metal.getColor());
                    Item.ToolMaterial tool = metal.getToolMetal();
                    addTraits(material);
                    TinkerRegistry.addMaterial(material);
                    TinkerRegistry.addMaterialStats(material,
                            new HeadMaterialStats(tool.getMaxUses(), tool.getEfficiency(), tool.getAttackDamage(), tool.getHarvestLevel()),
                            new HandleMaterialStats(0.9f, tool.getMaxUses()/2),
                            new ExtraMaterialStats(tool.getMaxUses()/10));
                    materials.add(new MaterialTFC(metal, material));
                }
            }
        }
    }

    public static void init(FMLInitializationEvent e)
    {
        OreDictionary.registerOre("grout", TFCTinkerItems.grout);
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(searedBrick), () -> new ItemHeatHandler(null, 0.35f, 1500));

        for(MaterialTFC materialTFC : materials) {
            Metal metal = materialTFC.getMetal();
            String registryName = metal.getRegistryName().getPath();
            Material material = materialTFC.getMaterial();
            material.setCraftable(false);
            material.setCastable(true);
            material.setFluid(FluidRegistry.getFluid(registryName));
            material.addCommonItems(cap(registryName));
            material.setRepresentativeItem("ingot"+cap(registryName));
            for(Metal.ItemType type : Metal.ItemType.values()) {
                if(type != Metal.ItemType.SHIELD)
                {
                    material.addItem(ItemMetal.get(metal, type), type.getSmeltAmount(), type.getSmeltAmount());
                }
            }
            TinkerSmeltery.registerToolpartMeltingCasting(material);
        }

        for(Gem gem : Gem.values()) {
            TinkerModifiers.modSharpness.addItem(ItemGem.get(gem, Gem.Grade.CHIPPED, 1), 1, 32);
            TinkerModifiers.modSharpness.addItem(ItemGem.get(gem, Gem.Grade.EXQUISITE, 1), 1, 52);
            TinkerModifiers.modSharpness.addItem(ItemGem.get(gem, Gem.Grade.NORMAL, 1), 1, 72);
            TinkerModifiers.modSharpness.addItem(ItemGem.get(gem, Gem.Grade.FLAWED, 1), 1, 89);
            TinkerModifiers.modSharpness.addItem(ItemGem.get(gem, Gem.Grade.FLAWLESS, 1), 1, 102);
        }

        for(Ore ore : TFCRegistries.ORES.getValuesCollection()) {
            if(ore.getMetal() != null) {
                Metal metal = ore.getMetal();
                String name = cap(metal.getRegistryName().getPath());
                Fluid fluid = FluidRegistry.getFluid(metal.getRegistryName().getPath()).setTemperature((int)metal.getMeltTemp()/2);
                TinkerRegistry.registerMelting("ore"+name+"Rich", fluid, 35);
                TinkerRegistry.registerMelting("ore"+name+"Normal", fluid, 25);
                TinkerRegistry.registerMelting("ore"+name+"Poor", fluid, 15);
                TinkerRegistry.registerMelting("ore"+name+"Small", fluid, 10);
            }
        }

    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onRecipeEvent(RegistryEvent.Register<IRecipe> event) {
        IForgeRegistryModifiable r = (IForgeRegistryModifiable)event.getRegistry();

        r.remove(new ResourceLocation("tconstruct:smeltery/grout_simple"));
        r.remove(new ResourceLocation("tconstruct:smeltery/grout"));

        if(ConfigMain.ConfigTFCTinker.general.register_alloys) {
            TinkerRegistry.registerAlloy(getFluidStack("bismuth_bronze", 8), getFluidStack("zinc", 2), getFluidStack("copper", 5), getFluidStack("bismuth", 1));
            TinkerRegistry.registerAlloy(getFluidStack("black_bronze", 7), getFluidStack("copper", 5), getFluidStack("silver", 1), getFluidStack("gold", 1));
            TinkerRegistry.registerAlloy(getFluidStack("rose_gold", 9), getFluidStack("copper", 2), getFluidStack("gold", 7));
            TinkerRegistry.registerAlloy(getFluidStack("sterling_silver", 8), getFluidStack("copper", 2), getFluidStack("silver", 6));
            TinkerRegistry.registerAlloy(getFluidStack("weak_steel", 9), getFluidStack("steel", 5), getFluidStack("nickel", 2), getFluidStack("black_bronze", 2));
            TinkerRegistry.registerAlloy(getFluidStack("weak_blue_steel", 9), getFluidStack("black_steel", 5), getFluidStack("steel", 2), getFluidStack("bismuth_bronze", 1), getFluidStack("sterling_silver", 1));
            TinkerRegistry.registerAlloy(getFluidStack("weak_red_steel", 9), getFluidStack("black_steel", 5), getFluidStack("steel", 2), getFluidStack("brass", 1), getFluidStack("rose_gold", 1));
        }

        for(Metal metal : TFCRegistries.METALS.getValuesCollection()) {

            if(metal.isUsable()) {
                Fluid fluid = FluidRegistry.getFluid(metal.getRegistryName().getPath());
                fluid.setTemperature((int)metal.getMeltTemp());

                if(metal.getRegistryName().getPath().equals("bismuth"))
                    fluid.setTemperature((int)metal.getMeltTemp()*2);

                TinkerSmeltery.registerOredictMeltingCasting(fluid, cap(metal.getRegistryName().getPath()));

                if(Loader.isModLoaded("tfctech")) {
                    GeneralCompat.onRecipeEvent(metal, fluid);
                }

                for(Metal.ItemType type : Metal.ItemType.values()) {
                    if(!metal.isToolMetal() && (type.isArmor() || type.isToolItem()))
                        continue;

                    if(ItemMetal.get(metal, type) != null) {
                        TinkerRegistry.registerMelting(new ItemStack(ItemMetal.get(metal, type), 1), fluid, type.getSmeltAmount());
                    }
                }



                for(String m : TFCTinkerItems.metals) {
                    Metal castMetal = Utils.getMetal(m);
                    if(metal.isToolMetal()) {
                        TinkerRegistry.registerTableCasting(new ItemStack(ItemMetal.get(metal, Metal.ItemType.TUYERE)), ItemCast.get(castMetal, Cast.TUYERE, 1), fluid, Metal.ItemType.TUYERE.getSmeltAmount());
                    }
                    TinkerRegistry.registerTableCasting(new ItemStack(ItemMetal.get(metal, Metal.ItemType.SHEET)), ItemCast.get(castMetal, Cast.SHEET, 1), fluid, Metal.ItemType.SHEET.getSmeltAmount());
                    TinkerRegistry.registerTableCasting(new ItemStack(ItemMetal.get(metal, Metal.ItemType.SCRAP)), ItemCast.get(castMetal, Cast.SCRAP, 1), fluid, Metal.ItemType.SCRAP.getSmeltAmount());
                    TinkerRegistry.registerTableCasting(new ItemStack(ItemMetal.get(metal, Metal.ItemType.DOUBLE_SHEET)), ItemCast.get(castMetal, Cast.DOUBLE_SHEET, 1), fluid, Metal.ItemType.DOUBLE_SHEET.getSmeltAmount());
                    TinkerRegistry.registerTableCasting(new ItemStack(ItemMetal.get(metal, Metal.ItemType.DOUBLE_INGOT)), ItemCast.get(castMetal, Cast.DOUBLE_INGOT, 1), fluid, Metal.ItemType.DOUBLE_INGOT.getSmeltAmount());
                }

                if(Loader.isModLoaded("tfctech") && ConfigMain.ConfigTFCTinker.general.tfctech) {
                    GeneralCompat.registerMelting(metal, fluid);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onWeldingRecipeEvent(RegistryEvent.Register<WeldingRecipe> event) {
        IForgeRegistry<WeldingRecipe> r = event.getRegistry();
        for(Metal metal: TFCRegistries.METALS.getValuesCollection()) {
            for(String metalName : TFCTinkerItems.metals)
            {
                Metal castMetal = Utils.getMetal(metalName);

                if(metal.isUsable())
                {
                    r.register(new WeldingRecipe(new ResourceLocation("tfc", metalName+"_"+Cast.DOUBLE_INGOT.name().toLowerCase()+"_cast_from_"+metal.getRegistryName().getPath()), IIngredient.of(ItemCast.get(castMetal, Cast.BLANK)), IIngredient.of(ItemMetal.get(metal, Metal.ItemType.DOUBLE_INGOT)), ItemCast.get(castMetal, Cast.DOUBLE_INGOT, 1), metal.getTier()));
                    r.register(new WeldingRecipe(new ResourceLocation("tfc", metalName+"_"+Cast.DOUBLE_SHEET.name().toLowerCase()+"_cast_from_"+metal.getRegistryName().getPath()), IIngredient.of(ItemCast.get(castMetal, Cast.BLANK)), IIngredient.of(ItemMetal.get(metal, Metal.ItemType.DOUBLE_SHEET)), ItemCast.get(castMetal, Cast.DOUBLE_SHEET, 1), metal.getTier()));
                    r.register(new WeldingRecipe(new ResourceLocation("tfc", metalName+"_"+Cast.SHEET.name().toLowerCase()+"_cast_from_"+metal.getRegistryName().getPath()), IIngredient.of(ItemCast.get(castMetal, Cast.BLANK)), IIngredient.of(ItemMetal.get(metal, Metal.ItemType.SHEET)), ItemCast.get(castMetal, Cast.SHEET, 1), metal.getTier()));
                    r.register(new WeldingRecipe(new ResourceLocation("tfc", metalName+"_"+Cast.SCRAP.name().toLowerCase()+"_cast_from_"+metal.getRegistryName().getPath()), IIngredient.of(ItemCast.get(castMetal, Cast.BLANK)), IIngredient.of(ItemMetal.get(metal, Metal.ItemType.SCRAP)), ItemCast.get(castMetal, Cast.SCRAP, 1), metal.getTier()));
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
            Metal castMetal = Utils.getMetal(metalName);
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

    private static void addTraits(Material material) {
        String identifier = material.getIdentifier();
        switch (identifier) {
            case "tfc_bismuth_bronze":
            case "tfc_black_bronze":
                material.addTrait(TinkerTraits.dense);
                break;
            case "tfc_black_steel":
            case "tfc_red_steel":
            case "tfc_blue_steel":
                material.addTrait(TinkerTraits.sharp, MaterialTypes.HEAD);
                material.addTrait(TinkerTraits.stiff);
                break;
            case "tfc_wrought_iron":
                material.addTrait(TinkerTraits.magnetic2, MaterialTypes.HEAD);
                material.addTrait(TinkerTraits.magnetic);
                break;
            default:
                material.addTrait(TinkerTraits.poisonous);
        }
    }
}
