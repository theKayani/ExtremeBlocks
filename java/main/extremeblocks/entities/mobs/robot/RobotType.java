package main.extremeblocks.entities.mobs.robot;

import main.com.hk.eb.util.JavaHelp;
import main.extremeblocks.entities.mobs.EntityRobot;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public enum RobotType
{
	WARRIOR("Sword", Items.golden_sword, RobotWarrior.class),
	FARMER("Hoe", Items.golden_hoe, RobotFarmer.class),
	MINER("Pickaxe", Items.golden_pickaxe, RobotMiner.class),
	HUNTER("Compass", Items.compass, RobotHunter.class),
	ARCHER("Bow", Items.bow, RobotWarrior.class);

	public final String name;
	public final Item item;
	public final String toolName;
	public final Class<? extends RobotTask> taskClass;

	private RobotType(String toolName, Item item, Class<? extends RobotTask> taskClass)
	{
		name = JavaHelp.normalName(name());
		this.toolName = toolName;
		this.item = item;
		this.taskClass = taskClass;
	}

	public boolean isValidStack(ItemStack stack)
	{
		if (stack != null)
		{
			Item item = stack.getItem();
			switch (this)
			{
				case ARCHER:
					return item instanceof ItemBow;
				case FARMER:
					return item instanceof ItemHoe;
				case HUNTER:
					return item == Items.compass;
				case MINER:
					return item instanceof ItemPickaxe;
				case WARRIOR:
					return item instanceof ItemSword;
				default:
				{
					throw new IllegalStateException(name() + " doesn't have a stack filter!");
				}
			}
		}
		return false;
	}

	public RobotTask taskCopy(EntityRobot robot)
	{
		try
		{
			return taskClass.getConstructor(EntityRobot.class).newInstance(robot);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}