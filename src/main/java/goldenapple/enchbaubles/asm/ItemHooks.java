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
                case common: return 10;
                case uncommon: return 15;
                case rare: return 20;
                case epic: return 25;
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
