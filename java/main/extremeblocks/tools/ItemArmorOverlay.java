package main.extremeblocks.tools;

import main.extremeblocks.Init;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemArmorOverlay extends ItemArmor
{
	public final int color;
	private final String name;

	public ItemArmorOverlay(ArmorMaterial mat, int part, int color)
	{
		super(mat, 5, part);
		name = mat.name().substring(0, 1).toUpperCase() + mat.name().substring(1).toLowerCase() + " " + getArmorSuffix(part);
		setUnlocalizedName(name);
		setCreativeTab(Init.tab_tools);
		setTextureName("iron_" + getArmorSuffix(part).toLowerCase());
		this.color = color;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack stack, int flag)
	{
		return color;
	}

	private String getArmorSuffix(int part)
	{
		switch (part)
		{
			case 0:
				return "Helmet";
			case 1:
				return "Chestplate";
			case 2:
				return "Leggings";
			case 3:
				return "Boots";
		}
		return "Armor";
	}

	@Override
	public String getUnlocalizedName()
	{
		return name;
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack)
	{
		return name;
	}

	@Override
	public String getItemStackDisplayName(ItemStack par1ItemStack)
	{
		return name;
	}

	@Override
	public String getArmorTexture(ItemStack itemstack, Entity entity, int slot, String layer)
	{
		if (armorType != 2) return Init.MODID + ":textures/models/armor/" + getArmorMaterial().name().toLowerCase() + "_layer_1.png";
		else if (armorType == 2) return Init.MODID + ":textures/models/armor/" + getArmorMaterial().name().toLowerCase() + "_layer_2.png";
		return null;
	}
}
