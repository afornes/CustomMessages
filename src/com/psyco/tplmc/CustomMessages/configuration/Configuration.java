package com.psyco.tplmc.CustomMessages.configuration;

import com.psyco.tplmc.CustomMessages.CustomMessages;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Configuration {
    private Map<String, Object> cMap = new HashMap<String, Object>();
    private CustomMessages plugin;
    private org.bukkit.util.config.Configuration config;

    public Configuration(CustomMessages instance) {
        this.plugin = instance;
    }

    public void loadConfig() {
        cMap.clear();
        config = new org.bukkit.util.config.Configuration(new File(plugin
                .getDataFolder().getPath() + "/config.yml"));
        config.load();
        config.getBoolean("Config.Use-Permissions-For-Messages", true);
        config.getBoolean("Config.Auto-Update", true);
        config.getString("Config.Global-Quit-Message",
                "&e/name &eleft the game.");
        config.getString("Config.Global-Join-Message",
                "&e/name &ejoined the game.");
        config.save();
        cMap = config.getAll();

    }

    public void saveConfig() {
        config.save();
        config = new org.bukkit.util.config.Configuration(new File(plugin
                .getDataFolder().getPath() + "/config.yml"));
        loadConfig();
        cMap.clear();
        cMap = config.getAll();
    }

    public String getColoredMessage(Player p, String action) {
        String name = p.getName();
        String actionLC = action.toLowerCase();
        String message = null;
        if (cMap.containsKey("users" + name + "." + actionLC)) {
            message = (String) cMap
                    .get("users." + name + "." + actionLC);
            if (message != null) {
                return message.replaceAll("(&([a-f0-9]))", "\u00A7$2").replace("/name", p.getDisplayName());
            }
        }
        message = getGlobalMessage(action);
        if (message != null) {
            return message.replaceAll("(&([a-f0-9]))", "\u00A7$2").replace("/name", p.getDisplayName());
        }
        return message;
    }

    public String setColoredMessage(Player p, String message, String action) {
        String name = p.getName();
        action = action.toLowerCase();
        config.setProperty("users." + name + "." + action, message);
        saveConfig();
        return message.replaceAll("(&([a-f0-9]))", "\u00A7$2");
    }

    public String getGlobalMessage(String action) {
        return ((String) cMap.get("Config.Global-" + action + "-Message"))
                .replaceAll("(&([a-f0-9]))", "\u00A7$2");
    }

    public String setGlobalMessage(String message, String action) {
        config.setProperty("Config.Global-" + action + "-Message", message);
        saveConfig();
        return message.replaceAll("(&([a-f0-9]))", "\u00A7$2");
    }

    public boolean getRequiredPerms() {
        return (Boolean) cMap.get("Config.Use-Permissions-For-Messages");
    }

    public boolean getAutoUpdate() {
        return (Boolean) cMap.get("Config.Auto-Update");
    }

    public void resetColoredMessage(Player p, String action) {
        action = action.toLowerCase();
        if (cMap.containsKey("users." + p.getName() + "." + action)) {


            config.removeProperty("users." + p.getName() + "." + action);
            saveConfig();
        }
    }

    public void resetGlobalMessage(String action) {
        config.removeProperty("Config.Global-" + action + "-Message");
        saveConfig();
    }
}

