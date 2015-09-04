package goldenapple.enchbaubles.enchant;

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
    public boolean canApply(ItemStack stack) {
        if(!(stack.getItem() instanceof IBauble))
            return false;

        switch(((IBauble) stack.getItem()).getBaubleType(stack)) {
            case AMULET: return type == amulet || type == bauble;
            case RING: return type == ring || type == bauble;
            case BELT: return type == belt || type == bauble;
            default: return type == bauble;
        }
    }

    @Override
    public String getName() {
        return "enchantment." + Reference.MOD_ID + "." + this.name;
    }
}
