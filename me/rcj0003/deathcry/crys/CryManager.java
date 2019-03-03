package me.rcj0003.deathcry.crys;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.entity.Player;

public class CryManager {
	private HashMap<UUID, Cry> cache = new HashMap<UUID, Cry>();
	
	public void deregisterPlayer(Player player) {
		deregisterPlayer(player.getUniqueId());
	}
	
	public void deregisterPlayer(UUID id) {
		cache.remove(id);
	}
	
	public void registerPlayer(Player player, Cry cry) {
		registerPlayer(player.getUniqueId(), cry);
	}
	
	public void registerPlayer(UUID id, Cry cry) {
		cache.put(id, cry);
	}

	public Cry getCryForPlayer(Player player) {
		return cache.getOrDefault(player.getUniqueId(), Cry.None);
	}
}