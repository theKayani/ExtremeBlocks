package main.extremeblocks.items;

import main.com.hk.testing.util.ItemCustom;
import main.com.hk.testing.util.MPUtil;
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
		this.setTextureName(Init.MODID + ":grenade");
	}

	public boolean hasEffect(ItemStack par1ItemStack, int pass)
	{
		return true;
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (MPUtil.isServerSide())
		{
			par2World.spawnEntityInWorld(new EntityGrenade(par2World, par3EntityPlayer));
			--par1ItemStack.stackSize;
		}
		return par1ItemStack;
	}
}
