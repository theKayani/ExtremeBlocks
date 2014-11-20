package main.extremeblocks.blocks;

import main.com.hk.eb.util.RegistryHelper.OreDict;
import main.extremeblocks.ExtremeBlocks;
import main.extremeblocks.Init;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.EnumPlantType;

@OreDict(values = { "sapling", "driedSapling" })
public class BlockDriedSapling extends BlockBush
{
	public BlockDriedSapling()
	{
		super();
		setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 0.8F, 0.9F);
		setBlockName("Dried Sapling");
		setBlockTextureName(Init.MODID + ":driedsapling");
		setCreativeTab(Init.tab_mainBlocks);
		setStepSound(soundTypeGrass);
		ExtremeBlocks.blocks.add(this);
	}

	@Override
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
