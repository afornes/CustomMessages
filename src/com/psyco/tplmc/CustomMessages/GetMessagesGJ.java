package com.psyco.tplmc.CustomMessages;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class GetMessagesGJ implements CommandExecutor{

	private CustomMessages plugin;

	public GetMessagesGJ(CustomMessages customMessages) {
		this.plugin = customMessages;
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String arg2,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("checkglobaljoin")) {
			String strgb = plugin.config
					.getGlobalMessage("Join");
			s.sendMessage(ChatColor.RED + "Your global join message is: "
					+ strgb);
			return true;
		}
		
		return false;
	}
	

}
