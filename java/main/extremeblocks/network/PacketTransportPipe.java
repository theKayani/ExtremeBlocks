package main.extremeblocks.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import java.util.ArrayList;
import main.extremeblocks.blocks.tileentities.TileEntityTransportPipe;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.network.ByteBufUtils;

public class PacketTransportPipe extends PacketPipe<TileEntityTransportPipe>
{
	public ArrayList<ItemStack> items;
	public int size;

	public PacketTransportPipe()
	{
		this.items = new ArrayList<ItemStack>();
	}

	public PacketTransportPipe(TileEntityTransportPipe pipe)
	{
		super(pipe);
		this.items = pipe.items;
		this.size = items.size();
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
	{
		super.encodeInto(ctx, buffer);

		buffer.writeInt(size);

		for (int i = 0; i < size; i++)
		{
			ByteBufUtils.writeItemStack(buffer, items.get(i));
		}
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
	{
		super.decodeInto(ctx, buffer);

		this.size = buffer.readInt();

		for (int i = 0; i < size; i++)
		{
			items.add(ByteBufUtils.readItemStack(buffer));
		}
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
