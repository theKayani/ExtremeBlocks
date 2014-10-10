package main.extremeblocks.blocks;

import java.util.Random;
import main.com.hk.eb.util.BlockCustom;
import main.com.hk.eb.util.MPUtil;
import main.extremeblocks.ExtremeBlocks;
import main.extremeblocks.GuiIDs;
import main.extremeblocks.Init;
import main.extremeblocks.tileentities.TileEntityGenerator;
import main.extremeblocks.tileentities.TileEntityInventory;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockGenerator extends BlockCustom implements ITileEntityProvider, GuiIDs
{
	@SideOnly(Side.CLIENT)
	private IIcon ventIcon;
	@SideOnly(Side.CLIENT)
	private IIcon inputIcon;
	@SideOnly(Side.CLIENT)
	private IIcon frontIcon;

	public BlockGenerator()
	{
		super(Material.iron, "Generator");
		setHardness(2.0F);
		setBlockTextureName("generator");
		setCreativeTab(Init.tab_mainBlocks);
		setTickRandomly(true);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		// 0 == -Y
		// 1 == +Y
		// 2 == +Z
		// 3 == -Z
		// 4 == -X
		// 5 == +X
		if (side == 0 || side == 1) return blockIcon;
		switch (meta)
		{
			case 0:
			{
				switch (side)
				{
					case 2:
					{
						return frontIcon;
					}
					case 3:
					{
						return ventIcon;
					}
					case 4:
					case 5:
					{
						return inputIcon;
					}
				}
				break;
			}
			case 1:
			{
				switch (side)
				{
					case 2:
					case 3:
					{
						return inputIcon;
					}
					case 4:
					{
						return ventIcon;
					}
					case 5:
					{
						return frontIcon;
					}
				}
				break;
			}
			case 2:
			{
				switch (side)
				{
					case 2:
					{
						return ventIcon;
					}
					case 3:
					{
						return frontIcon;
					}
					case 4:
					case 5:
					{
						return inputIcon;
					}
				}
				break;
			}
			case 3:
			{
				switch (side)
				{
					case 2:
					case 3:
					{
						return inputIcon;
					}
					case 4:
					{
						return frontIcon;
					}
					case 5:
					{
						return ventIcon;
					}
				}
				break;
			}
		}
		return blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir)
	{
		blockIcon = ir.registerIcon(Init.MODID + ":generator_top");
		ventIcon = ir.registerIcon(Init.MODID + ":generator_vent");
		inputIcon = ir.registerIcon(Init.MODID + ":generator_input");
		frontIcon = ir.registerIcon(Init.MODID + ":generator_front");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int y, int z, Random rand)
	{
		TileEntityGenerator generator = (TileEntityGenerator) world.getTileEntity(x, y, z);
		if (generator.isPowered() && generator.isBurning())
		{
			for (int i = 0; i < 3; i++)
			{
				float f = x + 0.5F;
				float f1 = y + 0.0F + rand.nextFloat() * 6.0F / 16.0F;
				float f2 = z + 0.5F;
				float f3 = 0.52F;
				float f4 = rand.nextFloat() * 0.6F - 0.3F;

				if (i == 0 && rand.nextInt(5) == 0)
				{
					for (float a = 0.0F; a < 1.1F; a += 0.3F)
					{
						for (float b = 0.0F; b < 1.1F; b += 0.3F)
						{
							world.spawnParticle("smoke", x + a, y + 1.0F, z + b, 0.0D, 0.02D, 0.0D);
							world.spawnParticle("flame", x + a, y + 1.0F, z + b, 0.0D, 0.02D, 0.0D);
						}
					}
				}

				world.spawnParticle("smoke", f - f3, f1 + 0.2F, f2 + f4, 0.0D, 0.0D, 0.0D);
				world.spawnParticle("flame", f - f3, f1 + 0.2F, f2 + f4, 0.0D, 0.0D, 0.0D);

				world.spawnParticle("smoke", f + f3, f1 + 0.2F, f2 + f4, 0.0D, 0.0D, 0.0D);
				world.spawnParticle("flame", f + f3, f1 + 0.2F, f2 + f4, 0.0D, 0.0D, 0.0D);

				world.spawnParticle("smoke", f + f4, f1 + 0.2F, f2 - f3, 0.0D, 0.0D, 0.0D);
				world.spawnParticle("flame", f + f4, f1 + 0.2F, f2 - f3, 0.0D, 0.0D, 0.0D);

				world.spawnParticle("smoke", f + f4, f1 + 0.2F, f2 + f3, 0.0D, 0.0D, 0.0D);
				world.spawnParticle("flame", f + f4, f1 + 0.2F, f2 + f3, 0.0D, 0.0D, 0.0D);
			}
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
				b0 = 2;
			}
			if (block1.func_149730_j() && !block.func_149730_j())
			{
				b0 = 0;
			}
			if (block2.func_149730_j() && !block3.func_149730_j())
			{
				b0 = 1;
			}
			if (block3.func_149730_j() && !block2.func_149730_j())
			{
				b0 = 3;
			}
			world.setBlockMetadataWithNotify(x, y, z, b0, 2);
		}
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack)
	{
		int l = MathHelper.floor_double(entity.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		world.setBlockMetadataWithNotify(x, y, z, l, 2);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int idk, float sideX, float sideY, float sideZ)
	{
		if (!player.isSneaking())
		{
			player.openGui(ExtremeBlocks.instance, TILE_GENERATOR, world, x, y, z);
			return true;
		}
		return false;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block par5, int par6)
	{
		TileEntityInventory.dropItems(world, x, y, z);
		super.breakBlock(world, x, y, z, par5, par6);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TileEntityGenerator();
	}
}
