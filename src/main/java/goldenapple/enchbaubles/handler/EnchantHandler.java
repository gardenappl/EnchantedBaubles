package goldenapple.enchbaubles.handler;

import baubles.api.IBauble;
import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import goldenapple.enchapi.api.GetItemEnchantabilityEvent;
import goldenapple.enchapi.api.ItemEnchantableEvent;

public class EnchantHandler {
    @SubscribeEvent
    public void isItemEnchantable(ItemEnchantableEvent event){
        if(event.stack.getItem() instanceof IBauble && !event.stack.isItemEnchanted())
            event.setResult(Event.Result.ALLOW);
    }

    @SubscribeEvent
    public void getItemEnchantability(GetItemEnchantabilityEvent event){
        if(event.stack.getItem() instanceof IBauble && event.stack.getItem().getItemEnchantability() <= 0) {
            switch (event.stack.getRarity()) {
                case common:
                    event.enchantability = 10; break;
                case uncommon:
                    event.enchantability = 15; break;
                case rare:
                    event.enchantability = 20; break;
                case epic:
                    event.enchantability = 25; break;
                default:
                    if(event.stack.getRarity().rarityName.equals("Relic")) //Botania relics
                        event.enchantability = 30;
                    else if(event.stack.getRarity().rarityName.equals("Cosmic")) //Avaritia stuff (I won't be surprised if they add a Bauble in the future)
                        event.enchantability = 200;
                    else
                        event.enchantability = 10;
            }
        }
    }
}
