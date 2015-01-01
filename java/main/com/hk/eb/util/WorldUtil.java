package main.com.hk.eb.util;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class WorldUtil
{
	private static boolean isAir, canStay, randomMetadata;

	public static float partiallyDestroyBlock(EntityLivingBase e, Vector3I vec, float blockDamage)
	{
		float copy = blockDamage;
		BlockIndex index = new BlockIndex(e.worldObj, vec);
		if (index.getHardness() != 0)
		{
			blockDamage += index.getBreakSpeed(e.getHeldItem()) / index.getHardness() / 30F;

			if (blockDamage > 1.0F)
			{
				e.worldObj.destroyBlockInWorldPartially(e.getEntityId(), vec.x, vec.y, vec.z, -1);
				blockDamage = 0;

				e.worldObj.playAuxSFXAtEntity(null, 2001, vec.x, vec.y, vec.z, Block.getIdFromBlock(index.getBlock()) + (index.getMetadata() << 12));

				if (e.getHeldItem() != null)
				{
					e.getHeldItem().getItem().onBlockDestroyed(e.getHeldItem(), e.worldObj, index.getBlock(), vec.x, vec.y, vec.z, e);
				}
			}
			else
			{
				e.worldObj.destroyBlockInWorldPartially(e.getEntityId(), vec.x, vec.y, vec.z, (int) (blockDamage * 10.0F) - 1);
			}
		}
		return blockDamage == copy ? 0.0F : blockDamage;
	}

	public static void createBox(World worldObj, int x, int y, int z, Block block, int length, int width, int height)
	{
		createBox(worldObj, x, y, z, block, length, width, height, 0);
	}

	public static void createBox(World worldObj, int x, int y, int z, Block block, int length, int width, int height, int meta)
	{
		if (MPUtil.isClientSide()) return;
		for (int i = 0; i < height + 1; i++)
		{
			createLayer(worldObj, x, y, z, block, length, width, meta);
		}
	}

	public static void makeColum(World worldObj, int x, int y, int z, Block block, int height)
	{
		makeColum(worldObj, x, y, z, block, height, 0);
	}

	public static void makeColum(World worldObj, int x, int y, int z, Block block, int height, int meta)
	{
		if (MPUtil.isClientSide()) return;
		for (int i = 0; i < height + 1; i++)
		{
			setBlock(worldObj, x, y + i, z, block, meta);
		}
	}

	public static void fillWithBlocks(World worldObj, int x, int y, int z, Block block, int radius)
	{
		fillWithBlocks(worldObj, x, y, z, block, radius, 0);
	}

	public static void fillWithBlocks(World worldObj, int x, int y, int z, Block block, int radius, int meta)
	{
		if (MPUtil.isClientSide()) return;
		radius *= 2;
		int i, j, k;
		for (i = -radius; i < radius + 1; i++)
		{
			for (j = -radius; j < radius + 1; j++)
			{
				for (k = -radius; k < radius + 1; k++)
				{
					setBlock(worldObj, x + i, y + j, z + k, block, meta);
				}
			}
		}
	}

	public static void createLayer(World worldObj, int x, int y, int z, Block block, int length, int width)
	{
		createLayer(worldObj, x, y, z, block, length, width, 0);
	}

	public static void createLayer(World worldObj, int x, int y, int z, Block block, int length, int width, int meta)
	{
		if (MPUtil.isClientSide()) return;
		int i, j;
		length /= 2;
		width /= 2;
		for (i = -length; i < length + 1; i++)
		{
			for (j = -width; j < width + 1; j++)
			{
				setBlock(worldObj, x + i, y, z + j, block, meta);
			}
		}
	}

	public static void setBlockWithItemStack(World worldObj, int x, int y, int z, Block block, ItemStack... items)
	{
		setBlockWithItemStack(worldObj, x, y, z, block, 0, items);
	}

	public static void setBlockWithItemStack(World worldObj, int x, int y, int z, Block block, int meta, ItemStack... items)
	{
		if (MPUtil.isClientSide()) return;
		setBlock(worldObj, x, y, z, block, meta);
		if (worldObj.getTileEntity(x, y, z) instanceof IInventory)
		{
			StackHelper.addToInv((IInventory) worldObj.getTileEntity(x, y, z), items);
		}
	}

	public static void setBlockWithItemStackInSlot(World worldObj, int x, int y, int z, Block block, ItemStack item, int slot)
	{
		setBlockWithItemStackInSlot(worldObj, x, y, z, block, 0, item, slot);
	}

	public static void setBlockWithItemStackInSlot(World worldObj, int x, int y, int z, Block block, int meta, ItemStack item, int slot)
	{
		if (MPUtil.isClientSide()) return;
		setBlock(worldObj, x, y, z, block, meta);
		if (worldObj.getTileEntity(x, y, z) instanceof IInventory)
		{
			StackHelper.addToInvSlot((IInventory) worldObj.getTileEntity(x, y, z), item, slot);
		}
	}

	public static void setBlock(World worldObj, int x, int y, int z, Block block)
	{
		setBlock(worldObj, x, y, z, block, 0);
	}

	public static void setBlock(World worldObj, int x, int y, int z, Block block, int meta)
	{
		if (MPUtil.isClientSide()) return;
		boolean isAirAndStay = isAir && canStay && worldObj.isAirBlock(x, y, z) && block.canBlockStay(worldObj, x, y, z);
		boolean isAirAndNotStay = isAir && !canStay && worldObj.isAirBlock(x, y, z);
		boolean isNotAirAndStay = canStay && !isAir && block.canBlockStay(worldObj, x, y, z);
		boolean isNotAirAndNotStay = !isAir && !canStay;
		if (isAirAndStay || isAirAndNotStay || isNotAirAndStay || isNotAirAndNotStay)
		{
			worldObj.setBlock(x, y, z, block, randomMetadata ? Rand.getRandomMetadataOf(Item.getItemFromBlock(block)) : meta, 2);
		}
	}

	public static void enableCanStay()
	{
		canStay = true;
	}

	public static void disableCanStay()
	{
		canStay = false;
	}

	public static void enableIsAir()
	{
		isAir = true;
	}

	public static void disableIsAir()
	{
		isAir = false;
	}

	public static void enableRandomMetadata()
	{
		randomMetadata = true;
	}

	public static void disableRandomMetadata()
	{
		randomMetadata = false;
	}
}
