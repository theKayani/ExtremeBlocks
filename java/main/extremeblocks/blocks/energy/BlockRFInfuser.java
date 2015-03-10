package main.extremeblocks.blocks.energy;

import main.extremeblocks.client.containers.ContainerEB;
import main.extremeblocks.client.containers.energy.ContainerRFInfuser;
import main.extremeblocks.client.guis.GuiEB;
import main.extremeblocks.client.guis.GuiRFInfuser;
import main.extremeblocks.tileentities.energy.TileEntityMachine;
import main.extremeblocks.tileentities.energy.TileEntityRFInfuser;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockRFInfuser extends BlockMachine
{
	public BlockRFInfuser()
	{
		super("Redstone Flux Infuser");
		setHardness(1.5F);
		setInfo("This block charges any item that could collect Redstone Flux. This includes the batteries");
		showRecipe();
	}

	@Override
	public Class<? extends TileEntityMachine> getTileClass()
	{
		return TileEntityRFInfuser.class;
	}

	@Override
	public String getTopTexture()
	{
		return "rf_infuser";
	}

	@Override
	public String getFrontTexture()
	{
		return "rf_infuser";
	}

	@Override
	public String getSideTexture()
	{
		return "apparatus_casing_side";
	}

	@Override
	public GuiEB getGui(InventoryPlayer inventory, World world, int x, int y, int z, TileEntity tile)
	{
		return new GuiRFInfuser(inventory, (TileEntityRFInfuser) tile);
	}

	@Override
	public ContainerEB getContainer(InventoryPlayer inventory, World world, int x, int y, int z, TileEntity tile)
	{
		return new ContainerRFInfuser(inventory, (TileEntityRFInfuser) tile);
	}
}
