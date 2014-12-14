package main.extremeblocks.tileentities;

import main.com.hk.eb.util.StackHelper;
import main.extremeblocks.misc.IConnector;
import main.extremeblocks.misc.Power.IPowerEmitter;
import main.extremeblocks.misc.PowerHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityGenerator extends TileEntityInventory implements IPowerEmitter, IConnector
{
	public int furnaceBurnTime;
	public int currentItemBurnTime;

	public TileEntityGenerator()
	{
		super("Generator");
		inventory = new ItemStack[1];
	}

	public boolean isPowered()
	{
		return worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);
	}

	@Override
	public void updateEntity()
	{
		boolean save = false;

		if (isPowered() && furnaceBurnTime == 0)
		{
			currentItemBurnTime = furnaceBurnTime = TileEntityFurnace.getItemBurnTime(getPayment());

			if (getPayment() != null && TileEntityFurnace.isItemFuel(getPayment()))
			{
				inventory[0] = StackHelper.consumeItem(getPayment());
				save = true;
			}
		}

		if (save)
		{
			markDirty();
		}
	}

	public ItemStack getPayment()
	{
		return inventory[0];
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setInteger("Burn Time", furnaceBurnTime);
		nbt.setInteger("Current Burn Time", currentItemBurnTime);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		furnaceBurnTime = nbt.getInteger("Burn Time");
		currentItemBurnTime = nbt.getInteger("Current Burn Time");
	}

	@Override
	public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack)
	{
		return par1 == 0 ? TileEntityFurnace.isItemFuel(par2ItemStack) : true;
	}

	@SideOnly(Side.CLIENT)
	public int getBurnTimeRemainingScaled(int scale)
	{
		if (currentItemBurnTime == 0)
		{
			currentItemBurnTime = 200;
		}

		return furnaceBurnTime * scale / currentItemBurnTime;
	}

	public boolean isBurning()
	{
		return furnaceBurnTime > 0;
	}

	@Override
	public boolean canSendPowerThrough(ForgeDirection side)
	{
		int meta = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
		if (side == ForgeDirection.DOWN || side == ForgeDirection.UP || side == ForgeDirection.UNKNOWN) return false;
		switch (meta)
		{
			case 0:
			case 2:
			{
				return side == ForgeDirection.EAST || side == ForgeDirection.WEST;
			}
			case 1:
			case 3:
			{
				return side == ForgeDirection.NORTH || side == ForgeDirection.SOUTH;
			}
		}
		return false;
	}

	@Override
	public float getPower()
	{
		return worldObj != null && isPowered() && isBurning() ? 1.0F : 0;
	}

	@Override
	public void takenPowerFrom(float power)
	{
		furnaceBurnTime -= power;
		if (furnaceBurnTime < 0)
		{
			furnaceBurnTime = 0;
		}
		markDirty();
	}

	@Override
	public boolean canConnect(World world, int x, int y, int z)
	{
		return canSendPowerThrough(PowerHelper.getSideAt(worldObj, this, x, y, z).getOpposite());
	}
}