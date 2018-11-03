package com.psyco.tplmc.CustomMessages;

import com.psyco.tplmc.CustomMessages.configuration.Configuration;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (event.getPlayer().hasPermission("CustomMessages.quit") && event.getQuitMessage() != null) {
            String message = CustomMessages.getConfiguration().getColoredMessage(event.getPlayer(), MessageTypes.QUIT);
            event.setQuitMessage(message);
            if (CustomMessages.getConfiguration().logToConsole()) {
                Bukkit.getLogger().info(message);
            }
        } else if (!event.getPlayer().hasPermission("CustomMessages.quit") && event.getQuitMessage() != null) {
            String message = Util.translateColor(CustomMessages.getConfiguration().replaceVars(CustomMessages.getConfiguration().getGlobalMessage(MessageTypes.QUIT), event.getPlayer(), MessageTypes.QUIT));
            event.setQuitMessage(message);
            if (CustomMessages.getConfiguration().logToConsole()) {
                Bukkit.getLogger().info(message);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (event.getPlayer().hasPermission("CustomMessages.join") && event.getJoinMessage() != null) {
            String message = CustomMessages.getConfiguration().getColoredMessage(event.getPlayer(), MessageTypes.JOIN);
            event.setJoinMessage(message);
            if (CustomMessages.getConfiguration().logToConsole()) {
                Bukkit.getLogger().info(message);
            }
        } else if (!event.getPlayer().hasPermission("CustomMessages.join") && event.getJoinMessage() != null) {
            String message = Util.translateColor(CustomMessages.getConfiguration().replaceVars(CustomMessages.getConfiguration().getGlobalMessage(MessageTypes.JOIN), event.getPlayer(), MessageTypes.JOIN));
            event.setJoinMessage(message);
            if (CustomMessages.getConfiguration().logToConsole()) {
                Bukkit.getLogger().info(message);
            }
        }
        if(!event.getPlayer().hasPlayedBefore() && CustomMessages.getConfiguration().isGlobalMessageEnabled(MessageTypes.FIRSTJOIN)){
            String message = Util.translateColor(CustomMessages.getConfiguration().replaceVars(CustomMessages.getConfiguration().getGlobalMessage(MessageTypes.FIRSTJOIN), event.getPlayer(), MessageTypes.FIRSTJOIN));
            Bukkit.broadcastMessage(message);
            if (CustomMessages.getConfiguration().logToConsole()) {
                Bukkit.getLogger().info(message);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerKick(PlayerKickEvent event) {
        if (event.getLeaveMessage() != null && CustomMessages.getConfiguration().isGlobalMessageEnabled(MessageTypes.KICK)) {
            String message = Util.translateColor(CustomMessages.getConfiguration().replaceVars(CustomMessages.getConfiguration().getGlobalMessage(MessageTypes.KICK), event.getPlayer(), MessageTypes.KICK));
            event.setLeaveMessage(message);
            if (CustomMessages.getConfiguration().logToConsole()) {
                Bukkit.getLogger().info(message);
            }
        }
    }

}
