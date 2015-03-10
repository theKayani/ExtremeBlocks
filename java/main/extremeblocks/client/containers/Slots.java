package main.extremeblocks.client.containers;

import java.util.ArrayList;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class Slots extends ArrayList<Slot>
{
	private static final long serialVersionUID = 1L;
	private final IInventory inv;
	private final int height, width, x, y;
	private int startID, apartX, apartY;

	public Slots(IInventory inv, int x, int y, int width, int height)
	{
		this.inv = inv;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		apartX = apartY = 18;
		startID = 0;
		getSlots();
	}

	public final Slots setStartID(int startID)
	{
		this.startID = startID;
		return getSlots();
	}

	public final Slots setApartX(int apartX)
	{
		this.apartX = apartX;
		return getSlots();
	}

	public final Slots setApartY(int apartY)
	{
		this.apartY = apartY;
		return getSlots();
	}

	public final Slots setApart(int apartX, int apartY)
	{
		this.apartX = apartX;
		this.apartY = apartY;
		return getSlots();
	}

	public final Slots setApart(int apart)
	{
		setApart(apart, apart);
		return getSlots();
	}

	private Slots getSlots()
	{
		clear();
		for (int i = 0; i < height; i++)
		{
			for (int j = 0; j < width; j++)
			{
				add(new Slot(inv, j + i * width + startID, x + j * apartX, y + i * apartY));
			}
		}
		return this;
	}
}
