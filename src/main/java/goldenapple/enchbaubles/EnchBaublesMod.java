package goldenapple.enchbaubles;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import goldenapple.enchbaubles.enchant.*;
import goldenapple.enchbaubles.handler.ConfigHandler;
import goldenapple.enchbaubles.handler.EnchantHandler;
import goldenapple.enchbaubles.handler.PlayerEventHandler;
import goldenapple.enchbaubles.reference.Reference;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, dependencies = Reference.DEPENDENCIES, guiFactory = Reference.GUI_FACTORY)
public class EnchBaublesMod {

    @Mod.Instance(Reference.MOD_ID)
    public static EnchBaublesMod instance;
    @SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.COMMON_PROXY)
    public static CommonProxy proxy;
    public static BaublesCreativeTab creativeTab = new BaublesCreativeTab();

    public static EnchantmentExperience experience;
    public static EnchantmentReach reach;
    public static EnchantmentSaturation saturation;
    public static EnchantmentRegen regen;
    public static EnchantmentCritical critical;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        ConfigHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigHandler());
        MinecraftForge.EVENT_BUS.register(new PlayerEventHandler());
        MinecraftForge.EVENT_BUS.register(new EnchantHandler());

        if(ConfigHandler.experienceEnabled)
            experience = new EnchantmentExperience(ConfigHandler.experienceID, ConfigHandler.experienceWeight);
        if(ConfigHandler.reachEnabled)
            reach = new EnchantmentReach(ConfigHandler.reachID, ConfigHandler.reachWeight);
        if(ConfigHandler.saturationEnabled)
            saturation = new EnchantmentSaturation(ConfigHandler.saturationID, ConfigHandler.saturationWeight);
        if(ConfigHandler.regenEnabled)
            regen = new EnchantmentRegen(ConfigHandler.regenID, ConfigHandler.reachWeight);
        if(ConfigHandler.criticalEnabled)
            critical = new EnchantmentCritical(ConfigHandler.criticalID, ConfigHandler.criticalWeight);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){

    }
}
