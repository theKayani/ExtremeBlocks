package main.extremeblocks.blocks.abstracts;

import main.com.hk.eb.util.BlockCustom;
import main.extremeblocks.Init;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSided extends BlockCustom
{
	private final String[] textures = new String[6];
	@SideOnly(Side.CLIENT)
	private final IIcon[] textureIcons = new IIcon[6];

	public BlockSided(Material mat, String name)
	{
		super(mat, name);
		setCreativeTab(Init.tab_mainBlocks);
	}

	public BlockSided setTexture(String textureName, int... sides)
	{
		for (int side : sides)
		{
			if (side >= 0 && side < 6)
			{
				textures[side] = textureName;
			}
			else throw new IllegalArgumentException(side + " must be between 0 and 6");
		}
		return this;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return textureIcons[side];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister reg)
	{
		for (int i = 0; i < 6; i++)
		{
			textureIcons[i] = reg.registerIcon(textures[i] == null ? "stone" : Init.MODID + ":" + textures[i]);
		}
	}
}
