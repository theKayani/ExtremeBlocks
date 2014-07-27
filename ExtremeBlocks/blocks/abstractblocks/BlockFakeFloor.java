package main.extremeblocks.blocks.abstractblocks;

import java.util.List;
import main.com.hk.testing.util.MPUtil;
import main.extremeblocks.Init;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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

	public BlockFakeFloor(Block parent)
	{
		super(parent.getMaterial());
		this.setCreativeTab(Init.tab_fakeFloors);
		this.setBlockName("[" + ID++ + "]Fake " + parent.getLocalizedName());

		this.id = ID;
		this.parent = parent;

		LanguageRegistry.addName(this, "Fake " + parent.getLocalizedName() + " Floor");
	}

	public void registerBlockIcons(IIconRegister ir)
	{
	}

	public int damageDropped(int p_149692_1_)
	{
		return parent.damageDropped(p_149692_1_);
	}

	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
	{
		return null;
	}

	public boolean isOpaqueCube()
	{
		return false;
	}

	public boolean renderAsNormalBlock()
	{
		return false;
	}

	public String getUnlocalizedName()
	{
		return "Fake " + parent.getLocalizedName() + " Floor[" + id + "]";
	}

	public String getLocalizedName()
	{
		return "Fake " + parent.getLocalizedName() + " Floor";
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return parent.getIcon(side, meta);
	}
}
