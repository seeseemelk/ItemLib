package be.seeseemelk.itemlib;

import org.bukkit.ChatColor;

public class Name
{
	public static final String PADDING = ChatColor.RESET.toString();
	private final String name;
	private final int id;
	private final String actualName;

	public Name(String name, int id)
	{
		this.name = name;
		this.id = id;
		
		StringBuilder builder = new StringBuilder(name.length() + id + 0);
		builder.append(PADDING);
		builder.append(name);
		for (int i = 0; i < id; i++)
		{
			builder.append(PADDING);
		}
		
		this.actualName = builder.toString();
	}

	public String getName()
	{
		return name;
	}
	
	public int getId()
	{
		return id;
	}
	
	@Override
	public String toString()
	{
		return actualName;
	}
	
	@Override
	public int hashCode()
	{
		return actualName.hashCode();
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof Name)
		{
			return actualName.equals(((Name) obj).actualName);
		}
		else
		{
			return false;
		}
	}
}
