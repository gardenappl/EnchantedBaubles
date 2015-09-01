package goldenapple.enchbaubles;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import goldenapple.enchbaubles.enchant.EnchantmentExperience;
import goldenapple.enchbaubles.handler.EntityUpdateHandler;
import goldenapple.enchbaubles.reference.Reference;
import net.minecraftforge.common.MinecraftForge;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, dependencies = Reference.DEPENDENCIES)
public class EnchBaublesMod {
    EnchantmentExperience experience = new EnchantmentExperience(133);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){
        MinecraftForge.EVENT_BUS.register(new EntityUpdateHandler());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event){

    }
}
