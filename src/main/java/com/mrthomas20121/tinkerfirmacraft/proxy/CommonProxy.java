package com.mrthomas20121.tinkerfirmacraft.proxy;

import com.mrthomas20121.tinkerfirmacraft.Config.Config;
import com.mrthomas20121.tinkerfirmacraft.ToolPart.Parts;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.File;

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
    }

    public void postInit(FMLPostInitializationEvent e) {
    }

    @SubscribeEvent
    public void registerHarvestLevels() {
        harvestLevelNames.put(7, "harvestlevel.redsteel");
        harvestLevelNames.put(6, "harvestlevel.bluesteel");
        // harvestLevelNames.put(1, "harvestlevel.rosegold");
        // harvestLevelNames.put(2, "harvestlevel.redsteel");
    }
}