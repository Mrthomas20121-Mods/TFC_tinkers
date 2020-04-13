package com.mrthomas20121.tinkerfirmacraft.proxy;

import com.mrthomas20121.tinkerfirmacraft.Config.Config;
import com.mrthomas20121.tinkerfirmacraft.ToolPart.Parts;
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

import static com.mrthomas20121.tinkerfirmacraft.TinkerFirmaCraft.logger;
import static slimeknights.tconstruct.library.utils.HarvestLevels.harvestLevelNames;

@Mod.EventBusSubscriber
public class CommonProxy {

    public static Configuration config;
    public void preInit(FMLPreInitializationEvent e) {
        File directory = e.getModConfigurationDirectory();
        config = new Configuration(new File(directory.getPath(), "tinkerfirmacraft.cfg"));
        Config.readConfig();
    }

    public void init(FMLInitializationEvent e) {
        logger.info("Loading Prospector pick");
    }

    public void postInit(FMLPostInitializationEvent e) {
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        Parts.registerItems(event);
    }
    @SubscribeEvent
    public void registerHarvestLevels() {
        harvestLevelNames.put(7, "harvestlevel.redsteel");
        harvestLevelNames.put(6, "harvestlevel.bluesteel");
        // harvestLevelNames.put(1, "harvestlevel.rosegold");
        // harvestLevelNames.put(2, "harvestlevel.redsteel");
    }
    public <T extends Item & IToolPart> void registerToolPartModel(T part) { }
    public void registerToolModel(ToolCore tc) { }
}