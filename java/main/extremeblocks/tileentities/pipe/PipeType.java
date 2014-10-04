package main.extremeblocks.tileentities.pipe;

public enum PipeType
{
	TRANSPORT, POWER;

	public final String name;

	private PipeType()
	{
		name = name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase();
	}

	@Override
	public String toString()
	{
		return name;
	}
}
