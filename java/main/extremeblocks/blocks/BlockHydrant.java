package main.extremeblocks.blocks;

import main.com.hk.eb.util.BlockCustom;
import main.com.hk.eb.util.MPUtil;
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
		setHardness(3.0F);
		setCreativeTab(Init.tab_mainBlocks);
		setBlockTextureName(Init.MODID + ":fire_hydrant");
		setBlockBounds(0.4F, 0.0F, 0.4F, 0.6F, 0.6F, 0.6F);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int idk, float sideX, float sideY, float sideZ)
	{
		if (player.getHeldItem() != null && player.getHeldItem().getItem() == Init.wrench)
		{
			Block block = world.getBlock(x, y + 1, z);
			if (MPUtil.isServerSide() && block.isReplaceable(world, x, y + 1, z))
			{
				world.setBlock(x, y + 1, z, Blocks.flowing_water);
			}
		}
		for (int i = -3; i < 4; i++)
		{
			for (int j = -3; j < 4; j++)
			{
				for (int k = -3; k < 4; k++)
				{
					if (MPUtil.isServerSide())
					{
						world.extinguishFire(player, x + i, y + j, z + k, 0);
					}
				}
			}
		}
		return true;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
}
