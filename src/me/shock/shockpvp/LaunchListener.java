package me.shock.shockpvp;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public class LaunchListener implements Listener
{

	
	final Main plugin;
	public LaunchListener(Main instance)
	{
		plugin = instance;
	}
	
	@EventHandler
	public void onBowShoot(ProjectileLaunchEvent event)
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
	
}
