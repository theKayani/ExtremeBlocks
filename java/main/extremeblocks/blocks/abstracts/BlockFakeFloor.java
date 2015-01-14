package main.extremeblocks.blocks.abstracts;

import main.extremeblocks.Init;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFakeFloor extends Block
{
	private final int id;
	private final Block parent;
	private static int ID;

	@SuppressWarnings("deprecation")
	public BlockFakeFloor(Block parent)
	{
		super(parent.getMaterial());
		setCreativeTab(Init.tab_fakeFloors);
		setBlockName("[" + ID++ + "]Fake " + parent.getLocalizedName());
		id = ID;
		this.parent = parent;
		LanguageRegistry.addName(this, "Fake " + parent.getLocalizedName() + " Floor");
	}

	@Override
	public void registerBlockIcons(IIconRegister ir)
	{
	}

	@Override
	public int damageDropped(int p_149692_1_)
	{
		return parent.damageDropped(p_149692_1_);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
	{
		return null;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public String getUnlocalizedName()
	{
		return "Fake " + parent.getLocalizedName() + " Floor[" + id + "]";
	}

	@Override
	public String getLocalizedName()
	{
		return "Fake " + parent.getLocalizedName() + " Floor";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return parent.getIcon(side, meta);
	}
}
