package com.psyco.tplmc.CustomMessages;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class GetMessagesGQ implements CommandExecutor{

	private CustomMessages plugin;

	public GetMessagesGQ(CustomMessages customMessages) {
		this.plugin = customMessages;
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String arg2,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("checkglobalquit")) {
			String strgb = plugin.config
					.getGlobalMessage("Quit");
			s.sendMessage(ChatColor.RED + "Your global quit message is: "
					+ strgb);
			return true;
		}
		return false;
	}

}
