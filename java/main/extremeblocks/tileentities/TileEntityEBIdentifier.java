package main.extremeblocks.tileentities;

import java.util.List;
import main.com.hk.eb.util.IInfo;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class TileEntityEBIdentifier extends TileEntityInventory
{
	public TileEntityEBIdentifier()
	{
		super("Extreme Blocks Identifier");
		inventory = new ItemStack[1];
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack)
	{
		return true;
	}

	public String getItemsInfo()
	{
		if (inventory[0] != null)
		{
			Item item = inventory[0].getItem();
			if (item instanceof IInfo) return ((IInfo) item).getInfo();
			else if (item instanceof ItemBlock && ((ItemBlock) item).field_150939_a instanceof IInfo) return ((IInfo) ((ItemBlock) item).field_150939_a).getInfo();
			else return "No Information available";
		}
		return "";
	}

	@Override
	public List<Slot> getSlots()
	{
		List<Slot> list = super.getSlots();
		list.add(new Slot(this, 0, 79, 98));
		return list;
	}

	@Override
	public boolean update()
	{
		return false;
	}
}
