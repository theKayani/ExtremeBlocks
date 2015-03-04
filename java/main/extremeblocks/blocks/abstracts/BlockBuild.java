package main.extremeblocks.blocks.abstracts;

import java.util.Random;
import main.com.hk.eb.util.Builder;
import main.com.hk.eb.util.IInfo;
import main.extremeblocks.Init;
import main.extremeblocks.worldgen.GenManager.Gen;
import main.extremeblocks.worldgen.Generation;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class BlockBuild extends Block implements IInfo
{
	private final Class<? extends Generation> clazz;
	private final String name;

	public BlockBuild(Class<? extends Generation> clazz)
	{
		super(Material.rock);
		name = clazz.getAnnotation(Gen.class).name();
		this.clazz = clazz;
		setBlockName(name);
		setBlockTextureName(Init.MODID + ":" + name.replaceAll(" ", "_").toLowerCase() + "_build");
		setCreativeTab(Init.tab_mainBlocks);
		setStepSound(soundTypeStone);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int idk, float sideX, float sideY, float sideZ)
	{
		try
		{
			clazz.newInstance().generateStructure(new Builder(world, x, y, z));
		}
		catch (Exception e)
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

	@Override
	public String getInfo()
	{
		return "This block is only allowed in creative. When right clicked, it will generate a " + name + " at the position. Not checking whether it could generate there! Useful for checking what's out there!";
	}

	@Override
	public Elements getElements()
	{
		return new Elements(true, false);
	}
}
