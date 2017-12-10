package be.seeseemelk.itemlib;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import be.seeseemelk.mockbukkit.MockBukkit;

public class ItemLibPluginTest
{

	@Before
	public void setUp() throws Exception
	{
		MockBukkit.mock();
		MockBukkit.load(ItemLibPlugin.class);
	}

	@Test
	public void onLoad_itemlibInitialised()
	{
		assertNotNull(ItemLib.getItemLib());
	}

}
