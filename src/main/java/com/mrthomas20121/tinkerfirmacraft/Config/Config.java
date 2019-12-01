package com.mrthomas20121.tinkerfirmacraft.Config;

import com.mrthomas20121.tinkerfirmacraft.TinkerFirmaCraft;
import com.mrthomas20121.tinkerfirmacraft.proxy.CommonProxy;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Level;

public class Config {

    private static final String CATEGORY_METERIALS = "materials";
    private static final String CATEGORY_GENERAL = "general";

    public static boolean register_alloy = true;
    public static boolean black_steel = true;
    public static boolean red_steel = true;
    public static boolean blue_steel = true;
    public static boolean roseGold = true;
    public static boolean sterlingSilver = true;
    public static boolean wrought_iron = true;
    public static boolean blackBronze = true;
    public static boolean bismuthBronze = true;
    public static boolean bismuth = true;
    public static boolean platinum = true;
    public static boolean zinc = true;

    // Call this from CommonProxy.preInit(). It will create our config if it doesn't
    // exist yet and read the values if it does exist.
    public static void readConfig() {
        Configuration cfg = CommonProxy.config;
        try {
            cfg.load();
            initGeneralConfig(cfg);
            initMaterialConfig(cfg);
        } catch (Exception e1) {
            TinkerFirmaCraft.logger.log(Level.ERROR, "Problem loading config file!", e1);
        } finally {
            if (cfg.hasChanged()) {
                cfg.save();
            }
        }
    }

    private static void initMaterialConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_METERIALS, "Tinker Materials");
        // cfg.getBoolean() will get the value in the config if it is already specified there. If not it will create the value.
        black_steel = cfg.getBoolean("black_steel", CATEGORY_METERIALS, black_steel, "Set to false to disable black steel");
        red_steel = cfg.getBoolean("red_steel", CATEGORY_METERIALS, red_steel, "Set to false to disable red steel");
        blue_steel = cfg.getBoolean("red_steel", CATEGORY_METERIALS, blue_steel, "Set to false to disable blue steel");
        roseGold = cfg.getBoolean("rose_gold", CATEGORY_METERIALS, roseGold, "Set to false to disable rose gold");
        sterlingSilver = cfg.getBoolean("sterling_silver", CATEGORY_METERIALS, sterlingSilver, "Set to false to disable sterling silver");
        wrought_iron = cfg.getBoolean("wrought_iron", CATEGORY_METERIALS, wrought_iron, "Set to false to disable wrought iron");
        blackBronze = cfg.getBoolean("black_bronze", CATEGORY_METERIALS, blackBronze, "Set to false to disable black bronze");
        bismuthBronze = cfg.getBoolean("bismuth_bronze", CATEGORY_METERIALS, bismuthBronze, "Set to false to disable bismuth bronze");
        bismuth = cfg.getBoolean("bismuth", CATEGORY_METERIALS, bismuth, "Set to false to disable bismuth");
        platinum = cfg.getBoolean("platinum", CATEGORY_METERIALS, platinum, "Set to false to disable platinum");
        zinc = cfg.getBoolean("zinc", CATEGORY_METERIALS, zinc, "Set to false to disable zinc");
    }
    private static void initGeneralConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_GENERAL, "General Stuff");
        // cfg.getBoolean() will get the value in the config if it is already specified there. If not it will create the value.
        register_alloy = cfg.getBoolean("register_alloy", CATEGORY_GENERAL, register_alloy, "Set to false to disable alloy from generating in the smeltery");
    }
}