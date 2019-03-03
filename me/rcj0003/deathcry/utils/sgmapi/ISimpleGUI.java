package me.rcj0003.deathcry.utils.sgmapi;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public interface ISimpleGUI {
	String getId();
	Plugin getPlugin();
	
	//Inventory management for the in-game GUIs.
	Inventory getInventory(Player player, Object arguments);
	void openInventory(Player player, Object arguments);
	void deregisterInventory(Inventory inventory);
	boolean isOwner(Inventory inventory);
	
	//Actions for inventory clicks.
	boolean runAction(int slot, Player player, Object argument);
	BukkitRunnable getAction(int slot, Player player, Object argument);
}