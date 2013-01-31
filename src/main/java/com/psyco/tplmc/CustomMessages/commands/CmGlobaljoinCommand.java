package com.psyco.tplmc.CustomMessages.commands;

import com.psyco.tplmc.CustomMessages.CustomMessages;
import com.psyco.tplmc.CustomMessages.MessageTypes;
import com.psyco.tplmc.CustomMessages.Util;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmGlobaljoinCommand extends CommandBase {

    private CmGlobaljoinCommand() {
        CommandManager.getInstance().registerCommand("globaljoin", this);
    }

    static {
        new CmGlobaljoinCommand();
    }

    @Override
    public void onPlayerCommand(Player player, String label, String[] args) {
        onCommandSenderCommand(player, label, args);
    }

    @Override
    public void onCommandSenderCommand(CommandSender sender, String label, String[] args) {
        if (args.length == 0) {
            if (!CustomMessages.getConfiguration().permsRequired() || sender.hasPermission("CustomMessages.globaljoin")) {
                sender.sendMessage(ChatColor.GREEN + "Current global join message:");
                sender.sendMessage(Util.translateColor(CustomMessages.getConfiguration().getGlobalMessage(MessageTypes.JOIN)));
            } else {
                sender.sendMessage(NO_PERMISSION);
            }
        } else {
            if (sender.hasPermission("CustomMessages.globaljoin")) {
                if(args[0].equalsIgnoreCase("enable")){
                    if(CustomMessages.getConfiguration().setGlobalMessageEnabled(MessageTypes.JOIN, true)){
                        sender.sendMessage(ChatColor.GREEN + "Global join message is now enabled");
                    } else {
                        sender.sendMessage(ChatColor.GREEN + "Global join message was already enabled");
                    }
                } else if(args[0].equalsIgnoreCase("disable")){
                    if(CustomMessages.getConfiguration().setGlobalMessageEnabled(MessageTypes.JOIN, false)){
                        sender.sendMessage(ChatColor.GREEN + "Global join message is now disabled");
                    } else {
                        sender.sendMessage(ChatColor.GREEN + "Global join message was already disabled");
                    }
                } else if (args[0].equalsIgnoreCase("reset")) {
                    CustomMessages.getConfiguration().resetGlobalMessage(MessageTypes.JOIN);
                    sender.sendMessage(ChatColor.GREEN + "Reset the global join message:");
                    sender.sendMessage(Util.translateColor(CustomMessages.getConfiguration().getGlobalMessage(MessageTypes.JOIN)) + getGlobalDisabledText(MessageTypes.JOIN));
                } else {
                    String messageString = Util.getSpacedString(args, 0, args.length);
                    messageString = CustomMessages.getConfiguration().setGlobalMessage(MessageTypes.JOIN, messageString);
                    sender.sendMessage(ChatColor.GREEN + "Set the global join message to:");
                    sender.sendMessage(messageString + getGlobalDisabledText(MessageTypes.JOIN));
                }
            } else {
                sender.sendMessage(NO_PERMISSION);
            }
        }
    }
}
