package main.extremeblocks.items;

import main.com.hk.eb.util.ItemCustom;
import main.extremeblocks.Init;

public class ItemFurnaceUpgrade extends ItemCustom
{
	public ItemFurnaceUpgrade(FurnaceUpgrade upgrade, int stage)
	{
		super(upgrade.name, Init.tab_mainItems);
		maxStackSize = 16;
		setInfo("Very expensive alternative to saving space and speeding up your Cookers!");
		setShowRecipe();
	}

	public static enum FurnaceUpgrade
	{
		SPEED;

		public final String name;

		private FurnaceUpgrade()
		{
			name = name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase() + " Upgrade";
		}
	}
}
