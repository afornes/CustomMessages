package com.psyco.tplmc.CustomMessages.configuration;

import com.psyco.tplmc.CustomMessages.CustomMessages;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Configuration {
    
    private CustomMessages plugin;
    private YamlConfiguration config;
    private File configFile;

    public Configuration(CustomMessages instance) {
        this.plugin = instance;
    }

    public void loadConfig() {
        configFile = new File(plugin.getDataFolder(), "config.yml");
        config = YamlConfiguration.loadConfiguration(configFile);
        config.addDefault("Config.Global-Quit-Message", "&e/name &eleft the game.");
        config.addDefault("Config.Global-Join-Message", "&e/name &ejoined the game.");
        config.options().copyDefaults(true);
        saveConfig();
    }

    public void saveConfig() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public String getColoredMessage(Player p, String action) {
        String name = p.getName();
        String actionLC = action.toLowerCase();
        String message = null;
        if (config.contains("users." + name + "." + actionLC)) {
            message = config.getString("users." + name + "." + actionLC);
            if (message != null) {
                message =  message.replaceAll("(&([a-f0-9]))", "\u00A7$2").replaceAll("/name", p.getDisplayName());
                return message.replaceAll("/count", Arrays.asList(plugin.getServer().getOfflinePlayers()).size() + "");
            }
        }
        message = getGlobalMessage(action);
        if (message != null) {
            message =  message.replaceAll("(&([a-f0-9]))", "\u00A7$2").replaceAll("/name", p.getDisplayName());
            return message.replaceAll("/count", Arrays.asList(plugin.getServer().getOfflinePlayers()).size() + "");
        }
        return message;
    }

    public String setColoredMessage(Player p, String message, String action) {
        String name = p.getName();
        action = action.toLowerCase();
        config.set("users." + name + "." + action, message);
        saveConfig();
        return message.replaceAll("(&([a-f0-9]))", "\u00A7$2");
    }

    public String getGlobalMessage(String action) {
        return (config.getString("Config.Global-" + action + "-Message"))
                .replaceAll("(&([a-f0-9]))", "\u00A7$2");
    }

    public String setGlobalMessage(String message, String action) {
        config.set("Config.Global-" + action + "-Message", message);
        saveConfig();
        return message.replaceAll("(&([a-f0-9]))", "\u00A7$2");
    }

    public boolean getRequiredPerms() {
        return config.getBoolean("Config.Use-Permissions-For-Messages");
    }

    public boolean getAutoUpdate() {
        return config.getBoolean("Config.Auto-Update");
    }

    public void resetColoredMessage(Player p, String action) {
        action = action.toLowerCase();
        if (config.contains("users." + p.getName() + "." + action)) {


            config.set("users." + p.getName() + "." + action, null);
            saveConfig();
        }
    }

    public void resetGlobalMessage(String action) {
        config.set("Config.Global-" + action + "-Message", null);
        saveConfig();
    }
}

