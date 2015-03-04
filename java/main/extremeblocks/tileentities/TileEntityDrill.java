package main.extremeblocks.tileentities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import main.com.hk.eb.util.JavaHelp;
import main.com.hk.eb.util.MPUtil;
import main.com.hk.eb.util.StackHelper;
import main.extremeblocks.Init;
import main.extremeblocks.blocks.BlockDrill;
import main.extremeblocks.util.IPlayerMessage;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityDrill extends TileEntity implements IPlayerMessage
{
	private int counter;
	private final List<ItemStack> stack;

	public List<ItemStack> getStack()
	{
		return stack;
	}

	public TileEntityDrill()
	{
		stack = JavaHelp.newArrayList();
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		int size = nbt.getInteger("Stacks Size");
		stack.addAll(Arrays.asList(StackHelper.loadFromNBT(size, nbt)));
		counter = nbt.getInteger("Counter");
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setInteger("Stacks Size", stack.size());
		StackHelper.saveToNBT(stack.toArray(new ItemStack[0]));
		nbt.setInteger("Counter", counter);
	}

	@Override
	public void updateEntity()
	{
		if (counter++ >= 100)
		{
			stack.addAll(drillBelow());
			counter = 0;
		}
		if (worldObj.getTileEntity(xCoord, yCoord - 1, zCoord) instanceof TileEntityDrill)
		{
			((TileEntityDrill) worldObj.getTileEntity(xCoord, yCoord - 1, zCoord)).stack.addAll(stack);
			stack.clear();
			worldObj.setBlock(xCoord, yCoord, zCoord, Init.drill_pole);
		}
	}

	public List<ItemStack> drillBelow()
	{
		if (canDrillBelow())
		{
			ArrayList<ItemStack> stack = null;
			Block block = belowBlock();
			stack = block.getDrops(worldObj, xCoord, yCoord - 1, zCoord, worldObj.getBlockMetadata(xCoord, yCoord - 1, zCoord), 1);
			worldObj.setBlock(xCoord, yCoord - 1, zCoord, Init.drill);
			return stack == null ? new ArrayList<ItemStack>() : stack;
		}
		return JavaHelp.newArrayList();
	}

	public boolean canDrillBelow()
	{
		return !isAirBlockBelow() && !isDrillBlock() && belowBlock().getBlockHardness(worldObj, xCoord, yCoord - 1, zCoord) > 0;
	}

	public boolean isDrillBlock()
	{
		return belowBlock() instanceof BlockDrill;
	}

	public boolean isAirBlockBelow()
	{
		return worldObj.isAirBlock(xCoord, yCoord - 1, zCoord);
	}

	public Block belowBlock()
	{
		return worldObj.getBlock(xCoord, yCoord - 1, zCoord);
	}

	@Override
	public boolean onClickedOn(EntityPlayer player)
	{
		if (stack.size() > 0)
		{
			MPUtil.sendMessage("You have collected " + stack.size() + " blocks!", player);
			MPUtil.dropItemsAsEntities(worldObj, xCoord, yCoord, zCoord, false, stack.toArray(new ItemStack[0]));
			stack.clear();
		}
		else
		{
			MPUtil.sendMessage("You have not collected any blocks! Start Drilling!", player);
		}
		return false;
	}
}
