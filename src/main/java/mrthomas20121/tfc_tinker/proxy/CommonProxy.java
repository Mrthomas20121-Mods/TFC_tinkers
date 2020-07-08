package mrthomas20121.tfc_tinker.proxy;

import mrthomas20121.tfc_tinker.Config.ConfigTic;
import mrthomas20121.tfc_tinker.registry.ItemsRegistry;
import mrthomas20121.tfc_tinker.toolparts.Parts;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.tools.IToolPart;
import slimeknights.tconstruct.library.tools.ToolCore;

import java.io.File;

import static mrthomas20121.tfc_tinker.TFC_Tinker.logger;

@Mod.EventBusSubscriber
public class CommonProxy {

    public static Configuration config;
    public void preInit(FMLPreInitializationEvent e) {
        File directory = e.getModConfigurationDirectory();
        config = new Configuration(new File(directory.getPath(), "tfc_tinkers.cfg"));
        ConfigTic.readConfig();
    }

    public void init(FMLInitializationEvent e) {
        logger.info("Loading Prospector pick");
    }

    public void postInit(FMLPostInitializationEvent e) {
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        Parts.registerItems(event);
        ItemsRegistry.registerItems(event);
    }

    public <T extends Item & IToolPart> void registerToolPartModel(T part) { }
    public void registerToolModel(ToolCore tc) { }
}