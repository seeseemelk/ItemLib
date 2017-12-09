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
	private Map<String, StaticItem> itemsByName = new HashMap<>();
	private Map<Class<? extends StaticItem>, StaticItem> itemsByClass = new HashMap<>();
	
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
	 * Store a new item in the internal maps.
	 * @param item The item to store.
	 */
	private void storeItem(StaticItem item)
	{
		itemsByName.put(item.getActualName().toString(), item);
		itemsByClass.put(item.getClass(), item);
	}
	
	/**
	 * Get an item by the name of the item.
	 * @param name The name of the item.
	 * @return The item or {@code null} if no such item exists.
	 */
	private StaticItem getItem(String name)
	{
		return itemsByName.get(name);
	}
	
	/**
	 * Get an item by the name of the item.
	 * @param name The name of the item.
	 * @return The item or {@code null} if no such item exists.
	 */
	@SuppressWarnings("unused")
	private StaticItem getItem(Name name)
	{
		return getItem(name.toString());
	}
	
	/**
	 * Registers a new item.
	 * @param item The new item to register
	 */
	public void registerItem(StaticItem item)
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
	 * @param name The name to convert.
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
	 * @param type The type of item to get.
	 * @return The item or {@code null} if no such item has been registered.
	 */
	public StaticItem getItem(Class<? extends StaticItem> type)
	{
		return itemsByClass.get(type);
	}
	
	/**
	 * Check if an item type has been registered yet.
	 * @param type The type of item to check for.
	 * @return {@code true} if the item has been registered, {@code false} if it has not been registered.
	 */
	public boolean isRegistered(Class<? extends StaticItem> type)
	{
		return itemsByClass.get(type) != null;
	}
	
	/**
	 * Get an itemstack of the item.
	 * @param type The type of item to get an itemstack of.
	 * @return An itemstack of the item.
	 */
	public ItemStack getItemStack(Class<? extends StaticItem> type)
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
	 * @param item The item to check.
	 * @return {@code true} if the item is a custom item, {@code false} if it isn't.
	 */
	public boolean isItem(ItemStack item)
	{
		return false;
	}
	
	@EventHandler
	private void onPlayerInteract(PlayerInteractEvent event)
	{
		ItemStack item = event.getItem();
	}
}

































