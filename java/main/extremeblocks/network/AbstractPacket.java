package main.extremeblocks.network;

import io.netty.buffer.ByteBuf;
import main.com.hk.eb.util.MPUtil;
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
		MPUtil.sendToServer(this);
	}

	public void sendTo(EntityPlayerMP player)
	{
		MPUtil.sendTo(this, player);
	}

	public void sendToDimension(int dimID)
	{
		MPUtil.sendToDimension(this, dimID);
	}

	public void sendToAllAround(TargetPoint point)
	{
		MPUtil.sendToAllAround(this, point);
	}
}