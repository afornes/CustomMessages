package com.psyco.tplmc.CustomMessages.configuration.defaultvariables;

import com.psyco.tplmc.CustomMessages.CustomMessages;
import com.psyco.tplmc.CustomMessages.MessageTypes;
import com.psyco.tplmc.CustomMessages.configuration.MessageVariable;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class VariableSuffix extends MessageVariable {

    @Override
    public String getReplacement(Player player, MessageTypes type) {
        if (player.hasPermission("CustomMessages.morevariables")) {
            String suffix = CustomMessages.getVaultCompat().getSuffix(player);
            if (suffix != null) {
                return suffix;
            } else {
                return "";
            }
        } else {
            return "";
        }
    }
}
