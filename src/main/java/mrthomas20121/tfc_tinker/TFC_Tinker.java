package mrthomas20121.tfc_tinker;

import mrthomas20121.tfc_tinker.modules.ModuleTFC;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.fml.common.SidedProxy;
import mrthomas20121.tfc_tinker.proxy.CommonProxy;
import mrthomas20121.tfc_tinker.toolparts.Parts;

@Mod(modid = TFC_Tinker.MODID, name = TFC_Tinker.NAME, version = TFC_Tinker.VERSION,
        dependencies = "required-after:forge@[14.23.5.2847,);"
        + "required-after:mantle@[1.12-1.3.3.49,);"
        + "required-after:tconstruct@[1.12.2-2.12.0.157,);"
        + "required-after:biolib@[1.0.4,);"
        + "required-after:tfc@[1.4.0.149,);")
public class TFC_Tinker
{
    @Mod.Instance
    public static TFC_Tinker instance;
    public static final String MODID = "tfc_tinker";
    public static final String NAME = "TFC_Tinker's";
    public static final String VERSION = "1.4.1";

    public static Logger logger;

    @SidedProxy(serverSide = "mrthomas20121.tfc_tinker.proxy.CommonProxy", clientSide = "mrthomas20121.tfc_tinker.proxy.ClientProxy")
    public static CommonProxy proxy;

    public static ModuleTFC moduleTFC = new ModuleTFC();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(this);
        logger = event.getModLog();
        proxy.preInit(event);
   }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
        Parts.init(event);
        moduleTFC.init(event);
    }
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
    }
}
