package com.mrthomas20121.tinkerfirmacraft.proxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static slimeknights.tconstruct.library.utils.HarvestLevels.harvestLevelNames;

@Mod.EventBusSubscriber
public class CommonProxy {
    public void preInit(FMLPreInitializationEvent e) {
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