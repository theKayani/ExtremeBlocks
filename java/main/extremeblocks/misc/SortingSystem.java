package main.extremeblocks.misc;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import main.com.hk.eb.util.Rand;
import main.com.hk.eb.util.StackHelper;
import main.extremeblocks.Init;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

public enum SortingSystem
{
	ALPHABETIZED(new ISortingMechanism()
	{
		@Override
		public void handleInv(IInventory tile, ItemStack[] inv)
		{
			Collections.sort(Arrays.asList(inv), new Comparator<ItemStack>()
			{
				@Override
				public int compare(ItemStack o1, ItemStack o2)
				{
					if (o1 == null) return -1;
					if (o2 == null) return 1;
					return o1.getDisplayName().compareTo(o2.getDisplayName());
				}
			});
			StackHelper.addToInv(tile, inv);
		}
	}, new ItemStack(Items.book)),
	SHUFFLED(new ISortingMechanism()
	{
		@Override
		public void handleInv(IInventory tile, ItemStack[] inv)
		{
			Collections.shuffle(Arrays.asList(inv));
			for (int i = 0; i < inv.length; i++)
			{
				ItemStack stack2 = inv[i];
				int j = Rand.nextInt(tile.getSizeInventory());
				if (tile.getStackInSlot(j) == null)
				{
					tile.setInventorySlotContents(j, stack2);
					continue;
				}
				i--;
			}
		}
	}, new ItemStack(Items.slime_ball)),
	RARITY(new ISortingMechanism()
	{
		@Override
		public void handleInv(IInventory tile, ItemStack[] inv)
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
			StackHelper.addToInv(tile, inv);
		}
	}, new ItemStack(Items.gold_ingot)),
	ITEMS(new ISortingMechanism()
	{
		@Override
		public void handleInv(IInventory tile, ItemStack[] inv)
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
			StackHelper.addToInv(tile, inv);
		}
	}, new ItemStack(Blocks.stone)),
	DURABILITY(new ISortingMechanism()
	{
		@Override
		public void handleInv(IInventory tile, ItemStack[] inv)
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
			StackHelper.addToInv(tile, inv);
		}
	}, new ItemStack(Items.shears)),
	TOOLS(new ISortingMechanism()
	{
		@Override
		public void handleInv(IInventory tile, ItemStack[] inv)
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
			StackHelper.addToInv(tile, inv);
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

	public void handleInv(IInventory tile, ItemStack[] inv)
	{
		sorter.handleInv(tile, inv);
	}

	public static interface ISortingMechanism
	{
		public void handleInv(IInventory tile, ItemStack[] inv);
	}
}