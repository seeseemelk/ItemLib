package be.seeseemelk.itemlib;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
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
		assertEquals(Name.PADDING + "Placebo", name.toString());
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
		assertEquals(Name.PADDING + "Test Item", item.getItemMeta().getDisplayName());
	}
	
	@Test
	public void isItem_NoneRegisteredAndNoCustomName_False()
	{
		ItemStack item = new ItemStack(Material.STICK);
		assertFalse(lib.isItem(item));
	}
	
	@Test
	public void isItem_NoneRegisteredAndCustomName_False()
	{
		ItemStack item = new ItemStack(Material.STICK);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Test Item");
		item.setItemMeta(meta);
		assertFalse(lib.isItem(item));
	}
	
	@Test
	public void isItem_OneRegisteredWithDifferentName_False()
	{
		lib.registerItem(new TestItem());
		ItemStack item = new ItemStack(Material.STICK);
		assertFalse(lib.isItem(item));
	}
	
	@Test
	public void isItem_OneRegisteredWithSameName_False()
	{
		lib.registerItem(new TestItem());
		ItemStack item = new ItemStack(Material.STICK);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName("Test Item");
		item.setItemMeta(meta);
		assertFalse(lib.isItem(item));
	}
	
	@Test
	public void isItem_RegisteredItem_True()
	{
		lib.registerItem(new TestItem());
		ItemStack item = lib.getItemStack(TestItem.class);
		assertTrue(lib.isItem(item));
	}
	
	@Test
	public void getType_NotRegisteredItem_Null()
	{
		lib.registerItem(new TestItem());
		ItemStack item = new ItemStack(Material.STICK);
		assertNull(lib.getType(item));
	}
	
	@Test
	public void getType_RegisteredItem_CorrectClass()
	{
		TestItem type = new TestItem();
		lib.registerItem(type);
		ItemStack item = lib.getItemStack(TestItem.class);
		assertEquals(type, lib.getType(item));
	}
	
	@Test
	public void is_NotRegisteredItem_False()
	{
		lib.registerItem(new TestItem());
		ItemStack item = new ItemStack(Material.STICK);
		assertFalse(lib.is(item, TestItem.class));
	}
	
	@Test
	public void is_RegisteredItem_True()
	{
		lib.registerItem(new TestItem());
		ItemStack item = lib.getItemStack(TestItem.class);
		assertTrue(lib.is(item, TestItem.class));
	}
	
}

class TestItem extends StaticPluginItem
{

	protected TestItem()
	{
		super("Test Item", Material.STICK);
	}
	
}