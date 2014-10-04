package main.extremeblocks.items;

import java.util.ArrayList;
import main.com.hk.eb.util.JavaHelp;
import main.com.hk.eb.util.Rand;
import main.com.hk.eb.util.StackHelper;
import main.extremeblocks.Init;
import main.extremeblocks.util.SortingSystem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ItemSorter extends Item
{
	public final SortingSystem sorter;

	public ItemSorter(SortingSystem sorter)
	{
		setCreativeTab(Init.tab_mainItems);
		setUnlocalizedName(sorter.name + " Sorter");
		setTextureName(Init.MODID + ":" + sorter.name.toLowerCase() + "_sorter");
		this.sorter = sorter;
	}

	@Override
	public String getUnlocalizedName()
	{
		return sorter.name + " Sorter";
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack)
	{
		return sorter.name + " Sorter";
	}

	@Override
	public String getItemStackDisplayName(ItemStack par1ItemStack)
	{
		return sorter.name + " Sorter";
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int sideHit, float sideX, float sideY, float sizeZ)
	{
		TileEntity te = world.getTileEntity(x, y, z);

		if (te instanceof IInventory)
		{
			IInventory tile = (IInventory) te;
			ArrayList<ItemStack> inv = JavaHelp.newArrayList();

			for (int i = 0; i < tile.getSizeInventory(); i++)
			{
				if (tile.getStackInSlot(i) != null)
				{
					inv.add(tile.getStackInSlot(i));
					tile.setInventorySlotContents(i, null);
				}
			}
			ItemStack[] stacks = inv.toArray(new ItemStack[0]);
			if (sorter.sort(stacks))
			{
				StackHelper.addToInv(tile, stacks);
			}
			else
			{
				for (int i = 0; i < stacks.length; i++)
				{
					ItemStack stack2 = stacks[i];
					int j = Rand.nextInt(tile.getSizeInventory());
					if (tile.getStackInSlot(j) == null)
					{
						tile.setInventorySlotContents(j, stack2);
						continue;
					}
					i--;
				}
			}
		}
		return true;
	}

	public Object[] getRecipe()
	{
		return sorter.recipe;
	}
}
