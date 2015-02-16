package main.extremeblocks.util;

import main.com.hk.eb.util.RecipeUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class DecodedRecipe
{
	private ItemStack result;
	private ItemStack[] recipeItems;
	private int width, height;
	private boolean is2x2;

	public DecodedRecipe(ItemStack stack)
	{
		IRecipe rec = RecipeUtils.getRecipeFor(stack);
		result = stack;
		if (rec instanceof ShapedOreRecipe)
		{
			ShapedOreRecipe oreRecipe = (ShapedOreRecipe) rec;
			is2x2 = RecipeUtils.is2x2(oreRecipe);
			width = RecipeUtils.getRecipeWidth(oreRecipe);
			height = RecipeUtils.getRecipeHeight(oreRecipe);
			recipeItems = RecipeUtils.getFirstRecipeForItem(result);
		}
		else if (rec instanceof ShapelessOreRecipe)
		{
			ShapelessOreRecipe oreRecipe = (ShapelessOreRecipe) rec;
			is2x2 = oreRecipe.getRecipeSize() <= 4;
			width = height = oreRecipe.getRecipeSize() / (is2x2 ? 2 : 3);
			recipeItems = RecipeUtils.convertToStacks(oreRecipe.getInput().toArray(new Object[0]));
		}
	}

	public final ItemStack getResult()
	{
		return result;
	}

	public final ItemStack[] getRecipeItems()
	{
		return recipeItems;
	}

	public final int getWidth()
	{
		return width;
	}

	public final int getHeight()
	{
		return height;
	}

	public final boolean isIs2x2()
	{
		return is2x2;
	}

	public final void setResult(ItemStack result)
	{
		this.result = result;
	}

	public final void setRecipeItems(ItemStack[] recipeItems)
	{
		this.recipeItems = recipeItems;
	}

	public final void setWidth(int width)
	{
		this.width = width;
	}

	public final void setHeight(int height)
	{
		this.height = height;
	}

	public final void setIs2x2(boolean is2x2)
	{
		this.is2x2 = is2x2;
	}
}
