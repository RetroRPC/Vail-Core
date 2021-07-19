package me.siding.core.commands;

import me.siding.core.Main;
import me.siding.core.tasks.RebootTask;
import me.siding.core.utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TemplarCommand implements CommandExecutor {

    public static boolean restart_task_exists = false;

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        try {
            if (player.hasPermission("core.admin")) {
                if (args.length == 0) {
                    player.sendMessage(Color.translate(""));
                    player.sendMessage(Color.translate("&6&lTemplar&f " + Main.getInstance().getDescription().getVersion()));
                    player.sendMessage(Color.translate("&eUsage: /templar <debug>"));
                    player.sendMessage(Color.translate("&eUsage: /templar <reboot>"));
                    player.sendMessage(Color.translate("&eUsage: /templar <reload> (may disable certain features)"));
                    player.sendMessage(Color.translate(""));
                }
                switch (args[0].toLowerCase()) {
                    case "debug":
                        player.sendMessage(Color.translate("&6[TEMPLAR]&f Debug Started... Activating"));
                        break;
                    case "reload":
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            all.playSound(all.getLocation(), Sound.ENDERDRAGON_WINGS, 3.0f, 2.0f);
                        }
                        Main.getInstance().reloadConfig();
                        player.sendMessage(Color.translate("&6[TEMPLAR] &FYou have reloaded the config."));
                        Bukkit.broadcastMessage("");
                        Bukkit.broadcastMessage(Color.translate("&6&l*** A TEMPLAR RELOAD HAS HAPPENED *** "));
                        Bukkit.broadcastMessage("");
                        Bukkit.broadcastMessage(Color.translate("&c&l** Some features might be disabled ***"));
                        Bukkit.broadcastMessage(Color.translate("&7This occurs when a administrator re-enables templar."));
                        Bukkit.broadcastMessage("");
                    case "reboot":
                        if (restart_task_exists) {
                            sender.sendMessage(ChatColor.YELLOW + "There is already a pending restart task running.");
                            return true;
                        }
                        restart_task_exists = true;
                        (new RebootTask(false)).runTaskTimer(Main.plugin, 20L, 20L);
                }
            } else {
                player.sendMessage(Color.translate("&6[TEMPLAR] &fYou do not have permissions."));
                return true;
            }

        } catch (Exception ex) {
            return false;
        }
        return true;
    }
}

