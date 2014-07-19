package main.extremeblocks.blocks.tileentities;

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
	private int storageSlots;
	private ItemStack[] items;
	private String texturePath;
	private BlockStorage block;
	protected String customName;

	public TileEntityStorage()
	{}

	public TileEntityStorage(BlockStorage block, int storageSlots, String texturePath, String customName)
	{
		this.block = block;
		this.storageSlots = storageSlots;
		this.items = new ItemStack[this.storageSlots];
		this.texturePath = texturePath;
		this.customName = customName;
	}

	/**
	 * Returns the number of slots in the inventory.
	 */
	public int getSizeInventory()
	{
		return this.storageSlots;
	}

	/**
	 * Returns the stack in slot i
	 */
	public ItemStack getStackInSlot(int par1)
	{
		return this.items[par1];
	}

	public ItemStack[] getItems()
	{
		return items;
	}

	/**
	 * Removes from an inventory slot (first arg) up to a specified number
	 * (second arg) of items and returns them in a new stack.
	 */
	public ItemStack decrStackSize(int par1, int par2)
	{
		if (this.items[par1] != null)
		{
			ItemStack itemstack;

			if (this.items[par1].stackSize <= par2)
			{
				itemstack = this.items[par1];
				this.items[par1] = null;
				this.markDirty();
				return itemstack;
			}
			else
			{
				itemstack = this.items[par1].splitStack(par2);

				if (this.items[par1].stackSize == 0)
				{
					this.items[par1] = null;
				}

				this.markDirty();
				return itemstack;
			}
		}
		else
		{
			return null;
		}
	}

	/**
	 * When some containers are closed they call this on each slot, then drop
	 * whatever it returns as an EntityItem - like when you close a workbench
	 * GUI.
	 */
	public ItemStack getStackInSlotOnClosing(int par1)
	{
		if (this.items[par1] != null)
		{
			ItemStack itemstack = this.items[par1];
			this.items[par1] = null;
			return itemstack;
		}
		else
		{
			return null;
		}
	}

	/**
	 * Sets the given item stack to the specified slot in the inventory (can be
	 * crafting or armor sections).
	 */
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
	{
		this.items[par1] = par2ItemStack;

		if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
		{
			par2ItemStack.stackSize = this.getInventoryStackLimit();
		}

		this.markDirty();
	}

	/**
	 * Add item stack in first available inventory slot
	 */
	public int addItem(ItemStack par1ItemStack)
	{
		for (int i = 0; i < this.items.length; ++i)
		{
			if (this.items[i] == null)
			{
				this.setInventorySlotContents(i, par1ItemStack);
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
		return this.customName != null;
	}

	/**
	 * Reads a tile entity from NBT.
	 */
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readFromNBT(par1NBTTagCompound);

		this.block = BlockStorage.getByID(par1NBTTagCompound.getInteger("Storage Block"));
		this.storageSlots = par1NBTTagCompound.getInteger("Storage Slots");
		this.texturePath = par1NBTTagCompound.getString("Texture Path");

		NBTTagList nbttaglist = (NBTTagList) par1NBTTagCompound.getTag("Items");
		this.items = new ItemStack[this.getSizeInventory()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			int j = nbttagcompound1.getByte("Slot") & 255;

			if (j >= 0 && j < this.items.length)
			{
				this.items[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}

		if (par1NBTTagCompound.hasKey("CustomName"))
		{
			this.customName = par1NBTTagCompound.getString("CustomName");
		}
	}

	/**
	 * Writes a tile entity to NBT.
	 */
	public void writeToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeToNBT(par1NBTTagCompound);

		par1NBTTagCompound.setInteger("Storage Block", block.id);
		par1NBTTagCompound.setInteger("Storage Slots", this.storageSlots);
		par1NBTTagCompound.setString("Texture Path", this.texturePath);

		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.items.length; ++i)
		{
			if (this.items[i] != null)
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.items[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		par1NBTTagCompound.setTag("Items", nbttaglist);

		if (this.isInvNameLocalized())
		{
			par1NBTTagCompound.setString("CustomName", this.customName);
		}
	}

	/**
	 * Returns the maximum stack size for a inventory slot. Seems to always be
	 * 64, possibly will be extended. *Isn't this more of a set than a get?*
	 */
	public int getInventoryStackLimit()
	{
		return 64;
	}

	/**
	 * Do not make give this method the name canInteractWith because it clashes
	 * with Container
	 */
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
	{
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D, (double) this.zCoord + 0.5D) <= 64.0D;
	}

	/**
	 * Returns true if automation is allowed to insert the given stack (ignoring
	 * stack size) into the given slot.
	 */
	public boolean isItemValidForSlot(int par1, ItemStack is)
	{
		return true;
	}

	@Override
	public String getInventoryName()
	{
		return this.customName;
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return true;
	}

	@Override
	public void openInventory()
	{}

	@Override
	public void closeInventory()
	{}

	public final ResourceLocation getTexture()
	{
		return new ResourceLocation(texturePath);
	}

	public Slot[] addSlotsToContainer()
	{
		return block.addSlotsToContainer(this);// slots.toArray(new Slot[0]);
	}
}
