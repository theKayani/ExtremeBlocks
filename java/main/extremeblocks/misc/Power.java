package main.extremeblocks.misc;

import net.minecraftforge.common.util.ForgeDirection;

public class Power
{
	public interface IPowerReceiver
	{
		public void receivePower(ForgeDirection sideReceived, float power);
	}

	public interface IPowerEmitter
	{
		public float getPower();

		public boolean canSendPowerThrough(ForgeDirection sideSent);

		public void takenPowerFrom(float power);
	}
}
