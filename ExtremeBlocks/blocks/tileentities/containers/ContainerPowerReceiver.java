package main.extremeblocks.blocks.tileentities.containers;

import main.extremeblocks.blocks.tileentities.TileEntityPowerReceiver;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class ContainerPowerReceiver extends Container
{
	private TileEntityPowerReceiver receiver;
	
	public ContainerPowerReceiver(TileEntityPowerReceiver receiver)
	{
		this.receiver = receiver;
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer var1)
	{
		return receiver.canInteractWith(var1);
	}
}
