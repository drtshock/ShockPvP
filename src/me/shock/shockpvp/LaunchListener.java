package me.shock.shockpvp;

import java.util.List;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;

public class LaunchListener implements Listener
{

	
	final Main plugin;
	public LaunchListener(Main instance)
	{
		plugin = instance;
	}
	
	double flashRadius;
	
	@EventHandler
	public void onLaunch(ProjectileLaunchEvent event)
	{
		LivingEntity shooter = event.getEntity().getShooter();
		EntityType entity = event.getEntityType();
		
		if (shooter instanceof Player && entity.equals(EntityType.ARROW))
		{
			Player player = (Player) shooter;
			Location loc = player.getLocation();
			if(player.hasPermission("shockpvp.loudbow"))
			{
				loc.getWorld().playEffect(loc, Effect.GHAST_SHOOT, 50);
				
			}
		}
	}
	
	/*
	 * Grenades
	 * - Flash
	 * - Concussion
	 * - Frag
	 * - Smoke
	 * - Decoy
	 */
	
	@EventHandler
	public void onHit(ProjectileHitEvent event)
	{
		// Get the shooter.
		LivingEntity shooter = event.getEntity().getShooter();
		
		// Get the entity shot.
		EntityType entity = event.getEntityType();
		
		// Check if it's a player.
		if(shooter instanceof Player)
		{
			// Get the Player
			Player player = (Player) shooter;
			
			// Snowball
			if(entity.equals(EntityType.SNOWBALL))
			{
				// Flash grenade.
				if(player.hasPermission("shockpvp.grenade.flash"))
				{
					Location loc = event.getEntity().getLocation();
					List<Entity> entities = event.getEntity().getNearbyEntities(flashRadius, flashRadius, flashRadius);
					
					// Blind the nearby entities.
					for(Entity entity : entities)
					{
						
					}
				}
			}
		}
		
	}
}
