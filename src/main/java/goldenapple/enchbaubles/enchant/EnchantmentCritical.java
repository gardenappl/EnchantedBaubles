package goldenapple.enchbaubles.enchant;

public class EnchantmentCritical extends EnchantmentBauble {
    public EnchantmentCritical(int id, int weight) {
        super(id, weight);
        this.setName("critical");
    }

    @Override
    public int getMinEnchantability(int lvl) {
        return 17 + 12 * (lvl - 1);
    }

    @Override
    public int getMaxEnchantability(int lvl) {
        return this.getMinEnchantability(lvl) + 20;
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }
}
