package main.extremeblocks.items;

import main.com.hk.eb.util.Info;
import main.extremeblocks.ExtremeBlocks;
import main.extremeblocks.Init;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class ItemSeed extends ItemSeeds implements Info
{
	public final int crop;
	public final String name;

	public ItemSeed(String name, int crop)
	{
		super(getCrop(crop), Blocks.farmland);
		setUnlocalizedName(name);
		setCreativeTab(Init.tab_mainItems);
		setTextureName(Init.MODID + ":" + name.toLowerCase().replaceAll(" ", "_"));
		this.name = name;
		this.crop = crop;
		ExtremeBlocks.items.add(this);
	}

	@Override
	public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int p_77648_7_, float p_77648_8_, float p_77648_9_, float p_77648_10_)
	{
		if (p_77648_7_ != 1) return false;
		else if (p_77648_2_.canPlayerEdit(p_77648_4_, p_77648_5_, p_77648_6_, p_77648_7_, p_77648_1_) && p_77648_2_.canPlayerEdit(p_77648_4_, p_77648_5_ + 1, p_77648_6_, p_77648_7_, p_77648_1_))
		{
			if (p_77648_3_.getBlock(p_77648_4_, p_77648_5_, p_77648_6_).canSustainPlant(p_77648_3_, p_77648_4_, p_77648_5_, p_77648_6_, ForgeDirection.UP, this) && p_77648_3_.isAirBlock(p_77648_4_, p_77648_5_ + 1, p_77648_6_))
			{
				p_77648_3_.setBlock(p_77648_4_, p_77648_5_ + 1, p_77648_6_, getCrop(crop));
				--p_77648_1_.stackSize;
				return true;
			}
			else return false;
		}
		else return false;
	}

	private static Block getCrop(int crop)
	{
		switch (crop)
		{
			case TOMATO_CROP:
				return Init.tomato_crop;
			case CUCUMBER_CROP:
				return Init.cucumber_crop;
		}
		return null;
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
		return getUnlocalizedName();
	}

	public static final int TOMATO_CROP = 0;
	public static final int CUCUMBER_CROP = 1;

	@Override
	public String getInfo()
	{
		return "Can be placed in tilled dirt to start growing it's corresponding plant. Obtained like wheat seeds are.";
	}

	@Override
	public Elements getElements()
	{
		return new Elements(true, false);
	}
}
