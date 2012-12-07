package me.shock.shockpvp;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class LaunchListener implements Listener
{
	private ArrayList<Location> explodes = new ArrayList<Location>();
	
	final Main plugin;
	
	public LaunchListener(Main instance)
	{
		plugin = instance;
	}
	
	double flashRadius = this.plugin.getFlashRadius();
	
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
					
					// Simulates explosion
					explodes.add(loc);
					loc.getWorld().createExplosion(loc, 3F);
					
					// Blind and slow the nearby players.
					for (Entity ents : entities)
					{
						if (ents instanceof Player) {
							Player victim = (Player) ents;
							victim.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 1));
							victim.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 0));
						}
					}
				}
				
				// Concussion grenade.
				if(player.hasPermission("shockpvp.grenade.concussion"))
				{
					Location loc = event.getEntity().getLocation();
					List<Entity> entities = event.getEntity().getNearbyEntities(flashRadius, flashRadius, flashRadius);
					
					// Simulates explosion
					explodes.add(loc);
					loc.getWorld().createExplosion(loc, 3F);
					
					// Daze and slow nearby players.
					for (Entity ents : entities)
					{
						if(ents instanceof Player)
						{
							Player victim = (Player) ents;
							victim.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 0));
							victim.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 100, 0));
						}
					}
				}
				
				// Frag grenade
				if(player.hasPermission("shockpvp.grenade.frag"))
				{
					Location loc = event.getEntity().getLocation();
					loc.getWorld().createExplosion(loc, 1, false);
				}
				
				// Smoke grenade
				if(player.hasPermission("shockpvp.grenade.smoke"));
				{
					Location loc = event.getEntity().getLocation();
					loc.getWorld().playEffect(loc, Effect.SMOKE, 1000);
				}
				
				// Decoy grenade
				if(player.hasPermission("shockpvp.grenade.decoy"));
				Location loc = event.getEntity().getLocation();
				loc.getWorld().playEffect(loc, Effect.STEP_SOUND, 20);
				loc.getWorld().playEffect(loc, Effect.BOW_FIRE, 10);
			}
		}
		
	}
	
	/*
	 * Cancels block breaking on explosion
	 */
	@EventHandler
	public void onEntityExplode(EntityExplodeEvent event) 
	{
		if (explodes.contains(event.getLocation())) 
		{
			event.blockList().clear();
			explodes.remove(event.getLocation());
		}
	}
}
