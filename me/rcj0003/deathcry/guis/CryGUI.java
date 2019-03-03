package me.rcj0003.deathcry.guis;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import me.rcj0003.deathcry.DeathCryPlugin;
import me.rcj0003.deathcry.crys.Cry;
import me.rcj0003.deathcry.utils.ItemBuilder;
import me.rcj0003.deathcry.utils.Utilities;
import me.rcj0003.deathcry.utils.Utilities.StringUtilities;
import me.rcj0003.deathcry.utils.sgmapi.SimpleGUI;

public class CryGUI extends SimpleGUI {
	public String getId() {
		return "deathcry.main";
	}

	public Plugin getPlugin() {
		return DeathCryPlugin.getInstance();
	}

	public Inventory getInventory(Player player, Object arguments) {
		Inventory inventory = Bukkit.createInventory(null, 27, StringUtilities.convertColorCodes("&c&lDeathCry"));
		int offset = 0;

		for (Cry cry : Cry.values())
			if (player.hasPermission(cry.getPermission()))
				inventory.setItem(offset++, cry.getItemStack());

		if (offset == 0)
			inventory.setItem(13,
					new ItemBuilder(Material.PAPER, 1).setDisplayName("&6Uh-oh! You do not have any sounds.")
							.setLore("&cYou can get access to these sounds at &bhttps://buy.guildcraft.org&c!")
							.createItem());

		inventory.setItem(26, new ItemBuilder(Material.BARRIER, 1).setDisplayName("&2Exit")
				.setLore("&5Click here to exit this menu.").createItem());

		return inventory;
	}

	public BukkitRunnable getAction(int slot, Player player, Object argument) {
		switch (slot) {
			default:
				return new BukkitRunnable() {
					public void run() {
						ItemStack stack = player.getOpenInventory().getTopInventory().getItem(slot);

						for (Cry cry : Cry.values())
							if (cry.getItemStack().getItemMeta().getDisplayName()
									.equals(stack.getItemMeta().getDisplayName())) {
								DeathCryPlugin.getInstance().getCryManager().registerPlayer(player, cry);
								player.closeInventory();
								player.sendMessage(Utilities.StringUtilities
										.convertColorCodes("&8[&6DeathCry&8]&a You have equipped the &6" + cry.getName()
												+ "&a death sound!"));
							}
					}
				};
			case 26:
				player.closeInventory();
		}

		return null;
	}
}