package com.psyco.tplmc.CustomMessages;

import com.psyco.tplmc.CustomMessages.commands.CommandManager;
import com.psyco.tplmc.CustomMessages.configuration.Configuration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomMessages extends JavaPlugin {

    public static CustomMessages p;
    private Configuration config;
    private VaultCompat vault;

    public CustomMessages() {
        super();
        p = this;
    }

    @Override
    public void onDisable() {
        p = null;
        vault.unhookVaultChat();
        vault.unhookVaultPerm();
        CommandManager.getInstance().releaseMemory();
    }

    @Override
    public void onEnable() {
        config = new Configuration(this);
        config.loadConfig();
        PlayerListener playerL = new PlayerListener();
        ServerListener server = new ServerListener();
        getServer().getPluginManager().registerEvents(playerL, this);
        getServer().getPluginManager().registerEvents(server, this);
        getCommand("cm").setExecutor(CommandManager.getInstance());
        CommandManager.getInstance().initCommands();
        vault = new VaultCompat();
        checkVault();
    }

    public void checkVault() {
        Plugin pl = getServer().getPluginManager().getPlugin("Vault");
        if (pl != null) {
            getLogger().info("Vault found, hooking into it");
            if (vault.hookVaultChat()) {
                getLogger().info("Hooked into Vault chat");
            } else {
                getLogger().info("Failed to hook into Vault chat");
            }
            if (vault.hookVaultPerm()) {
                getLogger().info("Hooked into Vault permissions");
            } else {
                getLogger().info("Failed to hook into Vault permissions");
            }
            return;
        }
        getLogger().info("Vault not found. Prefixes, Suffixes, and Groups will show up blank");
    }

    public static Configuration getConfiguration() {
        return p.config;
    }

    public static VaultCompat getVaultCompat() {
        return p.vault;
    }
}