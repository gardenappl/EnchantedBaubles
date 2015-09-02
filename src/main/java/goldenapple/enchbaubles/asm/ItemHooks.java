package goldenapple.enchbaubles.asm;

import baubles.api.IBauble;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class ItemHooks {
    public static boolean isEnchantable(ItemStack stack){
        return (stack.getItem().isItemTool(stack) || stack.getItem() instanceof IBauble) && !stack.isItemEnchanted();
    }

    public static int getEnchantability(ItemStack stack){
        if(stack.getItem() instanceof IBauble && stack.getItem().getItemEnchantability() <= 0) {
            switch (stack.getRarity()) {
                case uncommon: return 20;
                case rare:
                case epic: return 30;
                default:
                    if(stack.getRarity().rarityColor == EnumChatFormatting.GOLD) { //Botania relics
                        return 30;
                    }
                    return 10;
            }
        }else{
            return stack.getItem().getItemEnchantability();
        }
    }
}
