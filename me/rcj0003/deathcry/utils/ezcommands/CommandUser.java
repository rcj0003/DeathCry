package me.rcj0003.deathcry.utils.ezcommands;

import java.util.stream.Stream;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.rcj0003.deathcry.utils.Utilities;

public class CommandUser {
	private CommandSender sender;

	public CommandUser(final CommandSender sender) {
		this.sender = sender;
	}

	public CommandSender getSender() {
		return sender;
	}
	
	public void executeCommand(String command) {
		Bukkit.dispatchCommand(sender, command.replace("{sender}", getName()));
	}
	
	public boolean hasPermission(String permission) {
		return sender.hasPermission(permission);
	}

	public void sendFormattedMessage(final String... message) {
		sender.sendMessage(Stream.of(message).map(e -> getFormattedMessage(e)).toArray(e -> new String[e]));
	}

	public String getFormattedMessage(final String message) {
		return Utilities.StringUtilities.convertColorCodes(message.replace("{sender}", sender.getName()));
	}

	public String getName() {
		return sender.getName();
	}

	public boolean isPlayer() {
		return sender instanceof Player;
	}
	
	public Player getPlayer() {
		return (Player) sender;
	}
}