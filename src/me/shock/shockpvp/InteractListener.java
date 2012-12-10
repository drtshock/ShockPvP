package me.shock.shockpvp;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractListener implements Listener
{

	final Main plugin;
	public InteractListener(Main instance)
	{
		plugin = instance;
	}
	
	/*
	 * Doesn't work :(
	 */
	
	/*
	@EventHandler
	public void onInteractEnderChest(PlayerInteractEvent event)
	{
		Action action = event.getAction();
		if (action == Action.RIGHT_CLICK_BLOCK)
		{
			Player player = event.getPlayer();
			Block block = event.getClickedBlock();
			Material mat = block.getType();
			if(player.hasPermission("shockpvp.enderchest") && mat == (Material.ENDER_CHEST))
			{
				return;
			}
			if(!(player.hasPermission("shockpvp.enderchest") && mat == Material.ENDER_CHEST))
			{
				event.setCancelled(true);
				player.sendMessage(ChatColor.RED + "You do not have permission to use enderchests");
			}
			
		}
	}
	*/
	
}
