package com.psyco.tplmc.CustomMessages;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetMessagesQ implements CommandExecutor {

	private CustomMessages plugin;

	public GetMessagesQ(CustomMessages customMessages) {
		this.plugin = customMessages;
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String arg2,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("checkquit")) {
			if (args.length == 0) {
				if (s instanceof Player) {
					String strgb = plugin.config.getColoredMessage((Player) s,
							"Quit");
					strgb = strgb.replace("/name",
							((Player) s).getDisplayName());

					s.sendMessage(ChatColor.RED + "Your quit message is: "
							+ strgb);
					return true;
				}
				s.sendMessage(ChatColor.RED + "Usage is /checkquit <player>");
				return true;
			}
			if (args.length > 0) {
				if (plugin.isOnline(args[0]) != null) {
					Player p = plugin.getServer().getPlayer(
							plugin.isOnline(args[0]));
					String strgb = plugin.config.getColoredMessage(p, "Quit");
					strgb = strgb.replace("/name", p.getDisplayName());

					s.sendMessage(ChatColor.RED + p.getName()
							+ "'s quit message is: " + strgb);
					return true;
				}
			}

		}

		return false;
	}

}
