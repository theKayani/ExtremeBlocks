package main.extremeblocks.blocks;

import main.com.hk.eb.util.BlockCustom;
import main.com.hk.eb.util.MPUtil;
import main.extremeblocks.Init;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockPowderKeg extends BlockCustom
{
	public BlockPowderKeg()
	{
		super(Material.wood, "Powder Keg");
		setBlockTextureName(Init.MODID + ":powder_keg");
		setHardness(1.0F);
		setCreativeTab(Init.tab_mainBlocks);
		setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 0.8F, 0.75F);
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
	public void onNeighborBlockChange(World world, int x, int y, int z, Block lol)
	{
		for (int i = -1; i < 2; i++)
		{
			for (int k = -1; k < 2; k++)
			{
				if (MPUtil.isServerSide() && world.getBlock(x + i, y, z + k) == Blocks.fire)
				{
					explode(world, x, y, z);
				}
			}
		}
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int lol, float sideX, float sideY, float sideZ)
	{
		if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == Items.flint_and_steel)
		{
			explode(world, x, y, z);
			player.getCurrentEquippedItem().damageItem(1, player);
			return true;
		}
		return false;
	}

	@Override
	public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion explosion)
	{
		explode(world, x, y, z);
	}

	private void explode(World world, int x, int y, int z)
	{
		if (MPUtil.isServerSide())
		{
			world.createExplosion(null, x, y, z, 4.0F, true);
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		if (entity instanceof EntityArrow && MPUtil.isServerSide())
		{
			EntityArrow entityarrow = (EntityArrow) entity;
			if (entityarrow.isBurning())
			{
				explode(world, x, y, z);
			}
		}
	}

	@Override
	public boolean canDropFromExplosion(Explosion explosion)
	{
		return false;
	}
}
