package mrthomas20121.tfc_tinker;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = TFC_Tinker.MODID)
public final class ConfigMain {

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

        @Config.LangKey("config.materials")
        @Config.Comment("List of Tinkers' Construct material added by TFC Tinkers to remove.")
        public String[] material_blacklists = {};
    }

    public static class ConfigGeneral {
        @Config.LangKey("config.general.alloys")
        @Config.Comment("Set to false to disable Smeltery alloys added by TFC Tinker.")
        public boolean register_alloys = true;

        @Config.LangKey("config.general.tfctech")
        @Config.Comment("Set to false to disable TFCTech Integration.")
        public boolean tfctech = true;
    }
}