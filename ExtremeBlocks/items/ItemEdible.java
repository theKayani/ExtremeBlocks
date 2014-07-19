package main.extremeblocks.items;

import java.util.ArrayList;
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
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemEdible extends ItemFood
{
	private String name;
	private EnumAction action = EnumAction.eat;
	private ArrayList<Integer> potionIDs;
	private ArrayList<Integer> potionDurations;
	private ArrayList<Integer> potionAmplifiers;

	public ItemEdible(String name, int healedAmount)
	{
		this(name, Init.tab_foods, healedAmount, 0.6F);
	}

	public ItemEdible(String name, int healedAmount, float saturationModifier)
	{
		this(name, Init.tab_foods, healedAmount, saturationModifier);
	}

	public ItemEdible(String name, CreativeTabs tab, int healedAmount, float saturationModifier)
	{
		super(healedAmount, saturationModifier, false);
		this.setCreativeTab(tab);
		this.setUnlocalizedName(name);
		this.name = name;
		this.potionIDs = new ArrayList<Integer>();
		this.potionDurations = new ArrayList<Integer>();
		this.potionAmplifiers = new ArrayList<Integer>();

		ExtremeBlocks.items.add(this);
	}

	protected void onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (MPUtil.isServerSide())
		{
			for(int i = 0; i < potionIDs.size(); i++)
			{
				par3EntityPlayer.addPotionEffect(new PotionEffect(potionIDs.get(i), potionDurations.get(i), potionAmplifiers.get(i)));
			}
		}
	}

	public ItemEdible addPotionEffect(Potion potion, int potionDuration, int potionAmplifier)
	{
		if(potion == null) return this;
		potionIDs.add(potion.id);
		potionDurations.add(potionDuration * 20);
		potionAmplifiers.add(potionAmplifier);
		return this;
	}

	public ItemEdible addPotionEffect(Potion potion, int potionDuration)
	{
		return addPotionEffect(potion, potionDuration, 1);
	}

	public ItemEdible setAction(EnumAction action)
	{
		this.action  = action;
		return this;
	}

	public EnumAction getItemUseAction(ItemStack par1ItemStack)
	{
		return action;
	}

	public String getUnlocalizedName()
	{
		return name;
	}

	public String getUnlocalizedName(ItemStack par1ItemStack)
	{
		return this.getUnlocalizedName();
	}

	public String getItemStackDisplayName(ItemStack par1ItemStack)
	{
		return name;
	}
}