package main.extremeblocks.network;

import io.netty.buffer.ByteBuf;
import main.extremeblocks.ExtremeBlocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;

public abstract class AbstractPacket
{
	/** 
	 * Write the Packet to the {@code ByteBuf}
	 */
	public abstract void encodeInto(ByteBuf buffer);

	/** 
	 * Read the Packet from the {@code ByteBuf}
	 */
	public abstract void decodeInto(ByteBuf buffer);

	/** 
	 * Called by the client when this Packet is received, and synced v.i.a. {@link #decodeInto(ByteBuf)}
	 */
	public abstract void handleClientSide(EntityPlayer player);

	/** 
	 * Called by the server when this Packet is received, and synced v.i.a. {@link #decodeInto(ByteBuf)}
	 */
	public abstract void handleServerSide(EntityPlayer player);

	public void sendToServer()
	{
		ExtremeBlocks.packetPipeline.sendToServer(this);
	}

	public void sendToAll()
	{
		ExtremeBlocks.packetPipeline.sendToAll(this);
	}

	public void sendTo(EntityPlayerMP player)
	{
		ExtremeBlocks.packetPipeline.sendTo(this, player);
	}

	public void sendToDimension(int dimID)
	{
		ExtremeBlocks.packetPipeline.sendToDimension(this, dimID);
	}

	public void sendToAllAround(TargetPoint point)
	{
		ExtremeBlocks.packetPipeline.sendToAllAround(this, point);
	}
}