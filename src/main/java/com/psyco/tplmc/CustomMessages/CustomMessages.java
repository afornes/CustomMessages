package com.psyco.tplmc.CustomMessages;

import com.psyco.tplmc.CustomMessages.configuration.Configuration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomMessages extends JavaPlugin {

    public Configuration config;
    public static CustomMessages p;
    public VaultCompat vault;

    public CustomMessages(){
        super();
        p = this;
    }

    @Override
    public void onDisable() {
        p = null;
        vault.unhookVaultChat();
        vault.unhookVaultPerm();
    }

    @Override
    public void onEnable() {
        config = new Configuration(this);
        config.loadConfig();
        pListener playerL = new pListener();
        ServerListener server = new ServerListener();
        getServer().getPluginManager().registerEvents(playerL, this);
        getServer().getPluginManager().registerEvents(server, this);
        getCommand("cm").setExecutor(new CmCommand());
        vault = new VaultCompat();
        checkVault();
    }

    public void checkVault() {
        Plugin p = getServer().getPluginManager().getPlugin("Vault");
        if(p != null){
            getLogger().info("Vault found, hooking into it");
            if(vault.hookVaultChat()){
                getLogger().info("Hooked into Vault chat");
            } else {
                getLogger().info("Failed to hook into Vault chat");
            }
            if(vault.hookVaultPerm()){
                getLogger().info("Hooked into Vault permissions");
            } else {
                getLogger().info("Failed to hook into Vault permissions");
            }
            return;
        }
        getLogger().info("Vault not found, prefixes, suffixes, and groups will show up blank");
    }
}