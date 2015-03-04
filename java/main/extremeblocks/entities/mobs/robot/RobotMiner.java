package main.extremeblocks.entities.mobs.robot;

import java.util.List;
import main.com.hk.eb.util.BlockIndex;
import main.com.hk.eb.util.JavaHelp;
import main.com.hk.eb.util.StackHelper;
import main.com.hk.eb.util.Vector3I;
import main.com.hk.eb.util.WorldUtil;
import main.extremeblocks.blocks.abstracts.BlockEBOre;
import main.extremeblocks.entities.mobs.EntityRobot;
import net.minecraft.block.BlockOre;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class RobotMiner extends RobotTask
{
	private boolean done, next, mine;
	private float blockDamage;
	private int top, counter;
	private BlockIndex currentBlock;
	private boolean moveUp;

	public RobotMiner(EntityRobot robot)
	{
		super(robot);
	}

	@Override
	public void firstUpdate()
	{
		top = getPos().y + 3;
	}

	@Override
	public void nextUpdate(int ticks)
	{
		robot.setStill(true);
		List<BlockIndex> neighbors = getNeighbors();
		if (moveUp)
		{
			moveUp();
		}
		else
		{
			if (neighbors.size() > 0)
			{
				getNearbyBlocks(neighbors);
			}
			else
			{
				currentBlock = null;
				moveDown();
			}
		}
		if (currentBlock != null)
		{
			robot.getLookHelper().setLookPosition(currentBlock.vec.x, currentBlock.vec.y, currentBlock.vec.z, 10.0F, robot.getVerticalFaceSpeed());
			harvestBlock(currentBlock);
		}
	}

	@Override
	public void lastUpdate()
	{
		super.lastUpdate();
		robot.setStill(false);
	}

	public void moveUp()
	{
		if (!world.isAirBlock(getPos().x, getPos().y + 2, getPos().z))
		{
			harvestBlock(new BlockIndex(world, getPos().addLocal(0, 2, 0)));
		}
		else if (counter++ > 16)
		{
			counter = 0;
			world.setBlock(getPos().x, getPos().y, getPos().z, Blocks.cobblestone);
			robot.setPosition(robot.posX, robot.posY + 1.0D, robot.posZ);
		}
		if (getPos().y == top)
		{
			done = true;
		}
	}

	public void moveDown()
	{
		if (shouldMoveUp(getPos().addLocal(0, -1, 0)) && !moveUp)
		{
			moveUp = true;
		}
		else
		{
			if (harvestBlock(new BlockIndex(world, getPos().addLocal(next ? 1 : 0, -1, 0))))
			{
				next = !next;
			}
		}
	}

	public boolean harvestBlock(BlockIndex index)
	{
		if (currentBlock == null || currentBlock.equals(index))
		{
			mine = !mine;

			if (mine)
			{
				blockDamage = WorldUtil.partiallyDestroyBlock(robot, index.vec, blockDamage);
				currentBlock = index;
				if (blockDamage == 0.0F)
				{
					List<ItemStack> drops = index.getDrops(EnchantmentHelper.getFortuneModifier(robot));
					world.setBlockToAir(index.x, index.y, index.z);
					currentBlock = null;
					robot.inv.addItemStacks(drops.toArray(new ItemStack[0]));
					robot.getHeldItem().damageItem(1, robot);
					if (robot.getHeldItem().getItemDamage() >= robot.getHeldItem().getMaxDamage())
					{
						robot.setCurrentItemOrArmor(0, StackHelper.consumeItem(robot.getHeldItem()));
					}
					return true;
				}
			}
			return false;
		}
		return true;
	}

	public void getNearbyBlocks(List<BlockIndex> neighbors)
	{
		for (int i = 0; i < neighbors.size(); i++)
		{
			if (currentBlock == null || currentBlock == neighbors.get(i))
			{
				currentBlock = neighbors.get(i);
				break;
			}
		}
	}

	@Override
	public boolean keepUpdating(int ticks)
	{
		return !done && robot.getType().isValidStack(robot.getHeldItem());
	}

	public List<BlockIndex> getNeighbors()
	{
		List<BlockIndex> neighbors = JavaHelp.newArrayList();
		for (int i = -1; i < 2; i++)
		{
			for (int j = -1; j < 2; j++)
			{
				BlockIndex a = new BlockIndex(world, getPos().add(new Vector3I(i, 0, j)));
				BlockIndex b = new BlockIndex(world, getPos().add(new Vector3I(i, 1, j)));
				if (shouldHarvestBlock(a))
				{
					neighbors.add(a);
				}
				if (shouldHarvestBlock(b))
				{
					neighbors.add(b);
				}
			}
		}
		return neighbors;
	}

	public boolean shouldMoveUp(Vector3I vec)
	{
		BlockIndex in = new BlockIndex(world, vec);
		return in.getHardness() < 0 && in.getBlock().getMaterial().blocksMovement();
	}

	public boolean shouldHarvestBlock(BlockIndex in)
	{
		return in.getBlock() instanceof BlockOre || in.getBlock() instanceof BlockEBOre;
	}
}
