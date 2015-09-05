package goldenapple.enchbaubles.handler;

import baubles.api.BaublesApi;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import goldenapple.enchbaubles.EnchBaublesMod;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerPickupXpEvent;

import java.util.HashMap;

public class PlayerEventHandler {
    public static HashMap<Integer, Integer> experienceMap = new HashMap<Integer, Integer>();
    public static HashMap<Integer, Integer> reachMap = new HashMap<Integer, Integer>();
    public static HashMap<Integer, Integer> saturationMap = new HashMap<Integer, Integer>();
    public static HashMap<Integer, Integer> regenMap = new HashMap<Integer, Integer>();
    public static HashMap<Integer, Integer> criticalMap = new HashMap<Integer, Integer>();

    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent event){
        if(event.entityLiving != null && event.entityLiving instanceof EntityPlayer){
            EntityPlayer player = (EntityPlayer) event.entityLiving;
            IInventory baubles = BaublesApi.getBaubles(player);
            int experienceLvl = 0;
            int reachLvl = 0;
            int saturationLvl = 0;
            int regenLvl = 0;
            int criticalLvl = 0;

            for(int i = 0; i < baubles.getSizeInventory(); i++){
                ItemStack stack = baubles.getStackInSlot(i);
                experienceLvl += EnchantmentHelper.getEnchantmentLevel(EnchBaublesMod.experience.effectId, stack);
                reachLvl += EnchantmentHelper.getEnchantmentLevel(EnchBaublesMod.reach.effectId, stack);
                saturationLvl += EnchantmentHelper.getEnchantmentLevel(EnchBaublesMod.saturation.effectId, stack);
                regenLvl += EnchantmentHelper.getEnchantmentLevel(EnchBaublesMod.regen.effectId, stack);
                criticalLvl += EnchantmentHelper.getEnchantmentLevel(EnchBaublesMod.critical.effectId, stack);
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
            criticalMap.put(player.getEntityId(), criticalLvl);
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

    @SubscribeEvent
    public void onAttack(LivingHurtEvent event){
        if(!(event.source.getEntity() instanceof EntityPlayer))
            return;
        EntityPlayer player = (EntityPlayer) event.source.getEntity();
        int criticalLvl = criticalMap.get(player.getEntityId());

        if(criticalLvl > 0 && player.fallDistance > 0.0F && !player.onGround && !player.isOnLadder() && !player.isInWater() && !player.isPotionActive(Potion.blindness) && player.ridingEntity == null) {
            event.ammount *= 1 + criticalLvl * 0.25;

            if(event.entityLiving.isRiding()) {
                if(event.entityLiving.ridingEntity != null) {
                    event.entityLiving.ridingEntity.riddenByEntity = null;
                    event.entityLiving.ridingEntity = null;
                }
            }
            event.entityLiving.onGround = false;
            event.entityLiving.isAirBorne = true;
            event.entityLiving.motionY = 0.3D * criticalLvl;

            if(player.getCurrentEquippedItem() != null)
                event.entityLiving.renderBrokenItemStack(player.getCurrentEquippedItem());
        }
    }
}
