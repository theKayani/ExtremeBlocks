package ExtremeBlocks.Blocks;

import ExtremeBlocks.ExtremeBlocksMain;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockWeakCementWall extends Block 
{
	public BlockWeakCementWall(int par1, Material par2) 
	{
		super(par1, par2);
		this.setCreativeTab(ExtremeBlocksMain.EBBasicBlocksTab);
		this.setUnlocalizedName("WeakCementWall");
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) 
	{
		this.blockIcon = par1IconRegister.registerIcon(ExtremeBlocksMain.modid + ":" + (this.getUnlocalizedName().substring(5)));
	}

	public boolean canPlaceBlockAt(World par1world, int par2, int par3, int par4) 
	{
		return false;
	}

	public void onBlockAdded(World par1World, int par2, int par3, int par4) 
	{
		par1World.destroyBlock(par2, par3, par4, true);
	}
}
