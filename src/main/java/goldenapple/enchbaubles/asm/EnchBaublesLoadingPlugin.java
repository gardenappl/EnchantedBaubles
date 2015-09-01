package goldenapple.enchbaubles.asm;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

import java.util.Map;


@IFMLLoadingPlugin.MCVersion("1.7.10")
@IFMLLoadingPlugin.TransformerExclusions("goldenapple.enchbaubles")
public class EnchBaublesLoadingPlugin implements IFMLLoadingPlugin {

    public EnchBaublesLoadingPlugin(){
        EnchBaublesCoreContainer.info("Loading...");
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
