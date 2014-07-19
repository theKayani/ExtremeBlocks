package main.extremeblocks.blocks.tileentities;

import static main.extremeblocks.blocks.tileentities.pipe.TransferType.IN;
import static main.extremeblocks.blocks.tileentities.pipe.TransferType.OUT;
import static net.minecraftforge.common.util.ForgeDirection.DOWN;
import static net.minecraftforge.common.util.ForgeDirection.EAST;
import static net.minecraftforge.common.util.ForgeDirection.NORTH;
import static net.minecraftforge.common.util.ForgeDirection.SOUTH;
import static net.minecraftforge.common.util.ForgeDirection.UP;
import static net.minecraftforge.common.util.ForgeDirection.WEST;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import main.com.hk.testing.util.MPUtil;
import main.extremeblocks.blocks.abstractblocks.BlockStorage;
import main.extremeblocks.blocks.tileentities.pipe.TileEntityAbstractPipe;
import main.extremeblocks.blocks.tileentities.pipe.TransportPipeLogic;
import main.extremeblocks.network.PacketTransportPipe;
import main.extremeblocks.util.IConnector;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;

public class TileEntityTransportPipe extends TileEntityAbstractPipe<TransportPipeLogic, PacketTransportPipe> implements IConnector
{
	public ArrayList<ItemStack> items = new ArrayList<ItemStack>();
	public int counter;

	@Override
	public boolean canBePlacedAt(World world, int x, int y, int z)
	{
		TileEntity te = world.getTileEntity(x, y, z);

		return te instanceof TileEntityChest || te instanceof IInventory || te instanceof TileEntityTransportPipe;
	}

	@Override
	public void destroyedBlock(int x, int y, int z, int chance)
	{
		for (int i = 0; i < items.size(); i++)
		{
			if (items.get(i) == null) continue;

			double d0 = worldObj.rand.nextFloat() * 0.7F + 0.15D;
			double d1 = worldObj.rand.nextFloat() * 0.7F + 0.15D;
			double d2 = worldObj.rand.nextFloat() * 0.7F + 0.15D;
			worldObj.spawnEntityInWorld(new EntityItem(worldObj, d0 + x, d1 + y, d2 + z, items.get(i)));
		}
	}

	@Override
	public TransportPipeLogic[] getLogics()
	{
		return new TransportPipeLogic[] { new TransportPipeLogic(this, EAST), new TransportPipeLogic(this, UP), new TransportPipeLogic(this, SOUTH), new TransportPipeLogic(this, WEST), new TransportPipeLogic(this, DOWN), new TransportPipeLogic(this, NORTH) };
	}

	@Override
	public PacketTransportPipe getPacket()
	{
		return new PacketTransportPipe(this);
	}

	@Override
	public void nearEmitter(TransportPipeLogic side)
	{
		side.setType(IN);
		sideReceived = side.getSide();
		side.getFromChest();
	}

	@Override
	public void nearInPipe(TransportPipeLogic side)
	{
		side.getTransferer().sideSent = side.getSide().getOpposite();
		side.setType(IN);
		sideReceived = side.getSide();
	}

	@Override
	public void nearOutPipe(TransportPipeLogic side)
	{
		side.setType(OUT);
		sideSent = side.getSide();
		side.getTransferer().sideReceived = side.getSide().getOpposite();

		if (items.size() > 0 && counter++ >= 20)
		{
			side.transferItemsToNextPipe();
			counter = 0;
		}
	}

	@Override
	public void nearReceiver(TransportPipeLogic side)
	{
		side.setType(OUT);
		sideSent = side.getSide();
		side.sendToStorage();
	}

	@Override
	public void nearUnknownPipe(TransportPipeLogic side)
	{
		sideSent = side.getSide();
		side.getTransferer().sideReceived = sideSent.getOpposite();
	}

	public boolean onClickedOn(EntityPlayer player)
	{
		if (MPUtil.isServerSide())
		{
			MPUtil.sendMessage("Size: " + items.size(), player);
		}
		return false;
	}

	@Override
	public void receivedPacket(PacketTransportPipe packet)
	{
		sourceCount = packet.sourceCount;
		dirCount = packet.dirCount;
		hasSource = packet.hasSource;
		hasDir = packet.hasDir;
		sideReceived = packet.sideReceived;
		sideSent = packet.sideSent;

		items = packet.items;
	}
	
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		NBTTagList nbttaglist = new NBTTagList();
		ItemStack[] stacks = items.toArray(new ItemStack[0]);
		
		nbt.setInteger("Size", items.size());
	
		for (int i = 0; i < stacks.length; ++i)
		{
			if (stacks[i] != null)
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				stacks[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		nbt.setTag("Items", nbttaglist);
	}

	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		int size = nbt.getInteger("Size");
		ItemStack[] stacks = new ItemStack[size];
		NBTTagList nbttaglist = (NBTTagList) nbt.getTag("Items");
		
		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound lol = nbttaglist.getCompoundTagAt(i);
			int j = lol.getByte("Slot") & 255;
			if (j >= 0 && j < stacks.length) stacks[j] = ItemStack.loadItemStackFromNBT(lol);
		}
		
		items.addAll(Arrays.asList(stacks));
	}

	public void update()
	{
		Iterator<ItemStack> iterator = items.iterator();

		while (iterator.hasNext())
		{
			ItemStack item = iterator.next();

			if (item == null || item.stackSize == 0)
			{
				iterator.remove();
			}
		}
	}
}