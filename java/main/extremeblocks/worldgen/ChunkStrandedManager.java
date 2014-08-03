package main.extremeblocks.worldgen;

import java.util.Random;
import main.extremeblocks.Vars;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderGenerate;

public class ChunkStrandedManager extends ChunkProviderGenerate
{
	private Random rand;
	private World worldObj;

	public ChunkStrandedManager(World par1World, long par2, boolean par4)
	{
		super(par1World, par2, par4);
		this.rand = new Random(par2);
		this.worldObj = par1World;
	}

	public void populate(IChunkProvider par1IChunkProvider, int par2, int par3)
	{
		for (int i = 0; i < 20; i++)
		{
			int k = par2 * 16;
			int l = par3 * 16;
			int k1 = k + this.rand.nextInt(16) + 8;
			int l1 = this.rand.nextInt(256);
			int i2 = l + this.rand.nextInt(16) + 8;

			if (Vars.numbOfTrees > Vars.spawnedTrees && (new WorldGenOneTree()).generate(this.worldObj, this.rand, k1, l1, i2))
			{
				System.out.println("Spawned Tree- " + Vars.spawnedTrees + "/" + Vars.numbOfTrees);
				Vars.spawnedTrees++;
			}

			if (Vars.numbOfPumps > Vars.spawnedPumps && (new WorldGenBlock(Blocks.pumpkin)).generate(this.worldObj, this.rand, k1, l1, i2))
			{
				System.out.println("Spawned Pump- " + Vars.spawnedPumps + "/" + Vars.numbOfPumps);
				Vars.spawnedPumps++;
			}

			if (Vars.numbOfCactus > Vars.spawnedCactus && (new WorldGenBlock(Blocks.cactus, Blocks.sand)).generate(this.worldObj, this.rand, k1, l1, i2))
			{
				System.out.println("Spawned Cactus- " + Vars.spawnedCactus + "/" + Vars.numbOfCactus);
				Vars.spawnedCactus++;
			}

			if (Vars.numbOfMelons > Vars.spawnedMelons && (new WorldGenBlock(Blocks.melon_block)).generate(this.worldObj, this.rand, k1, l1, i2))
			{
				System.out.println("Spawned Melon- " + Vars.spawnedMelons + "/" + Vars.numbOfMelons);
				Vars.spawnedMelons++;
			}
		}

		super.populate(par1IChunkProvider, par2, par3);
	}
}
