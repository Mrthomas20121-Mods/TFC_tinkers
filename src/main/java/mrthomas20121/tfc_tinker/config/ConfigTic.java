package mrthomas20121.tfc_tinker.config;

import mrthomas20121.tfc_tinker.TFC_Tinker;
import mrthomas20121.tfc_tinker.proxy.CommonProxy;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.logging.log4j.Level;

@Mod.EventBusSubscriber(modid = TFC_Tinker.MODID)
public final class ConfigTic {

    private static final String CATEGORY_METERIALS = "materials";

    public static boolean register_alloy = true;
    public static boolean tfctech = true;
    public static String[] materialBlacklists = {};
    public static String[] castFluids = { "sterling_silver", "rose_gold", "brass" };
    public static String[] metalBlacklists = { "tfc:unknown", "tfc:weak_steel", "tfc:weak_blue_steel", "tfc:weak_red_steel", "tfc:high_carbon_steel", "tfc:high_carbon_blue_steel", "tfc:high_carbon_red_steel", "tfc:high_carbon_black_steel"};

    @SubscribeEvent
    public static void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.getModID().equals(TFC_Tinker.MODID))
        {
            ConfigManager.sync(TFC_Tinker.MODID, Config.Type.INSTANCE);
        }
    }

    // Call this from CommonProxy.preInit(). It will create our config if it doesn't
    // exist yet and read the values if it does exist.
    public static void readConfig() {
        Configuration cfg = CommonProxy.config;
        try {
            cfg.load();
            initGeneralConfig(cfg);
            initMaterialConfig(cfg);
        } catch (Exception e1) {
            TFC_Tinker.logger.log(Level.ERROR, "Problem loading config file!", e1);
        } finally {
            if (cfg.hasChanged()) {
                cfg.save();
            }
        }
    }

    private static void initMaterialConfig(Configuration cfg) {
        // cfg.getBoolean() will get the value in the config if it is already specified there. If not it will create the value.
        materialBlacklists = cfg.getStringList("material_blacklist", CATEGORY_METERIALS, materialBlacklists, "Materials blacklist. blacklisted materials won't be registered.");
    }
    private static void initGeneralConfig(Configuration cfg) {
        register_alloy = cfg.getBoolean("register_alloy", Configuration.CATEGORY_GENERAL, register_alloy, "Set to false to disable alloy from generating in the tinker smeltery");
        castFluids = cfg.getStringList("cast_fluids", Configuration.CATEGORY_GENERAL, castFluids, "List of Fluids used to make TFC Tinker's Casts.");
        metalBlacklists = cfg.getStringList("metal_blacklists", Configuration.CATEGORY_GENERAL, metalBlacklists, "Metal blacklist for casting and melting recipes, do not remove the default ones or it will crash.");
        tfctech = cfg.getBoolean("tfc_tech", Configuration.CATEGORY_GENERAL, tfctech, "Set to false to disable tfctech integration");
    }
}