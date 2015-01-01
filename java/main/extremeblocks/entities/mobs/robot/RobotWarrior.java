package main.extremeblocks.entities.mobs.robot;

import main.extremeblocks.entities.mobs.EntityRobot;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;

public class RobotWarrior extends RobotTask
{
	private final boolean isRanger;
	private final EntityAIBase aiAttack;
	private final EntityAIBase aiHostileMobAttack;

	public RobotWarrior(EntityRobot robot)
	{
		super(robot);
		isRanger = robot.getType() == RobotType.ARCHER;
		aiHostileMobAttack = new EntityAINearestAttackableTarget(robot, IMob.class, 0, false);
		aiAttack = isRanger ? new EntityAIArrowAttack(robot, 1.0D, 20, 60, 15.0F) : new EntityAIAttackOnCollide(robot, EntityMob.class, 1.2D, false);
	}

	@Override
	public void firstUpdate()
	{
		robot.tasks.removeTask(aiAttack);
		robot.tasks.addTask(2, aiAttack);
		robot.targetTasks.removeTask(aiHostileMobAttack);
		robot.targetTasks.addTask(2, aiHostileMobAttack);
	}

	@Override
	public void reset()
	{
		robot.setAttackTarget(null);
		robot.tasks.removeTask(aiAttack);
		robot.targetTasks.removeTask(aiHostileMobAttack);
		super.reset();
	}

	@Override
	public boolean keepUpdating(int ticks)
	{
		return robot.getType().isValidStack(robot.getHeldItem());
	}

	@Override
	public void nextUpdate(int ticks)
	{
		// TODO Auto-generated method stub

	}
}
