package main.extremeblocks.blocks.energy;

import main.extremeblocks.client.containers.ContainerEB;
import main.extremeblocks.client.containers.energy.ContainerFuelGenerator;
import main.extremeblocks.client.guis.GuiEB;
import main.extremeblocks.client.guis.energy.GuiFuelGenerator;
import main.extremeblocks.tileentities.energy.TileEntityFuelGenerator;
import main.extremeblocks.tileentities.energy.TileEntityGenerator;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFuelGenerator extends BlockGenerator
{
	public BlockFuelGenerator()
	{
		super("Fuel Generator");
		setInfo("The Fuel generator is the most basic RF generator. The upside to using this might be that it has multiple slots, so you get more for your money! In the Gui, if the red light is not on, you need to power the block with redstone!");
		showRecipe();
		setHardness(1.0F);
	}

	@Override
	public Class<? extends TileEntityGenerator> getTileClass()
	{
		return TileEntityFuelGenerator.class;
	}

	@Override
	public String getTopTexture()
	{
		return "apparatus_casing_top";
	}

	@Override
	public String getFrontTexture()
	{
		return "generator_front";
	}

	@Override
	public String getSideTexture()
	{
		return "generator_vent";
	}

	@SideOnly(Side.CLIENT)
	@Override
	public GuiEB getGui(InventoryPlayer inventory, World world, int x, int y, int z, TileEntity tile)
	{
		return new GuiFuelGenerator(inventory, (TileEntityFuelGenerator) tile);
	}

	@Override
	public ContainerEB getContainer(InventoryPlayer inventory, World world, int x, int y, int z, TileEntity tile)
	{
		return new ContainerFuelGenerator(inventory, (TileEntityFuelGenerator) tile);
	}

}
