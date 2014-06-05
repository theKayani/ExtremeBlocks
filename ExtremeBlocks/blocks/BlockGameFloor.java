package extremeblocks.blocks;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import com.hk.testing.util.BlockCustom;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import extremeblocks.Init;

public class BlockGameFloor extends BlockCustom
{
	private final GameBlockType type;

	public BlockGameFloor(GameBlockType type)
	{
		super(Material.rock, type.name() + " Game Block");
		this.setBlockTextureName(Init.MODID + ":" + (type == GameBlockType.Spread ? type.name().toLowerCase() + "_" : "") + "game_block");

		this.type = type;
	}

	public void onEntityWalking(World world, int x, int y, int z, Entity entity)
	{
		if (type == GameBlockType.Spread && entity instanceof EntityLivingBase)
		{
			for (int i = -1; i < 2; i++)
			{
				for (int j = -1; j < 2; j++)
				{
					world.setBlock(x + i, y, z + j, Init.spread_game_block);
				}
			}
		}
	}

	public Item getItemDropped(int meta, Random rand, int idk)
	{
		return null;
	}

	@SideOnly(Side.CLIENT)
	public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_)
	{
		return Item.getItemFromBlock(type != GameBlockType.Spread ? Init.red_game_floor : Init.red_game_floor);
	}

	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_)
	{
		switch (type)
		{
			case Red:
				return 0xFF0000;
			case Blue:
				return 0x0000FF;
			case Green:
				return 0x00FF00;
			case Yellow:
				return 0xFFFF00;
			default:
				break;
		}
		return super.colorMultiplier(p_149720_1_, p_149720_2_, p_149720_3_, p_149720_4_);
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int idk, float sideX, float sideY, float sideZ)
	{
		if (!world.isRemote)
		{
			player.addChatComponentMessage(new ChatComponentTranslation(type.name() + " Floor Activated!"));
		}

		ItemStack held = player.getHeldItem();

		if (held != null && held.getItem() == Init.game_remote && type != GameBlockType.Spread)
		{
			world.setBlock(x, y, z, type.getNext());
			return true;
		}
		return false;
	}

	public enum GameBlockType
	{
		Red, Blue, Green, Yellow, Spread;

		public Block getNext()
		{
			switch (this)
			{
				case Blue:
					return Init.green_game_floor;
				case Green:
					return Init.yellow_game_floor;
				case Red:
					return Init.blue_game_floor;
				case Yellow:
					return Init.red_game_floor;
				default:
					return null;
			}
		}
	}
}
