package main.extremeblocks.blocks;

import main.com.hk.eb.util.BlockCustom;
import main.extremeblocks.Init;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockWaste extends BlockCustom
{

	public BlockWaste()
	{
		super(Material.ground, "Nuclear Sewage");
		setBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.2F, 1.0F);
		setBlockTextureName(Init.MODID + ":waste");
		setHardness(0.5F);
		setCreativeTab(Init.tab_mainBlocks);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity e)
	{
		if (e instanceof EntityLivingBase)
		{
			EntityLivingBase entity = (EntityLivingBase) e;
			if (world.rand.nextInt(10) == 0)
			{
				for (Potion potionType : Potion.potionTypes)
				{
					if (potionType != null && potionType.isBadEffect())
					{
						entity.addPotionEffect(new PotionEffect(potionType.id, 5 * 20));
					}
				}
			}
			else
			{
				for (Potion potionType : Potion.potionTypes)
				{
					if (potionType != null && !potionType.isBadEffect())
					{
						entity.addPotionEffect(new PotionEffect(potionType.id, 5 * 20));
					}
				}
			}
		}
		if (world.rand.nextInt(20) == 0)
		{
			e.setFire(100);
			e.motionX /= 3;
			e.motionY /= 3;
			e.motionZ /= 3;
		}
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
	{
		return null;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		setDown(world, x, y, z);
	}

	public void setDown(World world, int x, int y, int z)
	{
		if (world.getBlock(x, y - 1, z).isReplaceable(world, x, y, z))
		{
			world.setBlockToAir(x, y, z);
			world.setBlock(x, y - 1, z, Init.nuclear_waste);

			double d5 = x + world.rand.nextFloat();
			double d6 = y + maxY;
			double d7 = z + world.rand.nextFloat();
			world.spawnParticle("lava", d5, d6, d7, 0.0D, 0.0D, 0.0D);
			world.playSound(d5, d6, d7, "liquid.lavapop", 0.2F + world.rand.nextFloat() * 0.2F, 0.9F + world.rand.nextFloat() * 0.15F, false);
			setDown(world, x, y - 1, z);
		}
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
}
