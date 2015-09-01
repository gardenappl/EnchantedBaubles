package goldenapple.enchbaubles.asm;

import com.google.common.eventbus.EventBus;
import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.relauncher.FMLRelaunchLog;
import goldenapple.enchbaubles.reference.Reference;
import org.apache.logging.log4j.Level;

public class EnchBaublesCoreContainer extends DummyModContainer {
    public EnchBaublesCoreContainer(){
        super(new ModMetadata());
        ModMetadata meta = getMetadata();
        meta.modId = "ench_baubles_core";
        meta.name = "Enchanted Baubles Core";
        meta.version = Reference.VERSION;
        meta.authorList.add("goldenapple");
        meta.description = "The core mod for Enchanted Baubles. It's needed for stuff and things to work.";
        meta.parent = "ench_baubles";
    }

    @Override
    public boolean registerBus(EventBus bus, LoadController controller) {
        return true;
    }

    public static void info(String string){
        FMLRelaunchLog.log("Enchanted Baubles Core", Level.INFO, string);
    }

    public static void error(String string){
        FMLRelaunchLog.log("Enchanted Baubles Core", Level.ERROR, string);
    }
}
