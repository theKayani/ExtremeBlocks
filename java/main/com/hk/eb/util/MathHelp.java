package main.com.hk.eb.util;

public class MathHelp
{
	public static int clamp(int number, int min, int max)
	{
		if (number > max) return max;
		if (number < min) return min;
		else return number;
	}

	public static int max(int number, int max)
	{
		if (number > max) return max;
		else return number;
	}

	public static int min(int number, int min)
	{
		if (number < min) return min;
		else return number;
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
