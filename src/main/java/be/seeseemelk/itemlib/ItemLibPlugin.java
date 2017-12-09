package be.seeseemelk.itemlib;

import java.io.File;

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
	public void onEnable()
	{
		ItemLib.instantiate();
	}
}
































