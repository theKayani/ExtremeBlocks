package main.extremeblocks.network.packets;

import io.netty.buffer.ByteBuf;
import main.com.hk.eb.util.MPUtil;
import main.extremeblocks.entities.mobs.EntityRobot;
import main.extremeblocks.entities.mobs.EntityRobot.RobotType;
import main.extremeblocks.network.AbstractPacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.network.ByteBufUtils;

public class PacketSyncRobot extends AbstractPacket
{
	public int entityID, type, size, ownerID;
	public boolean stayStill, isOff, startTask;
	public double[] homePosition;
	public ItemStack[] inv;

	public PacketSyncRobot()
	{
	}

	public PacketSyncRobot(EntityRobot robot, boolean startTask)
	{
		entityID = robot.getEntityId();
		type = robot.type.ordinal();
		stayStill = robot.stayStill;
		isOff = robot.isOff;
		homePosition = robot.homePosition;
		this.startTask = startTask;
		size = robot.inv.getSizeInventory();
		inv = robot.inv.inventory;
	}

	@Override
	public void encodeInto(ByteBuf buffer)
	{
		buffer.writeInt(entityID);
		buffer.writeInt(type);
		buffer.writeBoolean(stayStill);
		buffer.writeBoolean(isOff);
		buffer.writeBoolean(startTask);
		buffer.writeDouble(homePosition[0]);
		buffer.writeDouble(homePosition[1]);
		buffer.writeDouble(homePosition[2]);
		buffer.writeInt(ownerID);
		buffer.writeInt(size);
		for (int i = 0; i < size; i++)
		{
			ByteBufUtils.writeItemStack(buffer, inv[i]);
		}
	}

	@Override
	public void decodeInto(ByteBuf buffer)
	{
		entityID = buffer.readInt();
		type = buffer.readInt();
		stayStill = buffer.readBoolean();
		isOff = buffer.readBoolean();
		startTask = buffer.readBoolean();
		homePosition = new double[] { buffer.readDouble(), buffer.readDouble(), buffer.readDouble() };
		ownerID = buffer.readInt();
		size = buffer.readInt();
		inv = new ItemStack[size];
		for (int i = 0; i < size; i++)
		{
			inv[i] = ByteBufUtils.readItemStack(buffer);
		}
	}

	@Override
	public void handleClientSide(EntityPlayer player)
	{
		if (MPUtil.isClientSide())
		{
			handle(player);
		}
	}

	@Override
	public void handleServerSide(EntityPlayer player)
	{
		if (MPUtil.isServerSide())
		{
			handle(player);
		}
	}

	public void handle(EntityPlayer player)
	{
		EntityRobot robot = (EntityRobot) player.worldObj.getEntityByID(entityID);
		if (robot != null)
		{
			robot.type = RobotType.values()[type];
			robot.isOff = isOff;
			robot.stayStill = stayStill;
			robot.homePosition = homePosition;
			robot.inv.inventory = inv;
			if (startTask && MPUtil.isServerSide())
			{
				robot.beginTask(player, false);
			}
		}
	}
}
