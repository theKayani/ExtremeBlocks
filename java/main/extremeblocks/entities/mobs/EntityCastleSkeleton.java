package main.extremeblocks.entities.mobs;

import main.com.hk.eb.util.IReplacer;
import main.com.hk.eb.util.MPUtil;
import main.extremeblocks.Vars.Mob;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

@Mob(getName = "Castle Skeleton", getVanillaName = "Skeleton")
public class EntityCastleSkeleton extends EntitySkeleton implements MobSelectors, IReplacer
{
	private boolean atCastle = false;
	private final EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack(this, 1.0D, 20, 30, 15.0F);
	private final EntityAIAttackOnCollide aiAttackOnCollide = new EntityAIAttackOnCollide(this, EntityLiving.class, 1.2D, false);

	public EntityCastleSkeleton(World world)
	{
		super(world);
		tasks.taskEntries.clear();
		targetTasks.taskEntries.clear();
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIRestrictSun(this));
		tasks.addTask(3, new EntityAIFleeSun(this, 1.0D));
		tasks.addTask(5, new EntityAIWander(this, 1.0D));
		tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(6, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		if (world != null && !world.isRemote)
		{
			setCombatTask();
		}
	}

	@Override
	public void setCombatTask()
	{
		tasks.removeTask(aiAttackOnCollide);
		tasks.removeTask(aiArrowAttack);
		if (getHeldItem() != null && getHeldItem().getItem() == Items.bow)
		{
			tasks.addTask(4, aiArrowAttack);
		}
		else
		{
			tasks.addTask(4, aiAttackOnCollide);
		}
	}

	public EntityCastleSkeleton setAtCastle()
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
		ItemStack bow = new ItemStack(Items.bow);
		bow.addEnchantment(Enchantment.punch, Enchantment.punch.getMaxLevel());
		setCurrentItemOrArmor(0, bow);
	}

	@Override
	public void onLivingUpdate()
	{
		MPUtil.replace(this);
		extinguish();
		super.onLivingUpdate();
	}

	@Override
	protected boolean isValidLightLevel()
	{
		return true;
	}

	@Override
	protected boolean canDespawn()
	{
		return !atCastle;
	}

	@Override
	public EntityLivingBase getClone()
	{
		return new EntitySkeleton(worldObj);
	}
}
