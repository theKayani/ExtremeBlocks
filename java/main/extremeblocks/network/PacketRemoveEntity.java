package main.extremeblocks.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class PacketRemoveEntity extends AbstractPacket
{
	public int entityID;
	public boolean dropItems;

	public PacketRemoveEntity()
	{
	}

	public PacketRemoveEntity(Entity entity, boolean dropItems)
	{
		this.entityID = entity.getEntityId();
		this.dropItems = dropItems;
	}

	@Override
	public void encodeInto(ByteBuf buffer)
	{
		buffer.writeInt(entityID);
		buffer.writeBoolean(dropItems);
	}

	@Override
	public void decodeInto(ByteBuf buffer)
	{
		this.entityID = buffer.readInt();
		this.dropItems = buffer.readBoolean();
	}

	@Override
	public void handleClientSide(EntityPlayer player)
	{
	}

	@Override
	public void handleServerSide(EntityPlayer player)
	{
		Entity entity = player.worldObj.getEntityByID(entityID);
		if (entity instanceof EntityLivingBase && dropItems)
		{
			((EntityLivingBase) entity).setDead();
		}
		player.worldObj.removeEntity(entity);
	}
}
