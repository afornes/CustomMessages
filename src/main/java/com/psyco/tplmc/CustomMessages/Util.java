package com.psyco.tplmc.CustomMessages;

import org.bukkit.ChatColor;

public class Util {

    public static String getSpacedString(String[] args, int start, int end){
        if(args == null || start > end || (args.length + 1) <= end)
            throw new IllegalArgumentException();
        if(start == end)
            return args[start];
        StringBuilder sb = new StringBuilder();
        for(int i = start; i < end; i++){
            sb.append(args[i]).append(" ");
        }
        return sb.toString().trim();
    }

    public static String[] arrayPart(String[] args, int start, int length){
        String[] newArgs = new String[length - start];
        if(newArgs.length == 0)
            return newArgs;
        System.arraycopy(args, start, newArgs, start - start, length - start);
        return newArgs;
    }

    public static String translateColor(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }
}
