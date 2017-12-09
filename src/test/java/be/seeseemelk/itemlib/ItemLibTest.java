package be.seeseemelk.itemlib;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.junit.Before;
import org.junit.Test;

import be.seeseemelk.mockbukkit.MockBukkit;

public class ItemLibTest
{
	private ItemLib lib;

	@Before
	public void setUp() throws Exception
	{
		MockBukkit.mock();
		ItemLib.instantiate();
		lib = ItemLib.getItemLib();
	}
	
	@Test
	public void registerItem_Once_Succeeds()
	{
		lib.registerItem(new TestItem());
	}
	
	@Test(expected = IllegalStateException.class)
	public void registerItem_TwiceWithDifferentInstances_Fails()
	{
		lib.registerItem(new TestItem());
		lib.registerItem(new TestItem());
	}
	
	@Test(expected = IllegalStateException.class)
	public void registerItem_TwiceWithSameInstance_Failes()
	{
		TestItem item = new TestItem();
		lib.registerItem(item);
		lib.registerItem(item);
	}
	
	@Test
	public void getItem_NotRegistered_Null()
	{
		assertNull(lib.getItem(TestItem.class));
	}
	
	@Test
	public void getItem_Registered_Item()
	{
		TestItem item = new TestItem();
		lib.registerItem(item);
		assertEquals(item, lib.getItem(TestItem.class));
	}
	
	@Test
	public void isRegistered_NotRegistered_False()
	{
		assertFalse(lib.isRegistered(TestItem.class));
	}
	
	@Test
	public void isRegistered_Registered_True()
	{
		lib.registerItem(new TestItem());
		assertTrue(lib.isRegistered(TestItem.class));
	}
	
	@Test
	public void getName_NewName_ExactString()
	{
		Name name = lib.getName("Placebo");
		assertEquals("Placebo", name.getName());
		assertEquals("Placebo", name.toString());
		assertEquals(0, name.getId());
	}
	
	@Test
	public void getName_TwoSameNames_DifferentId()
	{
		Name name1 = lib.getName("Placebo");
		Name name2 = lib.getName("Placebo");
		assertEquals("Placebo", name1.getName());
		assertEquals("Placebo", name2.getName());
		assertEquals(0, name1.getId());
		assertEquals(1, name2.getId());
		assertNotSame(name1.toString(), name2.toString());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getItemStack_NotRegistered_ThrowsException()
	{
		lib.getItemStack(TestItem.class);
	}
	
	@Test
	public void getItemStack_Registered_GetsItemStack()
	{
		lib.registerItem(new TestItem());
		ItemStack item = lib.getItemStack(TestItem.class);
		assertEquals(Material.STICK, item.getType());
		assertEquals("Test Item", item.getItemMeta().getDisplayName());
	}
}

class TestItem extends StaticItem
{

	protected TestItem()
	{
		super("Test Item", Material.STICK);
	}
	
}