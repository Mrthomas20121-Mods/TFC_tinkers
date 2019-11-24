package com.mrthomas20121.tinkerfirmacraft;

import com.mrthomas20121.tinkerfirmacraft.Config.Config;
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
    public static final Material platinum = new Material("platinum", 0x434C57);
    public static final Material nickel = new Material("nickel", 0x80806B);
    public static final Material zinc = new Material("zinc", 0xE1E4E1);

    // tfc metallum Materials
    public static final Material tungsten = new Material("tungsten", 0x41454B);
    public static final Material tungstenSteel = new Material("tungstensteel", 0x565F6E);
    public static final Material osmium = new Material("osmium", 0xB9D2DD);
    public static final Material titanium = new Material("titanium", 0xC2C4CC);
    public static final Material aluminum = new Material("aluminum", 0xD9FBFC);
    public static final Material antimony = new Material("antimony", 0xE7E7F5);
    public static final Material constantan = new Material("constantan", 0xD28874);
    // Not needed as it is supported by TIC
    // public static final Material electrum = new Material("electrum", 0xDFB950);
    public static final Material mithril = new Material("mithril", 0x8ADAF6);
    public static final Material invar = new Material("invar", 0xC0C0B3);

    public void preInit()
	{
	    TinkerFirmaCraft.logger.info("Loading TFC TIC Materials");
        if(Config.black_steel) {
            // black steel
            Fluid blackSteelFluid = FluidRegistry.getFluid(("black_steel"));
            blackSteel.setRepresentativeItem("ingotBlackSteel");
            blackSteel.addCommonItems("BlackSteel");
            blackSteel.setFluid(blackSteelFluid);
            blackSteel.addTrait(TinkerTraits.sharp, MaterialTypes.HEAD);
            blackSteel.addTrait(TinkerTraits.stiff, MaterialTypes.HANDLE);
            blackSteel.addTrait(TraitsHelper.Crude4, MaterialTypes.PROJECTILE);
            blackSteel.addTrait(TinkerTraits.stiff, MaterialTypes.EXTRA);
            blackSteel.setCraftable(false).setCastable(true);
            TinkerRegistry.addMaterialStats(blackSteel,
                    new HeadMaterialStats(540, 8.00f, 7.00f, HarvestLevels.OBSIDIAN),
                    new HandleMaterialStats(0.9f, 150),
                    new ExtraMaterialStats(30));
            TinkerRegistry.addMaterialStats(blackSteel, new BowMaterialStats(1.0f, 0.9f, 3.0f), new ArrowShaftMaterialStats(1.5f, 1));
            TinkerRegistry.integrate(blackSteel, blackSteelFluid, "BlackSteel").toolforge().preInit();
        }

        if(Config.blue_steel) {
            // blue steel
            Fluid blueSteelFluid = FluidRegistry.getFluid(("red_steel"));
            blueSteel.setRepresentativeItem("ingotBlueSteel");
            blueSteel.addCommonItems("BlueSteel");
            blueSteel.setFluid(blueSteelFluid);
            blueSteel.setCraftable(false).setCastable(true);
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
        }

        if(Config.red_steel) {
            // red steel
            Fluid redSteelFluid = FluidRegistry.getFluid(("red_steel"));
            redSteel.setRepresentativeItem("ingotRedSteel");
            redSteel.addCommonItems("RedSteel");
            redSteel.setFluid(redSteelFluid);
            redSteel.setCraftable(false).setCastable(true);
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
        }

        // sterlingSilver
        Fluid sterlingSilverFluid = FluidRegistry.getFluid(("sterling_silver"));
        sterlingSilver.setRepresentativeItem("ingotSterlingSilver");
        sterlingSilver.addCommonItems("SterlingSilver");
        sterlingSilver.setFluid(sterlingSilverFluid);
        sterlingSilver.setCraftable(false).setCastable(true);
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
        roseGold.setCraftable(false).setCastable(true);
        roseGold.setFluid(roseGoldFluid);
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
        wroughtIron.setFluid(wroughtIronFluid);
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
        bismuth.setFluid(bismuthFluid);
        bismuth.addTrait(TinkerTraits.dense);
        bismuth.addTrait(TraitsHelper.Magnetic3, MaterialTypes.HEAD);
        bismuth.addTrait(TinkerTraits.poisonous, MaterialTypes.EXTRA);
        TinkerRegistry.addMaterialStats(bismuth,
                new HeadMaterialStats(200, 5.2f, 4.00f, HarvestLevels.IRON),
                new HandleMaterialStats(1f, 150),
                new ExtraMaterialStats(10));
        TinkerRegistry.addMaterialStats(bismuth, new BowMaterialStats(0.25f, 4.2f, 1.0f), new ArrowShaftMaterialStats(1.0f, 0));
        TinkerRegistry.integrate(bismuth, bismuthFluid, "Bismuth").toolforge().preInit();

        // bismuth bronze
        Fluid bismuthBronzeFluid = FluidRegistry.getFluid(("bismuth_bronze"));
        bismuthBronze.setFluid(bismuthBronzeFluid);
        bismuthBronze.setRepresentativeItem("ingotBismuthBronze");
        bismuthBronze.addCommonItems("BismuthBronze");
        bismuthBronze.setFluid(bismuthBronzeFluid);
        bismuthBronze.addTrait(TinkerTraits.dense);
        bismuthBronze.addTrait(TinkerTraits.heavy, MaterialTypes.HEAD);
        bismuthBronze.addTrait(TinkerTraits.heavy, MaterialTypes.EXTRA);
        bismuthBronze.setCastable(true).setCraftable(false);
        TinkerRegistry.addMaterialStats(bismuthBronze,
                new HeadMaterialStats(300, 6, 6, HarvestLevels.IRON),
                new HandleMaterialStats(1.3f, 120),
                new ExtraMaterialStats(300));
        TinkerRegistry.addMaterialStats(bismuthBronze, new BowMaterialStats(0.40f, 1.9f, 7.2f), new ArrowShaftMaterialStats(1.1f, 2));
        TinkerRegistry.integrate(bismuthBronze, bismuthBronzeFluid, "BismuthBronze").toolforge().preInit();

        // black bronze
        Fluid blackBronzeFluid = FluidRegistry.getFluid(("black_bronze"));
        blackBronze.setFluid(blackBronzeFluid);
        blackBronze.setRepresentativeItem("ingotBlackBronze");
        blackBronze.addCommonItems("BlackBronze");
        blackBronze.setFluid(blackBronzeFluid);
        blackBronze.addTrait(TinkerTraits.dense);
        blackBronze.addTrait(TinkerTraits.jagged, MaterialTypes.HEAD);
        blackBronze.addTrait(TinkerTraits.heavy, MaterialTypes.EXTRA);
        blackBronze.setCastable(true).setCraftable(false);
        TinkerRegistry.addMaterialStats(blackBronze,
                new HeadMaterialStats(330, 7, 6, HarvestLevels.DIAMOND),
                new HandleMaterialStats(1.9f, 200),
                new ExtraMaterialStats(250));
        TinkerRegistry.addMaterialStats(blackBronze, new BowMaterialStats(0.70f, 4.9f, 4.2f), new ArrowShaftMaterialStats(1.1f, 1));
        TinkerRegistry.integrate(blackBronze, blackBronzeFluid, "BlackBronze").toolforge().preInit();

        if(!Loader.isModLoaded("plustic")) {

            // platinum
            Fluid platinumFluid = FluidRegistry.getFluid(("platinum"));
            platinum.setFluid(platinumFluid);
            platinum.setRepresentativeItem("ingotPlatinum");
            platinum.addCommonItems("Platinum");
            platinum.addTrait(TinkerTraits.heavy);
            platinum.addTrait(TinkerTraits.poisonous, MaterialTypes.HEAD);
            platinum.addTrait(TinkerTraits.dense, MaterialTypes.EXTRA);
            platinum.setCastable(true).setCraftable(false);
            TinkerRegistry.addMaterialStats(platinum,
                    new HeadMaterialStats(800, 8, 8, HarvestLevels.DIAMOND),
                    new HandleMaterialStats(2, 300),
                    new ExtraMaterialStats(300));
            TinkerRegistry.addMaterialStats(platinum, new BowMaterialStats(0.70f, 4.9f, 4.2f), new ArrowShaftMaterialStats(1, 2));
            TinkerRegistry.integrate(platinum, platinumFluid, "Platinum").toolforge().preInit();

            // nickel
            Fluid nickelFluid = FluidRegistry.getFluid(("nickel"));
            nickel.setFluid(nickelFluid);
            nickel.setRepresentativeItem("ingotNickel");
            //nickel.addCommonItems("Nickel");
            nickel.addTrait(TinkerTraits.heavy);
            nickel.addTrait(TinkerTraits.poisonous, MaterialTypes.HEAD);
            nickel.addTrait(TinkerTraits.crumbling, MaterialTypes.EXTRA);
            nickel.setCastable(true).setCraftable(false);
            TinkerRegistry.addMaterialStats(nickel,
                    new HeadMaterialStats(500, 7, 8, HarvestLevels.DIAMOND),
                    new HandleMaterialStats(1.2f, 190),
                    new ExtraMaterialStats(120));
            TinkerRegistry.addMaterialStats(nickel, new BowMaterialStats(0.70f, 4.9f, 4.2f), new ArrowShaftMaterialStats(1, 2));
            TinkerRegistry.integrate(nickel, nickelFluid, "Nickel").toolforge().preInit();
        }
        if(!Loader.isModLoaded("nuclearcraft")) {
            // zinc
            Fluid zincFluid = FluidRegistry.getFluid(("zinc"));
            zinc.setFluid(zincFluid);
            zinc.setRepresentativeItem("ingotZinc");
            //zinc.addCommonItems("Zinc");
            zinc.addTrait(TinkerTraits.momentum);
            zinc.addTrait(TinkerTraits.unnatural, MaterialTypes.HEAD);
            zinc.addTrait(TinkerTraits.dense, MaterialTypes.EXTRA);
            zinc.setCastable(true).setCraftable(false);
            TinkerRegistry.addMaterialStats(zinc,
                    new HeadMaterialStats(500, 7, 8, HarvestLevels.DIAMOND),
                    new HandleMaterialStats(1.2f, 190),
                    new ExtraMaterialStats(120));
            TinkerRegistry.addMaterialStats(zinc, new BowMaterialStats(0.70f, 4.9f, 4.2f), new ArrowShaftMaterialStats(1, 2));
            TinkerRegistry.integrate(zinc, zincFluid, "Zinc").toolforge().preInit();
        }

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