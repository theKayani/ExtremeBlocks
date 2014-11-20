package main.com.hk.eb.util;

import java.util.Random;
import main.extremeblocks.ExtremeBlocks;
import main.extremeblocks.Init;
import main.extremeblocks.misc.IPlayerMessage;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCustom extends Block
{
	@SideOnly(Side.CLIENT)
	protected IIcon one;
	@SideOnly(Side.CLIENT)
	protected IIcon two;
	@SideOnly(Side.CLIENT)
	protected IIcon three;
	@SideOnly(Side.CLIENT)
	protected IIcon four;
	@SideOnly(Side.CLIENT)
	protected IIcon five;
	@SideOnly(Side.CLIENT)
	protected IIcon six;
	private boolean normal = true, different;
	private Item itemToDrop;
	private int amountDropped;

	public BlockCustom(Material mat, String name)
	{
		super(mat);
		setBlockName(name);
		setBlockTextureName(Init.MODID + ":" + name.replaceAll(" ", "_").toLowerCase());
		setDrop(this);
		setDroppedAmount(1);
		if (this instanceof INamed)
		{
			LanguageRegistry.addName(this, ((INamed) this).getName());
		}
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

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir)
	{
		super.registerBlockIcons(ir);
		one = ir.registerIcon(Init.MODID + ":one");
		two = ir.registerIcon(Init.MODID + ":two");
		three = ir.registerIcon(Init.MODID + ":three");
		four = ir.registerIcon(Init.MODID + ":four");
		five = ir.registerIcon(Init.MODID + ":five");
		six = ir.registerIcon(Init.MODID + ":six");
	}

	public BlockCustom setBounds(float minX, float minY, float minZ, float maxX, float maxY, float maxZ)
	{
		setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
		return this;
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

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int idk, float sideX, float sideY, float sideZ)
	{
		TileEntity te = world.getTileEntity(x, y, z);
		boolean clicked = false;
		if (te != null && te instanceof IPlayerMessage)
		{
			clicked = ((IPlayerMessage) te).onClickedOn(player);
		}
		return clicked;
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
}
