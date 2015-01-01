package main.extremeblocks.entities.mobs.robot;

import main.extremeblocks.entities.mobs.EntityRobot;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemSword;

public class RobotHunter extends RobotTask
{
	private boolean ranging;
	private final EntityAIBase aiPeacefulMobAttack;
	private final EntityAIBase aiAttackOnCollide;
	private final EntityAIBase aiArrowAttack;

	public RobotHunter(EntityRobot robot)
	{
		super(robot);
		aiPeacefulMobAttack = new EntityAINearestAttackableTarget(robot, EntityAnimal.class, 0, true, true);
		aiArrowAttack = new EntityAIArrowAttack(robot, 1.0D, 20, 60, 15.0F);
		aiAttackOnCollide = new EntityAIAttackOnCollide(robot, EntityLivingBase.class, 1.0D, true);
	}

	@Override
	public void reset()
	{
		super.reset();
		robot.setAttackTarget(null);
		aiArrowAttack.resetTask();
		aiAttackOnCollide.resetTask();
		aiPeacefulMobAttack.resetTask();
		removeTasks();
	}

	@Override
	public void nextUpdate(int ticks)
	{
		removeTasks();

		robot.targetTasks.addTask(1, aiPeacefulMobAttack);
		ranging = robot.getAttackTarget() == null ? true : robot.getDistanceToEntity(robot.getAttackTarget()) < 4;
		robot.tasks.addTask(2, ranging ? aiArrowAttack : aiAttackOnCollide);
		robot.inv.putInHand(ranging ? robot.inv.getStackOfItem(ItemBow.class) : robot.inv.getStackOfItem(ItemSword.class), true);
	}

	private void removeTasks()
	{
		robot.targetTasks.removeTask(aiArrowAttack);
		robot.targetTasks.removeTask(aiAttackOnCollide);
		robot.targetTasks.removeTask(aiPeacefulMobAttack);
	}

	@Override
	public boolean keepUpdating(int ticks)
	{
		removeTasks();
		return robot.inv.hasItem(Items.compass) && robot.inv.hasItem(ItemSword.class) && robot.inv.hasItem(ItemBow.class);
	}

	@Override
	public void firstUpdate()
	{
	}
}
