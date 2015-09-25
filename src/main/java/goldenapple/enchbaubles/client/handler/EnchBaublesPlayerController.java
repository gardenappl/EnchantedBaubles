package goldenapple.enchbaubles.client.handler;

import cpw.mods.fml.common.Optional;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.network.NetHandlerPlayClient;
import vazkii.botania.api.item.IExtendedPlayerController;

@SideOnly(Side.CLIENT)
@Optional.Interface(iface = "vazkii.botania.api.item.IExtendedPlayerController", modid = "Botania")
public class EnchBaublesPlayerController extends PlayerControllerMP implements IExtendedPlayerController{
    private float reachBonus = 0F;

    public EnchBaublesPlayerController(Minecraft minecraft, NetHandlerPlayClient net) {
        super(minecraft, net);
    }

    @Override
    public float getBlockReachDistance() {
        return super.getBlockReachDistance() + reachBonus;
    }

  //@Override
    public void setReachDistanceExtension(float reachBonus) {
        this.reachBonus = reachBonus;
    }

  //@Override
    public float getReachDistanceExtension() {
        return reachBonus;
    }

    public void addReachDistanceExtension(float reachBonus){
        this.reachBonus += reachBonus;
    }
}
