package main.extremeblocks.blocks.energy;

import main.com.hk.eb.util.MPUtil;
import main.extremeblocks.Init;
import main.extremeblocks.blocks.abstracts.BlockGui;
import main.extremeblocks.client.containers.ContainerEB;
import main.extremeblocks.client.guis.GuiEB;
import main.extremeblocks.tileentities.TileEntityInventory;
import main.extremeblocks.tileentities.energy.TileEntityMachine;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class BlockMachine extends BlockGui
{
	@SideOnly(Side.CLIENT)
	protected IIcon frontIcon;
	@SideOnly(Side.CLIENT)
	protected IIcon topIcon;

	public BlockMachine(String name)
	{
		super(name);
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
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register)
	{
		blockIcon = register.registerIcon(Init.MODID + ":" + getSideTexture());
		frontIcon = register.registerIcon(Init.MODID + ":" + getFrontTexture());
		topIcon = register.registerIcon(Init.MODID + ":" + getTopTexture());
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return side == 1 ? topIcon : side == 0 ? topIcon : side != meta ? blockIcon : frontIcon;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block par5, int par6)
	{
		TileEntityInventory.dropItems(world, x, y, z);
		super.breakBlock(world, x, y, z, par5, par6);
	}

	@Override
	public abstract Class<? extends TileEntityMachine> getTileClass();

	public abstract String getTopTexture();

	public abstract String getFrontTexture();

	public abstract String getSideTexture();

	@Override
	@SideOnly(Side.CLIENT)
	public abstract GuiEB getGui(InventoryPlayer inventory, World world, int x, int y, int z, TileEntity tile);

	@Override
	public abstract ContainerEB getContainer(InventoryPlayer inventory, World world, int x, int y, int z, TileEntity tile);
}
