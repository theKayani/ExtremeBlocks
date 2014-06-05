package extremeblocks.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import com.hk.testing.util.BlockCustom;
import extremeblocks.Init;

public class BlockPowderKeg extends BlockCustom
{
	public BlockPowderKeg()
	{
		super(Material.wood, "Powder Keg");
		this.setBlockTextureName(Init.MODID + ":powder_keg");
		this.setCreativeTab(Init.tab_misc);
		this.setBlockBounds(0.25F, 0.0F, 0.25F, 0.75F, 0.8F, 0.75F);
	}

	public boolean isOpaqueCube()
	{
		return false;
	}

	public boolean renderAsNormalBlock()
	{
		return false;
	}

	public void onNeighborBlockChange(World world, int x, int y, int z, Block lol)
	{
		for (int i = -1; i < 2; i++)
		{
			for (int k = -1; k < 2; k++)
			{
				if (!world.isRemote && world.getBlock(x + i, y, z + k) == Blocks.fire)
				{
					explode(world, x, y, z);
				}
			}
		}
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int lol, float sideX, float sideY, float sideZ)
	{
		player.addChatMessage(new ChatComponentTranslation("Side X: " + sideX + ", Side Y: " + sideY + ", Side Z: " + sideZ));
		if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == Items.flint_and_steel)
		{
			explode(world, x, y, z);
			player.getCurrentEquippedItem().damageItem(1, player);
			return true;
		}
		return false;
	}

	public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion explosion)
	{
		explode(world, x, y, z);
	}

	private void explode(World world, int x, int y, int z)
	{
		if(!world.isRemote)
		{
			world.createExplosion(null, x, y, z, 4.0F, true);
		}
	}

	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		if (entity instanceof EntityArrow && !world.isRemote)
		{
			EntityArrow entityarrow = (EntityArrow)entity;

			if (entityarrow.isBurning())
			{
				explode(world, x, y, z);
			}
		}
	}

	public boolean canDropFromExplosion(Explosion explosion)
	{
		return false;
	}
}
