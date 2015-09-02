package goldenapple.enchbaubles;

import baubles.api.IBauble;
import cpw.mods.fml.common.registry.GameRegistry;
import goldenapple.enchbaubles.enchant.EnchantmentBauble;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import java.util.List;

public class BaublesCreativeTab extends CreativeTabs {
    public BaublesCreativeTab() {
        super("baubles");
    }

    @Override
    public Item getTabIconItem() {
        return GameRegistry.findItem("Baubles", "Ring");
    }

    @Override
    public void displayAllReleventItems(List list) {
        for(Object object : Item.itemRegistry){
            Item item = (Item) object;

            if(item != null) {
                if (item instanceof IBauble)
                    item.getSubItems(item, this, list);
                else {
                    for (CreativeTabs tab : item.getCreativeTabs()) {
                        if (tab == this)
                            item.getSubItems(item, this, list);
                    }
                }
            }
        }

        addEnchantmentBooksToList(list, EnchantmentBauble.bauble);
        addEnchantmentBooksToList(list, EnchantmentBauble.amulet);
        addEnchantmentBooksToList(list, EnchantmentBauble.ring);
        addEnchantmentBooksToList(list, EnchantmentBauble.belt);
    }
}
