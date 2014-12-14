package main.extremeblocks.network.packets;

import io.netty.buffer.ByteBuf;
import main.extremeblocks.network.AbstractPacket;

public abstract class PacketPosition extends AbstractPacket
{
	public int xPos, yPos, zPos;

	public PacketPosition()
	{
	}

	public PacketPosition(int xPos, int yPos, int zPos)
	{
		this.xPos = xPos;
		this.yPos = yPos;
		this.zPos = zPos;
	}

	@Override
	public void encodeInto(ByteBuf buffer)
	{
		buffer.writeInt(xPos);
		buffer.writeInt(yPos);
		buffer.writeInt(zPos);
	}

	@Override
	public void decodeInto(ByteBuf buffer)
	{
		xPos = buffer.readInt();
		yPos = buffer.readInt();
		zPos = buffer.readInt();
	}
}
