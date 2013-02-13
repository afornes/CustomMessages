package com.psyco.tplmc.CustomMessages.configuration.defaultvariables;

import com.psyco.tplmc.CustomMessages.MessageTypes;
import com.psyco.tplmc.CustomMessages.configuration.MessageVariable;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class VariableOnline extends MessageVariable {

    @Override
    public String getReplacement(Player player, MessageTypes type) {
        if (type == MessageTypes.QUIT) {
            return String.valueOf(Bukkit.getOnlinePlayers().length - 1);
        } else {
            return String.valueOf(Bukkit.getOnlinePlayers().length);
        }
    }
}
