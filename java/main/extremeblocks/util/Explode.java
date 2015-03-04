package main.extremeblocks.util;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import main.com.hk.eb.util.JavaHelp;
import main.com.hk.eb.util.Rand;
import main.com.hk.eb.util.Vector3F;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class Explode extends Explosion
{
	public boolean isFlaming;
	public boolean isSmoking = true;
	private Random explosionRNG = new Random();
	private World worldObj;
	public float explosionX;
	public float explosionY;
	public float explosionZ;
	public Entity exploder;
	public float explosionSize;
	public List<ChunkPosition> affectedBlockPositions = JavaHelp.newArrayList();
	private Map<EntityPlayer, Vector3F> affectedPlayers = JavaHelp.newHashMap();

	public Explode(Entity entity, float strength)
	{
		this(entity.worldObj, entity, (float) entity.posX, (float) entity.posY, (float) entity.posZ, strength);
	}

	public Explode(World world, Entity entity, float x, float y, float z, float strength)
	{
		super(world, entity, x, y, z, strength);
		worldObj = world;
		exploder = entity;
		explosionSize = strength;
		explosionX = x;
		explosionY = y;
		explosionZ = z;
	}

	/**
	 * Does the first part of the explosion (destroy blocks)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void doExplosionA()
	{
		float f = explosionSize;
		int i;
		int j;
		int k;
		float d5;
		float d6;
		float d7;

		explosionSize *= 2.0F;
		for (i = (int) -explosionSize; i < explosionSize + 1; i++)
		{
			for (j = (int) -explosionSize; j < explosionSize + 1; j++)
			{
				for (k = (int) -explosionSize; k < explosionSize + 1; k++)
				{
					Vector3F vec = getVec((int) (i + explosionX), (int) (j + explosionY), (int) (k + explosionZ), new Vector3F(i, j, k));
					if (vec != null)
					{
						int a = MathHelper.floor_float(vec.x + explosionX);
						int b = MathHelper.floor_float(vec.y + explosionY);
						int c = MathHelper.floor_float(vec.z + explosionZ);
						affectedBlockPositions.add(new ChunkPosition(a, b, c));
					}
				}
			}
		}

		i = MathHelper.floor_double(explosionX - explosionSize - 1.0D);
		j = MathHelper.floor_double(explosionX + explosionSize + 1.0D);
		k = MathHelper.floor_double(explosionY - explosionSize - 1.0D);
		int i2 = MathHelper.floor_double(explosionY + explosionSize + 1.0D);
		int l = MathHelper.floor_double(explosionZ - explosionSize - 1.0D);
		int j2 = MathHelper.floor_double(explosionZ + explosionSize + 1.0D);
		List<Entity> list = worldObj.getEntitiesWithinAABBExcludingEntity(exploder, AxisAlignedBB.getBoundingBox(i, k, l, j, i2, j2));
		Vec3 vec3 = Vec3.createVectorHelper(explosionX, explosionY, explosionZ);

		for (int i1 = 0; i1 < list.size(); ++i1)
		{
			Entity entity = list.get(i1);
			float d4 = (float) entity.getDistance(explosionX, explosionY, explosionZ) / explosionSize;

			if (d4 <= 1.0D)
			{
				d5 = (float) entity.posX - explosionX;
				d6 = (float) entity.posY + entity.getEyeHeight() - explosionY;
				d7 = (float) entity.posZ - explosionZ;
				float d9 = MathHelper.sqrt_double(d5 * d5 + d6 * d6 + d7 * d7);

				if (d9 != 0.0D)
				{
					d5 /= d9;
					d6 /= d9;
					d7 /= d9;
					float d10 = worldObj.getBlockDensity(vec3, entity.boundingBox);
					float d11 = (float) (1.0D - d4) * d10;
					entity.attackEntityFrom(DamageSource.setExplosionSource(this), (int) ((d11 * d11 + d11) / 2.0D * 8.0D * explosionSize + 1.0D));
					float d8 = (float) EnchantmentProtection.func_92092_a(entity, d11);
					entity.motionX += d5 * d8;
					entity.motionY += d6 * d8;
					entity.motionZ += d7 * d8;

					if (entity instanceof EntityPlayer)
					{
						affectedPlayers.put((EntityPlayer) entity, new Vector3F(d5 * d11, d6 * d11, d7 * d11));
					}
				}
			}
		}

		explosionSize = f;
	}

	/**
	 * Does the second part of the explosion (sound, particles, drop spawn)
	 */
	@Override
	public void doExplosionB(boolean spawnParticles)
	{
		worldObj.playSoundEffect(explosionX, explosionY, explosionZ, "random.explode", 4.0F, (1.0F + (worldObj.rand.nextFloat() - worldObj.rand.nextFloat()) * 0.2F) * 0.7F);

		if (explosionSize >= 2.0F && isSmoking)
		{
			worldObj.spawnParticle("hugeexplosion", explosionX, explosionY, explosionZ, 1.0D, 0.0D, 0.0D);
		}
		else
		{
			worldObj.spawnParticle("largeexplode", explosionX, explosionY, explosionZ, 1.0D, 0.0D, 0.0D);
		}

		Iterator<ChunkPosition> iterator;
		ChunkPosition chunkposition;
		int i;
		int j;
		int k;
		Block block;

		if (isSmoking)
		{
			iterator = affectedBlockPositions.iterator();

			while (iterator.hasNext())
			{
				chunkposition = iterator.next();
				i = chunkposition.chunkPosX;
				j = chunkposition.chunkPosY;
				k = chunkposition.chunkPosZ;
				block = worldObj.getBlock(i, j, k);

				if (spawnParticles)
				{
					float d0 = i + worldObj.rand.nextFloat();
					float d1 = j + worldObj.rand.nextFloat();
					float d2 = k + worldObj.rand.nextFloat();
					float d3 = d0 - explosionX;
					float d4 = d1 - explosionY;
					float d5 = d2 - explosionZ;
					float d6 = MathHelper.sqrt_double(d3 * d3 + d4 * d4 + d5 * d5);
					d3 /= d6;
					d4 /= d6;
					d5 /= d6;
					float d7 = (float) (0.5D / (d6 / explosionSize + 0.1D));
					d7 *= worldObj.rand.nextFloat() * worldObj.rand.nextFloat() + 0.3F;
					d3 *= d7;
					d4 *= d7;
					d5 *= d7;
					worldObj.spawnParticle("explode", (d0 + explosionX * 1.0D) / 2.0D, (d1 + explosionY * 1.0D) / 2.0D, (d2 + explosionZ * 1.0D) / 2.0D, d3, d4, d5);
					worldObj.spawnParticle("smoke", d0, d1, d2, d3, d4, d5);
				}

				if (block.getMaterial() != Material.air)
				{
					if (block.canDropFromExplosion(this))
					{
						block.dropBlockAsItemWithChance(worldObj, i, j, k, worldObj.getBlockMetadata(i, j, k), 1.0F / explosionSize, 0);
					}

					block.onBlockExploded(worldObj, i, j, k, this);
				}
			}
		}

		if (isFlaming)
		{
			iterator = affectedBlockPositions.iterator();

			while (iterator.hasNext())
			{
				chunkposition = iterator.next();
				i = chunkposition.chunkPosX;
				j = chunkposition.chunkPosY;
				k = chunkposition.chunkPosZ;
				block = worldObj.getBlock(i, j, k);
				Block block1 = worldObj.getBlock(i, j - 1, k);

				if (block.getMaterial() == Material.air && block1.func_149730_j() && explosionRNG.nextInt(3) == 0)
				{
					worldObj.setBlock(i, j, k, Blocks.fire);
				}
			}
		}
	}

	public Vector3F getVec(int x, int y, int z, Vector3F vec)
	{
		if (vec.length() > explosionSize) return null;
		Block b = worldObj.getBlock(x, y, z);
		float resis = b.getExplosionResistance(exploder, worldObj, x, y, z, explosionX, explosionY, explosionZ);
		float f = 100 - resis * Rand.nextInt(20) + 40 - vec.mult(4).lengthSquared();
		boolean bool = Rand.isPercent(f);
		System.err.println(b.getLocalizedName() + ", " + resis + ", " + vec.toString() + ", " + vec.length() + ", " + f);
		return b.getBlockHardness(worldObj, x, y, z) >= 0 && bool ? vec : null;
	}

	@Override
	public Map<EntityPlayer, Vector3F> func_77277_b()
	{
		return affectedPlayers;
	}

	@Override
	public EntityLivingBase getExplosivePlacedBy()
	{
		return exploder instanceof EntityTNTPrimed ? ((EntityTNTPrimed) exploder).getTntPlacedBy() : exploder instanceof EntityLivingBase ? (EntityLivingBase) exploder : null;
	}

	public boolean someEntityFunc(Entity entity, int x, int y, int z, float f1)
	{
		return entity.func_145774_a(this, worldObj, x, y, z, worldObj.getBlock(x, y, z), f1);
	}

	public float someOtherEntityFunction(Entity entity, int x, int y, int z)
	{
		return entity.func_145772_a(this, worldObj, x, y, z, worldObj.getBlock(x, y, z));
	}

	public static Explode createExplosion(Entity cause, float x, float y, float z, float strength, boolean isFlaming, boolean isSmoking)
	{
		Explode explosion = new Explode(cause.worldObj, cause, x, y, z, strength);
		explosion.isFlaming = isFlaming;
		explosion.isSmoking = isSmoking;
		explosion.doExplosionA();
		explosion.doExplosionB(true);
		return explosion;
	}

	public static Explode createExplosion(Entity cause, float strength, boolean isFlaming, boolean isSmoking)
	{
		Explode explosion = new Explode(cause, strength);
		explosion.isFlaming = isFlaming;
		explosion.isSmoking = isSmoking;
		explosion.doExplosionA();
		explosion.doExplosionB(true);
		return explosion;
	}
}