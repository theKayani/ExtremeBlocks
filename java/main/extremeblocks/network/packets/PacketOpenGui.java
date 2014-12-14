package main.extremeblocks.network.packets;

import io.netty.buffer.ByteBuf;
import main.extremeblocks.ExtremeBlocks;
import main.extremeblocks.network.AbstractPacket;
import net.minecraft.entity.player.EntityPlayer;

public class PacketOpenGui extends AbstractPacket
{
	public int guiID, x, y, z;

	public PacketOpenGui()
	{
	}

	public PacketOpenGui(int guiID, int x, int y, int z)
	{
		this.guiID = guiID;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public void encodeInto(ByteBuf buffer)
	{
		buffer.writeInt(guiID);
		buffer.writeInt(x);
		buffer.writeInt(y);
		buffer.writeInt(z);
	}

	@Override
	public void decodeInto(ByteBuf buffer)
	{
		guiID = buffer.readInt();
		x = buffer.readInt();
		y = buffer.readInt();
		z = buffer.readInt();
	}

	@Override
	public void handleClientSide(EntityPlayer player)
	{
	}

	@Override
	public void handleServerSide(EntityPlayer player)
	{
		player.openGui(ExtremeBlocks.instance, guiID, player.worldObj, x, y, z);
	}
}
