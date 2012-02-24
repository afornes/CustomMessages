package com.psyco.tplmc.CustomMessages;

import com.nijiko.permissions.PermissionHandler;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class CustomMessages extends JavaPlugin {

    //TODO: Add SuperPerms support, Use new configuration, Log stuff

	public com.psyco.tplmc.CustomMessages.configuration.Configuration config;
	public PluginManager pm;
	public pListener playerL;
	public static PermissionHandler perms;
	public String playerOption;
	public Player cmdPlayer;
	public String colors = "&00&11&22&33&44&55&66&77&88&99&aa&bb&cc&dd&ee&ff";
	public String playerIsOnline;
	public Logger log;
	public String name;
	public String version;
	public String checklocation;
	public String downloadlocation;
	public String logprefix;
	public GetMessagesJ gmj;
	public GetMessagesGJ gmgj;
	public GetMessagesGQ gmgq;
	public GetMessagesQ gmq;

	@Override
	public void onDisable() {
	}

	@Override
	public void onEnable() {
		playerL = new pListener(this);
		gmj = new GetMessagesJ(this);
		gmgj = new GetMessagesGJ(this);
		gmgq = new GetMessagesGQ(this);
		gmq = new GetMessagesQ(this);
		config = new com.psyco.tplmc.CustomMessages.configuration.Configuration(
				this);

		config.loadConfig();
		setupPermissions();

		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(playerL, this);
		getCommand("checkjoin").setExecutor(gmj);
		getCommand("checkquit").setExecutor(gmq);
		getCommand("checkglobaljoin").setExecutor(gmgj);
		getCommand("checkglobalquit").setExecutor(gmgq);

	}

	private void setupPermissions() {
		if (perms != null) {
			return;
		}

		Plugin permissionsPlugin = this.getServer().getPluginManager()
				.getPlugin("Permissions");
	}

	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (!(args.length == 0)) {
			String str = "";
			for (int i = 0; i < args.length; i++) {
				str += args[i] + " ";
			}
		}

		if (command.getName().equalsIgnoreCase("customquit")) {
			playerOption = " ";
			if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED
						+ "Only Players can use this command.");
				return true;
			}
			cmdPlayer = (Player) sender;
			if (hasPerms(cmdPlayer, "CustomMessages.quit") || cmdPlayer.isOp()) {

				if (args.length == 0) {
					sender.sendMessage(ChatColor.RED
							+ "Usage is /customquit <message>");
					return true;
				}
				for (String s : args) {
					playerOption = playerOption + " " + s;
				}
				playerOption = playerOption.trim();
				playerOption = config.setColoredMessage(cmdPlayer,
						playerOption, "Quit");

				cmdPlayer.sendMessage("Your new quit message is "
						+ playerOption + ChatColor.WHITE + ".");

				return true;
			}
			cmdPlayer.sendMessage("You dont have the permissions to do this.");
			return true;
		}
		if (command.getName().equalsIgnoreCase("customjoin")) {
			playerOption = " ";
			if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED
						+ "Only Players can use this command.");
				return true;
			}
			cmdPlayer = (Player) sender;

			if (hasPerms(cmdPlayer, "CustomMessages.join") || cmdPlayer.isOp()) {
				if (args.length == 0) {
					sender.sendMessage(ChatColor.RED
							+ "Usage is /customjoin <message>");
					return true;
				}
				for (String s : args) {
					playerOption = playerOption + " " + s;
				}
				playerOption = playerOption.trim();
				playerOption = config.setColoredMessage(cmdPlayer,
						playerOption, "Join");
				cmdPlayer.sendMessage("Your new join message is "
						+ playerOption + ChatColor.WHITE + ".");
				return true;
			}
			cmdPlayer.sendMessage("You dont have the permissions to do this.");
			return true;
		}
		if (command.getName().equalsIgnoreCase("colors")) {
			colors = colors.replaceAll("(&([a-f0-9]))", "\u00A7$2");
			sender.sendMessage(colors);
			return true;
		}
		if (command.getName().equalsIgnoreCase("resetquit")) {
			playerOption = " ";
			if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED
						+ "Only Players can use this command.");
				return true;
			}
			cmdPlayer = (Player) sender;

			if (hasPerms(cmdPlayer, "CustomMessages.quit") || cmdPlayer.isOp()) {
				config.resetColoredMessage(cmdPlayer, "Quit");
				cmdPlayer
						.sendMessage(ChatColor.RED
								+ "Your Quit message has been reset to the global message.");
				return true;

			}
		}
		if (command.getName().equalsIgnoreCase("resetjoin")) {
			playerOption = " ";
			if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED
						+ "Only Players can use this command.");
				return true;
			}
			cmdPlayer = (Player) sender;

			if (hasPerms(cmdPlayer, "CustomMessages.join") || cmdPlayer.isOp()) {
				config.resetColoredMessage(cmdPlayer, "Join");
				cmdPlayer
						.sendMessage(ChatColor.RED
								+ "Your Join message has been reset to the global message.");
				return true;

			}
			return true;
		}
		if (command.getName().equalsIgnoreCase("resetglobaljoin")) {
			playerOption = " ";
			if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED
						+ "Only Players can use this command.");
				return true;
			}
			cmdPlayer = (Player) sender;

			if (hasPerms(cmdPlayer, "CustomMessages.Globaljoin")
					|| cmdPlayer.isOp()) {
				config.resetGlobalMessage("Join");
				cmdPlayer
						.sendMessage(ChatColor.RED
								+ "Your Global Join message has been reset to the default message.");
				return true;

			}
			return true;
		}
		if (command.getName().equalsIgnoreCase("resetglobalquit")) {
			playerOption = " ";
			if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED
						+ "Only Players can use this command.");
				return true;
			}
			cmdPlayer = (Player) sender;

			if (hasPerms(cmdPlayer, "CustomMessages.Globalquit")
					|| cmdPlayer.isOp()) {
				config.resetGlobalMessage("Quit");
				cmdPlayer
						.sendMessage(ChatColor.RED
								+ "Your Global Join message has been reset to the default message.");
				return true;

			}
			return true;
		}
		if (command.getName().equalsIgnoreCase("customglobaljoin")) {
			playerOption = " ";
			if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED
						+ "Only Players can use this command.");
				return true;
			}
			cmdPlayer = (Player) sender;

			if (hasPerms(cmdPlayer, "CustomMessages.Globaljoin")
					|| cmdPlayer.isOp()) {
				if (args.length == 0) {
					sender.sendMessage(ChatColor.RED
							+ "Usage is /customglobaljoin <message>");
					return true;
				}
				for (String s : args) {
					playerOption = playerOption + " " + s;
				}
				playerOption = playerOption.trim();
				playerOption = config.setGlobalMessage(playerOption, "Join");
				cmdPlayer.sendMessage("Your new global join message is "
						+ playerOption + ChatColor.WHITE + ".");
				return true;
			}
			cmdPlayer.sendMessage("You dont have the permissions to do this.");
			return true;
		}
		if (command.getName().equalsIgnoreCase("customglobalquit")) {
			playerOption = " ";
			if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.RED
						+ "Only Players can use this command.");
				return true;
			}
			cmdPlayer = (Player) sender;

			if (hasPerms(cmdPlayer, "CustomMessages.Globalquit")
					|| cmdPlayer.isOp()) {
				if (args.length == 0) {
					sender.sendMessage(ChatColor.RED
							+ "Usage is /customglobalquit <message>");
					return true;
				}
				for (String s : args) {
					playerOption = playerOption + " " + s;
				}
				playerOption = playerOption.trim();
				playerOption = config.setGlobalMessage(playerOption, "Quit");
				cmdPlayer.sendMessage("Your new global quit message is "
						+ playerOption + ChatColor.WHITE + ".");
				return true;
			}
			cmdPlayer.sendMessage("You dont have the permissions to do this.");
			return true;
		}
		if (command.getName().equalsIgnoreCase("customjoino")) {
			playerOption = " ";
			cmdPlayer = null;
			if (args.length < 2) {
				return false;
			}
			if ((sender instanceof Player)) {
				cmdPlayer = (Player) sender;
			}
			if (isOnline(args[0]) != null) {
				playerIsOnline = isOnline(args[0]);

				if (hasPerms(cmdPlayer, "CustomMessages.joino")
						|| cmdPlayer.isOp()) {
					for (int i = 1; i < args.length; i++) {
						String s = args[i];
						playerOption = playerOption + " " + s;
					}
					playerOption = playerOption.trim();
					playerOption = config.setColoredMessage(getServer()
							.getPlayer(playerIsOnline), playerOption, "Join");
					cmdPlayer.sendMessage("You set " + playerIsOnline
							+ "'s join message to " + playerOption
							+ ChatColor.WHITE + ".");
					getServer().getPlayer(playerIsOnline).sendMessage(
							"Your new join message is " + playerOption
									+ ChatColor.WHITE + ", set by "
									+ getName(cmdPlayer));
					return true;
				}
				cmdPlayer
						.sendMessage("You dont have the permissions to do this.");
				return true;
			}
			sender.sendMessage(ChatColor.RED + "Could not find player online.");
			return true;

		}
		if (command.getName().equalsIgnoreCase("customquito")) {
			playerOption = " ";
			cmdPlayer = null;
			if (args.length < 2) {
				return false;
			}
			if ((sender instanceof Player)) {
				cmdPlayer = (Player) sender;
			}
			if (isOnline(args[0]) != null) {
				playerIsOnline = isOnline(args[0]);

				if (hasPerms(cmdPlayer, "CustomMessages.quito")
						|| cmdPlayer.isOp()) {
					for (int i = 1; i < args.length; i++) {
						String s = args[i];
						playerOption = playerOption + " " + s;
					}
					playerOption = playerOption.trim();
					playerOption = config.setColoredMessage(getServer()
							.getPlayer(playerIsOnline), playerOption, "Quit");
					cmdPlayer.sendMessage("You set " + playerIsOnline
							+ "'s quit message to " + playerOption
							+ ChatColor.WHITE + ".");
					getServer().getPlayer(playerIsOnline).sendMessage(
							"Your new quit message is " + playerOption
									+ ChatColor.WHITE + ", set by "
									+ getName(cmdPlayer));
					return true;
				}
				cmdPlayer
						.sendMessage("You dont have the permissions to do this.");
				return true;
			}
			sender.sendMessage(ChatColor.RED + "Could not find player online.");
			return true;

		}
		if (command.getName().equalsIgnoreCase("resetquito")) {
			cmdPlayer = null;
			if (args.length < 1) {
				return false;
			}
			if ((sender instanceof Player)) {
				cmdPlayer = (Player) sender;
			}
			if (isOnline(args[0]) != null) {
				playerIsOnline = isOnline(args[0]);

				if (hasPerms(cmdPlayer, "CustomMessages.quito")
						|| cmdPlayer.isOp()) {
					config.resetColoredMessage(
							getServer().getPlayer(playerIsOnline), "Quit");
					cmdPlayer.sendMessage("You set " + playerIsOnline
							+ "'s quit message to the global default.");
					getServer().getPlayer(playerIsOnline).sendMessage(
							"Your new quit message is now the global default, set by "
									+ getName(cmdPlayer));
					return true;
				}
				cmdPlayer
						.sendMessage("You dont have the permissions to do this.");
				return true;
			}
			sender.sendMessage(ChatColor.RED + "Could not find player online.");
			return true;

		}
		if (command.getName().equalsIgnoreCase("resetjoino")) {
			cmdPlayer = null;
			if (args.length < 1) {
				return false;
			}
			if ((sender instanceof Player)) {
				cmdPlayer = (Player) sender;
			}
			if (isOnline(args[0]) != null) {
				playerIsOnline = isOnline(args[0]);

				if (hasPerms(cmdPlayer, "CustomMessages.joino")
						|| cmdPlayer.isOp()) {
					config.resetColoredMessage(
							getServer().getPlayer(playerIsOnline), "Join");
					cmdPlayer.sendMessage("You set " + playerIsOnline
							+ "'s join message to the global default.");
					getServer().getPlayer(playerIsOnline).sendMessage(
							"Your new join message is now the global default, set by "
									+ getName(cmdPlayer));
					return true;
				}
				cmdPlayer
						.sendMessage("You dont have the permissions to do this.");
				return true;
			}
			sender.sendMessage(ChatColor.RED + "Could not find player online.");
			return true;

		}

		return false;

	}

	private String getName(Player p) {
		if (p != null) {
			return p.getName();
		}
		return "Console";
	}

	private boolean hasPerms(CommandSender sender, String node) {
		if (perms != null) {
			if (sender == null) {
				return true;
			}
			return perms.has((Player) sender, node);

		} else {
			return sender.hasPermission(node);
		}
	}

	public String isOnline(String b) {
		for (Player pl : getServer().getOnlinePlayers()) {
			String player = pl.getName().toLowerCase();
			String test = b.toLowerCase();
			if (player.contains(test)) {
				return pl.getName();
			}
		}
		return null;

	}

}