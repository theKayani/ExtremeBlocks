package main.extremeblocks.worldgen.catacombs;

import java.util.Random;
import main.com.hk.eb.util.Builder;
import main.com.hk.eb.util.Rand;
import main.extremeblocks.entities.mobs.EntityCastleSkeleton;
import main.extremeblocks.entities.mobs.EntityCastleZombie;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.ItemStack;

public class CatacombPiece
{
	public static void clearArea(Builder helper)
	{
		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 6; j++)
			{
				for (int k = 0; k < 9; k++)
				{
					helper.setBlockToAir(i, j, k);
				}
			}
		}
		helper.spawnEntityAt(getMob(helper), 4, 1, 4);
	}

	private static EntityMob getMob(Builder helper)
	{
		EntityMob mob = Rand.nextBoolean() ? new EntityCastleSkeleton(helper.world) : new EntityCastleZombie(helper.world);
		mob.setCurrentItemOrArmor(1, new ItemStack(EntityLiving.getArmorItemForSlot(4, 0)));
		mob.setCurrentItemOrArmor(2, new ItemStack(EntityLiving.getArmorItemForSlot(3, 0)));
		mob.setCurrentItemOrArmor(3, new ItemStack(EntityLiving.getArmorItemForSlot(2, 0)));
		mob.setCurrentItemOrArmor(4, new ItemStack(EntityLiving.getArmorItemForSlot(1, 0)));
		mob.onSpawnWithEgg(null);
		return mob;
	}

	public static String[] getRandomLevel(int size, Random rand)
	{
		return new MazeGenerator(rand, size, size).getMap();
	}

	public static enum Pieces
	{
		HALL,
		TURN,
		ROOM;
	}
}
