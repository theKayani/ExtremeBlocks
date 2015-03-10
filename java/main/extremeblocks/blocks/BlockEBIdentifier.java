package main.extremeblocks.blocks;

import main.extremeblocks.Init;
import main.extremeblocks.blocks.abstracts.BlockGui;
import main.extremeblocks.client.containers.ContainerEBIdentifier;
import main.extremeblocks.client.guis.GuiEBIdentifier;
import main.extremeblocks.tileentities.TileEntityEBIdentifier;
import main.extremeblocks.tileentities.TileEntityInventory;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockEBIdentifier extends BlockGui
{
	@SideOnly(Side.CLIENT)
	private IIcon topIcon;

	public BlockEBIdentifier()
	{
		super("Extreme Blocks Identifier");
		setHardness(1.5F);
		setInfo("This block is used to identify things in the Extreme Blocks Mod. It could be an alternative to the guide as it's easier to find things!");
		showRecipe();
	}

	@Override
	public Class<? extends TileEntityInventory> getTileClass()
	{
		return TileEntityEBIdentifier.class;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public GuiContainer getGui(InventoryPlayer inventory, World world, int x, int y, int z, TileEntity tile)
	{
		return new GuiEBIdentifier(inventory, (TileEntityEBIdentifier) tile);
	}

	@Override
	public Container getContainer(InventoryPlayer inventory, World world, int x, int y, int z, TileEntity tile)
	{
		return new ContainerEBIdentifier(inventory, (TileEntityEBIdentifier) tile);
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		return side == 0 || side == 1 ? topIcon : blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg)
	{
		blockIcon = reg.registerIcon(Init.MODID + ":eb_identifier_side");
		topIcon = reg.registerIcon(Init.MODID + ":eb_identifier_top");
	}
}
