package main.extremeblocks.blocks.abstractblocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import main.com.hk.testing.util.JavaHelp;
import main.extremeblocks.ExtremeBlocks;
import main.extremeblocks.Init;
import main.extremeblocks.blocks.tileentities.TileEntityStorage;
import main.extremeblocks.util.BlockType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class BlockStorage extends BlockContainer implements ITileEntityProvider
{
	public static final boolean debug = true;
	private static int autoID = 0;
	public int id, storageSlots, ySize, xSize;
	public String containerName, guiTexturePath, name;
	public BlockType type;
	private static HashMap<Integer, BlockStorage> ids = JavaHelp.newHashMap();
	public static ArrayList<BlockStorage> blocks = JavaHelp.newArrayList();

	public BlockStorage(String name, Material mat, CreativeTabs tab, int storageSlots, String containerName, String guiTexturePath, BlockType type)
	{
		super(mat);
		this.setHardness(2.0F);
		this.setBlockType(type);
		this.setBlockName(name);
		this.setCreativeTab(tab);
		this.setStorageSlots(storageSlots);
		this.setName(name);
		this.setContainerName(containerName);
		this.setTexture(name.toLowerCase());
		this.setGuiTexturePath(guiTexturePath);
		this.setXSize(176);
		this.setYSize(166);
		this.setTickRandomly(true);
		this.id = autoID++;
		ids.put(this.id, this);
		blocks.add(this);

		ExtremeBlocks.blocks.add(this);
	}

	public BlockStorage(String name, Material material, BlockType type)
	{
		this(name, material, getCreativeTab(), 9, name, "textures/gui/container/dispenser.png", type);
	}

	@Override
	public int tickRate(World p_149738_1_)
	{
		return 20;
	}

	public BlockStorage setXSize(int xSize)
	{
		this.xSize = xSize;
		return this;
	}

	public BlockStorage setYSize(int ySize)
	{
		this.ySize = ySize;
		return this;
	}

	protected static CreativeTabs getCreativeTab()
	{
		return Init.tab_mainBlocks;
	}

	public BlockStorage setBounds(float minX, float minY, float minZ, float maxX, float maxY, float maxZ)
	{
		this.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
		return this;
	}

	public BlockStorage setBlockType(BlockType type)
	{
		this.type = type;
		return this;
	}

	public BlockStorage setName(String name)
	{
		this.name = name;
		return this;
	}

	public BlockStorage setTexture(String textureName)
	{
		setBlockTextureName(Init.MODID + ":" + textureName);
		return this;
	}

	public BlockStorage setContainerName(String containerName)
	{
		this.containerName = containerName;
		return this;
	}

	public BlockStorage setGuiTexturePath(String guiTexturePath)
	{
		this.guiTexturePath = guiTexturePath;
		return this;
	}

	public BlockStorage setStorageSlots(int storageSlots)
	{
		this.storageSlots = storageSlots;
		return this;
	}

	public abstract Slot[] addSlotsToContainer(TileEntityStorage te);

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float what, float these, float are)
	{
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if (tileEntity == null || player.isSneaking())
		{
			return false;
		}
		player.openGui(ExtremeBlocks.instance, 1, world, x, y, z);
		return true;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block par5, int par6)
	{
		dropItems(world, x, y, z);
		super.breakBlock(world, x, y, z, par5, par6);
	}

	private void dropItems(World world, int x, int y, int z)
	{
		Random rand = new Random();
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
		return new TileEntityStorage(this, storageSlots, guiTexturePath, containerName);
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		boolean sup = super.canPlaceBlockAt(world, x, y, z);
		return sup && this.canBlockStay(world, x, y, z);
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block lol)
	{
		if (!this.canBlockStay(world, x, y, z))
		{
			this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
			world.setBlockToAir(x, y, z);
		}
	}

	@Override
	public boolean canBlockStay(World world, int x, int y, int z)
	{
		return this.type.shouldDrop() ? world.getBlock(x, y - 1, z).getMaterial().isSolid() : super.canBlockStay(world, x, y, z);
	}

	public static BlockStorage getByID(int id)
	{
		if (ids.containsKey(id))
		{
			return ids.get(id);
		}
		return null;
	}
}
