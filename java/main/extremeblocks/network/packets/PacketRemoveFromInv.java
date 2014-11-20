package main.extremeblocks.network.packets;

import io.netty.buffer.ByteBuf;
import main.com.hk.eb.util.StackHelper;
import main.extremeblocks.network.AbstractPacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;

public class PacketRemoveFromInv extends AbstractPacket
{
	public int x, y, z;
	public Item item;

	public PacketRemoveFromInv()
	{
	}

	public PacketRemoveFromInv(int x, int y, int z, Item item)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.item = item;
	}

	@Override
	public void encodeInto(ByteBuf b)
	{
		b.writeInt(x);
		b.writeInt(y);
		b.writeInt(z);
		b.writeInt(Item.getIdFromItem(item));
	}

	@Override
	public void decodeInto(ByteBuf b)
	{
		x = b.readInt();
		y = b.readInt();
		z = b.readInt();
		item = Item.getItemById(b.readInt());
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
			StackHelper.consumeItemFrom((IInventory) tile, item);
		}
	}
}
