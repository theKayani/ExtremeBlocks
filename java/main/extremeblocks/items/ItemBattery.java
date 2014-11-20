package main.extremeblocks.items;

import java.util.List;
import main.com.hk.eb.util.ItemCustom;
import main.extremeblocks.Init;
import main.extremeblocks.misc.IBattery;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBattery extends ItemCustom implements IBattery
{
	public final int maxHeldPower;

	public ItemBattery(int maxHeldPower)
	{
		super("Battery", Init.tab_mainItems);
		setHasSubtypes(true);
		setMaxDamage(maxHeldPower);
		setMaxStackSize(1);
		setTextureName(Init.MODID + ":battery");
		this.maxHeldPower = maxHeldPower;
	}

	@Override
	public boolean receivePower(ItemStack stack, int power)
	{
		if (stack.getItemDamage() - power < 0)
		{
			stack.setItemDamage(0);
			return false;
		}
		else
		{
			stack.setItemDamage(stack.getItemDamage() - power);
			return true;
		}
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean lol)
	{
		list.add(maxHeldPower - stack.getItemDamage() + " EJ Held");
		list.add("EJ is Extreme Joules");
	}

	@Override
	public boolean removePower(ItemStack stack, int power)
	{
		if (stack.getItemDamage() + power > maxHeldPower)
		{
			stack.setItemDamage(maxHeldPower);
			return false;
		}
		else
		{
			stack.setItemDamage(stack.getItemDamage() + power);
			return true;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list)
	{
		list.add(new ItemStack(item, 1, 0));
		list.add(new ItemStack(item, 1, maxHeldPower));
	}

	@Override
	public int getMaxHeldPower()
	{
		return maxHeldPower;
	}
}
