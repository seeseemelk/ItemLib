package be.seeseemelk.itemlib;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.java.JavaPluginLoader;

public class ItemLibPlugin extends JavaPlugin
{
	public ItemLibPlugin()
	{
		super();
	}
	
	protected ItemLibPlugin(final JavaPluginLoader loader, final PluginDescriptionFile description, final File dataFolder, final File file)
	{
		super(loader, description, dataFolder, file);
	}
	
	@Override
	public void onLoad()
	{
		ItemLib.instantiate();
	}
	
	@Override
	public void onDisable()
	{
		ItemLib.deinstantiate();
	}
	
	@Override
	public void onEnable()
	{
		ItemLib.getItemLib().registerItem(new CoolNewItem());
		for (Player player : Bukkit.getOnlinePlayers())
		{
			ItemStack item = ItemLib.getItemLib().getItemStack(CoolNewItem.class);
			item.setAmount(1);
			player.getInventory().addItem(item);
			player.sendMessage(ChatColor.GREEN + "Added CoolNewItem");
		}
	}
}
































