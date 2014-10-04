package main.com.hk.eb.util;

import main.extremeblocks.Vars;
import main.extremeblocks.entities.mobs.EntityCastleSkeleton;
import main.extremeblocks.entities.mobs.EntityCastleZombie;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemDoor;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.ChestGenHooks;

public class Builder
{
	private final World world;
	private final int baseX, baseY, baseZ;

	public Builder(World world, int baseX, int baseY, int baseZ)
	{
		this.world = world;
		this.baseX = baseX;
		this.baseY = baseY;
		this.baseZ = baseZ;
	}

	public void enableCanStay()
	{
		WorldUtil.enableCanStay();
	}

	public void disableCanStay()
	{
		WorldUtil.disableCanStay();
	}

	public void enableIsAir()
	{
		WorldUtil.enableIsAir();
	}

	public void disableIsAir()
	{
		WorldUtil.disableIsAir();
	}

	public void enableRandomMetadata()
	{
		WorldUtil.enableRandomMetadata();
	}

	public void disableRandomMetadata()
	{
		WorldUtil.disableRandomMetadata();
	}

	public boolean isAllAir(int x, int y, int z, int length, int width, int height)
	{
		if (MathHelp.isOdd(length))
		{
			length++;
		}
		if (MathHelp.isOdd(width))
		{
			width++;
		}
		length /= 2;
		width /= 2;
		height /= 2;
		int i, j, k;
		for (i = -length; i < length + 1; i++)
		{
			for (j = -width; j < width + 1; j++)
			{
				for (k = -height; k < height + 1; k++)
				{
					if (!world.isAirBlock(baseX + x + i, baseY + y + j, baseZ + z + k)) return false;
				}
			}
		}
		return true;
	}

	public boolean isAirBlock(int x, int y, int z)
	{
		return world.isAirBlock(baseX + x, baseY + y, baseZ + z);
	}

	public void setBlockToAir(int x, int y, int z)
	{
		if (MPUtil.isClientSide()) return;
		world.setBlockToAir(baseX + x, baseY + y, baseZ + z);
	}

	public void setBlocksToAir(int x, int y, int z, int length, int width, int height)
	{
		if (MPUtil.isClientSide()) return;
		if (MathHelp.isOdd(length))
		{
			length++;
		}
		if (MathHelp.isOdd(width))
		{
			width++;
		}
		if (MathHelp.isOdd(height))
		{
			height++;
		}
		length /= 2;
		width /= 2;
		height /= 2;
		int i, j, k;
		for (i = -length; i < length + 1; i++)
		{
			for (k = -height; k < height + 1; k++)
			{
				for (j = -width; j < width + 1; j++)
				{
					world.setBlockToAir(baseX + x + i, baseY + y + k, baseZ + z + j);
				}
			}
		}
	}

	public void makeColum(Block block, int x, int y, int z, int height)
	{
		WorldUtil.makeColum(world, baseX + x, baseY + y, baseZ + z, block, height);
	}

	public void makeColum(Block block, int x, int y, int z, int height, int meta)
	{
		WorldUtil.makeColum(world, baseX + x, baseY + y, baseZ + z, block, height, meta);
	}

	public void fillWithBlocks(Block block, int x, int y, int z, int radius, int meta)
	{
		WorldUtil.fillWithBlocks(world, baseX + x, baseY + y, baseZ + z, block, radius, meta);
	}

	public void fillWithBlocks(Block block, int x, int y, int z, int radius)
	{
		WorldUtil.fillWithBlocks(world, baseX + x, baseY + y, baseZ + z, block, radius);
	}

	public void createLayer(Block block, int x, int y, int z, int length, int width)
	{
		WorldUtil.createLayer(world, baseX + x, baseY + y, baseZ + z, block, length, width);
	}

	public void createLayer(Block block, int x, int y, int z, int length, int width, int meta)
	{
		WorldUtil.createLayer(world, baseX + x, baseY + y, baseZ + z, block, length, width, meta);
	}

	public void createBox(Block block, int x, int y, int z, int length, int width, int height)
	{
		WorldUtil.createBox(world, baseX + x, baseY + y, baseZ + z, block, length, width, height);
	}

	public void createBox(Block block, int x, int y, int z, int length, int width, int height, int meta)
	{
		WorldUtil.createBox(world, baseX + x, baseY + y, baseZ + z, block, length, width, height, meta);
	}

	public void setBlock(Block block, int x, int y, int z)
	{
		WorldUtil.setBlock(world, baseX + x, baseY + y, baseZ + z, block);
	}

	public void setBlock(Block block, int x, int y, int z, int meta)
	{
		WorldUtil.setBlock(world, baseX + x, baseY + y, baseZ + z, block, meta);
	}

	public void setDoorAt(int x, int y, int z, int meta, boolean isWood)
	{
		ItemDoor.placeDoorBlock(world, baseX + x, baseY + y, baseZ + z, meta, isWood ? Blocks.wooden_door : Blocks.iron_door);
	}

	public void spawnEntityAt(Entity entity, double x, double y, double z)
	{
		if (MPUtil.isClientSide()) return;
		if (entity instanceof EntityCastleSkeleton && !Vars.addCastleSkeleton)
		{
			entity = ((EntityCastleSkeleton) entity).newVanillaClone();
		}
		if (entity instanceof EntityCastleZombie && !Vars.addCastleZombie)
		{
			entity = ((EntityCastleZombie) entity).newVanillaClone();
		}
		entity.setPosition(baseX + x, baseY + y, baseZ + z);
		world.spawnEntityInWorld(entity);
	}

	public void setEntitySpawnerAt(Class<? extends Entity> clazz, int x, int y, int z)
	{
		if (MPUtil.isClientSide() || !EntityList.classToStringMapping.containsKey(clazz)) return;
		if (clazz.equals(EntityCastleSkeleton.class) && !Vars.addCastleSkeleton)
		{
			clazz = EntitySkeleton.class;
		}
		if (clazz.equals(EntityCastleZombie.class) && !Vars.addCastleZombie)
		{
			clazz = EntityZombie.class;
		}
		world.setBlock(baseX + x, baseY + y, baseZ + z, Blocks.mob_spawner);
		((TileEntityMobSpawner) world.getTileEntity(baseX + x, baseY + y, baseZ + z)).func_145881_a().setEntityName((String) EntityList.classToStringMapping.get(clazz));
	}

	public void setChestAt(int x, int y, int z, ChestType chestType)
	{
		if (MPUtil.isClientSide()) return;
		if (world.setBlock(baseX + x, baseY + y, baseZ + z, Blocks.chest, 0, 2))
		{
			WeightedRandomChestContent.generateChestContents(world.rand, ChestGenHooks.getItems(chestType.getType(), world.rand), (TileEntityChest) world.getTileEntity(baseX + x, baseY + y, baseZ + z), ChestGenHooks.getCount(chestType.getType(), world.rand));
		}
	}

	public Block getBlockAt(int x, int y, int z)
	{
		return world.getBlock(baseX + x, baseY + y, baseZ + z);
	}

	public BiomeGenBase getBiomeAtSpot()
	{
		return getBiomeAt(0, 0);
	}

	public BiomeGenBase getBiomeAt(int x, int z)
	{
		return world.getBiomeGenForCoords(baseX + x, baseZ + z);
	}

	public boolean isBlockReplaceable(int x, int y, int z)
	{
		return !getBlockAt(x, y, z).getMaterial().isLiquid() && getBlockAt(x, y, z).isReplaceable(world, baseX + x, baseY + y, baseZ + z);
	}

	public enum ChestType
	{
		MINESHAFT_CORRIDOR("mineshaftCorridor"), PYRAMID_DESERT_CHEST("pyramidDesertyChest"), PYRAMID_JUNGLE_CHEST("pyramidJungleChest"), PYRAMID_JUNGLE_DISPENSER("pyramidJungleDispenser"), STRONGHOLD_CORRIDOR("strongholdCorridor"), STRONGHOLD_LIBRARY("strongholdLibrary"), STRONGHOLD_CROSSING("strongholdCrossing"), VILLAGE_BLACKSMITH("villageBlacksmith"), BONUS_CHEST("bonusChest"), DUNGEON_CHEST("dungeonChest");
		private final String type;

		private ChestType(String type)
		{
			this.type = type;
		}

		public String getType()
		{
			return type;
		}
	}
}
