package extremeblocks.blocks.abstractblocks;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extremeblocks.Init;

public class BlockFakeFloor extends Block
{
	private final int id;
	private final Block parent;
	private static int ID;

	public BlockFakeFloor(Block parent)
	{
		super(parent.getMaterial());
		this.setCreativeTab(Init.tab_fakeFloors);
		this.setBlockName("[" + ID++ + "]Fake_" + parent.getLocalizedName());

		this.id = ID;
		this.parent = parent;
	}

	public void registerBlockIcons(IIconRegister ir)
	{
		this.blockIcon = this.parent.getBlockTextureFromSide(0);
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
		return "[" + id + "]Fake " + parent.getLocalizedName() + " Floor";
	}

	public String getLocalizedName()
	{
		return getUnlocalizedName();
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return parent.getIcon(side, meta);
	}
}
