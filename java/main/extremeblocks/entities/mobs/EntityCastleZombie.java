package main.extremeblocks.entities.mobs;

import main.com.hk.eb.util.MPUtil;
import main.com.hk.eb.util.Rand;
import main.extremeblocks.Vars;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityCastleZombie extends EntityZombie implements MobSelectors
{
	private boolean atCastle = false;

	public EntityCastleZombie(World world)
	{
		super(world);
		tasks.taskEntries.clear();
		targetTasks.taskEntries.clear();
		getNavigator().setBreakDoors(true);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
		tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityVillager.class, 1.0D, true));
		tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
		tasks.addTask(6, new EntityAIMoveThroughVillage(this, 1.0D, false));
		tasks.addTask(7, new EntityAIWander(this, 1.0D));
		tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(8, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, false));
		setSize(0.6F, 1.8F);
	}

	@Override
	public void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0D);
		getEntityAttribute(field_110186_bp).setBaseValue(0.0D);
	}

	public EntityCastleZombie setAtCastle()
	{
		atCastle = true;
		return this;
	}

	@Override
	public boolean attackEntityFrom(DamageSource ds, float h)
	{
		if (ds.isFireDamage()) return false;
		return super.attackEntityFrom(ds, h);
	}

	@Override
	protected void addRandomArmor()
	{
		super.addRandomArmor();
		ItemStack stick = new ItemStack(Items.iron_shovel);
		stick.addEnchantment(Enchantment.knockback, Enchantment.knockback.getMaxLevel());
		if (Rand.nextInt(100) == 0)
		{
			stick.addEnchantment(Enchantment.fireAspect, 1);
		}
		setCurrentItemOrArmor(0, stick);
	}

	@Override
	protected void dropRareDrop(int p_70600_1_)
	{
		switch (rand.nextInt(4))
		{
			case 0:
				dropItem(Items.gold_ingot, 1);
				break;
			case 1:
				dropItem(Items.blaze_powder, 3);
				break;
			case 2:
				dropItem(Items.cookie, 1);
				break;
			case 3:
				dropItem(Items.blaze_rod, 1);
				break;
		}
	}

	@Override
	public void onLivingUpdate()
	{
		if (MPUtil.isServerSide() && !Vars.addCastleZombie)
		{
			worldObj.removeEntity(this);
			worldObj.spawnEntityInWorld(newVanillaClone());
			return;
		}
		extinguish();
		super.onLivingUpdate();
	}

	@Override
	protected boolean canDespawn()
	{
		return !(atCastle || isConverting());
	}

	public Entity newVanillaClone()
	{
		EntityZombie zombie = new EntityZombie(worldObj);
		zombie.copyLocationAndAnglesFrom(this);
		zombie.onSpawnWithEgg(null);
		return zombie;
	}

	@Override
	protected float getSoundPitch()
	{
		return super.getSoundPitch() - 1.0F;
	}

	@Override
	protected boolean isValidLightLevel()
	{
		return true;
	}
}
