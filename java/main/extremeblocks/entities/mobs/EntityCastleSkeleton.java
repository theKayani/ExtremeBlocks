package main.extremeblocks.entities.mobs;

import main.com.hk.eb.util.MPUtil;
import main.extremeblocks.Vars;
import main.extremeblocks.worldgen.WorldGenCastle;
import net.minecraft.command.IEntitySelector;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityCastleSkeleton extends EntitySkeleton implements MobSelectors
{
	private boolean atCastle = false;
	private final EntityAIArrowAttack aiArrowAttack = new EntityAIArrowAttack(this, 1.0D, 20, 30, 15.0F);
	private final EntityAIAttackOnCollide aiAttackOnCollide = new EntityAIAttackOnCollide(this, EntityLiving.class, 1.2D, false);

	public static final IEntitySelector ai = new IEntitySelector()
	{
		@Override
		public boolean isEntityApplicable(Entity entity)
		{
			return entity instanceof EntityPlayer ? true : !(entity instanceof EntityCastleZombie || entity instanceof EntityCastleSkeleton);
		}
	};

	public EntityCastleSkeleton(World world)
	{
		super(world);
		this.tasks.taskEntries.clear();
		this.targetTasks.taskEntries.clear();
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIRestrictSun(this));
		this.tasks.addTask(3, new EntityAIFleeSun(this, 1.0D));
		this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(6, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, true, true, ai));
		if (world != null && !world.isRemote)
		{
			this.setCombatTask();
		}
	}

	@Override
	public void setCombatTask()
	{
		this.tasks.removeTask(this.aiAttackOnCollide);
		this.tasks.removeTask(this.aiArrowAttack);
		if (getHeldItem() != null && getHeldItem().getItem() == Items.bow)
		{
			this.tasks.addTask(4, this.aiArrowAttack);
		}
		else
		{
			this.tasks.addTask(4, this.aiAttackOnCollide);
		}
	}

	public EntityCastleSkeleton setAtCastle()
	{
		this.atCastle = true;
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
		this.setCurrentItemOrArmor(0, bow);
	}

	@Override
	public void onLivingUpdate()
	{
		if (MPUtil.isServerSide() && !Vars.addCastleSkeleton)
		{
			worldObj.removeEntity(this);
			worldObj.spawnEntityInWorld(newVanillaClone());
			return;
		}
		extinguish();
		super.onLivingUpdate();
	}

	@Override
	protected boolean isValidLightLevel()
	{
		return WorldGenCastle.isCastleInRangeOf((int) posX, (int) posZ, 20.0D);
	}

	@Override
	protected boolean canDespawn()
	{
		return !atCastle;
	}

	public Entity newVanillaClone()
	{
		EntitySkeleton skele = new EntitySkeleton(worldObj);
		skele.copyLocationAndAnglesFrom(this);
		skele.onSpawnWithEgg(null);
		return skele;
	}
}
