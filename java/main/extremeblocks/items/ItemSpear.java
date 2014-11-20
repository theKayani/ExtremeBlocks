package main.extremeblocks.items;

import main.com.hk.eb.util.ItemCustom;
import main.com.hk.eb.util.MPUtil;
import main.extremeblocks.Init;
import main.extremeblocks.entities.EntitySpear;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemSpear extends ItemCustom
{
	public ItemSpear()
	{
		super("Spear", Init.tab_mainItems);
		setTextureName(Init.MODID + ":spear");
	}

	@Override
	public boolean hasEffect(ItemStack par1ItemStack, int pass)
	{
		return true;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		EntitySpear spear = new EntitySpear(par2World, par3EntityPlayer, 1.2F);
		if (par3EntityPlayer.capabilities.isCreativeMode)
		{
			spear.canBePickedUp = 2;
		}
		else
		{
			par1ItemStack.stackSize--;
		}

		if (MPUtil.isServerSide())
		{
			par2World.spawnEntityInWorld(spear);
		}
		return par1ItemStack;
	}
}
