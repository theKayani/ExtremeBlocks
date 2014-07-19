package main.extremeblocks.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import main.extremeblocks.blocks.tileentities.TileEntityPowerPipe;
import main.extremeblocks.blocks.tileentities.TileEntityTransportPipe;
import main.extremeblocks.blocks.tileentities.pipe.TileEntityAbstractPipe;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class PacketPipe<E extends TileEntityAbstractPipe> extends PacketPosition
{
	public boolean hasSource, hasDir;
	public int dirCount, sourceCount;
	public ForgeDirection sideReceived, sideSent;

	public PacketPipe()
	{}

	public PacketPipe(TileEntityPowerPipe pipe)
	{
		super(pipe.xCoord, pipe.yCoord, pipe.zCoord);
		this.hasDir = pipe.hasDir;
		this.hasSource = pipe.hasSource;
		this.dirCount = (byte) pipe.dirCount;
		this.sourceCount = (byte) pipe.sourceCount;
		this.sideReceived = ForgeDirection.values()[pipe.sideReceived.ordinal()];
		this.sideSent = ForgeDirection.values()[pipe.sideSent.ordinal()];
	}

	public PacketPipe(TileEntityTransportPipe pipe)
	{
		super(pipe.xCoord, pipe.yCoord, pipe.zCoord);
		this.hasDir = pipe.hasDir;
		this.hasSource = pipe.hasSource;
		this.dirCount = (byte) pipe.dirCount;
		this.sourceCount = (byte) pipe.sourceCount;
		this.sideReceived = ForgeDirection.values()[pipe.sideReceived.ordinal()];
		this.sideSent = ForgeDirection.values()[pipe.sideSent.ordinal()];
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
	{
		super.encodeInto(ctx, buffer);
		
		buffer.writeBoolean(hasDir);
		buffer.writeBoolean(hasSource);

		buffer.writeByte(dirCount);
		buffer.writeByte(sourceCount);

		buffer.writeByte(sideReceived.ordinal());
		buffer.writeByte(sideSent.ordinal());
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
	{
		super.decodeInto(ctx, buffer);
		
		this.hasDir = buffer.readBoolean();
		this.hasSource = buffer.readBoolean();

		this.dirCount = buffer.readByte();
		this.sourceCount = buffer.readByte();

		this.sideReceived = ForgeDirection.values()[buffer.readByte()];
		this.sideSent = ForgeDirection.values()[buffer.readByte()];
	}

	public boolean sendPacket(EntityPlayer player)
	{
		TileEntity te = player.worldObj.getTileEntity(xPos, yPos, zPos);

		if (te != null && te instanceof TileEntityAbstractPipe)
		{
			((E) te).receivedPacket(this);
			return true;
		}
		return false;
	}
}
