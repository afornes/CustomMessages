package com.psyco.tplmc.CustomMessages;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.PriorityQueue;

public class pListener implements Listener {
	public CustomMessages plugin;

	public pListener(CustomMessages plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerQuit(PlayerQuitEvent event) {
		if(event.getQuitMessage() != null){
		event.setQuitMessage(plugin.config.getColoredMessage(event.getPlayer(), "Quit"));
		/*Player ep = event.getPlayer();
		for(Player p : plugin.getServer().getOnlinePlayers()){
			SpoutPlayer sp = (SpoutPlayer)p;
			if(sp.isSpoutCraftEnabled()){
				sp.sendNotification(ep.getName() + " has", "logged off the server", Material.REDSTONE_TORCH_OFF);
			}
		}*/
		}

	}

    @EventHandler(priority = EventPriority.HIGH)
	public void onPlayerJoin(PlayerJoinEvent event) {
		if(event.getJoinMessage() != null){
		    event.setJoinMessage(plugin.config.getColoredMessage(event.getPlayer(), "Join"));
		/*Player ep = event.getPlayer();
		for(Player p : plugin.getServer().getOnlinePlayers()){
			SpoutPlayer sp = (SpoutPlayer)p;
			if(sp.isSpoutCraftEnabled()){
				sp.sendNotification(ep.getName() + " has", "logged on the server", Material.REDSTONE_TORCH_ON);
			}
		}*/
		}
	
	}


	public void onPlayerKick(PlayerKickEvent e){
		/*e.getType();
		Player ep = e.getPlayer();
		for(Player p : plugin.getServer().getOnlinePlayers()){
			SpoutPlayer sp = (SpoutPlayer)p;
			if(sp.isSpoutCraftEnabled()){
				sp.sendNotification(ep.getName() + " was", "kicked off the server", Material.REDSTONE_TORCH_OFF);
			}
		}*/
	}

}
