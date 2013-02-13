package com.psyco.tplmc.CustomMessages.configuration.defaultvariables;

import com.psyco.tplmc.CustomMessages.CustomMessages;
import com.psyco.tplmc.CustomMessages.MessageTypes;
import com.psyco.tplmc.CustomMessages.configuration.MessageVariable;
import org.bukkit.entity.Player;

public class VariableGroup extends MessageVariable {

    @Override
    public String getReplacement(Player player, MessageTypes type) {
        String group = CustomMessages.getVaultCompat().getGroup(player);
        if (group != null) {
            return group;
        } else {
            return "";
        }
    }
}
