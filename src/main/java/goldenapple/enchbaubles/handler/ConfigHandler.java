package goldenapple.enchbaubles.handler;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import goldenapple.enchbaubles.reference.Reference;
import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigHandler {
    public static Configuration config;

    public static boolean experienceEnabled;
    public static boolean reachEnabled;
    public static boolean saturationEnabled;
    public static boolean regenEnabled;
    public static boolean criticalEnabled;

    public static int experienceWeight;
    public static int reachWeight;
    public static int saturationWeight;
    public static int regenWeight;
    public static int criticalWeight;

    public static int experienceID;
    public static int reachID;
    public static int saturationID;
    public static int regenID;
    public static int criticalID;

    public static void init(File configFile) {
        if (config == null)
            config = new Configuration(configFile);
        loadConfig();
    }

    private static void loadConfig(){
        experienceEnabled = config.get(Configuration.CATEGORY_GENERAL, "Enable Experience Enchant", true).setLanguageKey("config.enchbaubles.experience").setRequiresMcRestart(true).getBoolean();
        reachEnabled = config.get(Configuration.CATEGORY_GENERAL, "Enable Sky Reach Enchant", true).setLanguageKey("config.enchbaubles.reach").setRequiresMcRestart(true).getBoolean();
        saturationEnabled = config.get(Configuration.CATEGORY_GENERAL, "Enable Saturation Enchant", true).setLanguageKey("config.enchbaubles.saturation").setRequiresMcRestart(true).getBoolean();
        regenEnabled = config.get(Configuration.CATEGORY_GENERAL, "Enable Regeneration Enchant", true).setLanguageKey("config.enchbaubles.regen").setRequiresMcRestart(true).getBoolean();
        criticalEnabled = config.get(Configuration.CATEGORY_GENERAL, "Enable Fatality Enchant", true).setLanguageKey("config.enchbaubles.critical").setRequiresMcRestart(true).getBoolean();

        experienceWeight = config.get(Configuration.CATEGORY_GENERAL, "Experience Enchant Weight", 10).setLanguageKey("config.enchbaubles.experience_weight").setRequiresMcRestart(true).getInt();
        reachWeight = config.get(Configuration.CATEGORY_GENERAL, "Sky Reach Enchant Weight", 3).setLanguageKey("config.enchbaubles.reach_weight").setRequiresMcRestart(true).getInt();
        saturationWeight = config.get(Configuration.CATEGORY_GENERAL, "Saturation Enchant Weight", 4).setLanguageKey("config.enchbaubles.saturation_weight").setRequiresMcRestart(true).getInt();
        regenWeight = config.get(Configuration.CATEGORY_GENERAL, "Regeneration Enchant Weight", 3).setLanguageKey("config.enchbaubles.regen_weight").setRequiresMcRestart(true).getInt();
        criticalWeight = config.get(Configuration.CATEGORY_GENERAL, "Fatality Enchant Weight", 5).setLanguageKey("config.enchbaubles.critical_weight").setRequiresMcRestart(true).getInt();

        experienceID = config.get(Configuration.CATEGORY_GENERAL, "Experience Enchant ID", 133).setMinValue(0).setMaxValue(255).setLanguageKey("config.enchbaubles.experience_id").setRequiresMcRestart(true).getInt();
        reachID = config.get(Configuration.CATEGORY_GENERAL, "Sky Reach Enchant ID", 134).setMinValue(0).setMaxValue(255).setLanguageKey("config.enchbaubles.reach_id").setRequiresMcRestart(true).getInt();
        saturationID = config.get(Configuration.CATEGORY_GENERAL, "Saturation Enchant ID", 135).setMinValue(0).setMaxValue(255).setLanguageKey("config.enchbaubles.saturation_id").setRequiresMcRestart(true).getInt();
        regenID = config.get(Configuration.CATEGORY_GENERAL, "Regeneration Enchant ID", 136).setMinValue(0).setMaxValue(255).setLanguageKey("config.enchbaubles.regen_id").setRequiresMcRestart(true).getInt();
        criticalID = config.get(Configuration.CATEGORY_GENERAL, "Fatality Enchant ID", 137).setMinValue(0).setMaxValue(255).setLanguageKey("config.enchbaubles.critical_id").setRequiresMcRestart(true).getInt();

        if (config.hasChanged())
            config.save();
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.modID.equalsIgnoreCase(Reference.MOD_ID))
            loadConfig();
    }
}
