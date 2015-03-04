package main.com.hk.eb.util;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemDoor;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.ChestGenHooks;

public class Builder
{
	public final Random rand;
	public final World world;
	public final int baseX, baseY, baseZ;
	private Vector3I offset;

	public Builder(World world, int baseX, int baseY, int baseZ)
	{
		this.world = world;
		rand = world.rand;
		this.baseX = baseX;
		this.baseY = baseY;
		this.baseZ = baseZ;
		offset = new Vector3I(0, 0, 0);
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

	public void removeOffset()
	{
		offset.zero();
	}

	public void setOffset(Vector3I offset)
	{
		this.offset = offset == null ? new Vector3I() : offset.clone();
	}

	public boolean isAllAir(int x, int y, int z, int length, int width, int height)
	{
		return isAll(Blocks.air, x, y, z, length, width, height);
	}

	public boolean isAll(Block block, int x, int y, int z, int length, int width, int height)
	{
		int i, j, k;
		length /= 2;
		width /= 2;
		for (i = -length; i < length + 1; i++)
		{
			for (j = -width; j < width + 1; j++)
			{
				for (k = 0; k < height; k++)
				{
					if (world.getBlock(baseX + offset.x + x + i, baseY + offset.y + y + j, baseZ + offset.z + z + k) != block) return false;
				}
			}
		}
		return true;
	}

	public boolean isAirBlock(int x, int y, int z)
	{
		return world.isAirBlock(baseX + offset.x + x, baseY + offset.y + y, baseZ + offset.z + z);
	}

	public void setBlockToAir(int x, int y, int z)
	{
		if (MPUtil.isClientSide()) return;
		world.setBlockToAir(baseX + offset.x + x, baseY + offset.y + y, baseZ + offset.z + z);
	}

	public void setBlocksToAir(int x, int y, int z, int length, int width, int height)
	{
		if (MPUtil.isClientSide()) return;
		int i, j, k;
		length /= 2;
		width /= 2;
		for (i = -length; i < length + 1; i++)
		{
			for (k = 0; k < height; k++)
			{
				for (j = -width; j < width + 1; j++)
				{
					world.setBlockToAir(baseX + offset.x + x + i, baseY + offset.y + y + k, baseZ + offset.z + z + j);
				}
			}
		}
	}

	public void makeColum(Block block, int x, int y, int z, int height)
	{
		WorldUtil.makeColum(world, baseX + offset.x + x, baseY + offset.y + y, baseZ + offset.z + z, block, height);
	}

	public void makeColum(Block block, int x, int y, int z, int height, int meta)
	{
		WorldUtil.makeColumn(world, baseX + offset.x + x, baseY + offset.y + y, baseZ + offset.z + z, block, height, meta);
	}

	public void fillWithBlocks(Block block, int x, int y, int z, int radius, int meta)
	{
		WorldUtil.fillWithBlocks(world, baseX + offset.x + x, baseY + offset.y + y, baseZ + offset.z + z, block, radius, meta);
	}

	public void fillWithBlocks(Block block, int x, int y, int z, int radius)
	{
		WorldUtil.fillWithBlocks(world, baseX + offset.x + x, baseY + offset.y + y, baseZ + offset.z + z, block, radius);
	}

	public void createLayer(Block block, int x, int y, int z, int length, int width)
	{
		WorldUtil.createLayer(world, baseX + offset.x + x, baseY + offset.y + y, baseZ + offset.z + z, block, length, width);
	}

	public void createLayer(Block block, int x, int y, int z, int length, int width, int meta)
	{
		WorldUtil.createLayer(world, baseX + offset.x + x, baseY + offset.y + y, baseZ + offset.z + z, block, length, width, meta);
	}

	public void createBox(Block block, int x, int y, int z, int length, int width, int height)
	{
		WorldUtil.createBox(world, baseX + offset.x + x, baseY + offset.y + y, baseZ + offset.z + z, block, length, width, height);
	}

	public void createBox(Block block, int x, int y, int z, int length, int width, int height, int meta)
	{
		WorldUtil.createBox(world, baseX + offset.x + x, baseY + offset.y + y, baseZ + offset.z + z, block, length, width, height, meta);
	}

	public void setBlock(Block block, int x, int y, int z)
	{
		WorldUtil.setBlock(world, baseX + offset.x + x, baseY + offset.y + y, baseZ + offset.z + z, block);
	}

	public void setBlock(Block block, int x, int y, int z, int meta)
	{
		WorldUtil.setBlock(world, baseX + offset.x + x, baseY + offset.y + y, baseZ + offset.z + z, block, meta);
	}

	public void setDoorAt(int x, int y, int z, int meta, boolean isWood)
	{
		ItemDoor.placeDoorBlock(world, baseX + offset.x + x, baseY + offset.y + y, baseZ + offset.z + z, meta, isWood ? Blocks.wooden_door : Blocks.iron_door);
	}

	public void spawnEntityAt(Entity entity, double x, double y, double z)
	{
		if (MPUtil.isClientSide()) return;
		entity.setPosition(baseX + offset.x + x, baseY + offset.y + y, baseZ + offset.z + z);
		world.spawnEntityInWorld(entity);
	}

	public void setEntitySpawnerAt(Class<? extends Entity> clazz, int x, int y, int z)
	{
		if (MPUtil.isClientSide() || !EntityList.classToStringMapping.containsKey(clazz)) return;
		world.setBlock(baseX + offset.x + x, baseY + offset.y + y, baseZ + offset.z + z, Blocks.mob_spawner);
		TileEntityMobSpawner te = (TileEntityMobSpawner) Blocks.mob_spawner.createTileEntity(world, 0);
		world.setTileEntity(baseX + offset.x + x, baseY + offset.y + y, baseZ + offset.z + z, te);
		te.func_145881_a().setEntityName((String) EntityList.classToStringMapping.get(clazz));
	}

	public void setChestAt(int x, int y, int z, ChestType chestType)
	{
		setChestAt(x, y, z, 0, chestType);
	}

	public void setChestAt(int x, int y, int z, int meta, ChestType chestType)
	{
		fillWithRandomItems(Blocks.chest, x, y, z, meta, chestType);
	}

	public void fillWithRandomItems(Block block, int x, int y, int z, ChestType chestType)
	{
		fillWithRandomItems(block, x, y, z, 0, chestType);
	}

	public void fillWithRandomItems(Block block, int x, int y, int z, int meta, ChestType chestType)
	{
		if (MPUtil.isClientSide()) return;
		if (world.setBlock(baseX + offset.x + x, baseY + offset.y + y, baseZ + offset.z + z, block, meta, 2))
		{
			WeightedRandomChestContent.generateChestContents(world.rand, ChestGenHooks.getItems(chestType.getType(), world.rand), (IInventory) world.getTileEntity(baseX + offset.x + x, baseY + offset.y + y, baseZ + offset.z + z), ChestGenHooks.getCount(chestType.getType(), world.rand));
		}
	}

	public Block getBlockAt(int x, int y, int z)
	{
		return world.getBlock(baseX + offset.x + x, baseY + offset.y + y, baseZ + offset.z + z);
	}

	public BiomeGenBase getBiomeAtSpot()
	{
		return getBiomeAt(0, 0);
	}

	public BiomeGenBase getBiomeAt(int x, int z)
	{
		return world.getBiomeGenForCoords(baseX + offset.x + x, baseZ + offset.z + z);
	}

	public boolean isBlockReplaceable(int x, int y, int z)
	{
		return !getBlockAt(x, y, z).getMaterial().isLiquid() && getBlockAt(x, y, z).isReplaceable(world, baseX + offset.x + x, baseY + offset.y + y, baseZ + offset.z + z);
	}

	@Override
	public String toString()
	{
		return "Base: " + new Vector3I(baseX + offset.x, baseY + offset.y, baseZ + offset.z).toString();
	}

	public enum ChestType
	{
		MINESHAFT_CORRIDOR("mineshaftCorridor"),
		PYRAMID_DESERT_CHEST("pyramidDesertyChest"),
		PYRAMID_JUNGLE_CHEST("pyramidJungleChest"),
		PYRAMID_JUNGLE_DISPENSER("pyramidJungleDispenser"),
		STRONGHOLD_CORRIDOR("strongholdCorridor"),
		STRONGHOLD_LIBRARY("strongholdLibrary"),
		STRONGHOLD_CROSSING("strongholdCrossing"),
		VILLAGE_BLACKSMITH("villageBlacksmith"),
		BONUS_CHEST("bonusChest"),
		DUNGEON_CHEST("dungeonChest");
		private final String type;

		private ChestType(String type)
		{
			this.type = type;
		}

		public static ChestType rand(Random random)
		{
			return values()[random.nextInt(values().length)];
		}

		public String getType()
		{
			return type;
		}
	}
}
