package me.siding.core.commands;

import me.siding.core.utils.Color;
import me.siding.core.utils.TimeUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LagCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] strings) {


        Player p = (Player) sender;


        //double lag = Math.round((1.0D - tps / 20.0D) * 100.0D);

        // server specs

        Runtime runtime = Runtime.getRuntime();
        long maxMemory = runtime.maxMemory();
        long freeMemory = runtime.freeMemory();


        sender.sendMessage(Color.translate("&7&l<!> &aVailPvP Server Statistics &7&l<!>"));
        sender.sendMessage("");
        sender.sendMessage(Color.translate("&7  - aUptime&7: &f" + TimeUtil.Uptime()));
        sender.sendMessage(Color.translate("&7  - aServer TPS&7: &aneed to find a way to get tps"));
        sender.sendMessage(Color.translate("&7  - &aOnline Players&7:&f " + Bukkit.getServer().getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers()));
        sender.sendMessage(Color.translate("&7  - &aScheduled Reboot: " + ""));
        sender.sendMessage("");


        return true;
    }
}

