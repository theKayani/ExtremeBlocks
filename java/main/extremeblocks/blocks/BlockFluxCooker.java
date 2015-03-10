package main.extremeblocks.blocks;

import main.extremeblocks.blocks.energy.BlockMachine;
import main.extremeblocks.client.containers.ContainerEB;
import main.extremeblocks.client.containers.energy.ContainerFluxCooker;
import main.extremeblocks.client.guis.GuiEB;
import main.extremeblocks.client.guis.energy.GuiFluxCooker;
import main.extremeblocks.tileentities.energy.TileEntityFluxCooker;
import main.extremeblocks.tileentities.energy.TileEntityMachine;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFluxCooker extends BlockMachine
{
	public BlockFluxCooker()
	{
		super("Flux Cooker");
		setHardness(1.5F);
		setInfo("This block is the Flux Cooker. This block is similar to a furnace but instead of using a fuel, it uses Redstone Flux. It uses 1024 RF per item smelted. It can also be upgraded to work faster using the Furnace Upgrades!");
		showRecipe();
	}

	@Override
	public Class<? extends TileEntityMachine> getTileClass()
	{
		return TileEntityFluxCooker.class;
	}

	@Override
	public String getTopTexture()
	{
		return "apparatus_casing_top";
	}

	@Override
	public String getFrontTexture()
	{
		return "flux_cooker_front";
	}

	@Override
	public String getSideTexture()
	{
		return "flux_cooker_side";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public GuiEB getGui(InventoryPlayer inventory, World world, int x, int y, int z, TileEntity tile)
	{
		return new GuiFluxCooker(inventory, (TileEntityFluxCooker) tile);
	}

	@Override
	public ContainerEB getContainer(InventoryPlayer inventory, World world, int x, int y, int z, TileEntity tile)
	{
		return new ContainerFluxCooker(inventory, (TileEntityFluxCooker) tile);
	}

}
