package be.seeseemelk.itemlib;

import static org.junit.Assert.*;

import org.bukkit.Material;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import be.seeseemelk.mockbukkit.MockBukkit;

public class StaticPluginTest
{
	private StaticPluginTestItem item;
	
	@Before
	public void setUp()
	{
		MockBukkit.mock();
		ItemLib.instantiate();
		item = new StaticPluginTestItem();
	}
	
	@After
	public void tearDown()
	{
		MockBukkit.unload();
	}
	
	@Test
	public void new_Default_ItemNameSet()
	{
		assertTrue(item.hasItemMeta());
		assertEquals(Name.PADDING + "Test Item", item.getItemMeta().getDisplayName());
	}
	
	@Test
	public void new_Default_MaterialSet()
	{
		assertEquals(Material.STICK, item.getType());
	}

}

class StaticPluginTestItem extends StaticPluginItem
{

	protected StaticPluginTestItem()
	{
		super("Test Item", Material.STICK);
	}
	
}