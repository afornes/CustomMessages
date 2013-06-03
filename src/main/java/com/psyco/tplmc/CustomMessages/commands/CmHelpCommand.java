package com.psyco.tplmc.CustomMessages.commands;

import com.psyco.tplmc.CustomMessages.CustomMessages;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmHelpCommand extends CommandBase {

    public CmHelpCommand() {
    }

    @Override
    public void onPlayerCommand(Player player, String label, String[] args) {
        onCommandSenderCommand(player, label, args);
    }

    @Override
    public void onCommandSenderCommand(CommandSender sender, String label, String[] args) {
        sender.sendMessage(ChatColor.GREEN + "Custom Messages - v " + CustomMessages.p.getDescription().getVersion() + " by psycowithespn");
        if (sender.hasPermission("CustomMessages.join") && sender instanceof Player) {
            sender.sendMessage(ChatColor.GREEN + "/" + label + " join [message|reset|enable|disable]" + ChatColor.GRAY + " Views or modifies your join message");
        } else if (!CustomMessages.getConfiguration().permsRequired() && sender instanceof Player){
            sender.sendMessage(ChatColor.GREEN + "/" + label + " join" + ChatColor.GRAY + " Views your join message");
        }
        if (sender.hasPermission("CustomMessages.join.other")) {
            sender.sendMessage(ChatColor.GREEN + "/" + label + " join <player> [message|reset|enable|disable]" + ChatColor.GRAY + " Views or modifies another player's join message");
        }
        if (sender.hasPermission("CustomMessages.join.group")) {
            sender.sendMessage(ChatColor.GREEN + "/" + label + " join <group> [message|reset|enable|disable]" + ChatColor.GRAY + " Views or modifies a group's join message");
        }
        if (sender.hasPermission("CustomMessages.quit") && sender instanceof Player) {
            sender.sendMessage(ChatColor.GREEN + "/" + label + " quit [message|reset|enable|disable]" + ChatColor.GRAY + " Views or modifies your quit message");
        } else if (!CustomMessages.getConfiguration().permsRequired() && sender instanceof Player){
            sender.sendMessage(ChatColor.GREEN + "/" + label + " quit" + ChatColor.GRAY + " Views your quit message");
        }
        if (sender.hasPermission("CustomMessages.quit.other")) {
            sender.sendMessage(ChatColor.GREEN + "/" + label + " quit <player> [message|reset|enable|disable]" + ChatColor.GRAY + " Views or modifies another player's quit message");
        }
        if (sender.hasPermission("CustomMessages.quit.group")) {
            sender.sendMessage(ChatColor.GREEN + "/" + label + " quit <group> [message|reset|enable|disable]" + ChatColor.GRAY + " Views or modifies a group's quit message");
        }
        if (sender.hasPermission("CustomMessages.kick")) {
            sender.sendMessage(ChatColor.GREEN + "/" + label + " kick [message|reset|enable|disable]" + ChatColor.GRAY + " Views or modifies the global kick message");
        }
        if (sender.hasPermission("CustomMessages.globaljoin")) {
            sender.sendMessage(ChatColor.GREEN + "/" + label + " globaljoin [message|reset|enable|disable]" + ChatColor.GRAY + " Views or modifies the server's global join message");
        } else if (!CustomMessages.getConfiguration().permsRequired()){
            sender.sendMessage(ChatColor.GREEN + "/" + label + " globaljoin" + ChatColor.GRAY + " Views the server's join message");
        }
        if (sender.hasPermission("CustomMessages.globalquit")) {
            sender.sendMessage(ChatColor.GREEN + "/" + label + " globalquit [message|reset|enable|disable]" + ChatColor.GRAY + " Views or modifies the server's global quit message");
        } else if (!CustomMessages.getConfiguration().permsRequired()){
            sender.sendMessage(ChatColor.GREEN + "/" + label + " globalquit" + ChatColor.GRAY + " Views the server's quit message");
        }
        if (sender.hasPermission("CustomMessages.firstjoin")) {
            sender.sendMessage(ChatColor.GREEN + "/" + label + " firstjoin [message|reset|enable|disable]" + ChatColor.GRAY + " Views or modifies the server's first join message");
        } else if (!CustomMessages.getConfiguration().permsRequired()){
            sender.sendMessage(ChatColor.GREEN + "/" + label + " firstjoin" + ChatColor.GRAY + " Views the server's first join message");
        }
        if (sender.hasPermission("CustomMessages.reload")) {
            sender.sendMessage(ChatColor.GREEN + "/" + label + " reload" + ChatColor.GRAY + " Reloads config from file");
        }
        sender.sendMessage(ChatColor.GREEN + "/" + label + " colors" + ChatColor.GRAY + " Shows all of the color codes");
        sender.sendMessage(ChatColor.GREEN + "/" + label + " variables" + ChatColor.GRAY + " Shows all of the message variables");
    }
}
