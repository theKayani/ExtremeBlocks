package main.extremeblocks.entities.mobs.robot;

import java.util.Map;
import main.com.hk.eb.util.BlockIndex;
import main.com.hk.eb.util.JavaHelp;
import main.com.hk.eb.util.MPUtil;
import main.com.hk.eb.util.Vector3I;
import main.extremeblocks.Init;
import main.extremeblocks.blocks.BlockCrop;
import main.extremeblocks.entities.mobs.EntityRobot;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;

public class RobotFarmer extends RobotTask
{
	private static final Map<Block, Item> cropAndSeeds;

	public RobotFarmer(EntityRobot robot)
	{
		super(robot);
	}

	@Override
	public void nextUpdate(int ticks)
	{
		for (int i = -10; i < 11; i++)
		{
			for (int j = -10; j < 11; j++)
			{
				for (int k = -10; k < 11; k++)
				{
					if (doStuff(getPos().addLocal(i, j, k)))
					{
						break;
					}
				}
			}
		}
		getCloseBlocks();
	}

	public boolean doStuff(Vector3I vec)
	{
		boolean bool = false;
		if (world.getBlock(vec.x, vec.y, vec.z) instanceof BlockCrops || world.getBlock(vec.x, vec.y, vec.z) instanceof BlockCrop)
		{
			bool = isValidCropToHarvest(new BlockIndex(world, vec));
			if (bool)
			{
				if (robot.getNavigator().noPath())
				{
					robot.setStill(false);
					robot.getNavigator().tryMoveToXYZ(vec.x, vec.y, vec.z, 1.0F);
				}
			}
			robot.setStill(true);
		}
		return bool;
	}

	public boolean isValidCropToHarvest(BlockIndex curr)
	{
		return (curr.getBlock() instanceof BlockCrops || curr.getBlock() instanceof BlockCrop) && curr.getMetadata() / 7.0F > 0.99F;
	}

	private void getCloseBlocks()
	{
		for (int i = -1; i < 2; i++)
		{
			for (int j = -1; j < 2; j++)
			{
				for (int k = -1; k < 2; k++)
				{
					harvestBlock(new BlockIndex(world, getPos().addLocal(i, j, k)));
				}
			}
		}
	}

	private void harvestBlock(BlockIndex curr)
	{
		if (isValidCropToHarvest(curr))
		{
			System.out.println("Current Block: " + curr.getBlock().getLocalizedName() + ", Pos: " + curr.vec);
			ItemStack[] stacks = curr.getDrops(EnchantmentHelper.getFortuneModifier(robot)).toArray(new ItemStack[0]);
			if (robot.inv.getHeldItem() != null && robot.inv.getHeldItem().isItemStackDamageable())
			{
				robot.inv.addItemStacks(stacks);
				robot.inv.getHeldItem().damageItem(stacks.length, robot);
			}
			world.setBlockToAir(curr.x, curr.y, curr.z);
			world.playAuxSFXAtEntity(null, 2001, curr.x, curr.y, curr.z, Block.getIdFromBlock(curr.getBlock()) + (curr.getMetadata() << 12));
			if (cropAndSeeds.containsKey(curr.getBlock()) && robot.inv.consumeInventoryItem(cropAndSeeds.get(curr.getBlock())))
			{
				world.setBlock(curr.x, curr.y, curr.z, curr.getBlock());
			}
		}
	}

	@Override
	public boolean keepUpdating(int ticks)
	{
		return MPUtil.isServerSide() && robot.getHeldItem() != null && robot.getHeldItem().getItem() instanceof ItemHoe;
	}

	static
	{
		cropAndSeeds = JavaHelp.newHashMap();
		cropAndSeeds.put(Blocks.wheat, Items.wheat_seeds);
		cropAndSeeds.put(Blocks.carrots, Items.carrot);
		cropAndSeeds.put(Blocks.potatoes, Items.potato);
		cropAndSeeds.put(Init.cucumber_crop, Init.cucumber_seeds);
		cropAndSeeds.put(Init.tomato_crop, Init.tomato_seeds);
	}

	@Override
	public void firstUpdate()
	{

	}
}
