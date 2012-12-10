package me.shock.shockpvp;

import java.io.File;
import java.util.logging.Logger;
import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{

	protected FileConfiguration config;
	File file;
	

	
	private Logger log = Bukkit.getLogger();
 	public static Economy econ = null;
 	
 	public static Main plugin;
	
	
	public void onEnable()
	{
		PluginManager pm = getServer().getPluginManager();
		
		loadConfig();
		saveConfig();
		
		pm.registerEvents(new DropListener(this), this);
		pm.registerEvents(new InteractListener(this), this);
		pm.registerEvents(new LaunchListener(this), this);
		pm.registerEvents(new DeathListener(this), this);
		pm.registerEvents(new DamageListener(this), this);
		pm.registerEvents(new ArmorListener(this), this);

		/**
		 * @author Poo poo
		 */
		try 
		{
			setupEconomy();
			File dirPlgFolder = new File(System.getProperty("user.dir") + File.separator + "plugins" + File.separator + "ShockPvP"); 
			if(!dirPlgFolder.isDirectory())
				dirPlgFolder.mkdir();
		}
		catch(Exception e)
		{
			log.info("[ShockPvP] can't find vault :(");
			log.info("[ShockPvP] not using economy");
		}
		
	}
	
	private void loadConfig()
	{
		this.file = new File(getDataFolder() + "/config.yml");
		if (!this.file.exists())
		{
			getConfig().options().copyDefaults(true);
		}
	}
	
	public void onDisable()
	{
		saveConfig();
	}
	
	private boolean setupEconomy() 
	{
		if (getServer().getPluginManager().getPlugin("Vault") == null) 
		{
			return false;
		}
		RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
		if (rsp == null) 
		{
			return false;
		}
		econ = rsp.getProvider();
		return econ != null;
	}
	
}
