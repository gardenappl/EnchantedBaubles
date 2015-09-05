package goldenapple.enchbaubles.enchant;

public class EnchantmentReach extends EnchantmentBauble {
    public EnchantmentReach(int id, int weight) {
        super(id, weight, EnchantmentBauble.ring);
        this.setName("reach");
    }

    @Override
    public int getMinEnchantability(int lvl) {
        return 15;
    }

    @Override
    public int getMaxEnchantability(int lvl) {
        return this.getMinEnchantability(lvl) + 20;
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }
}
