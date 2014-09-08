package main.extremeblocks.entities.mobs.ai;

import main.extremeblocks.entities.mobs.EntityRobot;
import main.extremeblocks.entities.mobs.RobotInventory;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityAIHarvestCrops extends EntityAIBase
{
	public final EntityRobot taskOwner;
	public final int rangeToCheck;
	public final RobotInventory entityInventory;
	public final World world;
	public int x, y, z, xPos, yPos, zPos, meta;
	public Block block;
	public boolean setPos;

	public EntityAIHarvestCrops(EntityRobot taskOwner, int rangeToCheck)
	{
		this.taskOwner = taskOwner;
		this.rangeToCheck = rangeToCheck;
		this.entityInventory = taskOwner.inv;
		this.world = taskOwner.worldObj;
		this.setMutexBits(3);
	}

	@Override
	public boolean shouldExecute()
	{
		boolean bool = false;
		for (int i = -rangeToCheck; i < rangeToCheck + 1; i++)
		{
			for (int j = -rangeToCheck; j < rangeToCheck + 1; j++)
			{
				for (int k = -rangeToCheck; k < rangeToCheck + 1; k++)
				{
					x = (int) taskOwner.posX + i;
					y = (int) taskOwner.posY + j;
					z = (int) taskOwner.posZ + k;
					int meta = world.getBlockMetadata(x, y, z);
					Block block = world.getBlock(x, y, z);
					bool = bool || isValidCropToHarvest(x, y, z);
					if (bool)
					{
						if (taskOwner.getNavigator().noPath())
						{
							taskOwner.getNavigator().tryMoveToXYZ(x, y, z, 1.0F);
						}
					}
				}
			}
		}
		if (bool)
		{
			getCloseBlocks((int) taskOwner.posX - 1, (int) taskOwner.posY, (int) taskOwner.posZ - 1);
			taskOwner.getLookHelper().setLookPosition((int) taskOwner.posX - 1, (int) taskOwner.posY - 2, (int) taskOwner.posZ - 1, 10.0F, this.taskOwner.getVerticalFaceSpeed());
		}
		return bool;
	}

	public boolean isValidCropToHarvest(int x, int y, int z)
	{
		return world.getBlock(x, y, z) instanceof BlockCrops && world.getBlockMetadata(x, y, z) / 7.0F > 0.99F;
	}

	private void getCloseBlocks(int x, int y, int z)
	{
		for (int i = -1; i < 2; i++)
		{
			for (int j = -2; j < 3; j++)
			{
				for (int k = -1; k < 2; k++)
				{
					if (isValidCropToHarvest(x + i, y + j, z + k)) harvestBlock(x + i, y + j, z + k);
				}
			}
		}
	}

	private void harvestBlock(int x, int y, int z)
	{
		Block block = world.getBlock(x, y, z);
		int meta = world.getBlockMetadata(x, y, z);
		for (ItemStack stack : block.getDrops(world, x, y, z, meta, 0).toArray(new ItemStack[0]))
		{
			entityInventory.addItemStack(stack);
		}
		if (entityInventory.getHeldItem() != null && entityInventory.getHeldItem().isItemStackDamageable())
		{
			entityInventory.getHeldItem().damageItem(1, taskOwner);
		}
		world.setBlockToAir(x, y, z);
		world.playAuxSFXAtEntity(null, 2001, x, y, z, Block.getIdFromBlock(block) + (meta << 12));
		if (block == Blocks.wheat && entityInventory.consumeInventoryItem(Items.wheat_seeds))
		{
			world.setBlock(x, y, z, Blocks.wheat);
		}
		if (block == Blocks.carrots && entityInventory.consumeInventoryItem(Items.carrot))
		{
			world.setBlock(x, y, z, Blocks.carrots);
		}
		if (block == Blocks.potatoes && entityInventory.consumeInventoryItem(Items.potato))
		{
			world.setBlock(x, y, z, Blocks.potatoes);
		}
	}
}
