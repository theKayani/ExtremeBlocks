package main.extremeblocks.blocks;

import java.util.Random;
import main.com.hk.testing.util.BlockCustom;
import main.com.hk.testing.util.Rand;
import main.extremeblocks.ExtremeBlocks;
import main.extremeblocks.Init;
import main.extremeblocks.blocks.tileentities.TileEntityPowerEmitter;
import main.extremeblocks.blocks.tileentities.TileEntityPowerReceiver;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockPower extends BlockCustom implements ITileEntityProvider
{
	private final PowerType type;

	public BlockPower(PowerType type)
	{
		super(Material.rock, "Power " + type.name());
		this.setCreativeTab(Init.tab_mainBlocks);
		this.setBlockTextureName("iron_block");

		this.type = type;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float what, float these, float are)
	{
		TileEntity tileEntity = world.getTileEntity(x, y, z);

		if (type == PowerType.EMITTER)
		{
			if (tileEntity == null || player.isSneaking())
			{
				return super.onBlockActivated(world, x, y, z, player, metadata, what, these, are);
			}

			player.openGui(ExtremeBlocks.instance, 2, world, x, y, z);
			return true;
		}
		return super.onBlockActivated(world, x, y, z, player, metadata, what, these, are);
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block par5, int par6)
	{
		dropItems(world, x, y, z);
		super.breakBlock(world, x, y, z, par5, par6);
	}

	private void dropItems(World world, int x, int y, int z)
	{
		Random rand = new Random(Rand.nextLong());

		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if (!(tileEntity instanceof IInventory))
		{
			return;
		}
		IInventory inventory = (IInventory) tileEntity;

		for (int i = 0; i < inventory.getSizeInventory(); i++)
		{
			ItemStack item = inventory.getStackInSlot(i);

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
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2)
	{
		switch (type)
		{
			case EMITTER:
				return new TileEntityPowerEmitter();
			case RECEIVER:
				return new TileEntityPowerReceiver();
			default:
				return null;
		}
	}

	public enum PowerType
	{
		EMITTER, RECEIVER;
	}
}
