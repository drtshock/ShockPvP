package me.shock.shockpvp;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{

	/*
	 * Variables for config
	 */
	double deathexplodesize = 2;
	double deathexpdrop = 10;
	double deathmoneyloss=  10;
	double noarmordamagemultiplier = 2;
	double flashradius = 5;
	
	private Logger log = Bukkit.getLogger();
 	public static Economy econ = null;
 	
 	public Main plugin;
	
	
	public void onEnable()
	{
		PluginManager pm = getServer().getPluginManager();
		
		loadConfig();
		
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
	
	
	private void loadConfig() 
	{
		try 
		{
			// Create a scanner, pass the name of the file
			// File.separator returns the system file separator: Ex for windows, it's \, for linux it is /
			Scanner s = new Scanner(new FileReader(System.getProperty("user.dir") + File.separator + "plugins" + File.separator + "ShockPvP" + File.separator + "config.yml"));
			// If there is another line inside the scanner (it reads from the document line by line, seperated by newlines)
			while (s.hasNext()) 
			{
				// Create an array of 2 pieces of data from the next line inside the file
				// Ex: "deathexplodezie=20" -> read[0] = deathexplodesize, read[1] = 20
				String[] read = s.next().split(Pattern.quote("="));
				// Check what read[0] in the if statement
				if (read[0].equals("deathexplodesize"))
					this.deathexplodesize = Double.valueOf(read[1]); // Assign the value depending on what read[0] is
				if(read[0].equals("deathexpdrop"))
					this.deathexpdrop = Double.valueOf(read[1]);
				if(read[0].equals("deathmoneyloss"))
					this.deathmoneyloss = Double.valueOf(read[1]);
				if(read[0].equals("noarmordamagemultiplier"))
					this.noarmordamagemultiplier = Double.valueOf(read[1]);
				if(read[0].equals("flashradius"))
					this.flashradius = Double.valueOf(read[1]);
			}
			// Close the scanner to allow for other programs to write to the file + tell OS that we are done
			s.close();
			this.log.info("[ShockPvP] config loaded");
		}
		catch (Exception e) 
		{
			this.log.log(Level.SEVERE, "[ShockPvP] Failed to load config", e);
			e.printStackTrace();
			// Hopefully never happens :o
		}
	}
	
	@Override
	public void saveConfig() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + File.separator + "plugins" + File.separator + "ShockPvP" + File.separator + "config.yml"));
			bw.write(("deathexplodesize=" + String.valueOf(this.deathexplodesize) + "\n"));
			bw.write(("deathexpdrop=" + String.valueOf(this.deathexpdrop) + "\n"));
			bw.write(("deathmoneyloss=" + String.valueOf(this.deathmoneyloss) + "\n"));
			bw.write(("noarmordamagemultiplier=" + String.valueOf(this.noarmordamagemultiplier) + "\n"));
			bw.write(("flashradius=" + String.valueOf(this.flashradius) + "\n"));
			bw.flush();
			bw.close();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
}
