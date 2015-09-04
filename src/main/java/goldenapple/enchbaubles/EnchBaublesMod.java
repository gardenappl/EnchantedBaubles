package goldenapple.enchbaubles;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import goldenapple.enchbaubles.enchant.EnchantmentExperience;
import goldenapple.enchbaubles.enchant.EnchantmentReach;
import goldenapple.enchbaubles.enchant.EnchantmentRegen;
import goldenapple.enchbaubles.enchant.EnchantmentSaturation;
import goldenapple.enchbaubles.handler.ConfigHandler;
import goldenapple.enchbaubles.handler.PlayerEventHandler;
import goldenapple.enchbaubles.reference.Reference;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, dependencies = Reference.DEPENDENCIES, guiFactory = Reference.GUI_FACTORY)
public class EnchBaublesMod {
    public static EnchantmentExperience experience;
    public static EnchantmentReach reach;
    public static EnchantmentSaturation saturation;
    public static EnchantmentRegen regen;

    public static BaublesCreativeTab creativeTab = new BaublesCreativeTab();

    @SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.COMMON_PROXY)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        ConfigHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigHandler());
        MinecraftForge.EVENT_BUS.register(new PlayerEventHandler());

        experience = new EnchantmentExperience(ConfigHandler.experienceID);
        reach = new EnchantmentReach(ConfigHandler.reachID);
        saturation = new EnchantmentSaturation(ConfigHandler.saturationID);
        regen = new EnchantmentRegen(ConfigHandler.regenID);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){

    }
}
