package main.extremeblocks.blocks.abstractblocks;

import java.util.Random;
import main.com.hk.eb.util.Builder;
import main.extremeblocks.Init;
import main.extremeblocks.worldgen.Generation;
import main.extremeblocks.worldgen.GenManager.Gen;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class BlockBuild extends Block
{
	private final Class<? extends Generation> clazz;

	public BlockBuild(Class<? extends Generation> clazz)
	{
		super(Material.rock);
		setBlockName(clazz.getAnnotation(Gen.class).name());
		setBlockTextureName(Init.MODID + ":" + clazz.getAnnotation(Gen.class).name().replaceAll(" ", "_").toLowerCase() + "_build");
		setHardness(1.0F);
		setCreativeTab(Init.tab_mainBlocks);
		setStepSound(soundTypeStone);
		this.clazz = clazz;
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int idk, float sideX, float sideY, float sideZ)
	{
		try
		{
			clazz.newInstance().generateStructure(new Builder(world, x, y, z));
		}
		catch (InstantiationException e)
		{
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int idk)
	{
		return null;
	}
}
