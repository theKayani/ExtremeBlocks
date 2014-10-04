package main.extremeblocks.blocks;

import main.com.hk.eb.util.BlockCustom;
import main.com.hk.eb.util.MPUtil;
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
	private IIcon frontIcon;

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
	public IIcon getIcon(int side, int meta)
	{
		return side == 1 ? this.blockIcon : (side == 0 ? this.blockIcon : (side != meta ? this.blockIcon : this.frontIcon));
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
		this.frontIcon = p_149651_1_.registerIcon(Init.MODID + ":vendingmachine_front");
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		super.onBlockAdded(world, x, y, z);
		this.setupOrientation(world, x, y, z);
	}

	private void setupOrientation(World world, int x, int y, int z)
	{
		if (MPUtil.isServerSide())
		{
			Block block = world.getBlock(x, y, z - 1);
			Block block1 = world.getBlock(x, y, z + 1);
			Block block2 = world.getBlock(x - 1, y, z);
			Block block3 = world.getBlock(x + 1, y, z);
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
			world.setBlockMetadataWithNotify(x, y, z, b0, 2);
		}
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack)
	{
		int l = MathHelper.floor_double(entity.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		if (l == 0)
		{
			world.setBlockMetadataWithNotify(x, y, z, 2, 2);
		}
		if (l == 1)
		{
			world.setBlockMetadataWithNotify(x, y, z, 5, 2);
		}
		if (l == 2)
		{
			world.setBlockMetadataWithNotify(x, y, z, 3, 2);
		}
		if (l == 3)
		{
			world.setBlockMetadataWithNotify(x, y, z, 4, 2);
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
