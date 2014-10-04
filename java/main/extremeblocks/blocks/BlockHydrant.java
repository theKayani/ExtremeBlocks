package main.extremeblocks.blocks;

import main.com.hk.eb.util.BlockCustom;
import main.extremeblocks.Init;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class BlockHydrant extends BlockCustom
{
	public BlockHydrant()
	{
		super(Material.iron, "Fire Hydrant");
		this.setHardness(3.0F);
		this.setCreativeTab(Init.tab_mainBlocks);
		this.setBlockTextureName(Init.MODID + ":fire_hydrant");
		this.setBlockBounds(0.4F, 0.0F, 0.4F, 0.6F, 0.6F, 0.6F);
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int idk, float sideX, float sideY, float sideZ)
	{
		if (player.getHeldItem().getItem() == Init.wrench)
		{
			Block block = world.getBlock(x, y + 1, z);
			if (block.isReplaceable(world, x, y + 1, z))
			{
				world.setBlock(x, y + 1, z, Blocks.flowing_water);
			}
		}
		else
		{
			for (int i = -3; i < 4; i++)
			{
				for (int j = -3; j < 4; j++)
				{
					for (int k = -3; k < 4; k++)
					{
						world.extinguishFire(player, x + i, y + j, z + k, 0);
					}
				}
			}
		}
		return true;
	}

	public boolean renderAsNormalBlock()
	{
		return false;
	}

	public boolean isOpaqueCube()
	{
		return false;
	}
}
