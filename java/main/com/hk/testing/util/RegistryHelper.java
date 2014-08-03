package main.com.hk.testing.util;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.world.biome.BiomeGenBase;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class RegistryHelper
{
	public static final Block[] blocksList = new Block[4095];
	public static final Item[] itemsList = new Item[31999];

	public static void register(Object obj)
	{
		Block block = null;
		Item item = null;

		if (obj instanceof Block) GameRegistry.registerBlock(((Block) obj), ((Block) obj).getUnlocalizedName());
		if (obj instanceof Item) GameRegistry.registerItem(((Item) obj), ((Item) obj).getUnlocalizedName());
		else
		{

		}
	}

	public static void registerEntity(Class<? extends EntityLiving> entityClass, Object mod, String mobName, EnumCreatureType creature, BiomeGenBase... biomes)
	{
		int id = EntityRegistry.findGlobalUniqueEntityId();

		if (creature == EnumCreatureType.monster)
		{
			EntityRegistry.registerGlobalEntityID(entityClass, mobName, id, 0, 16777215);
		}
		else
		{
			EntityRegistry.registerGlobalEntityID(entityClass, mobName, id, 16777215, 0);
		}
		EntityRegistry.registerModEntity(entityClass, mobName, id, mod, 80, 3, true);

		if (biomes != null && biomes.length != 0)
		{
			for (int i = 0; i < biomes.length; i++)
			{
				if (biomes[i] != null)
				{
					EntityRegistry.addSpawn(entityClass, 10, 1, 3, creature, BiomeGenBase.getBiomeGenArray()[i]);
				}
			}
		}
	}
}
