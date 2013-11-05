package ExtremeBlocks.Blocks;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemFencesBlock extends ItemBlock
{
	public ItemFencesBlock(int par1)
	{
		super(par1);
		setHasSubtypes(true);
	}

	public String getUnlocalizedName(ItemStack itemstack)
	{
		String name = "";
		switch(itemstack.getItemDamage())
		{
		case 0:
		{
			name = "Fences-0";
			break;
		}
		case 1:
		{
			name = "Fences-1";
			break;
		}
		case 2:
		{
			name = "Fences-2";
			break;
		}
		case 3:
		{
			name = "Fences-3";
			break;
		}
		case 4:
		{
			name = "Fences-4";
			break;
		}
		case 5:
		{
			name = "Fences-5";
			break;
		}
		case 6:
		{
			name = "Fences-6";
			break;
		}

		case 7:
		{
			name = "Fences-7";
			break;
		}
		case 8:
		{
			name = "Fences-8";
			break;
		}
		case 9:
		{
			name = "Fences-9";
			break;
		}
		case 10:
		{
			name = "Fences-10";
			break;
		}
		case 11:
		{
			name = "Fences-11";
			break;
		}
		case 12:
		{
			name = "Fences-12";
			break;
		}
		case 13:
		{
			name = "Fences-13";
			break;
		}

		case 14:
		{
			name = "Fences-14";
			break;
		}
		case 15:
		{
			name = "Fences-15";
			break;
		}
		default: name = "broken";
		}
		return getUnlocalizedName() + "." + name;
	}

	public int getMetadata(int par1)
	{
		return par1;
	}
}