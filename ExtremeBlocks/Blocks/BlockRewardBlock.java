package extremeblocks.blocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import com.hk.testing.util.BlockCustom;
import extremeblocks.Init;
import extremeblocks.blocks.tileentities.TileEntityRewardBlock;

public class BlockRewardBlock extends BlockCustom implements ITileEntityProvider
{
	public BlockRewardBlock()
	{
		super(Material.iron, "Reward Block");
		this.setCreativeTab(Init.tab_misc);
		this.setBlockTextureName(Init.MODID + ":reward_block");
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int idk, float sideX, float sideY, float sideZ)
	{
		return ((TileEntityRewardBlock) world.getTileEntity(x, y, z)).blockClicked(world, player);
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2)
	{
		return new TileEntityRewardBlock();
	}
}
