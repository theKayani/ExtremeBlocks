package main.extremeblocks;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraftforge.common.util.Constants;
import cpw.mods.fml.common.registry.GameRegistry;

public class Tets
{
	public static void writeBook(String title, String author, String[] lines, Object... recipe)
	{
		ItemStack stack = new ItemStack(Items.written_book);
		if (stack.stackTagCompound == null)
		{
			stack.setTagCompound(new NBTTagCompound());
		}
		NBTTagList bookPages = new NBTTagList();
		stack.setTagInfo("author", new NBTTagString(author));
		stack.setTagInfo("title", new NBTTagString(title));
		stack.stackTagCompound.setTag("pages", new NBTTagList());
		NBTTagList pagesTag = stack.stackTagCompound.getTagList("pages", Constants.NBT.TAG_LIST);

		for (String line : lines)
		{
			pagesTag.appendTag(new NBTTagString(line));
		}

		if (recipe != null && recipe.length > 0)
		{
			GameRegistry.addRecipe(stack, recipe);
		}
	}
}
