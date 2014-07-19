package main.extremeblocks.items;

import main.com.hk.testing.util.MPUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemWeed extends ItemEdible
{
	private int state;

	public ItemWeed(String name, int state)
	{
		super(name, 7);
		this.setAction(EnumAction.bow);
		this.setAlwaysEdible();

		this.state = state;
	}

	protected void onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (MPUtil.isServerSide())
		{
			switch(state)
			{
				case 1:
					par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.hunger.id, 200, 1));
					par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.confusion.id, 220, 1));
					break;
				case 2:
					par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.hunger.id, 200, 1));
					par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.confusion.id, 220, 3));
					break;
			}

			MPUtil.sendMessage("WOAH! Holy Crap Man! That was Wicked!", par3EntityPlayer);
		}
	}
}
