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
        meta.modId = Reference.MOD_ID + "core";
        meta.name = Reference.MOD_NAME + " Core";
        meta.version = Reference.VERSION;
        meta.authorList.add("goldenapple");
        meta.description = "The core mod for Enchanted Baubles. It's needed for stuff and things to work.";
        meta.credits = "Ljfa for providing examples of writing coremods";
        meta.parent = Reference.MOD_ID;
    }

    @Override
    public boolean registerBus(EventBus bus, LoadController controller) {
        return true;
    }

    public static class RelaunchLogHelper{
        public static void info(String string, Object... formatData){
            FMLRelaunchLog.log(Reference.MOD_NAME + " Core", Level.INFO, string, formatData);
        }

        public static void error(String string, Object... formatData){
            FMLRelaunchLog.log(Reference.MOD_NAME + " Core", Level.ERROR, string, formatData);
        }
    }
}
