package main.extremeblocks.util;

import net.minecraftforge.common.util.ForgeDirection;

public interface IPower
{
	public interface IPowerReceiver
	{
		public void receivePower(ForgeDirection sideReceived, int power);
	}

	public interface IPowerEmitter
	{
		public int getPower(ForgeDirection sideSent);
	}
}
