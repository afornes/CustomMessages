package com.psyco.tplmc.CustomMessages.configuration;

import com.psyco.tplmc.CustomMessages.CustomMessages;
import com.psyco.tplmc.CustomMessages.MessageTypes;
import org.bukkit.entity.Player;

public abstract class MessageVariable {

    private String variable;
    private boolean isRegistered = false;

    public abstract String getReplacement(Player player, MessageTypes type);

    public final void register(String variable) {
        if (!isRegistered) {
            if (CustomMessages.getConfiguration().registerVariable(variable, this)) {
                this.variable = variable;
                isRegistered = true;
            } else {
                throw new RuntimeException("Failed to register variable \"" + variable + "\": " + this.getClass().getName());
            }
        } else {
            throw new RuntimeException("Attempted to register an already registered MessageVariable: " + this.getClass().getName());
        }
    }

    public final void unregister() {
        if (isRegistered && CustomMessages.getConfiguration().unregisterVariable(variable)) {
            this.variable = null;
            isRegistered = false;
        } else {
            throw new RuntimeException("Attempted to unregister an unregistered MessageVariable: " + this.getClass().getName());
        }
    }

    public final String getRegisteredVariable() {
        return variable;
    }

    public final boolean isRegistered() {
        return isRegistered;
    }
}
