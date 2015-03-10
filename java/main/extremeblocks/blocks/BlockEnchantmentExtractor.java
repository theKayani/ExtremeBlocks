package main.extremeblocks.blocks;

import main.extremeblocks.GuiIDs;
import main.extremeblocks.Init;
import main.extremeblocks.blocks.abstracts.BlockGui;
import main.extremeblocks.client.containers.ContainerEnchantmentExtractor;
import main.extremeblocks.client.guis.GuiEnchantmentExtractor;
import main.extremeblocks.tileentities.TileEntityEnchantmentExtractor;
import main.extremeblocks.tileentities.TileEntityInventory;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockEnchantmentExtractor extends BlockGui implements GuiIDs
{
	public BlockEnchantmentExtractor()
	{
		super("Enchantment Extractor", Material.iron);
		setInfo("This block can place enchantments from the item you put in it's left-most slot, onto a book you put in it's right-most slot. Then that enchanted book will be placed in the top slot once the block is powered by redstone! " + EnumChatFormatting.RED + "But know this. It will automatically take the enchantment off and put it on a book! It doen't have any warning signs! This might be very dangerous if used freely!" + EnumChatFormatting.RESET);
		showRecipe();
		setHardness(3.0F);
		setBlockTextureName(Init.MODID + ":enchantment_extractor_side");
		setCreativeTab(Init.tab_mainBlocks);
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta)
	{
		TileEntityInventory.dropItems(world, x, y, z);
		super.breakBlock(world, x, y, z, block, meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return side == 0 || side == 1 ? Init.bronze_block.getBlockTextureFromSide(side) : blockIcon;
	}

	@Override
	public Class<? extends TileEntityInventory> getTileClass()
	{
		return TileEntityEnchantmentExtractor.class;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public GuiContainer getGui(InventoryPlayer inventory, World world, int x, int y, int z, TileEntity tile)
	{
		return new GuiEnchantmentExtractor(inventory, (TileEntityEnchantmentExtractor) tile);
	}

	@Override
	public Container getContainer(InventoryPlayer inventory, World world, int x, int y, int z, TileEntity tile)
	{
		return new ContainerEnchantmentExtractor(inventory, (TileEntityEnchantmentExtractor) tile);
	}
}
