package main.extremeblocks.tileentities;

import main.com.hk.eb.util.StackHelper;
import main.extremeblocks.blocks.BlockRecipeRevert;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

public class TileEntityRevertingStation extends TileEntityInventory
{
	public boolean pickedUpFrom, removeStack;

	public TileEntityRevertingStation()
	{
		inventory = new ItemStack[10];
	}

	public void removeStack()
	{
		IRecipe rec = BlockRecipeRevert.getRecipeFor(getResult());
		if (!pickedUpFrom && rec != null)
		{
			pickedUpFrom = true;
			inventory[0] = StackHelper.consumeItem(getResult(), rec.getRecipeOutput().stackSize);
		}
	}

	@Override
	public void updateEntity()
	{
		boolean reset = false;
		if (!pickedUpFrom && (getResult() == null || getResult().stackSize <= 0))
		{
			setAllToNull();
		}
		else if (!pickedUpFrom)
		{
			ItemStack[] recipes = BlockRecipeRevert.getRecipeItemsFor(getResult());
			if (recipes != null && recipes.length > 0)
			{
				for (int i = 0; i < recipes.length; i++)
				{
					ItemStack st = recipes[i] == null || recipes[i].getItem().hasContainerItem(recipes[i]) ? null : recipes[i].copy();
					if (st != null)
					{
						st.stackSize = 1;
					}
					inventory[i + 1] = st;
				}
			}
		}
		for (int i = 1; i < inventory.length; i++)
		{
			if (pickedUpFrom && inventory[i] != null)
			{
				break;
			}
			if (i == inventory.length - 1)
			{
				reset = true;
			}
		}

		if (reset)
		{
			pickedUpFrom = false;
		}
	}

	public ItemStack getResult()
	{
		return inventory[0];
	}

	public void setAllToNull()
	{
		setToNull(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
	}

	public void setToNull(int... ints)
	{
		for (int i = 0; i < ints.length; i++)
		{
			if (ints[i] >= 0 && ints[i] < inventory.length)
			{
				inventory[ints[i]] = null;
			}
		}
	}

	@Override
	public String getInventoryName()
	{
		return "Recipe Reverting Station";
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return false;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entity)
	{
		return worldObj.getTileEntity(xCoord, yCoord, zCoord) != this ? false : entity.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack)
	{
		return true;
	}
}
