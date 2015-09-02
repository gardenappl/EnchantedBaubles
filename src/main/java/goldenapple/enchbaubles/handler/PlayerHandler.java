package goldenapple.enchbaubles.handler;

import baubles.api.BaublesApi;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import goldenapple.enchbaubles.EnchBaublesMod;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;

import java.util.HashMap;

public class PlayerHandler {
    public static HashMap<EntityPlayer, Integer> experienceMap = new HashMap<EntityPlayer, Integer>();
    public static HashMap<EntityPlayer, Integer> reachMap = new HashMap<EntityPlayer, Integer>();
    public static HashMap<EntityPlayer, Integer> saturationMap = new HashMap<EntityPlayer, Integer>();
    public static HashMap<EntityPlayer, Integer> regenMap = new HashMap<EntityPlayer, Integer>();

    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent event){
        if(event.entityLiving != null && event.entityLiving instanceof EntityPlayer){
            EntityPlayer player = (EntityPlayer) event.entityLiving;
            IInventory baubles = BaublesApi.getBaubles(player);
            int experienceLvl = 0;

            for(int i = 0; i < baubles.getSizeInventory(); i++){
                ItemStack stack = baubles.getStackInSlot(i);
                experienceLvl += EnchantmentHelper.getEnchantmentLevel(EnchBaublesMod.experience.effectId, stack);
            }
            experienceMap.put(player, experienceLvl);
        }
    }

    @SubscribeEvent
    public void onPickupXP(PlayerPickupXpEvent event){
        if(event.entityPlayer != null && experienceMap.get(event.entityPlayer) != null){
            int lvl = experienceMap.get(event.entityPlayer);
            double bonus = 1 + (lvl * 0.2);
            event.orb.xpValue = (int)(event.orb.getXpValue() * bonus);
        }
    }
}
