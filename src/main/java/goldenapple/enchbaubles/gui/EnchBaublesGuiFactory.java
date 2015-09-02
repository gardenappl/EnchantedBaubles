package goldenapple.enchbaubles.gui;

import cpw.mods.fml.client.IModGuiFactory;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;
import goldenapple.enchbaubles.handler.ConfigHandler;
import goldenapple.enchbaubles.reference.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;

import java.util.List;
import java.util.Set;

public class EnchBaublesGuiFactory implements IModGuiFactory{
    @Override
    public void initialize(Minecraft minecraftInstance) {

    }

    @Override
    public Class<? extends GuiScreen> mainConfigGuiClass() {
        return ConveyorGuiConfig.class;
    }

    @Override
    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
        return null;
    }

    @Override
    public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) {
        return null;
    }

    public static class ConveyorGuiConfig extends GuiConfig {
        public ConveyorGuiConfig(GuiScreen guiScreen){
            super(guiScreen, getConfigElements(), Reference.MOD_ID, false, false, GuiConfig.getAbridgedConfigPath(ConfigHandler.config.toString()));
        }

        @SuppressWarnings("unchecked")
        private static List<IConfigElement> getConfigElements(){ //just to avoid having 200+ characters on one line in the constructor
            return new ConfigElement(ConfigHandler.config.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements();
        }
    }
}
