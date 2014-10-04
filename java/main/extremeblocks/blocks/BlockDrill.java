package main.extremeblocks.blocks;

import java.util.ArrayList;
import java.util.Random;
import main.com.hk.eb.util.BlockCustom;
import main.com.hk.eb.util.JavaHelp;
import main.com.hk.eb.util.MPUtil;
import main.extremeblocks.Init;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockDrill extends BlockCustom
{
	public final boolean isHead;
	private static ArrayList<ItemStack> loot = JavaHelp.newArrayList();

	public BlockDrill(boolean isHead)
	{
		super(Material.iron, "Drill" + (isHead ? " Head" : ""));
		this.setBlockTextureName(Init.MODID + ":drill" + (isHead ? "_head" : ""));
		this.isHead = isHead;

		if (isHead)
		{
			this.setCreativeTab(Init.tab_mainBlocks);
			this.setHardness(3.0F);
		}
		else this.setHardness(0.2F);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int idk, float sideX, float sideY, float sideZ)
	{
		if (isHead && MPUtil.isServerSide())
		{
			for (int i = 0; i < loot.size(); i++)
			{
				if (loot.get(i) != null)
				{
					player.inventory.addItemStackToInventory(loot.get(i));
				}
			}
			MPUtil.sendMessage("You have collected " + loot.size() + " blocks!", player);
			loot.clear();
			return true;
		}
		return false;
	}

	@Override
	public int getRenderType()
	{
		return 2;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4)
	{
		Block block = par1World.getBlock(par2, par3 - 1, par4);
		if (isHead)
		{
			if (block != Blocks.bedrock && !par1World.isAirBlock(par2, par3 - 1, par4))
			{
				par1World.setBlock(par2, par3 - 1, par4, Init.drill_pole);
			}
			if (block == Init.drill_pole)
			{
				par1World.setBlock(par2, par3, par4, Init.drill_pole);
			}
		}
		else
		{
			if (block != Blocks.bedrock && !par1World.isAirBlock(par2, par3 - 1, par4))
			{
				if (MPUtil.isServerSide())
				{

					loot.add(new ItemStack(block.getItemDropped(par1World.getBlockMetadata(par2, par3 - 1, par4), par1World.rand, 0)));
				}
				par1World.setBlock(par2, par3 - 1, par4, Init.drill_pole);
			}
			if (block == Blocks.bedrock || par1World.isAirBlock(par2, par3 - 1, par4))
			{
				par1World.setBlock(par2, par3, par4, Init.drill);
			}
		}
	}

	@Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, Block block)
	{
		if (isHead)
		{
			if (par1World.getBlock(par2, par3 - 1, par4) == Init.drill_pole || par1World.getBlock(par2, par3 - 1, par4) == Init.drill)
			{
				par1World.setBlock(par2, par3, par4, Init.drill_pole);
			}
			if (par1World.getBlock(par2, par3 - 1, par4) == Blocks.bedrock)
			{
				par1World.setBlock(par2, par3, par4, Init.drill);
			}
		}
	}

	@Override
	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
	{
		return isHead ? Item.getItemFromBlock(Init.drill) : Init.crushed_stone;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getItem(World par1World, int par2, int par3, int par4)
	{
		return Item.getItemFromBlock(Init.drill);
	}
}
