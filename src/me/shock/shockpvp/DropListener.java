package me.shock.shockpvp;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropListener implements Listener
{

	final Main plugin;
	public DropListener(Main instance)
	{
		plugin = instance;
	}
	
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent event)
	{
		Player player = event.getPlayer();
		
		if(player.hasPermission("shockpvp.drop"))
			event.setCancelled(false);
		
		else
			event.setCancelled(true);
	}
	
}
