package com.psyco.tplmc.CustomMessages.commands;

import com.psyco.tplmc.CustomMessages.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class CommandManager implements CommandExecutor {

    private static CommandManager ourInstance = new CommandManager();

    private final HashMap<String, CommandBase> commands = new HashMap<String, CommandBase>();
    private CommandBase helpCommand;

    private CommandManager() { }

    public void initCommands() {
        helpCommand = new CmHelpCommand();
        registerCommand("help", helpCommand);
        String pack = "com.psyco.tplmc.CustomMessages.commands.";
        try {
            Class.forName(pack + "CmJoinCommand");
            Class.forName(pack + "CmQuitCommand");
            Class.forName(pack + "CmFirstjoinCommand");
            Class.forName(pack + "CmGlobaljoinCommand");
            Class.forName(pack + "CmGlobalquitCommand");
            Class.forName(pack + "CmReloadCommand");
            Class.forName(pack + "CmColorsCommand");
            Class.forName(pack + "CmVariablesCommand");
            Class.forName(pack + "CmKickCommand");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static CommandManager getInstance() {
        return ourInstance;
    }

    public void registerCommand(String command, CommandBase base){
        if(!commands.containsKey(command)){
            commands.put(command, base);
        }
    }

    public void unregisterCommand(String command) {
        commands.remove(command);
    }

    public void releaseMemory(){
        commands.clear();
        helpCommand = null;
        ourInstance = null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(args.length > 0){
            if(commands.containsKey(args[0])){
                if(isPlayer(sender)){
                    commands.get(args[0]).onPlayerCommand(getPlayer(sender), label, Util.arrayPart(args, 1, args.length ));
                } else {
                    commands.get(args[0]).onCommandSenderCommand(sender, label, Util.arrayPart(args, 1, args.length));
                }
            } else {
                showHelp(sender, label);
            }
        } else {
            showHelp(sender, label);
        }
        return true;
    }

    public void showHelp(CommandSender sender, String label){
        if(isPlayer(sender))
            helpCommand.onPlayerCommand(getPlayer(sender), label, null);
        else
            helpCommand.onCommandSenderCommand(sender, label, null);
    }

    public static Player getPlayer(CommandSender sender){
        return isPlayer(sender) ? (Player) sender : null;
    }

    public static boolean isPlayer(CommandSender sender) {
        return sender instanceof Player;
    }
}
