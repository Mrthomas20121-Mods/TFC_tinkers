package com.mrthomas20121.tinkerfirmacraft;

import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.objects.fluids.FluidsTFC;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.fml.common.SidedProxy;
import com.mrthomas20121.tinkerfirmacraft.proxy.CommonProxy;
import slimeknights.tconstruct.library.MaterialIntegration;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.*;
import slimeknights.tconstruct.library.utils.HarvestLevels;
import slimeknights.tconstruct.tools.TinkerTraits;

@Mod(modid = TinkerFirmaCraft.MODID, name = TinkerFirmaCraft.NAME, version = TinkerFirmaCraft.VERSION)
public class TinkerFirmaCraft
{
    @Mod.Instance
    public static TinkerFirmaCraft instance;
    public static final String MODID = "tinkerfirmacraft";
    public static final String NAME = "TinkerFirmaCraft";
    public static final String VERSION = "1.0.0";

    public static Logger logger;

    @SidedProxy(serverSide = "com.mrthomas20121.tinkerfirmacraft.proxy.CommonProxy", clientSide = "com.mrthomas20121.tinkerfirmacraft.proxy.ClientProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(this);
        logger = event.getModLog();
        proxy.preInit(event);
        TConstructHelper.helper.preInit();
   }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
        logger.info("Loading TinkerFirmaCraft!");
        TConstructHelper.helper.init();
    }
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
        TConstructHelper.helper.postInit();
    }
}
