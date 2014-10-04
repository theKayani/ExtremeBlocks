package main.extremeblocks.blocks;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import main.com.hk.eb.util.BlockCustom;
import main.com.hk.eb.util.MPUtil;
import main.extremeblocks.Init;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.world.World;

public class BlockRecipeRevert extends BlockCustom
{
	public BlockRecipeRevert()
	{
		super(Material.wood, "Recipe Reverting Station");
		setCreativeTab(Init.tab_mainBlocks);
		setBlockTextureName(Init.MODID + ":recipe_reverter");
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int idk, float sideX, float sideY, float sideZ)
	{
		boolean dropItem = false;
		ItemStack heldItem = player.getHeldItem();
		List<IRecipe> list = CraftingManager.getInstance().getRecipeList();
		Collections.sort(list, new Comparator<IRecipe>()
		{
			@Override
			public int compare(IRecipe o1, IRecipe o2)
			{
				ItemStack a = o1.getRecipeOutput();
				ItemStack b = o2.getRecipeOutput();
				if (a == null || b == null) return 0;
				return a.getDisplayName().compareTo(b.getDisplayName());
			}
		});
		if (heldItem == null) return false;
		System.out.println("ItemStack: " + heldItem.getDisplayName() + ", " + heldItem.stackSize);
		for (int i = 0; i < list.size(); i++)
		{
			ItemStack result = list.get(i).getRecipeOutput();
			if (result != null && result.isItemEqual(heldItem) && result.stackSize <= heldItem.stackSize)
			{
				if (list.get(i) instanceof ShapedRecipes)
				{
					MPUtil.dropItemsAsEntities(world, x, y, z, true, ((ShapedRecipes) list.get(i)).recipeItems);
					dropItem = true;
				}
				else if (list.get(i) instanceof ShapelessRecipes)
				{
					MPUtil.dropItemsAsEntities(world, x, y, z, true, (ItemStack[]) ((ShapelessRecipes) list.get(i)).recipeItems.toArray(new ItemStack[0]));
					dropItem = true;
				}
				if (dropItem)
				{
					for (int f = 0; f < result.stackSize; f++)
					{
						player.inventory.consumeInventoryItem(heldItem.getItem());
					}
					break;
				}
			}
		}

		return true;
	}
}
