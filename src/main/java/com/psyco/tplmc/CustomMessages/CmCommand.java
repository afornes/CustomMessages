package com.psyco.tplmc.CustomMessages;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created with IntelliJ IDEA.
 * User: Patrick
 * Date: 4/11/12
 * Time: 10:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class CmCommand implements CommandExecutor {

    private final String NO_PERMISSION = ChatColor.RED + "You don't have permission for this command";

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        boolean fromPlayer = false;
        Player player = null;
        if (sender instanceof Player) {
            player = (Player) sender;
            fromPlayer = true;
        }
        if (args.length == 0) {
            showAllHelp(sender);
            return true;
        }
        if (args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission("CustomMessages.reload")) {
                if (CustomMessages.p.config.loadConfig())
                    sender.sendMessage(ChatColor.GREEN + "Configuration reloaded successfully");
                else
                    sender.sendMessage(ChatColor.RED + "Error reloading configuration. See console");
            } else {
                sender.sendMessage(NO_PERMISSION);
            }
            return true;
        }
        if (args[0].equalsIgnoreCase("join")) {
            if (args.length == 1) {
                if (fromPlayer) {
                    if (!CustomMessages.p.config.permsRequired() || sender.hasPermission("CustomMessages.join")) {
                        sender.sendMessage(ChatColor.GREEN + "Current join message:");
                        sender.sendMessage(CustomMessages.p.config.getColoredMessage(player, MessageTypes.JOIN));
                    } else {
                        sender.sendMessage(NO_PERMISSION);
                    }
                } else {
                    sender.sendMessage(ChatColor.GREEN + "/cm join <player> [message]");
                }
            } else if (args.length == 2) {
                Player target = Bukkit.getPlayer(args[1]);
                if (target != null) {
                    if (target.getName().equals(sender.getName())) {
                        if (!CustomMessages.p.config.permsRequired() || sender.hasPermission("CustomMessages.join")) {
                            sender.sendMessage(ChatColor.GREEN + "Current join message:");
                            sender.sendMessage(CustomMessages.p.config.getColoredMessage(player, MessageTypes.JOIN));
                        } else {
                            sender.sendMessage(NO_PERMISSION);
                        }
                    } else if (sender.hasPermission("CustomMessages.join.other")) {
                        sender.sendMessage(ChatColor.GREEN + target.getName() + "'s current join message:");
                        sender.sendMessage(CustomMessages.p.config.getColoredMessage(target, MessageTypes.JOIN));
                    } else {
                        sender.sendMessage(NO_PERMISSION);
                    }
                } else {
                    if (fromPlayer) {
                        if (sender.hasPermission("CustomMessages.join")) {
                            CustomMessages.p.config.setColoredMessage(player, args[1], MessageTypes.JOIN);
                            sender.sendMessage(ChatColor.GREEN + "Set your join message to:");
                            sender.sendMessage(CustomMessages.p.config.getColoredMessage(player, MessageTypes.JOIN));
                        } else {
                            sender.sendMessage(NO_PERMISSION);
                        }
                    } else {
                        sender.sendMessage(ChatColor.RED + "Could not find player \"" + args[1] + "\" online");
                    }
                }
            } else {
                Player target = Bukkit.getPlayer(args[1]);
                if (target != null) {
                    if (target.getName().equals(sender.getName())) {
                        if (sender.hasPermission("CustomMessages.join")) {
                            String messageString = Util.getSpacedString(args, 1, args.length);
                            messageString = CustomMessages.p.config.setColoredMessage(player, messageString, MessageTypes.JOIN);
                            sender.sendMessage(ChatColor.GREEN + "Set your join message to:");
                            sender.sendMessage(messageString);
                        } else {
                            sender.sendMessage(NO_PERMISSION);
                        }
                    } else {
                        if (sender.hasPermission("CustomMessages.join.other")) {
                            String messageString = Util.getSpacedString(args, 2, args.length);
                            messageString = CustomMessages.p.config.setColoredMessage(target, messageString, MessageTypes.JOIN);
                            sender.sendMessage(ChatColor.GREEN + "Set " + target.getName() + "'s join message to:");
                            sender.sendMessage(messageString);
                            target.sendMessage(ChatColor.GREEN + sender.getName() + " set your join message to:");
                            target.sendMessage(messageString);
                        } else {
                            sender.sendMessage(NO_PERMISSION);
                        }
                    }
                } else {
                    if(fromPlayer){
                        if(sender.hasPermission("CustomMessages.join")){
                            String messageString = Util.getSpacedString(args, 1, args.length );
                            messageString = CustomMessages.p.config.setColoredMessage(player, messageString, MessageTypes.JOIN);
                            sender.sendMessage(ChatColor.GREEN + "Set your join message to:");
                            sender.sendMessage(messageString);
                        } else {
                            sender.sendMessage(NO_PERMISSION);
                        }
                    } else {
                        sender.sendMessage(ChatColor.RED + "Could not find player \"" + args[1] + "\" online");
                    }
                }
            }
            return true;
        }
        if (args[0].equalsIgnoreCase("quit")) {
            if (args.length == 1) {
                if (fromPlayer) {
                    if (!CustomMessages.p.config.permsRequired() || sender.hasPermission("CustomMessages.quit")) {
                        sender.sendMessage(ChatColor.GREEN + "Current quit message:");
                        sender.sendMessage(CustomMessages.p.config.getColoredMessage(player, MessageTypes.QUIT));
                    } else {
                        sender.sendMessage(NO_PERMISSION);
                    }
                } else {
                    sender.sendMessage(ChatColor.GREEN + "/cm quit <player> [message]");
                }
            } else if (args.length == 2) {
                Player target = Bukkit.getPlayer(args[1]);
                if (target != null) {
                    if (target.getName().equals(sender.getName())) {
                        if (!CustomMessages.p.config.permsRequired() || sender.hasPermission("CustomMessages.quit")) {
                            sender.sendMessage(ChatColor.GREEN + "Current quit message:");
                            sender.sendMessage(CustomMessages.p.config.getColoredMessage(player, MessageTypes.QUIT));
                        } else {
                            sender.sendMessage(NO_PERMISSION);
                        }
                    } else if (sender.hasPermission("CustomMessages.quit.other")) {
                        sender.sendMessage(ChatColor.GREEN + target.getName() + "'s current quit message:");
                        sender.sendMessage(CustomMessages.p.config.getColoredMessage(target, MessageTypes.QUIT));
                    } else {
                        sender.sendMessage(NO_PERMISSION);
                    }
                } else {
                    if (fromPlayer) {
                        if (sender.hasPermission("CustomMessages.quit")) {
                            CustomMessages.p.config.setColoredMessage(player, args[1], MessageTypes.QUIT);
                            sender.sendMessage(ChatColor.GREEN + "Set your quit message to:");
                            sender.sendMessage(CustomMessages.p.config.getColoredMessage(player, MessageTypes.QUIT));
                        } else {
                            sender.sendMessage(NO_PERMISSION);
                        }
                    } else {
                        sender.sendMessage(ChatColor.RED + "Could not find player \"" + args[1] + "\" online");
                    }
                }
            } else {
                Player target = Bukkit.getPlayer(args[1]);
                if (target != null) {
                    if (target.getName().equals(sender.getName())) {
                        if (sender.hasPermission("CustomMessages.quit")) {
                            String messageString = Util.getSpacedString(args, 1, args.length);
                            messageString = CustomMessages.p.config.setColoredMessage(player, messageString, MessageTypes.QUIT);
                            sender.sendMessage(ChatColor.GREEN + "Set your quit message to:");
                            sender.sendMessage(messageString);
                        } else {
                            sender.sendMessage(NO_PERMISSION);
                        }
                    } else {
                        if (sender.hasPermission("CustomMessages.quit.other")) {
                            String messageString = Util.getSpacedString(args, 2, args.length);
                            messageString = CustomMessages.p.config.setColoredMessage(target, messageString, MessageTypes.QUIT);
                            sender.sendMessage(ChatColor.GREEN + "Set " + target.getName() + "'s quit message to:");
                            sender.sendMessage(messageString);
                            target.sendMessage(ChatColor.GREEN + sender.getName() + " set your quit message to:");
                            target.sendMessage(messageString);
                        } else {
                            sender.sendMessage(NO_PERMISSION);
                        }
                    }
                } else {
                    if(fromPlayer){
                        if(sender.hasPermission("CustomMessages.quit")){
                            String messageString = Util.getSpacedString(args, 1, args.length);
                            messageString = CustomMessages.p.config.setColoredMessage(player, messageString, MessageTypes.QUIT);
                            sender.sendMessage(ChatColor.GREEN + "Set your quit message to:");
                            sender.sendMessage(messageString);
                        } else {
                            sender.sendMessage(NO_PERMISSION);
                        }
                    } else {
                        sender.sendMessage(ChatColor.RED + "Could not find player \"" + args[1] + "\" online");
                    }
                }
            }
            return true;
        }
        if(args[0].equalsIgnoreCase("resetjoin")){
            if(args.length == 1){
                if(fromPlayer){
                    if(sender.hasPermission("CustomMessages.join")){
                        CustomMessages.p.config.resetColoredMessage(player, MessageTypes.JOIN);
                        sender.sendMessage(ChatColor.GREEN + "Reset your join message to the global default:");
                        sender.sendMessage(CustomMessages.p.config.getColoredMessage(player, MessageTypes.JOIN));
                    } else {
                        sender.sendMessage(NO_PERMISSION);
                    }
                } else {
                    sender.sendMessage(ChatColor.GREEN + "/cm resetjoin [player]");
                }
            } else if(args.length >= 2){
                Player target = Bukkit.getPlayer(args[1]);
                if(target != null){
                    if(sender.hasPermission("CustomMessages.join.other")){
                        CustomMessages.p.config.resetColoredMessage(target, MessageTypes.JOIN);
                        sender.sendMessage(ChatColor.GREEN + "Reset " + target.getName() + "'s join message to the global default:");
                        sender.sendMessage(CustomMessages.p.config.getColoredMessage(target, MessageTypes.JOIN));
                        target.sendMessage(ChatColor.GREEN + sender.getName() + " reset your join message to the global default:");
                        target.sendMessage(CustomMessages.p.config.getColoredMessage(target, MessageTypes.JOIN));
                    } else {
                        sender.sendMessage(NO_PERMISSION);
                    }
                } else {
                    if(sender.hasPermission("CustomMessages.join.other")){
                        sender.sendMessage(ChatColor.RED + "Could not find player \"" + args[1] + "\" online");
                    } else {
                        sender.sendMessage(NO_PERMISSION);
                    }
                }
            }
            return true;
        }
        if(args[0].equalsIgnoreCase("resetquit")){
            if(args.length == 1){
                if(fromPlayer){
                    if(sender.hasPermission("CustomMessages.quit")){
                        CustomMessages.p.config.resetColoredMessage(player, MessageTypes.QUIT);
                        sender.sendMessage(ChatColor.GREEN + "Reset your quit message to the global default:");
                        sender.sendMessage(CustomMessages.p.config.getColoredMessage(player, MessageTypes.QUIT));
                    } else {
                        sender.sendMessage(NO_PERMISSION);
                    }
                } else {
                    sender.sendMessage(ChatColor.GREEN + "/cm resetquit [player]");
                }
            } else if(args.length >= 2){
                Player target = Bukkit.getPlayer(args[1]);
                if(target != null){
                    if(sender.hasPermission("CustomMessages.quit.other")){
                        CustomMessages.p.config.resetColoredMessage(target, MessageTypes.QUIT);
                        sender.sendMessage(ChatColor.GREEN + "Reset " + target.getName() + "'s quit message to the global default:");
                        sender.sendMessage(CustomMessages.p.config.getColoredMessage(target, MessageTypes.QUIT));
                        target.sendMessage(ChatColor.GREEN + sender.getName() + " reset your quit message to the global default:");
                        target.sendMessage(CustomMessages.p.config.getColoredMessage(target, MessageTypes.QUIT));
                    } else {
                        sender.sendMessage(NO_PERMISSION);
                    }
                } else {
                    if(sender.hasPermission("CustomMessages.quit.other")){
                        sender.sendMessage(ChatColor.RED + "Could not find player \"" + args[1] + "\" online");
                    } else {
                        sender.sendMessage(NO_PERMISSION);
                    }
                }
            }
            return true;
        }
        if(args[0].equalsIgnoreCase("globaljoin")){
            if(args.length == 1){
                if(!CustomMessages.p.config.permsRequired() || sender.hasPermission("CustomMessages.globaljoin")){
                    sender.sendMessage(ChatColor.GREEN + "Current global join message:");
                    sender.sendMessage(CustomMessages.p.config.getGlobalMessage(MessageTypes.JOIN));
                } else {
                    sender.sendMessage(NO_PERMISSION);
                }
            } else {
                if(sender.hasPermission("CustomMessages.globaljoin")){
                    String messageString = Util.getSpacedString(args, 1, args.length);
                    messageString = CustomMessages.p.config.setGlobalMessage(messageString, MessageTypes.JOIN);
                    sender.sendMessage(ChatColor.GREEN + "Set the global join message to:");
                    sender.sendMessage(messageString);
                } else {
                    sender.sendMessage(NO_PERMISSION);
                }
            }
            return true;
        }
        if(args[0].equalsIgnoreCase("globalquit")){
            if(args.length == 1){
                if(!CustomMessages.p.config.permsRequired() || sender.hasPermission("CustomMessages.globalquit")){
                    sender.sendMessage(ChatColor.GREEN + "Current global quit message:");
                    sender.sendMessage(CustomMessages.p.config.getGlobalMessage(MessageTypes.QUIT));
                } else {
                    sender.sendMessage(NO_PERMISSION);
                }
            } else {
                if(sender.hasPermission("CustomMessages.globalquit")){
                    String messageString = Util.getSpacedString(args, 1, args.length - 1);
                    messageString = CustomMessages.p.config.setGlobalMessage(messageString, MessageTypes.QUIT);
                    sender.sendMessage(ChatColor.GREEN + "Set the global quit message to:");
                    sender.sendMessage(messageString);
                } else {
                    sender.sendMessage(NO_PERMISSION);
                }
            }
            return true;
        }
        if(args[0].equalsIgnoreCase("resetglobaljoin")){
            if(sender.hasPermission("CustomMessages.globaljoin")){
                CustomMessages.p.config.resetGlobalMessage(MessageTypes.JOIN);
                sender.sendMessage(ChatColor.GREEN + "Reset the global join message to the Minecraft default");
            } else {
                sender.sendMessage(NO_PERMISSION);
            }
            return true;
        }
        if(args[0].equalsIgnoreCase("resetglobalquit")){
            if(sender.hasPermission("CustomMessages.globalquit")){
                CustomMessages.p.config.resetGlobalMessage(MessageTypes.QUIT);
                sender.sendMessage(ChatColor.GREEN + "Reset the global quit message to the Minecraft default");
            } else {
                sender.sendMessage(NO_PERMISSION);
            }
            return true;
        }
        if(args[0].equalsIgnoreCase("colors")){
            sender.sendMessage(CustomMessages.p.config.getColorsString());
            return true;
        }
        if(args[0].equalsIgnoreCase("variables")){
            sender.sendMessage(ChatColor.GREEN + "/name" + ChatColor.GRAY + " The real name of the player");
            sender.sendMessage(ChatColor.GREEN + "/nname" + ChatColor.GRAY + " The nickname of the player");
            sender.sendMessage(ChatColor.GREEN + "/count" + ChatColor.GRAY + " The number of players that have been on the server");
            return true;
        }
        showAllHelp(sender);
        return true;
    }

    public void showAllHelp(CommandSender sender) {
        sender.sendMessage(ChatColor.GREEN + "Custom Messages - v " + CustomMessages.p.getDescription().getVersion() + " by psycowithespn");
        showJoinHelp(sender);
        showQuitHelp(sender);
        if (sender.hasPermission("CustomMessages.globaljoin")) {
            sender.sendMessage(ChatColor.GREEN + "/cm globaljoin [message]" + ChatColor.GRAY + " Gets or sets the default global join message");
            sender.sendMessage(ChatColor.GREEN + "/cm resetglobaljoin" + ChatColor.GRAY + " Resets the global join message");
        }
        if (sender.hasPermission("CustomMessages.globalquit")) {
            sender.sendMessage(ChatColor.GREEN + "/cm globalquit [message]" + ChatColor.GRAY + " Gets or sets the default global quit message");
            sender.sendMessage(ChatColor.GREEN + "/cm resetglobalquit" + ChatColor.GRAY + " Resets the global quit message");
        }
        if (sender.hasPermission("CustomMessages.reload")) {
            sender.sendMessage(ChatColor.GREEN + "/cm reload" + ChatColor.GRAY + " Reloads config from file");
        }
        sender.sendMessage(ChatColor.GREEN + "/cm colors" + ChatColor.GRAY + " Shows all of the color codes");
        sender.sendMessage(ChatColor.GREEN + "/cm variables" + ChatColor.GRAY + " Shows all of the message variables");
    }

    public void showJoinHelp(CommandSender sender) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.GREEN + "/cm join [player] [message]" + ChatColor.GRAY + " Gets or sets the join message of an online player");
            sender.sendMessage(ChatColor.GREEN + "/cm resetjoin [player]" + ChatColor.GRAY + " Resets the join message of an online player");
            return;
        }
        if (sender.hasPermission("CustomMessages.join")) {
            if (sender.hasPermission("CustomMessages.join.other")) {
                sender.sendMessage(ChatColor.GREEN + "/cm join [message|player] [message]" + ChatColor.GRAY + " Gets or sets the join message of you or another");
                sender.sendMessage(ChatColor.GREEN + "/cm resetjoin [player]" + ChatColor.GRAY + " Resets the join message of you or another");
            } else {
                sender.sendMessage(ChatColor.GREEN + "/cm join [message]" + ChatColor.GRAY + " Gets or sets your join message");
                sender.sendMessage(ChatColor.GREEN + "/cm resetjoin" + ChatColor.GRAY + " Resets your join message");
            }
        }
    }

    public void showQuitHelp(CommandSender sender) {
        if(!(sender instanceof Player)){
            sender.sendMessage(ChatColor.GREEN + "/cm quit [player] [message]" + ChatColor.GRAY + " Gets or sets the quit message of an online player");
            sender.sendMessage(ChatColor.GREEN + "/cm resetquit [player]" + ChatColor.GRAY + " Resets the quit message of an online player");
            return;
        }
        if (sender.hasPermission("CustomMessages.quit")) {
            if (sender.hasPermission("CustomMessages.quit.other")) {
                sender.sendMessage(ChatColor.GREEN + "/cm quit [message|player] [message]" + ChatColor.GRAY + " Gets or sets the quit message of you or another");
                sender.sendMessage(ChatColor.GREEN + "/cm resetquit [player]" + ChatColor.GRAY + " Resets the quit message of you or another");

            } else {
                sender.sendMessage(ChatColor.GREEN + "/cm quit [message]" + ChatColor.GRAY + " Gets or sets your quit message");
                sender.sendMessage(ChatColor.GREEN + "/cm resetquit" + ChatColor.GRAY + " Resets your quit message");
            }
        }
    }
}
