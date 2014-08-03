package main.com.hk.testing.util;

import java.util.Random;
import cpw.mods.fml.common.registry.LanguageRegistry;
import main.extremeblocks.ExtremeBlocks;
import main.extremeblocks.util.IPlayerMessage;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCustom extends Block
{
	private boolean normal = true;
	private Item itemToDrop;
	private int amountDropped;

	public BlockCustom(Material mat, String name)
	{
		super(mat);
		this.setBlockName(name);
		this.setBlockTextureName(name);
		setDrop(this);
		setDroppedAmount(1);

		if (this instanceof INamed)
		{
			LanguageRegistry.addName(this, ((INamed) this).getName());
		}

		ExtremeBlocks.blocks.add(this);
	}

	public BlockCustom setBounds(float minX, float minY, float minZ, float maxX, float maxY, float maxZ)
	{
		this.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
		return this;
	}

	public BlockCustom setDrop(Item itemToDrop)
	{
		this.itemToDrop = itemToDrop;
		return this;
	}

	public BlockCustom setDrop(Block blockToDrop)
	{
		return setDrop(Item.getItemFromBlock(blockToDrop));
	}

	public BlockCustom setDroppedAmount(int amountDropped)
	{
		this.amountDropped = amountDropped;
		return this;
	}

	public int quantityDropped(Random rand)
	{
		return amountDropped;
	}

	public Item getItemDropped(int meta, Random rand, int idk)
	{
		return itemToDrop;
	}

	public BlockCustom irregular()
	{
		normal = false;
		return this;
	}

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

	public boolean renderAsNormalBlock()
	{
		return normal;
	}

	public boolean isOpaqueCube()
	{
		return normal;
	}
}
