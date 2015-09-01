package goldenapple.enchbaubles.asm;

import baubles.api.IBauble;
import net.minecraft.item.ItemStack;

public class ItemHooks {
    public static boolean isEnchantable(ItemStack stack){
        return (stack.getItem().isItemTool(stack) || stack.getItem() instanceof IBauble) && !stack.isItemEnchanted();
    }

    public static int getEnchantability(ItemStack stack){
        switch(stack.getRarity()){
            case uncommon: return 20;
            case rare:
            case epic: return 30;
            default: return 10;
        }
    }
}
