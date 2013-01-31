package com.psyco.tplmc.CustomMessages.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmVariablesCommand extends CommandBase {

    private CmVariablesCommand(){
        CommandManager.getInstance().registerCommand("variables", this);
    }

    static {
        new CmVariablesCommand();
    }
    @Override
    public void onPlayerCommand(Player player, String label, String[] args) {
        onCommandSenderCommand(player, label, args);
    }

    @Override
    public void onCommandSenderCommand(CommandSender sender, String label, String[] args) {
        sender.sendMessage(ChatColor.GREEN + "/name" + ChatColor.GRAY + " The real name of the player");
        sender.sendMessage(ChatColor.GREEN + "/nname" + ChatColor.GRAY + " The nickname of the player");
        sender.sendMessage(ChatColor.GREEN + "/count" + ChatColor.GRAY + " The number of players that have been on the server");
        sender.sendMessage(ChatColor.GREEN + "/online" + ChatColor.GRAY + " The number of players currently online");
        sender.sendMessage(ChatColor.GREEN + "/maxonline" + ChatColor.GRAY + " The max number of players that can be online");
        sender.sendMessage(ChatColor.GREEN + "/prefix" + ChatColor.GRAY + " The prefix of the player");
        sender.sendMessage(ChatColor.GREEN + "/suffix" + ChatColor.GRAY + " The suffix of the player");
        sender.sendMessage(ChatColor.GREEN + "/group" + ChatColor.GRAY + " The group of the player");
        sender.sendMessage(ChatColor.GREEN + "/world" + ChatColor.GRAY + " The world the player is in");
    }
}
