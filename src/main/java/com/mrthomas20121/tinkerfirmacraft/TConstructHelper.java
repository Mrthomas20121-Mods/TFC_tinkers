package com.mrthomas20121.tinkerfirmacraft;

import com.mrthomas20121.tinkerfirmacraft.proxy.ClientProxy;
import com.mrthomas20121.tinkerfirmacraft.proxy.CommonProxy;
import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.api.types.Metal.Tier;
import net.dries007.tfc.objects.items.ItemTFC;
import net.minecraft.item.Item;
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
import net.dries007.tfc.objects.fluids.FluidsTFC;
import net.dries007.tfc.objects.items.ItemsTFC;

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

    public void preInit()
	{
	    TinkerFirmaCraft.logger.info("Loading TIC Materials");
        // sterlingSilver
        TinkerRegistry.addMaterialStats(sterlingSilver,
                new HeadMaterialStats(250, 6.00f, 5.00f, HarvestLevels.DIAMOND),
                new HandleMaterialStats(1.2f, 150),
                new ExtraMaterialStats(80));
        TinkerRegistry.addMaterialStats(sterlingSilver, new BowMaterialStats(1.2f, 0.8f, 2.0f), new ArrowShaftMaterialStats(1.4f, 0));
        sterlingSilver.setCraftable(false);
        sterlingSilver.setCastable(true);
        sterlingSilver.addCommonItems("SterlingSilver");
        sterlingSilver.addTrait(TinkerTraits.dense, MaterialTypes.HEAD);
        sterlingSilver.addTrait(TinkerTraits.holy);
        MaterialIntegration ss = new MaterialIntegration(sterlingSilver).setRepresentativeItem("ingotSterlingSilver");
        TinkerRegistry.integrate(ss).preInit();

        // roseGold
        TinkerRegistry.addMaterialStats(roseGold,
                new HeadMaterialStats(200, 5.00f, 4.00f, HarvestLevels.DIAMOND),
                new HandleMaterialStats(1.2f, 200),
                new ExtraMaterialStats(10));
        roseGold.setCastable(true);
        roseGold.addCommonItems("RoseGold");
        roseGold.addTrait(TinkerTraits.dense, MaterialTypes.HEAD);
        roseGold.addTrait(TinkerTraits.unnatural, MaterialTypes.EXTRA);
        roseGold.addTrait(TinkerTraits.heavy);
        TinkerRegistry.addMaterialStats(roseGold, new BowMaterialStats(1.2f, 1.8f, 1.0f), new ArrowShaftMaterialStats(1.5f, 0));
        MaterialIntegration rg = new MaterialIntegration(roseGold).setRepresentativeItem("ingotRoseGold");
        TinkerRegistry.integrate(rg).preInit();

        TinkerRegistry.addMaterialStats(bismuth,
                new HeadMaterialStats(170, 4.00f, 4.00f, HarvestLevels.IRON),
                new HandleMaterialStats(1f, 150),
                new ExtraMaterialStats(10));
        bismuth.setCastable(true);
        bismuth.addCommonItems("Bismuth");
        bismuth.addTrait(TinkerTraits.dense, MaterialTypes.HEAD);
        bismuth.addTrait(TinkerTraits.poisonous, MaterialTypes.EXTRA);
        TinkerRegistry.addMaterialStats(bismuth, new BowMaterialStats(0.25f, 4.2f, 1.0f), new ArrowShaftMaterialStats(1.0f, 0));
        MaterialIntegration bismuthMat = new MaterialIntegration(bismuth).setRepresentativeItem("ingotBismuth");
        TinkerRegistry.integrate(bismuthMat).preInit();


        TinkerFirmaCraft.logger.info("Loaded TIC Materials");
	}

    public void init()
	{

   }

	public void postInit()
	{
    }
}