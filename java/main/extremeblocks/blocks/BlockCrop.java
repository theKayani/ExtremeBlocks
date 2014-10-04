package main.extremeblocks.blocks;

import java.util.ArrayList;
import java.util.Random;
import main.extremeblocks.ExtremeBlocks;
import main.extremeblocks.Init;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCrop extends BlockBush implements IGrowable
{
	public final Item seeds, drop;
	public final IIcon[] cropIcons = new IIcon[8];

	public BlockCrop(String name, Item seeds, Item drop)
	{
		this.setBlockName(name);
		this.setBlockTextureName(Init.MODID + ":" + name.toLowerCase().replaceAll(" ", "_"));
		float f = 0.5F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
		this.setCreativeTab((CreativeTabs) null);
		this.setHardness(0.0F);
		this.setStepSound(soundTypeGrass);
		this.disableStats();
		this.seeds = seeds;
		this.drop = drop;
		ExtremeBlocks.blocks.add(this);
	}

	@Override
	public int getRenderType()
	{
		return 6;
	}

	@Override
	protected boolean canPlaceBlockOn(Block block)
	{
		return block == Blocks.farmland;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block b)
	{
		super.onNeighborBlockChange(world, x, y, z, b);
		this.checkAndDropBlock(world, x, y, z);
	}

	@Override
	protected void checkAndDropBlock(World p_149855_1_, int p_149855_2_, int p_149855_3_, int p_149855_4_)
	{
		if (!this.canBlockStay(p_149855_1_, p_149855_2_, p_149855_3_, p_149855_4_))
		{
			this.dropBlockAsItem(p_149855_1_, p_149855_2_, p_149855_3_, p_149855_4_, p_149855_1_.getBlockMetadata(p_149855_2_, p_149855_3_, p_149855_4_), 0);
			p_149855_1_.setBlock(p_149855_2_, p_149855_3_, p_149855_4_, getBlockById(0), 0, 2);
		}
	}

	@Override
	public boolean canBlockStay(World p_149718_1_, int p_149718_2_, int p_149718_3_, int p_149718_4_)
	{
		return p_149718_1_.getBlock(p_149718_2_, p_149718_3_ - 1, p_149718_4_).canSustainPlant(p_149718_1_, p_149718_2_, p_149718_3_ - 1, p_149718_4_, ForgeDirection.UP, this);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
	{
		return null;
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
	public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z)
	{
		return EnumPlantType.Crop;
	}

	@Override
	public Block getPlant(IBlockAccess world, int x, int y, int z)
	{
		return this;
	}

	@Override
	public int getPlantMetadata(IBlockAccess world, int x, int y, int z)
	{
		return world.getBlockMetadata(x, y, z);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir)
	{
		for (int i = 0; i < cropIcons.length; i++)
		{
			cropIcons[i] = ir.registerIcon(this.getTextureName() + "_" + i);
		}
	}

	@Override
	public void func_149853_b(World world, Random rand, int x, int y, int z)
	{
		this.growCrop(world, x, y, z);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		super.updateTick(world, x, y, z, random);

		if (world.getBlockLightValue(x, y + 1, z) >= 9)
		{
			int l = world.getBlockMetadata(x, y, z);

			if (l < 7)
			{
				float f = this.checkSurrounding(world, x, y, z);

				if (random.nextInt((int) (25.0F / f) + 1) == 0)
				{
					++l;
					world.setBlockMetadataWithNotify(x, y, z, l, 2);
				}
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		if (meta < 0 || meta > 7)
		{
			meta = 7;
		}

		return this.cropIcons[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getItem(World world, int x, int y, int z)
	{
		return getSeeds();
	}

	protected Item getSeeds()
	{
		return seeds;
	}

	protected Item getCrops()
	{
		return drop;
	}

	@Override
	public void dropBlockAsItemWithChance(World world, int x, int y, int z, int a, float b, int c)
	{
		super.dropBlockAsItemWithChance(world, x, y, z, a, b, 0);
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int fortune)
	{
		return meta == 7 ? this.getCrops() : this.getSeeds();
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
	{
		ArrayList<ItemStack> ret = super.getDrops(world, x, y, z, metadata, fortune);

		if (metadata >= 7)
		{
			for (int i = 0; i < 3 + fortune; ++i)
			{
				if (world.rand.nextInt(15) <= metadata)
				{
					ret.add(new ItemStack(this.getSeeds(), 1, 0));
				}
			}
		}

		return ret;
	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	@Override
	public int quantityDropped(Random rand)
	{
		return 1;
	}

	private float checkSurrounding(World world, int x, int y, int z)
	{
		float f = 1.0F;
		Block block = world.getBlock(x, y, z - 1);
		Block block1 = world.getBlock(x, y, z + 1);
		Block block2 = world.getBlock(x - 1, y, z);
		Block block3 = world.getBlock(x + 1, y, z);
		Block block4 = world.getBlock(x - 1, y, z - 1);
		Block block5 = world.getBlock(x + 1, y, z - 1);
		Block block6 = world.getBlock(x + 1, y, z + 1);
		Block block7 = world.getBlock(x - 1, y, z + 1);
		boolean flag = block2 == this || block3 == this;
		boolean flag1 = block == this || block1 == this;
		boolean flag2 = block4 == this || block5 == this || block6 == this || block7 == this;

		for (int l = x - 1; l <= x + 1; ++l)
		{
			for (int i1 = z - 1; i1 <= z + 1; ++i1)
			{
				float f1 = 0.0F;

				if (world.getBlock(l, y - 1, i1).canSustainPlant(world, l, y - 1, i1, ForgeDirection.UP, this))
				{
					f1 = 1.0F;

					if (world.getBlock(l, y - 1, i1).isFertile(world, l, y - 1, i1))
					{
						f1 = 3.0F;
					}
				}

				if (l != x || i1 != z)
				{
					f1 /= 4.0F;
				}

				f += f1;
			}
		}

		if (flag2 || flag && flag1)
		{
			f /= 2.0F;
		}

		return f;
	}

	public void growCrop(World world, int x, int y, int z)
	{
		int l = world.getBlockMetadata(x, y, z) + MathHelper.getRandomIntegerInRange(world.rand, 2, 5);

		if (l > 7)
		{
			l = 7;
		}

		world.setBlockMetadataWithNotify(x, y, z, l, 2);
	}

	@Override
	public boolean func_149851_a(World world, int x, int y, int z, boolean idk)
	{
		return world.getBlockMetadata(x, y, z) != 7;
	}

	@Override
	public boolean func_149852_a(World world, Random rand, int x, int y, int z)
	{
		return true;
	}

	// 347-808-9555
	// $10 large
	// $4.50 small
}
