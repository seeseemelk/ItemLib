package be.seeseemelk.itemlib;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class StaticItem extends ItemStack
{
	private Name name;
	
	/**
	 * Creates a new static item with the given name.
	 * @param name The name to give to the item.
	 */
	protected StaticItem(String name, Material material)
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
