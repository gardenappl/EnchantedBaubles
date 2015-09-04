package goldenapple.enchbaubles;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;

public class CommonProxy {
    public void extendRange(Entity entity, float reachBonus){
        if (entity instanceof EntityPlayerMP)
            ((EntityPlayerMP) entity).theItemInWorldManager.setBlockReachDistance(((EntityPlayerMP) entity).theItemInWorldManager.getBlockReachDistance() + reachBonus);
    }
}
