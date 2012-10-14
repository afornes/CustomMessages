package com.psyco.tplmc.CustomMessages.configuration;

import com.psyco.tplmc.CustomMessages.CustomMessages;
import com.psyco.tplmc.CustomMessages.MessageTypes;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class Configuration {

    private YamlConfiguration config;
    private File configFile;
    private static final String colorString = "&0&&00&1&&11&2&&22&3&&33&4&&44&5&&55&6&&66&7&&77&8&&88&9&&99&a&&aa&b&&bb&c&&cc&d&&dd&e&&ee&f&&ff".replaceAll("(&([a-f0-9]))", "\u00A7$2");

    public Configuration(CustomMessages instance) {
        configFile = new File(instance.getDataFolder(), "config.yml");
    }

    public boolean loadConfig() {
        try {
            config = YamlConfiguration.loadConfiguration(configFile);
            saveConfig();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void setDefaults() {
        String defQuit = "&e/name &eleft the game.";
        if (config.isString("Config.Global-Quit-Message")) {
            defQuit = config.getString("Config.Global-Quit-Message");
            config.set("Config.Global-Quit-Message", null);
        }
        String defJoin = "&e/name &ejoined the game.";
        if (config.isString("Config.Global-Join-Message")) {
            defJoin = config.getString("Config.Global-Join-Message");
            config.set("Config.Global-Join-Message", null);
        }
        config.addDefault("config.must-have-permissions-to-show-current-messages", false);
        config.addDefault("config.global-join-message", defJoin);
        //config.addDefault("config.global-join-message-enabled", true);
        config.addDefault("config.global-quit-message", defQuit);
        //config.addDefault("config.global-quit-message-enabled", true);
        config.addDefault("config.message-prefix", "[&aCM&f] ");
        config.options().copyDefaults(true);
    }

    public void saveConfig() {
        try {
            setDefaults();
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public String getColoredMessage(Player p, MessageTypes action) {
        //if(!isMessageEnabled(p, action))
        //    return null;
        String name = p.getName();
        String message;
        if (config.contains("users." + name + "." + action.toString().toLowerCase())) {
            message = config.getString("users." + name + "." + action.toString().toLowerCase());
            if (message != null) {
                if (!p.hasPermission("CustomMessages.noprefix"))
                    message = getMessagePrefix() + message;
                return translateColor(replaceVars(message, p));
            }
        }
        message = getGlobalMessage(action);
        if (message != null) {
            return translateColor(replaceVars(message, p));
        }
        return message;
    }

    public String setColoredMessage(Player p, String message, MessageTypes action) {
        String name = p.getName();
        config.set("users." + name + "." + action.toString().toLowerCase(), message);
        saveConfig();
        return translateColor(message);
    }

    //public boolean isMessageEnabled(Player p, MessageTypes action){
    //    return config.getBoolean("users." + p.getName() + "." + action.toString().toLowerCase() + "-enabled");
    //}

    /*public boolean setMessageEnabled(Player p, MessageTypes action, boolean enabled){
        if(isMessageEnabled(p, action) == enabled)
            return false;
        config.set("users." + p.getName() + "." + action.toString().toLowerCase() + "-enabled", enabled);
        saveConfig();
        return true;
    }*/

    public String getGlobalMessage(MessageTypes action) {
        //if(config.getBoolean("confg.global-" + action.toString().toLowerCase() + "message-enabled"))
        //    return null;
        return translateColor(config.getString("config.global-" + action.toString().toLowerCase() + "-message"));
    }

    public String setGlobalMessage(String message, MessageTypes action) {
        config.set("config.global-" + action.toString().toLowerCase() + "-message", message);
        saveConfig();
        return translateColor(message);
    }

    //public boolean isGlobalMessageEnabled(MessageTypes action){
    //    return config.getBoolean("config.global-" + action.toString().toLowerCase() + "-message-enabled");
    //}

    //public boolean setGlobalMessageEnabled(MessageTypes action, boolean enabled){
    //    if(isGlobalMessageEnabled(action) == enabled)
    //        return false;
    //    config.set("config.global-"+  action.toString().toLowerCase() + "-message-enabled", enabled);
    //    saveConfig();
    //    return true;
    //}

    public void resetColoredMessage(Player p, MessageTypes action) {
        if (config.contains("users." + p.getName() + "." + action.toString().toLowerCase())) {
            config.set("users." + p.getName() + "." + action.toString().toLowerCase(), null);
            saveConfig();
        }
    }

    public void resetGlobalMessage(MessageTypes action) {
        config.set("config.global-" + action.toString().toLowerCase() + "-message", null);
        saveConfig();
    }

    public boolean permsRequired() {
        return config.getBoolean("config.must-have-permissions-to-show-current-messages");
    }

    public String getColorsString() {
        return colorString;
    }

    public String getMessagePrefix() {
        return config.getString("config.message-prefix");
    }

    public String replaceVars(String str, Player p) {
        str = str.replaceAll("/name", p.getName());
        str = str.replaceAll("/nname", p.getDisplayName());
        str = str.replaceAll("/count", String.valueOf(Bukkit.getOfflinePlayers().length));
        str = str.replaceAll("/prefix", CustomMessages.p.vault.getPrefix(p));
        str = str.replaceAll("/suffix", CustomMessages.p.vault.getSuffix(p));
        str = str.replaceAll("/group", CustomMessages.p.vault.getGroup(p));
        str = str.replaceAll("/world", p.getWorld().getName());
        return str;
    }

    public String translateColor(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }
}

