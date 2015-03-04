package main.extremeblocks.util;

import java.util.ArrayList;
import java.util.List;

public class SimpleTimer
{
	private long startTime;
	private List<Long> laps;

	public SimpleTimer()
	{
		laps = new ArrayList<Long>();
		startTime = -1L;
	}

	public void start()
	{
		startTime = System.currentTimeMillis();
	}

	public void lap()
	{
		if (!throwError())
		{
			long i = System.currentTimeMillis() - startTime;
			laps.add(i);
		}
	}

	public long getLap(int index)
	{
		return laps.size() > 0 ? laps.get(index) : -1L;
	}

	public long getLastLap()
	{
		return laps.size() > 0 ? laps.get(laps.size() - 1) : -1L;
	}

	public long elapsed()
	{
		if (!throwError()) return System.currentTimeMillis() - startTime;
		return -1L;
	}

	public float elapsedInSeconds()
	{
		float f = elapsed();
		return f / 1000.0F;
	}

	public void reset()
	{
		if (!throwError())
		{
			laps.clear();
			startTime = -1L;
		}
	}

	public void restart()
	{
		reset();
		start();
	}

	public boolean hasStarted()
	{
		return startTime != -1L;
	}

	private boolean throwError()
	{
		if (startTime == -1L) throw new IllegalArgumentException("Must start beforehand!");
		return false;
	}
}
