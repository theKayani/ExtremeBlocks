package main.extremeblocks.items;

import main.com.hk.testing.util.ItemCustom;
import main.com.hk.testing.util.MPUtil;
import main.extremeblocks.Init;
import main.extremeblocks.entities.EntityGrenade;
import main.extremeblocks.entities.EntityMolotov;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemMolotov extends ItemCustom
{
	public ItemMolotov()
	{
		super("Molotov", Init.tab_mainItems);
		this.setTextureName(Init.MODID + ":molotov");
	}

	public boolean hasEffect(ItemStack par1ItemStack, int pass)
	{
		return true;
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (MPUtil.isServerSide())
		{
			par2World.spawnEntityInWorld(new EntityMolotov(par2World, par3EntityPlayer));
			--par1ItemStack.stackSize;
		}
		return par1ItemStack;
	}
}
