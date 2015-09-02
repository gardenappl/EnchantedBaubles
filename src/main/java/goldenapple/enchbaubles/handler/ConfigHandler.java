package goldenapple.enchbaubles.handler;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import goldenapple.enchbaubles.reference.Reference;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHandler {
    public static Configuration config;

    public static int experienceID;
    public static int reachID;
    public static int saturationID;

    public static void init(File configFile) {
        if (config == null)
            config = new Configuration(configFile);
        loadConfig();
    }

    private static void loadConfig(){
        experienceID = config.get(Configuration.CATEGORY_GENERAL, "Experience Enchant ID", 133).setMinValue(0).setMaxValue(255).setLanguageKey("config.ench_baubles.experience_id").setRequiresMcRestart(true).getInt();
        reachID = config.get(Configuration.CATEGORY_GENERAL, "Sky Reach Enchant ID", 134).setMinValue(0).setMaxValue(255).setLanguageKey("config.ench_baubles.reach_id").setRequiresMcRestart(true).getInt();
        saturationID = config.get(Configuration.CATEGORY_GENERAL, "Saturation Enchant ID", 135).setMinValue(0).setMaxValue(255).setLanguageKey("config.ench_baubles.saturation_id").setRequiresMcRestart(true).getInt();

        if (config.hasChanged())
            config.save();
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.modID.equalsIgnoreCase(Reference.MOD_ID))
            loadConfig();
    }
}
