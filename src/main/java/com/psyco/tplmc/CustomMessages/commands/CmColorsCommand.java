package com.psyco.tplmc.CustomMessages.commands;

import com.psyco.tplmc.CustomMessages.CustomMessages;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmColorsCommand extends CommandBase {

    private CmColorsCommand(){
        CommandManager.getInstance().registerCommand("colors", this);
    }

    static {
        new CmColorsCommand();
    }

    @Override
    public void onPlayerCommand(Player player, String label, String[] args) {
        player.sendMessage(CustomMessages.getConfiguration().getColorsString());
    }

    @Override
    public void onCommandSenderCommand(CommandSender sender, String label, String[] args) {
        sender.sendMessage(CustomMessages.getConfiguration().getColorsString());
    }
}
