package main.extremeblocks.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;

public abstract class AbstractPacket
{
	public abstract void encodeInto(ByteBuf buffer);

	public abstract void decodeInto(ByteBuf buffer);

	public abstract void handleClientSide(EntityPlayer player);

	public abstract void handleServerSide(EntityPlayer player);
}