package main.com.hk.testing.util;

public class Rand
{
	public static boolean nextBoolean()
	{
		return Math.random() > 0.5D;
	}

	public static short nextShort()
	{
		return (short) (Math.random() * Short.MAX_VALUE);
	}

	public static boolean isPercent(int percent)
	{
		if (percent > nextInt(100))
		{
			return true;
		}
		return false;
	}

	public static double nextDFG()
	{
		String cut = "" + Math.random();
		cut = cut.substring(0, 6);

		return Double.parseDouble(cut);
	}

	public static int nextInt()
	{
		return (int) (Math.random() * Integer.MAX_VALUE);
	}

	public static int nextInt(int n)
	{
		if (n <= 0)
		{
			IllegalArgumentException e = new IllegalArgumentException("n Must be a positive!");
			e.printStackTrace();
		}

		return (int) (Math.random() * n);
	}

	public static long nextLong()
	{
		return (long) (Math.random() * Long.MAX_VALUE);
	}
}