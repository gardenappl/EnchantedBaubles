package goldenapple.enchapi.api;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

@Event.HasResult
public class ItemEnchantableEvent extends Event {
    public final ItemStack stack;

    /**
     * Fired to check if an item is enchantable from {@link ItemStack#isItemEnchantable()}.
     * Set the result to DENY to disable the enchantment, ALLOW to force the item to be enchantable,
     * DEFAULT to run the default enchanting behaviour.
     *
     * Note: it's YOUR responsibility to check if an item has already been enchanted.
     *
     * Fired on the {@link MinecraftForge#EVENT_BUS}
     */
    public ItemEnchantableEvent(ItemStack stack){
        this.stack = stack;
    }
}
