package me.siding.core.commands;

import me.siding.core.utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BroadcastCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {


        if (!(sender.hasPermission("core.broadcast"))) {
            sender.sendMessage("no perms");
            return true;
        }
        if (cmd.getName().equalsIgnoreCase("broadcast")) {
            String r = "";
            if (args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    r = r + args[i] + " ";
                }
                r = r.replace("&s", "§");
                Bukkit.broadcastMessage("");
                Bukkit.broadcastMessage("");
                Bukkit.broadcastMessage(Color.translate("&8&l<&a&lVail&f&lPvP&8&l> &f" + r));
                Bukkit.broadcastMessage("");
                Bukkit.broadcastMessage("");

                for (Player pl : Bukkit.getServer().getOnlinePlayers())
                    pl.playSound(pl.getLocation(), Sound.CHICKEN_EGG_POP, 2.0F, 0.75F);
            } else {
                sender.sendMessage(ChatColor.RED + "Usage: /broadcast <message>");
            }
        }
        return true;
    }

}

