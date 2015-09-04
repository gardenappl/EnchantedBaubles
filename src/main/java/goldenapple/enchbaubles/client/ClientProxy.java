package goldenapple.enchbaubles.client;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.ReflectionHelper;
import goldenapple.enchbaubles.CommonProxy;
import goldenapple.enchbaubles.client.handler.EnchBaublesPlayerController;
import goldenapple.enchbaubles.integration.BotaniaIntegration;
import goldenapple.enchbaubles.integration.SteamcraftIntegration;
import goldenapple.enchbaubles.reference.LibObfuscation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import net.minecraft.world.WorldSettings;

public class ClientProxy extends CommonProxy{
    @Override
    public void extendRange(Entity entity, float reachBonus) {
        super.extendRange(entity, reachBonus);
        Minecraft mc = Minecraft.getMinecraft();
        if(entity.getEntityId() == mc.thePlayer.getEntityId()){
            if(Loader.isModLoaded("Botania")){
                BotaniaIntegration.extendReach(entity, reachBonus);
            }else if(Loader.isModLoaded("Steamcraft")){
                SteamcraftIntegration.extendReach(entity, reachBonus);
            }else {
                if (!(mc.playerController instanceof EnchBaublesPlayerController)) {
                    WorldSettings.GameType type = ReflectionHelper.getPrivateValue(PlayerControllerMP.class, mc.playerController, LibObfuscation.CURRENT_GAME_TYPE);
                    NetHandlerPlayClient net = ReflectionHelper.getPrivateValue(PlayerControllerMP.class, mc.playerController, LibObfuscation.NET_CLIENT_HANDLER);
                    EnchBaublesPlayerController controller = new EnchBaublesPlayerController(mc, net);
                    controller.setGameType(type);
                    mc.playerController = controller;
                }
                ((EnchBaublesPlayerController) mc.playerController).addReachDistanceExtension(reachBonus);
            }
        }
    }
}
