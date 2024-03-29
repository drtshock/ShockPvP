package me.shock.shockpvp;

import org.bukkit.entity.Boat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

public class DamageListener implements Listener
{

	public Main plugin;
	public DamageListener(Main instance)
	{
		this.plugin = instance;
	}
	
	
	/*
	 * Cancel players damage taken if in a boat with permission.
	 */
	
	@EventHandler
	public void onDamage(EntityDamageByEntityEvent event)
	{
		Entity entity = event.getEntity();
		double noarmordamagemultiplier = plugin.getConfig().getDouble("noarmordamagemultiplier");
		
		if(entity instanceof Player)
		{
			Player player = ((Player) entity).getPlayer();
			ItemStack[] item = player.getInventory().getArmorContents();
			if(item.length == 0)
			{
				int damage = event.getDamage();
				int newdamage = (int) (damage * noarmordamagemultiplier);
				event.setDamage(newdamage);
			}
			if(player.hasPermission("shockpvp.nodamage.inboat"))
			{
				Vehicle vehicle = (Vehicle) player.getVehicle();
				if(vehicle instanceof Boat)
				{
					event.setCancelled(true);
				}
			}
		}
	}
	
	
	/*
	 * Cancel fall damage for everyone
	 * Cancel drowning in water by permission.
	 * Cancel lava damage by permission.
	 */
	
	@EventHandler
	public void onFall(EntityDamageEvent event)
	{
		if((event.getCause() == EntityDamageEvent.DamageCause.FALL) && (event.getEntity() instanceof Player))
		{
			Entity entity = event.getEntity();
			Player player = ((Player) entity).getPlayer();
			if(player.hasPermission("shockpvp.nodamage.fall"))
			{
				event.setCancelled(true);
			}
		}
		
		if((event.getCause() == EntityDamageEvent.DamageCause.DROWNING) && (event.getEntity() instanceof Player))
		{
			Entity entity = event.getEntity();
			Player player = ((Player) entity).getPlayer();
			if(player.hasPermission("shockpvp.nodamage.drown"))
			{
				event.setCancelled(true);
			}
		}
		
		// No work :(
		if((event.getCause() == EntityDamageEvent.DamageCause.LAVA) && (event.getEntity() instanceof Player))
		{
			Entity entity = event.getEntity();
			Player player = ((Player) entity).getPlayer();
			if(player.hasPermission("shockpvp.nodamage.lava"))
			{
				event.setCancelled(true);
			}
		}
		
		if((event.getCause() == EntityDamageEvent.DamageCause.MAGIC) && (event.getEntity() instanceof Player))
		{
			Entity entity = event.getEntity();
			Player player = ((Player) entity).getPlayer();
			if(player.hasPermission("shockpvp.nodamage.potion"))
			{
				event.setCancelled(true);
			}
		}
	}
}