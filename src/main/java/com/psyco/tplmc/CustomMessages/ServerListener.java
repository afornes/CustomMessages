package com.psyco.tplmc.CustomMessages;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;

public class ServerListener implements Listener {

    @EventHandler
    public void onPluginEnable(PluginEnableEvent evt){
        if(evt.getPlugin().getName().equalsIgnoreCase("Vault")){
            CustomMessages.p.getLogger().info("Found Vault, hooking into it");
            if(CustomMessages.getVaultCompat().hookVaultChat()){
                CustomMessages.p.getLogger().info("Hooked into Vault chat");
            } else {
                CustomMessages.p.getLogger().info("Failed to hook into Vault chat");
            }
            if(CustomMessages.getVaultCompat().hookVaultPerm()){
                CustomMessages.p.getLogger().info("Hooked into Vault permissions");
            } else {
                CustomMessages.p.getLogger().info("Failed to hook into Vault permissions");
            }
        }
    }

    @EventHandler
    public void onPluginDisable(PluginDisableEvent evt){
        if(evt.getPlugin().getName().equalsIgnoreCase("Vault")){
            CustomMessages.p.getLogger().info("Unhooking from Vault");
            CustomMessages.getVaultCompat().unhookVaultChat();
            CustomMessages.getVaultCompat().unhookVaultPerm();
        }
    }
}
