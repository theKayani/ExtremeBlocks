package extremeblocks.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.world.World;
import com.hk.testing.util.BlockCustom;
import extremeblocks.Init;

public class BlockTrash extends BlockCustom
{
	public BlockTrash()
	{
		super(Material.iron, "Trash");
		this.setBlockBounds(0.2F, 0.0F, 0.2F, 0.8F, 0.8F, 0.8F);
		this.setBlockTextureName(Init.MODID + ":trash");
		this.setCreativeTab(Init.tab_misc);
	}

	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		if (entity instanceof EntityItem)
		{
			((EntityItem) entity).setDead();
		}
	}

	public boolean renderAsNormalBlock()
	{
		return false;
	}

	public boolean isOpaqueCube()
	{
		return false;
	}
}
