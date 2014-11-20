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
		setHardness(3.0F);
		setBlockBounds(0.05F, 0.0F, 0.05F, 0.95F, 1.0F, 0.95F);
		setBlockTextureName(Init.MODID + ":vendingmachine");
		setCreativeTab(Init.tab_mainBlocks);
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
		return side == 1 ? blockIcon : side == 0 ? blockIcon : side != meta ? blockIcon : frontIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister p_149651_1_)
	{
		blockIcon = p_149651_1_.registerIcon(Init.MODID + ":vendingmachine_side");
		frontIcon = p_149651_1_.registerIcon(Init.MODID + ":vendingmachine_front");
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		super.onBlockAdded(world, x, y, z);
		setupOrientation(world, x, y, z);
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
		if (MPUtil.isClientSide() && player.inventory.consumeInventoryItem(Items.diamond))
		{
			ItemStack s = new ItemStack(MPUtil.getRandomVanillaItem());
			if (!player.inventory.addItemStackToInventory(s))
			{
				MPUtil.dropItemAsEntity(world, x + 0.5D, y + 1D, z + 0.5D, true, s);
			}
		}
		return true;
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		return world.isAirBlock(x, y + 1, z);
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		return canBlockStay(world, x, y, z);
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
