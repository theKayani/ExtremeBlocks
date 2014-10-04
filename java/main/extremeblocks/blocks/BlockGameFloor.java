package main.extremeblocks.blocks;

import java.util.Random;
import main.com.hk.eb.util.BlockCustom;
import main.com.hk.eb.util.MPUtil;
import main.extremeblocks.Init;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockGameFloor extends BlockCustom
{
	private final GameBlockType type;

	public BlockGameFloor(GameBlockType type)
	{
		super(Material.rock, type.name + " Game Block");
		this.setBlockTextureName(Init.MODID + ":" + (type == GameBlockType.SPREAD ? type.name.toLowerCase() + "_" : "") + "game_block");
		this.type = type;
	}

	@Override
	public void onEntityWalking(World world, int x, int y, int z, Entity entity)
	{
		spreadBlock(world, x, y, z, entity);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		spreadBlock(world, x, y, z, entity);
	}

	@Override
	public void onFallenUpon(World world, int x, int y, int z, Entity entity, float lol)
	{
		spreadBlock(world, x, y, z, entity);
	}

	public void spreadBlock(World world, int x, int y, int z, Entity entity)
	{
		if (type == GameBlockType.SPREAD && entity instanceof EntityLivingBase)
		{
			for (int i = -1; i < 2; i++)
			{
				world.setBlock(x + i, y, z, Init.spread_game_block);
			}
			for (int i = -1; i < 2; i++)
			{
				world.setBlock(x, y, z + i, Init.spread_game_block);
			}
		}
	}

	@Override
	public Item getItemDropped(int meta, Random rand, int idk)
	{
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getItem(World p_149694_1_, int p_149694_2_, int p_149694_3_, int p_149694_4_)
	{
		return Item.getItemFromBlock(type == GameBlockType.SPREAD ? Init.spread_game_block : Init.red_game_floor);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int colorMultiplier(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_)
	{
		switch (type)
		{
			case RED:
				return 0xFF0000;
			case BLUE:
				return 0x0000FF;
			case GREEN:
				return 0x00FF00;
			case YELLOW:
				return 0xFFFF00;
			default:
				break;
		}
		return super.colorMultiplier(p_149720_1_, p_149720_2_, p_149720_3_, p_149720_4_);
	}

	@Override
	public int getBlockColor()
	{
		switch (type)
		{
			case RED:
				return 0xFF0000;
			case BLUE:
				return 0x0000FF;
			case GREEN:
				return 0x00FF00;
			case YELLOW:
				return 0xFFFF00;
			default:
				break;
		}
		return super.getBlockColor();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getRenderColor(int flag)
	{
		switch (type)
		{
			case RED:
				return 0xFF0000;
			case BLUE:
				return 0x0000FF;
			case GREEN:
				return 0x00FF00;
			case YELLOW:
				return 0xFFFF00;
			default:
				break;
		}
		return super.getRenderColor(flag);
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int idk, float sideX, float sideY, float sideZ)
	{
		ItemStack held = player.getHeldItem();
		if (held != null && held.getItem() == Init.game_remote && type != GameBlockType.SPREAD)
		{
			if (MPUtil.isServerSide())
			{
				world.setBlock(x, y, z, type.getNext());
			}
			return true;
		}
		return false;
	}

	public enum GameBlockType
	{
		RED, BLUE, GREEN, YELLOW, SPREAD;

		public final String name;

		private GameBlockType()
		{
			name = name().substring(0, 1).toUpperCase() + name().substring(1).toLowerCase();
		}

		public Block getNext()
		{
			switch (this)
			{
				case BLUE:
					return Init.green_game_floor;
				case GREEN:
					return Init.yellow_game_floor;
				case RED:
					return Init.blue_game_floor;
				case YELLOW:
					return Init.red_game_floor;
				default:
					return null;
			}
		}
	}
}
