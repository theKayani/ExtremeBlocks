package extremeblocks.blocks.abstractblocks;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import com.hk.testing.util.RegistryHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extremeblocks.Init;

public class BlockLightedBlock extends Block
{
	private final Block parent;
	private BlockLightedBlock opposite;
	private final boolean isLighted;
	private static int ID;
	private int id;

	private BlockLightedBlock(Block parent, boolean isLighted)
	{
		super(Material.wood);
		this.setBlockName((isLighted ? "Lighted_" : "Off_") + parent.getLocalizedName() + "[" + ID++ + "]");
		this.setHardness(0.3F);

		if (isLighted)
		{
			this.setCreativeTab(Init.tab_lightedBlocks);
		}

		this.setLightLevel(isLighted ? 0.9375F : 0.0F);

		this.id = ID;
		this.isLighted = isLighted;
		this.parent = parent;
	}

	public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_)
	{
		return Item.getItemFromBlock(isLighted ? this : opposite);
	}

	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return parent.getIcon(side, meta);
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int idk, float sideX, float sideY, float sideZ)
	{
		if (isLighted)
		{
			for (int i = 0; i < lightedBlocks.size(); i++)
			{
				if (parent == lightedBlocks.get(i).parent)
				{
					world.setBlock(x, y, z, offBlocks.get(i));
				}
			}
			return true;
		}
		else
		{
			for (int i = 0; i < offBlocks.size(); i++)
			{
				if (parent == offBlocks.get(i).parent)
				{
					world.setBlock(x, y, z, lightedBlocks.get(i));
				}
			}
			return true;
		}
	}

	public void registerBlockIcons(IIconRegister ir)
	{
		this.blockIcon = this.parent.getBlockTextureFromSide(0);
	}

	public static void createPair(Block parent)
	{
		BlockLightedBlock lighted = new BlockLightedBlock(parent, true);
		BlockLightedBlock off = new BlockLightedBlock(parent, false);
		off.opposite = lighted;
		lighted.opposite = off;

		lightedBlocks.add(lighted);
		offBlocks.add(off);

		// MPUtil.addRecipe(new ItemStack(lighted), " T ", "TBT", " T ", 'T',
		// Blocks.torch, 'B', parent);

		RegistryHelper.register(lighted);
		RegistryHelper.register(off);
	}

	public String getUnlocalizedName()
	{
		return "[" + id + "]Lighted " + parent.getLocalizedName();
	}

	public String getLocalizedName()
	{
		return getUnlocalizedName();
	}

	@SideOnly(Side.CLIENT)
	public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_)
	{
		return Item.getItemFromBlock(isLighted ? this : opposite);
	}

	private static ArrayList<BlockLightedBlock> lightedBlocks = new ArrayList<BlockLightedBlock>();
	private static ArrayList<BlockLightedBlock> offBlocks = new ArrayList<BlockLightedBlock>();
}
