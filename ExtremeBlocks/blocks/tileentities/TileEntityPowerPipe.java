package main.extremeblocks.blocks.tileentities;

import static main.extremeblocks.blocks.tileentities.pipe.TransferType.IN;
import static main.extremeblocks.blocks.tileentities.pipe.TransferType.OUT;
import static net.minecraftforge.common.util.ForgeDirection.DOWN;
import static net.minecraftforge.common.util.ForgeDirection.EAST;
import static net.minecraftforge.common.util.ForgeDirection.NORTH;
import static net.minecraftforge.common.util.ForgeDirection.SOUTH;
import static net.minecraftforge.common.util.ForgeDirection.UP;
import static net.minecraftforge.common.util.ForgeDirection.WEST;
import main.com.hk.testing.util.MPUtil;
import main.extremeblocks.blocks.tileentities.pipe.PowerPipeLogic;
import main.extremeblocks.blocks.tileentities.pipe.TileEntityAbstractPipe;
import main.extremeblocks.network.PacketPowerPipe;
import main.extremeblocks.util.IPower.IPowerEmitter;
import main.extremeblocks.util.IPower.IPowerReceiver;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityPowerPipe extends TileEntityAbstractPipe<PowerPipeLogic, PacketPowerPipe>
{
	public int power;

	@Override
	public boolean canBePlacedAt(World world, int x, int y, int z)
	{
		TileEntity te = world.getTileEntity(x, y, z);

		return te instanceof TileEntityPowerPipe || te instanceof IPowerEmitter || te instanceof IPowerReceiver;
	}

	public boolean isSideConnected(ForgeDirection side)
	{
		for (int i = 0; i < sides.length; i++)
		{
			if (sides[i].getSide() == side)
			{
				return sides[i].isTransferer();
			}
		}
		return false;
	}

	@Override
	public void destroyedBlock(int x, int y, int z, int chance)
	{
	}

	@Override
	public PowerPipeLogic[] getLogics()
	{
		return new PowerPipeLogic[] {
		new PowerPipeLogic(this, EAST), new PowerPipeLogic(this, UP), new PowerPipeLogic(this, SOUTH), new PowerPipeLogic(this, WEST), new PowerPipeLogic(this, DOWN), new PowerPipeLogic(this, NORTH)
		};
	}

	@Override
	public PacketPowerPipe getPacket()
	{
		return new PacketPowerPipe(this);
	}

	@Override
	public void nearEmitter(PowerPipeLogic side)
	{
		side.setType(IN);
		sideReceived = side.getSide();
		side.getFromEmitter();
	}

	@Override
	public void nearInPipe(PowerPipeLogic side)
	{
		side.getTransferer().sideSent = side.getSide().getOpposite();
		side.setType(IN);
		sideReceived = side.getSide();
	}

	@Override
	public void nearOutPipe(PowerPipeLogic side)
	{
		side.setType(OUT);
		sideSent = side.getSide();
		side.getTransferer().sideReceived = side.getSide().getOpposite();
		side.sendPowerToNextPipe();
	}

	@Override
	public void nearReceiver(PowerPipeLogic side)
	{
		side.setType(OUT);
		sideSent = side.getSide();
		side.sendToPowerReceiver();
	}

	@Override
	public void nearUnknownPipe(PowerPipeLogic side)
	{
		sideSent = side.getSide();
		side.getTransferer().sideReceived = sideSent.getOpposite();
	}

	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);

		nbt.setInteger("Power", power);
	}

	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);

		power = nbt.getInteger("Power");
	}

	@Override
	public boolean onClickedOn(EntityPlayer player)
	{
		MPUtil.sendMessage("Power: " + power, player);
		return false;
	}

	@Override
	public void receivedPacket(PacketPowerPipe packet)
	{
		sourceCount = packet.sourceCount;
		dirCount = packet.dirCount;
		hasSource = packet.hasSource;
		hasDir = packet.hasDir;
		sideReceived = packet.sideReceived;
		sideSent = packet.sideSent;

		power = packet.power;
	}

	@Override
	public void update()
	{
	}
}
