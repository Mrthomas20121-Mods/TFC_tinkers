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
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
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
import net.dries007.tfc.objects.fluids.FluidsTFC;

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
    public static final boolean metallum = Loader.isModLoaded("tfcmetallum");

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
            if(metallum) {
                if(!Loader.isModLoaded("immersiveengineering")) {
                    if(Config.constantan) {
                        Fluid constantanFluid = FluidRegistry.getFluid(("constantan"));
                        constantan.setFluid(constantanFluid);
                        constantan.setRepresentativeItem("ingotConstantan");
                        constantan.addCommonItems("Constantan");
                        constantan.addTrait(TinkerTraits.crumbling);
                        constantan.addTrait(TinkerTraits.jagged, MaterialTypes.HEAD);
                        constantan.addTrait(TinkerTraits.crumbling, MaterialTypes.EXTRA);
                        constantan.setCastable(true).setCraftable(false);
                        TinkerRegistry.addMaterialStats(constantan,
                                new HeadMaterialStats(800, 8.2f, 8, HarvestLevels.DIAMOND),
                                new HandleMaterialStats(0.9f, 300),
                                new ExtraMaterialStats(300));
                        TinkerRegistry.addMaterialStats(constantan, new BowMaterialStats(0.70f, 4.9f, 4.2f), new ArrowShaftMaterialStats(1, 2));
                        TinkerRegistry.integrate(constantan, constantanFluid).toolforge().preInit();
                    }
                }
                if(Config.tungsten) {
                    Fluid tungstenFluid = FluidRegistry.getFluid(("tungsten"));
                    tungsten.setFluid(tungstenFluid);
                    tungsten.setRepresentativeItem("ingotTungsten");
                    tungsten.addCommonItems("Tungsten");
                    tungsten.addTrait(TinkerTraits.heavy);
                    tungsten.addTrait(TinkerTraits.duritos, MaterialTypes.HEAD);
                    tungsten.addTrait(TraitsHelper.brittle, MaterialTypes.EXTRA);
                    tungsten.setCastable(true).setCraftable(false);
                    TinkerRegistry.addMaterialStats(tungsten,
                            new HeadMaterialStats(500, 6.2f, 7, HarvestLevels.IRON),
                            new HandleMaterialStats(0.4f, 330),
                            new ExtraMaterialStats(190));
                    TinkerRegistry.addMaterialStats(tungsten, new BowMaterialStats(0.60f, 3.9f, 6.2f), new ArrowShaftMaterialStats(1.2f, 2));
                    TinkerRegistry.integrate(tungsten, tungstenFluid).toolforge().preInit();
                }

                if(Config.tungstenSteel) {
                    Fluid tungstenSteelFluid = FluidRegistry.getFluid(("tungsten_steel"));
                    tungstenSteel.setFluid(tungstenSteelFluid);
                    tungstenSteel.setRepresentativeItem("ingotTungstenSteel");
                    tungstenSteel.addCommonItems("TungstenSteel");
                    tungstenSteel.addTrait(TinkerTraits.heavy);
                    tungstenSteel.addTrait(TinkerTraits.sharp, MaterialTypes.HEAD);
                    tungstenSteel.addTrait(TinkerTraits.stiff, MaterialTypes.HANDLE);
                    tungstenSteel.setCastable(true).setCraftable(false);
                    TinkerRegistry.addMaterialStats(tungstenSteel,
                            new HeadMaterialStats(500, 7.2f, 7, HarvestLevels.DIAMOND),
                            new HandleMaterialStats(0.4f, 330),
                            new ExtraMaterialStats(190));
                    TinkerRegistry.addMaterialStats(tungstenSteel, new BowMaterialStats(0.60f, 3.9f, 6.2f), new ArrowShaftMaterialStats(1.2f, 2));
                    TinkerRegistry.integrate(tungstenSteel, tungstenSteelFluid).toolforge().preInit();
                }

                if(Config.titanium) {
                    Fluid titaniumFluid = FluidRegistry.getFluid(("titanium"));
                    titanium.setFluid(titaniumFluid);
                    titanium.setRepresentativeItem("ingotTitanium");
                    titanium.addCommonItems("Titanium");
                    titanium.addTrait(TinkerTraits.lightweight);
                    titanium.addTrait(TinkerTraits.sharp, MaterialTypes.HEAD);
                    titanium.addTrait(TinkerTraits.momentum, MaterialTypes.HANDLE);
                    titanium.setCastable(true).setCraftable(false);
                    TinkerRegistry.addMaterialStats(titanium,
                            new HeadMaterialStats(100, 6.1f, 8.1f, HarvestLevels.IRON),
                            new HandleMaterialStats(0.9f, 100),
                            new ExtraMaterialStats(100));
                    TinkerRegistry.addMaterialStats(titanium, new BowMaterialStats(0.60f, 5.0f, 8.2f), new ArrowShaftMaterialStats(1, 2));
                    TinkerRegistry.integrate(titanium, titaniumFluid).toolforge().preInit();
                }

                if(Config.aluminum) {
                    Fluid aluminumFluid = FluidRegistry.getFluid(("aluminum"));
                    aluminum.setFluid(aluminumFluid);
                    aluminum.setRepresentativeItem("ingotAluminum");
                    aluminum.addCommonItems("Aluminum");
                    aluminum.addTrait(TinkerTraits.duritos);
                    aluminum.addTrait(TinkerTraits.jagged, MaterialTypes.HEAD);
                    aluminum.addTrait(TinkerTraits.momentum, MaterialTypes.HANDLE);
                    aluminum.setCastable(true).setCraftable(false);
                    TinkerRegistry.addMaterialStats(aluminum,
                            new HeadMaterialStats(320, 7.1f, 6.1f, HarvestLevels.IRON),
                            new HandleMaterialStats(0.9f, 200),
                            new ExtraMaterialStats(130));
                    TinkerRegistry.addMaterialStats(aluminum, new BowMaterialStats(0.60f, 5.0f, 4.2f), new ArrowShaftMaterialStats(1, 2));
                    TinkerRegistry.integrate(aluminum, aluminumFluid).toolforge().preInit();
                }

                if(Config.antimony) {
                    Fluid antimonyFluid = FluidRegistry.getFluid(("antimony"));
                    antimony.setFluid(antimonyFluid);
                    antimony.setRepresentativeItem("ingotAntimony");
                    antimony.addCommonItems("Antimony");
                    antimony.addTrait(TinkerTraits.depthdigger);
                    antimony.addTrait(TinkerTraits.jagged, MaterialTypes.HEAD);
                    antimony.addTrait(TinkerTraits.momentum, MaterialTypes.HANDLE);
                    antimony.setCastable(true).setCraftable(false);
                    TinkerRegistry.addMaterialStats(antimony,
                            new HeadMaterialStats(220, 7, 5.9f, HarvestLevels.IRON),
                            new HandleMaterialStats(0.7f, 400),
                            new ExtraMaterialStats(230));
                    TinkerRegistry.addMaterialStats(antimony, new BowMaterialStats(0.60f, 5.0f, 5.2f), new ArrowShaftMaterialStats(1, 2));
                    TinkerRegistry.integrate(antimony, antimonyFluid).toolforge().preInit();
                }

                if(Config.osmium) {
                    Fluid osmiumFluid = FluidRegistry.getFluid(("osmium"));
                    osmium.setFluid(osmiumFluid);
                    osmium.setRepresentativeItem("ingotOsmium");
                    osmium.addCommonItems("Osmium");
                    osmium.addTrait(TinkerTraits.dense);
                    osmium.addTrait(TinkerTraits.established, MaterialTypes.HEAD);
                    osmium.addTrait(TinkerTraits.dense, MaterialTypes.HANDLE);
                    osmium.setCastable(true).setCraftable(false);
                    TinkerRegistry.addMaterialStats(osmium,
                            new HeadMaterialStats(440, 6, 6.3f, HarvestLevels.IRON),
                            new HandleMaterialStats(1f, 330),
                            new ExtraMaterialStats(220));
                    TinkerRegistry.addMaterialStats(osmium, new BowMaterialStats(0.60f, 3.0f, 6.2f), new ArrowShaftMaterialStats(1, 2));
                    TinkerRegistry.integrate(osmium, osmiumFluid).toolforge().preInit();
                }

                if(Config.invar) {
                    Fluid invarFluid = FluidRegistry.getFluid(("invar"));
                    invar.setFluid(invarFluid);
                    invar.setRepresentativeItem("ingotInvar");
                    invar.addCommonItems("Invar");
                    invar.addTrait(TinkerTraits.crumbling);
                    invar.addTrait(TinkerTraits.established, MaterialTypes.HEAD);
                    invar.addTrait(TinkerTraits.crumbling, MaterialTypes.HANDLE);
                    invar.setCastable(true).setCraftable(false);
                    TinkerRegistry.addMaterialStats(invar,
                            new HeadMaterialStats(640, 7.2f, 7.5f, HarvestLevels.IRON),
                            new HandleMaterialStats(1.1f, 330),
                            new ExtraMaterialStats(330));
                    TinkerRegistry.addMaterialStats(invar, new BowMaterialStats(1.1f, 2.0f, 5.2f), new ArrowShaftMaterialStats(1, 2));
                    TinkerRegistry.integrate(invar, invarFluid).toolforge().preInit();
                }
            }
        }

	}

    public void init()
	{
        Fluid[] Fluids = {FluidRegistry.getFluid(("bismuth")).setTemperature(370), FluidRegistry.getFluid(("bismuth_bronze")).setTemperature(985), FluidRegistry.getFluid(("black_bronze")).setTemperature(1070), FluidRegistry.getFluid(("sterling_silver")).setTemperature(900), FluidRegistry.getFluid(("rose_gold")).setTemperature(960), FluidRegistry.getFluid(("wrought_iron")).setTemperature(1000), FluidRegistry.getFluid(("platinum")).setTemperature(930), FluidRegistry.getFluid(("black_steel")).setTemperature(1000), FluidRegistry.getFluid(("red_steel")).setTemperature(1000), FluidRegistry.getFluid(("blue_steel")).setTemperature(1000)};
        // Fluids
        Fluid blackSteelFluid = FluidRegistry.getFluid(("black_steel"));
        Fluid blueSteelFluid = FluidRegistry.getFluid(("blue_steel"));
        Fluid blueWeakSteelFluid = FluidRegistry.getFluid(("weak_blue_steel"));
        Fluid redSteelFluid = FluidRegistry.getFluid(("red_steel"));
        Fluid redWeakSteelFluid = FluidRegistry.getFluid(("weak_red_steel"));
        Fluid weakSteelFluid = FluidRegistry.getFluid(("weak_steel"));
        Fluid copperFluid = FluidRegistry.getFluid("copper");
        Fluid silverFluid = FluidRegistry.getFluid("silver");
        Fluid sterlingSilverFluid = FluidRegistry.getFluid(("sterling_silver"));
        Fluid roseGoldFluid = FluidRegistry.getFluid(("rose_gold"));
        Fluid blackBronzeFluid = FluidRegistry.getFluid(("black_bronze"));
        Fluid bismuthFluid = FluidRegistry.getFluid(("bismuth"));
        Fluid bismuthBronzeFluid = FluidRegistry.getFluid(("bismuth_bronze"));
        Fluid wroughtIronFluid = FluidRegistry.getFluid(("wrought_iron"));
        Fluid platinumFluid = FluidRegistry.getFluid(("platinum"));
        Fluid goldFluid = FluidRegistry.getFluid(("gold"));
        Fluid bronzeFluid = FluidRegistry.getFluid(("bronze"));
        Fluid brassFluid = FluidRegistry.getFluid(("brass"));
        Fluid zincFluid = FluidRegistry.getFluid(("zinc"));
        Fluid tungstenFluid = FluidRegistry.getFluid(("tungsten"));
        Fluid tungstenSteelFluid = FluidRegistry.getFluid(("tungsten_steel"));
        Fluid steelFluid = FluidRegistry.getFluid(("steel"));
        Fluid nickelFluid = FluidRegistry.getFluid(("nickel"));
        Fluid constantanFluid = FluidRegistry.getFluid(("constantan"));
        Fluid osmiumFluid = FluidRegistry.getFluid(("osmium"));
        Fluid titaniumFluid = FluidRegistry.getFluid(("titanium"));
        Fluid aluminumFluid = FluidRegistry.getFluid(("aluminum"));
        Fluid mithrilFluid = FluidRegistry.getFluid(("mithril"));
        Fluid invarFluid = FluidRegistry.getFluid(("invar"));
        Fluid antimonyFluid = FluidRegistry.getFluid(("antimony"));

        for (int i = 0; i<OredictNames.length; i++) {
            this.registeryMelting(Fluids[i], OredictNames[i]);
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
        if(metallum) {
            // constantan
            constantan.setFluid(constantanFluid);
            constantan.setCraftable(false).setCastable(true);
            constantanFluid.setTemperature(750);
            this.registeryMelting(constantanFluid, "Constantan");
            // tungten
            tungsten.setFluid(tungstenFluid);
            tungsten.setCraftable(false).setCastable(true);
            this.registeryMelting(tungstenFluid, "Tungsten");

            // tungsten steel
            tungstenSteel.setFluid(tungstenSteelFluid);
            tungstenSteel.setCraftable(false).setCastable(true);
            this.registeryMelting(tungstenSteelFluid, "TungstenSteel");

            // osmium
            osmium.setFluid(osmiumFluid);
            tungstenSteel.setCraftable(false).setCastable(true);
            this.registeryMelting(osmiumFluid, "Osmium");

            // titanium
            titanium.setFluid(titaniumFluid);
            titanium.setCraftable(false).setCastable(true);
            this.registeryMelting(titaniumFluid, "Titanium");

            // aluminum
            aluminum.setFluid(aluminumFluid);
            aluminum.setCraftable(false).setCastable(true);
            this.registeryMelting(aluminumFluid, "Aluminum");

            // antimony
            antimony.setFluid(antimonyFluid);
            antimony.setCraftable(false).setCastable(true);
            this.registeryMelting(antimonyFluid, "Antimony");

            // mithril
            mithril.setFluid(mithrilFluid);
            mithril.setCraftable(false).setCastable(true);
            this.registeryMelting(mithrilFluid, "Mithril");

            // invar
            invar.setFluid(invarFluid);
            invar.setCraftable(false).setCastable(true);
            this.registeryMelting(invarFluid, "Invar");
        }

        TinkerSmeltery.registerOredictMeltingCasting(blueWeakSteelFluid.setTemperature(1000), "WeakBlueSteel");
        TinkerSmeltery.registerOredictMeltingCasting(redWeakSteelFluid.setTemperature(1000), "WeakRedSteel");
        TinkerSmeltery.registerOredictMeltingCasting(weakSteelFluid.setTemperature(1000), "WeakSteel");

        // register hot_water as a smeltery fluid
        TinkerRegistry.registerSmelteryFuel(new FluidStack(FluidsTFC.HOT_WATER.get(), 1000),600);
        // register Alloy
        if(Config.register_alloy) {
            // sterling silver
            TinkerRegistry.registerAlloy(new FluidStack(sterlingSilverFluid, 144), new FluidStack(copperFluid, 144*2), new FluidStack(silverFluid, 144*4));
            TinkerRegistry.registerAlloy(new FluidStack(roseGoldFluid, 144), new FluidStack(copperFluid, 144), new FluidStack(goldFluid, 144*5));
            TinkerRegistry.registerAlloy(new FluidStack(bismuthBronzeFluid, 144), new FluidStack(bismuthFluid, 144), new FluidStack(zincFluid, 144*2), new FluidStack(copperFluid, 144*4));
            TinkerRegistry.registerAlloy(new FluidStack(blackBronzeFluid, 144), new FluidStack(goldFluid, 144*2), new FluidStack(silverFluid, 144*2), new FluidStack(copperFluid, 144*5));
            TinkerRegistry.registerAlloy(new FluidStack(redWeakSteelFluid, 144), new FluidStack(steelFluid, 144*2), new FluidStack(blackSteelFluid, 144*5), new FluidStack(roseGoldFluid, 144), new FluidStack(brassFluid, 144));
            TinkerRegistry.registerAlloy(new FluidStack(blueWeakSteelFluid, 144), new FluidStack(steelFluid, 144*2), new FluidStack(blackSteelFluid, 144*5), new FluidStack(sterlingSilverFluid, 144), new FluidStack(bismuthBronzeFluid, 144));
            TinkerRegistry.registerAlloy(new FluidStack(weakSteelFluid, 144), new FluidStack(steelFluid, 144*5), new FluidStack(nickelFluid, 144*2), new FluidStack(blackBronzeFluid, 144));

            if(metallum) {
                TinkerRegistry.registerAlloy(new FluidStack(tungstenSteelFluid, 144), new FluidStack(tungstenFluid, 144), new FluidStack(steelFluid, 144*5));
            }
        }
   }

    @SideOnly(Side.CLIENT)
	public void clientPostInit()
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
        constantan.setRenderInfo(0xD28874);
        tungsten.setRenderInfo(0x41454B);
        tungstenSteel.setRenderInfo(0x565F6E);
        osmium.setRenderInfo(0xB9D2DD);
        titanium.setRenderInfo(0xD9FBFC);
        aluminum.setRenderInfo(0xD9FBFC);
        antimony.setRenderInfo(0xE7E7F5);
        mithril.setRenderInfo(0x8ADAF6);
        invar.setRenderInfo(0xC0C0B3);
    }
    public void registeryMelting(Fluid f, String ore) {

        TinkerSmeltery.registerOredictMeltingCasting(f, ore);
        TinkerRegistry.registerMelting("ingotDouble" + ore, f, TMaterial.VALUE_DOUBLE_INGOT);
        TinkerRegistry.registerMelting("sheet" + ore, f, TMaterial.VALUE_SHEET);
        TinkerRegistry.registerMelting("sheetDouble" + ore, f, TMaterial.VALUE_DOUBLE_SHEET);
    }
}