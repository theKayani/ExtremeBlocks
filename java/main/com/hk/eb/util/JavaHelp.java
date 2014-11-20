package main.com.hk.eb.util;

import java.util.ArrayList;
import java.util.HashMap;
import org.apache.commons.lang3.ArrayUtils;

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
		Object[] tmp = new Object[obj.length + amountToGrow];
		for (int i = 0; i < obj.length; i++)
		{
			tmp[i] = obj[i];
		}
		return (T[]) tmp;
	}

	public static void split(Object[] source, Object[] part2)
	{
		Object[] part1 = new Object[part2.length];
		System.arraycopy(source, 0, part1, 0, part1.length);
		System.arraycopy(source, part1.length, part2, 0, part2.length);
		for (Object element : part2)
		{
			source = ArrayUtils.remove(source, source.length - 1);
		}
	}

	public static <T> T[] growArrayByAt(T[] obj, int indexToGrowAt, int amountToGrow)
	{
		Object[] tmp = new Object[obj.length + amountToGrow];
		for (int i = indexToGrowAt + 1; i < obj.length + amountToGrow; i++)
		{
			tmp[i] = obj[i];
		}
		return (T[]) tmp;
	}

	public static <T> T[] growArrayAt(T[] obj, int indexToGrowAt)
	{
		return growArrayByAt(obj, indexToGrowAt, 1);
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
		if (objs != null && objs.length > 0) return objs[Rand.nextInt(objs.length - 1)];
		return null;
	}

	public static boolean isVowel(char ch)
	{
		return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' || ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U';
	}

	public static boolean startsWithVowel(String string)
	{
		return string.length() > 0 && isVowel(string.charAt(0));
	}

	public static <T> String toString(T[] objects, StringExtractor<T> e)
	{
		String s = objects.getClass().getSimpleName() + "(";
		for (T obj : objects)
		{
			s += e.toString(obj) + ", ";
		}
		s = s.endsWith(", ") ? s.substring(0, s.lastIndexOf(", ")) : s;
		s += ")";
		return s;
	}

	public static interface StringExtractor<T>
	{
		public String toString(T obj);
	}
}
