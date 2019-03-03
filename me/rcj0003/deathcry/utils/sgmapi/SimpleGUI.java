package me.rcj0003.deathcry.utils.sgmapi;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class SimpleGUI implements ISimpleGUI {

	ArrayList<Inventory> guis = new ArrayList<Inventory>();
	
	@Override
	public void openInventory(Player player, Object arguments) {
		Inventory i = getInventory(player, arguments);
		if (registerInventory(i)) player.openInventory(i);
	}
	
	public boolean registerInventory(Inventory inv) {
		return guis.contains(inv) ? false : guis.add(inv);
	}

	@Override
	public void deregisterInventory(Inventory inv) {
		guis.remove(inv);
	}

	@Override
	public boolean isOwner(Inventory inv) {
		return guis.contains(inv);
	}

	@Override
	public boolean runAction(int slot, Player player, Object sender) {
		BukkitRunnable r = getAction(slot, player, sender);
		return r == null ? false : r.runTask(getPlugin()) != null;
	}
}