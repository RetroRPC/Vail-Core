package me.siding.core.commands;

import me.siding.core.Main;
import me.siding.core.utils.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StoreCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;

        if (!(sender instanceof Player)) {
            sender.sendMessage(Color.translate("&cYou are not a player!"));
            return false;
        }

        for (String s : Main.getInstance().getConfig().getStringList("store")) {
            sender.sendMessage(Color.translate(s));
        }
        return true;
    }
}
