package me.shock.shockpvp;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener
{

	public Main plugin;
	public DeathListener(Main instance)
	{
		this.plugin = instance;
	}
	
	
	public static Economy econ = null;
	
	
	/*
	 * Different things to do on player death.
	 * Add options to configure these more in the config.
	 * Open to more ideas :D
	 */
	
	
	@EventHandler(priority = EventPriority.LOW)
	public void onDeath(PlayerDeathEvent event)
	{
		Player player = event.getEntity();
		String name = player.getName();
		double deathexplodesize = plugin.getConfig().getDouble("deathexplodesize");
		double deathexpdrop = plugin.getConfig().getDouble("deathexpdrop");
		double deathmoneyloss = plugin.getConfig().getDouble("deathmoneyloss");
		
		/*
		 *  Explode on death.
		 */
		if(player.hasPermission("shockpvp.deathexplode"))
		{
			Location loc = player.getLocation();
			loc.getWorld().createExplosion(loc, (float) deathexplodesize, false);
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
		 * 
		 * Kick if not enough money to respawn.
		 * Add temp ban in later.
		 */
		if(player.hasPermission("shockpvp.deathtax"))
		{
			double balance = econ.getBalance(player.getName());
			EconomyResponse r = econ.withdrawPlayer(name, deathmoneyloss);
	  
			if (balance >= deathmoneyloss && r.transactionSuccess())
			{
				player.sendMessage(ChatColor.RED + "" + deathmoneyloss + ChatColor.YELLOW + " taken for dying.");
			}
			else
			{
				player.kickPlayer("You died without enough money to respawn. You can log back in or go to www.swarlocraft.com to purchase more credits.");
			}
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