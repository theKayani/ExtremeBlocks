package main.com.hk.eb.util;

public interface IEnergyHolder
{
	public int getMaxEnergyStored();

	public int getEnergyStored();

	public int getMaxExtract();

	public int getMaxReceive();

	public boolean isRedstonePowered();

	public int getEnergyStoredScaled(int scale);
}
