package be.seeseemelk.itemlib;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;

public class CoolNewItem extends StaticPluginItem
{
	public CoolNewItem()
	{
		super("My cool new item", Material.STICK);
	}
	
	@EventHandler
	public void onBlockInteract(PlayerInteractEvent event)
	{
		if (event.hasBlock() && event.getClickedBlock().getType() != Material.AIR)
		{
			Block block = event.getClickedBlock();
			block.getWorld().strikeLightning(block.getLocation());
		}
	}
}
