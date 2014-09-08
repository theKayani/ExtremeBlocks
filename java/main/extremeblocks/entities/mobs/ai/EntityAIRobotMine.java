package main.extremeblocks.entities.mobs.ai;

import main.extremeblocks.entities.mobs.EntityRobot;
import main.extremeblocks.entities.mobs.RobotInventory;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityAIRobotMine extends EntityAIBase
{
	public final EntityRobot taskOwner;
	public final Block[] blocks;
	public final int rangeToCheck;
	public final RobotInventory entityInventory;
	public final World world;
	public final EntityLookHelper lookHelper;
	private int[] orePosition;
	public int x, y, z, counter, swingCounter, levelCounter, maxCount;
	private boolean moveUp, next, done;
	public double top;

	public EntityAIRobotMine(EntityRobot taskOwner, int rangeToCheck, Block... blocks)
	{
		this.taskOwner = taskOwner;
		this.blocks = blocks;
		this.rangeToCheck = rangeToCheck;
		this.entityInventory = taskOwner.inv;
		this.lookHelper = taskOwner.getLookHelper();
		this.world = taskOwner.worldObj;
		this.x = (int) taskOwner.posX;
		this.y = (int) taskOwner.posY;
		this.z = (int) taskOwner.posZ;
		this.setMutexBits(3);
		setCount();
	}

	@Override
	public boolean shouldExecute()
	{
		return !done;
	}

	@Override
	public void updateTask()
	{
		x = (int) (taskOwner.posX);
		y = (int) (taskOwner.posY);
		z = (int) (taskOwner.posZ);
		setCount();
		taskOwner.stayStill = true;
		if (moveUp)
		{
			startMovingUp(x - 1, y, z - 1);
		}
		else
		{
			if (next)
			{
				doStuff(x - 1, y - 1, z - 1);
			}
			else
			{
				doStuff(x, y - 1, z - 1);
			}
		}
		lookHelper.setLookPosition(taskOwner.posX, taskOwner.posY - 2, taskOwner.posZ, 10.0F, this.taskOwner.getVerticalFaceSpeed());
		if (taskOwner.posY >= top)
		{
			taskOwner.startMining = false;
			taskOwner.stayStill = false;
			done = true;

		}
	}

	public void doStuff(int x, int y, int z)
	{
		if (world.getBlock(x, y, z) == Blocks.bedrock && !moveUp)
		{
			moveUp = true;
		}
		else
		{
			if (world.getBlock(x, y, z).getMaterial() == Material.lava)
			{
				for (int i = 0; i < 2; i++)
				{
					world.setBlock(x + 1, y - i, z + 1, Blocks.glass);
					world.setBlock(x + 1, y - i, z - 1, Blocks.glass);
					world.setBlock(x - 1, y - i, z + 1, Blocks.glass);
					world.setBlock(x - 1, y - i, z - 1, Blocks.glass);
					world.setBlockToAir(x, y - i, z);
				}
			}
			harvestBlockAt(x, y, z);
		}
	}

	private void startMovingUp(int x, int y, int z)
	{
		if (swingCounter++ >= maxCount)
		{
			System.err.println("Block: " + (world.getBlock(x, y + 2, z) == null ? Blocks.air : world.getBlock(x, y + 2, z)).getLocalizedName() + ", Max Count: " + maxCount);
			if (world.getBlock(x, y + 2, z) != null && !world.isAirBlock(x, y + 2, z))
			{
				harvestBlockAt(x, y + 2, z);
			}
			else
			{
				world.setBlock(x, y, z, getPlaceableBlock());
				taskOwner.setPosition(taskOwner.posX, taskOwner.posY + 1.0D, taskOwner.posZ);
			}
			swingCounter = 0;
		}
	}

	public Block getPlaceableBlock()
	{
		for (int i = 0; i < entityInventory.getSizeInventory(); i++)
		{
			if (entityInventory.getStackInSlot(i) != null && entityInventory.getStackInSlot(i).getItem() instanceof ItemBlock && Block.getBlockFromItem(entityInventory.getStackInSlot(i).getItem()).isNormalCube())
			{
				Block block = Block.getBlockFromItem(entityInventory.getStackInSlot(i).getItem());
				entityInventory.getStackInSlot(i).stackSize--;
				if (entityInventory.getStackInSlot(i).stackSize <= 0)
				{
					entityInventory.setInventorySlotContents(i, null);
				}
				return block;
			}
		}
		return Blocks.cobblestone;
	}

	public void harvestBlockAt(int x, int y, int z)
	{
		if (swingCounter++ >= maxCount)
		{
			swingCounter = 0;
			for (ItemStack stack : world.getBlock(x, y, z).getDrops(world, x, y, z, world.getBlockMetadata(x, y, z), 0).toArray(new ItemStack[0]))
			{
				entityInventory.addItemStack(stack);
			}
			world.playAuxSFXAtEntity(null, 2001, x, y, z, Block.getIdFromBlock(world.getBlock(x, y, z)) + (world.getBlockMetadata(x, y, z) << 12));
			world.setBlockToAir(x, y, z);
			next = !next;
			if (entityInventory.getHeldItem() != null && entityInventory.getHeldItem().isItemStackDamageable())
			{
				entityInventory.getHeldItem().damageItem(1, taskOwner);
			}
		}
	}

	public void setCount()
	{
		int top = 90;
		if (entityInventory.getHeldItem() != null && entityInventory.getHeldItem().getItem() instanceof ItemPickaxe)
		{
			int level = ((ItemPickaxe) entityInventory.getHeldItem().getItem()).func_150913_i().getHarvestLevel();
			top /= level <= 0 ? 1 : level;
		}
		maxCount = top;
	}
}
