package mrthomas20121.tfc_tinker;

import mrthomas20121.tfc_tinker.objects.fluids.FluidsTFCTinker;
import mrthomas20121.tfc_tinker.registry.RegistryHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.fml.common.SidedProxy;
import mrthomas20121.tfc_tinker.compat.tinkers_construct.Parts;

@Mod(modid = TFC_Tinker.MODID, name = TFC_Tinker.NAME, version = TFC_Tinker.VERSION,
        dependencies = "required-after:forge@[14.23.5.2847,);"
        + "required-after:tconstruct@[1.12.2-2.12.0.157,);"
        + "required-after:biolib@[1.1.1,);"
        + "required-after:tfc@[1.7.10.168,);"
        + "required-after:rocksalt@[1.0.1,);")
public class TFC_Tinker
{
    @Mod.Instance
    public static TFC_Tinker instance;
    public static final String MODID = "tfc_tinker";
    public static final String NAME = "TFC Tinker";
    public static final String VERSION = "1.5.4";

    public static Logger logger;

    @SidedProxy(serverSide = "mrthomas20121.tfc_tinker.CommonProxy", clientSide = "mrthomas20121.tfc_tinker.client.ClientProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(this);
        logger = event.getModLog();
        proxy.preInit(event);
        FluidsTFCTinker.registerFluids();
   }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
        Parts.init(event);
        RegistryHandler.init(event);
    }
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
    }
}
