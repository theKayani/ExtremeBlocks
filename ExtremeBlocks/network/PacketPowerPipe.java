package main.extremeblocks.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import main.extremeblocks.blocks.tileentities.TileEntityPowerPipe;
import net.minecraft.entity.player.EntityPlayer;

public class PacketPowerPipe extends PacketPipe
{
	public int power;

	public PacketPowerPipe()
	{
	}

	public PacketPowerPipe(TileEntityPowerPipe pipe)
	{
		super(pipe);
		this.power = pipe.power;
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
	{
		super.encodeInto(ctx, buffer);

		buffer.writeInt(power);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
	{
		super.decodeInto(ctx, buffer);

		power = buffer.readInt();
	}

	@Override
	public void handleClientSide(EntityPlayer player)
	{
		sendPacket(player);
	}

	@Override
	public void handleServerSide(EntityPlayer player)
	{
	}
}
