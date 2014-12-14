package main.extremeblocks.crafting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import main.com.hk.eb.util.JavaHelp;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

public class RecipeManager
{
	public static final ArrayList<IRecipe> PIES = JavaHelp.newArrayList();
	public static final HashMap<ItemStack, List<ItemStack>> recipes = JavaHelp.newHashMap();

	public static EBShapedRecipes add(boolean shouldAdd, ItemStack stack, Object... obs)
	{
		String s = "";
		int i = 0;
		int j = 0;
		int k = 0;
		if (obs[i] instanceof String[])
		{
			String[] astring = (String[]) obs[i++];
			for (String s1 : astring)
			{
				++k;
				j = s1.length();
				s = s + s1;
			}
		}
		else
		{
			while (obs[i] instanceof String)
			{
				String s2 = (String) obs[i++];
				++k;
				j = s2.length();
				s = s + s2;
			}
		}
		HashMap<Character, ItemStack> hashmap;
		for (hashmap = JavaHelp.newHashMap(); i < obs.length; i += 2)
		{
			Character character = (Character) obs[i];
			ItemStack itemstack1 = null;
			if (obs[i + 1] instanceof Item)
			{
				itemstack1 = new ItemStack((Item) obs[i + 1]);
			}
			else if (obs[i + 1] instanceof Block)
			{
				itemstack1 = new ItemStack((Block) obs[i + 1], 1, 32767);
			}
			else if (obs[i + 1] instanceof ItemStack)
			{
				itemstack1 = (ItemStack) obs[i + 1];
			}
			hashmap.put(character, itemstack1);
		}
		ItemStack[] aitemstack = new ItemStack[j * k];
		for (int i1 = 0; i1 < j * k; ++i1)
		{
			char c0 = s.charAt(i1);
			if (hashmap.containsKey(Character.valueOf(c0)))
			{
				aitemstack[i1] = hashmap.get(Character.valueOf(c0)).copy();
			}
			else
			{
				aitemstack[i1] = null;
			}
		}
		EBShapedRecipes recipe = new EBShapedRecipes(j, k, aitemstack, stack);
		if (shouldAdd)
		{
			ArrayList<ItemStack> list = JavaHelp.newArrayList();
			list.addAll(Arrays.asList(aitemstack));
			PIES.add(recipe);
			recipes.put(recipe.getRecipeOutput(), list);
		}
		return recipe;
	}

	public static EBShapelessRecipes addShapeless(boolean shouldAdd, ItemStack stack, Object... obs)
	{
		ArrayList<ItemStack> arraylist = JavaHelp.newArrayList();
		Object[] aobject = obs;
		int i = obs.length;
		for (int j = 0; j < i; ++j)
		{
			Object object1 = aobject[j];
			if (object1 instanceof ItemStack)
			{
				arraylist.add(((ItemStack) object1).copy());
			}
			else if (object1 instanceof Item)
			{
				arraylist.add(new ItemStack((Item) object1));
			}
			else
			{
				if (!(object1 instanceof Block)) throw new RuntimeException("Invalid shapeless recipe!");
				arraylist.add(new ItemStack((Block) object1));
			}
		}
		EBShapelessRecipes recipe = new EBShapelessRecipes(stack, arraylist);
		if (shouldAdd)
		{
			PIES.add(recipe);
			recipes.put(stack.copy(), arraylist);
		}
		return recipe;
	}

	public static boolean canFitOnVanillaTable(ItemStack item, Object... obs)
	{
		boolean val = true;
		if (obs.length > 3 && obs[3] instanceof String)
		{
			val = false;
		}
		for (Object ob : obs)
		{
			if (ob instanceof String && ((String) ob).length() >= 4)
			{
				val = false;
			}
		}
		if (val == false)
		{
			int h = Calendar.getInstance().get(Calendar.HOUR);
			int m = Calendar.getInstance().get(Calendar.MINUTE);
			int s = Calendar.getInstance().get(Calendar.SECOND);
			System.err.println("[" + (h < 10 ? "0" + h : h) + ":" + (m < 10 ? "0" + m : m) + ":" + (s < 10 ? "0" + s : s) + "] [extreme blocks/ERROR] [ExtremeBlocks]: REMOVED '" + add(false, item, obs).getRecipeOutput().getDisplayName() + "' RECIPE!");
		}
		return val;
	}
}
