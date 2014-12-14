package main.extremeblocks.items;

import main.com.hk.eb.util.MPUtil;
import main.extremeblocks.Init;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemWeed extends ItemEdible
{
	private final int state;

	public ItemWeed(String name, int state)
	{
		super(name, 7);
		setCreativeTab(Init.tab_mainItems);
		setAction(EnumAction.bow);
		setAlwaysEdible();
		this.state = state;
	}

	@Override
	protected void onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer player)
	{
		if (MPUtil.isServerSide())
		{
			switch (state)
			{
				case 0:
					player.addPotionEffect(new PotionEffect(Potion.hunger.id, 20));
					player.addPotionEffect(new PotionEffect(Potion.confusion.id, 22));
					MPUtil.sendMessage("What was that? That wasn't anything...", player);
					break;
				case 1:
					player.addPotionEffect(new PotionEffect(Potion.hunger.id, 200));
					player.addPotionEffect(new PotionEffect(Potion.confusion.id, 220));
					MPUtil.sendMessage("Woo! That wasn't Bad!", player);
					break;
				case 2:
					player.addPotionEffect(new PotionEffect(Potion.hunger.id, 200, 1));
					player.addPotionEffect(new PotionEffect(Potion.confusion.id, 220, 3));
					MPUtil.sendMessage("WOAH! Holy Crap Man! That was Even Better!", player);
					break;
			}
		}
	}

	@Override
	public String getInfo()
	{
		return "Can be smoked to cause different (\"pleasurable\") effects. Use with caution! The more powerful the hemp, the more powerful the effects.";
	}

	@Override
	public Elements getElements()
	{
		return new Elements(true, false);
	}
}
