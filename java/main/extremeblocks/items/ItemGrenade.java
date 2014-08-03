package main.extremeblocks.items;

import main.com.hk.testing.util.ItemCustom;
import main.com.hk.testing.util.MPUtil;
import main.extremeblocks.Init;
import main.extremeblocks.entities.EntityGrenade;
import main.extremeblocks.network.PacketGrenade;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemGrenade extends ItemCustom
{
	private float power;
	
	public ItemGrenade(float power)
	{
		super("Grenade X" + ((int) power), Init.tab_mainItems);
		this.setTextureName(Init.MODID + ":grenade");
		this.power = power;
	}

	public boolean hasEffect(ItemStack par1ItemStack, int pass)
	{
		return true;
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		if (MPUtil.isServerSide())
		{
			EntityGrenade grenade = new EntityGrenade(par2World, par3EntityPlayer, power);
			par2World.spawnEntityInWorld(grenade);
			MPUtil.sendToServer(new PacketGrenade(grenade));
			--par1ItemStack.stackSize;
		}
		return par1ItemStack;
	}
}
