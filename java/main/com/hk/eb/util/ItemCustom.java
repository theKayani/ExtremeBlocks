package main.com.hk.eb.util;

import java.util.List;
import java.util.Map;
import main.extremeblocks.ExtremeBlocks;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCustom extends Item implements IInfo
{
	private String info;
	private boolean showRecipe;
	private final String name;

	public ItemCustom(String name, CreativeTabs tab)
	{
		setUnlocalizedName(name);
		setCreativeTab(tab);
		this.name = name;
		ExtremeBlocks.items.add(this);
	}

	public ItemCustom setInfo(String info)
	{
		this.info = info;
		return this;
	}

	public ItemCustom setShowRecipe()
	{
		showRecipe = true;
		if (info == null || info.isEmpty())
		{
			setInfo("Used for crafting a multitude of crafting recipes");
		}
		return this;
	}

	@Override
	public ItemCustom setTextureName(String name)
	{
		super.setTextureName(name);
		return this;
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

	@Override
	public String getInfo()
	{
		return info;
	}

	@Override
	public Elements getElements()
	{
		return new Elements(info != null && !info.isEmpty(), showRecipe);
	}

	private static Map<String, IIcon> icons = JavaHelp.newHashMap();
	private static List<String> iconPaths = JavaHelp.newArrayList();
}
