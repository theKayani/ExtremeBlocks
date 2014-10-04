package main.extremeblocks.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import main.extremeblocks.Init;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

public enum SortingSystem
{
	ALPHABETIZED(new ISortingMechanism()
	{
		@Override
		public boolean sortInventory(ItemStack[] inv)
		{
			Collections.sort(Arrays.asList(inv), new Comparator<ItemStack>()
			{
				@Override
				public int compare(ItemStack o1, ItemStack o2)
				{
					if (o1 == null || o2 == null) return 0;
					return o1.getDisplayName().compareTo(o2.getDisplayName());
				}
			});
			return true;
		}
	}, new ItemStack(Items.book)), SHUFFLED(new ISortingMechanism()
	{
		@Override
		public boolean sortInventory(ItemStack[] inv)
		{
			Collections.shuffle(Arrays.asList(inv));
			return false;
		}
	}, new ItemStack(Items.slime_ball)), RARITY(new ISortingMechanism()
	{
		@Override
		public boolean sortInventory(ItemStack[] inv)
		{
			Collections.sort(Arrays.asList(inv), new Comparator<ItemStack>()
			{
				@Override
				public int compare(ItemStack o1, ItemStack o2)
				{
					if (o1 == null || o2 == null) return 0;
					return o2.getRarity().ordinal() - o1.getRarity().ordinal();
				}
			});
			return true;
		}
	}, new ItemStack(Items.gold_ingot)), ITEMS(new ISortingMechanism()
	{
		@Override
		public boolean sortInventory(ItemStack[] inv)
		{
			Collections.sort(Arrays.asList(inv), new Comparator<ItemStack>()
			{
				@Override
				public int compare(ItemStack o1, ItemStack o2)
				{
					if (o1 == null || o2 == null) return 0;
					int a = o1.getItem() instanceof ItemBlock ? -1 : 1;
					int b = o2.getItem() instanceof ItemBlock ? -1 : 1;
					return a - b;
				}
			});
			return true;
		}
	}, new ItemStack(Blocks.stone)), DURABILITY(new ISortingMechanism()
	{
		@Override
		public boolean sortInventory(ItemStack[] inv)
		{
			Collections.sort(Arrays.asList(inv), new Comparator<ItemStack>()
			{
				@Override
				public int compare(ItemStack o1, ItemStack o2)
				{
					if (o1 == null || o2 == null) return 0;
					int a = (int) (o1.getItem().isItemTool(o1) ? o1.getItem().getDurabilityForDisplay(o1) * 10 : Integer.MIN_VALUE);
					int b = (int) (o2.getItem().isItemTool(o2) ? o2.getItem().getDurabilityForDisplay(o2) * 10 : Integer.MIN_VALUE);
					return a - b;
				}
			});
			return true;
		}
	}, new ItemStack(Items.shears)), TOOLS(new ISortingMechanism()
	{
		@Override
		public boolean sortInventory(ItemStack[] inv)
		{
			Collections.sort(Arrays.asList(inv), new Comparator<ItemStack>()
			{
				@Override
				public int compare(ItemStack o1, ItemStack o2)
				{
					if (o1 == null || o2 == null) return 0;
					int a = o1.getItem() instanceof ItemTool ? ((ItemTool) o1.getItem()).func_150913_i().getHarvestLevel() : Integer.MIN_VALUE;
					int b = o2.getItem() instanceof ItemTool ? ((ItemTool) o2.getItem()).func_150913_i().getHarvestLevel() : Integer.MIN_VALUE;
					return a - b;
				}
			});
			return true;
		}
	}, new ItemStack(Items.blaze_rod));

	private final ISortingMechanism sorter;
	public final String name;
	public final Object[] recipe;

	private SortingSystem(ISortingMechanism sorter, ItemStack item)
	{
		name = name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase();
		this.sorter = sorter;
		recipe = new Object[] { " R ", "ICI", " R ", 'R', Items.redstone, 'I', item, 'C', Init.sorter_component };
	}

	public boolean sort(ItemStack[] inv)
	{
		return sorter.sortInventory(inv);
	}

	public static interface ISortingMechanism
	{
		public boolean sortInventory(ItemStack[] inv);
	}
}