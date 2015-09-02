package goldenapple.enchbaubles.enchant;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import goldenapple.enchbaubles.reference.Reference;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

public class EnchantmentBauble extends Enchantment {
    public static final EnumEnchantmentType bauble = EnumHelper.addEnchantmentType("bauble");
    public static final EnumEnchantmentType amulet = EnumHelper.addEnchantmentType("amulet");
    public static final EnumEnchantmentType ring = EnumHelper.addEnchantmentType("ring");
    public static final EnumEnchantmentType belt = EnumHelper.addEnchantmentType("belt");

    protected EnchantmentBauble(int id, int weight) {
        super(id, weight, bauble);
    }

    protected EnchantmentBauble(int id, int weight, EnumEnchantmentType type) {
        super(id, weight, type);
    }

    @Override
    public boolean canApply(ItemStack itemStack) {
        if(type == amulet)
            return itemStack.getItem() instanceof IBauble && ((IBauble) itemStack.getItem()).getBaubleType(itemStack) == BaubleType.AMULET;
        else if(type == ring)
            return itemStack.getItem() instanceof IBauble && ((IBauble) itemStack.getItem()).getBaubleType(itemStack) == BaubleType.RING;
        else if(type == belt)
            return itemStack.getItem() instanceof IBauble && ((IBauble) itemStack.getItem()).getBaubleType(itemStack) == BaubleType.BELT;
        else
            return itemStack.getItem() instanceof IBauble;
    }

    @Override
    public String getName() {
        return "enchantment." + Reference.MOD_ID + "." + this.name;
    }
}
