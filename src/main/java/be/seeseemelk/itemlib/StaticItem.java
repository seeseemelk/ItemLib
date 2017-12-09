package be.seeseemelk.itemlib;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

public abstract class StaticItem extends ItemStack
{
	private Name name;
	
	/**
	 * Creates a new static item with the given name.
	 * @param name The name to give to the item.
	 */
	protected StaticItem(String name, Material material)
	{
		ItemLib itemLib = ItemLib.getItemLib();
		if (!itemLib.hasBeenInitialized())
		{
			itemLib.initialize(this.name);
		}
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
	 * Set the name of the item.
	 * @param name The name to give to the item.
	 */
	public void setName(String name)
	{
		this.name = ItemLib.getItemLib().toName(ChatColor.RESET + name);
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
