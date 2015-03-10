package main.extremeblocks.blocks.abstracts;

import main.com.hk.eb.util.BlockCustom;
import main.extremeblocks.ExtremeBlocks;
import main.extremeblocks.Init;
import main.extremeblocks.tileentities.TileEntityInventory;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class BlockGui extends BlockCustom implements ITileEntityProvider
{
	public BlockGui(String name)
	{
		this(name, Material.iron);
	}

	public BlockGui(String name, Material material)
	{
		super(material, name);
		setHardness(5.0F);
		setCreativeTab(Init.tab_mainBlocks);
		setHarvestLevel("pickaxe", 0);
		tileClass = getTileClass();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int sideHit, float sideX, float sideY, float sideZ)
	{
		if (super.onBlockActivated(world, x, y, z, player, sideHit, sideX, sideY, sideZ)) return true;
		if (!player.isSneaking())
		{
			player.openGui(ExtremeBlocks.instance, -1, world, x, y, z);
			return true;
		}
		return false;
	}

	@Override
	public abstract Class<? extends TileEntityInventory> getTileClass();

	@SideOnly(Side.CLIENT)
	public abstract GuiContainer getGui(InventoryPlayer inventory, World world, int x, int y, int z, TileEntity tile);

	public abstract Container getContainer(InventoryPlayer inventory, World world, int x, int y, int z, TileEntity tile);
}
