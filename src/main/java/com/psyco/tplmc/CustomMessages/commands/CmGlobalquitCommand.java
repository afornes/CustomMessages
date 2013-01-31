package com.psyco.tplmc.CustomMessages.commands;

import com.psyco.tplmc.CustomMessages.CustomMessages;
import com.psyco.tplmc.CustomMessages.MessageTypes;
import com.psyco.tplmc.CustomMessages.Util;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmGlobalquitCommand extends CommandBase {
    
    private CmGlobalquitCommand(){
        CommandManager.getInstance().registerCommand("globalquit", this);
    }
    
    static {
        new CmGlobalquitCommand();
    }

    @Override
    public void onPlayerCommand(Player player, String label, String[] args) {
        onCommandSenderCommand(player, label, args);
    }

    @Override
    public void onCommandSenderCommand(CommandSender sender, String label, String[] args) {
        if (args.length == 0) {
            if (!CustomMessages.getConfiguration().permsRequired() || sender.hasPermission("CustomMessages.globalquit")) {
                sender.sendMessage(ChatColor.GREEN + "Current global quit message:");
                sender.sendMessage(Util.translateColor(CustomMessages.getConfiguration().getGlobalMessage(MessageTypes.QUIT)));
            } else {
                sender.sendMessage(NO_PERMISSION);
            }
        } else {
            if (sender.hasPermission("CustomMessages.globalquit")) {
                if(args[0].equalsIgnoreCase("enable")){
                    if(CustomMessages.getConfiguration().setGlobalMessageEnabled(MessageTypes.QUIT, true)){
                        sender.sendMessage(ChatColor.GREEN + "Global quit message is now enabled");
                    } else {
                        sender.sendMessage(ChatColor.GREEN + "Global quit message was already enabled");
                    }
                } else if(args[0].equalsIgnoreCase("disable")){
                    if(CustomMessages.getConfiguration().setGlobalMessageEnabled(MessageTypes.QUIT, false)){
                        sender.sendMessage(ChatColor.GREEN + "Global quit message is now disabled");
                    } else {
                        sender.sendMessage(ChatColor.GREEN + "Global quit message was already disabled");
                    }
                } else if (args[0].equalsIgnoreCase("reset")) {
                    CustomMessages.getConfiguration().resetGlobalMessage(MessageTypes.QUIT);
                    sender.sendMessage(ChatColor.GREEN + "Reset the global quit message:");
                    sender.sendMessage(Util.translateColor(CustomMessages.getConfiguration().getGlobalMessage(MessageTypes.QUIT)) + getGlobalDisabledText(MessageTypes.QUIT));
                } else {
                    String messageString = Util.getSpacedString(args, 0, args.length);
                    messageString = CustomMessages.getConfiguration().setGlobalMessage(MessageTypes.QUIT, messageString);
                    sender.sendMessage(ChatColor.GREEN + "Set the global quit message to:");
                    sender.sendMessage(messageString + getGlobalDisabledText(MessageTypes.QUIT));
                }
            } else {
                sender.sendMessage(NO_PERMISSION);
            }
        }
    }
}
