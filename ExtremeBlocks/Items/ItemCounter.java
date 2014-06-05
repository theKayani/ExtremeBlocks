package extremeblocks.items;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import com.hk.testing.util.ItemCustom;
import extremeblocks.ExtremeBlocks;
import extremeblocks.Init;

public class ItemCounter extends ItemCustom
{
	private static int count;

	public ItemCounter()
	{
		super("Counter", Init.tab_mainItems);
		this.setTextureName(Init.MODID + ":counter");
	}

	public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean par4)
	{
		list.add("Count: " + count);
	}

	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		count++;
		return par1ItemStack;
	}
}
