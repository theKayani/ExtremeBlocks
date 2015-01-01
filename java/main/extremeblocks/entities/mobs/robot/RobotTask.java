package main.extremeblocks.entities.mobs.robot;

import main.com.hk.eb.util.Vector3I;
import main.extremeblocks.entities.mobs.EntityRobot;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public abstract class RobotTask
{
	private int ticks;
	private boolean done;
	public final EntityRobot robot;
	public final World world;
	public final RobotInventory inv;
	public boolean execute;

	public RobotTask(EntityRobot robot)
	{
		this.robot = robot;
		world = robot.worldObj;
		inv = robot.inv;
		execute = false;
	}

	public abstract void nextUpdate(int ticks);

	public abstract boolean keepUpdating(int ticks);

	public abstract void firstUpdate();

	public void lastUpdate()
	{
		reset();
	}

	public final RobotTask setExecute(boolean execute)
	{
		this.execute = execute;
		return this;
	}

	public void reset()
	{
		ticks = 0;
		execute = true;
		done = false;
	}

	public final void update()
	{
		if (execute && !done)
		{
			ticks++;

			if (keepUpdating(ticks))
			{
				if (ticks == 1)
				{
					firstUpdate();
				}
				else
				{
					nextUpdate(ticks);
				}
			}
			else
			{
				lastUpdate();
				done = true;
			}
		}
	}

	public boolean isDone()
	{
		return done;
	}

	public Vector3I getPos()
	{
		return new Vector3I(robot);
	}

	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		nbt.setInteger("Ticks", ticks);
		nbt.setBoolean("Done", done);
	}

	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		ticks = nbt.getInteger("Ticks");
		done = nbt.getBoolean("Done");
	}
}
