package main.extremeblocks.network.packets;

import io.netty.buffer.ByteBuf;
import main.extremeblocks.network.AbstractPacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class PacketRemoveEntity extends AbstractPacket
{
	public int entityID;

	public PacketRemoveEntity()
	{
	}

	public PacketRemoveEntity(Entity entity, boolean dropItems)
	{
		entityID = entity.getEntityId();
	}

	@Override
	public void encodeInto(ByteBuf buffer)
	{
		buffer.writeInt(entityID);
	}

	@Override
	public void decodeInto(ByteBuf buffer)
	{
		entityID = buffer.readInt();
	}

	@Override
	public void handleClientSide(EntityPlayer player)
	{
	}

	@Override
	public void handleServerSide(EntityPlayer player)
	{
		Entity entity = player.worldObj.getEntityByID(entityID);
		if (entity instanceof EntityLivingBase)
		{
			((EntityLivingBase) entity).setDead();
		}
		player.worldObj.removeEntity(entity);
	}
}
