package main.extremeblocks.network.packets;

import io.netty.buffer.ByteBuf;
import main.extremeblocks.misc.PowerMap;
import main.extremeblocks.network.AbstractPacket;
import main.extremeblocks.tileentities.TileEntityWire;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.util.ForgeDirection;

public class PacketPipe extends AbstractPacket
{
	private int xCoord;
	private int yCoord;
	private int zCoord;
	private PowerMap sideSent;
	private PowerMap sideReceived;

	public PacketPipe()
	{
	}

	public PacketPipe(TileEntityWire pipe)
	{
		xCoord = pipe.xCoord;
		yCoord = pipe.yCoord;
		zCoord = pipe.zCoord;
		sideSent = pipe.sent;
		sideReceived = pipe.received;
	}

	@Override
	public void encodeInto(ByteBuf b)
	{
		b.writeInt(xCoord);
		b.writeInt(yCoord);
		b.writeInt(zCoord);
		for (int i = 0; i < ForgeDirection.VALID_DIRECTIONS.length; i++)
		{
			b.writeBoolean(sideSent.get(ForgeDirection.VALID_DIRECTIONS[i]));
			b.writeBoolean(sideReceived.get(ForgeDirection.VALID_DIRECTIONS[i]));
		}
	}

	@Override
	public void decodeInto(ByteBuf b)
	{
		xCoord = b.readInt();
		yCoord = b.readInt();
		zCoord = b.readInt();
		sideSent = new PowerMap("Sent");
		sideReceived = new PowerMap("Received");
		for (int i = 0; i < ForgeDirection.VALID_DIRECTIONS.length; i++)
		{
			sideSent.put(ForgeDirection.VALID_DIRECTIONS[i], b.readBoolean());
			sideReceived.put(ForgeDirection.VALID_DIRECTIONS[i], b.readBoolean());
		}

	}

	@Override
	public void handleClientSide(EntityPlayer player)
	{

	}

	@Override
	public void handleServerSide(EntityPlayer player)
	{

	}
}
