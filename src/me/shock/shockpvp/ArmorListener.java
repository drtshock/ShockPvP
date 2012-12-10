package me.shock.shockpvp;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;

public class ArmorListener implements Listener
{

	final Main plugin;
	public ArmorListener(Main instance)
	{
		plugin = instance;
	}
	
	
	/*
	 * Invisible on sneak with leather boots
	 * Deal more damage if no armor.
	 * Doesn't hide player while sneaking
	 */
	
	@EventHandler
	public void toggleSneak(PlayerToggleSneakEvent event)
	{
		Player player = event.getPlayer();
		ItemStack boots = player.getInventory().getBoots();
		if(boots != null)
		{
		Material mat = boots.getType();
		
		if((player.hasPermission("shockpvp.sneak")) && (mat == Material.LEATHER_BOOTS))
		{
			if(event.isSneaking())
				for(Player players : Bukkit.getOnlinePlayers())
				{
					players.hidePlayer(player);
				}
			else
				for(Player players : Bukkit.getOnlinePlayers())
				{
					players.showPlayer(player);
				}
			return;
		}
	}
	}
	
	
	/*
	 * Change block at foot if player is invisible sneaking.
	 */
	

	@EventHandler
	public void onMove(PlayerMoveEvent event)
	{
		Player player = event.getPlayer();
		if(player.isSneaking() && player.hasPermission("shockpvp.sneak"))
		{
			Location loc = player.getLocation();
			Block block = loc.getBlock().getRelative(BlockFace.DOWN);
			ItemStack blockType = new ItemStack(block.getType());
			if(blockType.getType() == Material.GRASS)
			{
				block.setType(Material.DIRT);
			}
			return;
		}
		
		/*
		 * Need to check for empty armor.
		 */
		
		// Get the players armor.
		/*
		ItemStack bootsi = player.getInventory().getBoots();
		if(bootsi != null)
		{
			Material boots = player.getInventory().getBoots().getType();
		}
		ItemStack legsi = player.getInventory().getLeggings();
		if(legsi != null)
		{
			Material legs = player.getInventory().getBoots().getType();
		}
		ItemStack chesti = player.getInventory().getBoots();
		if(chesti != null)
		{
			Material chest = player.getInventory().getChestplate().getType();
		}
		ItemStack helmi = player.getInventory().getBoots();
		if(helmi != null)
		{
			Material helm = player.getInventory().getBoots().getType();
		}
		*/
		
		/*
		 * Doesn't work :(
		Material boots = player.getInventory().getBoots().getType();
		Material legs = player.getInventory().getLeggings().getType();
		Material chest = player.getInventory().getChestplate().getType();
		Material helm = player.getInventory().getHelmet().getType();
		*/
		
		// Slow player with all diamond armor.
		/*
		 * Doesn't work :(
		if  (boots == Material.DIAMOND_BOOTS
				&& legs == Material.DIAMOND_LEGGINGS
				&& chest == Material.DIAMOND_CHESTPLATE
				&& helm == Material.DIAMOND_HELMET)
			{
				player.getVelocity().multiply(.75);
			}
		
		// Slow player without chestplate.
		if  (boots == Material.DIAMOND_BOOTS
				&& legs == Material.DIAMOND_LEGGINGS
				&& helm == Material.DIAMOND_HELMET)
		{
			player.getVelocity().multiply(.85);
		}
		
		// Slow player without leggings
		if  (boots == Material.DIAMOND_BOOTS
				&& chest == Material.DIAMOND_CHESTPLATE
				&& helm == Material.DIAMOND_HELMET)
			{
				player.getVelocity().multiply(.85);
			}
		
		// Slow player without helmet
		if  (boots == Material.DIAMOND_BOOTS
				&& legs == Material.DIAMOND_LEGGINGS
				&& chest == Material.DIAMOND_CHESTPLATE)
			{
				player.getVelocity().multiply(.78);
			}
		
		// Slow player without boots.
		if  (legs == Material.DIAMOND_LEGGINGS
				&& chest == Material.DIAMOND_CHESTPLATE
				&& helm == Material.DIAMOND_HELMET)
			{
				player.getVelocity().multiply(.79);
			}
		
		// Speed up player with leather armor
		if (boots == Material.LEATHER_BOOTS
				&& legs == Material.LEATHER_LEGGINGS
				&& chest == Material.LEATHER_CHESTPLATE
				&& helm == Material.LEATHER_HELMET)
		{
			player.getVelocity().multiply(1.2);
		}
		*/
	}
}
