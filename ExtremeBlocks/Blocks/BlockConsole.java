package ExtremeBlocks.Blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ExtremeBlocks.ExtremeBlocksMain;
import ExtremeBlocks.Vars;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockConsole extends Block 
{
	public static int blockX;
	public static int blockZ;

	public BlockConsole(int par1, Material par2Material) 
	{
		super(par1, par2Material);
		this.setHardness(2.0F);
		this.setUnlocalizedName("Console");
		this.setCreativeTab(ExtremeBlocksMain.EBMiscTab);
		this.setStepSound(soundStoneFootstep);
		this.setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 0.4F, 0.9F);
	}

	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
	{  
		if(!Vars.CFRestartConsole)
		{
			this.consolesGame(par1World, par2, par3, par4, par5EntityPlayer);
		}	
		else if(Vars.CFRestartConsole)
		{
			if(!Vars.isSure)
			{
				par5EntityPlayer.addChatMessage("WARNING: It will be twice as Hard! and the last time for you, " + par5EntityPlayer.username + ".");
				par5EntityPlayer.addChatMessage("Click Again if you are sure you want to Reset the game?");

				Vars.isSure = true;
			}
			else if(Vars.isSure)
			{
				Vars.gotReward = false;
				Vars.playing = false;
				Vars.startedGame = false;
				Vars.CFRestartConsole = false;

				Vars.x = 0;
				Vars.y = 0;
				Vars.z = 0;
				Vars.timesTried = 0;

				par5EntityPlayer.addChatMessage("You Have Sucessfully reset the Game!");
				par5EntityPlayer.addChatMessage("Go Ahead and Play! Same Rules!");
			}
		}
		return Vars.CFDoGamesWork;
	}  


	private void createStage(World par1World, int par2, int par3, int par4) 
	{
		blockX = 20 + par1World.rand.nextInt(12) + Vars.isNumber(Vars.CFGameDifficulty, 1, 100);
		blockZ = 20 + par1World.rand.nextInt(12) + Vars.isNumber(Vars.CFGameDifficulty, 1, 100);
		if(Vars.isSure)
		{
			blockX = blockX * 2;
			blockZ = blockZ * 2;
		}

		for(int i = -3; i <= 3; i++)
		{
			for(int k = -3; k <= 3; k++)
			{
				par1World.setBlock(i, 250, k, ExtremeBlocksMain.RedGameFloor.blockID);
				par1World.setBlock(i, 249, k, ExtremeBlocksMain.RedGameFloor.blockID);

				par1World.setBlock(5, 250, 0, ExtremeBlocksMain.SpreadGameBlock.blockID);

				par1World.setBlock(0, 250, 5, ExtremeBlocksMain.SpreadGameBlock.blockID);

				par1World.setBlock(-5, 250, 0, ExtremeBlocksMain.SpreadGameBlock.blockID);

				par1World.setBlock(0, 250, -5, ExtremeBlocksMain.SpreadGameBlock.blockID);

				par1World.setBlock(blockX, 251, blockZ, ExtremeBlocksMain.RewardBlock.blockID);
				par1World.setBlock(blockX, 250, blockZ, ExtremeBlocksMain.RedGameFloor.blockID);
			}
		}
	}

	private void startPlaying(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer) 
	{
		Vars.timesTried++;
		Vars.playing = true;

		if(!par5EntityPlayer.inventory.hasItem(ExtremeBlocksMain.Returner.itemID))
		{
			par5EntityPlayer.inventory.addItemStackToInventory(new ItemStack(ExtremeBlocksMain.Returner));
		}
	}

	private void consolesGame(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer)
	{
		ItemStack item = par5EntityPlayer.getCurrentEquippedItem();

		if(!Vars.CFRestartConsole)
		{
			if(Vars.CFDoGamesWork)
			{
				if(!Vars.isStarting)
				{
					par5EntityPlayer.addChatMessage("Are you Sure? Is VERY glitchy!");
					par5EntityPlayer.addChatMessage("Click again to Confirm!");

					Vars.isStarting = true;
				}
				else if(Vars.isStarting)
				{
					if(item != null && item.itemID == Item.recordBlocks.itemID)
					{
						if(Vars.startedGame)
						{
							if(Vars.playing)
							{
								par5EntityPlayer.addChatMessage("You need to use the returner to exit Gameplay");
							}
							else if(!Vars.playing)
							{
								startPlaying(par1World, par2, par3, par4, par5EntityPlayer);
								
								par5EntityPlayer.setPosition(0, 253, 0);
							}
						}
						else if(!Vars.startedGame)
						{
							Vars.x = par2;
							Vars.y = par3;
							Vars.z = par4;

							createStage(par1World, par2, par3, par4);

							startPlaying(par1World, par2, par3, par4, par5EntityPlayer);
							
							par5EntityPlayer.setPosition(0, 253, 0);

							par5EntityPlayer.inventory.addItemStackToInventory(new ItemStack(ExtremeBlocksMain.Notes));
							par5EntityPlayer.inventory.addItemStackToInventory(new ItemStack(ExtremeBlocksMain.Notes1));

							Vars.startedGame = true;
						}
					}
					else if(item != null && item.itemID != Item.recordBlocks.itemID)
					{
						par5EntityPlayer.addChatMessage("You need the Record 'blocks' to Play Finders Keepers!");
					}
				}
			}
			else if(!Vars.CFDoGamesWork)
			{
				par5EntityPlayer.addChatMessage("Change the 'DoGamesWork' option in the Mod's Config File!");
			}

		}
	}

	public boolean isOpaqueCube() 
	{
		return false;
	}

	public boolean renderAsNormalBlock() 
	{
		return false;
	}

	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) 
	{
		this.blockIcon = par1IconRegister.registerIcon(ExtremeBlocksMain.modid + ":" + (this.getUnlocalizedName().substring(5)));
	}
}