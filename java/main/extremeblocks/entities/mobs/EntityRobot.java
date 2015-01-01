package main.extremeblocks.entities.mobs;

import java.util.Random;
import main.com.hk.eb.util.IReplacer;
import main.com.hk.eb.util.MPUtil;
import main.extremeblocks.ExtremeBlocks;
import main.extremeblocks.GuiHandler;
import main.extremeblocks.GuiIDs;
import main.extremeblocks.Init;
import main.extremeblocks.Vars;
import main.extremeblocks.entities.mobs.robot.RobotInventory;
import main.extremeblocks.entities.mobs.robot.RobotTask;
import main.extremeblocks.entities.mobs.robot.RobotType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityRobot extends EntityCreature implements IRangedAttackMob, GuiIDs, IReplacer
{
	public RobotInventory inv = new RobotInventory(this);
	private RobotTask currentTask;

	public EntityRobot(World world)
	{
		super(world);
		setSize(0.6F, 1.8F);
		getNavigator().setAvoidsWater(true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAITempt(this, 1.5D, Items.gold_nugget, false));
		tasks.addTask(3, new EntityAIWander(this, 1.0D));
		tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(5, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		isImmuneToFire = true;
	}

	@Override
	public boolean isAIEnabled()
	{
		return true;
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		dataWatcher.addObject(16, new Integer(0));
		dataWatcher.addObject(17, new Integer(0));
		dataWatcher.addObject(18, new Integer(0));
	}

	@Override
	public boolean attackEntityAsMob(Entity entity)
	{
		if (entity instanceof EntityLivingBase)
		{
			((EntityLivingBase) entity).attackEntityFrom(DamageSource.causeMobDamage(this), inv.getDamageModifier());
		}
		return true;
	}

	@Override
	@SuppressWarnings("rawtypes")
	public boolean canAttackClass(Class clazz)
	{
		return !EntityRobot.class.isAssignableFrom(clazz) && !EntityPlayer.class.isAssignableFrom(clazz) && super.canAttackClass(clazz);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(50.0D);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase targ, float damage)
	{
		EntityArrow entityarrow = new EntityArrow(worldObj, this, targ, 1.6F, 14 - worldObj.difficultySetting.getDifficultyId() * 4);
		int i = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, getHeldItem());
		int j = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, getHeldItem());
		entityarrow.setDamage(damage * 2.0F + rand.nextGaussian() * 0.25D + worldObj.difficultySetting.getDifficultyId() * 0.11F);

		if (i > 0)
		{
			entityarrow.setDamage(entityarrow.getDamage() + i * 0.5D + 0.5D);
		}

		if (j > 0)
		{
			entityarrow.setKnockbackStrength(j);
		}

		if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, getHeldItem()) > 0)
		{
			entityarrow.setFire(100);
		}

		playSound("random.bow", 1.0F, 1.0F / (getRNG().nextFloat() * 0.4F + 0.8F));
		worldObj.spawnEntityInWorld(entityarrow);
	}

	@Override
	protected boolean isMovementCeased()
	{
		return isStill();
	}

	@Override
	public void damageArmor(float damage)
	{
		inv.damageArmor(damage);
	}

	@Override
	public int getTotalArmorValue()
	{
		return inv.getTotalArmorValue();
	}

	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return player.getDistance(posX, posY, posZ) <= 8.0D;
	}

	@Override
	public ItemStack getEquipmentInSlot(int slot)
	{
		return inv.inventory[slot];
	}

	@Override
	public ItemStack func_130225_q(int p_130225_1_)
	{
		return inv.inventory[p_130225_1_ + 1];
	}

	@Override
	public void setCurrentItemOrArmor(int slot, ItemStack item)
	{
		inv.inventory[slot] = item;
	}

	@Override
	public ItemStack getHeldItem()
	{
		return inv.getHeldItem();
	}

	@Override
	public ItemStack[] getLastActiveItems()
	{
		ItemStack[] eq = new ItemStack[5];
		eq[0] = inv.getHeldItem();
		eq[1] = inv.inventory[2];
		eq[2] = inv.inventory[1];
		eq[3] = inv.inventory[4];
		eq[4] = inv.inventory[3];
		return eq;
	}

	@Override
	protected boolean canDespawn()
	{
		return false;
	}

	@Override
	public void onDeath(DamageSource ds)
	{
		if (MPUtil.isServerSide())
		{
			Random rand = new Random(getRNG().nextLong());
			for (int i = 0; i < inv.getSizeInventory(); i++)
			{
				ItemStack item = inv.getStackInSlot(i);
				if (item != null && item.stackSize > 0)
				{
					float rx = rand.nextFloat() * 0.8F + 0.1F;
					float ry = rand.nextFloat() * 0.8F + 0.1F;
					float rz = rand.nextFloat() * 0.8F + 0.1F;
					EntityItem entityItem = new EntityItem(worldObj, posX + rx, posY + ry, posZ + rz, item.copy());
					if (item.hasTagCompound())
					{
						entityItem.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
					}
					float factor = 0.05F;
					entityItem.motionX = rand.nextGaussian() * factor;
					entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
					entityItem.motionZ = rand.nextGaussian() * factor;
					worldObj.spawnEntityInWorld(entityItem);
					item.stackSize = 0;
				}
			}
		}
		super.onDeath(ds);
	}

	@Override
	protected boolean interact(EntityPlayer player)
	{
		if (!player.isSneaking())
		{
			GuiHandler.entityID = getEntityId();
			player.openGui(ExtremeBlocks.instance, GUI_ROBOT, worldObj, (int) posX, (int) posY, (int) posZ);
		}
		MPUtil.sendMessage("Hello, Master!", player);
		if (onTask())
		{
			MPUtil.sendMessage("I am currently on a task.", player);
		}
		else
		{
			MPUtil.sendMessage("I need a " + getType().toolName + " to start working!", player);
		}
		return true;
	}

	@Override
	protected void dropFewItems(boolean flag, int looting)
	{
		dropItem(Init.power_core, 1);
		dropItem(Init.robot_arm, 2);
		dropItem(Init.robot_head, 1);
		dropItem(Init.robot_leg, 2);
		dropItem(Init.robot_torso, 1);
	}

	@Override
	public void onLivingUpdate()
	{
		MPUtil.replace(this, Vars.addRobot);
		getTask().update();
		super.onLivingUpdate();
	}

	public void startTask()
	{
		if (getType().isValidStack(getHeldItem()))
		{
			getTask().reset();
			getTask().execute = true;
			setOnTask(true);
		}
	}

	public void endTask()
	{
		getTask().lastUpdate();
		getTask().execute = false;
		setOnTask(false);
	}

	public RobotTask getTask()
	{
		if (currentTask == null || !currentTask.getClass().equals(getType().taskClass))
		{
			currentTask = getType().taskCopy(this);
		}
		return currentTask;
	}

	public void setType(RobotType type)
	{
		dataWatcher.updateObject(16, new Integer(type.ordinal()));
	}

	public RobotType getType()
	{
		return RobotType.values()[dataWatcher.getWatchableObjectInt(16)];
	}

	public boolean isStill()
	{
		return dataWatcher.getWatchableObjectInt(17) != 0;
	}

	public void setStill(boolean still)
	{
		dataWatcher.updateObject(17, new Integer(still ? 1 : 0));
	}

	public boolean onTask()
	{
		return dataWatcher.getWatchableObjectInt(18) != 0;
	}

	public void setOnTask(boolean task)
	{
		dataWatcher.updateObject(18, new Integer(task ? 1 : 0));
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);
		nbt.setBoolean("Stay Still", isStill());
		nbt.setBoolean("On Task", onTask());
		nbt.setInteger("Type", getType().ordinal());
		inv.writeEntityToNBT(nbt);
		getTask().writeEntityToNBT(nbt);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);
		setStill(nbt.getBoolean("Stay Still"));
		setOnTask(nbt.getBoolean("On Task"));
		setType(RobotType.values()[nbt.getInteger("Type")]);
		inv.readEntityFromNBT(nbt);
		getTask().readEntityFromNBT(nbt);

		if (onTask())
		{
			startTask();
		}
	}

	@Override
	public EntityLivingBase getClone()
	{
		return new EntityVillager(worldObj);
	}
}
