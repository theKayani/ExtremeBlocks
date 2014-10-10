package main.extremeblocks.blocks;

import main.com.hk.eb.util.BlockCustom;
import main.extremeblocks.ExtremeBlocks;
import main.extremeblocks.GuiIDs;
import main.extremeblocks.Init;
import main.extremeblocks.tileentities.TileEntityCharger;
import main.extremeblocks.tileentities.TileEntityInventory;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCharger extends BlockCustom implements ITileEntityProvider, GuiIDs
{
	public BlockCharger()
	{
		super(Material.iron, "Charger");
		setHardness(2.0F);
		setBlockTextureName(Init.MODID + ":charger");
		setCreativeTab(Init.tab_mainBlocks);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int idk, float sideX, float sideY, float sideZ)
	{
		if (!player.isSneaking())
		{
			player.openGui(ExtremeBlocks.instance, TILE_CHARGER, world, x, y, z);
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
		return new TileEntityCharger();
	}
}
