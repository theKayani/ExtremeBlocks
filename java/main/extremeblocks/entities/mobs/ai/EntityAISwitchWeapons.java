package main.extremeblocks.entities.mobs.ai;

import main.extremeblocks.entities.mobs.EntityRobot;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

public class EntityAISwitchWeapons extends EntityAIBase
{
	private final EntityRobot entityHost;
	private EntityLivingBase attackTarget;
	private double entityMoveSpeed;
	private int amplifier, maxRangedAttackTime, field_75318_f, rangedAttackTime;
	private float distance, distanceSquare;
	private boolean useRange;
	private final Item sword, bow;

	public EntityAISwitchWeapons(EntityRobot entity, double entityMoveSpeed, int amplifier, float distance)
	{
		this(entity, entityMoveSpeed, amplifier, amplifier, distance, Items.iron_sword, Items.bow);
	}

	public EntityAISwitchWeapons(EntityRobot entity, double entityMoveSpeed, int amplifier, int maxRangedAttackTime, float distance, Item sword, Item bow)
	{
		this.rangedAttackTime = -1;
		if (!(entity instanceof EntityLivingBase))
		{
			throw new IllegalArgumentException("ArrowAttackGoal requires Mob implements RangedAttackMob");
		}
		else
		{
			this.sword = sword;
			this.bow = bow;
			this.entityHost = entity;
			this.entityMoveSpeed = entityMoveSpeed;
			this.amplifier = amplifier;
			this.maxRangedAttackTime = maxRangedAttackTime;
			this.distance = distance;
			this.distanceSquare = distance * distance;
			this.setMutexBits(3);
		}
	}

	/**
	 * Returns whether the EntityAIBase should begin execution.
	 */
	@Override
	public boolean shouldExecute()
	{
		EntityLivingBase entitylivingbase = this.entityHost.getAttackTarget();
		if (entitylivingbase == null)
		{
			return false;
		}
		else
		{
			this.attackTarget = entitylivingbase;
			return true;
		}
	}

	/**
	 * Returns whether an in-progress EntityAIBase should continue executing
	 */
	@Override
	public boolean continueExecuting()
	{
		boolean exec = this.shouldExecute() || !this.entityHost.getNavigator().noPath();
		if (exec)
		{
			EntityLivingBase targ = entityHost.getAttackTarget();
			if (targ != null)
			{
				if (targ.getDistanceToEntity(entityHost) < 5.0F)
				{
					if (entityHost.getEquipmentInSlot(0) != null && entityHost.getEquipmentInSlot(0).getItem() != sword)
					{
						entityHost.setCurrentItemOrArmor(0, new ItemStack(sword));
					}
					useRange = false;
					entityHost.isRanging = false;
				}
				else
				{
					if (entityHost.getEquipmentInSlot(0) != null && entityHost.getEquipmentInSlot(0).getItem() != bow)
					{
						entityHost.setCurrentItemOrArmor(0, new ItemStack(bow));
					}
					useRange = true;
					entityHost.isRanging = true;
				}
			}
		}
		return exec;
	}

	/**
	 * Resets the task
	 */
	@Override
	public void resetTask()
	{
		this.attackTarget = null;
		this.field_75318_f = 0;
		this.rangedAttackTime = -1;
	}

	/**
	 * Updates the task
	 */
	@Override
	public void updateTask()
	{
		double d0 = this.entityHost.getDistanceSq(this.attackTarget.posX, this.attackTarget.boundingBox.minY, this.attackTarget.posZ);
		boolean flag = this.entityHost.getEntitySenses().canSee(this.attackTarget);
		if (flag)
		{
			++this.field_75318_f;
		}
		else
		{
			this.field_75318_f = 0;
		}
		if (d0 <= this.distanceSquare && this.field_75318_f >= 20 && useRange)
		{
			this.entityHost.getNavigator().clearPathEntity();
		}
		else
		{
			this.entityHost.getNavigator().tryMoveToEntityLiving(this.attackTarget, this.entityMoveSpeed);
		}
		this.entityHost.getLookHelper().setLookPositionWithEntity(this.attackTarget, 30.0F, 30.0F);
		float f;
		if (--this.rangedAttackTime == 0)
		{
			if (d0 > this.distanceSquare || !flag)
			{
				return;
			}
			f = MathHelper.sqrt_double(d0) / this.distance;
			float f1 = f;
			if (f < 0.1F)
			{
				f1 = 0.1F;
			}
			if (f1 > 1.0F)
			{
				f1 = 1.0F;
			}
			if (useRange) this.entityHost.attackEntityWithRangedAttack(this.attackTarget, f1);
			this.rangedAttackTime = MathHelper.floor_float(f * (this.maxRangedAttackTime - this.amplifier) + this.amplifier);
		}
		else if (this.rangedAttackTime < 0)
		{
			f = MathHelper.sqrt_double(d0) / this.distance;
			this.rangedAttackTime = MathHelper.floor_float(f * (this.maxRangedAttackTime - this.amplifier) + this.amplifier);
		}
	}
}
