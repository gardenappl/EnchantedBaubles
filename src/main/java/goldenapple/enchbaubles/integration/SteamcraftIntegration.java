package goldenapple.enchbaubles.integration;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import goldenapple.enchbaubles.helper.LogHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;

import java.lang.reflect.Field;

public class SteamcraftIntegration {
    @SideOnly(Side.CLIENT)
    public static void extendReach(Entity entity, float reachBonus){
        Minecraft mc = Minecraft.getMinecraft();

        //Using reflection for this. Unfortunately, FSP doesn't provide a public API to do this in a nicer way
        if(mc.playerController.getClass().getName().contains("SteamcraftPlayerController")){
            try {
                Class playerControllerClass = mc.playerController.getClass();
                Field distanceField = playerControllerClass.getDeclaredField("distance");
                distanceField.setAccessible(true);
                distanceField.set(mc.playerController, reachBonus + (Float)distanceField.get(mc.playerController));
            } catch (Exception e) {
                LogHelper.error("Couldn't reflect distance in %s! REPORT TO THE MOD AUTHOR IMMEDIATELY (or when you get some free time... Sure, I can wait)");
                e.printStackTrace();
            }
        }
    }
}
