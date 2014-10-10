package main.extremeblocks.entities.mobs;

import main.com.hk.eb.util.MPUtil;
import main.com.hk.eb.util.Rand;
import main.extremeblocks.Init;
import main.extremeblocks.Vars;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityEvilIronGolem extends EntityMob implements MobSelectors
{
	public int attackTimer;
	private boolean isPlayerCreated;

	public EntityEvilIronGolem(World world)
	{
		super(world);
		setSize(1.4F, 2.9F);
		getNavigator().setAvoidsWater(true);
		tasks.addTask(1, new EntityAIAttackOnCollide(this, 1.0D, true));
		tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 0.9D, 32.0F));
		tasks.addTask(3, new EntityAIMoveThroughVillage(this, 0.6D, true));
		tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
		tasks.addTask(5, new EntityAIWander(this, 0.6D));
		tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(7, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, false, true));
	}

	@Override
	public boolean isAIEnabled()
	{
		return true;
	}

	public boolean isPlayerCreated()
	{
		return isPlayerCreated;
	}

	public void setPlayerCreated(boolean isPlayerCreated)
	{
		this.isPlayerCreated = isPlayerCreated;
	}

	public EntityIronGolem newVanillaClone()
	{
		EntityIronGolem golem = new EntityIronGolem(worldObj);
		golem.copyLocationAndAnglesFrom(this);
		golem.onSpawnWithEgg(null);
		return golem;
	}

	@Override
	public boolean attackEntityAsMob(Entity entity)
	{
		attackTimer = 10;
		worldObj.setEntityState(this, (byte) 4);
		boolean flag = entity.attackEntityFrom(DamageSource.causeMobDamage(this), 7 + rand.nextInt(15));
		if (flag)
		{
			entity.motionY += 0.4000000059604645D;
		}
		playSound("mob.irongolem.throw", 1.0F, 1.0F);
		return flag;
	}

	@Override
	public void onLivingUpdate()
	{
		if (MPUtil.isServerSide() && !Vars.addEvilIronGolem)
		{
			worldObj.removeEntity(this);
			worldObj.spawnEntityInWorld(newVanillaClone());
			return;
		}
		super.onLivingUpdate();
		if (attackTimer > 0)
		{
			--attackTimer;
		}
		if (motionX * motionX + motionZ * motionZ > 2.500000277905201E-7D && rand.nextInt(5) == 0)
		{
			int i = MathHelper.floor_double(posX);
			int j = MathHelper.floor_double(posY - 0.20000000298023224D - yOffset);
			int k = MathHelper.floor_double(posZ);
			Block block = worldObj.getBlock(i, j, k);
			if (block.getMaterial() != Material.air)
			{
				worldObj.spawnParticle("blockcrack_" + Block.getIdFromBlock(block) + "_" + worldObj.getBlockMetadata(i, j, k), posX + (rand.nextFloat() - 0.5D) * width, boundingBox.minY + 0.1D, posZ + (rand.nextFloat() - 0.5D) * width, 4.0D * (rand.nextFloat() - 0.5D), 0.5D, (rand.nextFloat() - 0.5D) * 4.0D);
			}
		}
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);
		nbt.setInteger("Attack Timer", attackTimer);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);
		attackTimer = nbt.getInteger("Attack Timer");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void handleHealthUpdate(byte state)
	{
		if (state == 4)
		{
			attackTimer = 10;
			playSound("mob.irongolem.throw", 1.0F, 1.0F);
		}
		else
		{
			super.handleHealthUpdate(state);
		}
	}

	@Override
	protected void dropFewItems(boolean iAmUseless, int chance)
	{
		int j = rand.nextInt(3);
		int k;
		for (k = 0; k < j; ++k)
		{
			dropItem(Item.getItemFromBlock(Blocks.yellow_flower), 1);
		}
		k = 3 + rand.nextInt(3);
		for (int l = 0; l < k; ++l)
		{
			dropItem(Init.trinquantium_ingot, 1);
		}
	}

	@Override
	protected String getHurtSound()
	{
		return "mob.irongolem.hit";
	}

	@Override
	protected String getDeathSound()
	{
		return "mob.irongolem.death";
	}

	@Override
	protected void func_145780_a(int x, int y, int z, Block block)
	{
		playSound("mob.irongolem.walk", 1.0F, 1.0F);
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(100.0D);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
	}

	@Override
	protected int decreaseAirSupply(int supply)
	{
		return supply;
	}

	@Override
	protected void collideWithEntity(Entity entity)
	{
		if (entity.isCreatureType(EnumCreatureType.creature, false) && Rand.nextInt(20) == 0)
		{
			setAttackTarget((EntityLivingBase) entity);
		}
		super.collideWithEntity(entity);
	}
}
