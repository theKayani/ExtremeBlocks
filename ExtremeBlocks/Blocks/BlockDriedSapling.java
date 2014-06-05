package extremeblocks.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import extremeblocks.ExtremeBlocks;
import extremeblocks.Init;

public class BlockDriedSapling extends BlockBush
{
	public BlockDriedSapling()
	{
		this.setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 0.4F * 2.0F, 0.9F);
		this.setBlockName("Dried Sapling");
		this.setBlockTextureName(Init.MODID + ":driedsapling");
		this.setCreativeTab(Init.tab_misc);

		ExtremeBlocks.blocks.add(this);
	}

	public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
	{
		return false;
	}

	public boolean canPlaceBlockOn(Block block)
	{
		return super.canPlaceBlockOn(block) || block == Blocks.sand;
	}

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z)
	{
		return EnumPlantType.Desert;
	}
}
