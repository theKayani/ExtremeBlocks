package main.extremeblocks.entities.mobs;

import main.com.hk.eb.util.IReplacer;
import main.com.hk.eb.util.MPUtil;
import main.com.hk.eb.util.Rand;
import main.extremeblocks.Init;
import main.extremeblocks.Vars.Mob;
import net.minecraft.block.Block;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityWitherSkull;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

@Mob(getName = "Demon", getVanillaName = "Blaze")
public class EntityDemon extends EntityMob implements IRangedAttackMob, IReplacer
{
	private boolean sentOutZombies;

	public EntityDemon(World world)
	{
		super(world);
		isImmuneToFire = true;
		getNavigator().setCanSwim(true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIArrowAttack(this, 1.0D, 20, 20.0F));
		tasks.addTask(5, new EntityAIWander(this, 1.0D));
		tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(7, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityLivingBase.class, 0, false, false, attackEntitySelector));
		experienceValue = 50;
		setSize(0.6F, 1.8F);
	}

	@Override
	public boolean isAIEnabled()
	{
		return true;
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(120.0D);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.35D);
	}

	@Override
	public void onLivingUpdate()
	{
		MPUtil.replace(this);
		if (!onGround && motionY < 0.0D)
		{
			motionY *= 0.6D;
		}
		for (int k = 0; k < 2; ++k)
		{
			worldObj.spawnParticle("crit", posX + (rand.nextDouble() - 0.5D) * width, posY + rand.nextDouble() * height - 0.25D, posZ + (rand.nextDouble() - 0.5D) * width, (rand.nextDouble() - 0.5D) * 2.0D, -rand.nextDouble(), (rand.nextDouble() - 0.5D) * 2.0D);
		}
		if (isInWater())
		{
			for (int i = -2; i <= 2; i++)
			{
				for (int j = -2; j <= 2; j++)
				{
					for (int k = -2; k <= 2; k++)
					{
						if (worldObj.getBlock(MathHelper.floor_double(posX + i), MathHelper.floor_double(posY + j), MathHelper.floor_double(posZ + k)).getMaterial().isLiquid())
						{
							worldObj.setBlockToAir(MathHelper.floor_double(posX + i), MathHelper.floor_double(posY + j), MathHelper.floor_double(posZ + k));
						}
					}
				}
			}
		}
		super.onLivingUpdate();
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);
		nbt.setBoolean("Spawned Zombies", sentOutZombies);
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);
		sentOutZombies = nbt.getBoolean("Spawned Zombies");
	}

	@Override
	protected String getLivingSound()
	{
		return "mob.ghast.moan";
	}

	@Override
	protected String getHurtSound()
	{
		return "mob.ghast.scream";
	}

	@Override
	protected String getDeathSound()
	{
		return "mob.ghast.death";
	}

	@Override
	protected float getSoundPitch()
	{
		return super.getSoundPitch() - 1.0F;
	}

	@Override
	public int getTotalArmorValue()
	{
		return 3;
	}

	private double getForXCoord(int flag)
	{
		if (flag <= 0) return posX;
		else
		{
			float f = (renderYawOffset + 180 * (flag - 1)) / 180.0F * (float) Math.PI;
			float f1 = MathHelper.cos(f);
			return posX + f1 * 1.3D;
		}
	}

	private double getForYCoord(int flag)
	{
		return flag <= 0 ? posY + 3.0D : posY + 2.2D;
	}

	private double getForZCoord(int flag)
	{
		if (flag <= 0) return posZ;
		else
		{
			float f = (renderYawOffset + 180 * (flag - 1)) / 180.0F * (float) Math.PI;
			float f1 = MathHelper.sin(f);
			return posZ + f1 * 1.3D;
		}
	}

	private void shootWitherSkullAt(int flag, EntityLivingBase target)
	{
		this.shootWitherSkullAt(flag, target.posX, target.posY + target.getEyeHeight() / 2.0D, target.posZ, flag == 0 && rand.nextFloat() < 0.001F);
	}

	private void shootWitherSkullAt(int flag, double x, double y, double z, boolean invulSkull)
	{
		worldObj.playAuxSFXAtEntity((EntityPlayer) null, 1014, (int) posX, (int) posY, (int) posZ, 0);
		double d3 = getForXCoord(flag);
		double d4 = getForYCoord(flag);
		double d5 = getForZCoord(flag);
		double d6 = x - d3;
		double d7 = y - d4;
		double d8 = z - d5;
		EntityWitherSkull entitywitherskull = new EntityWitherSkull(worldObj, this, d6, d7, d8);

		if (invulSkull)
		{
			entitywitherskull.setInvulnerable(true);
		}

		entitywitherskull.posY = d4;
		entitywitherskull.posX = d3;
		entitywitherskull.posZ = d5;
		worldObj.spawnEntityInWorld(entitywitherskull);
	}

	/**
	 * Attack the specified entity using a ranged attack.
	 */
	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase entity, float f)
	{
		int c = 0;
		if (Rand.nextInt(6) < 3)
		{
			this.shootWitherSkullAt(0, entity);
		}
		else if (Rand.nextInt(6) == 0)
		{
			for (int i = 0; i < 5; i++)
			{
				EntityArrow e = new EntityArrow(worldObj, this, entity, 1.6F, 14 - worldObj.difficultySetting.getDifficultyId() * 4);
				playSound("random.bow", 1.0F, 1.0F / (getRNG().nextFloat() * 0.4F + 0.8F));
				worldObj.spawnEntityInWorld(e);
			}
		}
		else if (Rand.nextInt(6) == 0)
		{
			if (c < 20 && !teleportRandomly())
			{
				teleportRandomly();
				c++;
			}
		}
		if (isEnraged() && !sentOutZombies)
		{
			for (int i = -1; i <= 1; i++)
			{
				for (int j = -1; j <= 1; j++)
				{
					EntityPigZombie e = new EntityPigZombie(worldObj);
					e.setTarget(entity);
					e.setPosition(posX + i, posY, posZ + j);
					e.setCurrentItemOrArmor(0, new ItemStack(Items.golden_sword));
					worldObj.spawnEntityInWorld(e);
				}
			}
			sentOutZombies = true;
		}
		if (Rand.nextInt(6) == 0)
		{
			worldObj.setBlock(MathHelper.floor_double(entity.posX), MathHelper.floor_double(entity.posY), MathHelper.floor_double(entity.posZ), Blocks.web);
			worldObj.setBlock(MathHelper.floor_double(entity.posX), MathHelper.floor_double(entity.posY + 1), MathHelper.floor_double(entity.posZ), Blocks.web);
		}
		if (Rand.nextInt(6) == 0)
		{
			entity.motionY += 1.2D;
		}
		if (entity instanceof EntityPlayer && Rand.nextInt(25) == 0)
		{
			((EntityPlayer) entity).inventory.dropAllItems();
		}
	}

	@Override
	public void dropFewItems(boolean p_70628_1_, int p_70628_2_)
	{
		ItemStack stack = new ItemStack(Items.fireworks);

		if (stack.stackTagCompound == null)
		{
			stack.stackTagCompound = new NBTTagCompound();
		}
		stack.stackTagCompound.setByte("Type", (byte) 3);
		stack.stackTagCompound.setBoolean("Trail", true);
		stack.stackTagCompound.setIntArray("Colors", new int[] { 11743532 });

		for (int i = -2; i <= 2; i++)
		{
			EntityFireworkRocket entityfireworkrocket = new EntityFireworkRocket(worldObj, posX + i * 2, posY, posZ + i * 2, stack.copy());
			worldObj.spawnEntityInWorld(entityfireworkrocket);
			EntityFireworkRocket entityfireworkrocket2 = new EntityFireworkRocket(worldObj, posX + i * 2, posY, posZ - i * 2, stack.copy());
			worldObj.spawnEntityInWorld(entityfireworkrocket2);
		}

		dropItem(Init.spirit_fragment, 1);

		for (int i = -2; i <= 2; i++)
		{
			for (int j = -2; j <= 2; j++)
			{
				for (int k = -2; k <= 2; k++)
				{
					EntityItem item = new EntityItem(worldObj, posX + i * 2, posY + j * 2, posZ + k * 2);
					item.delayBeforeCanPickup = 10;
					item.setEntityItemStack(new ItemStack(Items.rotten_flesh, 2));
					worldObj.spawnEntityInWorld(item);
				}
			}
		}
	}

	@Override
	protected void fall(float p_70069_1_)
	{
	}

	public boolean isEnraged()
	{
		return getHealth() <= getMaxHealth() / 2.0D;
	}

	protected boolean teleportRandomly()
	{
		double d0 = posX + (rand.nextDouble() - 0.5D) * 64.0D;
		double d1 = posY + (rand.nextInt(64) - 32);
		double d2 = posZ + (rand.nextDouble() - 0.5D) * 64.0D;
		return teleportTo(d0, d1, d2);
	}

	protected boolean teleportTo(double p_70825_1_, double p_70825_3_, double p_70825_5_)
	{
		double d3 = posX;
		double d4 = posY;
		double d5 = posZ;
		boolean flag = false;
		int i = MathHelper.floor_double(posX);
		int j = MathHelper.floor_double(posY);
		int k = MathHelper.floor_double(posZ);

		if (worldObj.blockExists(i, j, k))
		{
			boolean flag1 = false;

			while (!flag1 && j > 0)
			{
				Block block = worldObj.getBlock(i, j - 1, k);

				if (block.getMaterial().blocksMovement())
				{
					flag1 = true;
				}
				else
				{
					--posY;
					--j;
				}
			}

			if (flag1)
			{
				setPosition(posX, posY, posZ);

				if (worldObj.getCollidingBoundingBoxes(this, boundingBox).isEmpty() && !worldObj.isAnyLiquid(boundingBox))
				{
					flag = true;
				}
			}
		}

		if (!flag)
		{
			setPosition(d3, d4, d5);
			return false;
		}
		else
		{
			short short1 = 128;

			for (int l = 0; l < short1; ++l)
			{
				double d6 = l / (short1 - 1.0D);
				float f = (rand.nextFloat() - 0.5F) * 0.2F;
				float f1 = (rand.nextFloat() - 0.5F) * 0.2F;
				float f2 = (rand.nextFloat() - 0.5F) * 0.2F;
				double d7 = d3 + (posX - d3) * d6 + (rand.nextDouble() - 0.5D) * width * 2.0D;
				double d8 = d4 + (posY - d4) * d6 + rand.nextDouble() * height;
				double d9 = d5 + (posZ - d5) * d6 + (rand.nextDouble() - 0.5D) * width * 2.0D;
				worldObj.spawnParticle("portal", d7, d8, d9, f, f1, f2);
			}

			worldObj.playSoundEffect(d3, d4, d5, "mob.endermen.portal", 1.0F, 1.0F);
			playSound("mob.endermen.portal", 1.0F, 1.0F);
			return true;
		}
	}

	@Override
	public void setInWeb()
	{
	}

	@Override
	public EntityBlaze getClone()
	{
		return new EntityBlaze(worldObj);
	}

	private static final IEntitySelector attackEntitySelector = new IEntitySelector()
	{
		@Override
		public boolean isEntityApplicable(Entity e)
		{
			return e instanceof EntityPlayer || e instanceof EntityAnimal;
		}
	};
}
