package com.psyco.tplmc.CustomMessages.commands;

import com.psyco.tplmc.CustomMessages.CustomMessages;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmReloadCommand extends CommandBase {

    private CmReloadCommand() {
        CommandManager.getInstance().registerCommand("reload", this);
    }

    static {
        new CmReloadCommand();
    }

    @Override
    public void onPlayerCommand(Player player, String label, String[] args) {
        if (player.hasPermission("CustomMessages.reload")) {
            if (CustomMessages.getConfiguration().loadConfig())
                player.sendMessage(ChatColor.GREEN + "Configuration reloaded successfully");
            else
                player.sendMessage(ChatColor.RED + "Error reloading configuration. See console");
        } else {
            player.sendMessage(NO_PERMISSION);
        }
    }

    @Override
    public void onCommandSenderCommand(CommandSender sender, String label, String[] args) {
        if (sender.hasPermission("CustomMessages.reload")) {
            if (CustomMessages.getConfiguration().loadConfig())
                sender.sendMessage(ChatColor.GREEN + "Configuration reloaded successfully");
            else
                sender.sendMessage(ChatColor.RED + "Error reloading configuration. See console");
        } else {
            sender.sendMessage(NO_PERMISSION);
        }
    }
}
