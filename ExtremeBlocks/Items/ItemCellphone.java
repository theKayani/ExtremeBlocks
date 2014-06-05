package extremeblocks.items;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import com.hk.testing.util.ItemCustom;
import extremeblocks.ExtremeBlocks;
import extremeblocks.Init;

public class ItemCellphone extends ItemCustom
{
	public ItemCellphone()
	{
		super("Cellphone", Init.tab_mainItems);
		this.setTextureName(Init.MODID + ":cellphone");
	}

	public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean par4)
	{
		list.add(getFirstLine(player.worldObj, player));
		list.add(getSecondLine(player.worldObj, player));
		list.add(getThirdLine(player.worldObj, player));
	}

	private String getFirstLine(World world, EntityPlayer player)
	{
		Block block = world.getBlock((int) player.posX, (int) (player.posY - 2), (int) player.posZ);
		return "Time: " + (int) world.getWorldTime() + ", Traveled: " + player.distanceWalkedModified + ", Block: " + (block == Blocks.air ? "none" : block.getLocalizedName());
	}

	private String getSecondLine(World world, EntityPlayer player)
	{
		return "Age: " + player.getAge() + ", Moon Phase: " + world.getCurrentMoonPhaseFactor() + ", Biome: " + world.provider.worldChunkMgr.getBiomeGenAt((int) player.posX, (int) player.posZ).biomeName;
	}

	private String getThirdLine(World world, EntityPlayer player)
	{
		return "X: " + (int) player.posX + ", Y: " + (int) player.posY + ", Z: " + (int) player.posZ + ", Dimension: " + world.provider.getDimensionName() + "(" + world.provider.dimensionId + ")";
	}
}