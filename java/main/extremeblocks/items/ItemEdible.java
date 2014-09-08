package main.extremeblocks.items;

import java.util.ArrayList;
import main.com.hk.testing.util.JavaHelp;
import main.com.hk.testing.util.MPUtil;
import main.extremeblocks.ExtremeBlocks;
import main.extremeblocks.Init;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemEdible extends ItemFood
{
	private EnumAction action = EnumAction.eat;
	private int maxUseDuration = 32;
	private final ArrayList<Integer> potionIDs, potionDurations,
			potionAmplifiers;

	public ItemEdible(String name)
	{
		this(name, 0);
	}

	public ItemEdible(String name, int healedAmount)
	{
		this(name, healedAmount, 0.6F);
	}

	public ItemEdible(String name, int healedAmount, float saturationModifier)
	{
		this(name, Init.tab_mainItems, healedAmount, saturationModifier);
	}

	public ItemEdible(String name, CreativeTabs tab, int healedAmount, float saturationModifier)
	{
		super(healedAmount, saturationModifier, false);
		this.setUnlocalizedName(name);
		this.setCreativeTab(tab);
		this.potionIDs = JavaHelp.newArrayList();
		this.potionDurations = JavaHelp.newArrayList();
		this.potionAmplifiers = JavaHelp.newArrayList();
		ExtremeBlocks.items.add(this);
	}

	@Override
	protected void onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (MPUtil.isServerSide())
		{
			for (int i = 0; i < potionIDs.size(); i++)
			{
				par3EntityPlayer.addPotionEffect(new PotionEffect(potionIDs.get(i), potionDurations.get(i), potionAmplifiers.get(i)));
			}
		}
	}

	public ItemEdible addPotionEffect(Potion potion, int potionDuration, int potionAmplifier)
	{
		if (potion != null)
		{
			potionIDs.add(potion.id);
			potionDurations.add(potionDuration * 20);
			potionAmplifiers.add(potionAmplifier);
		}
		return this;
	}

	public ItemEdible addPotionEffect(Potion potion, int potionDuration)
	{
		return addPotionEffect(potion, potionDuration, 0);
	}

	public ItemEdible setAction(EnumAction action)
	{
		this.action = action;
		return this;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack)
	{
		return action;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack)
	{
		return this.maxUseDuration;
	}

	public ItemEdible setMaxUseDuration(int maxUseDuration)
	{
		this.maxUseDuration = maxUseDuration;
		return this;
	}
}