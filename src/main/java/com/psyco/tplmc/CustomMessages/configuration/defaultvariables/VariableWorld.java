package com.psyco.tplmc.CustomMessages.configuration.defaultvariables;

import com.psyco.tplmc.CustomMessages.MessageTypes;
import com.psyco.tplmc.CustomMessages.configuration.MessageVariable;
import org.bukkit.entity.Player;

public class VariableWorld extends MessageVariable {

    @Override
    public String getReplacement(Player player, MessageTypes type) {
        return player.getWorld().getName();
    }
}
