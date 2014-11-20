package main.extremeblocks.tileentities;

import main.extremeblocks.blocks.abstractblocks.BlockStorage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class TileEntityStorage extends TileEntity implements IInventory
{
	private int storageSlots, xSize, ySize;
	private ItemStack[] items;
	private String texturePath;
	protected String customName;

	public TileEntityStorage()
	{
	}

	public TileEntityStorage(int storageSlots, String texturePath, String customName, int xSize, int ySize)
	{
		this.storageSlots = storageSlots;
		items = new ItemStack[this.storageSlots];
		this.texturePath = texturePath;
		this.customName = customName;
		this.xSize = xSize;
		this.ySize = ySize;
	}

	/**
	 * Returns the number of slots in the inventory.
	 */
	@Override
	public int getSizeInventory()
	{
		return storageSlots;
	}

	/**
	 * Returns the stack in slot i
	 */
	@Override
	public ItemStack getStackInSlot(int par1)
	{
		return items[par1];
	}

	public ItemStack[] getItems()
	{
		return items;
	}

	/**
	 * Removes from an inventory slot (first arg) up to a specified number
	 * (second arg) of items and returns them in a new stack.
	 */
	@Override
	public ItemStack decrStackSize(int par1, int par2)
	{
		if (items[par1] != null)
		{
			ItemStack itemstack;
			if (items[par1].stackSize <= par2)
			{
				itemstack = items[par1];
				items[par1] = null;
				markDirty();
				return itemstack;
			}
			else
			{
				itemstack = items[par1].splitStack(par2);
				if (items[par1].stackSize == 0)
				{
					items[par1] = null;
				}
				markDirty();
				return itemstack;
			}
		}
		else return null;
	}

	/**
	 * When some containers are closed they call this on each slot, then drop
	 * whatever it returns as an EntityItem - like when you close a workbench
	 * GUI.
	 */
	@Override
	public ItemStack getStackInSlotOnClosing(int par1)
	{
		if (items[par1] != null)
		{
			ItemStack itemstack = items[par1];
			items[par1] = null;
			return itemstack;
		}
		else return null;
	}

	/**
	 * Sets the given item stack to the specified slot in the inventory (can be
	 * crafting or armor sections).
	 */
	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
	{
		items[par1] = par2ItemStack;
		if (par2ItemStack != null && par2ItemStack.stackSize > getInventoryStackLimit())
		{
			par2ItemStack.stackSize = getInventoryStackLimit();
		}
		markDirty();
	}

	/**
	 * Add item stack in first available inventory slot
	 */
	public int addItem(ItemStack par1ItemStack)
	{
		for (int i = 0; i < items.length; ++i)
		{
			if (items[i] == null)
			{
				setInventorySlotContents(i, par1ItemStack);
				return i;
			}
		}
		return -1;
	}

	/**
	 * If this returns false, the inventory name will be used as an unlocalized
	 * name, and translated into the player's language. Otherwise it will be
	 * used directly.
	 */
	public boolean isInvNameLocalized()
	{
		return customName != null;
	}

	/**
	 * Reads a tile entity from NBT.
	 */
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		xSize = nbt.getInteger("X Size");
		ySize = nbt.getInteger("Y Size");
		storageSlots = nbt.getInteger("Storage Slots");
		texturePath = nbt.getString("Texture Path");
		NBTTagList nbttaglist = (NBTTagList) nbt.getTag("Items");
		items = new ItemStack[getSizeInventory()];
		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			int j = nbttagcompound1.getByte("Slot") & 255;
			if (j >= 0 && j < items.length)
			{
				items[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}
		if (nbt.hasKey("CustomName"))
		{
			customName = nbt.getString("CustomName");
		}
	}

	/**
	 * Writes a tile entity to NBT.
	 */
	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setInteger("X Size", xSize);
		par1NBTTagCompound.setInteger("Y Size", ySize);
		par1NBTTagCompound.setInteger("Storage Slots", storageSlots);
		par1NBTTagCompound.setString("Texture Path", texturePath);
		NBTTagList nbttaglist = new NBTTagList();
		for (int i = 0; i < items.length; ++i)
		{
			if (items[i] != null)
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				items[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}
		par1NBTTagCompound.setTag("Items", nbttaglist);
		if (isInvNameLocalized())
		{
			par1NBTTagCompound.setString("CustomName", customName);
		}
	}

	/**
	 * Returns the maximum stack size for a inventory slot. Seems to always be
	 * 64, possibly will be extended. *Isn't this more of a set than a get?*
	 */
	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	/**
	 * Do not make give this method the name canInteractWith because it clashes
	 * with Container
	 */
	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
	{
		return worldObj.getTileEntity(xCoord, yCoord, zCoord) != this ? false : par1EntityPlayer.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D) <= 64.0D;
	}

	/**
	 * Returns true if automation is allowed to insert the given stack (ignoring
	 * stack size) into the given slot.
	 */
	@Override
	public boolean isItemValidForSlot(int par1, ItemStack is)
	{
		return true;
	}

	@Override
	public String getInventoryName()
	{
		return customName;
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return true;
	}

	@Override
	public void openInventory()
	{
	}

	@Override
	public void closeInventory()
	{
	}

	public final ResourceLocation getTexture()
	{
		return new ResourceLocation(texturePath);
	}

	public Slot[] addSlotsToContainer()
	{
		return ((BlockStorage) worldObj.getBlock(xCoord, yCoord, zCoord)).addSlotsToContainer(this);
	}

	public int getXSize()
	{
		return xSize;
	}

	public int getYSize()
	{
		return ySize;
	}
}
