package com.mrthomas20121.tfc_tinker;

import com.mrthomas20121.tfc_tinker.ToolPart.Parts;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.fml.common.SidedProxy;
import com.mrthomas20121.tfc_tinker.proxy.CommonProxy;

@Mod(modid = TFC_Tinker.MODID, name = TFC_Tinker.NAME, version = TFC_Tinker.VERSION,
        dependencies = "required-after:forge@[14.23.5.2847,);"
        + "required-after:mantle@[1.12-1.3.3.49,);"
        + "required-after:tconstruct@[1.12.2-2.12.0.157,);"
        + "required-after:tfc@[1.0.0.127,);")
public class TFC_Tinker
{
    @Mod.Instance
    public static TFC_Tinker instance;
    public static final String MODID = "tfc_tinker";
    public static final String NAME = "TFC_Tinker's";
    public static final String VERSION = "1.2.5";

    public static Logger logger;

    @SidedProxy(serverSide = "com.mrthomas20121.tfc_tinker.proxy.CommonProxy", clientSide = "com.mrthomas20121.tfc_tinker.proxy.ClientProxy")
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
        Parts.init(event);
        TConstructHelper.helper.init();
    }
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
    }
}
