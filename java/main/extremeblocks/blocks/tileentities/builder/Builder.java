package main.extremeblocks.blocks.tileentities.builder;

import main.com.hk.testing.util.MPUtil;
import main.com.hk.testing.util.StackHelper;
import main.com.hk.testing.util.WorldUtil;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Builder
{
	private World worldObj;
	private int baseX, baseY, baseZ;
	public boolean done;

	public Builder(World worldObj, int baseX, int baseY, int baseZ)
	{
		this.worldObj = worldObj;
		this.baseX = baseX;
		this.baseY = baseY;
		this.baseZ = baseZ;
	}

	public static int maxStructures()
	{
		return 5;
	}

	public static int getPowerNeeded(int buildingMode)
	{
		switch (buildingMode)
		{
			case 1:
				return 300;
			case 2:
				return 1000;
			case 3:
				return 4500;
			case 4:
				return 2300;
			case 5:
				return 3700;
		}
		return 0;
	}

	public void buildStructure(int buildingMode, int stage)
	{
		switch (buildingMode)
		{
			case 1:
				buildDirtHut(stage);
				break;
			case 2:
				buildFarm(stage);
				break;
			case 3:
				buildHouse(stage);
				break;
			case 4:
				buildSmallBakery(stage);
				break;
			case 5:
				buildSteakHouse(stage);
				break;
		}
	}

	public static String getStructure(int buildingMode)
	{
		switch (buildingMode)
		{
			case 1:
				return "Epic Dirt Hut";
			case 2:
				return "Small Village Farm";
			case 3:
				return "Two Story House";
			case 4:
				return "Small Bakery";
			case 5:
				return "Steak House";
		}
		return "None";
	}

	public static int getMaxStages(int buildingMode)
	{
		switch (buildingMode)
		{
			case 1:
				return 3;
			case 2:
				return 6;
			case 3:
				return 15;
			case 4:
				return 15;
			case 5:
				return 20;
		}
		return 0;
	}

	public static int getDistance(int buildingMode)
	{
		switch (buildingMode)
		{
			case 1:
			case 2:
			case 3:
				return 4;
			case 4:
				return 7;
			case 5:
				return 9;
		}

		return 1;
	}

	private void buildSteakHouse(int stage)
	{
		switch (stage)
		{
			case 0:
			{
				for (int i = 6; i >= 0; i--)
				{
					WorldUtil.createLayer(worldObj, baseX, baseY + i, baseZ, Blocks.air, 12, 16, false, false);
				}
				break;
			}
			case 1:
			{
				for (int i = 5; i >= 0; i--)
				{
					WorldUtil.createLayer(worldObj, baseX, baseY + i, baseZ, Blocks.planks, 12, 16, false, false);
					WorldUtil.createLayer(worldObj, baseX, baseY + i, baseZ, Blocks.air, 10, 14, false, false);
				}
				WorldUtil.createLayer(worldObj, baseX, baseY + 5, baseZ, Blocks.planks, 12, 16, false, false);
				WorldUtil.createLayer(worldObj, baseX, baseY, baseZ, Blocks.planks, 12, 16, false, false);
				break;
			}
			case 2:
			{
				WorldUtil.createLayer(worldObj, baseX - 1, baseY + 1, baseZ - 5, Blocks.planks, 10, 0, false, false);
				WorldUtil.setBlock(worldObj, baseX + 4, baseY + 1, baseZ - 6, Blocks.fence);
				WorldUtil.setBlock(worldObj, baseX + 4, baseY + 1, baseZ - 7, Blocks.fence_gate, 1);
				WorldUtil.setBlockWithItemStack(worldObj, baseX + 3, baseY + 1, baseZ - 6, Blocks.chest, new ItemStack(Items.cooked_beef, 12), new ItemStack(Items.cooked_porkchop, 4), new ItemStack(Items.beef, 17), new ItemStack(Items.porkchop, 8));
				WorldUtil.setBlock(worldObj, baseX - 5, baseY + 1, baseZ - 6, Blocks.crafting_table);
				WorldUtil.setBlock(worldObj, baseX - 5, baseY + 1, baseZ - 7, Blocks.crafting_table);
				WorldUtil.setBlock(worldObj, baseX - 6, baseY + 1, baseZ - 6, Blocks.cobblestone);
				WorldUtil.setBlock(worldObj, baseX - 6, baseY + 1, baseZ - 7, Blocks.cobblestone);
				WorldUtil.setBlockWithItemStack(worldObj, baseX - 6, baseY + 2, baseZ - 6, Blocks.furnace, new ItemStack(Items.cooked_beef, 6));
				WorldUtil.setBlockWithItemStack(worldObj, baseX - 6, baseY + 2, baseZ - 7, Blocks.furnace, new ItemStack(Items.cooked_beef, 11));
				break;
			}
			case 3:
			{
				/* CHAIRS */
				// < = +Z | > = -Z | V = +X | ^ = -X
				// X, Y, Z, META
				WorldUtil.setBlock(worldObj, baseX + 0, baseY + 1, baseZ + 1, Blocks.oak_stairs, 3);
				WorldUtil.setBlock(worldObj, baseX + 0, baseY + 1, baseZ - 1, Blocks.oak_stairs, 2);
				WorldUtil.setBlock(worldObj, baseX + 0, baseY + 1, baseZ - 3, Blocks.oak_stairs, 3);
				WorldUtil.setBlock(worldObj, baseX + 0, baseY + 1, baseZ + 3, Blocks.oak_stairs, 2);
				WorldUtil.setBlock(worldObj, baseX + 1, baseY + 1, baseZ + 2, Blocks.oak_stairs, 0);
				WorldUtil.setBlock(worldObj, baseX - 1, baseY + 1, baseZ + 2, Blocks.oak_stairs, 1);
				WorldUtil.setBlock(worldObj, baseX + 1, baseY + 1, baseZ - 2, Blocks.oak_stairs, 0);
				WorldUtil.setBlock(worldObj, baseX - 1, baseY + 1, baseZ - 2, Blocks.oak_stairs, 1);
				break;
			}
			case 4:
			{
				WorldUtil.setBlock(worldObj, baseX + 4, baseY + 1, baseZ + 0, Blocks.oak_stairs, 2);
				WorldUtil.setBlock(worldObj, baseX + 5, baseY + 1, baseZ + 0, Blocks.oak_stairs, 2);
				WorldUtil.setBlock(worldObj, baseX + 4, baseY + 1, baseZ - 2, Blocks.oak_stairs, 3);
				WorldUtil.setBlock(worldObj, baseX + 5, baseY + 1, baseZ - 2, Blocks.oak_stairs, 3);
				WorldUtil.setBlock(worldObj, baseX + 4, baseY + 1, baseZ + 1, Blocks.oak_stairs, 3);
				WorldUtil.setBlock(worldObj, baseX + 5, baseY + 1, baseZ + 1, Blocks.oak_stairs, 3);
				WorldUtil.setBlock(worldObj, baseX + 4, baseY + 1, baseZ + 3, Blocks.oak_stairs, 2);
				WorldUtil.setBlock(worldObj, baseX + 5, baseY + 1, baseZ + 3, Blocks.oak_stairs, 2);
				break;
			}
			case 5:
			{
				WorldUtil.setBlock(worldObj, baseX - 4, baseY + 1, baseZ + 0, Blocks.oak_stairs, 2);
				WorldUtil.setBlock(worldObj, baseX - 5, baseY + 1, baseZ + 0, Blocks.oak_stairs, 2);
				WorldUtil.setBlock(worldObj, baseX - 4, baseY + 1, baseZ - 2, Blocks.oak_stairs, 3);
				WorldUtil.setBlock(worldObj, baseX - 5, baseY + 1, baseZ - 2, Blocks.oak_stairs, 3);
				WorldUtil.setBlock(worldObj, baseX - 4, baseY + 1, baseZ + 1, Blocks.oak_stairs, 3);
				WorldUtil.setBlock(worldObj, baseX - 5, baseY + 1, baseZ + 1, Blocks.oak_stairs, 3);
				WorldUtil.setBlock(worldObj, baseX - 4, baseY + 1, baseZ + 3, Blocks.oak_stairs, 2);
				WorldUtil.setBlock(worldObj, baseX - 5, baseY + 1, baseZ + 3, Blocks.oak_stairs, 2);
				break;
			}
			case 6:
			{
				WorldUtil.setBlock(worldObj, baseX + 2, baseY + 1, baseZ + 6, Blocks.oak_stairs, 0);
				WorldUtil.setBlock(worldObj, baseX + 2, baseY + 1, baseZ + 7, Blocks.oak_stairs, 0);
				WorldUtil.setBlock(worldObj, baseX + 3, baseY + 1, baseZ + 6, Blocks.oak_stairs, 1);
				WorldUtil.setBlock(worldObj, baseX + 3, baseY + 1, baseZ + 7, Blocks.oak_stairs, 1);
				WorldUtil.setBlock(worldObj, baseX + 5, baseY + 1, baseZ + 6, Blocks.oak_stairs, 0);
				WorldUtil.setBlock(worldObj, baseX + 5, baseY + 1, baseZ + 7, Blocks.oak_stairs, 0);
				break;
			}
			case 7:
			{
				WorldUtil.setBlock(worldObj, baseX - 2, baseY + 1, baseZ + 6, Blocks.oak_stairs, 1);
				WorldUtil.setBlock(worldObj, baseX - 2, baseY + 1, baseZ + 7, Blocks.oak_stairs, 1);
				WorldUtil.setBlock(worldObj, baseX - 3, baseY + 1, baseZ + 6, Blocks.oak_stairs, 0);
				WorldUtil.setBlock(worldObj, baseX - 3, baseY + 1, baseZ + 7, Blocks.oak_stairs, 0);
				WorldUtil.setBlock(worldObj, baseX - 5, baseY + 1, baseZ + 6, Blocks.oak_stairs, 1);
				WorldUtil.setBlock(worldObj, baseX - 5, baseY + 1, baseZ + 7, Blocks.oak_stairs, 1);
				break;
			}
			case 8:
			{
				WorldUtil.setBlock(worldObj, baseX, baseY + 1, baseZ + 6, Blocks.glass_pane);
				WorldUtil.setBlock(worldObj, baseX, baseY + 1, baseZ + 7, Blocks.glass_pane);
				WorldUtil.setBlock(worldObj, baseX, baseY + 2, baseZ + 6, Blocks.glass_pane);
				WorldUtil.setBlock(worldObj, baseX, baseY + 2, baseZ + 7, Blocks.glass_pane);
				WorldUtil.setBlock(worldObj, baseX, baseY + 3, baseZ + 6, Blocks.glass_pane);
				WorldUtil.setBlock(worldObj, baseX, baseY + 3, baseZ + 7, Blocks.glass_pane);
				break;
			}
			case 9:
			{
				WorldUtil.createLayer(worldObj, baseX, baseY + 2, baseZ + 8, Blocks.glass_pane, 10, 0, false, false);
				WorldUtil.createLayer(worldObj, baseX, baseY + 3, baseZ + 8, Blocks.glass_pane, 10, 0, false, false);
				break;
			}
			case 10:
			{
				WorldUtil.createLayer(worldObj, baseX + 6, baseY + 2, baseZ, Blocks.glass_pane, 0, 10, false, false);
				WorldUtil.createLayer(worldObj, baseX + 6, baseY + 3, baseZ, Blocks.glass_pane, 0, 10, false, false);
				break;
			}
			case 11:
			{
				WorldUtil.createLayer(worldObj, baseX - 6, baseY + 2, baseZ, Blocks.glass_pane, 0, 10, false, false);
				WorldUtil.createLayer(worldObj, baseX - 6, baseY + 3, baseZ, Blocks.glass_pane, 0, 10, false, false);
				break;
			}
			case 12:
			{
				createTable(1, 1, 6);
				createTable(1, 1, 7);
				createTable(-1, 1, 6);
				createTable(-1, 1, 7);
				break;
			}
			case 13:
			{
				createTable(4, 1, 6);
				createTable(4, 1, 7);
				createTable(-4, 1, 6);
				createTable(-4, 1, 7);
				break;
			}
			case 14:
			{
				createTable(0, 1, 2);
				createTable(0, 1, -2);
				break;
			}
			case 15:
			{
				createTable(4, 1, -1);
				createTable(5, 1, -1);
				createTable(-4, 1, -1);
				createTable(-5, 1, -1);
				break;
			}
			case 16:
			{
				createTable(4, 1, 2);
				createTable(5, 1, 2);
				createTable(-4, 1, 2);
				createTable(-5, 1, 2);
				break;
			}
			case 17:
			{
				ItemDoor.placeDoorBlock(worldObj, baseX + 6, baseY + 1, baseZ - 5, 0, Blocks.wooden_door);
				ItemDoor.placeDoorBlock(worldObj, baseX + 6, baseY + 1, baseZ - 6, 0, Blocks.wooden_door);
				WorldUtil.setBlock(worldObj, baseX + 6, baseY + 3, baseZ - 5, Blocks.planks);
				WorldUtil.setBlock(worldObj, baseX + 6, baseY + 3, baseZ - 4, Blocks.planks);
				WorldUtil.setBlock(worldObj, baseX + 6, baseY + 2, baseZ - 4, Blocks.planks);
				break;
			}
			case 18:
			{
				WorldUtil.createLayer(worldObj, baseX, baseY + 4, baseZ, Blocks.planks, 0, 14, false, false);
				WorldUtil.createLayer(worldObj, baseX + 1, baseY + 4, baseZ, Blocks.torch, 0, 14, false, true);
				WorldUtil.createLayer(worldObj, baseX - 1, baseY + 4, baseZ, Blocks.torch, 0, 14, false, true);
				WorldUtil.createLayer(worldObj, baseX + 5, baseY + 4, baseZ, Blocks.torch, 0, 14, false, true);
				WorldUtil.createLayer(worldObj, baseX - 5, baseY + 4, baseZ, Blocks.torch, 0, 14, false, true);
				break;
			}
			case 19:
			{
				done = true;
				MPUtil.sendMessage("Your Steak House is Done Building!");
				break;
			}
		}
	}

	private void buildHouse(int stage)
	{
		switch (stage)
		{
			case 0:
			{
				for (int i = 7; i >= 0; i--)
				{
					WorldUtil.createLayer(worldObj, baseX, baseY + i, baseZ, Blocks.air, 6, 8, false, false);
				}
				break;
			}
			case 1:
			{
				WorldUtil.createLayer(worldObj, baseX, baseY, baseZ, Blocks.brick_block, 6, 8, false, false);
				break;
			}
			case 2:
			{
				WorldUtil.createLayer(worldObj, baseX, baseY + 1, baseZ, Blocks.brick_block, 6, 8, false, false);
				WorldUtil.createLayer(worldObj, baseX, baseY + 1, baseZ, Blocks.air, 4, 6, false, false);
				break;
			}
			case 3:
			{
				WorldUtil.createLayer(worldObj, baseX, baseY + 2, baseZ, Blocks.brick_block, 6, 8, false, false);
				WorldUtil.createLayer(worldObj, baseX, baseY + 2, baseZ, Blocks.air, 4, 6, false, false);
				break;
			}
			case 4:
			{
				WorldUtil.createLayer(worldObj, baseX, baseY + 3, baseZ, Blocks.brick_block, 6, 8, false, false);
				break;
			}
			case 5:
			{
				WorldUtil.createLayer(worldObj, baseX, baseY + 4, baseZ, Blocks.brick_block, 6, 8, false, false);
				WorldUtil.createLayer(worldObj, baseX, baseY + 4, baseZ, Blocks.air, 4, 6, false, false);
				break;
			}
			case 6:
			{
				WorldUtil.createLayer(worldObj, baseX, baseY + 5, baseZ, Blocks.brick_block, 6, 8, false, false);
				WorldUtil.createLayer(worldObj, baseX, baseY + 5, baseZ, Blocks.air, 4, 6, false, false);
				break;
			}
			case 7:
			{
				WorldUtil.createLayer(worldObj, baseX, baseY + 6, baseZ, Blocks.brick_block, 6, 8, false, false);
				break;
			}
			case 8:
			{
				WorldUtil.setBlock(worldObj, baseX + 3, baseY + 2, baseZ, Blocks.glass_pane);
				WorldUtil.setBlock(worldObj, baseX + 3, baseY + 2, baseZ + 1, Blocks.glass_pane);
				WorldUtil.setBlock(worldObj, baseX - 3, baseY + 2, baseZ + 1, Blocks.glass_pane);
				WorldUtil.setBlock(worldObj, baseX + 3, baseY + 2, baseZ + 2, Blocks.glass_pane);
				WorldUtil.setBlock(worldObj, baseX - 3, baseY + 2, baseZ + 2, Blocks.glass_pane);
				WorldUtil.setBlock(worldObj, baseX, baseY + 2, baseZ - 4, Blocks.glass_pane);
				break;
			}
			case 9:
			{
				WorldUtil.setBlock(worldObj, baseX + 3, baseY + 5, baseZ, Blocks.glass_pane);
				WorldUtil.setBlock(worldObj, baseX + 3, baseY + 5, baseZ + 1, Blocks.glass_pane);
				WorldUtil.setBlock(worldObj, baseX - 3, baseY + 5, baseZ + 1, Blocks.glass_pane);
				WorldUtil.setBlock(worldObj, baseX + 3, baseY + 5, baseZ + 2, Blocks.glass_pane);
				WorldUtil.setBlock(worldObj, baseX - 3, baseY + 5, baseZ + 2, Blocks.glass_pane);
				WorldUtil.setBlock(worldObj, baseX, baseY + 5, baseZ - 4, Blocks.glass_pane);
				break;
			}
			case 10:
			{
				WorldUtil.setBlock(worldObj, baseX + 3, baseY + 1, baseZ - 2, Blocks.air);
				WorldUtil.setBlock(worldObj, baseX + 3, baseY + 2, baseZ - 2, Blocks.air);
				WorldUtil.setBlock(worldObj, baseX + 4, baseY + 2, baseZ - 1, Blocks.air);
				ItemDoor.placeDoorBlock(worldObj, baseX + 3, baseY + 1, baseZ - 2, 0, Blocks.iron_door);
				WorldUtil.setBlock(worldObj, baseX + 4, baseY + 2, baseZ - 1, Blocks.stone_button, 1);
				WorldUtil.setBlock(worldObj, baseX + 2, baseY + 2, baseZ - 1, Blocks.stone_button, 2);
				break;
			}
			case 11:
			{
				WorldUtil.setBlock(worldObj, baseX, baseY + 1, baseZ + 3, Blocks.brick_stairs, 1);
				WorldUtil.setBlock(worldObj, baseX - 1, baseY + 2, baseZ + 3, Blocks.brick_stairs, 1);
				WorldUtil.setBlock(worldObj, baseX - 1, baseY + 1, baseZ + 3, Blocks.brick_block);
				WorldUtil.setBlock(worldObj, baseX - 2, baseY + 3, baseZ + 3, Blocks.brick_stairs, 1);
				WorldUtil.setBlock(worldObj, baseX - 2, baseY + 2, baseZ + 3, Blocks.brick_block);
				WorldUtil.setBlock(worldObj, baseX - 2, baseY + 1, baseZ + 3, Blocks.brick_block);
				WorldUtil.setBlock(worldObj, baseX, baseY + 3, baseZ + 3, Blocks.air);
				WorldUtil.setBlock(worldObj, baseX - 1, baseY + 3, baseZ + 3, Blocks.air);
				WorldUtil.setBlock(worldObj, baseX + 1, baseY + 3, baseZ + 3, Blocks.air);
				break;
			}
			case 12:
			{
				WorldUtil.setBlock(worldObj, baseX - 2, baseY + 2, baseZ, Blocks.torch);
				WorldUtil.setBlock(worldObj, baseX - 2, baseY + 5, baseZ, Blocks.torch);
				WorldUtil.setBlock(worldObj, baseX + 2, baseY + 2, baseZ - 3, Blocks.torch);
				WorldUtil.setBlock(worldObj, baseX + 2, baseY + 5, baseZ - 1, Blocks.torch);
				break;
			}
			case 13:
			{
				WorldUtil.setBlock(worldObj, baseX - 2, baseY + 1, baseZ - 2, Blocks.chest);
				WorldUtil.setBlock(worldObj, baseX - 2, baseY + 1, baseZ - 3, Blocks.crafting_table);

				WorldUtil.setBlock(worldObj, baseX - 2, baseY + 1, baseZ + 2, Blocks.chest);
				WorldUtil.setBlock(worldObj, baseX - 2, baseY + 1, baseZ + 1, Blocks.chest);

				WorldUtil.setBlockWithItemStackInSlot(worldObj, baseX - 2, baseY + 4, baseZ - 3, Blocks.furnace, new ItemStack(Items.coal, 3), 1);
				WorldUtil.setBlockWithItemStackInSlot(worldObj, baseX - 2, baseY + 5, baseZ - 3, Blocks.furnace, new ItemStack(Items.coal, 7), 1);

				WorldUtil.setBlockWithItemStack(worldObj, baseX + 2, baseY + 4, baseZ - 3, Blocks.chest, new ItemStack(Blocks.brick_block, 10));
				WorldUtil.setBlock(worldObj, baseX + 2, baseY + 4, baseZ - 2, Blocks.chest);
				break;
			}
			case 14:
			{
				done = true;
				MPUtil.sendMessage("Your House is Done Building!");
				break;
			}
		}
	}

	private void buildDirtHut(int stage)
	{
		switch (stage)
		{
			case 0:
			{
				WorldUtil.fillWithBlocks(worldObj, baseX, baseY + 3, baseZ, Blocks.dirt, false, false, 4);
				WorldUtil.fillWithBlocks(worldObj, baseX, baseY + 3, baseZ, Blocks.air, false, false, 3);
				break;
			}
			case 1:
			{
				WorldUtil.createLayer(worldObj, baseX, baseY + 6, baseZ, Blocks.torch, 5, 5, true, true);
				break;
			}
			case 2:
			{
				done = true;
				MPUtil.sendMessage("Your Dirt Hut is Done Building!");
				break;
			}
		}
	}

	private void buildSmallBakery(int stage)
	{
		switch (stage)
		{
			case 0:
			{
				for (int i = 7; i >= 0; i--)
				{
					WorldUtil.createLayer(worldObj, baseX, baseY + i, baseZ, Blocks.air, 8, 12, false, false);
				}
				break;
			}
			case 1:
			{
				WorldUtil.createLayer(worldObj, baseX, baseY, baseZ, Blocks.cobblestone, 8, 12, false, false);
				break;
			}
			case 2:
			{
				WorldUtil.createLayer(worldObj, baseX, baseY + 1, baseZ, Blocks.planks, 8, 12, false, false);
				WorldUtil.createLayer(worldObj, baseX, baseY + 1, baseZ, Blocks.air, 6, 10, false, false);
				break;
			}
			case 3:
			{
				WorldUtil.createLayer(worldObj, baseX, baseY + 2, baseZ, Blocks.planks, 8, 12, false, false);
				WorldUtil.createLayer(worldObj, baseX, baseY + 2, baseZ, Blocks.air, 6, 10, false, false);
				break;
			}
			case 4:
			{
				WorldUtil.createLayer(worldObj, baseX, baseY + 3, baseZ, Blocks.planks, 8, 12, false, false);
				WorldUtil.createLayer(worldObj, baseX, baseY + 3, baseZ, Blocks.air, 6, 10, false, false);
				break;
			}
			case 5:
			{
				WorldUtil.createLayer(worldObj, baseX, baseY + 4, baseZ, Blocks.planks, 6, 10, false, false);
				WorldUtil.createLayer(worldObj, baseX, baseY + 4, baseZ, Blocks.air, 4, 8, false, false);
				break;
			}
			case 6:
			{
				WorldUtil.createLayer(worldObj, baseX, baseY + 5, baseZ, Blocks.planks, 4, 8, false, false);
				WorldUtil.createLayer(worldObj, baseX, baseY + 5, baseZ, Blocks.air, 2, 6, false, false);
				break;
			}
			case 7:
			{
				WorldUtil.createLayer(worldObj, baseX, baseY + 6, baseZ, Blocks.planks, 2, 6, false, false);
				WorldUtil.createLayer(worldObj, baseX, baseY + 6, baseZ, Blocks.air, 0, 4, false, false);
				break;
			}
			case 8:
			{
				WorldUtil.createLayer(worldObj, baseX, baseY + 7, baseZ, Blocks.wooden_slab, 0, 4, false, false);
				break;
			}
			case 9:
			{
				ItemDoor.placeDoorBlock(worldObj, baseX + 4, baseY + 1, baseZ + 4, 0, Blocks.wooden_door);
				break;
			}
			case 10:
			{
				WorldUtil.setBlock(worldObj, baseX + 4, baseY + 2, baseZ + 2, Blocks.glass_pane);
				WorldUtil.setBlock(worldObj, baseX + 4, baseY + 2, baseZ + 1, Blocks.glass_pane);
				WorldUtil.setBlock(worldObj, baseX + 4, baseY + 2, baseZ + 0, Blocks.glass_pane);
				WorldUtil.setBlock(worldObj, baseX + 4, baseY + 2, baseZ - 1, Blocks.glass_pane);
				WorldUtil.setBlock(worldObj, baseX + 4, baseY + 2, baseZ - 2, Blocks.glass_pane);
				WorldUtil.setBlock(worldObj, baseX - 4, baseY + 2, baseZ + 2, Blocks.glass_pane);
				WorldUtil.setBlock(worldObj, baseX - 4, baseY + 2, baseZ + 1, Blocks.glass_pane);
				WorldUtil.setBlock(worldObj, baseX - 4, baseY + 2, baseZ + 0, Blocks.glass_pane);

				WorldUtil.setBlock(worldObj, baseX + 1, baseY + 2, baseZ + 6, Blocks.glass_pane);
				WorldUtil.setBlock(worldObj, baseX + 0, baseY + 2, baseZ + 6, Blocks.glass_pane);
				WorldUtil.setBlock(worldObj, baseX - 1, baseY + 2, baseZ + 6, Blocks.glass_pane);
				WorldUtil.setBlock(worldObj, baseX + 1, baseY + 2, baseZ - 6, Blocks.glass_pane);
				WorldUtil.setBlock(worldObj, baseX + 0, baseY + 2, baseZ - 6, Blocks.glass_pane);
				WorldUtil.setBlock(worldObj, baseX - 1, baseY + 2, baseZ - 6, Blocks.glass_pane);
				break;
			}
			case 11:
			{
				WorldUtil.setBlock(worldObj, baseX - 1, baseY + 1, baseZ + 4, Blocks.oak_stairs);
				WorldUtil.setBlock(worldObj, baseX - 3, baseY + 1, baseZ + 4, Blocks.oak_stairs, 1);
				WorldUtil.setBlock(worldObj, baseX - 2, baseY + 1, baseZ + 3, Blocks.oak_stairs, 3);
				WorldUtil.setBlock(worldObj, baseX - 2, baseY + 1, baseZ + 5, Blocks.oak_stairs, 2);
				createTable(-2, 1, 4);
				WorldUtil.createLayer(worldObj, baseX, baseY + 1, baseZ, Blocks.log, 0, 4, false, false);
				break;
			}
			case 12:
			{
				WorldUtil.setBlock(worldObj, baseX + 3, baseY + 1, baseZ + 2, Blocks.cake);
				WorldUtil.setBlock(worldObj, baseX + 3, baseY + 1, baseZ + 1, Blocks.cake);
				WorldUtil.setBlock(worldObj, baseX + 3, baseY + 1, baseZ + 0, Blocks.cake);
				WorldUtil.setBlock(worldObj, baseX + 3, baseY + 1, baseZ - 1, Blocks.cake);
				WorldUtil.setBlock(worldObj, baseX + 3, baseY + 1, baseZ - 2, Blocks.cake);
				break;
			}
			case 13:
			{
				WorldUtil.setBlockWithItemStack(worldObj, baseX - 3, baseY + 1, baseZ - 2, Blocks.furnace, new ItemStack(Items.bread));
				WorldUtil.setBlockWithItemStack(worldObj, baseX - 3, baseY + 1, baseZ - 3, Blocks.furnace, new ItemStack(Items.bread));
				WorldUtil.setBlockWithItemStack(worldObj, baseX - 3, baseY + 1, baseZ - 4, Blocks.furnace, new ItemStack(Items.bread));
				WorldUtil.setBlockWithItemStack(worldObj, baseX - 3, baseY + 1, baseZ - 5, Blocks.furnace, new ItemStack(Items.bread));
				break;
			}
			case 14:
			{
				done = true;
				MPUtil.sendMessage("Your Small Bakery is Done Building!");
				break;
			}
		}
	}

	private void buildFarm(int stage)
	{
		switch (stage)
		{
			case 0:
			{
				WorldUtil.createLayer(worldObj, baseX, baseY - 1, baseZ, Blocks.grass, 6, 4, false, false);
				break;
			}
			case 1:
			{
				WorldUtil.createLayer(worldObj, baseX, baseY, baseZ, Blocks.log, 6, 4, false, false);
				WorldUtil.createLayer(worldObj, baseX, baseY, baseZ, Blocks.air, 4, 2, false, false);
				break;
			}
			case 2:
			{
				WorldUtil.createLayer(worldObj, baseX, baseY, baseZ, Blocks.farmland, 4, 2, false, false);
				WorldUtil.createLayer(worldObj, baseX, baseY, baseZ, Blocks.air, 1, 0, false, false);
				break;
			}
			case 3:
			{
				WorldUtil.createLayer(worldObj, baseX, baseY, baseZ, Blocks.flowing_water, 1, 0, false, false);
				break;
			}
			case 4:
			{
				WorldUtil.createLayer(worldObj, baseX, baseY + 1, baseZ, Blocks.wheat, 4, 2, false, true);
				break;
			}
			case 5:
			{
				done = true;
				MPUtil.sendMessage("Your Farm is Done Building!");
				break;
			}
		}
	}

	public void createTable(int x, int y, int z)
	{
		WorldUtil.setBlock(worldObj, baseX + x, baseY + y, baseZ + z, Blocks.fence);
		WorldUtil.setBlock(worldObj, baseX + x, baseY + y + 1, baseZ + z, Blocks.wooden_pressure_plate);
	}
}
