package main.com.hk.eb.util;

import java.lang.reflect.Field;
import java.util.List;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class RecipeUtils
{
	@SuppressWarnings("unchecked")
	public static List<IRecipe> getVanillaRecipes()
	{
		return CraftingManager.getInstance().getRecipeList();
	}

	public static ItemStack[] getFirstRecipeForItem(ItemStack resultingItem)
	{
		ItemStack[] recipeItems = null;
		for (IRecipe recipe : getVanillaRecipes())
		{
			if (recipe == null)
			{
				continue;
			}

			ItemStack result = recipe.getRecipeOutput();
			if (result == null || !result.isItemEqual(resultingItem) || !StackHelper.areStacksSameTypeCrafting(result, resultingItem))
			{
				continue;
			}

			Object[] input = getRecipeInput(recipe);
			if (input != null)
			{
				recipeItems = convertToStacks(input);
			}

		}
		return recipeItems;
	}

	public static ItemStack[] getFirstRecipeForItem(Item item)
	{
		for (int i = 0; i < item.getItemStackLimit(new ItemStack(item)); i++)
		{
			for (int j = 0; j < item.getMaxDamage(); j++)
			{
				ItemStack[] stacks = getFirstRecipeForItem(new ItemStack(item, i, j));
				if (StackHelper.isEmpty(stacks))
				{
					continue;
				}
				return stacks;
			}
		}
		return null;
	}

	public static IRecipe getRecipeFor(ItemStack stack)
	{
		for (IRecipe recipe : getVanillaRecipes())
		{
			if (recipe != null)
			{
				if (recipe.getRecipeOutput() != null && StackHelper.areStacksSameTypeCrafting(stack, recipe.getRecipeOutput())) return recipe;
			}
		}
		return null;
	}

	public static ItemStack[] convertToStacks(Object[] input)
	{
		ItemStack[] result = new ItemStack[input.length];
		for (int i = 0; i < input.length; i++)
		{
			result[i] = convertToStack(input[i]);
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public static ItemStack convertToStack(Object obj)
	{
		ItemStack entry = null;

		if (obj instanceof ItemStack)
		{
			entry = (ItemStack) obj;
		}
		else if (obj instanceof List)
		{
			List<ItemStack> list = (List<ItemStack>) obj;
			entry = list.get(Rand.nextInt(list.size()));
		}

		if (entry == null) return null;
		entry = entry.copy();
		if (entry.getItemDamage() == OreDictionary.WILDCARD_VALUE)
		{
			entry.setItemDamage(0);
		}
		return entry;
	}

	@SuppressWarnings("unchecked")
	public static Object[] getRecipeInput(IRecipe recipe)
	{
		if (recipe instanceof ShapelessOreRecipe) return ((ShapelessOreRecipe) recipe).getInput().toArray();
		else if (recipe instanceof ShapedOreRecipe) return getShapedOreRecipe((ShapedOreRecipe) recipe);
		else if (recipe instanceof ShapedRecipes) return ((ShapedRecipes) recipe).recipeItems;
		else if (recipe instanceof ShapelessRecipes) return ((ShapelessRecipes) recipe).recipeItems.toArray(new ItemStack[0]);
		return null;
	}

	private static Object[] getShapedOreRecipe(ShapedOreRecipe recipe)
	{
		final int width;
		try
		{
			width = getRecipeWidth(recipe);
		}
		catch (Exception e)
		{
			System.err.println("Failed to get input information from " + recipe);
			return null;
		}

		Object[] input = recipe.getInput();
		int inputIndex = 0;
		Object[] grid = new Object[9];
		for (int y = 0; y < 3; y++)
		{
			for (int x = 0; x < 3; x++)
			{
				final int outputIndex = y * 3 + x;
				if (x < width && inputIndex < input.length)
				{
					grid[outputIndex] = input[inputIndex];
					inputIndex++;
				}
				else
				{
					grid[outputIndex] = null;
				}
			}
		}
		return grid;
	}

	public static int getRecipeWidth(ShapedOreRecipe recipe)
	{
		try
		{
			if (shapedOreRecipeWidth == null)
			{

				shapedOreRecipeWidth = ShapedOreRecipe.class.getDeclaredField("width");

				shapedOreRecipeWidth.setAccessible(true);
			}
			return shapedOreRecipeWidth.getInt(recipe);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}

	public static int getRecipeHeight(ShapedOreRecipe recipe)
	{
		try
		{
			if (shapedOreRecipeHeight == null)
			{
				shapedOreRecipeHeight = ShapedOreRecipe.class.getDeclaredField("height");
				shapedOreRecipeHeight.setAccessible(true);
			}
			return shapedOreRecipeHeight.getInt(recipe);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}

	public static boolean is2x2(ShapedOreRecipe recipe)
	{
		try
		{
			return getRecipeHeight(recipe) < 3 && getRecipeWidth(recipe) < 3;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	private static Field shapedOreRecipeWidth;
	private static Field shapedOreRecipeHeight;
}