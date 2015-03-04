package main.com.hk.eb.util;

import java.util.List;

public interface IWailaInfo
{
	public List<String> getTips(List<String> currentList);

	public boolean shouldAddTip();
}
