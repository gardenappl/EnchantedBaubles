package goldenapple.enchbaubles.enchant;

public class EnchantmentRegen extends EnchantmentBauble {
    public EnchantmentRegen(int id){
        super(id, 2);
        this.setName("regen");
    }

    @Override
    public int getMinEnchantability(int lvl) {
        return 12 + 15 * (lvl - 1);
    }

    @Override
    public int getMaxEnchantability(int lvl) {
        return this.getMinEnchantability(lvl) + 20;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }
}
