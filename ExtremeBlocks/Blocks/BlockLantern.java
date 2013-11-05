package ExtremeBlocks.Blocks;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import ExtremeBlocks.ExtremeBlocksMain;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockLantern extends Block 
{
	public BlockLantern(int par1, Material par2) 
	{
		super(par1, par2);
		this.setHardness(0.9F);
		this.setUnlocalizedName("Lantern");
		this.setCreativeTab(ExtremeBlocksMain.EBMiscTab);
		this.setLightValue(0.6F);
		this.setTickRandomly(true);
		this.setStepSound(soundGlassFootstep);
		this.setBlockBounds(0.3F, 0.3F, 0.3F, 0.7F, 1.0F, 0.7F);
	}

	public int idDropped(int par1, Random par2Random, int par3) 
	{
		return this.blockID;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) 
	{
		this.blockIcon = par1IconRegister.registerIcon(ExtremeBlocksMain.modid + ":" + (this.getUnlocalizedName().substring(5)));
	}

	public boolean isOpaqueCube() 
	{
		return false;
	}

	public boolean renderAsNormalBlock() 
	{
		return false;
	}

	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4) 
	{
		return par1World.isBlockSolidOnSide(par2, par3 + 1, par4,ForgeDirection.UP, true);
	}

	public void updateTick(World par1World, int par2, int par3, int par4,Random par5Random) 
	{
		super.updateTick(par1World, par2, par3, par4, par5Random);

		if (par1World.getBlockMetadata(par2, par3, par4) == 0) 
		{
			this.onBlockAdded(par1World, par2, par3, par4);
		}
	}

	protected boolean dropTorchIfCantStay(World par1World, int par2, int par3, int par4) 
	{
		if (!this.canPlaceBlockAt(par1World, par2, par3, par4)) 
		{
			if (par1World.getBlockId(par2, par3, par4) == this.blockID) 
			{
				this.dropBlockAsItem(par1World, par2, par3, par4, par1World.getBlockMetadata(par2, par3, par4), 0);
				par1World.setBlockToAir(par2, par3, par4);
			}

			return false;
		} 
		else 
		{
			return true;
		}
	}
	
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
	{
		if(!par1World.getBlockMaterial(par2, par3 + 1, par4).isSolid())
		{
			par1World.destroyBlock(par2, par3, par4, true);
		}
	}
}
