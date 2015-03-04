package main.extremeblocks.blocks.energy;

import main.extremeblocks.Init;
import main.extremeblocks.client.containers.ContainerEB;
import main.extremeblocks.client.containers.energy.ContainerBaseProtector;
import main.extremeblocks.client.guis.GuiEB;
import main.extremeblocks.client.guis.energy.GuiBaseProtector;
import main.extremeblocks.tileentities.TileEntityInventory;
import main.extremeblocks.tileentities.energy.TileEntityBaseProtector;
import main.extremeblocks.tileentities.energy.TileEntityMachine;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class BlockBaseProtector extends BlockMachine
{
	public BlockBaseProtector()
	{
		super("Base Protector");
		setHardness(1.5F);
		setInfo("This block is the Base Protector. Similarily to previous versions, it protects mobs in a 10x10x10 radius of the block. It will drop arrows onto enemies consuming 2048 RF per arrow dropped. There are many modifiers you can have on the machine as well!");
	}

	@Override
	public Class<? extends TileEntityMachine> getTileClass()
	{
		return TileEntityBaseProtector.class;
	}

	@Override
	public String getTopTexture()
	{
		return "";
	}

	@Override
	public String getFrontTexture()
	{
		return "";
	}

	@Override
	public String getSideTexture()
	{
		return "";
	}

	@Override
	public GuiEB getGui(InventoryPlayer inventory, TileEntity tile)
	{
		return new GuiBaseProtector(inventory, (TileEntityBaseProtector) tile);
	}

	@Override
	public ContainerEB getContainer(InventoryPlayer inventory, TileEntity tile)
	{
		return new ContainerBaseProtector(inventory, (TileEntityInventory) tile);
	}

	public static enum ProtectorUpgrade
	{
		DAMAGE,
		SPEED,
		RADIUS,
		KNOCKBACK,
		ENERGY_REDUCTION;

		public boolean isValid(ItemStack stack)
		{
			if (stack == null || stack.stackSize <= 0) return false;
			Item item = stack.getItem();
			switch (this)
			{
				case DAMAGE:
					return item == Init.spirit_fragment;
				case ENERGY_REDUCTION:
					return item == Item.getItemFromBlock(Blocks.gold_block);
				case KNOCKBACK:
					return item == Items.golden_apple;
				case RADIUS:
					return item == Items.diamond;
				case SPEED:
					return item == Items.emerald;
			}
			return false;
		}

		public int getAmount(ItemStack stack)
		{
			int i = !isValid(stack) ? 1 : stack.stackSize;
			int val = 0;
			switch (this)
			{
				case DAMAGE:
					val = 2 + i;
					break;
				case ENERGY_REDUCTION:
					val = 1024 - 15 * i;
					break;
				case SPEED:
					val = 20 - i;
					break;
				case RADIUS:
					val = 10 + i;
					break;
				case KNOCKBACK:
					val = i;
					break;
			}
			return val;
		}
	}
}
