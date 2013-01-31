package com.psyco.tplmc.CustomMessages;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (event.getQuitMessage() != null) {
            event.setQuitMessage(CustomMessages.getConfiguration().getColoredMessage(event.getPlayer(), MessageTypes.QUIT));
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (event.getJoinMessage() != null) {
            event.setJoinMessage(CustomMessages.getConfiguration().getColoredMessage(event.getPlayer(), MessageTypes.JOIN));
        }
        if(!event.getPlayer().hasPlayedBefore() && CustomMessages.getConfiguration().isGlobalMessageEnabled(MessageTypes.FIRSTJOIN)){
            Bukkit.broadcastMessage(Util.translateColor(CustomMessages.getConfiguration().replaceVars(CustomMessages.getConfiguration().getGlobalMessage(MessageTypes.FIRSTJOIN), event.getPlayer())));
        }
    }

}
