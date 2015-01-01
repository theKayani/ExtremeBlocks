package main.extremeblocks.worldgen;

public enum Rotation
{
	D180,
	D0,
	D90,
	D270;

	public int getDegrees()
	{
		return Integer.parseInt(name().replaceAll("D", ""));
	}
}
