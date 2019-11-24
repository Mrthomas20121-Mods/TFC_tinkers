package com.mrthomas20121.tinkerfirmacraft;

import com.mrthomas20121.tinkerfirmacraft.proxy.ClientProxy;
import com.mrthomas20121.tinkerfirmacraft.proxy.CommonProxy;
import static net.dries007.tfc.types.DefaultMetals.*;

import net.dries007.tfc.api.recipes.knapping.KnappingRecipeStone;
import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.api.types.Rock;
import net.dries007.tfc.compat.jei.wrappers.KnappingRecipeWrapper;
import net.dries007.tfc.objects.fluids.properties.FluidWrapper;
import net.dries007.tfc.objects.items.ItemTFC;
import net.minecraft.item.Item;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import slimeknights.tconstruct.library.MaterialIntegration;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.client.MaterialRenderInfo;
import slimeknights.tconstruct.library.materials.*;
import slimeknights.tconstruct.library.tinkering.TinkersItem;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.utils.HarvestLevels;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;
import slimeknights.tconstruct.tools.TinkerTraits;
import slimeknights.tconstruct.tools.traits.TraitMagnetic;
import net.dries007.tfc.objects.fluids.FluidsTFC;
import net.dries007.tfc.objects.items.ItemsTFC;
import com.mrthomas20121.tinkerfirmacraft.Traits.TraitsHelper;
import net.minecraftforge.fml.common.Loader;

@Mod.EventBusSubscriber(modid=TinkerFirmaCraft.MODID)
public class TConstructHelper {

    public TConstructHelper() {
        MinecraftForge.EVENT_BUS.register(this);
    }
    public static TConstructHelper helper = new TConstructHelper();

    public static final Material sterlingSilver = new Material("sterlingSilver", 0xCABEB6);
    public static final Material roseGold = new Material("rosegold", 0xF7CCBC);
    public static final Material redSteel = new Material("redsteel", 0xBB3434);
    public static final Material blueSteel = new Material("bluesteel", 0x4673AD);
    public static final Material blackSteel = new Material("blacksteel", 0x303030);
    public static final Material blackBronze = new Material("blackbronze", 0x5A344A);
    public static final Material bismuthBronze = new Material("bismuthbronze", 0x477D51);
    public static final Material bismuth = new Material("bismuth", 0x4B614E);
    public static final Material wroughtIron = new Material("wroughtiron", 0xBABABA);
    // tfc metallum Materials
    public static final Material tungsten = new Material("tungsten", 0x41454B);
    public static final Material tungstenSteel = new Material("tungstensteel", 0x565F6E);
    public static final Material osmium = new Material("osmium", 0xB9D2DD);
    public static final Material titanium = new Material("titanium", 0xC2C4CC);
    public static final Material aluminum = new Material("aluminum", 0xD9FBFC);

    public void preInit()
	{
	    TinkerFirmaCraft.logger.info("Loading TFC TIC Materials");

	    // black steel
        Fluid blackSteelFluid = FluidRegistry.getFluid(("black_steel"));
        blackSteel.setRepresentativeItem("ingotBlackSteel");
        blackSteel.addCommonItems("BlackSteel");
        blackSteel.addTrait(TinkerTraits.sharp, MaterialTypes.HEAD);
        blackSteel.addTrait(TinkerTraits.stiff, MaterialTypes.HANDLE);
        blackSteel.addTrait(TraitsHelper.Crude4, MaterialTypes.PROJECTILE);
        blackSteel.addTrait(TinkerTraits.stiff, MaterialTypes.EXTRA);
        TinkerRegistry.addMaterialStats(blackSteel,
                new HeadMaterialStats(540, 8.00f, 7.00f, HarvestLevels.OBSIDIAN),
                new HandleMaterialStats(0.9f, 150),
                new ExtraMaterialStats(30));
        TinkerRegistry.addMaterialStats(blackSteel, new BowMaterialStats(1.0f, 0.9f, 3.0f), new ArrowShaftMaterialStats(1.5f, 1));
        TinkerRegistry.integrate(blackSteel, blackSteelFluid, "BlackSteel").toolforge().preInit();

        // blue steel
        Fluid blueSteelFluid = FluidRegistry.getFluid(("red_steel"));
        blueSteel.setRepresentativeItem("ingotBlueSteel");
        blueSteel.addCommonItems("BlueSteel");
        blueSteel.addTrait(TinkerTraits.sharp, MaterialTypes.HEAD);
        blueSteel.addTrait(TinkerTraits.stiff, MaterialTypes.HANDLE);
        blueSteel.addTrait(TinkerTraits.poisonous, MaterialTypes.PROJECTILE);
        blueSteel.addTrait(TinkerTraits.stiff, MaterialTypes.EXTRA);
        TinkerRegistry.addMaterialStats(blueSteel,
                new HeadMaterialStats(740, 9.00f, 9.00f, 6),
                new HandleMaterialStats(0.9f, 150),
                new ExtraMaterialStats(30));
        TinkerRegistry.addMaterialStats(blueSteel, new BowMaterialStats(1.0f, 0.9f, 3.0f), new ArrowShaftMaterialStats(1.5f, 1));
        TinkerRegistry.integrate(blueSteel, blueSteelFluid, "BlueSteel").toolforge().preInit();

        // red steel
        Fluid redSteelFluid = FluidRegistry.getFluid(("red_steel"));
        redSteel.setRepresentativeItem("ingotRedSteel");
        redSteel.addCommonItems("RedSteel");
        redSteel.addTrait(TinkerTraits.sharp, MaterialTypes.HEAD);
        redSteel.addTrait(TinkerTraits.stiff, MaterialTypes.HANDLE);
        redSteel.addTrait(TinkerTraits.poisonous, MaterialTypes.PROJECTILE);
        redSteel.addTrait(TinkerTraits.stiff, MaterialTypes.EXTRA);
        TinkerRegistry.addMaterialStats(redSteel,
                new HeadMaterialStats(800, 11.00f, 11.00f, 7),
                new HandleMaterialStats(0.9f, 150),
                new ExtraMaterialStats(30));
        TinkerRegistry.addMaterialStats(redSteel, new BowMaterialStats(1.0f, 0.9f, 3.0f), new ArrowShaftMaterialStats(1.8f, 2));
        TinkerRegistry.integrate(redSteel, redSteelFluid, "RedSteel").toolforge().preInit();

        // sterlingSilver
        Fluid sterlingSilverFluid = FluidRegistry.getFluid(("sterling_silver"));
        sterlingSilver.setRepresentativeItem("ingotSterlingSilver");
        sterlingSilver.addCommonItems("SterlingSilver");
        sterlingSilver.addTrait(TinkerTraits.dense, MaterialTypes.HEAD);
        sterlingSilver.addTrait(TinkerTraits.holy);
        TinkerRegistry.addMaterialStats(sterlingSilver,
                new HeadMaterialStats(250, 6.00f, 5.00f, HarvestLevels.DIAMOND),
                new HandleMaterialStats(1.2f, 150),
                new ExtraMaterialStats(80));
        TinkerRegistry.addMaterialStats(sterlingSilver, new BowMaterialStats(1.2f, 0.8f, 2.0f), new ArrowShaftMaterialStats(1.4f, 0));
        TinkerRegistry.integrate(sterlingSilver, sterlingSilverFluid, "SterlingSilver").toolforge().preInit();

        // roseGold
        Fluid roseGoldFluid = FluidRegistry.getFluid(("rose_gold"));
        roseGold.setRepresentativeItem("ingotRoseGold");
        roseGold.addCommonItems("RoseGold");
        roseGold.addTrait(TinkerTraits.dense, MaterialTypes.HEAD);
        roseGold.addTrait(TinkerTraits.unnatural, MaterialTypes.EXTRA);
        roseGold.addTrait(TinkerTraits.heavy);
        TinkerRegistry.addMaterialStats(roseGold,
                new HeadMaterialStats(200, 5.00f, 4.00f, HarvestLevels.DIAMOND),
                new HandleMaterialStats(1.2f, 200),
                new ExtraMaterialStats(10));
        TinkerRegistry.addMaterialStats(roseGold, new BowMaterialStats(1.2f, 1.8f, 1.0f), new ArrowShaftMaterialStats(1.5f, 0));
        TinkerRegistry.integrate(roseGold, roseGoldFluid, "RoseGold").toolforge().preInit();

        // wrought iron
        Fluid wroughtIronFluid = FluidRegistry.getFluid(("wrought_iron"));
        wroughtIron.setRepresentativeItem("ingotWroughtIron");
        wroughtIron.addCommonItems("WroughtIron");
        wroughtIron.addTrait(TinkerTraits.established);
        wroughtIron.addTrait(TraitsHelper.Magnetic3, MaterialTypes.HEAD);
        wroughtIron.addTrait(TinkerTraits.jagged, MaterialTypes.EXTRA);
        TinkerRegistry.addMaterialStats(wroughtIron,
                new HeadMaterialStats(500, 6.3f, 5.00f, HarvestLevels.DIAMOND),
                new HandleMaterialStats(1.1f, 200),
                new ExtraMaterialStats(100));
        TinkerRegistry.addMaterialStats(wroughtIron, new BowMaterialStats(0.25f, 4.2f, 1.0f), new ArrowShaftMaterialStats(1.0f, 0));
        TinkerRegistry.integrate(wroughtIron, wroughtIronFluid, "WroughtIron").toolforge().preInit();

        // bismuth
        Fluid bismuthFluid = FluidRegistry.getFluid(("bismuth"));
        bismuth.setRepresentativeItem("ingotBismuth");
        bismuth.addCommonItems("Bismuth");
        bismuth.addTrait(TinkerTraits.dense);
        bismuth.addTrait(TraitsHelper.Magnetic3, MaterialTypes.HEAD);
        bismuth.addTrait(TinkerTraits.poisonous, MaterialTypes.EXTRA);
        TinkerRegistry.addMaterialStats(bismuth,
                new HeadMaterialStats(200, 5.2f, 4.00f, HarvestLevels.IRON),
                new HandleMaterialStats(1f, 150),
                new ExtraMaterialStats(10));
        TinkerRegistry.addMaterialStats(bismuth, new BowMaterialStats(0.25f, 4.2f, 1.0f), new ArrowShaftMaterialStats(1.0f, 0));
        TinkerRegistry.integrate(bismuth, bismuthFluid, "Bismuth").toolforge().preInit();

        if(!Loader.isModLoaded("plustic") && Loader.isModLoaded("tfcmetallum")) {
            // load
        }

        TinkerFirmaCraft.logger.info("TFC TIC Materials Loaded");
	}

    public void init()
	{

   }

	public void postInit()
	{
    }
}