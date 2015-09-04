package goldenapple.enchbaubles.enchant;

public class EnchantmentExperience extends EnchantmentBauble {
    public EnchantmentExperience(int id){
        super(id, 10);
        this.setName("experience");
    }

    @Override
    public int getMinEnchantability(int lvl) {
        return 15 + 10 * (lvl - 1);
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
