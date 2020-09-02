package mrthomas20121.tfc_tinker.proxy;

import mrthomas20121.tfc_tinker.registry.TFCTicGuiHandler;
import mrthomas20121.tfc_tinker.config.ConfigTic;
import mrthomas20121.tfc_tinker.registry.ItemsRegistry;
import mrthomas20121.tfc_tinker.toolparts.Parts;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.registries.IForgeRegistryModifiable;
import slimeknights.tconstruct.library.tools.IToolPart;
import slimeknights.tconstruct.library.tools.ToolCore;

import java.io.File;

import static mrthomas20121.tfc_tinker.TFC_Tinker.instance;

@Mod.EventBusSubscriber
public class CommonProxy {

    public static Configuration config;
    public void preInit(FMLPreInitializationEvent e) {
        File directory = e.getModConfigurationDirectory();
        config = new Configuration(new File(directory.getPath(), "tfc_tinkers.cfg"));
        ConfigTic.readConfig();
    }

    public void init(FMLInitializationEvent e) {
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new TFCTicGuiHandler());
    }

    public void postInit(FMLPostInitializationEvent e) {
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onRegisterRecipesEvent(RegistryEvent.Register<IRecipe> event) {
        IForgeRegistryModifiable r = (IForgeRegistryModifiable)event.getRegistry();

        r.remove(new ResourceLocation("tconstruct:smeltery/grout_simple"));
        r.remove(new ResourceLocation("tconstruct:smeltery/grout"));
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