package main.extremeblocks.blocks;

import main.com.hk.eb.util.BlockCustom;
import main.extremeblocks.ExtremeBlocks;
import main.extremeblocks.GuiIDs;
import main.extremeblocks.Init;
import main.extremeblocks.tileentities.TileEntityEnchantmentExtractor;
import main.extremeblocks.tileentities.TileEntityInventory;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockEnchantmentExtractor extends BlockCustom implements ITileEntityProvider, GuiIDs
{
	public BlockEnchantmentExtractor()
	{
		super(Material.iron, "Enchantment Extractor");
		setInfo("This block can place enchantments from the item you put in it's left-most slot, onto a book you put in it's right-most slot. Then that enchanted book will be placed in the top slot once the block is powered by redstone! " + EnumChatFormatting.RED + "But know this. It will automatically take the enchantment off and put it on a book! It doen't have any warning signs! This might be very dangerous if used freely!" + EnumChatFormatting.RESET);
		setShowRecipe();
		setHardness(3.0F);
		setBlockTextureName(Init.MODID + ":enchantment_extractor_side");
		setCreativeTab(Init.tab_mainBlocks);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int idk, float sideX, float sideY, float sideZ)
	{
		if (!player.isSneaking())
		{
			player.openGui(ExtremeBlocks.instance, TILE_ENCHANTMENT_EXTRACTOR, world, x, y, z);
			return true;
		}
		return false;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta)
	{
		TileEntityInventory.dropItems(world, x, y, z);
		super.breakBlock(world, x, y, z, block, meta);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int eye)
	{
		return new TileEntityEnchantmentExtractor();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return side == 0 || side == 1 ? Init.bronze_block.getBlockTextureFromSide(side) : blockIcon;
	}
}
