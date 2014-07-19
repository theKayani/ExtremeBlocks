package main.extremeblocks.blocks;

import java.util.ArrayList;
import main.com.hk.testing.util.BlockCustom;
import main.com.hk.testing.util.MPUtil;
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
	private static ArrayList<Block> loot = new ArrayList<Block>();

	public BlockDrill(boolean isHead)
	{
		super(Material.iron, "Drill" + (isHead ? " Head" : ""));
		if (isHead) this.setCreativeTab(Init.tab_misc);
		this.setBlockTextureName(Init.MODID + ":drill" + (isHead ? "_head" : ""));
		this.setHardness(3.0F);
		this.isHead = isHead;
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int idk, float sideX, float sideY, float sideZ)
	{
		boolean activated = activated(player);

		if (activated)
		{
			loot.clear();
		}

		return activated;
	}

	private boolean activated(EntityPlayer player)
	{
		if (isHead)
		{
			MPUtil.sendMessage("You have collected " + loot.size() + " blocks!", player);

			for (int i = 0; i < loot.size(); i++)
			{
				if (loot.get(i) != null)
				{
					player.inventory.addItemStackToInventory(new ItemStack(loot.get(i)));
				}
			}
			return true;
		}
		return false;
	}

	public int getRenderType()
	{
		return 2;
	}

	public boolean isOpaqueCube()
	{
		return false;
	}

	public boolean renderAsNormalBlock()
	{
		return false;
	}

	public void onBlockAdded(World par1World, int par2, int par3, int par4)
	{
		if (isHead)
		{
			if (par1World.getBlock(par2, par3 - 1, par4) != Blocks.bedrock && !par1World.isAirBlock(par2, par3 - 1, par4))
			{
				par1World.setBlock(par2, par3 - 1, par4, Init.drill_pole);
			}
			if (par1World.getBlock(par2, par3 - 1, par4) == Init.drill_pole)
			{
				par1World.setBlock(par2, par3, par4, Init.drill_pole);
			}
		}
		else
		{
			if (par1World.getBlock(par2, par3 - 1, par4) != Blocks.bedrock && !par1World.isAirBlock(par2, par3 - 1, par4))
			{
				if (par1World.getBlock(par2, par3 - 1, par4).getCreativeTabToDisplayOn() != null && MPUtil.isServerSide())
				{
					loot.add(par1World.getBlock(par2, par3 - 1, par4));
				}
				par1World.setBlock(par2, par3 - 1, par4, Init.drill_pole);
			}
			if (par1World.getBlock(par2, par3 - 1, par4) == Blocks.bedrock || par1World.isAirBlock(par2, par3 - 1, par4))
			{
				par1World.setBlock(par2, par3, par4, Init.drill);
			}
		}
	}

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

	@SideOnly(Side.CLIENT)
	public Item getItem(World par1World, int par2, int par3, int par4)
	{
		return Item.getItemFromBlock(Init.drill);
	}
}
