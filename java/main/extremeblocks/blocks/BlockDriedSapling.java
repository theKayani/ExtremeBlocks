package main.extremeblocks.blocks;

import main.extremeblocks.ExtremeBlocks;
import main.extremeblocks.Init;
import main.extremeblocks.worldgen.WorldGenDriedTree;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;

public class BlockDriedSapling extends BlockBush
{
	public BlockDriedSapling()
	{
		super();
		this.setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 0.4F * 2.0F, 0.9F);
		this.setBlockName("Dried Sapling");
		this.setBlockTextureName(Init.MODID + ":driedsapling");
		this.setCreativeTab(Init.tab_mainBlocks);
		ExtremeBlocks.blocks.add(this);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float sideX, float sideY, float sideZ)
	{
		if (player.getHeldItem() != null && player.getHeldItem().getItem() == Items.dye)
		{
			(new WorldGenDriedTree()).generate(world, world.rand, x, y, z);
		}
		return true;
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
