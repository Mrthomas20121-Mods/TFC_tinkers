package com.mrthomas20121.tinkerfirmacraft;

import net.dries007.tfc.util.forge.ForgeRule;
import net.dries007.tfc.api.recipes.anvil.*;
import net.dries007.tfc.objects.items.ItemTFC;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Mod;
import slimeknights.tconstruct.library.MaterialIntegration;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.client.MaterialRenderInfo;
import slimeknights.tconstruct.library.materials.*;
import slimeknights.tconstruct.library.utils.HarvestLevels;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;
import slimeknights.tconstruct.tools.TinkerTraits;
import com.mrthomas20121.tinkerfirmacraft.Config.Config;
import com.mrthomas20121.tinkerfirmacraft.Traits.TraitsHelper;
import com.mrthomas20121.tinkerfirmacraft.TMaterial;
import net.minecraftforge.fml.common.Loader;

@Mod.EventBusSubscriber(modid=TinkerFirmaCraft.MODID)
public class TConstructHelper {

    public TConstructHelper() {
        MinecraftForge.EVENT_BUS.register(this);
    }
    public static TConstructHelper helper = new TConstructHelper();

    public static final Material sterlingSilver = new Material("sterling_silver", 0xAC927B);
    public static final Material roseGold = new Material("rose_gold", 0xEB7137);
    public static final Material redSteel = new Material("red_steel", 0x700503);
    public static final Material blueSteel = new Material("blue_steel", 0x2D5596);
    public static final Material blackSteel = new Material("black_steel", 0x111111);
    public static final Material blackBronze = new Material("black_bronze", 0x3B2636);
    public static final Material bismuthBronze = new Material("bismuth_bronze", 0x418E4F);
    public static final Material bismuth = new Material("bismuth", 0x486B72);
    public static final Material wroughtIron = new Material("wrought_iron", 0x989897);
    public static final Material platinum = new Material("platinum", 0x9DADC0);
    public static final Material nickel = new Material("nickel", 0x4E4E3C);
    public static final Material zinc = new Material("zinc", 0xE1E4E1);

    // tfc metallum Materials
    public static final Material tungsten = new Material("tungsten", 0x41454B);
    public static final Material tungstenSteel = new Material("tungstensteel", 0x565F6E);
    public static final Material osmium = new Material("osmium", 0xB9D2DD);
    public static final Material titanium = new Material("titanium", 0xC2C4CC);
    public static final Material aluminum = new Material("aluminum", 0xD9FBFC);
    public static final Material antimony = new Material("antimony", 0xE7E7F5);
    public static final Material constantan = new Material("constantan", 0xD28874);
    // Electrum not supported as it added by Tinker construct
    // public static final Material electrum = new Material("electrum", 0xDFB950);
    public static final Material mithril = new Material("mithril", 0x8ADAF6);
    public static final Material invar = new Material("invar", 0xC0C0B3);

    public String[] OredictNames = {"Bismuth", "BismuthBronze", "BlackBronze", "SterlingSilver", "RoseGold", "WroughtIron", "Platinum", "BlackSteel", "RedSteel", "BlueSteel"};
    public void preInit()
	{
	    TinkerFirmaCraft.logger.info("Loading Materials");

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
            blackSteel.setRenderInfo(0x303030);
            TinkerRegistry.addMaterialStats(blackSteel,
                    new HeadMaterialStats(540, 11.09f, 13.3f, HarvestLevels.OBSIDIAN),
                    new HandleMaterialStats(0.9f, 150),
                    new ExtraMaterialStats(30));
            TinkerRegistry.addMaterialStats(blackSteel, new BowMaterialStats(1.0f, 0.9f, 3.0f), new ArrowShaftMaterialStats(1.5f, 1));
            TinkerRegistry.integrate(blackSteel, blackSteelFluid, "BlackSteel").toolforge().preInit();
        }

        if(Config.blue_steel) {
            // blue steel
            Fluid blueSteelFluid = FluidRegistry.getFluid(("blue_steel"));
            blueSteel.setRepresentativeItem("ingotBlueSteel");
            blueSteel.addCommonItems("BlueSteel");
            blueSteel.setFluid(blueSteelFluid);
            blueSteel.setCraftable(false).setCastable(true);
            blueSteel.addTrait(TinkerTraits.sharp, MaterialTypes.HEAD);
            blueSteel.addTrait(TinkerTraits.stiff, MaterialTypes.HANDLE);
            blueSteel.addTrait(TinkerTraits.poisonous, MaterialTypes.PROJECTILE);
            blueSteel.addTrait(TinkerTraits.stiff, MaterialTypes.EXTRA);
            TinkerRegistry.addMaterialStats(blueSteel,
                    new HeadMaterialStats(1400, 12.99f, 15.00f, 6),
                    new HandleMaterialStats(0.9f, 300),
                    new ExtraMaterialStats(300));
            TinkerRegistry.addMaterialStats(blueSteel, new BowMaterialStats(4.0f, 4, 6.0f), new ArrowShaftMaterialStats(1.8f, 3));
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
                    new HeadMaterialStats(1400, 14.00f, 15.00f, 7),
                    new HandleMaterialStats(0.9f, 300),
                    new ExtraMaterialStats(300));
            TinkerRegistry.addMaterialStats(redSteel, new BowMaterialStats(4.0f, 4, 6.0f), new ArrowShaftMaterialStats(1.8f, 3));
            TinkerRegistry.integrate(redSteel, redSteelFluid, "RedSteel").toolforge().preInit();
        }
        if(Config.sterlingSilver) {

            // sterlingSilver
            Fluid sterlingSilverFluid = FluidRegistry.getFluid(("sterling_silver"));
            sterlingSilver.setRepresentativeItem("ingotSterlingSilver");
            sterlingSilver.addCommonItems("SterlingSilver");
            sterlingSilver.setFluid(sterlingSilverFluid);
            sterlingSilver.setCraftable(false).setCastable(true);
            sterlingSilver.addTrait(TinkerTraits.dense, MaterialTypes.HEAD);
            sterlingSilver.addTrait(TinkerTraits.holy);
            TinkerRegistry.addMaterialStats(sterlingSilver,
                    new HeadMaterialStats(250, 7.02f, 5.00f, HarvestLevels.DIAMOND),
                    new HandleMaterialStats(1.2f, 150),
                    new ExtraMaterialStats(80));
            TinkerRegistry.addMaterialStats(sterlingSilver, new BowMaterialStats(1.2f, 0.8f, 2.0f), new ArrowShaftMaterialStats(1.4f, 0));
            TinkerRegistry.integrate(sterlingSilver, sterlingSilverFluid, "SterlingSilver").toolforge().preInit();

        }

        if(Config.roseGold) {

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
                    new HeadMaterialStats(200, 8.00f, 4.00f, HarvestLevels.DIAMOND),
                    new HandleMaterialStats(1.2f, 200),
                    new ExtraMaterialStats(10));
            TinkerRegistry.addMaterialStats(roseGold, new BowMaterialStats(1.2f, 1.8f, 1.0f), new ArrowShaftMaterialStats(1.5f, 0));
            TinkerRegistry.integrate(roseGold, roseGoldFluid, "RoseGold").toolforge().preInit();

        }

        if(Config.wrought_iron) {
            // wrought iron
            Fluid wroughtIronFluid = FluidRegistry.getFluid(("wrought_iron"));
            wroughtIron.setRepresentativeItem("ingotWroughtIron");
            wroughtIron.addCommonItems("WroughtIron");
            wroughtIron.setFluid(wroughtIronFluid);
            wroughtIron.addTrait(TinkerTraits.magnetic);
            wroughtIron.addTrait(TraitsHelper.Magnetic3, MaterialTypes.HEAD);
            wroughtIron.addTrait(TinkerTraits.crumbling, MaterialTypes.EXTRA);
            TinkerRegistry.addMaterialStats(wroughtIron,
                    new HeadMaterialStats(800, 8.1f, 10.2f, HarvestLevels.DIAMOND),
                    new HandleMaterialStats(1.1f, 200),
                    new ExtraMaterialStats(100));
            TinkerRegistry.addMaterialStats(wroughtIron, new BowMaterialStats(3.2f, 5.2f, 1.0f), new ArrowShaftMaterialStats(3.2f, 0));
            TinkerRegistry.integrate(wroughtIron, wroughtIronFluid, "WroughtIron").toolforge().preInit();

        }

        if(Config.bismuth) {

            // bismuth
            Fluid bismuthFluid = FluidRegistry.getFluid(("bismuth"));
            bismuth.setRepresentativeItem("ingotBismuth");
            bismuth.addCommonItems("Bismuth");
            bismuth.setFluid(bismuthFluid);
            bismuth.addTrait(TinkerTraits.dense);
            bismuth.addTrait(TraitsHelper.Magnetic3, MaterialTypes.HEAD);
            bismuth.addTrait(TinkerTraits.magnetic, MaterialTypes.EXTRA);
            TinkerRegistry.addMaterialStats(bismuth,
                    new HeadMaterialStats(210, 5.3f, 4.9f, HarvestLevels.IRON),
                    new HandleMaterialStats(1f, 150),
                    new ExtraMaterialStats(10));
            TinkerRegistry.addMaterialStats(bismuth, new BowMaterialStats(0.25f, 4.2f, 1.0f), new ArrowShaftMaterialStats(1.0f, 0));
            TinkerRegistry.integrate(bismuth, bismuthFluid, "Bismuth").toolforge().preInit();

        }

        if(Config.bismuthBronze) {

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
                    new HeadMaterialStats(300, 7, 6, HarvestLevels.DIAMOND),
                    new HandleMaterialStats(1.3f, 120),
                    new ExtraMaterialStats(300));
            TinkerRegistry.addMaterialStats(bismuthBronze, new BowMaterialStats(0.40f, 1.9f, 7.2f), new ArrowShaftMaterialStats(1.1f, 2));
            TinkerRegistry.integrate(bismuthBronze, bismuthBronzeFluid, "BismuthBronze").toolforge().preInit();

        }

        if(Config.blackBronze) {

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

        }

        if(Config.zinc) {
            // zinc
            Fluid zincFluid = FluidRegistry.getFluid(("zinc"));
            zinc.setFluid(zincFluid);
            zinc.setRepresentativeItem("ingotZinc");
            zinc.addCommonItems("Zinc");
            zinc.addTrait(TraitsHelper.brittle);
            zinc.addTrait(TraitsHelper.brittle, MaterialTypes.HEAD);
            zinc.addTrait(TraitsHelper.brittle, MaterialTypes.EXTRA);
            zinc.setCastable(true).setCraftable(false);
            TinkerRegistry.addMaterialStats(zinc,
                    new HeadMaterialStats(300, 5, 4, HarvestLevels.DIAMOND),
                    new HandleMaterialStats(1.2f, 190),
                    new ExtraMaterialStats(120));
            TinkerRegistry.addMaterialStats(zinc, new BowMaterialStats(0.70f, 4.9f, 4.2f), new ArrowShaftMaterialStats(1, 2));
            TinkerRegistry.integrate(zinc, zincFluid).toolforge().preInit();
        }

        if(!Loader.isModLoaded("plustic")) {

            if(Config.platinum) {

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
                        new HeadMaterialStats(800, 7.2f, 8.9f, HarvestLevels.DIAMOND),
                        new HandleMaterialStats(2, 300),
                        new ExtraMaterialStats(300));
                TinkerRegistry.addMaterialStats(platinum, new BowMaterialStats(0.2f, 9.2f, 6.1f), new ArrowShaftMaterialStats(1.9f, 1));
                TinkerRegistry.integrate(platinum, platinumFluid).toolforge().preInit();

            }
            // nickel
            Fluid nickelFluid = FluidRegistry.getFluid(("nickel"));
            nickel.setFluid(nickelFluid);
            nickel.setRepresentativeItem("ingotNickel");
            nickel.addCommonItems("Nickel");
            nickel.addTrait(TraitsHelper.ferromagnetism);
            nickel.addTrait(TinkerTraits.poisonous, MaterialTypes.HEAD);
            nickel.addTrait(TraitsHelper.ferromagnetism, MaterialTypes.EXTRA);
            nickel.setCastable(true).setCraftable(false);
            TinkerRegistry.addMaterialStats(nickel,
                    new HeadMaterialStats(500, 5.2f, 7, HarvestLevels.DIAMOND),
                    new HandleMaterialStats(1.2f, 190),
                    new ExtraMaterialStats(120));
            TinkerRegistry.addMaterialStats(nickel, new BowMaterialStats(0.70f, 4.9f, 4.2f), new ArrowShaftMaterialStats(1, 2));
            TinkerRegistry.integrate(nickel, nickelFluid).toolforge().preInit();

            // load tfcmetallum materials
            if(Loader.isModLoaded("tfcmetallum")) {

            }
        }

	}

    public void init()
	{
        Fluid[] Fluids = {FluidRegistry.getFluid(("bismuth")).setTemperature(370), FluidRegistry.getFluid(("bismuth_bronze")).setTemperature(985), FluidRegistry.getFluid(("black_bronze")).setTemperature(1070), FluidRegistry.getFluid(("sterling_silver")).setTemperature(900), FluidRegistry.getFluid(("rose_gold")).setTemperature(960), FluidRegistry.getFluid(("wrought_iron")).setTemperature(1535), FluidRegistry.getFluid(("platinum")).setTemperature(1730), FluidRegistry.getFluid(("black_steel")).setTemperature(1485), FluidRegistry.getFluid(("red_steel")).setTemperature(1540), FluidRegistry.getFluid(("blue_steel")).setTemperature(1540)};
        // Fluids
        Fluid blackSteelFluid = FluidRegistry.getFluid(("black_steel"));
        Fluid blueSteelFluid = FluidRegistry.getFluid(("blue_steel"));
        Fluid redSteelFluid = FluidRegistry.getFluid(("red_steel"));
        Fluid copperFluid = FluidRegistry.getFluid("copper");
        Fluid silverFluid = FluidRegistry.getFluid("silver");
        Fluid sterlingSilverFluid = FluidRegistry.getFluid(("sterling_silver"));
        Fluid roseGoldFluid = FluidRegistry.getFluid(("rose_gold"));
        Fluid blackBronzeFluid = FluidRegistry.getFluid(("black_bronze"));
        Fluid bismuthFluid = FluidRegistry.getFluid(("bismuth"));
        Fluid bismuthBronzeFluid = FluidRegistry.getFluid(("bismuth_bronze"));
        Fluid wroughtIronFluid = FluidRegistry.getFluid(("wrought_iron"));
        Fluid platinumFluid = FluidRegistry.getFluid(("platinum"));

        for (int i = 0; i<OredictNames.length; i++) {
            TinkerSmeltery.registerOredictMeltingCasting(Fluids[i], OredictNames[i]);
            TinkerRegistry.registerMelting("ingotDouble" + OredictNames[i], Fluids[i], TMaterial.VALUE_DOUBLE_INGOT);
            TinkerRegistry.registerMelting("sheet" + OredictNames[i], Fluids[i], TMaterial.VALUE_SHEET);
            TinkerRegistry.registerMelting("sheetDouble" + OredictNames[i], Fluids[i], TMaterial.VALUE_DOUBLE_SHEET);
        }
        // addition
        blackSteel.setFluid(blackSteelFluid);
        blackSteel.setCraftable(false).setCastable(true);
        blueSteel.setFluid(blueSteelFluid);
        blueSteel.setCraftable(false).setCastable(true);
        redSteel.setFluid(redSteelFluid);
        redSteel.setCraftable(false).setCastable(true);
        blackBronze.setFluid(blackBronzeFluid);
        blackBronze.setCraftable(false).setCastable(true);
        bismuthBronze.setFluid(bismuthBronzeFluid);
        bismuthBronze.setCraftable(false).setCastable(true);
        bismuth.setFluid(bismuthFluid);
        bismuth.setCraftable(false).setCastable(true);
        sterlingSilver.setFluid(sterlingSilverFluid);
        sterlingSilver.setCraftable(false).setCastable(true);
        wroughtIron.setFluid(wroughtIronFluid);
        wroughtIron.setCraftable(false).setCastable(true);
        roseGold.setFluid(roseGoldFluid);
        roseGold.setCraftable(false).setCastable(true);
        platinum.setFluid(platinumFluid);
        platinum.setCraftable(false).setCastable(true);

        // register hot_water as a smeltery fluid
        Fluid hot_water = FluidRegistry.getFluid("hot_water");
        TinkerRegistry.registerSmelteryFuel(new FluidStack(hot_water.setTemperature(350), 1000),600);
        // register Alloy
        if(Config.register_alloy) {
            // sterling silver
            TinkerRegistry.registerAlloy(new FluidStack(sterlingSilverFluid, 144), new FluidStack(copperFluid, 144*2), new FluidStack(silverFluid, 144*4));

        }
   }

	public void postInit()
	{
	    sterlingSilver.setRenderInfo(0xAC927B);
        roseGold.setRenderInfo(0xEB7137);
        redSteel.setRenderInfo(0x700503);
        blueSteel.setRenderInfo(0x2D5596);
        blackSteel.setRenderInfo(0x111111);
        blackBronze.setRenderInfo(0x3B2636);
        bismuthBronze.setRenderInfo(0x418E4F);
        bismuth.setRenderInfo(0x486B72);
        wroughtIron.setRenderInfo(0x989897);
        platinum.setRenderInfo(0x9DADC0);
        nickel.setRenderInfo(0x4E4E3C);
        zinc.setRenderInfo(0xE1E4E1);
    }
}