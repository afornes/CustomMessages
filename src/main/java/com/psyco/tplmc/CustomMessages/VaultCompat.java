package com.psyco.tplmc.CustomMessages;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

/**
 * Created with IntelliJ IDEA.
 * User: Patrick
 * Date: 10/14/12
 * Time: 12:29 AM
 * To change this template use File | Settings | File Templates.
 */
public class VaultCompat {

    public Permission perm;
    public Chat chat;
    public boolean isPermHooked = false;
    public boolean isChatHooked = false;

    public boolean hookVaultPerm(){
        RegisteredServiceProvider<Permission> permProvider = Bukkit.getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
        if(permProvider != null){
            perm = permProvider.getProvider();
            isPermHooked = true;
            return true;
        }
        return false;
    }

    public boolean unhookVaultPerm(){
        if(perm == null)
            return false;
        perm = null;
        isPermHooked = false;
        return true;
    }

    public boolean hookVaultChat(){
        RegisteredServiceProvider<Chat> chatProvider = Bukkit.getServer().getServicesManager().getRegistration(net.milkbowl.vault.chat.Chat.class);
        if(chatProvider != null){
            chat = chatProvider.getProvider();
            isChatHooked = true;
            return true;
        }
        return false;
    }

    public boolean unhookVaultChat(){
        if(chat == null)
            return false;
        chat = null;
        isChatHooked = false;
        return true;
    }

    public String getPrefix(Player p){
        if(chat == null)
            return "";
        return chat.getPlayerPrefix(p);
    }

    public String getSuffix(Player p){
        if(chat == null)
            return "";
        return chat.getPlayerSuffix(p);
    }

    public String getGroup(Player p){
        if(perm == null)
            return "";
        return perm.getPrimaryGroup(p);
    }
}