package main.extremeblocks;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import main.com.hk.eb.util.IInfo;
import main.com.hk.eb.util.JavaHelp;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraftforge.common.util.Constants;
import cpw.mods.fml.common.registry.GameRegistry;

public class Guide
{
	public final static List<IInfo> infos;
	public final static int size;

	public static void writeBook(String title, String author, String[] lines, Object... recipe)
	{
		ItemStack stack = new ItemStack(Items.written_book);
		if (stack.stackTagCompound == null)
		{
			stack.setTagCompound(getBookCompound(title, author, lines));
		}

		if (recipe != null && recipe.length > 0)
		{
			GameRegistry.addRecipe(stack, recipe);
		}
	}

	public static NBTTagCompound getBookCompound(String title, String author, String[] lines)
	{
		NBTTagCompound tag = new NBTTagCompound();
		tag.setTag("author", new NBTTagString(author));
		tag.setTag("title", new NBTTagString(title));
		tag.setTag("pages", new NBTTagList());
		NBTTagList pagesTag = tag.getTagList("pages", Constants.NBT.TAG_LIST);

		for (String line : lines)
		{
			pagesTag.appendTag(new NBTTagString(line));
		}
		return tag;
	}

	static
	{
		infos = JavaHelp.newArrayList();
		size = setup();
	}

	private static int setup()
	{
		int s = 0;
		List<Object> objs = JavaHelp.newArrayList();
		objs.addAll(ExtremeBlocks.items);
		objs.addAll(ExtremeBlocks.blocks);
		Collections.sort(objs, new Comparator<Object>()
		{
			@Override
			public int compare(Object o1, Object o2)
			{
				String s1 = o1 instanceof Block ? ((Block) o1).getLocalizedName() : o1 instanceof Item ? ((Item) o1).getUnlocalizedName() : "";
				String s2 = o2 instanceof Block ? ((Block) o2).getLocalizedName() : o2 instanceof Item ? ((Item) o2).getUnlocalizedName() : "";
				return s1.compareToIgnoreCase(s2);
			}

		});

		for (int i = 0; i < objs.size(); i++)
		{
			Object obj = objs.get(i);
			if (obj instanceof IInfo && ((IInfo) obj).getElements().isUnique)
			{
				infos.add((IInfo) obj);
				s++;
			}
		}

		return s;
	}
}
