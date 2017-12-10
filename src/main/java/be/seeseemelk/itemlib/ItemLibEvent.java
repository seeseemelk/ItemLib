package be.seeseemelk.itemlib;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ItemLibEvent implements Listener
{
	@SuppressWarnings("unused")
	private JavaPlugin plugin;
	private ItemLib lib;

	public ItemLibEvent(JavaPlugin plugin, ItemLib lib)
	{
		this.plugin = plugin;
		this.lib = lib;
	}
	
	@EventHandler
	public void onPlayer(PlayerInteractEvent event)
	{
		lib.callEvent(event.getItem(), event);
	}

}
