package goldenapple.enchbaubles;

import baubles.api.IBauble;
import cpw.mods.fml.common.registry.GameRegistry;
import goldenapple.enchbaubles.enchant.EnchantmentBauble;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import java.util.Iterator;
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
        Iterator iterator = Item.itemRegistry.iterator();

        while(iterator.hasNext()){
            Item item = (Item) iterator.next();

            if(item != null && item instanceof IBauble)
                item.getSubItems(item, this, list);
        }

        this.addEnchantmentBooksToList(list, EnchantmentBauble.bauble);
        this.addEnchantmentBooksToList(list, EnchantmentBauble.amulet);
        this.addEnchantmentBooksToList(list, EnchantmentBauble.ring);
        this.addEnchantmentBooksToList(list, EnchantmentBauble.belt);
    }
}
