package main.extremeblocks.entities.mobs;

import main.com.hk.testing.util.MPUtil;
import main.com.hk.testing.util.Rand;
import main.extremeblocks.Vars;
import main.extremeblocks.worldgen.WorldGenCastle;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
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
		this.tasks.taskEntries.clear();
		this.targetTasks.taskEntries.clear();
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityLiving.class, 1.0D, false));
		this.tasks.addTask(6, new EntityAIMoveTowardsRestriction(this, 1.0D));
		this.tasks.addTask(7, new EntityAIMoveThroughVillage(this, 1.0D, false));
		this.tasks.addTask(8, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(9, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, false, true, allButCastleMobs));
	}

	public void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0D);
		this.getEntityAttribute(field_110186_bp).setBaseValue(0.0D);
	}

	public EntityCastleZombie setAtCastle()
	{
		this.atCastle = true;
		return this;
	}

	public boolean attackEntityFrom(DamageSource ds, float h)
	{
		if (ds.isFireDamage()) return false;
		return super.attackEntityFrom(ds, h);
	}

	protected void addRandomArmor()
	{
		super.addRandomArmor();
		ItemStack stick = new ItemStack(Items.blaze_rod);
		stick.addEnchantment(Enchantment.knockback, Enchantment.knockback.getMaxLevel());
		if (Rand.nextInt(100) == 0) stick.addEnchantment(Enchantment.fireAspect, 1);
		this.setCurrentItemOrArmor(0, stick);
	}

	protected void dropRareDrop(int p_70600_1_)
	{
		switch (this.rand.nextInt(4))
		{
			case 0:
				this.dropItem(Items.gold_ingot, 1);
				break;
			case 1:
				this.dropItem(Items.blaze_powder, 3);
				break;
			case 2:
				this.dropItem(Items.cookie, 1);
				break;
			case 3:
				this.dropItem(Items.blaze_rod, 1);
				break;
		}
	}

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

	protected float getSoundPitch()
	{
		return super.getSoundPitch() - 1.0F;
	}

	protected boolean isValidLightLevel()
	{
		return WorldGenCastle.isCastleInRangeOf((int) posX, (int) posZ, 20.0D);
	}
}
