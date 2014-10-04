package main.extremeblocks.worldgen;

import java.util.ArrayList;
import java.util.Random;
import main.com.hk.eb.util.Builder;
import main.com.hk.eb.util.JavaHelp;
import main.com.hk.eb.util.Rand;
import main.com.hk.eb.util.Vector3F;
import main.com.hk.eb.util.Builder.ChestType;
import main.extremeblocks.Vars;
import main.extremeblocks.entities.mobs.EntityCastleSkeleton;
import main.extremeblocks.entities.mobs.EntityCastleZombie;
import main.extremeblocks.entities.mobs.EntityEvilIronGolem;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenCastle extends WorldGenerator
{
	public static final ArrayList<Vector3F> positions;
	private final boolean netherish;
	static
	{
		positions = JavaHelp.newArrayList();
	}

	public WorldGenCastle()
	{
		netherish = Rand.nextBoolean();
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z)
	{
		if (!Vars.genCastle) return false;
		Builder helper = new Builder(world, x, y, z);
		for (int i = -4; i < 5; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				for (int k = -5; k < 6; k++)
				{
					if (!helper.isBlockReplaceable(i, j, k))
					{
						return false;
					}
				}
			}
		}
		if (isCastleInRangeOf(x, z, 100)) return false;
		positions.add(new Vector3F(x, y, z));
		for (int i = 0; i < 22; i++)
		{
			helper.enableRandomMetadata();
			helper.createLayer(getBuildingBlock(), 0, i, 0, 12, 12);
			helper.disableRandomMetadata();
			helper.setBlockToAir(6, i, 6);
			helper.setBlockToAir(5, i, 6);
			helper.setBlockToAir(6, i, 5);
			helper.setBlockToAir(4, i, 6);
			helper.setBlockToAir(6, i, 4);
			helper.setBlockToAir(-6, i, 6);
			helper.setBlockToAir(-5, i, 6);
			helper.setBlockToAir(-6, i, 5);
			helper.setBlockToAir(-4, i, 6);
			helper.setBlockToAir(-6, i, 4);
			helper.setBlockToAir(-5, i, 5);
			helper.setBlockToAir(5, i, 5);
			if (i == 6 || i == 12 || i == 18) helper.setEntitySpawnerAt(EntityCastleSkeleton.class, 0, 6, 1);
			if (i == 21) helper.setEntitySpawnerAt(EntityEvilIronGolem.class, 0, 6, 1);
			if (i != 2 && i != 5 && i != 8 && i != 11 && i != 14 && i != 17 && i != 20) helper.createLayer(Blocks.air, 0, i, 0, 10, 10);
			if (i == 1 || i == 4 || i == 7 || i == 10 || i == 13 || i == 16 || i == 19 || i == 22)
			{
				for (int l = -3; l < 3; l++)
				{
					helper.setBlock(Blocks.iron_bars, 3, i, -6);
					helper.setBlock(Blocks.iron_bars, 6, i, l);
					helper.setBlock(Blocks.iron_bars, -6, i, l);
					helper.setBlock(Blocks.iron_bars, l, i, -6);
				}
				if (i != 1)
				{
					helper.spawnEntityAt(getRangeSkeleton(world), -4.5D, i, 5.5D);
					helper.spawnEntityAt(getRangeSkeleton(world), 5.5D, i, 5.5D);
					helper.setBlock(Blocks.iron_bars, -2, i, 6);
					helper.setBlock(Blocks.iron_bars, -1, i, 6);
					helper.setBlock(Blocks.iron_bars, 1, i, 6);
					helper.setBlock(Blocks.iron_bars, 2, i, 6);
					helper.setBlock(getWallBlock(), -6, i, 6);
					helper.setBlock(getWallBlock(), -5, i, 6);
					helper.setBlock(getWallBlock(), -4, i, 6);
					helper.setBlock(getWallBlock(), -6, i, 5);
					helper.setBlock(getWallBlock(), -6, i, 4);
					helper.setBlock(getWallBlock(), 6, i, 6);
					helper.setBlock(getWallBlock(), 5, i, 6);
					helper.setBlock(getWallBlock(), 4, i, 6);
					helper.setBlock(getWallBlock(), 6, i, 5);
					helper.setBlock(getWallBlock(), 6, i, 4);
				}
			}
			if (i == 3 || i == 6 || i == 9 || i == 12 || i == 15 || i == 18 || i == 21)
			{
				if (i != 6 && i != 12 && i != 18 && i != 21)
				{
					helper.setEntitySpawnerAt(EntityCastleZombie.class, 3, i, 0);
					helper.setEntitySpawnerAt(EntityCastleZombie.class, -3, i, 0);
				}
				helper.setBlock(Blocks.iron_bars, -2, i, 6);
				helper.setBlock(Blocks.iron_bars, -1, i, 6);
				helper.setBlock(Blocks.iron_bars, 0, i, 6);
				helper.setBlock(Blocks.iron_bars, 1, i, 6);
				helper.setBlock(Blocks.iron_bars, 2, i, 6);
				helper.setBlock(getBuildingBlock(), 6, i, 6);
				helper.setBlock(getBuildingBlock(), 5, i, 6);
				helper.setBlock(getBuildingBlock(), 4, i, 6);
				helper.setBlock(getBuildingBlock(), 6, i, 5);
				helper.setBlock(getBuildingBlock(), 6, i, 4);
				helper.setBlock(getBuildingBlock(), 5, i, 5);
				helper.setBlock(getBuildingBlock(), -6, i, 6);
				helper.setBlock(getBuildingBlock(), -5, i, 6);
				helper.setBlock(getBuildingBlock(), -4, i, 6);
				helper.setBlock(getBuildingBlock(), -6, i, 5);
				helper.setBlock(getBuildingBlock(), -6, i, 4);
				helper.setBlock(getBuildingBlock(), -5, i, 5);
				helper.spawnEntityAt(getZombie(world), -3, i, 1);
				helper.spawnEntityAt(getZombie(world), -3, i, -1);
				helper.spawnEntityAt(getZombie(world), 3, i, 1);
				helper.spawnEntityAt(getZombie(world), 3, i, -1);
			}
			helper.setBlock(getBuildingBlock(), -4, i, 5);
			helper.setBlock(getBuildingBlock(), -5, i, 4);
			helper.setBlock(getBuildingBlock(), 4, i, 5);
			helper.setBlock(getBuildingBlock(), 5, i, 4);
		}
		helper.enableRandomMetadata();
		helper.createLayer(getBuildingBlock(), 0, 0, 0, 12, 12);
		helper.createLayer(getBuildingBlock(), 0, -1, 0, 12, 12);
		helper.createLayer(Blocks.air, 0, 0, 0, 10, 10);
		helper.setBlock(getBuildingBlock(), -4, 0, 5);
		helper.setBlock(getBuildingBlock(), -5, 0, 4);
		helper.setBlock(getBuildingBlock(), 4, 0, 5);
		helper.setBlock(getBuildingBlock(), 5, 0, 4);
		helper.setBlock(getBuildingBlock(), 0, 4, 6);
		helper.disableRandomMetadata();
		helper.enableRandomMetadata();
		helper.disableRandomMetadata();
		helper.setDoorAt(0, 0, 6, 0, true);
		helper.setBlock(Blocks.lava, -5, 0, 5);
		helper.setBlock(Blocks.lava, 5, 0, 5);
		helper.setEntitySpawnerAt((Rand.nextBoolean() ? EntitySpider.class : EntityPigZombie.class), 0, 0, 0);
		helper.setChestAt(5, 21, -5, getChest(world));
		helper.setChestAt(-5, 21, -5, getChest(world));
		helper.setChestAt(3, 21, -5, getChest(world));
		helper.setChestAt(-3, 21, -5, getChest(world));
		helper.setChestAt(1, 21, -5, getChest(world));
		helper.setChestAt(-1, 21, -5, getChest(world));
		return true;
	}

	public Block getBuildingBlock()
	{
		return netherish ? Blocks.nether_brick : Blocks.stonebrick;
	}

	public Block getWallBlock()
	{
		return netherish ? Blocks.nether_brick_fence : Blocks.cobblestone_wall;
	}

	public EntitySkeleton getRangeSkeleton(World world)
	{
		EntityCastleSkeleton skele = new EntityCastleSkeleton(world).setAtCastle();
		skele.setCurrentItemOrArmor(1, new ItemStack(EntityLiving.getArmorItemForSlot(4, 3)));
		skele.setCurrentItemOrArmor(2, new ItemStack(EntityLiving.getArmorItemForSlot(3, 3)));
		skele.setCurrentItemOrArmor(3, new ItemStack(EntityLiving.getArmorItemForSlot(2, 3)));
		skele.setCurrentItemOrArmor(4, new ItemStack(EntityLiving.getArmorItemForSlot(1, 3)));
		skele.onSpawnWithEgg(null);
		return skele;
	}

	public EntityCastleZombie getZombie(World world)
	{
		EntityCastleZombie zombie = new EntityCastleZombie(world);
		zombie.setAtCastle();
		zombie.onSpawnWithEgg(null);
		return zombie;
	}

	private ChestType getChest(World world)
	{
		if (world == null || world.difficultySetting == null) return ChestType.DUNGEON_CHEST;
		switch (world.difficultySetting)
		{
			case EASY:
			{
				return Rand.nextBoolean() ? ChestType.PYRAMID_DESERT_CHEST : ChestType.STRONGHOLD_CORRIDOR;
			}
			case NORMAL:
			{
				return Rand.nextBoolean() ? ChestType.STRONGHOLD_LIBRARY : ChestType.DUNGEON_CHEST;
			}
			case HARD:
			{
				return Rand.nextBoolean() ? ChestType.DUNGEON_CHEST : ChestType.MINESHAFT_CORRIDOR;
			}
			default:
			{
				return ChestType.BONUS_CHEST;
			}
		}
	}

	public static boolean isCastleInRangeOf(int x, int z, double distance)
	{
		return getClosestCastleTo(x, z, distance) != null;
	}

	public static Vector3F getClosestCastleTo(int x, int z, double distance)
	{
		final Vector3F at = new Vector3F((float) x, 0.0F, (float) z);
		Vector3F spot = null;
		for (final Vector3F pos : positions)
		{
			final Vector3F loc = pos.clone().setY(0.0F);
			if (loc.distance(at) < distance)
			{
				distance = loc.distance(at);
				spot = pos;
			}
		}
		return spot;
	}
}
