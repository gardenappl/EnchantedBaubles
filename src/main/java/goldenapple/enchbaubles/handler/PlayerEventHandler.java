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

public class PlayerEventHandler {
    public static HashMap<Integer, Integer> experienceMap = new HashMap<Integer, Integer>();
    public static HashMap<Integer, Integer> reachMap = new HashMap<Integer, Integer>();
    public static HashMap<Integer, Integer> saturationMap = new HashMap<Integer, Integer>();
    public static HashMap<Integer, Integer> regenMap = new HashMap<Integer, Integer>();

    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent event){
        if(event.entityLiving != null && event.entityLiving instanceof EntityPlayer){
            EntityPlayer player = (EntityPlayer) event.entityLiving;
            IInventory baubles = BaublesApi.getBaubles(player);
            int experienceLvl = 0;
            int reachLvl = 0;
            int saturationLvl = 0;
            int regenLvl = 0;

            for(int i = 0; i < baubles.getSizeInventory(); i++){
                ItemStack stack = baubles.getStackInSlot(i);
                experienceLvl += EnchantmentHelper.getEnchantmentLevel(EnchBaublesMod.experience.effectId, stack);
                reachLvl += EnchantmentHelper.getEnchantmentLevel(EnchBaublesMod.reach.effectId, stack);
                saturationLvl += EnchantmentHelper.getEnchantmentLevel(EnchBaublesMod.saturation.effectId, stack);
                regenLvl += EnchantmentHelper.getEnchantmentLevel(EnchBaublesMod.regen.effectId, stack);
            }

            if(reachMap.containsKey(player.getEntityId())){
                if(reachMap.get(player.getEntityId()) != reachLvl) //if the reach has changed
                    EnchBaublesMod.proxy.extendRange(player, reachLvl - reachMap.get(player.getEntityId()));
            }else{
                if(reachLvl != 0)
                    EnchBaublesMod.proxy.extendRange(player, reachLvl);
            }

            experienceMap.put(player.getEntityId(), experienceLvl);
            reachMap.put(player.getEntityId(), reachLvl);
            saturationMap.put(player.getEntityId(), saturationLvl);
            regenMap.put(player.getEntityId(), regenLvl);
        }
    }

    @SubscribeEvent
    public void onPickupXP(PlayerPickupXpEvent event){
        if(event.entityPlayer != null && experienceMap.get(event.entityPlayer.getEntityId()) != null){
            int lvl = experienceMap.get(event.entityPlayer.getEntityId());
            double bonus = 1 + (lvl * 0.1);
            event.orb.xpValue = (int)(event.orb.getXpValue() * bonus);
        }
    }
}
