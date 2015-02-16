package main.extremeblocks.items;

import java.util.ArrayList;
import main.com.hk.eb.util.JavaHelp;
import main.extremeblocks.Init;
import main.extremeblocks.util.SortingSystem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
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
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int sideHit, float sideX, float sideY, float sizeZ)
	{
		TileEntity te = world.getTileEntity(x, y, z);

		if (te instanceof IInventory)
		{
			IInventory tile = (IInventory) te;
			ArrayList<ItemStack> inv = JavaHelp.newArrayList();
			addToList(tile, inv);
			sorter.handleInv(tile, inv.toArray(new ItemStack[0]));
		}
		return true;
	}

	public boolean addToList(IInventory inv, ArrayList<ItemStack> list)
	{
		if (inv != null)
		{
			for (int i = 0; i < inv.getSizeInventory(); i++)
			{
				list.add(inv.getStackInSlot(i));
				inv.setInventorySlotContents(i, null);
			}
			return true;
		}
		return false;
	}

	public boolean hasSecondChest(TileEntityChest chest)
	{
		return getAdjacentChest(chest) != null;
	}

	public TileEntityChest getAdjacentChest(TileEntityChest chest)
	{
		chest.checkForAdjacentChests();
		if (chest.adjacentChestXNeg != null) return chest.adjacentChestXNeg;
		if (chest.adjacentChestZNeg != null) return chest.adjacentChestZNeg;
		if (chest.adjacentChestXPos != null) return chest.adjacentChestXPos;
		if (chest.adjacentChestZPos != null) return chest.adjacentChestZPos;
		return null;
	}

	public Object[] getRecipe()
	{
		return sorter.recipe;
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
}
