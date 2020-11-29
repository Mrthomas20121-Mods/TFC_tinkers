package mrthomas20121.tfc_tinker.modules;

import com.google.common.collect.Lists;
import mrthomas20121.biolib.common.*;
import mrthomas20121.biolib.objects.material.MaterialStats;
import mrthomas20121.biolib.util.armorUtils;
import mrthomas20121.tfc_tinker.config.ConfigTic;
import mrthomas20121.tfc_tinker.TFC_Tinker;
import mrthomas20121.tfc_tinker.compat.tinkers_construct.MaterialWrapperTFC;
import mrthomas20121.tfc_tinker.objects.items.TFCTinkerItems;
import net.dries007.tfc.api.capability.heat.CapabilityItemHeat;
import net.dries007.tfc.api.capability.heat.ItemHeatHandler;
import net.dries007.tfc.api.recipes.barrel.BarrelRecipe;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.registries.TFCRegistryEvent;
import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.objects.blocks.BlocksTFC;
import net.dries007.tfc.objects.inventory.ingredient.IIngredient;
import net.dries007.tfc.util.calendar.ICalendar;
import net.minecraft.init.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.IForgeRegistry;
import net.dries007.tfc.api.types.Ore;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.commons.lang3.StringUtils;
import slimeknights.tconstruct.library.materials.Material;
import static slimeknights.tconstruct.shared.TinkerCommons.searedBrick;

import java.util.ArrayList;

@Mod.EventBusSubscriber(modid = TFC_Tinker.MODID)
public class ModuleTFC {

    public static ArrayList<MaterialWrapperTFC> materials = new ArrayList<>();
    private static ArrayList<String> nerfs = Lists.newArrayList("steel", "copper", "cobalt", "manyullyn");
    private static ArrayList<String> registerBlacklist = Lists.newArrayList("copper", "cobalt", "manyullyn", "bronze", "steel");

    private static ModuleTFC instance = new ModuleTFC();

    public static ModuleTFC getInstance() {
        return instance;
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onPreRegisterMetal(TFCRegistryEvent.RegisterPreBlock<Metal> event)
    {
        ArrayList<String> blacklists = Lists.newArrayList(ConfigTic.ConfigTFCTinker.material.material_blacklists);
        for(Metal metal : event.getRegistry().getValuesCollection()) {
            String metalName = metal.getRegistryName().getPath();
            if(metal.isToolMetal() && !registerBlacklist.contains(metalName))
            {
                if(!blacklists.contains(metalName))
                {

                    MaterialWrapperTFC materialWrapperTFC = new MaterialWrapperTFC(metal);
                    materialWrapperTFC.setTraits();

                    Item.ToolMaterial tool = metal.getToolMetal();
                    MaterialStats stats = new MaterialStats();
                    stats.setHeadMaterialStats(tool.getMaxUses(), tool.getEfficiency(), tool.getAttackDamage(), tool.getHarvestLevel());
                    stats.setHandleMaterialStats(1, tool.getMaxUses()/2);
                    stats.setExtraMaterialStats(tool.getMaxUses()/10);
                    materialWrapperTFC.createMaterial(stats);

                    if(Loader.isModLoaded("conarm"))
                    {
                        armorUtils.setArmorStats(materialWrapperTFC, stats, 0);
                    }
                    materials.add(materialWrapperTFC);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onRegisterBarrelRecipeEvent(RegistryEvent.Register<BarrelRecipe> event)
    {
        IForgeRegistry<BarrelRecipe> r = event.getRegistry();
        r.register(new BarrelRecipe(IIngredient.of(FluidRegistry.getFluid("hot_water"), 1000), IIngredient.of(new ItemStack(Items.CLAY_BALL)), new FluidStack(FluidRegistry.getFluid("clay"), 500), ItemStack.EMPTY, 4 * ICalendar.TICKS_IN_HOUR).setRegistryName("liquid_clay"));
        r.register(new BarrelRecipe(IIngredient.of(FluidRegistry.getFluid("clay"), 1000), IIngredient.of(new ItemStack(BlocksTFC.AGGREGATE, 1)), null, new ItemStack(TFCTinkerItems.grout, 1), 4 * ICalendar.TICKS_IN_HOUR).setRegistryName("grout"));
    }

    public void init(FMLInitializationEvent e)
    {
        CapabilityItemHeat.CUSTOM_ITEMS.put(IIngredient.of(searedBrick), () -> new ItemHeatHandler(null, 0.35f, 1500));

        ArrayList<Metal> blacklists = new ArrayList<>();
        for(String metalLoc : ConfigTic.ConfigTFCTinker.general.metalBlacklist)
        {
            blacklists.add(TFCRegistries.METALS.getValue(new ResourceLocation(metalLoc)));
        }

        for(MaterialWrapperTFC material : materials)
        {
            material.setMode(true);
            String path = cap(material.getMaterial().getIdentifier());

            String doubleIngot = "ingotDouble"+path;
            String doubleSheet = "sheetDouble"+path;
            String sheet = "sheet"+path;
            Fluid fluid = getFluid(material.metal);
            material.getMaterial().setFluid(fluid);
            material.getMaterial().setRepresentativeItem("ingot"+path);
            material.getMaterial().addItem(doubleIngot,1, Material.VALUE_Ingot*2);
            material.getMaterial().addItem(sheet,1, Material.VALUE_Ingot);
            material.getMaterial().addItem(doubleSheet,1, Material.VALUE_Ingot*2);
        }

        for(Ore ore : TFCRegistries.ORES.getValuesCollection())
        {
            if(ore.getMetal() != null)
            {
                Metal metal = ore.getMetal();
                String name = cap(metal.getRegistryName().getPath());
                Fluid fluid = FluidRegistry.getFluid(metal.getRegistryName().getPath()).setTemperature((int)metal.getMeltTemp()/2);
                SmelteryUtils.registerMelting("ore"+name+"Rich", fluid, 35);
                SmelteryUtils.registerMelting("ore"+name+"Normal", fluid, 25);
                SmelteryUtils.registerMelting("ore"+name+"Poor", fluid, 15);
                SmelteryUtils.registerMelting("ore"+name+"Small", fluid, 10);
            }
        }

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
    private Fluid getFluid(Metal metal)
    {
        return FluidRegistry.getFluid(metal.getRegistryName().getPath()).setTemperature((int)metal.getMeltTemp()/2);
    }
}