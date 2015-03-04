package main.extremeblocks.worldgen.catacombs;

import java.util.List;
import main.com.hk.eb.util.JavaHelp;

public class StringMap
{
	public final int size, x, y;
	private final String[][] chars;

	public StringMap(int x, int y)
	{
		chars = new String[y][x];
		for (int i = 0; i < y; i++)
		{
			for (int j = 0; j < x; j++)
			{
				chars[i][j] = " ";
			}
		}
		this.x = x;
		this.y = y;
		size = x * y;
	}

	public String getAt(int x, int y)
	{
		if (inBetween(x, y)) return chars[y][x];
		return " ";
	}

	public void putAt(String c, int x, int y)
	{
		if (inBetween(x, y))
		{
			chars[y][x] = c;
		}
	}

	private boolean inBetween(int x, int y)
	{
		if (y >= 0 && y < chars.length) return x >= 0 && x < chars[y].length;
		return false;
	}

	public String[] getMap()
	{
		List<String> strings = JavaHelp.newArrayList();
		for (String[] c : chars)
		{
			String s = "";
			for (String ch : c)
			{
				s += ch;
			}
			strings.add(s);
		}
		return strings.toArray(new String[0]);
	}

	public void print()
	{
		for (String[] c : chars)
		{
			String s = "";
			for (String ch : c)
			{
				s += "[" + ch + "]";
			}
			System.out.println(s);
		}
	}
}
