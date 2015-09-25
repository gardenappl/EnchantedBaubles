package goldenapple.enchapi.api;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;

/**
 * Fired to check the enchantability of an ItemStack from {@link Item#getItemEnchantability(net.minecraft.item.ItemStack)}. Note:
 * this won't be called if an item overrides that method.
 *
 * Fired on the {@link MinecraftForge#EVENT_BUS}
 */
public class GetItemEnchantabilityEvent extends Event {
    public int enchantability;
    public final ItemStack stack;

    public GetItemEnchantabilityEvent(ItemStack stack){
        this.stack = stack;
        this.enchantability = stack.getItem().getItemEnchantability();
    }
}
