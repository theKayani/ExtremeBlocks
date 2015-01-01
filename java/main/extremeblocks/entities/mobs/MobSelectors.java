package main.extremeblocks.entities.mobs;

import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;

public interface MobSelectors
{
	public static final IEntitySelector onlyPlayer = new IEntitySelector()
	{
		@Override
		public boolean isEntityApplicable(Entity entity)
		{
			return entity instanceof EntityPlayer;
		}
	};
	public static final IEntitySelector allCastleMobs = new IEntitySelector()
	{
		@Override
		public boolean isEntityApplicable(Entity entity)
		{
			return entity instanceof EntityCastleZombie || entity instanceof EntityCastleSkeleton;
		}
	};
	public static final IEntitySelector allCastleMobsAndPlayer = new IEntitySelector()
	{
		@Override
		public boolean isEntityApplicable(Entity entity)
		{
			return allCastleMobs.isEntityApplicable(entity) || onlyPlayer.isEntityApplicable(entity);
		}
	};
	public static final IEntitySelector allButCastleMobs = new IEntitySelector()
	{
		@Override
		public boolean isEntityApplicable(Entity entity)
		{
			return !(entity instanceof EntityCastleZombie || entity instanceof EntityCastleSkeleton);
		}
	};
	public static final IEntitySelector vanillaSkeletonsAndZombies = new IEntitySelector()
	{
		@Override
		public boolean isEntityApplicable(Entity entity)
		{
			return !(entity instanceof EntityCastleZombie) && entity instanceof EntityZombie || !(entity instanceof EntityCastleSkeleton) && entity instanceof EntitySkeleton;
		}
	};
	public static final IEntitySelector vanillaSkeletonsAndZombiesAndPlayer = new IEntitySelector()
	{
		@Override
		public boolean isEntityApplicable(Entity entity)
		{
			return vanillaSkeletonsAndZombies.isEntityApplicable(entity) || onlyPlayer.isEntityApplicable(entity);
		}
	};
	public static final IEntitySelector allHostileMobs = new IEntitySelector()
	{
		@Override
		public boolean isEntityApplicable(Entity entity)
		{
			return IMob.mobSelector.isEntityApplicable(entity);
		}
	};
	public static final IEntitySelector allHostileMobsAndPlayer = new IEntitySelector()
	{
		@Override
		public boolean isEntityApplicable(Entity entity)
		{
			return allHostileMobs.isEntityApplicable(entity) || onlyPlayer.isEntityApplicable(entity);
		}
	};
	public static final IEntitySelector allPeacefulMobs = new IEntitySelector()
	{
		@Override
		public boolean isEntityApplicable(Entity entity)
		{
			return !IMob.mobSelector.isEntityApplicable(entity);
		}
	};
	public static final IEntitySelector allPeacefulMobsExceptPlayer = new IEntitySelector()
	{
		@Override
		public boolean isEntityApplicable(Entity entity)
		{
			return allPeacefulMobs.isEntityApplicable(entity) && !onlyPlayer.isEntityApplicable(entity);
		}
	};
	public static final IEntitySelector allPeacefulMobsExceptPlayerAndRobots = new IEntitySelector()
	{
		@Override
		public boolean isEntityApplicable(Entity entity)
		{
			return allPeacefulMobsExceptPlayer.isEntityApplicable(entity) && !(entity instanceof EntityRobot);
		}
	};
}
