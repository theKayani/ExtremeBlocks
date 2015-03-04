package main.extremeblocks.worldgen.catacombs;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import main.com.hk.eb.util.JavaHelp;

public class MazeGenerator
{
	private final int x;
	private final int y;
	private final List<String> map;
	private final StringMap strMap;
	private final int[][] maze;
	private final Random rand;

	public MazeGenerator(Random rand, int x, int y)
	{
		strMap = new StringMap(x * 4 + 1, y * 2 + 1);
		this.rand = rand;
		this.x = x;
		this.y = y;
		maze = new int[x][y];
		map = JavaHelp.newArrayList();
		generateMaze(0, 0);
		display();
	}

	public void display()
	{
		for (int i = 0; i < y; i++)
		{
			String s = "";
			for (int j = 0; j < x; j++)
			{
				s += (maze[j][i] & 1) == 0 ? "+--" : "+  ";
			}
			s += "+";
			map.add(s);
			s = "";
			for (int j = 0; j < x; j++)
			{
				s += (maze[j][i] & 8) == 0 ? "|  " : "   ";
			}
			s += "|";
			map.add(s);
		}
		String s = "";
		for (int j = 0; j < x; j++)
		{
			s += "+--";
		}
		s += "+";
		map.add(s);
	}

	private void generateMaze(int cx, int cy)
	{
		DIR[] dirs = DIR.values();
		Collections.shuffle(Arrays.asList(dirs), rand);
		for (DIR dir : dirs)
		{
			int nx = cx + dir.dx;
			int ny = cy + dir.dy;
			if (between(nx, x) && between(ny, y) && maze[nx][ny] == 0)
			{
				maze[cx][cy] |= dir.bit;
				maze[nx][ny] |= dir.opposite().bit;
				generateMaze(nx, ny);
			}
		}
	}

	private static boolean between(int v, int upper)
	{
		return v >= 0 && v < upper;
	}

	public String[] getMap()
	{
		for (int i = 0; i < map.size(); i++)
		{
			for (int j = 0; j < map.get(i).toCharArray().length; j++)
			{
				strMap.putAt(map.get(i).toCharArray()[j] + "", j, i);
			}
		}
		for (int i = 0; i < strMap.y; i++)
		{
			for (int j = 0; j < strMap.x; j++)
			{
				strMap.putAt(replaceString(strMap.getAt(j, i), strMap.getAt(j, i + 1), strMap.getAt(j, i - 1), strMap.getAt(j - 1, i), strMap.getAt(j + 1, i)) + "", j, i);
			}
		}
		return strMap.getMap();
	}

	private String replaceString(String s, String top, String bottom, String left, String right)
	{
		if (s.equals("+"))
		{
			if (createRoom(top, bottom, left, right)) return CatacombRoom.randomRoom();
			if (top.equals("|"))
			{
				if (right.equals("-")) return "v";
				else if (left.equals("-")) return "<";
			}
			else if (bottom.equals("|"))
			{
				if (right.equals("-")) return ">";
				else if (left.equals("-")) return "^";
			}
			if (top.equals("|") && bottom.equals("|"))
			{
				if (isEmpty(left) && isEmpty(right)) return "|";
			}
			if (left.equals("-") && right.equals("-"))
			{
				if (isEmpty(top) && isEmpty(bottom)) return "-";
			}
			return "#";
		}
		return s;
	}

	private boolean createRoom(String a, String b, String c, String d)
	{
		if (isEmpty(a) && isEmpty(b) && isEmpty(c) && !isEmpty(d)) return true;
		if (isEmpty(a) && isEmpty(b) && !isEmpty(c) && isEmpty(d)) return true;
		if (isEmpty(a) && !isEmpty(b) && isEmpty(c) && isEmpty(d)) return true;
		if (!isEmpty(a) && isEmpty(b) && isEmpty(c) && isEmpty(d)) return true;
		return false;
	}

	private boolean isEmpty(String s)
	{
		return s.trim().isEmpty();
	}

	private enum DIR
	{
		N(1, 0, -1),
		S(2, 0, 1),
		E(4, 1, 0),
		W(8, -1, 0);
		private final int bit;
		private final int dx;
		private final int dy;

		private DIR(int bit, int dx, int dy)
		{
			this.bit = bit;
			this.dx = dx;
			this.dy = dy;
		}

		public DIR opposite()
		{
			switch (this)
			{
				case N:
					return S;
				case S:
					return N;
				case E:
					return W;
				case W:
					return E;
			}
			return null;
		}
	};
}