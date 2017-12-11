package be.seeseemelk.itemlib;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.enchantment.PrepareItemEnchantEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.event.entity.ItemMergeEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.inventory.BrewingStandFuelEvent;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerBucketEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerItemMendEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
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
	public void onBlockDamage(BlockDamageEvent event)
	{
		lib.callEvent(event.getItemInHand(), event);
	}
	
	@EventHandler
	public void onBlockDispense(BlockDispenseEvent event)
	{
		lib.callEvent(event.getItem(), event);
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event)
	{
		lib.callEvent(event.getItemInHand(), event);
	}
	
	@EventHandler
	public void onEnchantItem(EnchantItemEvent event)
	{
		lib.callEvent(event.getItem(), event);
	}
	
	@EventHandler
	public void onPrepareItemEnchant(PrepareItemEnchantEvent event)
	{
		lib.callEvent(event.getItem(), event);
	}
	
	@EventHandler
	public void onEntityPickupItem(EntityPickupItemEvent event)
	{
		lib.callEvent(event.getItem().getItemStack(), event);
	}
	
	@EventHandler
	public void onEntityShootBow(EntityShootBowEvent event)
	{
		lib.callEvent(event.getBow(), event);
	}
	
	@EventHandler
	public void onItemDespawn(ItemDespawnEvent event)
	{
		lib.callEvent(event.getEntity().getItemStack(), event);
	}
	
	@EventHandler
	public void onItemMerge(ItemMergeEvent event)
	{
		lib.callEvent(event.getEntity().getItemStack(), event);
		lib.callEvent(event.getTarget().getItemStack(), event);
	}
	
	@EventHandler
	public void onItemSpawn(ItemSpawnEvent event)
	{
		lib.callEvent(event.getEntity().getItemStack(), event);
	}
	
	@EventHandler
	public void onBrewingStandFuel(BrewingStandFuelEvent event)
	{
		lib.callEvent(event.getFuel(), event);
	}
	
	@EventHandler
	public void onFurnaceBurn(FurnaceBurnEvent event)
	{
		lib.callEvent(event.getFuel(), event);
	}
	
	@EventHandler
	public void onFurnaceSmelt(FurnaceSmeltEvent event)
	{
		lib.callEvent(event.getResult(), event);
		lib.callEvent(event.getSource(), event);
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event)
	{
		lib.callEvent(event.getCurrentItem(), event);
		lib.callEvent(event.getCursor(), event);
	}
	
	@EventHandler
	public void onInventoryCreative(InventoryCreativeEvent event)
	{
		lib.callEvent(event.getCursor(), event);
	}
	
	@EventHandler
	public void onInventoryDrag(InventoryDragEvent event)
	{
		lib.callEvent(event.getCursor(), event);
		lib.callEvent(event.getOldCursor(), event);
	}
	
	@EventHandler
	public void onInventoryMoveItem(InventoryMoveItemEvent event)
	{
		lib.callEvent(event.getItem(), event);
	}
	
	@EventHandler
	public void onInventoryPickupItem(InventoryPickupItemEvent event)
	{
		lib.callEvent(event.getItem().getItemStack(), event);
	}
	
	@EventHandler
	public void onPrepareAnvil(PrepareAnvilEvent event)
	{
		lib.callEvent(event.getResult(), event);
	}
	
	@EventHandler
	public void onPlayerArmorStandManipulate(PlayerArmorStandManipulateEvent event)
	{
		lib.callEvent(event.getArmorStandItem(), event);
		lib.callEvent(event.getPlayerItem(), event);
	}
	
	@EventHandler
	public void onPlayerBucket(PlayerBucketEvent event)
	{
		lib.callEvent(event.getItemStack(), event);
	}
	
	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent event)
	{
		lib.callEvent(event.getItemDrop().getItemStack(), event);
	}
	
	@EventHandler
	public void onPlayer(PlayerInteractEvent event)
	{
		lib.callEvent(event.getItem(), event);
	}
	
	@EventHandler
	public void onPlayerItemBreak(PlayerItemBreakEvent event)
	{
		lib.callEvent(event.getBrokenItem(), event);
	}
	
	@EventHandler
	public void onPlayerItemConsume(PlayerItemConsumeEvent event)
	{
		lib.callEvent(event.getItem(), event);
	}
	
	@EventHandler
	public void onPlayerItemMend(PlayerItemMendEvent event)
	{
		lib.callEvent(event.getItem(), event);
	}
	
	@EventHandler
	public void onPlayerSwapHandItems(PlayerSwapHandItemsEvent event)
	{
		lib.callEvent(event.getMainHandItem(), event);
		lib.callEvent(event.getOffHandItem(), event);
	}

}


























