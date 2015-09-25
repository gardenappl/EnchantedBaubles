package goldenapple.enchapi.api;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

public class ItemHooks {
    public static boolean isEnchantable(ItemStack stack){
        ItemEnchantableEvent event = new ItemEnchantableEvent(stack);
        MinecraftForge.EVENT_BUS.post(event);
        switch (event.getResult()){
            case ALLOW:
                return true;
            case DENY:
                return false;
            default:
                return stack.getItem().isItemTool(stack) && !stack.isItemEnchanted();
        }
    }

    public static int getEnchantability(ItemStack stack){
        GetItemEnchantabilityEvent event = new GetItemEnchantabilityEvent(stack);
        MinecraftForge.EVENT_BUS.post(event);
        return event.enchantability;
    }
}
