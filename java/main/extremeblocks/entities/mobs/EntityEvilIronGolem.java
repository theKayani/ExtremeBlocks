package main.extremeblocks.entities.mobs;

import main.com.hk.eb.util.MPUtil;
import main.com.hk.eb.util.Rand;
import main.com.hk.eb.util.Vector3F;
import main.com.hk.eb.util.WorldUtil;
import main.extremeblocks.Init;
import main.extremeblocks.Vars;
import main.extremeblocks.worldgen.WorldGenCastle;
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
	public int attackTimer, homeCheckTimer = 90 + this.rand.nextInt(50);
	private boolean isPlayerCreated, atCastle;

	public EntityEvilIronGolem(World world)
	{
		super(world);
		this.setSize(1.4F, 2.9F);
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(1, new EntityAIAttackOnCollide(this, 1.0D, true));
		this.tasks.addTask(2, new EntityAIMoveTowardsTarget(this, 0.9D, 32.0F));
		this.tasks.addTask(3, new EntityAIMoveThroughVillage(this, 0.6D, true));
		this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
		this.tasks.addTask(5, new EntityAIWander(this, 0.6D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, false, true, allButCastleMobs));
	}

	public boolean isAIEnabled()
	{
		return true;
	}

	public EntityEvilIronGolem setAtCastle()
	{
		atCastle = true;
		return this;
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

	public boolean attackEntityAsMob(Entity entity)
	{
		this.attackTimer = 10;
		this.worldObj.setEntityState(this, (byte) 4);
		boolean flag = entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float) (7 + this.rand.nextInt(15)));
		if (flag)
		{
			entity.motionY += 0.4000000059604645D;
		}
		this.playSound("mob.irongolem.throw", 1.0F, 1.0F);
		return flag;
	}

	public void onLivingUpdate()
	{
		if (MPUtil.isServerSide() && !Vars.addEvilIronGolem)
		{
			worldObj.removeEntity(this);
			worldObj.spawnEntityInWorld(newVanillaClone());
			return;
		}
		if (WorldGenCastle.isCastleInRangeOf((int) posX, (int) posZ, 50.0D) && !isPlayerCreated() && this.homeCheckTimer-- <= 0)
		{
			Vector3F pos = WorldGenCastle.getClosestCastleTo((int) posX, (int) posZ, 50.0D);
			this.homeCheckTimer = 90 + this.rand.nextInt(50);
			this.getNavigator().tryMoveToXYZ(pos.x, pos.y, pos.z, 1.0D);
		}
		super.onLivingUpdate();
		if (this.attackTimer > 0)
		{
			--this.attackTimer;
		}
		if (this.motionX * this.motionX + this.motionZ * this.motionZ > 2.500000277905201E-7D && this.rand.nextInt(5) == 0)
		{
			int i = MathHelper.floor_double(this.posX);
			int j = MathHelper.floor_double(this.posY - 0.20000000298023224D - (double) this.yOffset);
			int k = MathHelper.floor_double(this.posZ);
			Block block = this.worldObj.getBlock(i, j, k);
			if (block.getMaterial() != Material.air)
			{
				this.worldObj.spawnParticle("blockcrack_" + Block.getIdFromBlock(block) + "_" + this.worldObj.getBlockMetadata(i, j, k), this.posX + ((double) this.rand.nextFloat() - 0.5D) * (double) this.width, this.boundingBox.minY + 0.1D, this.posZ + ((double) this.rand.nextFloat() - 0.5D) * (double) this.width, 4.0D * ((double) this.rand.nextFloat() - 0.5D), 0.5D, ((double) this.rand.nextFloat() - 0.5D) * 4.0D);
			}
		}
	}

	protected boolean canDespawn()
	{
		return !atCastle;
	}

	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);
		nbt.setInteger("Attack Timer", this.attackTimer);
	}

	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);
		this.attackTimer = nbt.getInteger("Attack Timer");
	}

	@SideOnly(Side.CLIENT)
	public void handleHealthUpdate(byte state)
	{
		if (state == 4)
		{
			this.attackTimer = 10;
			this.playSound("mob.irongolem.throw", 1.0F, 1.0F);
		}
		else
		{
			super.handleHealthUpdate(state);
		}
	}

	protected void dropFewItems(boolean iAmUseless, int chance)
	{
		int j = this.rand.nextInt(3);
		int k;
		for (k = 0; k < j; ++k)
		{
			this.dropItem(Item.getItemFromBlock(Blocks.yellow_flower), 1);
		}
		k = 3 + this.rand.nextInt(3);
		for (int l = 0; l < k; ++l)
		{
			this.dropItem(Init.trinquantium_ingot, 1);
		}
	}

	protected String getHurtSound()
	{
		return "mob.irongolem.hit";
	}

	protected String getDeathSound()
	{
		return "mob.irongolem.death";
	}

	protected void func_145780_a(int x, int y, int z, Block block)
	{
		this.playSound("mob.irongolem.walk", 1.0F, 1.0F);
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(100.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
	}

	protected int decreaseAirSupply(int supply)
	{
		return supply;
	}

	protected void collideWithEntity(Entity entity)
	{
		if (entity.isCreatureType(EnumCreatureType.creature, false) && Rand.nextInt(20) == 0)
		{
			this.setAttackTarget((EntityLivingBase) entity);
		}
		super.collideWithEntity(entity);
	}
}
