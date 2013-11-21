package ExtremeBlocks.Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCommandBlock;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import ExtremeBlocks.ExtremeBlocksMain;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockClone extends BlockCommandBlock
{
	private boolean ActivatedDaBlock = false;
	
	private int lightOff;
	
	public BlockClone(int par1) 
	{
		super(par1);
		this.setHardness(1.0F);
		this.setUnlocalizedName("Clone");
		this.setStepSound(soundStoneFootstep);
		this.setCreativeTab(ExtremeBlocksMain.EBMiscTab);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) 
	{
		this.blockIcon = par1IconRegister.registerIcon(ExtremeBlocksMain.modid + ":" + "LightedPlank");
	}
}
