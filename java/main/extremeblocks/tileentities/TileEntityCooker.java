package main.extremeblocks.tileentities;

import main.extremeblocks.blocks.BlockCooker;
import main.extremeblocks.items.ItemFurnaceUpgrade;
import main.extremeblocks.misc.IConnector;
import main.extremeblocks.misc.PowerHelper;
import main.extremeblocks.misc.Power.IPowerReceiver;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityCooker extends TileEntityInventory implements IConnector, IPowerReceiver
{
	public int[] powerDelay;
	public String customName;
	public int overallPower, cookDelay, additionCounter, upgradeCount = 1;

	public TileEntityCooker()
	{
		inventory = new ItemStack[5];
		powerDelay = new int[2];
	}

	@Override
	public void updateEntity()
	{
		boolean flag = overallPower > 0;
		boolean flag1 = false;
		overallPower += PowerHelper.calculatePowerFromInventory(this, 70, 1, 2);

		if (inventory[4] != null && inventory[4].getItem() instanceof ItemFurnaceUpgrade && upgradeCount < 20)
		{
			upgradeCount++;
			inventory[4] = null;
		}
		if (canSmelt() && canUsePower())
		{
			cookDelay += upgradeCount;
			if (cookDelay >= 200)
			{
				overallPower--;
				cookDelay = 0;
				smeltItem();
				flag1 = true;
			}
		}
		else
		{
			cookDelay = 0;
		}
		if (flag != overallPower > 0)
		{
			flag1 = true;
			BlockCooker.updateCookerBlockState(overallPower > 0, worldObj, xCoord, yCoord, zCoord);
		}
		if (flag1)
		{
			markDirty();
		}
	}

	public boolean canUsePower()
	{
		return overallPower >= 1.0F;
	}

	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled(int p_145953_1_)
	{
		return cookDelay * p_145953_1_ / 200;
	}

	private ItemStack getSmeltee()
	{
		return inventory[2];
	}

	private ItemStack getResult()
	{
		return inventory[3];
	}

	private boolean canSmelt()
	{
		if (getSmeltee() != null && overallPower > 0)
		{
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(getSmeltee());
			if (itemstack == null) return false;
			if (getResult() == null) return true;
			if (!getResult().isItemEqual(itemstack)) return false;
			int result = getResult().stackSize + itemstack.stackSize;
			return result <= getInventoryStackLimit() && result <= getResult().getMaxStackSize();
		}
		return false;
	}

	public void smeltItem()
	{
		if (canSmelt())
		{
			ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(getSmeltee());

			if (getResult() == null)
			{
				inventory[3] = itemstack.copy();
			}
			else if (getResult().getItem() == itemstack.getItem())
			{
				getResult().stackSize += itemstack.stackSize;
			}

			getSmeltee().stackSize--;

			if (getSmeltee().stackSize <= 0)
			{
				inventory[2] = null;
			}
		}
	}

	@Override
	public String getInventoryName()
	{
		return hasCustomInventoryName() ? customName : "Placer";
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return customName != null;
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
	{
		return worldObj.getTileEntity(xCoord, yCoord, zCoord) != this ? false : par1EntityPlayer.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack)
	{
		return slot != 4;
	}

	@Override
	public void receivePower(ForgeDirection sideReceived, float power)
	{
		additionCounter += power;
		if (additionCounter >= 20)
		{
			overallPower++;
			additionCounter -= 20;
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);

		overallPower = nbt.getInteger("Overall Power");
		cookDelay = nbt.getShort("Cook Time");
		additionCounter = nbt.getShort("Addition Counter");
		upgradeCount = nbt.getInteger("Upgrade Count");

		if (nbt.hasKey("CustomName", 8))
		{
			customName = nbt.getString("CustomName");
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setShort("Cook Time", (short) cookDelay);
		nbt.setInteger("Overall Power", overallPower);
		nbt.setShort("Addition Counter", (short) additionCounter);
		nbt.setInteger("Upgrade Count", upgradeCount);

		if (hasCustomInventoryName())
		{
			nbt.setString("Custom Name", customName);
		}
	}

	@Override
	public boolean canConnect(World world, int x, int y, int z)
	{
		return true;
	}
}
