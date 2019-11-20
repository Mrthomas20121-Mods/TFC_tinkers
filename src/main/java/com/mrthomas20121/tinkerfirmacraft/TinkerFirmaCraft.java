package com.mrthomas20121.tinkerfirmacraft;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.fml.common.SidedProxy;
import com.mrthomas20121.tinkerfirmacraft.proxy.CommonProxy;

@Mod(modid = TinkerFirmaCraft.MODID, name = TinkerFirmaCraft.NAME, version = TinkerFirmaCraft.VERSION)
public class TinkerFirmaCraft
{
    public static final String MODID = "tinkerfirmacraft";
    public static final String NAME = "TinkerFirmaCraft";
    public static final String VERSION = "0.0.1";

    private static Logger logger;
    @SidedProxy(clientSide = "com.mrthomas20121.tinkerfirmacraft.proxy.ClientProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        proxy.preInit(event);
   }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
    }
}
