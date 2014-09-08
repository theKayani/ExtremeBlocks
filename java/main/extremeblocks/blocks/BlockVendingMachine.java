package main.extremeblocks.blocks;

import main.com.hk.testing.util.BlockCustom;
import main.com.hk.testing.util.MPUtil;
import main.extremeblocks.Init;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockVendingMachine extends BlockCustom
{
	@SideOnly(Side.CLIENT)
	private IIcon field_149936_O;
	@SideOnly(Side.CLIENT)
	private IIcon field_149935_N;

	public BlockVendingMachine()
	{
		super(Material.iron, "Vending Machine");
		this.irregular();
		this.setHardness(3.0F);
		this.setBlockBounds(0.05F, 0.0F, 0.05F, 0.95F, 1.4F, 0.95F);
		this.setBlockTextureName(Init.MODID + ":vendingmachine");
		this.setCreativeTab(Init.tab_mainBlocks);
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

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int p_149691_1_, int p_149691_2_)
	{
		return p_149691_1_ == 1 ? this.field_149935_N : (p_149691_1_ == 0 ? this.field_149935_N : (p_149691_1_ != p_149691_2_ ? this.blockIcon : this.field_149936_O));
	}

	@Override
	public void setBlockBoundsForItemRender()
	{
		this.setBlockBounds(0.05F, 0.0F, 0.05F, 0.95F, 1.0F, 0.95F);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_)
	{
		this.setBlockBounds(0.05F, 0.0F, 0.05F, 0.95F, 1.4F, 0.95F);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister p_149651_1_)
	{
		this.blockIcon = p_149651_1_.registerIcon(Init.MODID + ":vendingmachine_side");
		this.field_149936_O = p_149651_1_.registerIcon(Init.MODID + ":vendingmachine_front");
		this.field_149935_N = p_149651_1_.registerIcon(Init.MODID + ":vendingmachine_side");
	}

	@Override
	public void onBlockAdded(World p_149726_1_, int p_149726_2_, int p_149726_3_, int p_149726_4_)
	{
		super.onBlockAdded(p_149726_1_, p_149726_2_, p_149726_3_, p_149726_4_);
		this.func_149930_e(p_149726_1_, p_149726_2_, p_149726_3_, p_149726_4_);
	}

	private void func_149930_e(World p_149930_1_, int p_149930_2_, int p_149930_3_, int p_149930_4_)
	{
		if (MPUtil.isServerSide())
		{
			Block block = p_149930_1_.getBlock(p_149930_2_, p_149930_3_, p_149930_4_ - 1);
			Block block1 = p_149930_1_.getBlock(p_149930_2_, p_149930_3_, p_149930_4_ + 1);
			Block block2 = p_149930_1_.getBlock(p_149930_2_ - 1, p_149930_3_, p_149930_4_);
			Block block3 = p_149930_1_.getBlock(p_149930_2_ + 1, p_149930_3_, p_149930_4_);
			byte b0 = 3;
			if (block.func_149730_j() && !block1.func_149730_j())
			{
				b0 = 3;
			}
			if (block1.func_149730_j() && !block.func_149730_j())
			{
				b0 = 2;
			}
			if (block2.func_149730_j() && !block3.func_149730_j())
			{
				b0 = 5;
			}
			if (block3.func_149730_j() && !block2.func_149730_j())
			{
				b0 = 4;
			}
			p_149930_1_.setBlockMetadataWithNotify(p_149930_2_, p_149930_3_, p_149930_4_, b0, 2);
		}
	}

	@Override
	public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_)
	{
		int l = MathHelper.floor_double(p_149689_5_.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		if (l == 0)
		{
			p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 2, 2);
		}
		if (l == 1)
		{
			p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 5, 2);
		}
		if (l == 2)
		{
			p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 3, 2);
		}
		if (l == 3)
		{
			p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 4, 2);
		}
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int idk, float sideX, float sideY, float sideZ)
	{
		if (player.inventory.consumeInventoryItem(Items.diamond))
		{
			return player.inventory.addItemStackToInventory(new ItemStack(MPUtil.getRandomItem(), world.rand.nextInt(20) == 0 ? 2 : 1));
		}
		return false;
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		return world.isAirBlock(x, y + 1, z);
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		return this.canBlockStay(world, x, y, z);
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		if (!canBlockStay(world, x, y, z))
		{
			this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
			world.setBlockToAir(x, y, z);
		}
	}
}
