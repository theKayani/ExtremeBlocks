package extremeblocks.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import com.hk.testing.util.BlockCustom;
import extremeblocks.Init;
import extremeblocks.blocks.tileentities.TileEntityConsole;

public class BlockConsole extends BlockCustom implements ITileEntityProvider
{
	public BlockConsole()
	{
		super(Material.wood, "Console");
		this.setBlockTextureName(Init.MODID + ":Console");
		this.setCreativeTab(Init.tab_misc);
		this.setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 0.4F, 0.9F);
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int idk, float sideX, float sideY, float sideZ)
	{
		return ((TileEntityConsole) world.getTileEntity(x, y, z)).blockClicked(world, player);
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2)
	{
		return new TileEntityConsole();
	}

	public boolean renderAsNormalBlock()
	{
		return false;
	}

	public boolean isOpaqueCube()
	{
		return false;
	}
}