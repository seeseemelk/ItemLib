package be.seeseemelk.itemlib;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * A static plugin item is an item that is created by a different plugin but uses the same
 * instance for every itemstack.
 * This makes the items easily stackable, but you won't be able to store different information for
 * each instance of the item.
 * A {@code StaticPluginItem} class should only be instantiated once, and that instance should
 * then be passed on the {@link ItemLib#registerItem(StaticPluginItem)}.
 * When you went to create actual item stacks of the item, {@link ItemLib#getItemStack(Class)} is your friend.
 * @author seeseemelk
 *
 */
public abstract class StaticPluginItem extends ItemStack
{
	private Name name;
	
	/**
	 * Creates a new static item with the given name.
	 * @param name The name to give to the item.
	 */
	protected StaticPluginItem(String name, Material material)
	{
		super(material);
		this.name = ItemLib.getItemLib().getName(name);
		ItemMeta meta = getItemMeta();
		meta.setDisplayName(this.name.toString());
		setItemMeta(meta);
	}
	
	/**
	 * Get the name of the object.
	 * @return The name of the object.
	 */
	public String getName()
	{
		return name.getName();
	}
	
	/**
	 * Get the actual name of the item.
	 * @return The {@link Name} of the object.
	 */
	public Name getActualName()
	{
		return name;
	}
	
}
