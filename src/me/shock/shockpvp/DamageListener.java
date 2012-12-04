package me.shock.shockpvp;

import org.bukkit.entity.Boat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListener implements Listener
{

	final Main plugin;
	public DamageListener(Main instance)
	{
		plugin = instance;
	}
	
	/*
	 * Cancel players damage taken if in a boat with permission.
	 */
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent event)
	{
		Entity entity = event.getEntity();
		if(entity instanceof Player)
		{
			Player player = ((Player) entity).getPlayer();
			if(player.hasPermission("shockpvp.boatprotect"))
			{
				Vehicle vehicle = (Vehicle) player.getVehicle();
				if(vehicle instanceof Boat)
				{
					event.setCancelled(true);
				}
			}
			
		}
		else
			return;
		
	}
	
	
	/*
	 * Cancel fall damage.
	 */
	
	@EventHandler
	public void onFall(EntityDamageEvent event)
	{
		if((event.getCause() == EntityDamageEvent.DamageCause.FALL) && (event.getEntity() instanceof Player))
		{
			event.setCancelled(true);
		}
	}
	
}
