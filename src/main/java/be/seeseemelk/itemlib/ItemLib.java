package be.seeseemelk.itemlib;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class ItemLib
{
	private static ItemLib itemLib;
	private ItemLibPlugin plugin;
	private Map<String, Integer> itemNameCounts = new HashMap<>();
	private Map<String, StaticPluginItem> itemsByName = new HashMap<>();
	private Map<Class<? extends StaticPluginItem>, StaticPluginItem> itemsByClass = new HashMap<>();
	private Map<Plugin, List<StaticPluginItem>> itemsByPlugin = new HashMap<>();

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
	protected static void instantiate(ItemLibPlugin plugin)
	{
		itemLib = new ItemLib(plugin);
	}

	/**
	 * Removes the static reference to the {@code ItemLib} singleton.
	 */
	protected static void deinstantiate()
	{
		itemLib = null;
	}

	/**
	 * Creates a new instance of the {@code ItemLib} plugin.
	 * 
	 * @param plugin The plugin that instantiated the {@code ItemLib}.
	 */
	protected ItemLib(ItemLibPlugin plugin)
	{
		this.plugin = plugin;
	}

	/**
	 * Store a new item in the internal maps.
	 * 
	 * @param plugin The plugin that owns the item.
	 * @param item The item to store.
	 */
	private void storeItem(Plugin plugin, StaticPluginItem item)
	{
		itemsByName.put(item.getActualName().toString(), item);
		itemsByClass.put(item.getClass(), item);
		
		List<StaticPluginItem> items = itemsByPlugin.get(plugin);
		if (items == null)
		{
			items = new ArrayList<>();
			itemsByPlugin.put(plugin, items);
		}
		items.add(item);
	}

	/**
	 * Performs tasks that have to be done during the {@code onEnable} method of the plugin,
	 * such as registering events.
	 */
	protected void enable()
	{
		Bukkit.getPluginManager().registerEvents(new ItemLibEvent(plugin, this), plugin);
	}

	/**
	 * Calls an event for an item, if the item is a registered item.
	 * @param item The {@link ItemStack} to check.
	 * @param event The event to call.
	 */
	protected void callEvent(ItemStack item, Event event)
	{
		if (isItem(item))
		{
			StaticPluginItem pluginItem = getType(item);
			for (Method method : pluginItem.getClass().getMethods())
			{
				if (method.isAnnotationPresent(EventHandler.class)
					&& method.getParameterCount() == 1
					&& method.getParameters()[0].getType().isInstance(event))
				{
					try
					{
						method.invoke(pluginItem, event);
					}
					catch (IllegalAccessException e)
					{
						e.printStackTrace();
					}
					catch (IllegalArgumentException e)
					{
						plugin.getLogger().severe(pluginItem.getClass().getName() + "#" + method.getName() + " is not a correct event");
						e.printStackTrace();
					}
					catch (InvocationTargetException e)
					{
						plugin.getLogger().throwing(pluginItem.getClass().getName(), method.getName(), e);
					}
				}
			}
		}
	}
	
	/**
	 * Unload all items made by a certain plugin.
	 * @param plugin The plugin to unload.
	 */
	protected void unloadPlugin(Plugin plugin)
	{
		for (StaticPluginItem item : itemsByPlugin.get(plugin))
		{
			itemsByName.remove(item.getActualName().toString());
			itemsByClass.remove(item.getClass());
		}
		itemsByPlugin.remove(plugin);
	}

	/**
	 * Registers a new item.
	 * 
	 * @param plugin The plugin that is registering the item.
	 * @param item The new item to register
	 */
	public void registerItem(Plugin plugin, StaticPluginItem item)
	{
		if (!isRegistered(item.getClass()))
		{
			storeItem(plugin, item);
		}
		else
		{
			throw new IllegalStateException("Already registered");
		}
	}

	/**
	 * Converts a string to a name object.
	 * 
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
	 * 
	 * @param type The type of item to get.
	 * @return The item or {@code null} if no such item has been registered.
	 */
	public StaticPluginItem getItem(Class<? extends StaticPluginItem> type)
	{
		return itemsByClass.get(type);
	}

	/**
	 * Check if an item type has been registered yet.
	 * 
	 * @param type The type of item to check for.
	 * @return {@code true} if the item has been registered, {@code false} if it
	 *         has not been registered.
	 */
	public boolean isRegistered(Class<? extends StaticPluginItem> type)
	{
		return itemsByClass.get(type) != null;
	}

	/**
	 * Get an itemstack of the item.
	 * 
	 * @param type The type of item to get an itemstack of.
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
	 * Returns {@code false} if a null is passed to the method.
	 * 
	 * @param item The item to check.
	 * @return {@code true} if the item is a custom item, {@code false} if it
	 *         isn't.
	 */
	public boolean isItem(ItemStack item)
	{
		if (item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName())
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
	 * Get the instance of the class a certain itemstack belongs to, if it
	 * belongs to any.
	 * 
	 * @param item The item to get the class type of.
	 * @return The instance of the class type that describes the item that was
	 *         used to register the item, or {@code null} if it isn't a
	 *         registered item.
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
	 * 
	 * @param item The item to check for.
	 * @param type The type to check against.
	 * @return {@code true} if the item is of that type, {@code false} if it is
	 *         of a different type.
	 */
	public boolean is(ItemStack item, Class<? extends StaticPluginItem> type)
	{
		return isItem(item) && getType(item).getClass().equals(type);
	}
}
