package main.extremeblocks.blocks.tileentities.pipe;

public enum PipeType
{
	TRANSPORT, POWER;

	public String getName()
	{
		String val = "";

		char[] chars = name().toCharArray();

		for (int i = 0; i < chars.length; i++)
		{
			chars[i] = Character.toLowerCase(chars[i]);
		}
		chars[0] = Character.toUpperCase(chars[0]);

		for (int i = 0; i < chars.length; i++)
		{
			val += chars[i];
		}

		return val;
	}

	public String toString()
	{
		return getName();
	}
}
