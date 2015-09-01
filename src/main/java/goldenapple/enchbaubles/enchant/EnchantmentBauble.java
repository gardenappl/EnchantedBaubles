package goldenapple.enchbaubles.enchant;

import baubles.api.IBauble;
import goldenapple.enchbaubles.reference.Reference;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;

public class EnchantmentBauble extends Enchantment {
    protected EnchantmentBauble(int id, int weight) {
        super(id, weight, EnumEnchantmentType.all);
    }

    @Override
    public boolean canApply(ItemStack itemStack) {
        return itemStack.getItem() instanceof IBauble;
    }

    @Override
    public String getName() {
        return "enchantment." + Reference.MOD_ID + "." + this.name;
    }
}
