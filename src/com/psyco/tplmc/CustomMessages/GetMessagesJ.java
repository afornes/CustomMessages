package com.psyco.tplmc.CustomMessages;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetMessagesJ implements CommandExecutor {

	private CustomMessages plugin;

	public GetMessagesJ(CustomMessages customMessages) {
		this.plugin = customMessages;
	}

	@Override
	public boolean onCommand(CommandSender s, Command cmd, String crap,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("checkjoin")) {
			if (args.length == 0) {
				if (s instanceof Player) {
					Player cmdPlayer = (Player) s;
					String strgb = plugin.config
							.getColoredMessage(cmdPlayer, "Join");
					strgb = strgb.replace("/name",
							((Player) s).getDisplayName());

					s.sendMessage(ChatColor.RED + "Your join message is: "
							+ strgb);
					return true;
				}
				s.sendMessage(ChatColor.RED + "Usage is /checkjoin <player>");
				return true;
			}
			if (args.length > 0) {
				if (plugin.isOnline(args[0]) != null) {
					Player p = plugin.getServer().getPlayer(
							plugin.isOnline(args[0]));
					
					String strgb = plugin.config
							.getColoredMessage(p,"Join");
					strgb = strgb.replace("/name", p.getDisplayName());

					s.sendMessage(ChatColor.RED + p.getName()
							+ "'s join message is: " + strgb);
					return true;
				}
			}

		}
		
		
		
		return false;
	}

}
