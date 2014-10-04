package main.extremeblocks.items;

import java.util.List;
import main.com.hk.eb.util.ItemCustom;
import main.extremeblocks.Init;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemCellphone extends ItemCustom
{
	public ItemCellphone()
	{
		super("Cellphone", Init.tab_mainItems);
		this.setTextureName(Init.MODID + ":cellphone");
	}

	@Override
	public void addInformation(ItemStack itemstack, EntityPlayer player, List list, boolean par4)
	{
		list.add(getFirstLine(player.worldObj, player));
		list.add(getSecondLine(player.worldObj, player));
		list.add(getThirdLine(player.worldObj, player));
	}

	private String getFirstLine(World world, EntityPlayer player)
	{
		Entity entity = world.findNearestEntityWithinAABB(EntityLiving.class, player.boundingBox.expand(100.0D, 100.0D, 100.0D), player);
		return "Time: " + (int) world.getWorldTime() + ", Traveled: " + player.distanceWalkedModified + ", Closest Mob: " + (entity == null ? "None" : EntityList.getEntityString(entity));
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