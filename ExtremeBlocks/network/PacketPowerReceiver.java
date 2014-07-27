package main.extremeblocks.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import main.extremeblocks.blocks.tileentities.TileEntityPowerReceiver;
import main.extremeblocks.blocks.tileentities.builder.Builder;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class PacketPowerReceiver extends PacketPosition
{
	public boolean isBuilding, done;
	public int power, stage, counter, buildingMode;
	public int baseX, baseY, baseZ;

	public PacketPowerReceiver()
	{
	}

	public PacketPowerReceiver(TileEntityPowerReceiver r)
	{
		super(r.xCoord, r.yCoord, r.zCoord);

		isBuilding = r.isBuilding;
		buildingMode = r.buildingMode;
		power = r.power;
		stage = r.stage;
		counter = r.counter;
		baseX = r.baseX;
		baseY = r.baseY;
		baseZ = r.baseZ;
		done = r.done;
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
	{
		super.encodeInto(ctx, buffer);

		buffer.writeBoolean(isBuilding);
		buffer.writeInt(buildingMode);
		buffer.writeInt(power);
		buffer.writeInt(stage);
		buffer.writeInt(counter);
		buffer.writeInt(baseX);
		buffer.writeInt(baseY);
		buffer.writeInt(baseZ);
		buffer.writeBoolean(done);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
	{
		super.decodeInto(ctx, buffer);

		isBuilding = buffer.readBoolean();
		buildingMode = buffer.readInt();
		power = buffer.readInt();
		stage = buffer.readInt();
		counter = buffer.readInt();
		baseX = buffer.readInt();
		baseY = buffer.readInt();
		baseZ = buffer.readInt();
		done = buffer.readBoolean();
	}

	@Override
	public void handleClientSide(EntityPlayer player)
	{
		send(player);
	}

	@Override
	public void handleServerSide(EntityPlayer player)
	{
		send(player);
	}

	private void send(EntityPlayer player)
	{
		TileEntity te = player.worldObj.getTileEntity(xPos, yPos, zPos);

		if (te instanceof TileEntityPowerReceiver)
		{
			((TileEntityPowerReceiver) te).receivePacket(this);
		}
	}
}
