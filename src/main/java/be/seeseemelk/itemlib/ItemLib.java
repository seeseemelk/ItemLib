package be.seeseemelk.itemlib;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ItemLib
{
	private static ItemLib itemLib;
	private Map<String, Integer> itemNameCounts = new HashMap<>();
	private Map<String, StaticPluginItem> itemsByName = new HashMap<>();
	private Map<Class<? extends StaticPluginItem>, StaticPluginItem> itemsByClass = new HashMap<>();
	
	/**
	 * Get the currently running instance of {@code ItemLib}.
	 * 
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
	 * Store a new item in the internal maps.
	 * 
	 * @param item
	 *            The item to store.
	 */
	private void storeItem(StaticPluginItem item)
	{
		itemsByName.put(item.getActualName().toString(), item);
		itemsByClass.put(item.getClass(), item);
	}
	
	/**
	 * Get an item by the name of the item.
	 * 
	 * @param name
	 *            The name of the item.
	 * @return The item or {@code null} if no such item exists.
	 */
	private StaticPluginItem getItem(String name)
	{
		return itemsByName.get(name);
	}
	
	/**
	 * Get an item by the name of the item.
	 * 
	 * @param name
	 *            The name of the item.
	 * @return The item or {@code null} if no such item exists.
	 */
	@SuppressWarnings("unused")
	private StaticPluginItem getItem(Name name)
	{
		return getItem(name.toString());
	}
	
	/**
	 * Registers a new item.
	 * 
	 * @param item
	 *            The new item to register
	 */
	public void registerItem(StaticPluginItem item)
	{
		if (!isRegistered(item.getClass()))
		{
			storeItem(item);
		}
		else
		{
			throw new IllegalStateException("Already registered");
		}
	}
	
	/**
	 * Converts a string to a name object.
	 * 
	 * @param name
	 *            The name to convert.
	 * @return The converted name.
	 */
	public Name getName(String name)
	{
		if (itemNameCounts.containsKey(name))
		{
			int id = itemNameCounts.get(name) + 1;
			itemNameCounts.put(name, id);
			return new Name(name, id);
		}
		else
		{
			itemNameCounts.put(name, 0);
			return new Name(name, 0);
		}
	}
	
	/**
	 * Get an item by the class of the item.
	 * 
	 * @param type
	 *            The type of item to get.
	 * @return The item or {@code null} if no such item has been registered.
	 */
	public StaticPluginItem getItem(Class<? extends StaticPluginItem> type)
	{
		return itemsByClass.get(type);
	}
	
	/**
	 * Check if an item type has been registered yet.
	 * 
	 * @param type
	 *            The type of item to check for.
	 * @return {@code true} if the item has been registered, {@code false} if it has
	 *         not been registered.
	 */
	public boolean isRegistered(Class<? extends StaticPluginItem> type)
	{
		return itemsByClass.get(type) != null;
	}
	
	/**
	 * Get an itemstack of the item.
	 * 
	 * @param type
	 *            The type of item to get an itemstack of.
	 * @return An itemstack of the item.
	 */
	public ItemStack getItemStack(Class<? extends StaticPluginItem> type)
	{
		if (isRegistered(type))
		{
			return new ItemStack(getItem(type));
		}
		else
		{
			throw new IllegalArgumentException("Not a registered type");
		}
	}
	
	/**
	 * Check if the item is a custom item.
	 * 
	 * @param item
	 *            The item to check.
	 * @return {@code true} if the item is a custom item, {@code false} if it isn't.
	 */
	public boolean isItem(ItemStack item)
	{
		if (item.hasItemMeta() && item.getItemMeta().hasDisplayName())
		{
			String name = item.getItemMeta().getDisplayName();
			if (itemsByName.containsKey(name))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Get the instance of the class a certain itemstack belongs to, if it belongs
	 * to any.
	 * 
	 * @param item
	 *            The item to get the class type of.
	 * @return The instance of the class type that describes the item that was used
	 *         to register the item, or {@code null} if it isn't a registered item.
	 */
	public StaticPluginItem getType(ItemStack item)
	{
		if (isItem(item))
		{
			return itemsByName.get(item.getItemMeta().getDisplayName());
		}
		else
		{
			return null;
		}
	}
	
	/**
	 * Checks if the given itemstack is of a certain type.
	 * @param item The item to check for.
	 * @param type The type to check against.
	 * @return {@code true} if the item is of that type, {@code false} if it is of a different type.
	 */
	public boolean is(ItemStack item, Class<? extends StaticPluginItem> type)
	{
		return isItem(item) && getType(item).getClass().equals(type);
	}
	
	@EventHandler
	private void onPlayerInteract(PlayerInteractEvent event)
	{
		// ItemStack item = event.getItem();
	}
}
