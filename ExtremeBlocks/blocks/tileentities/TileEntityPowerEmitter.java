package main.extremeblocks.blocks.tileentities;

import java.util.Arrays;
import main.com.hk.testing.util.MPUtil;
import main.extremeblocks.util.IPower.IPowerEmitter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityPowerEmitter extends TileEntity implements IPowerEmitter, IInventory
{
	public int furnaceBurnTime;
	public int currentItemBurnTime;
	public ItemStack payment;
	protected String customName;

	public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
	{
		this.payment = par2ItemStack;

		if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
		{
			par2ItemStack.stackSize = this.getInventoryStackLimit();
		}

		this.markDirty();
	}

	public ItemStack getStackInSlot(int par1)
	{
		return par1 == 0 ? this.payment : null;
	}

	public ItemStack decrStackSize(int par1, int par2)
	{
		if (this.payment != null)
		{
			ItemStack itemstack;

			if (this.payment.stackSize <= par2)
			{
				itemstack = this.payment;
				this.payment = null;
				this.markDirty();
				return itemstack;
			}
			else
			{
				itemstack = this.payment.splitStack(par2);
				if (this.payment.stackSize == 0) this.payment = null;
				this.markDirty();
				return itemstack;
			}
		}
		else
		{
			return null;
		}
	}

	public ItemStack getStackInSlotOnClosing(int par1)
	{
		if (this.payment != null)
		{
			ItemStack itemstack = this.payment;
			this.payment = null;
			return itemstack;
		}
		else
		{
			return null;
		}
	}

	@Override
	public int getPower(ForgeDirection sideSent)
	{
		return worldObj != null && isPowered() ? getPower() : 0;
	}

	private boolean isPowered()
	{
		return worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);
	}

	public int getSizeInventory()
	{
		return 1;
	}

	public void updateEntity()
	{
		boolean flag = furnaceBurnTime > 0 && isPowered();
		boolean flag1 = false;

		if (flag)
		{
			furnaceBurnTime -= 3;
		}

		if (MPUtil.isServerSide())
		{
			if (furnaceBurnTime == 0)
			{
				currentItemBurnTime = furnaceBurnTime = TileEntityFurnace.getItemBurnTime(payment);

				if (furnaceBurnTime > 0)
				{
					flag1 = true;

					if (payment != null)
					{
						--payment.stackSize;

						if (payment.stackSize == 0)
						{
							payment = payment.getItem().getContainerItem(payment);
						}
					}
				}
			}

			flag1 = flag != furnaceBurnTime > 0;
		}

		if (flag1)
		{
			markDirty();
		}
	}

	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		NBTTagList nbttaglist = new NBTTagList();
		ItemStack[] stacks = new ItemStack[] {
			payment
		};

		nbt.setInteger("Size", 1);

		for (int i = 0; i < stacks.length; ++i)
		{
			if (stacks[i] != null)
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				stacks[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		nbt.setTag("Items", nbttaglist);

		furnaceBurnTime = nbt.getShort("Burn Time");
		currentItemBurnTime = TileEntityFurnace.getItemBurnTime(payment);

		if (hasCustomInventoryName())
		{
			nbt.setString("CustomName", customName);
		}
	}

	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		int size = nbt.getInteger("Size");
		ItemStack[] stacks = new ItemStack[size];
		NBTTagList nbttaglist = (NBTTagList) nbt.getTag("Items");

		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound lol = nbttaglist.getCompoundTagAt(i);
			int j = lol.getByte("Slot") & 255;
			if (j >= 0 && j < stacks.length) stacks[j] = ItemStack.loadItemStackFromNBT(lol);
		}

		payment = stacks[0];

		nbt.setShort("Burn Time", (short) furnaceBurnTime);

		if (nbt.hasKey("CustomName"))
		{
			customName = nbt.getString("CustomName");
		}
	}

	public int getInventoryStackLimit()
	{
		return 64;
	}

	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
	{
		return worldObj.getTileEntity(xCoord, yCoord, zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double) xCoord + 0.5D, (double) yCoord + 0.5D, (double) zCoord + 0.5D) <= 64.0D;
	}

	public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack)
	{
		return TileEntityFurnace.isItemFuel(par2ItemStack);
	}

	@Override
	public String getInventoryName()
	{
		return customName;
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return customName != null;
	}

	@Override
	public void openInventory()
	{
	}

	@Override
	public void closeInventory()
	{
	}

	public int getPower()
	{
		return TileEntityFurnace.getItemBurnTime(payment) / 100;// (currentItemBurnTime
																// *
																// itemstack.stackSize)
																// / 500 : 0;
	}

	@SideOnly(Side.CLIENT)
	public int getBurnTimeRemainingScaled(int p_145955_1_)
	{
		if (currentItemBurnTime == 0)
		{
			currentItemBurnTime = 200;
		}

		return furnaceBurnTime * p_145955_1_ / currentItemBurnTime;
	}

	public boolean isBurning()
	{
		return furnaceBurnTime > 0;
	}
}