package main.extremeblocks.tileentities.energy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import main.com.hk.eb.util.JavaHelp;
import main.com.hk.eb.util.Vector3I;
import main.extremeblocks.tileentities.TileEntityWire;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class WirePath
{
	public final long seed;
	public final World world;
	public final Vector3I start, end;
	public final Vector3I[] validPositions;
	private final List<Vector3I> hasVisited;
	private final List<Vector3I> correctPath;

	public WirePath(long seed, World world, Vector3I start, Vector3I end, Vector3I[] validPositions)
	{
		this.seed = seed;
		this.world = world;
		this.start = start;
		this.end = end;
		this.validPositions = validPositions;
		hasVisited = JavaHelp.newArrayList();
		correctPath = JavaHelp.newArrayList();
		recursiveSolve(start);
	}

	private boolean recursiveSolve(Vector3I pos)
	{
		if (pos.equals(end))
		{
			correctPath.remove(pos);
			return true;
		}
		if (!(world.getTileEntity(pos.x, pos.y, pos.z) instanceof TileEntityWire) || hasVisited.contains(pos)) return false;
		hasVisited.add(pos);
		boolean done = false;
		List<ForgeDirection> dirs = new ArrayList<ForgeDirection>(Arrays.asList(ForgeDirection.VALID_DIRECTIONS));
		Collections.shuffle(dirs, new Random(seed));
		for (ForgeDirection d : dirs)
		{
			done = done || doIt(pos.add(d.offsetX, d.offsetY, d.offsetZ));
		}
		return done;
	}

	private boolean doIt(Vector3I vec)
	{
		if (recursiveSolve(vec))
		{
			correctPath.add(vec);
			return true;
		}
		return false;
	}

	public List<Vector3I> getPath()
	{
		return Collections.unmodifiableList(correctPath);
	}
}
