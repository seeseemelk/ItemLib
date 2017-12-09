package be.seeseemelk.itemlib;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ItemLib
{
	private static ItemLib itemLib;
	private Map<Name, StaticItem> items = new HashSet<>();
	
	/**
	 * Get the currently running instance of {@code ItemLib}.
	 * @return The currently running instance of {@code ItemLib}.
	 */
	public static ItemLib getItemLib()
	{
		return itemLib;
	}

	/**
	 * Creates a new instance of the {@code ItemLib}.
	 */
	protected static void instantiate()
	{
		itemLib = new ItemLib();
	}

	protected ItemLib()
	{
	}
	
	/**
	 * Converts a string to a name object.
	 * @param name The name to convert.
	 * @return The converted name.
	 */
	public Name toName(String name)
	{
		int id = 0;
		for (Name otherName : items.keySet())
		{
			if (name.equals(otherName.getName()))
			{
				id++;
			}
		}
		return new Name(name, id);
	}
	
	/**
	 * Registers a new item.
	 * @param item The new item to register
	 */
	public void registerItem(Class<? extends StaticItem> item)
	{
		item.newInstance();
		/*Name name = item.getActualName();
		if (!items.containsKey(name))
		{
			items.put(item.getActualName(), item);
			return item;
		}
		else
		{
			throw new IllegalStateException("Already registered");
		}*/
	}
	
	/**
	 * Check if the item is a custom item.
	 * @param item The item to check.
	 * @return {@code true} if the item is a custom item, {@code false} if it isn't.
	 */
	public boolean isItem(ItemStack item)
	{
		if (item)
	}
	
	@EventHandler
	private void onPlayerInteract(PlayerInteractEvent event)
	{
		ItemStack item = event.getItem();
	}
}

































