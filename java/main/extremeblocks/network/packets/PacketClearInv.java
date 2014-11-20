package main.extremeblocks.network.packets;

import io.netty.buffer.ByteBuf;
import main.com.hk.eb.util.StackHelper;
import main.extremeblocks.network.AbstractPacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;

public class PacketClearInv extends AbstractPacket
{
	public int x, y, z;

	public PacketClearInv()
	{
	}

	public PacketClearInv(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public void encodeInto(ByteBuf b)
	{
		b.writeInt(x);
		b.writeInt(y);
		b.writeInt(z);
	}

	@Override
	public void decodeInto(ByteBuf b)
	{
		x = b.readInt();
		y = b.readInt();
		z = b.readInt();
	}

	@Override
	public void handleClientSide(EntityPlayer player)
	{

	}

	@Override
	public void handleServerSide(EntityPlayer player)
	{
		TileEntity tile = player.worldObj.getTileEntity(x, y, z);
		if (tile instanceof IInventory)
		{
			StackHelper.clearInv((IInventory) tile);
		}
	}
}
