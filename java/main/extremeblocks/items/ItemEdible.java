package main.extremeblocks.items;

import java.util.ArrayList;
import main.com.hk.testing.util.ItemCustom;
import main.com.hk.testing.util.MPUtil;
import main.extremeblocks.ExtremeBlocks;
import main.extremeblocks.Init;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemEdible extends ItemCustom
{
	private EnumAction action = EnumAction.eat;
	private boolean alwaysEdible;
	private int healAmount, maxUseDuration;
	private float saturationModifier;
	private final ArrayList<Integer> potionIDs, potionDurations, potionAmplifiers;

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
		this(name, Init.tab_consumables, healedAmount, saturationModifier);
	}

	public ItemEdible(String name, CreativeTabs tab, int healedAmount, float saturationModifier)
	{
		super(name, tab);
		this.setCreativeTab(tab);
		this.potionIDs = new ArrayList<Integer>();
		this.potionDurations = new ArrayList<Integer>();
		this.potionAmplifiers = new ArrayList<Integer>();
		this.healAmount = healedAmount;
		this.saturationModifier = saturationModifier;
		this.maxUseDuration = 32;
	}

	public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player)
	{
		--stack.stackSize;
		player.getFoodStats().addStats(getHealAmount(), getSaturationModifier());
		world.playSoundAtEntity(player, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
		
		if (MPUtil.isServerSide())
		{
			for (int i = 0; i < potionIDs.size(); i++)
			{
				player.addPotionEffect(new PotionEffect(potionIDs.get(i), potionDurations.get(i), potionAmplifiers.get(i)));
			}
		}
		
		return stack;
	}

	protected void onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {}

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
		return addPotionEffect(potion, potionDuration, 1);
	}

	public ItemEdible setAction(EnumAction action)
	{
		this.action = action;
		return this;
	}

	public EnumAction getItemUseAction(ItemStack par1ItemStack)
	{
		return action;
	}

	public int getMaxItemUseDuration(ItemStack stack)
	{
		return this.maxUseDuration;
	}

	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if (canEat(player))
		{
			player.setItemInUse(stack, this.getMaxItemUseDuration(stack));
			this.onEaten(stack, world, player);
		}

		return stack;
	}
	
	protected boolean canEat(EntityPlayer player)
    {
        return (this.alwaysEdible || player.getFoodStats().needFood()) && !player.capabilities.disableDamage;
    }

	public int getHealAmount()
	{
		return this.healAmount;
	}

	public float getSaturationModifier()
	{
		return this.saturationModifier;
	}

	public ItemEdible setAlwaysEdible()
	{
		this.alwaysEdible = true;
		return this;
	}
	
	public ItemEdible setMaxUseDuration(int maxUseDuration)
	{
		this.maxUseDuration = maxUseDuration;
		return this;
	}
}