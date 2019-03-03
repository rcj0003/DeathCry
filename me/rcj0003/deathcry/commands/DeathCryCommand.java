package me.rcj0003.deathcry.commands;

import me.rcj0003.deathcry.utils.ezcommands.CommandUser;
import me.rcj0003.deathcry.utils.ezcommands.MessageWrapper;
import me.rcj0003.deathcry.utils.ezcommands.SubCommand;
import me.rcj0003.deathcry.utils.ezcommands.SuperCommand;
import me.rcj0003.deathcry.utils.sgmapi.SimpleGUIManager;

public class DeathCryCommand extends SuperCommand implements SubCommand {
	public DeathCryCommand() {
		super(new MessageWrapper(new String[] { "&8[&6DeathCry&8]&c No arguments have been supplied!" },
				"&8[&6DeathCry&8]&c Sub-command not found!",
				"&8[&6DeathCry&8]&c You do not have permission to use this sub-command!",
				"&8[&6DeathCry&8]&c This command must be executed by a player!",
				"&8[&6DeathCry&8]&c Usage: {usage}"));
		registerSubcommand(this);
	}

	public boolean getRequiresPlayer() {
		return true;
	}

	public int getMinimumArguments() {
		return 0;
	}

	public String getPermission() {
		return "deathcry.cry";
	}

	public boolean hasPermission(CommandUser user) {
		return user.getSender().isOp() || user.hasPermission(getPermission());
	}

	public String getName() {
		return "open";
	}

	public String[] getDescription() {
		return new String[0];
	}

	public String getUsage() {
		return "open";
	}

	public void execute(CommandUser user, String[] arguments) {
		SimpleGUIManager.getInstance().openNewGUI(user.getPlayer(), "deathcry.main");
	}
}