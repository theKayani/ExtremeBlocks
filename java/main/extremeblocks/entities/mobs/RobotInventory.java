package main.extremeblocks.entities.mobs;

import main.com.hk.eb.util.StackHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;

public class RobotInventory implements IInventory
{
	public ItemStack[] inventory;
	public EntityRobot robot;

	public RobotInventory(EntityRobot robot)
	{
		this.robot = robot;
		this.inventory = new ItemStack[32];
	}

	public void useHeldItem()
	{
		inventory[0].damageItem(1, robot);
		this.robot.swingItem();
	}

	public int getFirstEmptySlot()
	{
		for (int i = 0; i < inventory.length; i++)
		{
			if (inventory[i] == null)
			{
				return i;
			}
		}
		return -1;
	}

	public ItemStack getHeldItem()
	{
		return inventory[0];
	}

	public void addItemStack(ItemStack stack)
	{
		for (int i = 0; i < inventory.length; i++)
		{
			if (i == 1 || i == 2 || i == 3 || i == 4) continue;
			StackHelper.addToInvSlot(this, stack, i);
		}
	}

	public boolean hasItem(Item item)
	{
		return getSlotOfItem(item) >= 0;
	}

	public int getSlotOfItem(Item item)
	{
		for (int i = 0; i < inventory.length; ++i)
		{
			if (inventory[i] != null && inventory[i].getItem() == item)
			{
				return i;
			}
		}

		return -1;
	}

	public boolean consumeInventoryItem(Item item)
	{
		int i = this.getSlotOfItem(item);

		if (i < 0)
		{
			return false;
		}
		else
		{
			if (--this.inventory[i].stackSize <= 0)
			{
				this.inventory[i] = null;
			}

			return true;
		}
	}

	public void eatFood()
	{
		for (int i = 0; i < inventory.length; i++)
		{
			if (inventory[i] != null && inventory[i].getItem() instanceof ItemFood)
			{
				float amount = ((ItemFood) inventory[i].getItem()).func_150906_h(inventory[i]);
				if (consumeInventoryItem(inventory[i].getItem()))
				{
					robot.heal(amount);
					robot.worldObj.playSoundAtEntity(robot, "random.burp", 0.5F, robot.getRNG().nextFloat() * 0.1F + 0.9F);
					robot.syncServerAndClient(false);
				}
			}
		}
	}

	@Override
	public int getSizeInventory()
	{
		return inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return inventory[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount)
	{
		ItemStack stack = getStackInSlot(slot);
		if (stack != null)
		{
			if (stack.stackSize > amount)
			{
				stack = stack.splitStack(amount);
				markDirty();
			}
			else
			{
				setInventorySlotContents(slot, null);
			}
		}
		return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot)
	{
		ItemStack stack = getStackInSlot(slot);
		setInventorySlotContents(slot, null);
		return stack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemstack)
	{
		if (itemstack != null && itemstack.stackSize > getInventoryStackLimit())
		{
			itemstack.stackSize = getInventoryStackLimit();
		}
		inventory[slot] = itemstack;
		markDirty();
	}

	@Override
	public String getInventoryName()
	{
		return robot.getCustomNameTag();
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return robot.hasCustomNameTag();
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}

	@Override
	public void markDirty()
	{
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return player.getDistance(this.robot.posX + 0.5D, this.robot.posY + 0.5D, this.robot.posZ + 0.5D) <= 8.0D;
	}

	@Override
	public void openInventory()
	{
		robot.stayStill = true;
	}

	@Override
	public void closeInventory()
	{
		robot.stayStill = false;
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack)
	{
		return true;
	}

	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		NBTTagList items = new NBTTagList();
		for (int i = 0; i < inventory.length; ++i)
		{
			if (inventory[i] != null)
			{
				NBTTagCompound item = new NBTTagCompound();
				item.setByte("Slot", (byte) i);
				inventory[i].writeToNBT(item);
				items.appendTag(item);
			}
		}
		nbt.setTag("Items", items);
	}

	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		NBTTagList items = nbt.getTagList("Items", Constants.NBT.TAG_COMPOUND);
		for (int i = 0; i < items.tagCount(); ++i)
		{
			NBTTagCompound item = items.getCompoundTagAt(i);
			byte slot = item.getByte("Slot");
			if (slot >= 0 && slot < inventory.length)
			{
				inventory[slot] = ItemStack.loadItemStackFromNBT(item);
			}
		}
	}

	public void damageArmor(float damage)
	{
		damage /= 4.0F;

		if (damage < 1.0F)
		{
			damage = 1.0F;
		}

		for (int i = 1; i < 5; ++i)
		{
			if (this.inventory[i] != null && this.inventory[i].getItem() instanceof ItemArmor)
			{
				this.inventory[i].damageItem((int) damage, this.robot);

				if (this.inventory[i].stackSize == 0)
				{
					this.inventory[i] = null;
				}
			}
		}
	}

	public int getTotalArmorValue()
	{
		int i = 0;

		for (int j = 1; j < 5; ++j)
		{
			if (this.inventory[j] != null && this.inventory[j].getItem() instanceof ItemArmor)
			{
				int k = ((ItemArmor) this.inventory[j].getItem()).damageReduceAmount;
				i += k;
			}
		}

		return i;
	}

	public float getDamageModifier()
	{
		float base = 1.0F;
		if (getHeldItem() != null && getHeldItem().getItem() instanceof ItemSword)
		{
			base += 3 + ((ItemSword) getHeldItem().getItem()).func_150931_i();
		}
		return base;
	}
}
