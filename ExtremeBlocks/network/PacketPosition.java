package main.extremeblocks.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;

public abstract class PacketPosition extends AbstractPacket
{
	public int xPos, yPos, zPos;
	
	public PacketPosition() {}
	
	public PacketPosition(int xPos, int yPos, int zPos)
	{
		this.xPos = xPos;
		this.yPos = yPos;
		this.zPos = zPos;
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
	{
		buffer.writeInt(xPos);
		buffer.writeInt(yPos);
		buffer.writeInt(zPos);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
	{
		xPos = buffer.readInt();
		yPos = buffer.readInt();
		zPos = buffer.readInt();
	}
}
