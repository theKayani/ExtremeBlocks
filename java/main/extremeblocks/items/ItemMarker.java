package main.extremeblocks.items;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import main.com.hk.eb.util.FileHelper;
import main.com.hk.eb.util.ItemCustom;
import main.com.hk.eb.util.JavaHelp;
import main.com.hk.eb.util.MPUtil;
import main.com.hk.eb.util.Vector3I;
import main.extremeblocks.Init;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemMarker extends ItemCustom
{
	public ItemMarker()
	{
		super("Block Marker", Init.tab_mainItems);
		setTextureName(Init.MODID + ":block_marker");
		setInfo("Marks a block upon right clicking. Then with that, you could right click again, and it will tell you the distance between them.");
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float sideX, float sideY, float sideZ)
	{
		if (stack.stackTagCompound == null)
		{
			stack.stackTagCompound = new NBTTagCompound();
		}
		if (!stack.stackTagCompound.getBoolean("Set"))
		{
			stack.stackTagCompound.setInteger("Vec X", x);
			stack.stackTagCompound.setInteger("Vec Y", y);
			stack.stackTagCompound.setInteger("Vec Z", z);
			stack.stackTagCompound.setBoolean("Set", true);
			MPUtil.sendMessage("Set At: " + x + ", " + y + ", " + z, player);
		}
		else
		{
			int a = stack.stackTagCompound.getInteger("Vec X");
			int b = stack.stackTagCompound.getInteger("Vec Y");
			int c = stack.stackTagCompound.getInteger("Vec Z");
			stack.stackTagCompound.setBoolean("Set", false);
			Vector3I pre = new Vector3I(a, b, c);
			Vector3I pos = new Vector3I(x, y, z);
			int i = pos.distance(pre);
			MPUtil.sendMessage("Distance: " + i, player);
			//writeTo(world, pre, pos);
		}
		return true;
	}

	public void writeTo(World world, Vector3I pre, Vector3I pos)
	{
		if (MPUtil.isClientSide()) return;
		ArrayList<String> list = JavaHelp.newArrayList();
		int a = Math.min(pre.x, pos.x);
		int b = Math.min(pre.y, pos.y);
		int c = Math.min(pre.z, pos.z);
		int a1 = Math.max(pre.x, pos.x);
		int b1 = Math.max(pre.y, pos.y);
		int c1 = Math.max(pre.z, pos.z);

		String f = new File(System.getProperty("user.home") + "/Desktop/lol.txt").getPath();
		FileHelper.resetFile(f);

		for (int x = a; x < a1; x++)
		{
			for (int y = b; y < b1; y++)
			{
				for (int z = c; z < c1; z++)
				{
					Block block = world.getBlock(x, y, z);
					int meta = world.getBlockMetadata(x, y, z);
					if (block != Blocks.air)
					{
						if (block == Blocks.bedrock)
						{
							list.add("helper.setBlockToAir(" + (x - a) + ", " + (y - b) + ", " + (z - c) + ");");
						}
						else if (block == Blocks.coal_block)
						{
							list.add("helper.setBlock(Blocks.flowing_water, " + (x - a) + ", " + (y - b) + ", " + (z - c) + ");");
						}
						else if (block == Blocks.wool)
						{
							list.add("helper.setBlock(Blocks.wool, " + (x - a) + ", " + (y - b) + ", " + (z - c) + ");");
						}
						else
						{
							list.add("helper.setBlock(" + getBlockFromName(block) + ", " + (x - a) + ", " + (y - b) + ", " + (z - c) + (meta != 0 ? ", " + meta : "") + ");");
						}
					}
				}
			}
		}
		Collections.sort(list);
		for (String s : list)
		{
			FileHelper.writeToFile(f, s);
		}
	}

	public static String getBlockFromName(Block block)
	{
		try
		{
			List<Field> list = JavaHelp.newArrayList();
			Collections.addAll(list, Init.class.getFields());
			Collections.addAll(list, Blocks.class.getFields());
			Field[] fields = list.toArray(new Field[0]);
			for (Field field : fields)
			{
				if (Modifier.isStatic(field.getModifiers()) && field.get(null) instanceof Block)
				{
					if (block == field.get(null)) return field.getDeclaringClass().getSimpleName() + "." + field.getName();
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return block.getLocalizedName();
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean bool)
	{
		if (stack.stackTagCompound == null)
		{
			stack.stackTagCompound = new NBTTagCompound();
		}
		if (stack.stackTagCompound.getBoolean("Set"))
		{
			int x = stack.stackTagCompound.getInteger("Vec x");
			int y = stack.stackTagCompound.getInteger("Vec y");
			int z = stack.stackTagCompound.getInteger("Vec z");
			Vector3I dest = new Vector3I(x, y, z);
			Vector3I pos = new Vector3I(player);
			int i = pos.distance(dest);
			list.add("Orgin: " + dest);
			list.add("Current Distance: " + i);
		}
		else
		{
			list.add("Orgin: " + EnumChatFormatting.RED + "Not set yet!" + EnumChatFormatting.RESET);
			list.add("Current Distance: " + EnumChatFormatting.RED + "Not set yet!" + EnumChatFormatting.RESET);
		}
	}
}
