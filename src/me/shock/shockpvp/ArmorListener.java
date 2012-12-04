package me.shock.shockpvp;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
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
	 */
	@EventHandler
	public void toggleSneak(PlayerToggleSneakEvent event)
	{
		Player player = event.getPlayer();
		ItemStack boots = player.getInventory().getBoots();
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
