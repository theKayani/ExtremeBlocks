package main.com.hk.eb.util;

import java.util.ArrayList;
import main.extremeblocks.ExtremeBlocks;
import main.extremeblocks.Vars;
import main.extremeblocks.crafting.RecipeManager;
import main.extremeblocks.network.AbstractPacket;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;

public class MPUtil
{
	public static void addRecipe(ItemStack item, Object... objects)
	{
		if (Vars.customCraftingTable && RecipeManager.canFitOnVanillaTable(item, objects))
		{
			GameRegistry.addRecipe(item, objects);
		}
		RecipeManager.add(true, item, objects);
	}

	public static void addShapelessRecipe(ItemStack item, Object... objects)
	{
		if (Vars.customCraftingTable && RecipeManager.canFitOnVanillaTable(item, objects))
		{
			GameRegistry.addShapelessRecipe(item, objects);
		}
		RecipeManager.addShapeless(true, item, objects);
	}

	public static void addCompactRecipe(ItemStack item, ItemStack obj)
	{
		addRecipe(item, "###", "###", "###", '#', obj);
	}

	public static void addReversedRecipe(ItemStack item, ItemStack obj)
	{
		item.stackSize = 9;
		addRecipe(item, "#", '#', obj);
	}

	public static void addCompactAndReversedRecipe(ItemStack item, ItemStack obj)
	{
		addCompactRecipe(item, obj);
		addReversedRecipe(obj, item);
	}

	public static void addSmeltingRecipe(ItemStack item, ItemStack reward, float xp)
	{
		GameRegistry.addSmelting(item, reward, xp);
	}

	public static void addToolSetRecipe(Object material, Item... tools)
	{
		assert StackHelper.isBlockOrItem(material) : "[ERR]~~~~~~~~~~~~ ERROR CREATING TOOL SET RECIPES: MATERIAL ISN'T BLOCK OR ITEM ~~~~~~~~~~~~~~|";
		assert tools.length == 5 : "[ERR]~~~~~~~~~~~~ ERROR CREATING TOOL SET RECIPES: NOT ENOUGH TOOLS ~~~~~~~~~~~~~~|";
		addRecipe(new ItemStack(tools[0]), "MMM", " # ", " # ", 'M', material, '#', Items.stick);
		addRecipe(new ItemStack(tools[1]), "M", "#", "#", 'M', material, '#', Items.stick);
		addRecipe(new ItemStack(tools[2]), "MM", "M#", " #", 'M', material, '#', Items.stick);
		addRecipe(new ItemStack(tools[3]), "MM", " #", " #", 'M', material, '#', Items.stick);
		addRecipe(new ItemStack(tools[4]), "M", "M", "#", 'M', material, '#', Items.stick);
	}

	public static String obfuscatedMessage(int length)
	{
		String val = "";
		String obs = String.valueOf(EnumChatFormatting.OBFUSCATED);
		for (int i = 0; i < length; i++)
		{
			val += obs + 'a';
		}
		return val;
	}

	public static TileEntity[] getNeighborTiles(World world, int x, int y, int z)
	{
		ArrayList<TileEntity> tiles = JavaHelp.newArrayList();
		for (int i = -1; i < 2; i++)
		{
			if (i != 0 && world.getTileEntity(x + i, y, z) != null)
			{
				tiles.add(world.getTileEntity(x + i, y, z));
			}
		}
		for (int i = -1; i < 2; i++)
		{
			if (i != 0 && world.getTileEntity(x, y + i, z) != null)
			{
				tiles.add(world.getTileEntity(x, y + i, z));
			}
		}
		for (int i = -1; i < 2; i++)
		{
			if (i != 0 && world.getTileEntity(x, y, z + i) != null)
			{
				tiles.add(world.getTileEntity(x, y, z + i));
			}
		}
		return tiles.toArray(new TileEntity[0]);
	}

	public static Block[] getNeighborBlocks(World world, int x, int y, int z)
	{
		ArrayList<Block> tiles = JavaHelp.newArrayList();
		for (int i = -1; i < 2; i++)
		{
			if (i != 0)
			{
				tiles.add(world.getBlock(x + i, y, z));
			}
		}
		for (int i = -1; i < 2; i++)
		{
			if (i != 0)
			{
				tiles.add(world.getBlock(x, y + i, z));
			}
		}
		for (int i = -1; i < 2; i++)
		{
			if (i != 0)
			{
				tiles.add(world.getBlock(x, y, z + i));
			}
		}
		return tiles.toArray(new Block[0]);
	}

	public static void dropItemsAsEntities(World world, double x, double y, double z, boolean useClone, ItemStack... stacks)
	{
		if (stacks == null || stacks.length <= 0) return;
		for (ItemStack stack : stacks)
		{
			dropItemAsEntity(world, x, y, z, useClone, stack);
		}
	}

	public static void dropItemAsEntity(World world, double x, double y, double z, boolean useClone, ItemStack stack)
	{
		if (stack == null) return;
		ItemStack item = useClone ? stack.copy() : stack;
		if (MPUtil.isServerSide() && item != null && item.stackSize > 0)
		{
			float rx = world.rand.nextFloat() * 0.8F + 0.1F;
			float ry = world.rand.nextFloat() * 0.8F + 0.1F;
			float rz = world.rand.nextFloat() * 0.8F + 0.1F;
			EntityItem entityItem = new EntityItem(world, x + rx, y + ry, z + rz, item.copy());
			if (item.hasTagCompound())
			{
				entityItem.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
			}
			float factor = 0.05F;
			entityItem.motionX = world.rand.nextGaussian() * factor;
			entityItem.motionY = world.rand.nextGaussian() * factor + 0.2F;
			entityItem.motionZ = world.rand.nextGaussian() * factor;
			world.spawnEntityInWorld(entityItem);
			item.stackSize = 0;
		}
	}

	public static Item getRandomItem()
	{
		int random = Rand.nextInt(RegistryHelper.itemsList.length - 1);
		if (RegistryHelper.itemsList[random] != null && RegistryHelper.itemsList[random].getCreativeTab() != null) return RegistryHelper.itemsList[random];
		else return getRandomItem();
	}

	public static Item getRandomModItem()
	{
		Item item = getRandomItem();
		if (!item.getClass().getName().startsWith("net.minecraft.")) return item;
		return getRandomModItem();
	}

	public static Item getRandomEBItem()
	{
		Item item = getRandomItem();
		if (item.getClass().getName().startsWith("main.extremeblocks.")) return item;
		return getRandomEBItem();
	}

	public static Item getRandomVanillaItem()
	{
		Item item = getRandomItem();
		if (item.getClass().getName().startsWith("net.minecraft.")) return item;
		return getRandomVanillaItem();
	}

	public static boolean isServerSide()
	{
		return FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER;
	}

	public static boolean isClientSide()
	{
		return FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT;
	}

	public static void sendMessage(String message, EntityPlayer player)
	{
		if (isServerSide())
		{
			player.addChatMessage(new ChatComponentTranslation(message));
		}
	}

	public static void sendMessage(String message)
	{
		sendMessage(message, getFirstServerPlayer());
	}

	public static void sendMessageToAll(String message)
	{
		for (EntityPlayer player : getServerPlayers())
		{
			sendMessage(message, player);
		}
	}

	public static EntityPlayer[] getServerPlayers()
	{
		return (EntityPlayer[]) getServerWorld().playerEntities.toArray(new EntityPlayer[0]);
	}

	public static EntityPlayer getFirstServerPlayer()
	{
		return getServerPlayers()[0];
	}

	public static EntityPlayer getClientPlayer()
	{
		return Minecraft.getMinecraft().thePlayer;
	}

	public static World getClientWorld()
	{
		return Minecraft.getMinecraft().theWorld;
	}

	public static World getServerWorld()
	{
		return MinecraftServer.getServer().getEntityWorld();
	}

	public static void sendToAll(AbstractPacket message)
	{
		ExtremeBlocks.packetPipeline.sendToAll(message);
	}

	public static void sendTo(AbstractPacket message, EntityPlayerMP player)
	{
		ExtremeBlocks.packetPipeline.sendTo(message, player);
	}

	public static void sendToAllAround(AbstractPacket message, NetworkRegistry.TargetPoint point)
	{
		ExtremeBlocks.packetPipeline.sendToAllAround(message, point);
	}

	public static void sendToDimension(AbstractPacket message, int dimensionId)
	{
		ExtremeBlocks.packetPipeline.sendToDimension(message, dimensionId);
	}

	public static void sendToServer(AbstractPacket message)
	{
		ExtremeBlocks.packetPipeline.sendToServer(message);
	}
}
