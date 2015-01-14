package main.com.hk.eb.util;

import java.util.ArrayList;
import main.extremeblocks.EBClient;
import main.extremeblocks.Vars;
import main.extremeblocks.crafting.RecipeManager;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;

public class MPUtil
{
	public static void addRecipe(ItemStack item, Object... objects)
	{
		if (Vars.customCraftingTable && RecipeManager.canFitOnVanillaTable(item, objects))
		{
			GameRegistry.addRecipe(new ShapedOreRecipe(item, objects));
		}
		RecipeManager.add(true, item, objects);
	}

	public static void addShapelessRecipe(ItemStack item, Object... objects)
	{
		if (Vars.customCraftingTable && RecipeManager.canFitOnVanillaTable(item, objects))
		{
			GameRegistry.addRecipe(new ShapelessOreRecipe(item, objects));
		}
		RecipeManager.addShapeless(true, item, objects);
	}

	public static void addCompactRecipe(ItemStack item, ItemStack obj)
	{
		addRecipe(item, "###", "###", "###", '#', obj);
	}

	public static void addReversedRecipe(ItemStack item, ItemStack obj)
	{
		item = item.copy();
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

	public static void addArmorSetRecipe(Object material, Item... armor)
	{
		addRecipe(new ItemStack(armor[0]), "MMM", "M M", 'M', material);
		addRecipe(new ItemStack(armor[1]), "M M", "MMM", "MMM", 'M', material);
		addRecipe(new ItemStack(armor[2]), "MMM", "M M", "M M", 'M', material);
		addRecipe(new ItemStack(armor[3]), "M M", "M M", 'M', material);
	}

	public static void addToolSetRecipe(Object material, Item... tools)
	{
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

	public static void rangeEntity(EntityLivingBase entity, EntityLivingBase target, float damage, ItemStack stack)
	{
		World worldObj = target.worldObj;
		EntityArrow entityarrow = new EntityArrow(worldObj, entity, target, 1.6F, 14);
		int i = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, stack);
		int j = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, stack);
		entityarrow.setDamage(damage * 2.0F + worldObj.rand.nextGaussian() * 0.36D);

		if (i > 0)
		{
			entityarrow.setDamage(entityarrow.getDamage() + i * 0.5D + 0.5D);
		}

		if (j > 0)
		{
			entityarrow.setKnockbackStrength(j);
		}

		if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, stack) > 0)
		{
			entityarrow.setFire(100);
		}

		entity.playSound("random.bow", 1.0F, 1.0F / (worldObj.rand.nextFloat() * 0.4F + 0.8F));
		worldObj.spawnEntityInWorld(entityarrow);
	}

	public static void replace(IReplacer replacer)
	{
		if (replacer instanceof EntityLivingBase)
		{
			if (isServerSide() && EBClient.removeMobs.get(replacer.getClass()).booleanValue())
			{
				EntityLivingBase copy = replacer.getClone();
				copy.copyLocationAndAnglesFrom((EntityLivingBase) replacer);
				if (copy instanceof EntityLiving)
				{
					((EntityLiving) copy).onSpawnWithEgg(null);
				}
				((EntityLivingBase) replacer).worldObj.removeEntity((EntityLivingBase) replacer);
				((EntityLivingBase) replacer).worldObj.spawnEntityInWorld(copy);
				return;
			}
		}
		else throw new IllegalArgumentException(replacer.getClass().getName() + " isn't an EntityLivingBase");
	}

	public static TileEntity[] getNeighborTiles(World world, int x, int y, int z)
	{
		ArrayList<TileEntity> tiles = JavaHelp.newArrayList();
		tiles.add(world.getTileEntity(x + 1, y, z));
		tiles.add(world.getTileEntity(x - 1, y, z));
		tiles.add(world.getTileEntity(x, y + 1, z));
		tiles.add(world.getTileEntity(x, y - 1, z));
		tiles.add(world.getTileEntity(x, y, z + 1));
		tiles.add(world.getTileEntity(x, y, z - 1));
		return tiles.toArray(new TileEntity[0]);
	}

	public static Block[] getNeighborBlocks(World world, int x, int y, int z)
	{
		ArrayList<Block> blocks = JavaHelp.newArrayList();
		blocks.add(world.getBlock(x + 1, y, z));
		blocks.add(world.getBlock(x - 1, y, z));
		blocks.add(world.getBlock(x, y + 1, z));
		blocks.add(world.getBlock(x, y - 1, z));
		blocks.add(world.getBlock(x, y, z + 1));
		blocks.add(world.getBlock(x, y, z - 1));
		return blocks.toArray(new Block[0]);
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
		int random = Rand.nextInt(Item.itemRegistry.getKeys().size());
		if (Item.itemRegistry.containsId(random) && ((Item) Item.itemRegistry.getObjectById(random)).getCreativeTab() != null) return (Item) Item.itemRegistry.getObjectById(random);
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

	public static ItemStack getEntityEgg(Class<Entity> clazz)
	{
		for (int i = 0; i < EntityList.entityEggs.size(); i++)
		{
			if (((Class<?>) EntityList.IDtoClassMapping.get(i)).isAssignableFrom(clazz)) return new ItemStack(Items.spawn_egg, 1, i);
		}
		return null;
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

	@SuppressWarnings("unchecked")
	public static EntityPlayerMP[] getServerPlayers()
	{
		return (EntityPlayerMP[]) getServerWorld().playerEntities.toArray(new EntityPlayerMP[0]);
	}

	public static EntityPlayerMP getFirstServerPlayer()
	{
		return getServerPlayers()[0];
	}

	public static EntityClientPlayerMP getClientPlayer()
	{
		return Minecraft.getMinecraft().thePlayer;
	}

	public static WorldClient getClientWorld()
	{
		return Minecraft.getMinecraft().theWorld;
	}

	public static WorldServer getServerWorld()
	{
		return (WorldServer) MinecraftServer.getServer().getEntityWorld();
	}
}
