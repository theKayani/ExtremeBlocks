package main.extremeblocks.tileentities;

import main.com.hk.eb.util.RecipeUtils;
import main.com.hk.eb.util.StackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

public class TileEntityRevertingStation extends TileEntityInventory
{
	public boolean taken;

	public TileEntityRevertingStation()
	{
		super("Reverting Station");
		inventory = new ItemStack[10];
	}

	public void slotTakenFrom()
	{
		ItemStack rec = getRecipeResult(getResult());
		if (!taken && rec != null)
		{
			taken = true;
			inventory[0] = StackHelper.consumeItem(getResult(), rec.stackSize);
		}
	}

	@Override
	public boolean update()
	{
		if (!taken)
		{
			placeRecipeInGrid();
			if (getResult() == null)
			{
				clearGrid();
			}
		}

		for (int i = 1; i < inventory.length; i++)
		{
			if (taken && inventory[i] != null)
			{
				break;
			}
			if (i == inventory.length - 1)
			{
				taken = false;
			}
		}
		return false;
	}

	public ItemStack getResult()
	{
		return inventory[0];
	}

	public void clearGrid()
	{
		for (int i = 1; i < inventory.length; i++)
		{
			inventory[i] = null;
		}
	}

	public ItemStack getRecipeResult(ItemStack item)
	{
		for (IRecipe recipe : RecipeUtils.getVanillaRecipes())
		{
			if (item != null && recipe != null && recipe.getRecipeOutput() != null && recipe.getRecipeOutput().getItem().equals(item.getItem())) return recipe.getRecipeOutput().copy();
		}
		return null;
	}

	public boolean canPlaceRecipe()
	{
		ItemStack r = getRecipeResult(getResult());
		return !taken && r != null && getResult() != null && StackHelper.areStacksSameTypeCrafting(r, getResult()) && getResult().stackSize >= r.stackSize;
	}

	public void placeRecipeInGrid()
	{
		if (canPlaceRecipe())
		{
			ItemStack[] recipe = RecipeUtils.getFirstRecipeForItem(getResult());
			if (recipe != null && recipe.length > 0)
			{
				for (int i = 0; i < recipe.length; i++)
				{
					inventory[i + 1] = recipe[i] == null || recipe[i].getItem().hasContainerItem(recipe[i]) ? null : recipe[i].copy();
				}
			}
		}
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack)
	{
		return slot == 0 ? true : false;
	}
}
