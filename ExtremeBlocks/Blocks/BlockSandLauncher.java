package ExtremeBlocks.Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCommandBlock;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.item.EntityFallingSand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import ExtremeBlocks.ExtremeBlocksMain;
import ExtremeBlocks.Vars;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSandLauncher extends Block
{
	public boolean isRedstonePowered = false;
	public int height = 1;

	public BlockSandLauncher(int par1, Material par2Material) 
	{
		super(par1, par2Material);
		this.setHardness(1.0F);
		this.setUnlocalizedName("SandLauncher");
		this.setStepSound(soundStoneFootstep);
		this.setCreativeTab(ExtremeBlocksMain.EBMiscTab);
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) 
	{
		this.blockIcon = par1IconRegister.registerIcon(ExtremeBlocksMain.modid + ":" + "RedGameFloor");
	}
}
