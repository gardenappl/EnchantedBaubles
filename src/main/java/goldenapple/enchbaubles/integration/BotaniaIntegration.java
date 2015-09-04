package goldenapple.enchbaubles.integration;

import cpw.mods.fml.relauncher.ReflectionHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import goldenapple.enchbaubles.client.handler.EnchBaublesPlayerController;
import goldenapple.enchbaubles.reference.LibObfuscation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import net.minecraft.world.WorldSettings;
import vazkii.botania.api.item.IExtendedPlayerController;

public class BotaniaIntegration {
    @SideOnly(Side.CLIENT)
    public static void extendReach(Entity entity, float reachBonus) {
        Minecraft mc = Minecraft.getMinecraft();
        if (!(mc.playerController instanceof IExtendedPlayerController)) {
            WorldSettings.GameType type = ReflectionHelper.getPrivateValue(PlayerControllerMP.class, mc.playerController, LibObfuscation.CURRENT_GAME_TYPE);
            NetHandlerPlayClient net = ReflectionHelper.getPrivateValue(PlayerControllerMP.class, mc.playerController, LibObfuscation.NET_CLIENT_HANDLER);
            EnchBaublesPlayerController controller = new EnchBaublesPlayerController(mc, net);
            controller.setGameType(type);
            mc.playerController = controller;
        }
        ((IExtendedPlayerController) mc.playerController).setReachDistanceExtension(((IExtendedPlayerController) mc.playerController).getReachDistanceExtension() + reachBonus);
    }
}
