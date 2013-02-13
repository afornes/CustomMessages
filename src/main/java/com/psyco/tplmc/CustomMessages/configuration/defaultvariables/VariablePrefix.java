package com.psyco.tplmc.CustomMessages.configuration.defaultvariables;

import com.psyco.tplmc.CustomMessages.CustomMessages;
import com.psyco.tplmc.CustomMessages.MessageTypes;
import com.psyco.tplmc.CustomMessages.configuration.MessageVariable;
import org.bukkit.entity.Player;

public class VariablePrefix extends MessageVariable {

    @Override
    public String getReplacement(Player player, MessageTypes type) {
        String prefix = CustomMessages.getVaultCompat().getPrefix(player);
        if (prefix != null) {
            return prefix;
        } else {
            return "";
        }
    }
}
