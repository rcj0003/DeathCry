package me.rcj0003.deathcry;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.rcj0003.deathcry.commands.DeathCryCommand;
import me.rcj0003.deathcry.crys.CryManager;
import me.rcj0003.deathcry.guis.CryGUI;
import me.rcj0003.deathcry.listeners.DeathCryListener;
import me.rcj0003.deathcry.utils.sgmapi.SimpleGUIManager;

public class DeathCryPlugin extends JavaPlugin {
	private static DeathCryPlugin instance;
	private CryManager cryManager = new CryManager();

	@Override
	public void onEnable() {
		instance = instance == null ? this : instance;

		getCommand("deathcry").setExecutor(new DeathCryCommand());
				
		Bukkit.getServer().getPluginManager().registerEvents(new DeathCryListener(), this);
		Bukkit.getServer().getPluginManager().registerEvents(SimpleGUIManager.getInstance(), this);
		
		SimpleGUIManager.getInstance().registerGUI(new CryGUI());
	}

	@Override
	public void onDisable() {
	}

	public static DeathCryPlugin getInstance() {
		return instance;
	}

	public CryManager getCryManager() {
		return cryManager;
	}
}