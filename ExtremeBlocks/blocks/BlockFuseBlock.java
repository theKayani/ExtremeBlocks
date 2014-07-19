package main.extremeblocks.blocks;

import java.util.Random;
import main.com.hk.testing.util.BlockCustom;
import main.com.hk.testing.util.MPUtil;
import main.extremeblocks.Init;
import main.extremeblocks.blocks.tileentities.TileEntityFuse;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFuseBlock extends BlockCustom implements ITileEntityProvider
{
	public BlockFuseBlock()
	{
		super(Material.circuits, "Fuse Block");
		this.setBlockTextureName(Init.MODID + ":fuse");
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.15F, 1.0F);
	}

	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		return world.getBlock(x, y - 1, z).getMaterial().isOpaque();
	}

	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
	{
		return Init.fuse;
	}

	@SideOnly(Side.CLIENT)
	public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_)
	{
		return Init.fuse;
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int lol, float sideX, float sideY, float sideZ)
	{
		MPUtil.sendMessage("Side X: " + sideX + ", Side Y: " + sideY + ", Side Z: " + sideZ, player);

		if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == Items.flint_and_steel)
		{
			world.setBlock(x, y, z, Blocks.fire);
			player.getCurrentEquippedItem().damageItem(1, player);
			return true;
		}
		return false;
	}

	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		if (MPUtil.isServerSide())
		{
			if (!this.canPlaceBlockAt(world, x, y, z))
			{
				this.dropBlockAsItem(world, x, y, z, 0, 0);
				world.setBlockToAir(x, y, z);
			}

			super.onNeighborBlockChange(world, x, y, z, block);
		}
	}

	public boolean isOpaqueCube()
	{
		return false;
	}

	public boolean renderAsNormalBlock()
	{
		return false;
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		return null;
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2)
	{
		return new TileEntityFuse();
	}
}
