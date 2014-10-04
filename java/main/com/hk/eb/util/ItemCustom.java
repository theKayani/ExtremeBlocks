package main.com.hk.eb.util;

import java.util.ArrayList;
import java.util.HashMap;
import main.extremeblocks.ExtremeBlocks;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCustom extends Item
{
	private final String name;

	public ItemCustom(String name, CreativeTabs tab)
	{
		setUnlocalizedName(name);
		setCreativeTab(tab);
		this.name = name;
		ExtremeBlocks.items.add(this);
	}

	@Override
	public String getUnlocalizedName()
	{
		return name;
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack)
	{
		return this.getUnlocalizedName();
	}

	@Override
	public String getItemStackDisplayName(ItemStack par1ItemStack)
	{
		return ("" + StatCollector.translateToLocal(getUnlocalizedNameInefficiently(par1ItemStack))).trim();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		super.registerIcons(ir);
		for (int i = 0; i < iconPaths.size(); i++)
		{
			icons.put(iconPaths.get(i), ir.registerIcon(iconPaths.get(i)));
		}
	}

	public static IIcon getIconFromString(String iiconName)
	{
		return icons.get(iiconName);
	}

	public static boolean hasKey(String iiconName)
	{
		return icons.containsKey(iiconName);
	}

	public static void registerIIcon(String iiconPath)
	{
		iconPaths.add(iiconPath);
	}

	private static HashMap<String, IIcon> icons = JavaHelp.newHashMap();
	private static ArrayList<String> iconPaths = JavaHelp.newArrayList();
}
