package com.psyco.tplmc.CustomMessages.commands;

import com.psyco.tplmc.CustomMessages.CustomMessages;
import com.psyco.tplmc.CustomMessages.MessageTypes;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class CommandBase {

    protected static final String NO_PERMISSION = ChatColor.RED + "You don't have permission for this command";

    protected static final String PLAYER_DISABLED_MESSAGE = ChatColor.WHITE + " (" + ChatColor.RED + "PLAYER DISABLED" + ChatColor.WHITE + ")";

    protected static final String GROUP_DISABLED_MESSAGE = ChatColor.WHITE + " (" + ChatColor.RED + "GROUP DISABLED" + ChatColor.WHITE + ")";

    protected static final String GLOBAL_DISABLED_MESSAGE = ChatColor.WHITE + " (" + ChatColor.RED + "GLOBAL DISABLED" + ChatColor.WHITE + ")";

    public abstract void onPlayerCommand(Player player, String label, String[] args);

    public abstract void onCommandSenderCommand(CommandSender sender, String label, String[] args);

    public static String getPlayerDisabledText(Player player, MessageTypes type) {
        if (!CustomMessages.getConfiguration().isPlayerMessageEnabled(player, type)) {
            return PLAYER_DISABLED_MESSAGE;
        }
        if(CustomMessages.getConfiguration().isPlayerMessageSet(player, type))
            return "";
        if (CustomMessages.getVaultCompat().getGroup(player) != null)
            return getGroupDisabledText(CustomMessages.getVaultCompat().getGroup(player), type);
        return getGlobalDisabledText(type);
    }

    public static String getGroupDisabledText(String group, MessageTypes type) {
        if (!CustomMessages.getConfiguration().isGroupMessageEnabled(group, type))
            return GROUP_DISABLED_MESSAGE;
        if(CustomMessages.getConfiguration().isGroupMessageSet(group, type))
            return "";
        return getGlobalDisabledText(type);
    }

    public static String getGlobalDisabledText(MessageTypes type) {
        if (!CustomMessages.getConfiguration().isGlobalMessageEnabled(type))
            return GLOBAL_DISABLED_MESSAGE;
        return "";
    }
}
