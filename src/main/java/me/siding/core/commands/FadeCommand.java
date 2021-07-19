package me.siding.core.commands;

import me.siding.core.utils.Color;
import me.siding.core.utils.Title;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FadeCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        String message = String.join(" ", args);

        if (args.length == 0) {
            sender.sendMessage(Color.translate("&cUsage: /fade <player> <text>"));
        }
        Player target = Bukkit.getPlayer(args[0]);


        if (args.length == 1) {


            sender.sendMessage(Color.translate("&cUsage: /fade <player> <text>"));
        }

        if (args.length > 2) {
            StringBuilder str = new StringBuilder();
            for (int i = 1; i < args.length; ++i) {
                str.append(args[i] + " ");
            }
            if (message.equals("")) {
                sender.sendMessage(ChatColor.RED + "Please supply a message!");
            } else {
                Title title = new Title(message, "", 10, 40, 10);
                title.setTitleColor(ChatColor.GOLD);
                title.setSubtitleColor(ChatColor.GREEN);
                title.setTimingsToTicks(); // IMPORTANT
                title.send(target);
            }


        }
        return false;
    }
}
