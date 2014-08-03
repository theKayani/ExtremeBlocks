package main.extremeblocks.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import java.util.List;
import main.extremeblocks.entities.EntityGrenade;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;

public class PacketGrenade extends AbstractPacket
{
	public float power;
	public double x, y, z;

	public PacketGrenade() {}

	public PacketGrenade(EntityGrenade grenade)
	{
		this.power = grenade.power;
		this.x = grenade.posX;
		this.y = grenade.posY;
		this.z = grenade.posZ;
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
	{
		buffer.writeFloat(power);
		buffer.writeDouble(x);
		buffer.writeDouble(y);
		buffer.writeDouble(z);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer)
	{
		this.power = buffer.readFloat();
		this.x = buffer.readDouble();
		this.y = buffer.readDouble();
		this.z = buffer.readDouble();
	}

	@Override
	public void handleClientSide(EntityPlayer player) {}

	@Override
	public void handleServerSide(EntityPlayer player)
	{
		List<EntityGrenade> list = player.worldObj.getEntitiesWithinAABB(EntityGrenade.class, AxisAlignedBB.getBoundingBox(x - 0.1D, y - 0.1D, z - 0.1D, x + 0.1D, y + 0.1D, z + 0.1D));

		for(int i = 0; i < list.size(); i++)
		{
			list.get(i).power = power;
		}
	}

}
