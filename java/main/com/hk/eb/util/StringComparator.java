package main.com.hk.eb.util;

import java.lang.reflect.Method;
import java.util.Comparator;

public class StringComparator implements Comparator<Object>
{
	private final Class<?>[] classArgs;
	private final Object[] args;
	private final String methodName;
	private boolean hasArgs;

	public StringComparator(String methodName)
	{
		this(methodName, null, null);
		hasArgs = false;
	}

	public StringComparator(String methodName, Class<?>[] classArgs, Object[] args)
	{
		this.methodName = methodName;
		this.args = args;
		this.classArgs = classArgs;
		hasArgs = true;
	}

	@Override
	public int compare(Object o1, Object o2)
	{
		if (o1 == o2) return 0;
		if (o1 == null) return -1;
		try
		{
			Class<?> clazz = o1.getClass();
			Method m = clazz.getMethod(methodName, hasArgs ? classArgs : new Class<?>[0]);
			String str1 = (String) m.invoke(o1, hasArgs ? args : new Object[0]);
			String str2 = (String) m.invoke(o2, hasArgs ? args : new Object[0]);
			return str1 == str2 ? 0 : str1 == null ? -1 : str1.compareTo(str2);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}
}
