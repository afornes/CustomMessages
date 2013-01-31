package com.psyco.tplmc.CustomMessages.commands;

import com.psyco.tplmc.CustomMessages.CustomMessages;
import com.psyco.tplmc.CustomMessages.MessageTypes;
import com.psyco.tplmc.CustomMessages.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmJoinCommand extends CommandBase {

    private CmJoinCommand() {
        CommandManager.getInstance().registerCommand("join", this);
    }

    static {
        new CmJoinCommand();
    }

    @Override
    public void onPlayerCommand(Player player, String label, String[] args) {
        if (args.length == 0) {
            if (!CustomMessages.getConfiguration().permsRequired() || player.hasPermission("CustomMessages.join")) {
                player.sendMessage(ChatColor.GREEN + "Your current join message:");
                player.sendMessage(Util.translateColor(CustomMessages.getConfiguration().getPlayerMessage(player, MessageTypes.JOIN)) + getPlayerDisabledText(player, MessageTypes.JOIN));
            } else {
                player.sendMessage(NO_PERMISSION);
            }
        } else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("enable")) {
                if (player.hasPermission("CustomMessages.join")) {
                    if (CustomMessages.getConfiguration().setPlayerMessageEnabled(player, MessageTypes.JOIN, true)) {
                        player.sendMessage(ChatColor.GREEN + "Your join message is now enabled");
                    } else {
                        player.sendMessage(ChatColor.RED + "Your join message is already enabled");
                    }
                } else {
                    player.sendMessage(NO_PERMISSION);
                }
            } else if (args[0].equalsIgnoreCase("disable")) {
                if (player.hasPermission("CustomMessages.join")) {
                    if (CustomMessages.getConfiguration().setPlayerMessageEnabled(player, MessageTypes.JOIN, false)) {
                        player.sendMessage(ChatColor.GREEN + "Your join message is now disabled");
                    } else {
                        player.sendMessage(ChatColor.RED + "Your join message is already disabled");
                    }
                }
            } else if (args[0].equalsIgnoreCase("reset")) {
                if (player.hasPermission("CustomMessages.join")) {
                    CustomMessages.getConfiguration().resetPlayerMessage(player, MessageTypes.JOIN);
                    player.sendMessage(ChatColor.GREEN + "Reset your join message:");
                    player.sendMessage(Util.translateColor(CustomMessages.getConfiguration().getPlayerMessage(player, MessageTypes.JOIN)) + getPlayerDisabledText(player, MessageTypes.JOIN));
                } else {
                    player.sendMessage(NO_PERMISSION);
                }
            } else {
                Player target = Bukkit.getPlayer(args[0]);
                if (target != null) {
                    if (player.hasPermission("CustomMessages.join.other")) {
                        player.sendMessage(ChatColor.GREEN + target.getName() + "'s current join message:");
                        player.sendMessage(Util.translateColor(CustomMessages.getConfiguration().getPlayerMessage(target, MessageTypes.JOIN)) + getPlayerDisabledText(target, MessageTypes.JOIN));
                    } else {
                        player.sendMessage(NO_PERMISSION);
                    }
                } else if (CustomMessages.getVaultCompat().isGroup(args[0])) {
                    if (player.hasPermission("CustomMessages.join.group")) {
                        player.sendMessage(ChatColor.GREEN + "Group " + args[0] + "'s current join message:");
                        player.sendMessage(Util.translateColor(CustomMessages.getConfiguration().getGroupMessage(args[0], MessageTypes.JOIN)) + getGroupDisabledText(args[0], MessageTypes.JOIN));
                    } else {
                        player.sendMessage(NO_PERMISSION);
                    }
                } else {
                    if (player.hasPermission("CustomMessages.join")) {
                        player.sendMessage(ChatColor.GREEN + "Set your join message to:");
                        player.sendMessage(CustomMessages.getConfiguration().setPlayerMessage(player, MessageTypes.JOIN, args[0]) + getPlayerDisabledText(player, MessageTypes.JOIN));
                    } else {
                        player.sendMessage(NO_PERMISSION);
                    }
                }
            }
        } else {
            Player target = Bukkit.getPlayer(args[0]);
            if (target != null) {
                if (target.getName().equals(player.getName())) {
                    if (player.hasPermission("CustomMessages.join")) {
                        if (args[0].equalsIgnoreCase("enable")) {
                            if (CustomMessages.getConfiguration().setPlayerMessageEnabled(player, MessageTypes.JOIN, true)) {
                                player.sendMessage(ChatColor.GREEN + "Your join message is now enabled");
                            } else {
                                player.sendMessage(ChatColor.RED + "Your join message is already enabled");
                            }
                        } else if (args[0].equalsIgnoreCase("disable")) {
                            if (CustomMessages.getConfiguration().setPlayerMessageEnabled(player, MessageTypes.JOIN, false)) {
                                player.sendMessage(ChatColor.GREEN + "Your join message is now disabled");
                            } else {
                                player.sendMessage(ChatColor.GREEN + "Your join message is already disabled");
                            }
                        } else if (args[0].equalsIgnoreCase("reset")) {
                            CustomMessages.getConfiguration().resetPlayerMessage(player, MessageTypes.JOIN);
                            player.sendMessage(ChatColor.GREEN + "Reset your join message:");
                            player.sendMessage(Util.translateColor(CustomMessages.getConfiguration().getPlayerMessage(player, MessageTypes.JOIN)) + getPlayerDisabledText(player, MessageTypes.JOIN));
                        } else {
                            String messageString = Util.getSpacedString(args, 1, args.length);
                            messageString = CustomMessages.getConfiguration().setPlayerMessage(player, MessageTypes.JOIN, messageString);
                            player.sendMessage(ChatColor.GREEN + "Set your join message to:");
                            player.sendMessage(messageString + getPlayerDisabledText(player, MessageTypes.JOIN));
                        }
                    } else {
                        player.sendMessage(NO_PERMISSION);
                    }
                } else {
                    if (player.hasPermission("CustomMessages.join.other")) {
                        if (args[1].equalsIgnoreCase("enable")) {
                            if (CustomMessages.getConfiguration().setPlayerMessageEnabled(target, MessageTypes.JOIN, true)) {
                                player.sendMessage(ChatColor.GREEN + target.getName() + "'s join message is now enabled");
                                target.sendMessage(ChatColor.GREEN + "Your join message was enabled by " + player.getName());
                            } else {
                                player.sendMessage(ChatColor.GREEN + target.getName() + "'s join message was already enabled");
                            }
                        } else if (args[1].equalsIgnoreCase("disable")) {
                            if (CustomMessages.getConfiguration().setPlayerMessageEnabled(target, MessageTypes.JOIN, false)) {
                                player.sendMessage(ChatColor.GREEN + target.getName() + "'s join message is now disabled");
                                target.sendMessage(ChatColor.GREEN + "Your join message was disabled by " + player.getName());
                            } else {
                                player.sendMessage(ChatColor.GREEN + target.getName() + "'s join message was already disabled");
                            }
                        } else if (args[1].equalsIgnoreCase("reset")) {
                            CustomMessages.getConfiguration().resetPlayerMessage(target, MessageTypes.JOIN);
                            player.sendMessage(ChatColor.GREEN + "Reset " + target.getName() + "'s join message:");
                            target.sendMessage(ChatColor.GREEN + player.getName() + " reset your join message:");
                            player.sendMessage(Util.translateColor(CustomMessages.getConfiguration().getPlayerMessage(target, MessageTypes.JOIN)) + getPlayerDisabledText(target, MessageTypes.JOIN));
                            target.sendMessage(Util.translateColor(CustomMessages.getConfiguration().getPlayerMessage(target, MessageTypes.JOIN)) + getPlayerDisabledText(target, MessageTypes.JOIN));
                        } else {
                            String messageString = Util.getSpacedString(args, 1, args.length);
                            messageString = CustomMessages.getConfiguration().setPlayerMessage(target, MessageTypes.JOIN, messageString);
                            player.sendMessage(ChatColor.GREEN + "Set " + target.getName() + "'s join message to:");
                            player.sendMessage(messageString + getPlayerDisabledText(target, MessageTypes.JOIN));
                            target.sendMessage(ChatColor.GREEN + player.getName() + " set your join message to:");
                            target.sendMessage(messageString + getPlayerDisabledText(target, MessageTypes.JOIN));
                        }
                    } else {
                        player.sendMessage(NO_PERMISSION);
                    }
                }
            } else if (CustomMessages.getVaultCompat().isGroup(args[0])) {
                if (player.hasPermission("CustomMessages.join.group")) {
                    if (args[1].equalsIgnoreCase("enable")) {
                        if (CustomMessages.getConfiguration().setGroupMessageEnabled(args[0], MessageTypes.JOIN, true)) {
                            player.sendMessage(ChatColor.GREEN + "Group " + args[0] + "'s join message was enabled");
                        } else {
                            player.sendMessage(ChatColor.GREEN + "Group " + args[0] + "'s join message is already enabled");
                        }
                    } else if (args[1].equalsIgnoreCase("disable")) {
                        if (CustomMessages.getConfiguration().setGroupMessageEnabled(args[0], MessageTypes.JOIN, false)) {
                            player.sendMessage(ChatColor.GREEN + "Group " + args[0] + "'s join message was disabled");
                        } else {
                            player.sendMessage(ChatColor.GREEN + "Group " + args[0] + "'s join message is already disabled");
                        }
                    } else if(args[1].equalsIgnoreCase("reset")){
                        CustomMessages.getConfiguration().resetGroupMessage(args[0], MessageTypes.JOIN);
                        player.sendMessage(ChatColor.GREEN + "Reset group " + args[0] + "'s join message:");
                        player.sendMessage(Util.translateColor(CustomMessages.getConfiguration().getGroupMessage(args[0], MessageTypes.JOIN)) + getGroupDisabledText(args[0], MessageTypes.JOIN));
                    } else {
                        String messageString = Util.getSpacedString(args, 1, args.length);
                        messageString = CustomMessages.getConfiguration().setGroupMessage(args[0], MessageTypes.JOIN, messageString);
                        player.sendMessage(ChatColor.GREEN + "Group " + args[0] + "'s join message is now:");
                        player.sendMessage(messageString + getGroupDisabledText(args[0], MessageTypes.JOIN));
                    }
                } else {
                    player.sendMessage(NO_PERMISSION);
                }
            } else {
                if (player.hasPermission("CustomMessages.join")) {
                    String messageString = Util.getSpacedString(args, 0, args.length);
                    messageString = CustomMessages.getConfiguration().setPlayerMessage(player, MessageTypes.JOIN, messageString);
                    player.sendMessage(ChatColor.GREEN + "Set your join message to:");
                    player.sendMessage(messageString);
                } else {
                    player.sendMessage(NO_PERMISSION);
                }
            }
        }
    }

    @Override
    public void onCommandSenderCommand(CommandSender sender, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.GREEN + "/" + label + " join <player|group> [message|enable|disable|reset]");
        } else if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target != null) {
                if (sender.hasPermission("CustomMessages.join.other")) {
                    sender.sendMessage(ChatColor.GREEN + target.getName() + "'s current join message:");
                    sender.sendMessage(Util.translateColor(CustomMessages.getConfiguration().getPlayerMessage(target, MessageTypes.JOIN)) + getPlayerDisabledText(target, MessageTypes.JOIN));
                } else {
                    sender.sendMessage(NO_PERMISSION);
                }
            } else if (CustomMessages.getVaultCompat().isGroup(args[0])) {
                if (sender.hasPermission("CustomMessages.join.group")) {
                    sender.sendMessage(ChatColor.GREEN + "Group " + args[0] + "'s current join message:");
                    sender.sendMessage(Util.translateColor(CustomMessages.getConfiguration().getGroupMessage(args[0], MessageTypes.JOIN)) + getGroupDisabledText(args[0], MessageTypes.JOIN));
                } else {
                    sender.sendMessage(NO_PERMISSION);
                }
            } else {
                sender.sendMessage(ChatColor.GREEN + "Could not find a player or group called '" + args[0] + "'");
            }
        } else {
            Player target = Bukkit.getPlayer(args[0]);
            if (target != null) {
                if (sender.hasPermission("CustomMessages.join.other")) {
                    if (args[1].equalsIgnoreCase("enable")) {
                        if (CustomMessages.getConfiguration().setPlayerMessageEnabled(target, MessageTypes.JOIN, true)) {
                            sender.sendMessage(ChatColor.GREEN + target.getName() + "'s join message was enabled");
                            target.sendMessage(ChatColor.GREEN + sender.getName() + " enabled your join message");
                        } else {
                            sender.sendMessage(ChatColor.GREEN + target.getName() + "'s join message is already enabled");
                        }
                    } else if (args[1].equalsIgnoreCase("disable")) {
                        if (CustomMessages.getConfiguration().setPlayerMessageEnabled(target, MessageTypes.JOIN, false)) {
                            sender.sendMessage(ChatColor.GREEN + target.getName() + "'s join message was disabled");
                            target.sendMessage(ChatColor.GREEN + sender.getName() + " disabled your join message");
                        } else {
                            sender.sendMessage(ChatColor.GREEN + target.getName() + "'s join message is already disabled");
                        }
                    } else if(args[1].equalsIgnoreCase("reset")){
                        CustomMessages.getConfiguration().resetPlayerMessage(target, MessageTypes.JOIN);
                        sender.sendMessage(ChatColor.GREEN + "Reset " + target.getName() + "'s join message:");
                        sender.sendMessage(Util.translateColor(CustomMessages.getConfiguration().getPlayerMessage(target, MessageTypes.JOIN)) + getPlayerDisabledText(target, MessageTypes.JOIN));
                        target.sendMessage(ChatColor.GREEN + sender.getName() + " reset your join message:");
                        target.sendMessage(Util.translateColor(CustomMessages.getConfiguration().getPlayerMessage(target, MessageTypes.JOIN)) + getPlayerDisabledText(target, MessageTypes.JOIN));
                    } else {
                        String messageString = Util.getSpacedString(args, 1, args.length);
                        messageString = CustomMessages.getConfiguration().setPlayerMessage(target, MessageTypes.JOIN, messageString);
                        sender.sendMessage(ChatColor.GREEN + target.getName() + "'s join message is now:");
                        sender.sendMessage(messageString + getPlayerDisabledText(target, MessageTypes.JOIN));
                        target.sendMessage(ChatColor.GREEN + sender.getName() + " set your join message to:");
                        target.sendMessage(messageString + getPlayerDisabledText(target, MessageTypes.JOIN));
                    }
                } else {
                    sender.sendMessage(NO_PERMISSION);
                }
            } else if (CustomMessages.getVaultCompat().isGroup(args[0])) {
                if (sender.hasPermission("CustomMessages.join.group")) {
                    if (args[1].equalsIgnoreCase("enable")) {
                        if (CustomMessages.getConfiguration().setGroupMessageEnabled(args[0], MessageTypes.JOIN, true)) {
                            sender.sendMessage(ChatColor.GREEN + "Group " + args[0] + "'s join message was enabled");
                        } else {
                            sender.sendMessage(ChatColor.GREEN + "Group " + args[0] + "'s join message is already enabled");
                        }
                    } else if (args[1].equalsIgnoreCase("disable")) {
                        if (CustomMessages.getConfiguration().setGroupMessageEnabled(args[0], MessageTypes.JOIN, false)) {
                            sender.sendMessage(ChatColor.GREEN + "Group " + args[0] + "'s join message was disabled");
                        } else {
                            sender.sendMessage(ChatColor.GREEN + "Group " + args[0] + "'s join message is already disabled");
                        }
                    } else if (args[1].equalsIgnoreCase("reset")) {
                        CustomMessages.getConfiguration().resetGroupMessage(args[0], MessageTypes.JOIN);
                        sender.sendMessage(ChatColor.GREEN + "Reset group " + args[0] + "'s join message:");
                        sender.sendMessage(Util.translateColor(CustomMessages.getConfiguration().getGroupMessage(args[0], MessageTypes.JOIN)) + getGroupDisabledText(args[0], MessageTypes.JOIN));
                    } else {
                        String messageString = Util.getSpacedString(args, 1, args.length);
                        messageString = CustomMessages.getConfiguration().setGroupMessage(args[0], MessageTypes.JOIN, messageString);
                        sender.sendMessage(ChatColor.GREEN + "Group " + args[0] + "'s join message is now:");
                        sender.sendMessage(messageString + getGroupDisabledText(args[0], MessageTypes.JOIN));
                    }
                } else {
                    sender.sendMessage(NO_PERMISSION);
                }
            } else {
                sender.sendMessage(ChatColor.GREEN + "Could not find a player or group called '" + args[0] + "'");
            }
        }
    }
}
