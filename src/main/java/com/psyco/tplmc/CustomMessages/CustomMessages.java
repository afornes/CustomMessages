package com.psyco.tplmc.CustomMessages;

import com.psyco.tplmc.CustomMessages.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomMessages extends JavaPlugin {

    public Configuration config;
    public static CustomMessages p;

    public CustomMessages(){
        super();
        p = this;
    }

    @Override
    public void onDisable() {
        p = null;
    }

    @Override
    public void onEnable() {
        config = new Configuration(this);
        config.loadConfig();
        pListener playerL = new pListener();
        getServer().getPluginManager().registerEvents(playerL, this);
        getCommand("cm").setExecutor(new CmCommand());
    }
}