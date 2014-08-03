package main.com.hk.testing.util;

public class Element
{
	private final String line;

	public Element(String line)
	{
		this.line = line;
	}

	public boolean isBoolean()
	{
		try
		{
			Boolean.parseBoolean(line);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public boolean isInt()
	{
		try
		{
			Integer.parseInt(line);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public boolean isDouble()
	{
		try
		{
			Double.parseDouble(line);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public boolean isByte()
	{
		try
		{
			Byte.parseByte(line);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}

	public boolean isString()
	{
		return !isBoolean() && !isInt() && !isDouble() && !isByte();
	}

	public int getInt()
	{
		return isInt() ? Integer.parseInt(line) : 0;
	}

	public boolean getBoolean()
	{
		return isBoolean() ? Boolean.parseBoolean(line) : false;
	}

	public double getDouble()
	{
		return isDouble() ? Double.parseDouble(line) : 0.0D;
	}

	public byte getByte()
	{
		return isByte() ? Byte.parseByte(line) : 0;
	}

	public String getString()
	{
		return line;
	}
}