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

    @SubscribeEvent
    public static void onConfigChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
    {
        if (event.getModID().equals(TFC_Tinker.MODID))
        {
            ConfigManager.sync(TFC_Tinker.MODID, Config.Type.INSTANCE);
        }
    }

    @Config(name = "TFCTinkers", modid = TFC_Tinker.MODID)
    public static class ConfigTFCTinker {
        public final static ConfigMaterial material = new ConfigMaterial();
        public final static ConfigGeneral general = new ConfigGeneral();
    }

    public static class ConfigMaterial {

        @Config.LangKey("config.general.tfctech")
        @Config.Comment("List of Tinkers' Construct material added by TFC Tinkers to remove.")
        public String[] material_blacklists = {};
    }

    public static class ConfigGeneral {
        @Config.LangKey("config.general.alloys")
        @Config.Comment("Set to false to disable Smeltery alloys added by TFC Tinker.")
        public boolean register_alloys = true;

        @Config.LangKey("config.general.metal_list")
        @Config.Comment("List of Metals ignored by TFC Tinker. do not remove the default one or it will crash.")
        public String[] metalBlacklist = { "tfc:unknown", "tfc:weak_steel", "tfc:weak_blue_steel", "tfc:weak_red_steel", "tfc:high_carbon_steel", "tfc:high_carbon_blue_steel", "tfc:high_carbon_red_steel", "tfc:high_carbon_black_steel"};

        @Config.LangKey("config.general.tfctech")
        @Config.Comment("Set to false to disable TFCTech Integration.")
        public boolean tfctech = true;
    }
}