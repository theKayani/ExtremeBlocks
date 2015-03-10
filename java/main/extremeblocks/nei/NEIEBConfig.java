package main.extremeblocks.nei;

import main.extremeblocks.Init;
import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;

public class NEIEBConfig implements IConfigureNEI
{
	@Override
	public void loadConfig()
	{
		API.registerRecipeHandler(new EBInfoRecipeHandler());
		API.registerUsageHandler(new EBInfoRecipeHandler());
	}

	@Override
	public String getName()
	{
		return "Extreme Blocks NEI Plugin";
	}

	@Override
	public String getVersion()
	{
		return Init.VERSION;
	}
}
