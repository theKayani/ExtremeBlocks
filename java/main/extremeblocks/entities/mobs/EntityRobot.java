package main.extremeblocks.entities.mobs;

import java.util.Random;
import main.com.hk.eb.util.JavaHelp;
import main.com.hk.eb.util.MPUtil;
import main.extremeblocks.ExtremeBlocks;
import main.extremeblocks.GuiHandler;
import main.extremeblocks.GuiIDs;
import main.extremeblocks.Init;
import main.extremeblocks.Vars;
import main.extremeblocks.entities.mobs.ai.EntityAIHarvestCrops;
import main.extremeblocks.entities.mobs.ai.EntityAIRobotMine;
import main.extremeblocks.entities.mobs.ai.EntityAISwitchWeapons;
import main.extremeblocks.network.packets.PacketSyncRobot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITempt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityRobot extends EntityCreature implements MobSelectors, IRangedAttackMob, GuiIDs
{
	public boolean stayStill, isOff, hasHome;
	public int[] homePosition = { 0, 0, 0 };
	private int hitCounter;
	public RobotType type;
	public RobotInventory inv = new RobotInventory(this);
	public final EntityAINearestAttackableTarget aiHostileMobAttack;
	public final EntityAINearestAttackableTarget aiPeacefulMobAttack;
	public final EntityAIHarvestCrops aiHarvestCrops;
	public final EntityAIRobotMine aiRobotMine;
	public final EntityAIAttackOnCollide aiAttackOnCollide;
	public final EntityAIArrowAttack aiArrowAttack;
	public final EntityAIBase aiSwitchWeapons;
	private int counter;
	public boolean startMining, onTask;
	private int foodDelay;
	public boolean isRanging;

	public EntityRobot(World world)
	{
		super(world);
		setSize(0.6F, 1.8F);
		getNavigator().setAvoidsWater(true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAITempt(this, 1.5D, Items.gold_nugget, false));
		tasks.addTask(4, new EntityAIWander(this, 1.0D));
		tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(6, new EntityAILookIdle(this));
		counter = 300;

		aiSwitchWeapons = new EntityAISwitchWeapons(this, 1.0D, 20, 60, 15.0F, Items.iron_sword, Items.bow);
		aiAttackOnCollide = new EntityAIAttackOnCollide(this, IMob.class, 1.0D, true);
		aiHostileMobAttack = new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, true, true, IMob.mobSelector);
		aiPeacefulMobAttack = new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, true, true, allPeacefulMobsExceptPlayerAndRobots);
		aiHarvestCrops = new EntityAIHarvestCrops(this, 30);
		aiRobotMine = new EntityAIRobotMine(this, 10);
		aiArrowAttack = new EntityAIArrowAttack(this, 1.0D, 20, 60, 15.0F);
	}

	@Override
	public boolean isAIEnabled()
	{
		return true;
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
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(50.0D);
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase p_82196_1_, float p_82196_2_)
	{
		EntityArrow entityarrow = new EntityArrow(worldObj, this, p_82196_1_, 1.6F, 14 - worldObj.difficultySetting.getDifficultyId() * 4);
		int i = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, getHeldItem());
		int j = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, getHeldItem());
		entityarrow.setDamage(p_82196_2_ * 2.0F + rand.nextGaussian() * 0.25D + worldObj.difficultySetting.getDifficultyId() * 0.11F);

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
	protected void updateAITasks()
	{
		super.updateAITasks();
		setCombatTask();
	}

	@Override
	protected boolean isMovementCeased()
	{
		return stayStill;
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

	public void setCombatTask()
	{
		if (type == RobotType.HUNTER)
		{
			endTask();
			if (isRanging)
			{
				tasks.addTask(2, aiArrowAttack);
			}
			else
			{
				tasks.addTask(2, aiAttackOnCollide);
			}
		}
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
		return true;
	}

	@Override
	protected void dropFewItems(boolean flag, int looting)
	{
		if (looting > 0)
		{
			dropItem(Init.power_core, 1);
			dropItem(Init.robot_arm, 2);
			dropItem(Init.robot_head, 1);
			dropItem(Init.robot_leg, 2);
			dropItem(Init.robot_torso, 1);
		}
		else
		{
			dropItem(Init.power_core, getRNG().nextInt(2));
			dropItem(Init.robot_arm, getRNG().nextInt(3));
			dropItem(Init.robot_head, getRNG().nextInt(2));
			dropItem(Init.robot_leg, getRNG().nextInt(3));
			dropItem(Init.robot_torso, getRNG().nextInt(2));
		}
	}

	@Override
	public void onLivingUpdate()
	{
		if (MPUtil.isServerSide())
		{
			if (!Vars.addRobot)
			{
				worldObj.removeEntity(this);
				worldObj.spawnEntityInWorld(newVanillaClone());
				return;
			}
			if (isWet() && hitCounter++ >= 100)
			{
				hitCounter = 0;
				attackEntityFrom(DamageSource.drown, 1.0F);
			}
			if (foodDelay++ > 40)
			{
				if (getHealth() / getMaxHealth() < 0.50F)
				{
					inv.eatFood();
				}
				foodDelay = 0;
			}
			if (counter++ > 200)
			{
				syncServerAndClient(false);
				counter = 0;
			}
		}
		if (hasHome && !workingTask())
		{
			getNavigator().tryMoveToXYZ(homePosition[0], homePosition[1], homePosition[2], 1.0F);
		}
		super.onLivingUpdate();
	}

	public boolean workingTask()
	{
		return stayStill || onTask;
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		super.writeEntityToNBT(nbt);
		nbt.setBoolean("Stay Still", stayStill);
		nbt.setBoolean("Is Off", isOff);
		nbt.setBoolean("On Task", onTask);
		nbt.setInteger("Type", type.ordinal());
		nbt.setInteger("Water Count", hitCounter);
		nbt.setInteger("Food Delay", foodDelay);
		nbt.setBoolean("Has Home", hasHome);
		nbt.setIntArray("Home Position", homePosition);
		inv.writeEntityToNBT(nbt);
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);
		stayStill = nbt.getBoolean("Stay Still");
		isOff = nbt.getBoolean("Is Off");
		onTask = nbt.getBoolean("On Task");
		type = RobotType.values()[nbt.getInteger("Type")];
		hitCounter = nbt.getInteger("Water Counter");
		foodDelay = nbt.getInteger("Food Delay");
		hasHome = nbt.getBoolean("Has Home");
		homePosition = nbt.getIntArray("Home Position");
		inv.readEntityFromNBT(nbt);

		if (onTask)
		{
			beginTask(null, true);
		}
	}

	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return player.getDistance(posX, posY, posZ) <= 8.0D;
	}

	public void endTask()
	{
		onTask = false;
		targetTasks.removeTask(aiHostileMobAttack);
		targetTasks.removeTask(aiPeacefulMobAttack);
		tasks.removeTask(aiHarvestCrops);
		tasks.removeTask(aiRobotMine);
		tasks.removeTask(aiArrowAttack);
		tasks.removeTask(aiSwitchWeapons);
		tasks.removeTask(aiAttackOnCollide);
	}

	public void beginTask(EntityPlayer player, boolean justBegin)
	{
		if (justBegin || canBeginTask(player))
		{
			endTask();
			switch (type)
			{
				case ARCHER:
				{
					tasks.addTask(2, aiArrowAttack);
					targetTasks.addTask(1, aiHostileMobAttack);
					break;
				}
				case FARMER:
				{
					tasks.addTask(2, aiHarvestCrops);
					break;
				}
				case HUNTER:
				{
					targetTasks.addTask(1, aiPeacefulMobAttack);
					tasks.addTask(2, aiSwitchWeapons);
					break;
				}
				case MINER:
				{
					startMining = true;
					aiRobotMine.top = posY + 3;
					tasks.addTask(2, aiRobotMine);
					break;
				}
				case WARRIOR:
				{
					tasks.addTask(2, aiAttackOnCollide);
					targetTasks.addTask(1, aiHostileMobAttack);
					break;
				}
				default:
				{
					break;
				}
			}
		}
		syncServerAndClient(false);
		onTask = true;
	}

	public boolean canBeginTask(EntityPlayer player)
	{
		boolean able = true;
		switch (type)
		{
			case ARCHER:
			{
				if (getHeldItem() == null || !(getHeldItem().getItem() instanceof ItemBow))
				{
					able = false;
					MPUtil.sendMessage("Master, I can't begin! I need a Bow!", player);
				}
				break;
			}
			case FARMER:
			{
				if (getHeldItem() == null || !(getHeldItem().getItem() instanceof ItemHoe))
				{
					able = false;
					MPUtil.sendMessage("Master, I can't begin! I need a Hoe!", player);
				}
				break;
			}
			case HUNTER:
			{
				if (getHeldItem() == null || !(getHeldItem().getItem() == Items.compass))
				{
					able = false;
					MPUtil.sendMessage("Master, I can't begin! I need a Compass!", player);
				}
				break;
			}
			case MINER:
			{
				if (getHeldItem() == null || !(getHeldItem().getItem() instanceof ItemPickaxe))
				{
					able = false;
					MPUtil.sendMessage("Master, I can't begin! I need a Pickaxe!", player);
				}
				break;
			}
			case WARRIOR:
			{
				if (getHeldItem() == null || !(getHeldItem().getItem() instanceof ItemSword))
				{
					able = false;
					MPUtil.sendMessage("Master, I can't begin! I need a Sword!", player);
				}
				break;
			}
			default:
			{
				able = false;
				MPUtil.sendMessage("Master, What is the meaning of Life? Why Am I HERE? RIGHT NOW?", player);
				break;
			}
		}
		if (able)
		{
			MPUtil.sendMessage("Thank you Master! I will not let you down!", player);
			MPUtil.sendMessage("I will bring you back the finest of fine stuff!", player);
		}
		return able;
	}

	@Override
	public IEntityLivingData onSpawnWithEgg(IEntityLivingData data)
	{
		type = JavaHelp.getRandomElementFrom(RobotType.values());
		return super.onSpawnWithEgg(data);
	}

	public void syncServerAndClient(boolean startTask)
	{
		if (MPUtil.isServerSide())
		{
			MPUtil.sendToAll(new PacketSyncRobot(this, startTask));
		}
		if (MPUtil.isClientSide())
		{
			MPUtil.sendToServer(new PacketSyncRobot(this, startTask));
		}
	}

	public Entity newVanillaClone()
	{
		EntityVillager villager = new EntityVillager(worldObj);
		villager.copyLocationAndAnglesFrom(this);
		villager.onSpawnWithEgg(null);
		return villager;
	}

	@Override
	protected boolean canDespawn()
	{
		return false;
	}

	public static enum RobotType
	{
		WARRIOR(Items.golden_sword), FARMER(Items.golden_hoe), MINER(Items.golden_pickaxe), HUNTER(Items.compass), ARCHER(Items.bow);
		public final String name;
		public final Item[] items;

		private RobotType(Item... items)
		{
			name = name().charAt(0) + name().toLowerCase().substring(1);
			this.items = items;
		}
	}
}
