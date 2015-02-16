package main.com.hk.eb.util;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class BlockIndex
{
	public final World world;
	public final int x, y, z;
	public final Vector3I vec;
	private int metadata;
	private Block block;
	private float hardness;

	public BlockIndex(World world, int x, int y, int z)
	{
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
		vec = new Vector3I(x, y, z);
		set();
	}

	public BlockIndex(World world, Vector3I vec)
	{
		this(world, vec.x, vec.y, vec.z);
	}

	private void set()
	{
		block = world.getBlock(x, y, z);
		metadata = world.getBlockMetadata(x, y, z);
		hardness = block.getBlockHardness(world, x, y, z);
	}

	public Block getBlock()
	{
		return block;
	}

	public float getHardness()
	{
		return hardness;
	}

	public int getMetadata()
	{
		return metadata;
	}

	public List<ItemStack> getDrops(int fortune)
	{
		return block.getDrops(world, x, y, z, metadata, fortune);
	}

	public List<ItemStack> getDrops()
	{
		return getDrops(0);
	}

	public TileEntity getTileEntity()
	{
		return world.getTileEntity(x, y, z);
	}

	public float getBreakSpeed(ItemStack stack)
	{
		float f = stack == null ? 1.0F : stack.getItem().getDigSpeed(stack, block, metadata);

		if (f > 1.0F)
		{
			int i = EnchantmentHelper.getEnchantmentLevel(Enchantment.efficiency.effectId, stack);

			if (i > 0)
			{
				float f1 = i * i + 1;

				boolean canHarvest = ForgeHooks.canToolHarvestBlock(block, metadata, stack);

				if (!canHarvest && f <= 1.0F)
				{
					f += f1 * 0.08F;
				}
				else
				{
					f += f1;
				}
			}
		}

		return f;
	}

	public boolean isAirBlock()
	{
		return block == Blocks.air;
	}

	@Override
	public String toString()
	{
		return block.getLocalizedName() + "-" + vec.toString() + ", " + metadata;
	}

	@Override
	public boolean equals(Object o)
	{
		return o instanceof BlockIndex ? ((BlockIndex) o).vec.equals(vec) : false;
	}

	@Override
	public int hashCode()
	{
		return vec.hashCode() + metadata + block.getLocalizedName().hashCode();
	}
}
