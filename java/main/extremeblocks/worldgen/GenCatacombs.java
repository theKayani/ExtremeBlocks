package main.extremeblocks.worldgen;

import java.util.ArrayList;
import java.util.Random;
import main.com.hk.eb.util.Builder;
import main.com.hk.eb.util.JavaHelp;
import main.com.hk.eb.util.Vector3I;
import main.extremeblocks.worldgen.GenManager.Gen;
import main.extremeblocks.worldgen.catacombs.CatacombHallway;
import main.extremeblocks.worldgen.catacombs.CatacombRoom;
import main.extremeblocks.worldgen.catacombs.CatacombRoom.Rooms;
import main.extremeblocks.worldgen.catacombs.CatacombTurn;
import main.extremeblocks.worldgen.catacombs.MazeGenerator;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

@Gen(name = "Catacombs", chance = 2)
public class GenCatacombs extends Generation
{
	private int size;
	private final ArrayList<Vector3I> positions = JavaHelp.newArrayList();
	private CatacombTurn turn;
	private CatacombHallway hall;
	private CatacombRoom room;

	@Override
	public Vector3I getSpawnPosition(World world, Random random, int chunkX, int chunkZ)
	{
		Vector3I vec = super.getSpawnPosition(world, random, chunkX, chunkZ);
		return vec.setY(vec.y / 2);
	}

	@Override
	public boolean canGenerateAt(Builder helper)
	{
		boolean b = helper.isAll(Blocks.stone, 0, 0, 0, 5, 5, 5) && helper.rand.nextInt(300) == 0;
		if (b)
		{
			System.err.println("Generated Catacombs at " + helper.baseX + ", " + helper.baseY + ", " + helper.baseZ);
		}
		return b;
	}

	//-182, 38, -293
	@Override
	public boolean generateStructure(Builder helper)
	{
		turn = new CatacombTurn(helper);
		hall = new CatacombHallway(helper);
		room = new CatacombRoom(helper);
		Random rand = helper.rand;
		size = rand.nextInt(5) + 3;
		String[] map = new MazeGenerator(rand, size, size).getMap();

		for (int y = 0; y < map.length; y++)
		{
			char[] chars = map[y].toCharArray();
			for (int x = 0; x < chars.length; x++)
			{
				if (Character.isDigit(chars[x]))
				{
					add(Rooms.values()[Integer.parseInt(chars[x] + "")], x, y, ForgeDirection.values());
				}
				else
				{
					switch (chars[x])
					{
						case '-':
						{
							add(false, x, y);
							break;
						}
						case '|':
						{
							add(true, x, y);
							break;
						}
						case '>':
						{
							add(180, x, y);
							break;
						}
						case '^':
						{
							add(270, x, y);
							break;
						}
						case '<':
						{
							add(0, x, y);
							break;
						}
						case 'v':
						{
							add(90, x, y);
							break;
						}
					}
				}
			}
		}
		return true;
	}

	public void add(boolean isUp, int x, int z)
	{
		Vector3I vec = new Vector3I(x, 0, z);
		if (!positions.contains(vec))
		{
			positions.add(vec);
			hall.create(isUp, x, z);
		}
	}

	public void add(int deg, int x, int z)
	{
		Vector3I vec = new Vector3I(x, 0, z);
		if (!positions.contains(vec))
		{
			positions.add(vec);
			turn.create(deg, x, z);
		}
	}

	public void add(Rooms rm, int x, int z, ForgeDirection... sides)
	{
		Vector3I vec = new Vector3I(x, 0, z);
		if (!positions.contains(vec))
		{
			positions.add(vec);
			room.create(rm, x, z, sides);
		}
	}
}
