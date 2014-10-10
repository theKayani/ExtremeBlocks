package main.extremeblocks.network.packets;

import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import main.com.hk.eb.util.JavaHelp;
import main.extremeblocks.network.AbstractPacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.network.ByteBufUtils;

public class PacketAddToPlayer extends AbstractPacket
{
	public ItemStack[] stacks;

	public PacketAddToPlayer()
	{
	}

	public PacketAddToPlayer(ItemStack... stacks)
	{
		this.stacks = stacks;
	}

	@Override
	public void encodeInto(ByteBuf buffer)
	{
		buffer.writeInt(stacks.length);
		for (ItemStack stack : stacks)
		{
			ByteBufUtils.writeItemStack(buffer, stack);
		}
	}

	@Override
	public void decodeInto(ByteBuf buffer)
	{
		ArrayList<ItemStack> staks = JavaHelp.newArrayList();
		int size = buffer.readInt();
		for (int i = 0; i < size; i++)
		{
			staks.add(ByteBufUtils.readItemStack(buffer));
		}
		stacks = staks.toArray(new ItemStack[0]);
	}

	@Override
	public void handleClientSide(EntityPlayer player)
	{
	}

	@Override
	public void handleServerSide(EntityPlayer player)
	{
		for (ItemStack stack : stacks)
		{
			player.inventory.addItemStackToInventory(stack);
		}
	}
}
