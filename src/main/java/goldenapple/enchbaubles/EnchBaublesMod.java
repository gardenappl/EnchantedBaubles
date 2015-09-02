package goldenapple.enchbaubles;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import goldenapple.enchbaubles.enchant.EnchantmentExperience;
import goldenapple.enchbaubles.enchant.EnchantmentReach;
import goldenapple.enchbaubles.enchant.EnchantmentSaturation;
import goldenapple.enchbaubles.handler.ConfigHandler;
import goldenapple.enchbaubles.handler.PlayerHandler;
import goldenapple.enchbaubles.reference.Reference;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, dependencies = Reference.DEPENDENCIES, guiFactory = Reference.GUI_FACTORY)
public class EnchBaublesMod {
    public static EnchantmentExperience experience;
    public static EnchantmentReach reach;
    public static EnchantmentSaturation saturation;

    public static BaublesCreativeTab creativeTab = new BaublesCreativeTab();

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        ConfigHandler.init(event.getSuggestedConfigurationFile());
        FMLCommonHandler.instance().bus().register(new ConfigHandler());
        MinecraftForge.EVENT_BUS.register(new PlayerHandler());

        experience = new EnchantmentExperience(ConfigHandler.experienceID);
        reach = new EnchantmentReach(ConfigHandler.reachID);
        saturation = new EnchantmentSaturation(ConfigHandler.saturationID);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){

    }
}
