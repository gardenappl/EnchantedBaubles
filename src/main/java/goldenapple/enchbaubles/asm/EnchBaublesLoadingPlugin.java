package goldenapple.enchbaubles.asm;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

import java.util.Map;

import static goldenapple.enchbaubles.asm.EnchBaublesCoreContainer.info;

@IFMLLoadingPlugin.MCVersion("1.7.10")
@IFMLLoadingPlugin.TransformerExclusions("goldenapple.enchbaubles") //modifying my own mod while I'm modifying my own mod isn't cool.
public class EnchBaublesLoadingPlugin implements IFMLLoadingPlugin {

    public EnchBaublesLoadingPlugin(){
        info("Loading...");
    }

    @Override
    public String[] getASMTransformerClass() {
        return new String[]{ItemTransformer.class.getName()};
    }

    @Override
    public String getModContainerClass() {
        return EnchBaublesCoreContainer.class.getName();
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {

    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
