package me.rcj0003.deathcry.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import me.rcj0003.deathcry.DeathCryPlugin;
import me.rcj0003.deathcry.crys.Cry;

public class DeathCryListener implements Listener {
	@EventHandler(priority = EventPriority.MONITOR)
	public void onDeath(EntityDamageByEntityEvent e) {
		if (e.getEntity() instanceof Player) {
			Player player = (Player) e.getEntity();
			
			if (player.getHealth() - e.getFinalDamage() <= 0) {
				Cry cry = DeathCryPlugin.getInstance().getCryManager().getCryForPlayer(player);
				
				if (!cry.isPlaceholder() && player.hasPermission("deathcry.cry"))
					player.getWorld().playSound(player.getLocation(), cry.getSound(), 25, 1);
			}
		}
	}
}