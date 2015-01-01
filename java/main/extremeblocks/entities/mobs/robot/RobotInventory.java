package main.extremeblocks.entities.mobs.robot;

import main.com.hk.eb.util.StackHelper;
import main.extremeblocks.entities.mobs.EntityRobot;
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
		inventory = new ItemStack[32];
	}

	public void useHeldItem()
	{
		inventory[0].damageItem(1, robot);
		robot.swingItem();
	}

	public int getFirstEmptySlot()
	{
		for (int i = 0; i < inventory.length; i++)
		{
			if (inventory[i] == null) return i;
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
			if (i == 1 || i == 2 || i == 3 || i == 4)
			{
				continue;
			}
			StackHelper.addToInvSlot(this, stack, i);
		}
	}

	public void addItemStacks(ItemStack... stacks)
	{
		for (ItemStack stack : stacks)
		{
			addItemStack(stack);
		}
	}

	public void putInHand(ItemStack stack, boolean switchOut)
	{
		if (switchOut)
		{
			if (hasItem(stack.getItem()))
			{
				ItemStack current = inventory[0];
				ItemStack switchStack = inventory[getSlotOfItem(stack.getItem())];
				inventory[0] = switchStack;
				inventory[getSlotOfItem(stack.getItem())] = current;
			}
		}
		else
		{
			inventory[0] = stack;
		}
	}

	public boolean hasItem(Item item)
	{
		return getSlotOfItem(item) >= 0;
	}

	public boolean hasItem(Class<? extends Item> clazz)
	{
		return getSlotOfItem(clazz) >= 0;
	}

	public int getSlotOfItem(Class<? extends Item> clazz)
	{
		for (int i = 0; i < inventory.length; ++i)
		{
			if (inventory[i] != null && clazz.isAssignableFrom(inventory[i].getItem().getClass())) return i;
		}
		return -1;
	}

	public int getSlotOfItem(Item item)
	{
		for (int i = 0; i < inventory.length; ++i)
		{
			if (inventory[i] != null && inventory[i].getItem() == item) return i;
		}
		return -1;
	}

	public ItemStack getStackOfItem(Class<? extends Item> clazz)
	{
		int i = getSlotOfItem(clazz);
		return i < 0 ? null : getStackInSlot(i);
	}

	public ItemStack getStackOfItem(Item item)
	{
		int i = getSlotOfItem(item);
		return i < 0 ? null : getStackInSlot(i);
	}

	public boolean consumeInventoryItem(Item item)
	{
		int i = getSlotOfItem(item);

		if (i < 0) return false;
		else
		{
			if (--inventory[i].stackSize <= 0)
			{
				inventory[i] = null;
			}

			return true;
		}
	}

	public void eatFood()
	{
		for (ItemStack stack : inventory)
		{
			if (stack != null && stack.getItem() instanceof ItemFood)
			{
				float amount = ((ItemFood) stack.getItem()).func_150906_h(stack);
				if (consumeInventoryItem(stack.getItem()))
				{
					robot.heal(amount);
					robot.worldObj.playSoundAtEntity(robot, "random.burp", 0.5F, robot.getRNG().nextFloat() * 0.1F + 0.9F);
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
		return player.getDistance(robot.posX + 0.5D, robot.posY + 0.5D, robot.posZ + 0.5D) <= 8.0D;
	}

	@Override
	public void openInventory()
	{
		robot.setStill(true);
	}

	@Override
	public void closeInventory()
	{
		robot.setStill(false);
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
				item.setInteger("Slot", i);
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
			int slot = item.getInteger("Slot");
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
			if (inventory[i] != null && inventory[i].getItem() instanceof ItemArmor)
			{
				inventory[i].damageItem((int) damage, robot);

				if (inventory[i].stackSize == 0)
				{
					inventory[i] = null;
				}
			}
		}
	}

	public int getTotalArmorValue()
	{
		int i = 0;

		for (int j = 1; j < 5; ++j)
		{
			if (inventory[j] != null && inventory[j].getItem() instanceof ItemArmor)
			{
				int k = ((ItemArmor) inventory[j].getItem()).damageReduceAmount;
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
