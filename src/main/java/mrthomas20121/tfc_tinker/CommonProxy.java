package mrthomas20121.tfc_tinker;

import mrthomas20121.tfc_tinker.client.TFCTicGuiHandler;
import mrthomas20121.tfc_tinker.objects.blocks.TFCTinkerBlocks;
import mrthomas20121.tfc_tinker.objects.items.TFCTinkerItems;
import mrthomas20121.tfc_tinker.compat.tinkers_construct.Parts;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import slimeknights.tconstruct.library.tools.IToolPart;
import slimeknights.tconstruct.library.tools.ToolCore;

import static mrthomas20121.tfc_tinker.TFC_Tinker.instance;

@Mod.EventBusSubscriber
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent e) {
    }

    public void init(FMLInitializationEvent e) {
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new TFCTicGuiHandler());
    }

    public void postInit(FMLPostInitializationEvent e) {
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        Parts.registerItems(event);
        TFCTinkerItems.init(event);
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        TFCTinkerBlocks.init(event);
    }

    public <T extends Item & IToolPart> void registerToolPartModel(T part) { }
    public void registerToolModel(ToolCore tc) { }
}