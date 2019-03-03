package me.rcj0003.deathcry.utils.sgmapi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.Inventory;

public class SimpleGUIManager implements Listener {
	private static SimpleGUIManager instance = new SimpleGUIManager();

	private HashMap<String, ISimpleGUI> guis = new HashMap<String, ISimpleGUI>();

	public static SimpleGUIManager getInstance() {
		return instance;
	}

	public void registerGUI(ISimpleGUI... gui) {
		for (ISimpleGUI currentGui : gui)
			guis.put(currentGui.getId(), currentGui);
	}

	public ISimpleGUI getGUIByID(String ID) {
		return guis.containsKey(ID) ? guis.get(ID) : null;
	}

	public List<ISimpleGUI> searchGUIByID(String search) {
		ArrayList<ISimpleGUI> results = new ArrayList<ISimpleGUI>();
		for (ISimpleGUI gui : guis.values())
			if (gui.getId().contains(search))
				results.add(gui);
		return results;
	}

	private List<ISimpleGUI> getGUIs() {
		return new ArrayList<ISimpleGUI>(guis.values());
	}

	public void openNewGUI(Player player, String id) {
		openNewGUI(player, id, null);
	}

	public void openNewGUI(Player player, String id, Object argument) {
		if (player.getOpenInventory().getTopInventory() != null)
			closeInventory(player.getOpenInventory().getTopInventory());
		openGUI(player, id, argument);
	}

	private void openGUI(Player p, String id, Object argument) {
		if (guis.containsKey(id))
			guis.get(id).openInventory(p, argument);
	}

	public boolean closeInventory(Inventory inventory) {
		if (inventory == null || guis.size() == 0)
			return false;

		try {
			ISimpleGUI[] GUIs = getGUIs().stream().filter(e -> e.isOwner(inventory)).toArray(s -> new ISimpleGUI[s]);

			if (GUIs.length == 0)
				return false;

			Stream.of(GUIs).forEach(e -> e.deregisterInventory(inventory));
			inventory.getViewers().forEach(e -> e.closeInventory());
		} catch (Exception e) {
			// Possible ConcurrentSomethingException expected here, especially if other plugins are using this API. This error is harmless in this case, I have tested it pretty extensively and have yet to see any issues. ~short1der
		}

		return true;
	}

	@EventHandler
	private void onInventoryClick(InventoryClickEvent event) {
		if (guis.size() == 0 || event.getSlotType() == SlotType.OUTSIDE
				|| event.getClickedInventory().getHolder() != null)
			return;

		ISimpleGUI[] GUIs = getGUIs().stream().filter(e -> e.isOwner(event.getInventory()))
				.toArray(s -> new ISimpleGUI[s]);

		if (GUIs.length == 0)
			return;

		event.setCancelled(Stream.of(GUIs)
				.filter(e -> e.runAction(event.getSlot(), (Player) event.getWhoClicked(), event.getInventory()))
				.count() > 0);
	}

	@EventHandler
	private void onInventoryClose(InventoryCloseEvent event) {
		closeInventory(event.getInventory());
	}
}