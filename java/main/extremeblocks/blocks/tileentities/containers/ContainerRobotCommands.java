package main.extremeblocks.blocks.tileentities.containers;

import main.extremeblocks.entities.mobs.EntityRobot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.world.World;

public class ContainerRobotCommands extends Container
{
	private final World world;
	private final int x, y, z;
	private final EntityRobot robot;
	private final InventoryPlayer inv;

	public ContainerRobotCommands(InventoryPlayer inv, World world, int x, int y, int z, EntityRobot robot)
	{
		this.inv = inv;
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
		this.robot = robot;
	}

	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return player.getDistance(this.x + 0.5D, this.y + 0.5D, this.z + 0.5D) <= 8.0D;
	}
}
