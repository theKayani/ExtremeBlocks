package main.extremeblocks.network.packets;

import io.netty.buffer.ByteBuf;
import main.extremeblocks.entities.mobs.EntityRobot;
import main.extremeblocks.network.AbstractPacket;
import net.minecraft.entity.player.EntityPlayer;

public class PacketStartTask extends AbstractPacket
{
	private int entityID;
	private boolean startTask;

	public PacketStartTask()
	{
	}

	public PacketStartTask(EntityRobot robot, boolean startTask)
	{
		entityID = robot.getEntityId();
		this.startTask = startTask;
	}

	@Override
	public void encodeInto(ByteBuf buffer)
	{
		buffer.writeInt(entityID);
		buffer.writeBoolean(startTask);
	}

	@Override
	public void decodeInto(ByteBuf buffer)
	{
		entityID = buffer.readInt();
		startTask = buffer.readBoolean();
	}

	@Override
	public void handleClientSide(EntityPlayer player)
	{
		if (startTask)
		{
			((EntityRobot) player.worldObj.getEntityByID(entityID)).startTask();
		}
		else
		{
			((EntityRobot) player.worldObj.getEntityByID(entityID)).endTask();
		}
	}

	@Override
	public void handleServerSide(EntityPlayer player)
	{
		if (startTask)
		{
			((EntityRobot) player.worldObj.getEntityByID(entityID)).startTask();
		}
		else
		{
			((EntityRobot) player.worldObj.getEntityByID(entityID)).endTask();
		}
	}
}
