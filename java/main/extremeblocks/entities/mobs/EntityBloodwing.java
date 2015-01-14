package main.extremeblocks.entities.mobs;

import main.com.hk.eb.util.IReplacer;
import main.com.hk.eb.util.MPUtil;
import main.com.hk.eb.util.Rand;
import main.com.hk.eb.util.Vector3F;
import main.com.hk.eb.util.Vector3I;
import main.extremeblocks.Vars.Mob;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

@Mob(getName = "Bloodwing", getVanillaName = "Bat")
public class EntityBloodwing extends EntityTameable implements IReplacer
{
	private Vector3F pos;

	public EntityBloodwing(World world)
	{
		super(world);
		setSize(0.5F, 0.9F);
		tasks.addTask(1, new EntityAIAttackOnCollide(this, 1.0D, true));
		tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(2, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
		targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
		targetTasks.addTask(3, new EntityAIHurtByTarget(this, true));
		setTamed(false);
		setCustomNameTag("Bloodwing");
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(isTamed() ? 20.0D : 8.0D);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		dataWatcher.addObject(18, new Integer(0));
	}

	public int getHealthCounter()
	{
		return dataWatcher.getWatchableObjectInt(18);
	}

	public void setHealthCounter(int healthCounter)
	{
		dataWatcher.updateObject(18, new Integer(healthCounter));
	}

	@Override
	public boolean interact(EntityPlayer player)
	{
		ItemStack itemstack = player.inventory.getCurrentItem();
		if (!isTamed() && itemstack != null && itemstack.getItem() == Items.spider_eye && player.getDistanceSqToEntity(this) < 9.0D)
		{
			if (!player.capabilities.isCreativeMode)
			{
				--itemstack.stackSize;
			}

			if (itemstack.stackSize <= 0)
			{
				player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
			}

			if (!worldObj.isRemote)
			{
				if (rand.nextInt(3) == 0)
				{
					setOwner(player);
					playTameEffect(true);
					aiSit.setSitting(true);
					worldObj.setEntityState(this, (byte) 7);
				}
				else
				{
					playTameEffect(false);
					worldObj.setEntityState(this, (byte) 6);
				}
			}

			return true;
		}

		return super.interact(player);
	}

	public void setOwner(EntityPlayer player)
	{
		setCustomNameTag(player.getCommandSenderName() + "'s Bloodwing");
		setTamed(true);
		super.func_152115_b(player.getUniqueID().toString());
	}

	@Override
	public void onUpdate()
	{
		MPUtil.replace(this);
		super.onUpdate();
		if (pos == null || pos.y < posY)
		{
			motionY *= 0.6000000238418579D;
		}
		setHealthCounter(getHealthCounter() + 1);
		if (getHealthCounter() > 60)
		{
			if (MPUtil.isClientSide())
			{
				playTameEffect(true);
				playLivingSound();
			}
			else if (MPUtil.isServerSide())
			{
				setHealth(getHealth() + 1);
			}
			setHealthCounter(0);
		}
	}

	@Override
	protected void updateAITasks()
	{
		super.updateAITasks();
		boolean hasOwner = getOwner() != null;
		if (isTamed() && hasOwner)
		{
			if (getAITarget() == null && getOwner().getAITarget() == null)
			{
				if (getOwner().getDistanceToEntity(this) >= 7.0F)
				{
					moveTo(new Vector3F(getOwner()).addLocal(0, 0.12F, 0));
				}
				if (getDistanceToEntity(getOwner()) >= 12.0F)
				{
					int i = MathHelper.floor_double(getOwner().posX) - 2;
					int j = MathHelper.floor_double(getOwner().posZ) - 2;
					int k = MathHelper.floor_double(getOwner().boundingBox.minY);

					for (int l = 0; l <= 4; ++l)
					{
						for (int i1 = 0; i1 <= 4; ++i1)
						{
							if ((l < 1 || i1 < 1 || l > 3 || i1 > 3) && World.doesBlockHaveSolidTopSurface(worldObj, i + l, k - 1, j + i1) && !worldObj.getBlock(i + l, k, j + i1).isNormalCube() && !worldObj.getBlock(i + l, k + 1, j + i1).isNormalCube())
							{
								setLocationAndAngles(i + l + 0.5F, k, j + i1 + 0.5F, rotationYaw, rotationPitch);
								getNavigator().clearPathEntity();
								return;
							}
						}
					}
				}
			}
			else if (getAITarget() != null || getOwner().getAITarget() != null)
			{
				EntityLivingBase e = getAITarget() == null ? getOwner().getAITarget() : getAITarget();
				setAttackTarget(e);
				moveTo(new Vector3F(e).addLocal(0, e.height, 0));
			}
		}
		else if (Rand.nextInt(30) == 0)
		{
			moveTo(new Vector3F(this).add(Rand.nextInt(7), Rand.nextInt(7), Rand.nextInt(7)).add(-Rand.nextInt(7), -Rand.nextInt(7), -Rand.nextInt(7)));
		}
	}

	public void moveTo(Vector3F v)
	{
		Vector3I f = v.floored();
		pos = !worldObj.isAirBlock(f.x, f.y, f.z) ? getClosestVecTo(v) : v;
		pos.y += 0.6F;
		move();
		pos = null;
	}

	public Vector3F getClosestVecTo(Vector3F pos)
	{
		Vector3F v = pos.clone();
		Vector3I f = v.floored();
		return !worldObj.isAirBlock(f.x, f.y, f.z) || v.distance(pos) > 3 ? getClosestVecTo(pos.add(Rand.nextInt(7), Rand.nextInt(7), Rand.nextInt(7)).add(-Rand.nextInt(7), -Rand.nextInt(7), -Rand.nextInt(7))) : v;
	}

	public void move()
	{
		if (pos == null)
		{
			pos = new Vector3F(this);
		}
		double d0 = pos.x + 0.5D - posX;
		double d1 = pos.y + 0.1D - posY;
		double d2 = pos.z + 0.5D - posZ;
		motionX += (Math.signum(d0) * 0.5D - motionX) * 0.10000000149011612D;
		motionY += (Math.signum(d1) * 0.699999988079071D - motionY) * 0.10000000149011612D;
		motionZ += (Math.signum(d2) * 0.5D - motionZ) * 0.10000000149011612D;
		float f = (float) (Math.atan2(motionZ, motionX) * 180.0D / Math.PI) - 90.0F;
		float f1 = MathHelper.wrapAngleTo180_float(f - rotationYaw);
		moveForward = 0.5F;
		rotationYaw += f1;
	}

	@Override
	public void setTamed(boolean tamed)
	{
		super.setTamed(tamed);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(tamed ? 20.0D : 8.0D);
	}

	@Override
	public boolean attackEntityAsMob(Entity mob)
	{
		return mob.attackEntityFrom(DamageSource.causeMobDamage(this), isTamed() ? 7.0F : 5.0F);
	}

	@Override
	public EntityLivingBase getClone()
	{
		return new EntityBat(worldObj);
	}

	@Override
	public EntityAgeable createChild(EntityAgeable p_90011_1_)
	{
		return null;
	}

	@Override
	public boolean canBePushed()
	{
		return false;
	}

	@Override
	protected void collideWithEntity(Entity p_82167_1_)
	{
	}

	@Override
	protected void collideWithNearbyEntities()
	{
	}

	@Override
	protected boolean isAIEnabled()
	{
		return true;
	}

	@Override
	protected boolean canTriggerWalking()
	{
		return false;
	}

	@Override
	protected void fall(float p_70069_1_)
	{
	}

	@Override
	protected void updateFallState(double p_70064_1_, boolean p_70064_3_)
	{
	}

	@Override
	public boolean doesEntityNotTriggerPressurePlate()
	{
		return true;
	}

	@Override
	protected boolean canDespawn()
	{
		return !isTamed() && super.canDespawn();
	}

	@Override
	public boolean canMateWith(EntityAnimal animal)
	{
		return false;
	}

	@Override
	public boolean isBreedingItem(ItemStack stack)
	{
		return false;
	}

	@Override
	protected float getSoundVolume()
	{
		return 0.1F;
	}

	@Override
	protected float getSoundPitch()
	{
		return super.getSoundPitch() * 0.95F;
	}

	@Override
	protected String getLivingSound()
	{
		return Rand.nextInt(4) != 0 ? null : "mob.bat.idle";
	}

	@Override
	protected String getHurtSound()
	{
		return "mob.bat.hurt";
	}

	@Override
	protected String getDeathSound()
	{
		return "mob.bat.death";
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);
		nbt.setInteger("Health Counter", getHealthCounter());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);
		if (getOwner() instanceof EntityPlayer)
		{
			setOwner((EntityPlayer) getOwner());
		}

		setHealthCounter(nbt.getInteger("Health Counter"));
	}
}
