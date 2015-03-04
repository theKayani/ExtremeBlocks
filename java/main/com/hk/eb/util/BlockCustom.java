package main.com.hk.eb.util;

import java.util.Random;
import main.extremeblocks.ExtremeBlocks;
import main.extremeblocks.Init;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockCustom extends Block implements IInfo, IInitialization
{
	private boolean normal = true, different;
	private Item itemToDrop;
	private int amountDropped;
	private String info;
	private boolean showRecipe;
	private final String name;
	protected Class<? extends TileEntity> teClass;

	public BlockCustom(Material mat, String name)
	{
		super(mat);
		this.name = name;
		setBlockName(name);
		setBlockTextureName(Init.MODID + ":" + name.replaceAll(" ", "_").toLowerCase());
		setDrop(this);
		setDroppedAmount(1);
		setHardness(1.0F);
		if (mat == Material.iron)
		{
			setStepSound(soundTypeMetal);
		}
		if (mat == Material.rock)
		{
			setStepSound(soundTypeStone);
		}
		if (mat == Material.ground)
		{
			setStepSound(soundTypeGravel);
		}
		if (mat == Material.grass)
		{
			setStepSound(soundTypeGrass);
		}
		if (mat == Material.glass)
		{
			setStepSound(soundTypeGlass);
		}
		if (mat == Material.cloth)
		{
			setStepSound(soundTypeCloth);
		}
		if (mat == Material.sand)
		{
			setStepSound(soundTypeSand);
		}
		if (mat == Material.wood)
		{
			setStepSound(soundTypeWood);
		}
		if (mat == Material.plants)
		{
			setStepSound(soundTypeGrass);
		}
		if (mat == Material.piston)
		{
			setStepSound(soundTypePiston);
		}
		ExtremeBlocks.blocks.add(this);
		different = false;
	}

	public BlockCustom setBounds(float minX, float minY, float minZ, float maxX, float maxY, float maxZ)
	{
		setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
		return this;
	}

	public String getName()
	{
		return name;
	}

	public BlockCustom setDrop(Item itemToDrop)
	{
		different = true;
		this.itemToDrop = itemToDrop;
		return this;
	}

	public BlockCustom setDrop(Block blockToDrop)
	{
		different = true;
		return setDrop(Item.getItemFromBlock(blockToDrop));
	}

	public BlockCustom setDroppedAmount(int amountDropped)
	{
		this.amountDropped = amountDropped;
		return this;
	}

	@Override
	public int quantityDropped(Random rand)
	{
		return amountDropped;
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int idk)
	{
		return different ? itemToDrop : Item.getItemFromBlock(this);
	}

	public BlockCustom irregular()
	{
		normal = false;
		return this;
	}

	public Class<? extends TileEntity> getTileClass()
	{
		return teClass;
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		TileEntity te = world.getTileEntity(x, y, z);
		if (te instanceof TileEntityEB)
		{
			((TileEntityEB) te).onBlockAdded();
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		TileEntity te = world.getTileEntity(x, y, z);
		if (te instanceof TileEntityEB)
		{
			((TileEntityEB) te).onEntityCollidedWithBlock(entity);
		}
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta)
	{
		TileEntity te = world.getTileEntity(x, y, z);
		if (te instanceof TileEntityEB)
		{
			((TileEntityEB) te).breakBlock(block, meta);
		}
		super.breakBlock(world, x, y, z, block, meta);
	}

	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z)
	{
		TileEntity te = world.getTileEntity(x, y, z);
		if (te instanceof TileEntityEB) return ((TileEntityEB) te).getLightValue();
		return super.getLightValue(world, x, y, z);
	}

	@Override
	public int getLightOpacity(IBlockAccess world, int x, int y, int z)
	{
		TileEntity te = world.getTileEntity(x, y, z);
		if (te instanceof TileEntityEB) return ((TileEntityEB) te).getLightOpacity();
		return super.getLightOpacity(world, x, y, z);
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack)
	{
		TileEntity te = world.getTileEntity(x, y, z);
		if (te instanceof TileEntityEB)
		{
			((TileEntityEB) te).onBlockPlacedBy(entity, stack);
		}
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		TileEntity te = world.getTileEntity(x, y, z);
		if (te instanceof TileEntityEB)
		{
			((TileEntityEB) te).onNeighborBlockChange(block);
		}
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float sideX, float sideY, float sideZ)
	{
		TileEntity te = world.getTileEntity(x, y, z);
		if (te instanceof TileEntityEB)
		{
			ItemStack stack = player.getHeldItem();
			if (stack != null && stack.getItem() == Init.wrench && ((TileEntityEB) te).canWrench())
			{
				this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
				world.setBlockToAir(x, y, z);
			}
			return ((TileEntityEB) te).onBlockActivated(player, ForgeDirection.getOrientation(side), new Vector3F(sideX, sideY, sideZ));
		}
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return normal;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return normal;
	}

	public BlockCustom setInfo(String info)
	{
		this.info = info;
		return this;
	}

	public BlockCustom setShowRecipe()
	{
		showRecipe = true;
		if (info == null || info.isEmpty())
		{
			setInfo("Nice decoration block. May be used for recipes as well.");
		}
		return this;
	}

	@Override
	public String getInfo()
	{
		return info;
	}

	@Override
	public Elements getElements()
	{
		return new Elements(info != null && !info.isEmpty(), showRecipe);
	}

	@Override
	public void init()
	{
		GameRegistry.registerBlock(this, getClass().getSimpleName() + "|" + getLocalizedName());
		if (teClass != null)
		{
			GameRegistry.registerTileEntity(teClass, teClass.getName());
		}
	}

	@Override
	public void postInit()
	{

	}
}
