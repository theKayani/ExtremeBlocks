package main.com.hk.eb.util;

public class MathHelp
{
	public static int clamp(int number, int min, int max)
	{
		return number < min ? min : number > max ? max : number;
	}

	public static int max(int number, int max)
	{
		return number > max ? number : max;
	}

	public static int min(int number, int min)
	{
		return number < min ? number : min;
	}

	public static int min(int a, int b, int c)
	{
		return min(c, min(a, b));
	}

	public static int max(int a, int b, int c)
	{
		return max(c, max(a, b));
	}

	public static boolean isOdd(int number)
	{
		switch (String.valueOf(number).charAt(String.valueOf(number).length() - 1))
		{
			case '1':
			case '3':
			case '5':
			case '7':
			case '9':
				return true;
			default:
				return false;
		}
	}

	public static boolean isEven(int number)
	{
		return !isOdd(number);
	}
}
