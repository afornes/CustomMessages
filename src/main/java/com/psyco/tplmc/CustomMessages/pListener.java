package com.psyco.tplmc.CustomMessages;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class pListener implements Listener {
	public CustomMessages plugin;

	public pListener(CustomMessages plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerQuit(PlayerQuitEvent event) {
		if(event.getQuitMessage() != null){
		event.setQuitMessage(plugin.config.getColoredMessage(event.getPlayer(), "Quit"));
		}

	}

    @EventHandler(priority = EventPriority.HIGH)
	public void onPlayerJoin(PlayerJoinEvent event) {
		if(event.getJoinMessage() != null){
		    event.setJoinMessage(plugin.config.getColoredMessage(event.getPlayer(), "Join"));
		}
	
	}

}
