package me.shock.shockpvp;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener
{

	final Main plugin;
	public DeathListener(Main instance)
	{
		plugin = instance;
	}
	
	//private Config config;
	
	double deathexplodesize;
	double deathexpdrop;
	
	
	/*
	 * Different things to do on player death.
	 * Add options to configure these more in the config.
	 * Open to more ideas :D
	 */
	
	@EventHandler(priority = EventPriority.LOW)
	public void onDeath(PlayerDeathEvent event)
	{
		Player player = event.getEntity();
		
		/*
		 *  Explode on death.
		 */
		if(player.hasPermission("shockpvp.deathexplode"))
		{
			Location loc = player.getLocation();
			loc.getWorld().createExplosion(loc, (float) deathexplodesize);
		}
		
		/*
		 *  Keep exp level on death. I don't know why yet :o
		 */
		if(player.hasPermission("shockpvp.keepexp"))
		{
			((PlayerDeathEvent) player).setKeepLevel(true);
		}
		
		/*
		 * Cancel death messages for anyone without this permission.
		 */
		if(player.hasPermission("shockpvp.nodeathmessage"))
		{
			event.setDeathMessage(null);
		}
		
		/*
		 * Set money lost on death
		 */
		if(player.hasPermission("shockpvp.deathtax"))
		{
			
		}
		
		/*
		 * Set exp drop amount on death.
		 */
		if(player.hasPermission("shockpvp.deathxp"))
		{
			event.setDroppedExp((int) deathexpdrop);
		}
		
	}
	
	
}
