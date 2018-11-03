package com.psyco.tplmc.CustomMessages.configuration.defaultvariables;

import com.psyco.tplmc.CustomMessages.MessageTypes;
import com.psyco.tplmc.CustomMessages.configuration.MessageVariable;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class VariableOnline extends MessageVariable {

    @Override
    public String getReplacement(Player player, MessageTypes type) {
        if (player.hasPermission("CustomMessages.morevariables")) {
            if (type == MessageTypes.QUIT) {
                return String.valueOf(Bukkit.getOnlinePlayers().size() - 1);
            } else {
                return String.valueOf(Bukkit.getOnlinePlayers().size());
            }
        } else {
            return "";
        }
    }
}
