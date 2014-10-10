package main.extremeblocks.blocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import main.com.hk.eb.util.BlockCustom;
import main.com.hk.eb.util.JavaHelp;
import main.com.hk.eb.util.StackHelper;
import main.extremeblocks.ExtremeBlocks;
import main.extremeblocks.GuiIDs;
import main.extremeblocks.Init;
import main.extremeblocks.tileentities.TileEntityRevertingStation;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class BlockRecipeRevert extends BlockCustom implements ITileEntityProvider, GuiIDs
{
	public BlockRecipeRevert()
	{
		super(Material.wood, "Recipe Reverting Station");
		setCreativeTab(Init.tab_mainBlocks);
		setBlockTextureName(Init.MODID + ":recipe_reverter");
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block par5, int par6)
	{
		Random rand = new Random();
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if (!(tileEntity instanceof IInventory)) return;
		IInventory inventory = (IInventory) tileEntity;
		int i = 0;
		ItemStack item = inventory.getStackInSlot(i);
		if (item != null && item.stackSize > 0)
		{
			float rx = rand.nextFloat() * 0.8F + 0.1F;
			float ry = rand.nextFloat() * 0.8F + 0.1F;
			float rz = rand.nextFloat() * 0.8F + 0.1F;
			EntityItem entityItem = new EntityItem(world, x + rx, y + ry, z + rz, item.copy());
			if (item.hasTagCompound())
			{
				entityItem.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
			}
			float factor = 0.05F;
			entityItem.motionX = rand.nextGaussian() * factor;
			entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
			entityItem.motionZ = rand.nextGaussian() * factor;
			world.spawnEntityInWorld(entityItem);
			item.stackSize = 0;
		}
		super.breakBlock(world, x, y, z, par5, par6);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int idk, float sideX, float sideY, float sideZ)
	{
		if (!player.isSneaking())
		{
			player.openGui(ExtremeBlocks.instance, TILE_REVERTING_STATION, world, x, y, z);
			return true;
		}
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TileEntityRevertingStation();
	}

	public static IRecipe getRecipeFor(ItemStack stack)
	{
		for (IRecipe irecipe : (List<IRecipe>) CraftingManager.getInstance().getRecipeList())
		{
			if (StackHelper.areStacksSameTypeCrafting(stack, irecipe.getRecipeOutput())) return irecipe;
		}
		return null;
	}

	public static ItemStack[] extractRecipeItems(Object obj)
	{
		if (obj instanceof ItemStack) return new ItemStack[] { (ItemStack) obj };
		if (obj instanceof ItemStack[]) return (ItemStack[]) obj;
		if (obj instanceof List) return ((List<ItemStack>) obj).toArray(new ItemStack[0]);
		return null;
	}

	public static ItemStack[] getRecipeItemsFor(ItemStack stack)
	{
		IRecipe recipe = getRecipeFor(stack);
		if (recipe instanceof ShapedRecipes) return ((ShapedRecipes) recipe).recipeItems;
		if (recipe instanceof ShapelessRecipes) return (ItemStack[]) ((ShapelessRecipes) recipe).recipeItems.toArray(new ItemStack[0]);
		if (recipe instanceof ShapelessOreRecipe)
		{
			ArrayList<ItemStack> stacks = JavaHelp.newArrayList();
			Object[] items = ((ShapelessOreRecipe) recipe).getInput().toArray();
			for (Object item : items)
			{
				if (item instanceof List && ((List<?>) item).isEmpty()) return null;
				stacks.addAll(Arrays.asList(extractRecipeItems(item)));
			}
			return stacks.toArray(new ItemStack[0]);
		}
		if (recipe instanceof ShapedOreRecipe)
		{
			ArrayList<ItemStack> stacks = JavaHelp.newArrayList();
			Object[] items = ((ShapedOreRecipe) recipe).getInput();
			for (Object item : items)
			{
				if (item instanceof List && ((List<?>) item).isEmpty()) return null;
				if (extractRecipeItems(item) != null)
				{
					stacks.addAll(Arrays.asList(extractRecipeItems(item)));
				}
			}
			return stacks.toArray(new ItemStack[0]);
		}
		return null;
	}
}
