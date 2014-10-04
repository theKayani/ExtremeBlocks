package main.extremeblocks.blocks;

import java.util.Random;
import main.com.hk.eb.util.BlockCustom;
import main.com.hk.eb.util.MPUtil;
import main.extremeblocks.Init;
import main.extremeblocks.tileentities.TileEntityPlate;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockPlate extends BlockCustom implements ITileEntityProvider
{
	public BlockPlate()
	{
		super(Material.wood, "Plate");
		this.setHardness(0.25F);
		this.setCreativeTab(Init.tab_mainBlocks);
		this.setBlockTextureName("planks_oak");
		this.setBlockBounds(0.05F, 0.0F, 0.05F, 0.95F, 0.15F, 0.95F);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
	{
		return null;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int chance)
	{
		Random rand = new Random(world.rand.nextLong());
		TileEntityPlate te = (TileEntityPlate) world.getTileEntity(x, y, z);

		if (MPUtil.isServerSide())
		{
			ItemStack item = te.inventory[0];
			if (item != null && item.stackSize > 0)
			{
				float rx = rand.nextFloat() * 0.8F + 0.1F;
				float ry = rand.nextFloat() * 0.8F + 0.1F;
				float rz = rand.nextFloat() * 0.8F + 0.1F;
				EntityItem entityItem = new EntityItem(world, x + rx, y + ry, z + rz, item.copy());
				if (item.hasTagCompound())
				{
					entityItem.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
				}
				float factor = 0.05F;
				entityItem.motionX = rand.nextGaussian() * factor;
				entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
				entityItem.motionZ = rand.nextGaussian() * factor;
				world.spawnEntityInWorld(entityItem);
				item.stackSize = 0;
			}
		}

		super.breakBlock(world, x, y, z, block, chance);
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int idk)
	{
		return Item.getItemFromBlock(this);
	}

	@Override
	public int getRenderType()
	{
		return -1;
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
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int idk, float sideX, float sideY, float sideZ)
	{
		TileEntityPlate te = (TileEntityPlate) world.getTileEntity(x, y, z);
		if (te.inventory[0] == null && player.getHeldItem() != null)
		{
			te.inventory[0] = player.getHeldItem().copy();
			player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
		}
		else if (te.inventory[0] != null)
		{
			player.inventory.addItemStackToInventory(te.inventory[0]);
			te.inventory[0] = null;
		}
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TileEntityPlate();
	}
}
