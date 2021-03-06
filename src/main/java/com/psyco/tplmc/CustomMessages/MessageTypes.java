package com.psyco.tplmc.CustomMessages;

public enum MessageTypes {
    JOIN("join"),
    QUIT("quit"),
    FIRSTJOIN("firstjoin"),
    KICK("kick");

    private final String config;

    private MessageTypes(String configString){
        config = configString;
    }

    public String getConfig(){
        return config;
    }
}
