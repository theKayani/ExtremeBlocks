package extremeblocks.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import com.hk.testing.util.BlockCustom;
import extremeblocks.Init;

public class BlockCement extends BlockCustom
{
	private final boolean isCement;

	public BlockCement(boolean isCement)
	{
		super(Material.rock, (isCement ? "Cement" : "Plaster") + " Block");
		this.setCreativeTab(Init.tab_mainBlocks);
		this.setBlockTextureName(Init.MODID + ":" + (isCement ? "cement" : "plaster") + "block");

		this.isCement = isCement;
	}

	public boolean canBlockStay(World world, int x, int y, int z)
	{
		return isCement;
	}

	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		return super.canPlaceBlockAt(world, x, y, z) && this.canBlockStay(world, x, y, z);
	}
}
