package main.extremeblocks.blocks;

import main.com.hk.eb.util.BlockCustom;
import main.com.hk.eb.util.MPUtil;
import main.extremeblocks.Init;
import main.extremeblocks.entities.mobs.EntityDemon;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockAltar extends BlockCustom
{
	@SideOnly(Side.CLIENT)
	private IIcon topIcon;

	public BlockAltar()
	{
		super(Material.rock, "Altar");
		setBlockTextureName(Init.MODID + ":altar_on");
		setCreativeTab(Init.tab_mainBlocks);
		setHardness(1.5F);
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		Block b = world.getBlock(x, y - 1, z);
		Block b1 = world.getBlock(x, y - 2, z);
		Block b2 = world.getBlock(x, y - 3, z);

		if (this == Init.altar && b == Init.altar && b1 == Init.altar && b2 == Init.altar)
		{
			world.setBlockToAir(x, y - 0, z);
			world.setBlockToAir(x, y - 1, z);
			world.setBlockToAir(x, y - 2, z);
			world.setBlockToAir(x, y - 3, z);
			world.createExplosion(null, x, y, z, 2.0F, false);
			world.createExplosion(null, x, y - 2, z, 2.0F, false);
			EntityDemon demon = new EntityDemon(world);
			demon.setPosition(x + 0.5D, y, z + 0.5D);
			if (MPUtil.isServerSide())
			{
				world.spawnEntityInWorld(demon);
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return side == 1 || side == 0 ? topIcon : blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister p_149651_1_)
	{
		blockIcon = p_149651_1_.registerIcon(Init.MODID + ":altar_on");
		topIcon = p_149651_1_.registerIcon(Init.MODID + ":altar_on_top");
	}
}
