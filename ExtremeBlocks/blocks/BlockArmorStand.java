package main.extremeblocks.blocks;

import java.util.ArrayList;
import main.extremeblocks.Init;
import main.extremeblocks.blocks.abstractblocks.BlockStorage;
import main.extremeblocks.blocks.tileentities.TileEntityStorage;
import main.extremeblocks.util.BlockType;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.inventory.Slot;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockArmorStand extends BlockStorage
{
	public BlockArmorStand()
	{
		super("Armor Stand", Material.wood, getCreativeTab(), 4, "Armor Stand", Init.MODID + ":textures/gui/armorstand.png", BlockType.BARREL);
		this.setBounds(0.4F, 0.0F, 0.4F, 0.6F, 2.0F, 0.6F);
		this.setHardness(1.0F);
		this.setBlockTextureName("log_oak");
	}

	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		return canPlaceAt(world, x, y, z);
	}

	public boolean renderAsNormalBlock()
	{
		return false;
	}

	public boolean isOpaqueCube()
	{
		return false;
	}

	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		if (!this.canPlaceBlockAt(world, x, y, z))
		{
			this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
			world.setBlockToAir(x, y, z);
		}
	}

	public void setBlockBoundsBasedOnState(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_)
	{
		this.setBlockBounds(0.4F, 0.0F, 0.4F, 0.6F, 2.0F, 0.6F);
	}

	public void setBlockBoundsForItemRender()
	{
		this.setBlockBounds(0.4F, 0.0F, 0.4F, 0.6F, 1.0F, 0.6F);
	}

	private boolean canPlaceAt(World world, int x, int y, int z)
	{
		return world.getBlock(x, y + 1, z).isReplaceable(world, x, y + 1, z) && world.getBlock(x, y - 1, z).getMaterial().isSolid();
	}

	@Override
	public Slot[] addSlotsToContainer(TileEntityStorage te)
	{
		ArrayList<Slot> slots = new ArrayList<Slot>();

		for (int i = 0; i < 4; ++i)
		{
			slots.add(new Slot(te, i, 53 + i * 18, 37));
		}

		return slots.toArray(new Slot[0]);
	}
}
