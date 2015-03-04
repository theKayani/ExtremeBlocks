package main.extremeblocks.worldgen.catacombs;

import java.util.Collections;
import java.util.List;
import main.com.hk.eb.util.Builder;
import main.com.hk.eb.util.Builder.ChestType;
import main.com.hk.eb.util.JavaHelp;
import main.com.hk.eb.util.Rand;
import main.com.hk.eb.util.Vector3I;
import main.extremeblocks.entities.mobs.EntityCastleSkeleton;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.util.ForgeDirection;

public class CatacombRoom
{
	private Builder helper;

	public CatacombRoom(Builder helper)
	{
		this.helper = helper;
	}

	public void create(Rooms room, int x, int z, ForgeDirection... sides)
	{
		helper.setOffset(new Vector3I(x * 9, 0, z * 9));
		CatacombPiece.clearArea(helper);
		createRoom(sides);
		switch (room)
		{
			case REWARD:
				createRewardRoom();
				break;
			case SPAWNER:
				helper.setEntitySpawnerAt(EntityCastleSkeleton.class, 4, 1, 4);
				break;
			case REWARD_TRAP:
				createRewardTrapRoom();
				break;
			case TRAP:
				createTrapRoom();
				break;
		}
		helper.removeOffset();
	}

	private void createRewardTrapRoom()
	{
		helper.setBlock(Blocks.gold_block, 4, 3, 4);
		helper.setBlock(getWall(), 2, 1, 2);
		helper.setBlock(getWall(), 2, 1, 3);
		helper.setBlock(getWall(), 2, 1, 4);
		helper.setBlock(getWall(), 2, 1, 5);
		helper.setBlock(getWall(), 2, 1, 6);
		helper.setBlock(getWall(), 3, 1, 2);
		helper.setBlock(getWall(), 3, 1, 6);
		helper.setBlock(getWall(), 3, 2, 3);
		helper.setBlock(getWall(), 3, 2, 4);
		helper.setBlock(getWall(), 3, 2, 5);
		helper.setBlock(getWall(), 4, 1, 2);
		helper.setBlock(getWall(), 4, 1, 6);
		helper.setBlock(getWall(), 4, 2, 3);
		helper.setBlock(getWall(), 4, 2, 4);
		helper.setBlock(getWall(), 4, 2, 5);
		helper.setBlock(getWall(), 5, 1, 2);
		helper.setBlock(getWall(), 5, 1, 6);
		helper.setBlock(getWall(), 5, 2, 3);
		helper.setBlock(getWall(), 5, 2, 4);
		helper.setBlock(getWall(), 5, 2, 5);
		helper.setBlock(getWall(), 6, 1, 2);
		helper.setBlock(getWall(), 6, 1, 3);
		helper.setBlock(getWall(), 6, 1, 4);
		helper.setBlock(getWall(), 6, 1, 5);
		helper.setBlock(getWall(), 6, 1, 6);
		helper.setBlock(Blocks.stone_pressure_plate, 2, 2, 2);
		helper.setBlock(Blocks.stone_pressure_plate, 2, 2, 3);
		helper.setBlock(Blocks.stone_pressure_plate, 2, 2, 4);
		helper.setBlock(Blocks.stone_pressure_plate, 2, 2, 5);
		helper.setBlock(Blocks.stone_pressure_plate, 2, 2, 6);
		helper.setBlock(Blocks.stone_pressure_plate, 3, 2, 2);
		helper.setBlock(Blocks.stone_pressure_plate, 3, 2, 6);
		helper.setBlock(Blocks.stone_pressure_plate, 4, 2, 2);
		helper.setBlock(Blocks.stone_pressure_plate, 4, 2, 6);
		helper.setBlock(Blocks.stone_pressure_plate, 5, 2, 2);
		helper.setBlock(Blocks.stone_pressure_plate, 5, 2, 6);
		helper.setBlock(Blocks.stone_pressure_plate, 6, 2, 2);
		helper.setBlock(Blocks.stone_pressure_plate, 6, 2, 3);
		helper.setBlock(Blocks.stone_pressure_plate, 6, 2, 4);
		helper.setBlock(Blocks.stone_pressure_plate, 6, 2, 5);
		helper.setBlock(Blocks.stone_pressure_plate, 6, 2, 6);
		helper.setBlock(Blocks.tnt, 2, 0, 2);
		helper.setBlock(Blocks.tnt, 2, 0, 3);
		helper.setBlock(Blocks.tnt, 2, 0, 4);
		helper.setBlock(Blocks.tnt, 2, 0, 5);
		helper.setBlock(Blocks.tnt, 3, 0, 2);
		helper.setBlock(Blocks.tnt, 3, 0, 3);
		helper.setBlock(Blocks.tnt, 3, 0, 4);
		helper.setBlock(Blocks.tnt, 3, 0, 5);
		helper.setBlock(Blocks.tnt, 3, 1, 3);
		helper.setBlock(Blocks.tnt, 3, 1, 4);
		helper.setBlock(Blocks.tnt, 3, 1, 5);
		helper.setBlock(Blocks.tnt, 4, 0, 2);
		helper.setBlock(Blocks.tnt, 4, 0, 3);
		helper.setBlock(Blocks.tnt, 4, 0, 4);
		helper.setBlock(Blocks.tnt, 4, 0, 5);
		helper.setBlock(Blocks.tnt, 4, 1, 3);
		helper.setBlock(Blocks.tnt, 4, 1, 4);
		helper.setBlock(Blocks.tnt, 4, 1, 5);
		helper.setBlock(Blocks.tnt, 5, 0, 2);
		helper.setBlock(Blocks.tnt, 5, 0, 3);
		helper.setBlock(Blocks.tnt, 5, 0, 4);
		helper.setBlock(Blocks.tnt, 5, 0, 5);
		helper.setBlock(Blocks.tnt, 5, 1, 3);
		helper.setBlock(Blocks.tnt, 5, 1, 4);
		helper.setBlock(Blocks.tnt, 5, 1, 5);
		helper.fillWithRandomItems(Blocks.trapped_chest, 3, 3, 4, 4, ChestType.rand(helper.rand));
		helper.fillWithRandomItems(Blocks.trapped_chest, 4, 3, 3, 2, ChestType.rand(helper.rand));
		helper.fillWithRandomItems(Blocks.trapped_chest, 4, 3, 5, 3, ChestType.rand(helper.rand));
		helper.fillWithRandomItems(Blocks.trapped_chest, 5, 3, 4, 5, ChestType.rand(helper.rand));
		helper.fillWithRandomItems(Blocks.chest, 3, 3, 3, 2, ChestType.rand(helper.rand));
		helper.fillWithRandomItems(Blocks.chest, 3, 3, 5, 3, ChestType.rand(helper.rand));
		helper.fillWithRandomItems(Blocks.chest, 5, 3, 3, 2, ChestType.rand(helper.rand));
		helper.fillWithRandomItems(Blocks.chest, 5, 3, 5, 3, ChestType.rand(helper.rand));
	}

	private void createTrapRoom()
	{
		helper.setBlock(Blocks.chest, 1, 1, 2, 5);
		helper.setBlock(Blocks.chest, 1, 1, 4, 5);
		helper.setBlock(Blocks.chest, 1, 1, 6, 5);
		helper.setBlock(Blocks.chest, 2, 1, 1, 3);
		helper.setBlock(Blocks.chest, 2, 1, 7, 2);
		helper.setBlock(Blocks.chest, 4, 1, 1, 3);
		helper.setBlock(Blocks.chest, 4, 1, 7, 2);
		helper.setBlock(Blocks.chest, 6, 1, 1, 3);
		helper.setBlock(Blocks.chest, 6, 1, 7, 2);
		helper.setBlock(Blocks.chest, 7, 1, 3, 4);
		helper.setBlock(Blocks.chest, 7, 1, 5, 4);
		helper.setBlock(Blocks.tnt, 1, 0, 3);
		helper.setBlock(Blocks.tnt, 1, 0, 5);
		helper.setBlock(Blocks.tnt, 3, 0, 1);
		helper.setBlock(Blocks.tnt, 3, 0, 7);
		helper.setBlock(Blocks.tnt, 5, 0, 1);
		helper.setBlock(Blocks.tnt, 5, 0, 7);
		helper.setBlock(Blocks.tnt, 7, 0, 2);
		helper.setBlock(Blocks.tnt, 7, 0, 4);
		helper.setBlock(Blocks.tnt, 7, 0, 6);
		helper.setBlock(Blocks.trapped_chest, 1, 1, 3, 5);
		helper.setBlock(Blocks.trapped_chest, 1, 1, 5, 5);
		helper.setBlock(Blocks.trapped_chest, 3, 1, 1, 3);
		helper.setBlock(Blocks.trapped_chest, 3, 1, 7, 2);
		helper.setBlock(Blocks.trapped_chest, 5, 1, 1, 3);
		helper.setBlock(Blocks.trapped_chest, 5, 1, 7, 2);
		helper.setBlock(Blocks.trapped_chest, 7, 1, 2, 4);
		helper.setBlock(Blocks.trapped_chest, 7, 1, 4, 4);
		helper.setBlock(Blocks.trapped_chest, 7, 1, 6, 4);
	}

	private void createRewardRoom()
	{
		helper.fillWithRandomItems(Blocks.trapped_chest, 1, 1, 3, 5, ChestType.rand(helper.rand));
		helper.fillWithRandomItems(Blocks.trapped_chest, 1, 1, 5, 5, ChestType.rand(helper.rand));
		helper.fillWithRandomItems(Blocks.trapped_chest, 3, 1, 1, 3, ChestType.rand(helper.rand));
		helper.fillWithRandomItems(Blocks.trapped_chest, 3, 1, 7, 2, ChestType.rand(helper.rand));
		helper.fillWithRandomItems(Blocks.trapped_chest, 5, 1, 1, 3, ChestType.rand(helper.rand));
		helper.fillWithRandomItems(Blocks.trapped_chest, 5, 1, 7, 2, ChestType.rand(helper.rand));
		helper.fillWithRandomItems(Blocks.trapped_chest, 7, 1, 2, 4, ChestType.rand(helper.rand));
		helper.fillWithRandomItems(Blocks.trapped_chest, 7, 1, 4, 4, ChestType.rand(helper.rand));
		helper.fillWithRandomItems(Blocks.trapped_chest, 7, 1, 6, 4, ChestType.rand(helper.rand));
		helper.fillWithRandomItems(Blocks.chest, 1, 1, 2, 5, ChestType.rand(helper.rand));
		helper.fillWithRandomItems(Blocks.chest, 1, 1, 4, 5, ChestType.rand(helper.rand));
		helper.fillWithRandomItems(Blocks.chest, 1, 1, 6, 5, ChestType.rand(helper.rand));
		helper.fillWithRandomItems(Blocks.chest, 2, 1, 1, 3, ChestType.rand(helper.rand));
		helper.fillWithRandomItems(Blocks.chest, 2, 1, 7, 2, ChestType.rand(helper.rand));
		helper.fillWithRandomItems(Blocks.chest, 4, 1, 1, 3, ChestType.rand(helper.rand));
		helper.fillWithRandomItems(Blocks.chest, 4, 1, 7, 2, ChestType.rand(helper.rand));
		helper.fillWithRandomItems(Blocks.chest, 6, 1, 1, 3, ChestType.rand(helper.rand));
		helper.fillWithRandomItems(Blocks.chest, 6, 1, 7, 2, ChestType.rand(helper.rand));
		helper.fillWithRandomItems(Blocks.chest, 7, 1, 3, 4, ChestType.rand(helper.rand));
		helper.fillWithRandomItems(Blocks.chest, 7, 1, 5, 4, ChestType.rand(helper.rand));
	}

	private void createRoom(ForgeDirection... sides)
	{
		helper.setBlock(getWall(), 0, 0, 0);
		helper.setBlock(getWall(), 0, 0, 1);
		helper.setBlock(getWall(), 0, 0, 2);
		helper.setBlock(getWall(), 0, 0, 3);
		helper.setBlock(getWall(), 0, 0, 4);
		helper.setBlock(getWall(), 0, 0, 5);
		helper.setBlock(getWall(), 0, 0, 6);
		helper.setBlock(getWall(), 0, 0, 7);
		helper.setBlock(getWall(), 0, 0, 8);
		helper.setBlock(getWall(), 0, 1, 0);
		helper.setBlock(getWall(), 0, 1, 1);
		helper.setBlock(getWall(), 0, 1, 2);
		helper.setBlock(getWall(), 0, 1, 3);
		helper.setBlock(getWall(), 0, 1, 4);
		helper.setBlock(getWall(), 0, 1, 5);
		helper.setBlock(getWall(), 0, 1, 6);
		helper.setBlock(getWall(), 0, 1, 7);
		helper.setBlock(getWall(), 0, 1, 8);
		helper.setBlock(getWall(), 0, 2, 0);
		helper.setBlock(getWall(), 0, 2, 1);
		helper.setBlock(getWall(), 0, 2, 2);
		helper.setBlock(getWall(), 0, 2, 3);
		helper.setBlock(getWall(), 0, 2, 4);
		helper.setBlock(getWall(), 0, 2, 5);
		helper.setBlock(getWall(), 0, 2, 6);
		helper.setBlock(getWall(), 0, 2, 7);
		helper.setBlock(getWall(), 0, 2, 8);
		helper.setBlock(getWall(), 0, 3, 0);
		helper.setBlock(getWall(), 0, 3, 1);
		helper.setBlock(getWall(), 0, 3, 2);
		helper.setBlock(getWall(), 0, 3, 3);
		helper.setBlock(getWall(), 0, 3, 4);
		helper.setBlock(getWall(), 0, 3, 5);
		helper.setBlock(getWall(), 0, 3, 6);
		helper.setBlock(getWall(), 0, 3, 7);
		helper.setBlock(getWall(), 0, 3, 8);
		helper.setBlock(getWall(), 0, 4, 0);
		helper.setBlock(getWall(), 0, 4, 1);
		helper.setBlock(getWall(), 0, 4, 2);
		helper.setBlock(getWall(), 0, 4, 3);
		helper.setBlock(getWall(), 0, 4, 4);
		helper.setBlock(getWall(), 0, 4, 5);
		helper.setBlock(getWall(), 0, 4, 6);
		helper.setBlock(getWall(), 0, 4, 7);
		helper.setBlock(getWall(), 0, 4, 8);
		helper.setBlock(getWall(), 0, 5, 0);
		helper.setBlock(getWall(), 0, 5, 1);
		helper.setBlock(getWall(), 0, 5, 2);
		helper.setBlock(getWall(), 0, 5, 3);
		helper.setBlock(getWall(), 0, 5, 4);
		helper.setBlock(getWall(), 0, 5, 5);
		helper.setBlock(getWall(), 0, 5, 6);
		helper.setBlock(getWall(), 0, 5, 7);
		helper.setBlock(getWall(), 0, 5, 8);
		helper.setBlock(getWall(), 1, 0, 0);
		helper.setBlock(getWall(), 1, 0, 1);
		helper.setBlock(getWall(), 1, 0, 2);
		helper.setBlock(getWall(), 1, 0, 3);
		helper.setBlock(getWall(), 1, 0, 4);
		helper.setBlock(getWall(), 1, 0, 5);
		helper.setBlock(getWall(), 1, 0, 6);
		helper.setBlock(getWall(), 1, 0, 7);
		helper.setBlock(getWall(), 1, 0, 8);
		helper.setBlock(getWall(), 1, 1, 0);
		helper.setBlock(getWall(), 1, 1, 8);
		helper.setBlock(getWall(), 1, 2, 0);
		helper.setBlock(getWall(), 1, 2, 8);
		helper.setBlock(getWall(), 1, 3, 0);
		helper.setBlock(getWall(), 1, 3, 8);
		helper.setBlock(getWall(), 1, 4, 0);
		helper.setBlock(getWall(), 1, 4, 8);
		helper.setBlock(getWall(), 1, 5, 0);
		helper.setBlock(getWall(), 1, 5, 1);
		helper.setBlock(getWall(), 1, 5, 2);
		helper.setBlock(getWall(), 1, 5, 3);
		helper.setBlock(getWall(), 1, 5, 4);
		helper.setBlock(getWall(), 1, 5, 5);
		helper.setBlock(getWall(), 1, 5, 6);
		helper.setBlock(getWall(), 1, 5, 7);
		helper.setBlock(getWall(), 1, 5, 8);
		helper.setBlock(getWall(), 2, 0, 0);
		helper.setBlock(getWall(), 2, 0, 1);
		helper.setBlock(getWall(), 2, 0, 2);
		helper.setBlock(getWall(), 2, 0, 3);
		helper.setBlock(getWall(), 2, 0, 4);
		helper.setBlock(getWall(), 2, 0, 5);
		helper.setBlock(getWall(), 2, 0, 6);
		helper.setBlock(getWall(), 2, 0, 7);
		helper.setBlock(getWall(), 2, 0, 8);
		helper.setBlock(getWall(), 2, 1, 0);
		helper.setBlock(getWall(), 2, 1, 8);
		helper.setBlock(getWall(), 2, 2, 0);
		helper.setBlock(getWall(), 2, 2, 8);
		helper.setBlock(getWall(), 2, 3, 0);
		helper.setBlock(getWall(), 2, 3, 8);
		helper.setBlock(getWall(), 2, 4, 0);
		helper.setBlock(getWall(), 2, 4, 8);
		helper.setBlock(getWall(), 2, 5, 0);
		helper.setBlock(getWall(), 2, 5, 1);
		helper.setBlock(getWall(), 2, 5, 2);
		helper.setBlock(getWall(), 2, 5, 3);
		helper.setBlock(getWall(), 2, 5, 4);
		helper.setBlock(getWall(), 2, 5, 5);
		helper.setBlock(getWall(), 2, 5, 6);
		helper.setBlock(getWall(), 2, 5, 7);
		helper.setBlock(getWall(), 2, 5, 8);
		helper.setBlock(getWall(), 3, 0, 0);
		helper.setBlock(getWall(), 3, 0, 1);
		helper.setBlock(getWall(), 3, 0, 2);
		helper.setBlock(getWall(), 3, 0, 3);
		helper.setBlock(getWall(), 3, 0, 4);
		helper.setBlock(getWall(), 3, 0, 5);
		helper.setBlock(getWall(), 3, 0, 6);
		helper.setBlock(getWall(), 3, 0, 7);
		helper.setBlock(getWall(), 3, 0, 8);
		helper.setBlock(getWall(), 3, 1, 0);
		helper.setBlock(getWall(), 3, 1, 8);
		helper.setBlock(getWall(), 3, 2, 0);
		helper.setBlock(getWall(), 3, 2, 8);
		helper.setBlock(getWall(), 3, 3, 0);
		helper.setBlock(getWall(), 3, 3, 8);
		helper.setBlock(getWall(), 3, 4, 0);
		helper.setBlock(getWall(), 3, 4, 8);
		helper.setBlock(getWall(), 3, 5, 0);
		helper.setBlock(getWall(), 3, 5, 1);
		helper.setBlock(getWall(), 3, 5, 2);
		helper.setBlock(getWall(), 3, 5, 3);
		helper.setBlock(getWall(), 3, 5, 4);
		helper.setBlock(getWall(), 3, 5, 5);
		helper.setBlock(getWall(), 3, 5, 6);
		helper.setBlock(getWall(), 3, 5, 7);
		helper.setBlock(getWall(), 3, 5, 8);
		helper.setBlock(getWall(), 4, 0, 0);
		helper.setBlock(getWall(), 4, 0, 1);
		helper.setBlock(getWall(), 4, 0, 2);
		helper.setBlock(getWall(), 4, 0, 3);
		helper.setBlock(getWall(), 4, 0, 4);
		helper.setBlock(getWall(), 4, 0, 5);
		helper.setBlock(getWall(), 4, 0, 6);
		helper.setBlock(getWall(), 4, 0, 7);
		helper.setBlock(getWall(), 4, 0, 8);
		helper.setBlock(getWall(), 4, 1, 0);
		helper.setBlock(getWall(), 4, 1, 8);
		helper.setBlock(getWall(), 4, 2, 0);
		helper.setBlock(getWall(), 4, 2, 8);
		helper.setBlock(getWall(), 4, 3, 0);
		helper.setBlock(getWall(), 4, 3, 8);
		helper.setBlock(getWall(), 4, 4, 0);
		helper.setBlock(getWall(), 4, 4, 8);
		helper.setBlock(getWall(), 4, 5, 0);
		helper.setBlock(getWall(), 4, 5, 1);
		helper.setBlock(getWall(), 4, 5, 2);
		helper.setBlock(getWall(), 4, 5, 3);
		helper.setBlock(getWall(), 4, 5, 4);
		helper.setBlock(getWall(), 4, 5, 5);
		helper.setBlock(getWall(), 4, 5, 6);
		helper.setBlock(getWall(), 4, 5, 7);
		helper.setBlock(getWall(), 4, 5, 8);
		helper.setBlock(getWall(), 5, 0, 0);
		helper.setBlock(getWall(), 5, 0, 1);
		helper.setBlock(getWall(), 5, 0, 2);
		helper.setBlock(getWall(), 5, 0, 3);
		helper.setBlock(getWall(), 5, 0, 4);
		helper.setBlock(getWall(), 5, 0, 5);
		helper.setBlock(getWall(), 5, 0, 6);
		helper.setBlock(getWall(), 5, 0, 7);
		helper.setBlock(getWall(), 5, 0, 8);
		helper.setBlock(getWall(), 5, 1, 0);
		helper.setBlock(getWall(), 5, 1, 8);
		helper.setBlock(getWall(), 5, 2, 0);
		helper.setBlock(getWall(), 5, 2, 8);
		helper.setBlock(getWall(), 5, 3, 0);
		helper.setBlock(getWall(), 5, 3, 8);
		helper.setBlock(getWall(), 5, 4, 0);
		helper.setBlock(getWall(), 5, 4, 8);
		helper.setBlock(getWall(), 5, 5, 0);
		helper.setBlock(getWall(), 5, 5, 1);
		helper.setBlock(getWall(), 5, 5, 2);
		helper.setBlock(getWall(), 5, 5, 3);
		helper.setBlock(getWall(), 5, 5, 4);
		helper.setBlock(getWall(), 5, 5, 5);
		helper.setBlock(getWall(), 5, 5, 6);
		helper.setBlock(getWall(), 5, 5, 7);
		helper.setBlock(getWall(), 5, 5, 8);
		helper.setBlock(getWall(), 6, 0, 0);
		helper.setBlock(getWall(), 6, 0, 1);
		helper.setBlock(getWall(), 6, 0, 2);
		helper.setBlock(getWall(), 6, 0, 3);
		helper.setBlock(getWall(), 6, 0, 4);
		helper.setBlock(getWall(), 6, 0, 5);
		helper.setBlock(getWall(), 6, 0, 6);
		helper.setBlock(getWall(), 6, 0, 7);
		helper.setBlock(getWall(), 6, 0, 8);
		helper.setBlock(getWall(), 6, 1, 0);
		helper.setBlock(getWall(), 6, 1, 8);
		helper.setBlock(getWall(), 6, 2, 0);
		helper.setBlock(getWall(), 6, 2, 8);
		helper.setBlock(getWall(), 6, 3, 0);
		helper.setBlock(getWall(), 6, 3, 8);
		helper.setBlock(getWall(), 6, 4, 0);
		helper.setBlock(getWall(), 6, 4, 8);
		helper.setBlock(getWall(), 6, 5, 0);
		helper.setBlock(getWall(), 6, 5, 1);
		helper.setBlock(getWall(), 6, 5, 2);
		helper.setBlock(getWall(), 6, 5, 3);
		helper.setBlock(getWall(), 6, 5, 4);
		helper.setBlock(getWall(), 6, 5, 5);
		helper.setBlock(getWall(), 6, 5, 6);
		helper.setBlock(getWall(), 6, 5, 7);
		helper.setBlock(getWall(), 6, 5, 8);
		helper.setBlock(getWall(), 7, 0, 0);
		helper.setBlock(getWall(), 7, 0, 1);
		helper.setBlock(getWall(), 7, 0, 2);
		helper.setBlock(getWall(), 7, 0, 3);
		helper.setBlock(getWall(), 7, 0, 4);
		helper.setBlock(getWall(), 7, 0, 5);
		helper.setBlock(getWall(), 7, 0, 6);
		helper.setBlock(getWall(), 7, 0, 7);
		helper.setBlock(getWall(), 7, 0, 8);
		helper.setBlock(getWall(), 7, 1, 0);
		helper.setBlock(getWall(), 7, 1, 8);
		helper.setBlock(getWall(), 7, 2, 0);
		helper.setBlock(getWall(), 7, 2, 8);
		helper.setBlock(getWall(), 7, 3, 0);
		helper.setBlock(getWall(), 7, 3, 8);
		helper.setBlock(getWall(), 7, 4, 0);
		helper.setBlock(getWall(), 7, 4, 8);
		helper.setBlock(getWall(), 7, 5, 0);
		helper.setBlock(getWall(), 7, 5, 1);
		helper.setBlock(getWall(), 7, 5, 2);
		helper.setBlock(getWall(), 7, 5, 3);
		helper.setBlock(getWall(), 7, 5, 4);
		helper.setBlock(getWall(), 7, 5, 5);
		helper.setBlock(getWall(), 7, 5, 6);
		helper.setBlock(getWall(), 7, 5, 7);
		helper.setBlock(getWall(), 7, 5, 8);
		helper.setBlock(getWall(), 8, 0, 0);
		helper.setBlock(getWall(), 8, 0, 1);
		helper.setBlock(getWall(), 8, 0, 2);
		helper.setBlock(getWall(), 8, 0, 3);
		helper.setBlock(getWall(), 8, 0, 4);
		helper.setBlock(getWall(), 8, 0, 5);
		helper.setBlock(getWall(), 8, 0, 6);
		helper.setBlock(getWall(), 8, 0, 7);
		helper.setBlock(getWall(), 8, 0, 8);
		helper.setBlock(getWall(), 8, 1, 0);
		helper.setBlock(getWall(), 8, 1, 1);
		helper.setBlock(getWall(), 8, 1, 2);
		helper.setBlock(getWall(), 8, 1, 3);
		helper.setBlock(getWall(), 8, 1, 4);
		helper.setBlock(getWall(), 8, 1, 5);
		helper.setBlock(getWall(), 8, 1, 6);
		helper.setBlock(getWall(), 8, 1, 7);
		helper.setBlock(getWall(), 8, 1, 8);
		helper.setBlock(getWall(), 8, 2, 0);
		helper.setBlock(getWall(), 8, 2, 1);
		helper.setBlock(getWall(), 8, 2, 2);
		helper.setBlock(getWall(), 8, 2, 3);
		helper.setBlock(getWall(), 8, 2, 4);
		helper.setBlock(getWall(), 8, 2, 5);
		helper.setBlock(getWall(), 8, 2, 6);
		helper.setBlock(getWall(), 8, 2, 7);
		helper.setBlock(getWall(), 8, 2, 8);
		helper.setBlock(getWall(), 8, 3, 0);
		helper.setBlock(getWall(), 8, 3, 1);
		helper.setBlock(getWall(), 8, 3, 2);
		helper.setBlock(getWall(), 8, 3, 3);
		helper.setBlock(getWall(), 8, 3, 4);
		helper.setBlock(getWall(), 8, 3, 5);
		helper.setBlock(getWall(), 8, 3, 6);
		helper.setBlock(getWall(), 8, 3, 7);
		helper.setBlock(getWall(), 8, 3, 8);
		helper.setBlock(getWall(), 8, 4, 0);
		helper.setBlock(getWall(), 8, 4, 1);
		helper.setBlock(getWall(), 8, 4, 2);
		helper.setBlock(getWall(), 8, 4, 3);
		helper.setBlock(getWall(), 8, 4, 4);
		helper.setBlock(getWall(), 8, 4, 5);
		helper.setBlock(getWall(), 8, 4, 6);
		helper.setBlock(getWall(), 8, 4, 7);
		helper.setBlock(getWall(), 8, 4, 8);
		helper.setBlock(getWall(), 8, 5, 0);
		helper.setBlock(getWall(), 8, 5, 1);
		helper.setBlock(getWall(), 8, 5, 2);
		helper.setBlock(getWall(), 8, 5, 3);
		helper.setBlock(getWall(), 8, 5, 4);
		helper.setBlock(getWall(), 8, 5, 5);
		helper.setBlock(getWall(), 8, 5, 6);
		helper.setBlock(getWall(), 8, 5, 7);
		helper.setBlock(getWall(), 8, 5, 8);
		for (int i = 1; i < 3; i++)
		{
			helper.setBlockToAir(4, i, 0);
			helper.setBlockToAir(0, i, 4);
			helper.setBlockToAir(8, i, 4);
			helper.setBlockToAir(4, i, 8);
		}
	}

	private Block getWall()
	{
		return Rand.nextBoolean() ? Blocks.cobblestone : Blocks.mossy_cobblestone;
	}

	public static String randomRoom()
	{
		List<Rooms> rooms = JavaHelp.newArrayList();
		for (Rooms room : Rooms.values())
		{
			for (int j = 0; j < room.ordinal() + 1; j++)
			{
				rooms.add(room);
			}
		}
		Collections.shuffle(rooms);
		return String.valueOf(rooms.get(Rand.nextInt(rooms.size())).ordinal());
	}

	public static enum Rooms implements Comparable<Rooms>
	{
		REWARD,
		REWARD_TRAP,
		SPAWNER,
		TRAP;
	}
}
