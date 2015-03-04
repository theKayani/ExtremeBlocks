package main.extremeblocks.blocks;

import main.com.hk.eb.util.BlockCustom;
import main.com.hk.eb.util.MPUtil;
import main.extremeblocks.ExtremeBlocks;
import main.extremeblocks.GuiIDs;
import main.extremeblocks.Init;
import main.extremeblocks.tileentities.TileEntityRevertingStation;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.world.World;

public class BlockRecipeRevert extends BlockCustom implements GuiIDs
{
	public BlockRecipeRevert()
	{
		super(Material.wood, "Recipe Reverting Station");
		setCreativeTab(Init.tab_mainBlocks);
		setBlockTextureName(Init.MODID + ":recipe_reverter");
		teClass = TileEntityRevertingStation.class;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block par5, int par6)
	{
		MPUtil.dropItemAsEntity(world, x, y, z, true, ((IInventory) world.getTileEntity(x, y, z)).getStackInSlot(0));
		super.breakBlock(world, x, y, z, par5, par6);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int idk, float sideX, float sideY, float sideZ)
	{
		if (!player.isSneaking())
		{
			player.openGui(ExtremeBlocks.instance, TILE_REVERTING_STATION, world, x, y, z);
			return true;
		}
		return false;
	}
}
