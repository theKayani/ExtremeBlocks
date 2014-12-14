package main.extremeblocks.misc;

import java.util.HashMap;
import net.minecraftforge.common.util.ForgeDirection;

public class PowerMap extends HashMap<ForgeDirection, Boolean>
{
	private static final long serialVersionUID = 1L;
	public final String id;

	public PowerMap(String id)
	{
		super();
		this.id = id;
		setAllTo(false);
	}

	public void setAllTo(boolean value)
	{
		for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS)
		{
			put(dir, value);
		}
	}

	public boolean isAllFalse()
	{
		for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS)
		{
			if (get(dir)) return false;
		}
		return true;
	}

	public boolean isAllTrue()
	{
		for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS)
		{
			if (!get(dir)) return false;
		}
		return true;
	}

	public String getStringFor(ForgeDirection dir)
	{
		return dir.name().substring(0, 1) + "-" + get(dir).toString();
	}
}
