package main.extremeblocks.items;

import java.util.List;
import main.com.hk.eb.util.ItemCustom;
import main.extremeblocks.Init;
import main.extremeblocks.Vars;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCounter extends ItemCustom
{
	public ItemCounter()
	{
		super("Counter", Init.tab_mainItems);
		setTextureName(Init.MODID + ":counter");
		maxStackSize = 1;
		setInfo("Can be right clicked to increase the count. Can't be reversed in any way.");
		setShowRecipe();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack par1ItemStack, int pass)
	{
		return true;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean par4)
	{
		list.add("Count: " + itemstack.getItemDamage());
		list.add(Vars.counterMessage.equals("") ? player.getCommandSenderName() : Vars.counterMessage);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World par2World, EntityPlayer par3EntityPlayer)
	{
		stack.setItemDamage(stack.getItemDamage() + 1);
		return stack;
	}
}
