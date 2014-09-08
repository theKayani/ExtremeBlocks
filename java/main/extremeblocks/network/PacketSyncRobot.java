package main.extremeblocks.network;

import io.netty.buffer.ByteBuf;
import main.com.hk.testing.util.MPUtil;
import main.extremeblocks.entities.mobs.EntityRobot;
import main.extremeblocks.entities.mobs.EntityRobot.RobotType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.network.ByteBufUtils;

public class PacketSyncRobot extends AbstractPacket
{
	public int entityID, type, size, ownerID;
	public boolean stayStill, isOff, startTask;
	public int[] homePosition;
	public ItemStack[] inv;

	public PacketSyncRobot()
	{
	}

	public PacketSyncRobot(EntityRobot robot, boolean startTask)
	{
		this.entityID = robot.getEntityId();
		this.type = robot.type.ordinal();
		this.stayStill = robot.stayStill;
		this.isOff = robot.isOff;
		this.homePosition = robot.homePosition;
		this.startTask = startTask;
		this.size = robot.inv.getSizeInventory();
		this.inv = robot.inv.inventory;
	}

	@Override
	public void encodeInto(ByteBuf buffer)
	{
		buffer.writeInt(entityID);
		buffer.writeInt(type);
		buffer.writeBoolean(stayStill);
		buffer.writeBoolean(isOff);
		buffer.writeBoolean(startTask);
		buffer.writeInt(homePosition[0]);
		buffer.writeInt(homePosition[1]);
		buffer.writeInt(homePosition[2]);
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
		this.entityID = buffer.readInt();
		this.type = buffer.readInt();
		this.stayStill = buffer.readBoolean();
		this.isOff = buffer.readBoolean();
		this.startTask = buffer.readBoolean();
		this.homePosition = new int[] { buffer.readInt(), buffer.readInt(), buffer.readInt() };
		this.ownerID = buffer.readInt();
		this.size = buffer.readInt();
		this.inv = new ItemStack[size];
		for (int i = 0; i < size; i++)
		{
			inv[i] = ByteBufUtils.readItemStack(buffer);
		}
	}

	@Override
	public void handleClientSide(EntityPlayer player)
	{
		if (MPUtil.isClientSide()) handle(player);
	}

	@Override
	public void handleServerSide(EntityPlayer player)
	{
		if (MPUtil.isServerSide()) handle(player);
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
			if (this.startTask && MPUtil.isServerSide())
			{
				robot.beginTask(player, false);
			}
		}
	}
}
