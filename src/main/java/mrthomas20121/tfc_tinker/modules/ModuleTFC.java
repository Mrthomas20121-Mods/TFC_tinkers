package mrthomas20121.tfc_tinker.modules;

import com.google.common.collect.Lists;
import mrthomas20121.biolib.common.*;
import mrthomas20121.biolib.util.armorUtils;
import mrthomas20121.tfc_tinker.Config.ConfigTic;
import mrthomas20121.tfc_tinker.TFC_Tinker;
import mrthomas20121.tfc_tinker.common.MaterialBuilderTFC;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.registries.TFCRegistryEvent;
import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.objects.items.metal.ItemMetal;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import slimeknights.tconstruct.library.events.MaterialEvent;
import slimeknights.tconstruct.library.materials.*;

import net.dries007.tfc.api.types.Ore;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.commons.lang3.StringUtils;
import slimeknights.tconstruct.library.materials.Material;

import java.util.ArrayList;

import static mrthomas20121.tfc_tinker.Items.Items.*;

@Mod.EventBusSubscriber(modid = TFC_Tinker.MODID)
public class ModuleTFC implements ModuleBase {

    public static ArrayList<MaterialBuilderTFC> materials = new ArrayList<>();
    private static ArrayList<String> nerfs = Lists.newArrayList(new String[] {"steel", "copper", "cobalt", "manyullyn"});

    @SubscribeEvent(priority=EventPriority.LOWEST)
    public void onStatRegister(MaterialEvent.StatRegisterEvent<IMaterialStats> statRegisterEvent) {
        Material mat = statRegisterEvent.material;
        IMaterialStats newStats = null;

        if(nerfs.contains(mat.getIdentifier()))
        {
            String intentifier = mat.getIdentifier();
            Metal metal = TFCRegistries.METALS.getValue(new ResourceLocation("tfc:"+intentifier));
            IMaterialStats oldStats = statRegisterEvent.newStats != null ? statRegisterEvent.newStats:statRegisterEvent.stats;
            HandleMaterialStats handleStats = null;
            Item.ToolMaterial tool = metal.getToolMetal();
            if (oldStats instanceof HeadMaterialStats) {
                newStats = new HeadMaterialStats(tool.getMaxUses(), tool.getEfficiency(), tool.getAttackDamage(), tool.getHarvestLevel());
            }
            else if (oldStats instanceof HandleMaterialStats) {
                handleStats = (HandleMaterialStats)oldStats;
                newStats = new HandleMaterialStats(handleStats.modifier, tool.getMaxUses()/2);
            }
            else if (oldStats instanceof ExtraMaterialStats) {
                newStats = new ExtraMaterialStats(tool.getMaxUses()/10);
            }
        }
        if (newStats != null)
        {
            statRegisterEvent.overrideResult(newStats);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onPreRegisterMetal(TFCRegistryEvent.RegisterPreBlock<Metal> event)
    {
        ArrayList<String> blacklists = Lists.newArrayList(ConfigTic.materialBlacklists);
        for(Metal metal : TFCRegistries.METALS.getValuesCollection()) {
            if(metal.isToolMetal() && !metal.getRegistryName().getPath().equals("cobalt") && !metal.getRegistryName().getPath().equals("manyullyn") && !metal.getRegistryName().getPath().equals("bronze") && !metal.getRegistryName().getPath().equals("copper") && !metal.getRegistryName().getPath().equals("steel"))
            {
                if(!blacklists.contains(metal.getRegistryName().getPath()))
                {
                    MaterialBuilderTFC material = new MaterialBuilderTFC(metal);
                    material.setCastable(true).setCraftable(false);
                    material.setTraits();
                    Item.ToolMaterial tool = metal.getToolMetal();
                    material.setHeadStats(tool.getMaxUses(), tool.getEfficiency(), tool.getAttackDamage(), tool.getHarvestLevel());
                    material.setHandleStats(1, tool.getMaxUses()/2);
                    material.setExtraStats(tool.getMaxUses()/10);
                    material.preInit(cap(metal.getRegistryName().getPath()));
                    TFC_Tinker.moduleTFC.addArmorStats(material);
                    materials.add(material);
                }
            }
        }
    }

    public void preInit(FMLPreInitializationEvent e)
    {
    }

    @Override
    public void init(FMLInitializationEvent e)
    {
        if(ConfigTic.register_alloy)
        {
            SmelteryUtils.registerAlloy(getFluidStack("bismuth_bronze", 1), getFluidStack("zinc", 2), getFluidStack("copper", 5), getFluidStack("bismuth", 1));
            SmelteryUtils.registerAlloy(getFluidStack("black_bronze", 1), getFluidStack("copper", 5), getFluidStack("silver", 1), getFluidStack("gold", 1));
            SmelteryUtils.registerAlloy(getFluidStack("rose_gold", 1), getFluidStack("copper", 2), getFluidStack("gold", 7));
            SmelteryUtils.registerAlloy(getFluidStack("sterling_silver", 1), getFluidStack("copper", 2), getFluidStack("silver", 6));
            SmelteryUtils.registerAlloy(getFluidStack("weak_steel", 1), getFluidStack("steel", 5), getFluidStack("nickel", 2), getFluidStack("black_bronze", 2));
            SmelteryUtils.registerAlloy(getFluidStack("weak_blue_steel", 1), getFluidStack("black_steel", 5), getFluidStack("steel", 2), getFluidStack("bismuth_bronze", 1), getFluidStack("sterling_silver", 1));
            SmelteryUtils.registerAlloy(getFluidStack("weak_red_steel", 1), getFluidStack("black_steel", 5), getFluidStack("steel", 2), getFluidStack("brass", 1), getFluidStack("rose_gold", 1));
        }

        ArrayList<Metal> blacklists = new ArrayList<>();
        for(String metalLoc : ConfigTic.metalBlacklists)
        {
            blacklists.add(TFCRegistries.METALS.getValue(new ResourceLocation(metalLoc)));
        }

        for(Metal metal : TFCRegistries.METALS.getValuesCollection())
        {
            if(!blacklists.contains(metal))
            {
                String path = cap(metal.getRegistryName().getPath());
                String doubleIngot = "ingotDouble"+path;
                String doubleSheet = "sheetDouble"+path;
                String sheet = "sheet"+path;
                String scrap = "scrap"+path;

                Fluid fluid = getFluid(metal);

                SmelteryUtils.registerMelting("ingot"+path,fluid,Material.VALUE_Ingot);
                SmelteryUtils.registerMelting(doubleIngot,fluid,Material.VALUE_Ingot*2);
                SmelteryUtils.registerMelting(doubleSheet,fluid,Material.VALUE_Ingot*2);
                SmelteryUtils.registerMelting(sheet,fluid,Material.VALUE_Ingot);
                SmelteryUtils.registerMelting(scrap,fluid,Material.VALUE_Ingot);

                for(String str : ConfigTic.castFluids)
                {
                    Fluid f = FluidRegistry.getFluid(str);
                    if(metal.isToolMetal())
                    {
                        SmelteryUtils.registerCasting(new ItemStack(castTuyere), new ItemStack(ItemMetal.get(metal, Metal.ItemType.TUYERE)), f, Material.VALUE_Ingot*2);
                    }

                    SmelteryUtils.registerCasting(new ItemStack(castSheet), new ItemStack(ItemMetal.get(metal, Metal.ItemType.SHEET)), f, Material.VALUE_Ingot*2);
                    SmelteryUtils.registerCasting(new ItemStack(castScrap), new ItemStack(ItemMetal.get(metal, Metal.ItemType.SCRAP)), f, Material.VALUE_Ingot*2);
                    SmelteryUtils.registerCasting(new ItemStack(castDoubleSheet), new ItemStack(ItemMetal.get(metal, Metal.ItemType.DOUBLE_SHEET)), f, Material.VALUE_Ingot*2);
                    SmelteryUtils.registerCasting(new ItemStack(castDoubleIngot), new ItemStack(ItemMetal.get(metal, Metal.ItemType.DOUBLE_INGOT)), f, Material.VALUE_Ingot*2);
                    if(Loader.isModLoaded("tfctech"))
                    {
                        ModuleTFCTech.castRecipes(metal, f);
                    }
                }

                if(metal.isToolMetal())
                {
                    SmelteryUtils.registerCasting(new ItemStack(ItemMetal.get(metal, Metal.ItemType.TUYERE)), new ItemStack(castTuyere), fluid, Material.VALUE_Ingot);
                }
                SmelteryUtils.registerCasting(new ItemStack(ItemMetal.get(metal, Metal.ItemType.SHEET)), new ItemStack(castSheet), fluid, Material.VALUE_Ingot);
                SmelteryUtils.registerCasting(new ItemStack(ItemMetal.get(metal, Metal.ItemType.SCRAP)), new ItemStack(castScrap), fluid, Material.VALUE_Ingot);
                SmelteryUtils.registerCasting(new ItemStack(ItemMetal.get(metal, Metal.ItemType.DOUBLE_SHEET)), new ItemStack(castDoubleSheet), fluid, Material.VALUE_Ingot*2);
                SmelteryUtils.registerCasting(new ItemStack(ItemMetal.get(metal, Metal.ItemType.DOUBLE_INGOT)), new ItemStack(castDoubleIngot), fluid, Material.VALUE_Ingot*2);

                if(Loader.isModLoaded("tfctech"))
                {
                    SmelteryUtils.registerMelting("rackwheelPiece"+path,fluid,Material.VALUE_Ingot);
                    SmelteryUtils.registerMelting("rackwheel"+path,fluid,Material.VALUE_Ingot*4);
                    SmelteryUtils.registerMelting("wire"+path,fluid,Material.VALUE_Ingot);
                    ModuleTFCTech.castingRecipes(metal, fluid);
                }
            }
        }

        for(MaterialBuilderTFC material : materials)
        {
            material.setCraftable(false).setCastable(true);
            Metal metal = material.getMetal();
            String path = cap(metal.getRegistryName().getPath());

            String doubleIngot = "ingotDouble"+path;
            String doubleSheet = "sheetDouble"+path;
            String sheet = "sheet"+path;
            Fluid fluid = getFluid(metal);
            material.setFluid(fluid);
            material.setRepresentativeItem("ingot"+path);
            material.addItem(doubleIngot, Material.VALUE_Ingot*2);
            material.addItem(sheet, Material.VALUE_Ingot);
            material.addItem(doubleSheet, Material.VALUE_Ingot*2);
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
    public void postInit(FMLPostInitializationEvent e)
    {

    }
    public void addArmorStats(MaterialBuilder mat) {
        if(Loader.isModLoaded("conarm"))
        {
            new armorUtils().setArmorStats(mat, 1);
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
    private static FluidStack getFluidStack(String fluidName, int stack)
    {
        return new FluidStack(FluidRegistry.getFluid(fluidName), Material.VALUE_Ingot*stack);
    }
}