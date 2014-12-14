package main.extremeblocks.crafting;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import main.extremeblocks.blocks.BlockEBTable;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class EBShapelessRecipes implements IRecipe
{
	private final ItemStack recipeOutput;
	public final List<ItemStack> recipeItems;

	public EBShapelessRecipes(ItemStack p_i1918_1_, List<ItemStack> p_i1918_2_)
	{
		recipeOutput = p_i1918_1_;
		recipeItems = p_i1918_2_;
	}

	@Override
	public ItemStack getRecipeOutput()
	{
		return recipeOutput;
	}

	/**
	 * Used to check if a recipe matches current crafting inventory
	 */
	@Override
	public boolean matches(InventoryCrafting p_77569_1_, World p_77569_2_)
	{
		ArrayList<ItemStack> arraylist = new ArrayList<ItemStack>(recipeItems);
		for (int i = 0; i < BlockEBTable.CRAFTING_SLOTS; ++i)
		{
			for (int j = 0; j < BlockEBTable.CRAFTING_SLOTS; ++j)
			{
				ItemStack itemstack = p_77569_1_.getStackInRowAndColumn(j, i);
				if (itemstack != null)
				{
					boolean flag = false;
					Iterator<ItemStack> iterator = arraylist.iterator();
					while (iterator.hasNext())
					{
						ItemStack itemstack1 = iterator.next();
						if (itemstack.getItem() == itemstack1.getItem() && (itemstack1.getItemDamage() == 32767 || itemstack.getItemDamage() == itemstack1.getItemDamage()))
						{
							flag = true;
							arraylist.remove(itemstack1);
							break;
						}
					}
					if (!flag) return false;
				}
			}
		}
		return arraylist.isEmpty();
	}

	/**
	 * Returns an Item that is the result of this recipe
	 */
	@Override
	public ItemStack getCraftingResult(InventoryCrafting p_77572_1_)
	{
		return recipeOutput.copy();
	}

	/**
	 * Returns the size of the recipe area
	 */
	@Override
	public int getRecipeSize()
	{
		return recipeItems.size();
	}
}