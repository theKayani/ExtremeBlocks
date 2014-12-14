package main.extremeblocks.crafting;

import main.extremeblocks.blocks.BlockEBTable;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EBShapedRecipes implements IRecipe
{
	public final int recipeWidth;
	public final int recipeHeight;
	public final ItemStack[] recipeItems;
	private ItemStack recipeOutput;
	private boolean something;

	public EBShapedRecipes(int p_i1917_1_, int p_i1917_2_, ItemStack[] p_i1917_3_, ItemStack p_i1917_4_)
	{
		recipeWidth = p_i1917_1_;
		recipeHeight = p_i1917_2_;
		recipeItems = p_i1917_3_;
		recipeOutput = p_i1917_4_;
	}

	@Override
	public ItemStack getRecipeOutput()
	{
		return recipeOutput;
	}

	@Override
	public boolean matches(InventoryCrafting inv, World world)
	{
		for (int i = 0; i <= BlockEBTable.CRAFTING_SLOTS - recipeWidth; ++i)
		{
			for (int j = 0; j <= BlockEBTable.CRAFTING_SLOTS - recipeHeight; ++j)
			{
				if (checkMatch(inv, i, j, true)) return true;
				if (checkMatch(inv, i, j, false)) return true;
			}
		}
		return false;
	}

	private boolean checkMatch(InventoryCrafting inv, int x, int y, boolean flag)
	{
		for (int k = 0; k < BlockEBTable.CRAFTING_SLOTS; ++k)
		{
			for (int l = 0; l < BlockEBTable.CRAFTING_SLOTS; ++l)
			{
				int i1 = k - x;
				int j1 = l - y;
				ItemStack itemstack = null;
				if (i1 >= 0 && j1 >= 0 && i1 < recipeWidth && j1 < recipeHeight)
				{
					if (flag)
					{
						itemstack = recipeItems[recipeWidth - i1 - 1 + j1 * recipeWidth];
					}
					else
					{
						itemstack = recipeItems[i1 + j1 * recipeWidth];
					}
				}
				ItemStack itemstack1 = inv.getStackInRowAndColumn(k, l);
				if (itemstack1 != null || itemstack != null)
				{
					if (itemstack1 == null && itemstack != null || itemstack1 != null && itemstack == null) return false;
					if (itemstack.getItem() != itemstack1.getItem()) return false;
					if (itemstack.getItemDamage() != 32767 && itemstack.getItemDamage() != itemstack1.getItemDamage()) return false;
				}
			}
		}
		return true;
	}

	@Override
	public ItemStack getCraftingResult(InventoryCrafting p_77572_1_)
	{
		ItemStack itemstack = getRecipeOutput().copy();
		if (something)
		{
			for (int i = 0; i < p_77572_1_.getSizeInventory(); ++i)
			{
				ItemStack itemstack1 = p_77572_1_.getStackInSlot(i);
				if (itemstack1 != null && itemstack1.hasTagCompound())
				{
					itemstack.setTagCompound((NBTTagCompound) itemstack1.stackTagCompound.copy());
				}
			}
		}
		return itemstack;
	}

	@Override
	public int getRecipeSize()
	{
		return recipeWidth * recipeHeight;
	}

	public EBShapedRecipes enableSomething()
	{
		something = true;
		return this;
	}
}