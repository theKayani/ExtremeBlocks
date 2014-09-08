package main.com.hk.testing.util;

import java.util.ArrayList;
import java.util.HashMap;

public class JavaHelp
{
	private JavaHelp()
	{
	}

	public static <T> T[] growArrayByOne(T[] obj)
	{
		return growArray(obj, 1);
	}

	public static <T> T[] growArray(T[] obj, int amountToGrow)
	{
		assert amountToGrow > 0 : "Amount to Grow must be positive";
		Object[] tmp = new Object[obj.length + amountToGrow];
		for (int i = 0; i < obj.length; i++)
		{
			tmp[i] = obj[i];
		}
		return (T[]) tmp;
	}

	public static <T> ArrayList<T> newArrayList()
	{
		return new ArrayList<T>();
	}

	public static <T, V> HashMap<T, V> newHashMap()
	{
		return new HashMap<T, V>();
	}

	public static <T> T getRandomElementFrom(T... objs)
	{
		if (objs.length > 0)
		{
			return objs[Rand.nextInt(objs.length - 1)];
		}
		return null;
	}
}
