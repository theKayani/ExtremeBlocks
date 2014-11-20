package main.extremeblocks.misc;

import net.minecraft.item.ItemStack;

public interface IBattery
{
	public boolean receivePower(ItemStack stack, int power);

	public boolean removePower(ItemStack stack, int power);

	public int getMaxHeldPower();
}
