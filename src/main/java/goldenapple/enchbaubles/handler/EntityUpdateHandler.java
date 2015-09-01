package goldenapple.enchbaubles.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingDropsEvent;

public class EntityUpdateHandler {
    @SubscribeEvent
    public void onLivingUpdate(LivingDropsEvent event){
        if(event.entityLiving instanceof EntityPlayer){

        }
    }
}
