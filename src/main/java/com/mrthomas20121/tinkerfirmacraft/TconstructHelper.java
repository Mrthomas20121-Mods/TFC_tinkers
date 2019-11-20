package com.mrthomas20121.tinkerfirmacraft;

import com.mrthomas20121.tinkerfirmacraft.proxy.ClientProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
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

@Mod.EventBusSubscriber(modid = TinkerFirmaCraft.MODID)
public class TconstructHelper {

    public TconstructHelper() {
        MinecraftForge.EVENT_BUS.register(this);
    }
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
        // sterlingSilver registry
        TinkerRegistry.addMaterialStats(sterlingSilver,
				new HeadMaterialStats(250, 6.00f, 5.00f, HarvestLevels.IRON),
				new HandleMaterialStats(1.2f, 150),
				new ExtraMaterialStats(80));
        TinkerRegistry.addMaterialStats(sterlingSilver, new BowMaterialStats(1.2f, 0.8f, 2.0f), new ArrowShaftMaterialStats(1.4f, 0));
        sterlingSilver.setCastable(true);
        sterlingSilver.addItem("nuggetSterlingSilver", 1, Material.VALUE_Nugget);
        sterlingSilver.addItem("blockSterlingSilver", 1, Material.VALUE_Block);
        sterlingSilver.addItem("ingotSterlingSilver", 1, Material.VALUE_Ingot);
        sterlingSilver.addTrait(TinkerTraits.dense, MaterialTypes.HEAD);
        sterlingSilver.addTrait(TinkerTraits.holy);
        MaterialIntegration sterling = new MaterialIntegration(sterlingSilver).setRepresentativeItem("ingotSterlingSIlver");
        TinkerRegistry.integrate(sterling).preInit();
        // roseGold registry
        TinkerRegistry.addMaterialStats(roseGold,
        new HeadMaterialStats(200, 5.00f, 4.00f, HarvestLevels.IRON),
        new HandleMaterialStats(1.2f, 200),
        new ExtraMaterialStats(10));
        TinkerRegistry.addMaterialStats(sterlingSilver, new BowMaterialStats(1.2f, 1.8f, 1.0f), new ArrowShaftMaterialStats(1.5f, 0));
        roseGold.setCastable(true);
        roseGold.addItem("nuggetRoseGold", 1, Material.VALUE_Nugget);
        roseGold.addItem("ingotRoseGold", 1, Material.VALUE_Ingot);
        roseGold.addTrait(TinkerTraits.dense, MaterialTypes.HEAD);
        roseGold.addTrait(TinkerTraits.heavy);
        MaterialIntegration rose = new MaterialIntegration(sterlingSilver).setRepresentativeItem("ingotRoseGold");
        TinkerRegistry.integrate(rose).preInit();
        sterlingSilver.setRenderInfo(new MaterialRenderInfo.Metal(0xCABEB6, 0.1f, 0.2f, 0f));
        roseGold.setRenderInfo(new MaterialRenderInfo.Metal(0xF7CCBC, 0.1f, 0.2f, 0f));
    }
    public void init()
	{

    }
	public void postInit()
	{

	}
}