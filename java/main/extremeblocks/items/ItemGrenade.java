package main.extremeblocks.items;

import main.com.hk.eb.util.ItemCustom;
import main.com.hk.eb.util.MPUtil;
import main.extremeblocks.Init;
import main.extremeblocks.entities.EntityGrenade;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemGrenade extends ItemCustom
{
	public ItemGrenade()
	{
		super("Grenade", Init.tab_mainItems);
		setTextureName(Init.MODID + ":grenade");
	}

	@Override
	public boolean hasEffect(ItemStack par1ItemStack, int pass)
	{
		return true;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		EntityGrenade grenade = new EntityGrenade(par2World, par3EntityPlayer);
		if (MPUtil.isServerSide())
		{
			par2World.spawnEntityInWorld(grenade);
			--par1ItemStack.stackSize;
		}
		return par1ItemStack;
	}
}
